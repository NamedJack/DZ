package com.wongxd.partymanage.base;


import android.animation.Animator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.ArcMotion;
import android.transition.ChangeBounds;
import android.transition.TransitionValues;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wongxd.partymanage.R;
import com.wongxd.partymanage.utils.SystemBarHelper;
import com.zhy.http.okhttp.OkHttpUtils;

/**
 * baseActivity的父类，解耦预留
 */
public class BaseNetStateActivity extends AppCompatActivity {

//    private LinearLayout rootLayout;
//    private LinearLayout ll_fillToStatuBar;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        // 这句很关键，注意是调用父类的方法
//        super.setContentView(R.layout.activity_base);
//        // 经测试在代码里直接声明透明状态栏更有效
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
//            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
//        }
//    }
//
//    @Override
//    public void setContentView(int layoutId) {
//        setContentView(View.inflate(this, layoutId, null));
//    }
//
//    @Override
//    public void setContentView(View view) {
//        rootLayout = (LinearLayout) findViewById(R.id.root_layout);
//        ll_fillToStatuBar = (LinearLayout) rootLayout.findViewById(R.id.ll_fillToStatuBar);
//        if (rootLayout == null) return;
//        rootLayout.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//    }
//
//    protected void setStatuBarColor(@ColorRes int res) {
//        ll_fillToStatuBar.setBackground(new ColorDrawable(getResources().getColor(res)));
//        }

    protected Object netTag;
    private CheckNetReceive netCheckReceive;
    private TextView tvNoNet;
    protected boolean isNoNet = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.aty_base);
        SystemBarHelper.tintStatusBar(getWindow(), getResources().getColor(R.color.app_color));
        tvNoNet = (TextView) findViewById(R.id.tv_no_net);
//网络tag
        netTag = this;


        //注册检查网络的广播
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        netCheckReceive = new CheckNetReceive();
        this.registerReceiver(netCheckReceive, filter);

    }


    @Override
    public void setContentView(int layoutId) {
        setContentView(View.inflate(this, layoutId, null));
    }

    @Override
    public void setContentView(View view) {
        LinearLayout rootLayout = (LinearLayout) findViewById(R.id.ll_root_layout);
        if (rootLayout == null) return;
        rootLayout.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }


    @Override
    protected void onDestroy() {
        OkHttpUtils.getInstance().cancelTag(netTag);
        this.unregisterReceiver(netCheckReceive);
        super.onDestroy();
    }


    @Override
    protected void onStart() {
        super.onStart();

    }


    /**
     * 设置自定义 Shared Element切换动画
     * 默认不开启曲线路径切换动画，
     * 开启需要重写setHeaderPicView()，和调用此方法并将isShow值设为true
     *
     * @param imageView 共享的图片
     * @param isShow    是否显示曲线动画
     */
    protected void setMotion(ImageView imageView, boolean isShow) {
        if (!isShow) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //定义ArcMotion
            ArcMotion arcMotion = new ArcMotion();
            arcMotion.setMinimumHorizontalAngle(50f);
            arcMotion.setMinimumVerticalAngle(50f);
            //插值器，控制速度
            Interpolator interpolator = AnimationUtils.loadInterpolator(this, android.R.interpolator.fast_out_slow_in);

            //实例化自定义的ChangeBounds
            CustomChangeBounds changeBounds = new CustomChangeBounds();
            changeBounds.setPathMotion(arcMotion);
            changeBounds.setInterpolator(interpolator);
            changeBounds.addTarget(imageView);
            //将切换动画应用到当前的Activity的进入和返回
            getWindow().setSharedElementEnterTransition(changeBounds);
            getWindow().setSharedElementReturnTransition(changeBounds);
        }
    }


    public class CustomChangeBounds extends ChangeBounds {

        @Override
        public Animator createAnimator(final ViewGroup sceneRoot,
                                       TransitionValues startValues,
                                       final TransitionValues endValues) {

            Animator changeBounds = super.createAnimator(sceneRoot, startValues, endValues);
            if (startValues == null || endValues == null || changeBounds == null)
                return null;

//        if (endValues.view instanceof ViewGroup) {
//            ViewGroup vg = (ViewGroup) endValues.view;
//            float offset = vg.getHeight() / 3;
//            for (int i = 0; i < vg.getChildCount(); i++) {
//                View v = vg.getChildAt(i);
//                v.setTranslationY(offset);
//                v.setAlpha(0f);
//                v.animate()
//                        .alpha(1f)
//                        .translationY(0f)
//                        .setDuration(150)
//                        .setStartDelay(150)
//                        .setInterpolator(AnimationUtils.loadInterpolator(vg.getContext(),
//                                android.R.interpolator.fast_out_slow_in));
//                offset *= 1.8f;
//            }
//        }

            changeBounds.setDuration(500);
            changeBounds.setInterpolator(AnimationUtils.loadInterpolator(sceneRoot.getContext(),
                    android.R.interpolator.fast_out_slow_in));
            return changeBounds;
        }

    }

    /***
     * 检查网络状态的广播
     */
    private class CheckNetReceive extends BroadcastReceiver {
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

            if (isNoNet) tvNoNet.setVisibility(View.VISIBLE);
            else tvNoNet.setVisibility(View.GONE);

        }
    }

}