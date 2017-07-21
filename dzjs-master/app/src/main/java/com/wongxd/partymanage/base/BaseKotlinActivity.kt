package com.wongxd.partymanage.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.zhy.http.okhttp.OkHttpUtils
import kotlin.properties.Delegates

/**
 * Created by wxd1 on 2017/6/29.
 */
open class BaseKotlinActivity : AppCompatActivity() {
    protected var netTag: Any by Delegates.notNull()

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        netTag = this
    }

     override fun onDestroy() {
        OkHttpUtils.getInstance().cancelTag(netTag)
        super.onDestroy()
    }
}