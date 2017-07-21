package com.wongxd.partymanage.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.wongxd.partymanage.R;
import com.wongxd.partymanage.databinding.ActivityBaseBinding;
import com.zhy.http.okhttp.OkHttpUtils;


/**
 * Created by wxd1 on 17/5/25.
 */
public class BaseBindingActivity<SV extends ViewDataBinding> extends AppCompatActivity {

    private ActivityBaseBinding mBaseBinding;
    // 布局view
    protected SV bindingView;

    //####################网络检查
    protected Object netTag;
    private CheckNetReceive netCheckReceive;
    protected boolean isNoNet = false;

    protected <T extends View> T getView(int id) {
        return (T) findViewById(id);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        SystemBarHelper.tintStatusBar(getWindow(), getResources().getColor(R.color.app_color));
        //网络tag
        netTag = this;
        //注册检查网络的广播
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        netCheckReceive = new CheckNetReceive();
        this.registerReceiver(netCheckReceive, filter);

    }

    @Override
    protected void onDestroy() {
        OkHttpUtils.getInstance().cancelTag(netTag);
        this.unregisterReceiver(netCheckReceive);
        super.onDestroy();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {

        mBaseBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_base, null, false);

        bindingView = DataBindingUtil.inflate(getLayoutInflater(), layoutResID, null, false);

        // content
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        bindingView.getRoot().setLayoutParams(params);
        RelativeLayout mContainer = (RelativeLayout) mBaseBinding.getRoot().findViewById(R.id.container);
        mContainer.addView(bindingView.getRoot());
        getWindow().setContentView(mBaseBinding.getRoot());

    }

    @Override
    protected void onResume() {
        super.onResume();
//        if (!isNoNet) mBaseBinding.tvNoNet.setVisibility(View.GONE);
    }

    /***
     * 检查网络状态的广播
     */
    class CheckNetReceive extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            NetworkInfo.State wifiState = null;
            NetworkInfo.State mobileState = null;
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            wifiState = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
            mobileState = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
            if (wifiState != null && mobileState != null
                    && NetworkInfo.State.CONNECTED != wifiState
                    && NetworkInfo.State.CONNECTED == mobileState) {
                // 手机网络连接成功
                isNoNet = false;
            } else if (wifiState != null && mobileState != null
                    && NetworkInfo.State.CONNECTED != wifiState
                    && NetworkInfo.State.CONNECTED != mobileState) {
                // 手机没有任何的网络
                isNoNet = true;

            } else if (wifiState != null && NetworkInfo.State.CONNECTED == wifiState) {
                // 无线网络连接成功
                isNoNet = false;
            }

//            if (isNoNet) mBaseBinding.tvNoNet.setVisibility(View.VISIBLE);
//            else mBaseBinding.tvNoNet.setVisibility(View.GONE);

        }
    }

}
