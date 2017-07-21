package com.wongxd.partymanage.party.threeAndOne

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.gson.Gson
import com.orhanobut.logger.Logger
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import com.wongxd.partymanage.App
import com.wongxd.partymanage.R
import com.wongxd.partymanage.TakeImgActivity
import com.wongxd.partymanage.base.BaseKotlinActivity
import com.wongxd.partymanage.party.threeAndOne.bean.StudyRecordDetailBean
import com.wongxd.partymanage.utils.DensityUtil
import com.wongxd.partymanage.utils.SystemBarHelper
import com.wongxd.partymanage.utils.TU
import com.wongxd.partymanage.utils.conf.UrlConf
import com.wongxd.partymanage.utils.net.WNetUtil
import com.zhy.http.okhttp.OkHttpUtils
import kotlinx.android.synthetic.main.aty_add_study_record.*
import okhttp3.Call
import org.json.JSONObject
import java.io.File
import java.lang.Exception
import java.util.*
import kotlin.collections.HashSet


class AddStudyRecordActivity : BaseKotlinActivity(), DatePickerDialog.OnDateSetListener {
    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        val date = year.toString() + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日"
        tv_time_picker.text = date
    }

    var isEdit = false
    var typeId: String? = ""
    var connId: String? = ""
    var id: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SystemBarHelper.tintStatusBar(this, resources.getColor(R.color.app_red), 0f)
        setContentView(R.layout.aty_add_study_record)

        isEdit = intent.getBooleanExtra("isEdit", false)
        typeId = intent?.getStringExtra("typeId")
        connId = intent?.getStringExtra("connId")
        id = intent?.getStringExtra("id")
        when (id) {
            "" -> finish()
            else -> initView()
        }

        when (isEdit) {
            true -> btn_submit.setText("修改")
        }
        getDetail()
    }

    /**
     * base  id  query info
     */
    private fun getDetail() {
        val url = UrlConf.StudyRecordDetailUrl
        WNetUtil.StringCallBack(OkHttpUtils.post().url(url).tag(netTag).addParams("id", id).addParams("token", App.token)
                , url, this
                , object : WNetUtil.WNetStringCallback {
            override fun success(response: String?, id: Int) {
                Logger.e(response)
                val bean = Gson().fromJson(response, StudyRecordDetailBean::class.java)
                tv_time_picker.text = bean?.data?.time
                et_title.setText(bean?.data?.title)
                et_content.setText(bean?.data?.comment)
                if (bean?.data?.imgs == null) return
                for (path in bean.data?.imgs!!) {
                    val iv = ImageView(applicationContext)
                    iv.adjustViewBounds = true
                    val lp = LinearLayout.LayoutParams(DensityUtil.dip2px(applicationContext, 80f), LinearLayout.LayoutParams.MATCH_PARENT)
                    iv.layoutParams = lp
                    iv.setOnClickListener {
                        imgPath.remove(path)
                        ll_imgs.removeView(iv)
                        Logger.e(imgPath.size.toString() + "个imgPath")
                    }
                    Glide.with(applicationContext)
                            .load(UrlConf.HOST + path)
                            .skipMemoryCache(true)
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .into(iv)
                    ll_imgs.addView(iv)
                }
            }

            override fun error(call: Call?, e: Exception?, id: Int) {
            }
        })
    }


    private fun initView() {
        iv_return.setOnClickListener { finish() }
        tv_time_picker.setOnClickListener {
            val now = Calendar.getInstance()
            val dpd: DatePickerDialog = DatePickerDialog.newInstance(
                    this,
                    now.get(Calendar.YEAR),
                    now.get(Calendar.MONTH),
                    now.get(Calendar.DAY_OF_MONTH)
            )
            dpd.show(fragmentManager, "Datepickerdialog")
        }

        tv_img_picker.setOnClickListener {
            startActivityForResult(Intent(this, TakeImgActivity::class.java), 10010)
        }

        btn_submit.setOnClickListener {
            when (isEdit) {
                true -> doChange()
                else -> doAdd()
            }

        }
    }

    var imgPath: MutableSet<String> = HashSet()
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && data != null) {
            val path = data.getStringExtra("path")
            imgPath.add(path)
            Logger.e(path + "  " + imgPath.size.toString() + "个imgPath")
            val iv = ImageView(applicationContext)
            iv.adjustViewBounds = true
            val lp = LinearLayout.LayoutParams(DensityUtil.dip2px(applicationContext, 80f), LinearLayout.LayoutParams.MATCH_PARENT)
            iv.layoutParams = lp
            iv.setOnClickListener {
                imgPath.remove(path)
                ll_imgs.removeView(iv)
                Logger.e(imgPath.size.toString() + "个imgPath")
            }
            Glide.with(applicationContext)
                    .load(path)
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(iv)
            ll_imgs.addView(iv)

//            ll_imgs.removeAllViews()
//
//            for (path in imgPath) {
//                val iv = ImageView(applicationContext)
//                iv.adjustViewBounds = true
//                val lp = LinearLayout.LayoutParams(DensityUtil.dip2px(applicationContext, 80f), LinearLayout.LayoutParams.MATCH_PARENT)
//                iv.layoutParams = lp
//                iv.setOnClickListener {
//                    imgPath.remove(path)
//                    ll_imgs.removeView(iv)
//                    Logger.e(imgPath.size.toString() + "个imgPath")
//                }
//                Glide.with(applicationContext)
//                        .load(path)
//                        .skipMemoryCache(true)
//                        .diskCacheStrategy(DiskCacheStrategy.NONE)
//                        .into(iv)
//                ll_imgs.addView(iv)
//                Logger.e(imgPath.size.toString() + "个imgPath")
//            }

        }
    }

    /**
     * doadd record
     */
    fun doAdd() {
        val title = et_title.text.toString().trim()
        val content = et_content.text.toString().trim()
        val time = tv_time_picker.text.toString().trim().replace("年", "").replace("月", "").replace("日", "")
        val url = UrlConf.AddStudyRecordListUrl
        val builder = OkHttpUtils.post().url(url).tag(netTag)
                .addParams("token", App.token)
                .addParams("typeId", 2.toString())
                .addParams("title", title)
                .addParams("comment", content)
                .addParams("time", time)
        //添加图片
        for (path in imgPath) {
            val fileName = path.substring(path.lastIndexOf("/") + 1)
            val file = File(path)
            builder.addFile("imgs", fileName, file)
        }

        WNetUtil.StringCallBack(builder, url, this, "上传心得中", true, object : WNetUtil.WNetStringCallback {
            override fun error(call: Call?, e: Exception?, id: Int) {
                TU.cT(e?.message)
            }

            override fun success(response: String?, id: Int) {
                Logger.e(response)
                val obj = JSONObject(response)
                TU.cT(obj.optString("msg"))
                if (obj.optInt("code") == 100) {
                    finish()
                }
            }

        }

        )
    }

    /**
     * domodify record
     */
    fun doChange() {
        val url = UrlConf.AddStudyRecordListUrl

        val title = et_title.text.toString().trim()
        val content = et_content.text.toString().trim()
        val time = tv_time_picker.text.toString().trim().replace("年", "").replace("月", "").replace("日", "")
        val builder = OkHttpUtils.post().url(url).tag(netTag)
                .addParams("token", App.token)
                .addParams("typeId", typeId)
                .addParams("connId", connId)
                .addParams("id", id)
                .addParams("title", title)
                .addParams("comment", content)
                .addParams("time", time)


        //添加图片
        for (path in imgPath) {
            val fileName = path.substring(path.lastIndexOf("/") + 1)
            val file = File(path)
            builder.addFile("imgs", fileName, file)
        }
        WNetUtil.StringCallBack(builder, url, this, "上传心得中", true, object : WNetUtil.WNetStringCallback {
            override fun error(call: Call?, e: Exception?, id: Int) {
                TU.cT(e?.message)
            }

            override fun success(response: String?, id: Int) {
                Logger.e(response)
                val obj = JSONObject(response)
                TU.cT(obj.optString("msg"))
                if (obj.optInt("code") == 100) {
                    finish()
                }
            }

        }

        )
    }


}
