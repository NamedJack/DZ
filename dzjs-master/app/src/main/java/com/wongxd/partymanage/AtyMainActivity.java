package com.wongxd.partymanage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.wongxd.partymanage.base.BaseBindingActivity;
import com.wongxd.partymanage.base.BaseBindingFragment;
import com.wongxd.partymanage.databinding.AtyMainBinding;
import com.wongxd.partymanage.home.HomeFragment;
import com.wongxd.partymanage.me.MeFragment;
import com.wongxd.partymanage.mqtt.bean.PushMessage;
import com.wongxd.partymanage.mqtt.service.MqttService;
import com.wongxd.partymanage.msg.MsgFragment;
import com.wongxd.partymanage.party.PartyFragment;
import com.wongxd.partymanage.utils.SystemBarHelper;
import com.wongxd.partymanage.widget.bnve.BottomNavigationViewEx;

import java.util.ArrayList;
import java.util.List;

public class AtyMainActivity extends BaseBindingActivity<AtyMainBinding> {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_main);
        startService(new Intent(this, MqttService.class));
        SystemBarHelper.tintStatusBar(this, getResources().getColor(R.color.app_red), 0f);
        initData();
    }


    private void initData() {
        List<Fragment> fragments = new ArrayList<>(3);

        // create  fragment and add it
        HomeFragment homeFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", "首页");
        homeFragment.setArguments(bundle);
        // add to fragments for adapter
        fragments.add(homeFragment);
//
//        // create  fragment and add it
//        BaseBindingFragment activityFragment = new ActivityFragment();
//        Bundle bActivity = new Bundle();
//        bActivity.putString("title", "活动");
//        activityFragment.setArguments(bActivity);
//        // add to fragments for adapter
//        fragments.add(activityFragment);

        // create  fragment and add it
        BaseBindingFragment partyFragment = new PartyFragment();
        Bundle bParty = new Bundle();
        bParty.putString("title", "党务管理");
        partyFragment.setArguments(bParty);
        // add to fragments for adapter
        fragments.add(partyFragment);

        // create  fragment and add it
        BaseBindingFragment msgFragment = new MsgFragment();
        Bundle bMsg = new Bundle();
        bMsg.putString("title", "推送消息");
        msgFragment.setArguments(bMsg);
        // add to fragments for adapter
        fragments.add(msgFragment);

        // create  fragment and add it
        BaseBindingFragment meFragment = new MeFragment();
        Bundle bMe = new Bundle();
        bMe.putString("title", "个人中心");
        meFragment.setArguments(bMe);
        // add to fragments for adapter
        fragments.add(meFragment);


        // set adapter
        VpAdapter adapter = new VpAdapter(getSupportFragmentManager(), fragments);
        bindingView.vpMain.setAdapter(adapter);
        bindingView.vpMain.setOffscreenPageLimit(5);

        // binding with ViewPager
        bindingView.bnveMain.setupWithViewPager(bindingView.vpMain);

        bindingView.bnveMain.enableShiftingMode(false);
        bindingView.bnveMain.enableItemShiftingMode(false);
        bindingView.bnveMain.setTextVisibility(false);
        // set icon size
        int iconSize = 35;
        bindingView.bnveMain.setIconSize(iconSize, iconSize);
        // set item height
        bindingView.bnveMain.setItemHeight(BottomNavigationViewEx.dp2px(this, iconSize + 16));
    }


    @Override
    protected void onResume() {
        Intent intent = getIntent();
        boolean isPushMessage = intent.getBooleanExtra("isPushMessage",false);
        Log.e("msg",""+isPushMessage);
        if(isPushMessage){
            bindingView.bnveMain.setCurrentItem(2);
            PushMessage message = (PushMessage) intent.getSerializableExtra("message");
        }
        super.onResume();
    }

    /**
     * view pager adapter
     */
    private static class VpAdapter extends FragmentPagerAdapter {
        private List<Fragment> data;

        public VpAdapter(FragmentManager fm, List<Fragment> data) {
            super(fm);
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Fragment getItem(int position) {
            return data.get(position);
        }
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }
}
