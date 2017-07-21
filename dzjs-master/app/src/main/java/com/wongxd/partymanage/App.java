package com.wongxd.partymanage;

import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.wongxd.partymanage.utils.TU;
import com.wongxd.partymanage.utils.net.interceptor.RequestInterceptor;
import com.wongxd.partymanage.utils.net.interceptor.ResponseInterceptor;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by wxd1 on 2017/5/25.
 */

public class App extends Application {
    public static Context instance;
    public static String token;
    public static boolean isDeBug = true;
    public static String id;

    public static Context getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggerInterceptor("党政建设网络请求"))
                .addInterceptor(new RequestInterceptor())
                .addInterceptor(new ResponseInterceptor())
                .connectTimeout(8000L, TimeUnit.MILLISECONDS)
                .readTimeout(8000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);

        TU.register(this);

        Logger.init().logLevel(LogLevel.FULL);

//        LoadDataLayout.getBuilder()
//                .setLoadingText(getString(R.string.custom_loading_text))
//                .setLoadingTextSize(16)
//                .setLoadingTextColor(R.color.colorPrimary)
//                //.setLoadingViewLayoutId(R.layout.custom_loading_view) //如果设置了自定义loading视图,上面三个方法失效
//                .setEmptyImgId(R.drawable.ic_empty)
//                .setErrorImgId(R.drawable.ic_error)
//                .setNoNetWorkImgId(R.drawable.ic_no_network)
//                .setEmptyImageVisible(true)
//                .setErrorImageVisible(true)
//                .setNoNetWorkImageVisible(true)
//                .setEmptyText(getString(R.string.custom_empty_text))
//                .setErrorText(getString(R.string.custom_error_text))
//                .setNoNetWorkText(getString(R.string.custom_nonetwork_text))
//                .setAllTipTextSize(16)
//                .setAllTipTextColor(R.color.text_color_child)
//                .setAllPageBackgroundColor(R.color.pageBackground)
//                .setReloadBtnText(getString(R.string.custom_reloadBtn_text))
//                .setReloadBtnTextSize(16)
//                .setReloadBtnTextColor(R.color.colorPrimary)
//                .setReloadBtnBackgroundResource(R.drawable.selector_btn_normal)
//                .setReloadBtnVisible(true)
//                .setReloadClickArea(LoadDataLayout.FULL);

    }
}
