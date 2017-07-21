package com.wongxd.partymanage.party.threeAndOne

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.google.gson.Gson
import com.orhanobut.logger.Logger
import com.wongxd.partymanage.App
import com.wongxd.partymanage.R
import com.wongxd.partymanage.home.bean.StudyRecordBean
import com.wongxd.partymanage.utils.SystemBarHelper
import com.wongxd.partymanage.utils.conf.UrlConf
import com.wongxd.partymanage.utils.net.WNetUtil
import com.zhy.http.okhttp.OkHttpUtils
import kotlinx.android.synthetic.main.aty_study_record.*
import okhttp3.Call

class StudyRecordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.aty_study_record)
        SystemBarHelper.tintStatusBar(this, resources.getColor(R.color.app_red), 0f)
        initRecycleView()
        getList(false)
        srl_study_record.post({ srl_study_record.isRefreshing = true })
        iv_return.setOnClickListener { finish() }
        iv_add.setOnClickListener { startActivity(Intent(this, AddStudyRecordActivity::class.java)) }

    }


    internal var adapter: RvAdapter = RvAdapter()

    private fun initRecycleView() {
        //设置样式刷新显示的位置
        srl_study_record.setProgressViewOffset(true, 10, 150)
        srl_study_record.setColorSchemeResources(R.color.swiperefresh_color1, R.color.swiperefresh_color2,
                R.color.swiperefresh_color3, R.color.swiperefresh_color4)

        rv_study_record.adapter = adapter
        rv_study_record.layoutManager = LinearLayoutManager(applicationContext)
        adapter.setEmptyView(R.layout.item_rv_empty, rv_study_record)

        srl_study_record.setOnRefreshListener(
                {
                    pageNo = 1
                    type = 1
                    getList(false)
                })


        adapter.setOnItemClickListener { adapter3, view, position ->
            val intent = Intent(this, AddStudyRecordActivity::class.java).apply {
                putExtra("isEdit", true)
                putExtra("typeId", adapter.data[position].typeId)
                putExtra("connId", adapter.data[position].connId)
                putExtra("id", adapter.data[position].id)
            }
            startActivity(intent)
        }

        // 滑动最后一个Item的时候回调onLoadMoreRequested方法
        adapter.setOnLoadMoreListener({ getList(true) }, rv_study_record)


    }

    internal var pageNo = 1
    internal var type = 1

    private fun getList(isLoadMore: Boolean) {
        val url = UrlConf.StudyRecordListUrl
        val builder = OkHttpUtils.post().url(url)
                .tag(this)
                .addParams("token", App.token)
                .addParams("pageNo", pageNo.toString())
                .addParams("pageSize", "10")

        WNetUtil.StringCallBack(builder, url, this, object : WNetUtil.WNetStringCallback {
            override fun success(response: String, id: Int) {
                Logger.e(response)
                srl_study_record.isRefreshing = false
                val gson = Gson()
                val bean = gson.fromJson(response, StudyRecordBean::class.java)
                if (bean.code == 100) {
                    val totalPage = bean.data.page.totalPage
                    if (isLoadMore) {
                        if (pageNo > totalPage) {
                            adapter.loadMoreEnd()
                        } else {
                            adapter.addData(bean.data.experienceList)
                            adapter.loadMoreComplete()
                        }
                    } else {
                        adapter.setNewData(bean.data.experienceList)
                        // 检查是否满一屏，如果不满足关闭loadMore
                        //adapter.disableLoadMoreIfNotFullPage(bindingView.rvHome);
                    }
                    if (bean.data.experienceList.size != 0)
                        pageNo++
                } else {
                    if (isLoadMore)
                        adapter.loadMoreFail()

                }
            }

            override fun error(call: Call, e: Exception, id: Int) {
                srl_study_record.isRefreshing = false
//                if (isLoadMore) adapter.loadMoreFail()
            }
        })
    }

    internal class RvAdapter : BaseQuickAdapter<StudyRecordBean.DataBean.ExperienceBean, BaseViewHolder>(R.layout.item_rv_study_record) {

        override fun convert(helper: BaseViewHolder, item: StudyRecordBean.DataBean.ExperienceBean) {
            var imgRes = 0
            when (item.typeId) {
            //会议类型 1：党课 2：党委会：3：支委会4：支部大会
                1.toString() -> imgRes = R.drawable.ke
                2.toString() -> imgRes = R.drawable.dang
                3.toString() -> imgRes = R.drawable.zhi
                4.toString() -> imgRes = R.drawable.zhibuhui
            }

            helper.setBackgroundRes(R.id.iv_type, imgRes)
                    .setText(R.id.tv_title, item.title)
                    .setText(R.id.tv_time, item.time)
                    .setText(R.id.tv_content, item.comment)
        }


    }
}
