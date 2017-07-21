package com.wongxd.partymanage.party.threeAndOne

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.orhanobut.logger.Logger
import com.wongxd.partymanage.R
import com.wongxd.partymanage.party.threeAndOne.bean.MeetingDetailBean
import com.wongxd.partymanage.utils.SystemBarHelper
import kotlinx.android.synthetic.main.aty_meeting_record.*


class MeetingRecordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.aty_meeting_record)
        SystemBarHelper.tintStatusBar(this, resources.getColor(R.color.app_red), 0f)
        val info: MeetingDetailBean.DataBean.MeetingnoticeBean = intent.getSerializableExtra("info") as MeetingDetailBean.DataBean.MeetingnoticeBean
        Logger.e(info.meetingRecord)
        webview.loadData(info.meetingRecord, "text/html; charset=UTF-8", null)//这种写法可以正确解码

        iv_return.setOnClickListener { finish() }

    }


}
