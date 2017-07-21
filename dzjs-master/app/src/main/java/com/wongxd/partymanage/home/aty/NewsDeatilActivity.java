package com.wongxd.partymanage.home.aty;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.wongxd.partymanage.App;
import com.wongxd.partymanage.R;
import com.wongxd.partymanage.base.BaseBindingActivity;
import com.wongxd.partymanage.databinding.AtyNewsDeatilBinding;
import com.wongxd.partymanage.home.bean.NewsDeatilBean;
import com.wongxd.partymanage.utils.TU;
import com.wongxd.partymanage.utils.conf.UrlConf;
import com.wongxd.partymanage.utils.net.WNetUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import okhttp3.Call;

public class NewsDeatilActivity extends BaseBindingActivity<AtyNewsDeatilBinding> {

    private AppCompatActivity thisActivity;
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_news_deatil);
        thisActivity = this;
        mContext = this.getApplicationContext();
        String id = getIntent().getStringExtra("id");
        if (TextUtils.isEmpty(id)) {
            TU.cT("未获取到文章id");
            return;
        }
        setWebView(bindingView.webview);
        getInfo(id);
    }

    private void getInfo(String id) {
        String url = UrlConf.HomeNewsDetailUrl;
        WNetUtil.StringCallBack(OkHttpUtils.post().tag(netTag).url(url).addParams("token", App.token)
                .addParams("id", id), url, thisActivity, "获取新闻详情中", true, new WNetUtil.WNetStringCallback() {
            @Override
            public void success(String response, int id) {
                Logger.e(response);
                NewsDeatilBean newsDeatilBean = new Gson().fromJson(response, NewsDeatilBean.class);
                String url = newsDeatilBean.getUrl();
                bindingView.webview.loadUrl(UrlConf.HOST + url);
            }

            @Override
            public void error(Call call, Exception e, int id) {

            }
        });
    }


    private void setWebView(WebView mWebView) {
        mWebView.getSettings().setJavaScriptEnabled(false);
        mWebView.setVerticalScrollBarEnabled(true);
        mWebView.setHorizontalScrollBarEnabled(false);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    bindingView.pbLoadurl.setVisibility(View.GONE);
                } else {
//                    if (newProgress < 10) newProgress = 10;
                    bindingView.pbLoadurl.setProgress(newProgress);
                }
            }
        });


    }
}
