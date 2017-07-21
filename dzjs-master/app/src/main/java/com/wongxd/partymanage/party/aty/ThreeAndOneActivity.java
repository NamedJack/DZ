package com.wongxd.partymanage.party.aty;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.wongxd.partymanage.R;
import com.wongxd.partymanage.base.BaseBindingActivity;
import com.wongxd.partymanage.databinding.AtyThreeAndOneBinding;
import com.wongxd.partymanage.party.threeAndOne.fgt.HistoryMsgFragment;
import com.wongxd.partymanage.party.threeAndOne.fgt.NewMsgFragmene;
import com.wongxd.partymanage.utils.SystemBarHelper;

/**
 * 三会一课
 */
public class ThreeAndOneActivity extends BaseBindingActivity<AtyThreeAndOneBinding> {
    int colorUnSelected = Color.parseColor("#afb6b5");
    int colorSeleted = Color.parseColor("#ffffff");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_three_and_one);
        SystemBarHelper.tintStatusBar(this, ContextCompat.getColor(getApplicationContext(), R.color.app_red), 0f);
        initFragment();
        initView();
    }

    private void initView() {
        bindingView.rlNew.setOnClickListener(v -> {
            bindingView.tvHistory.setTextSize(14);
            bindingView.vHistory.setVisibility(View.GONE);
            bindingView.tvHistory.setTextColor(colorUnSelected);
            bindingView.tvNew.setTextSize(16);
            bindingView.vNew.setVisibility(View.VISIBLE);
            bindingView.tvNew.setTextColor(colorSeleted);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.hide(historyMsgFragment)
                    .show(msgFragmene);
            fragmentTransaction.commit();
        });

        bindingView.rlHistory.setOnClickListener(v -> {
            bindingView.tvHistory.setTextSize(16);
            bindingView.vHistory.setVisibility(View.VISIBLE);
            bindingView.tvHistory.setTextColor(colorSeleted);
            bindingView.tvNew.setTextSize(14);
            bindingView.vNew.setVisibility(View.GONE);
            bindingView.tvNew.setTextColor(colorUnSelected);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            if (historyMsgFragment.isAdded()) {
                fragmentTransaction.hide(msgFragmene)
                        .show(historyMsgFragment);
                fragmentTransaction.commit();
            }else {
                fragmentTransaction.add(R.id.fl_content,historyMsgFragment,"history").hide(msgFragmene).commit();
            }
        });

        bindingView.rlReturn.setOnClickListener(v -> finish());


    }

    NewMsgFragmene msgFragmene = new NewMsgFragmene();
    HistoryMsgFragment historyMsgFragment = new HistoryMsgFragment();

    private void initFragment() {
        FragmentManager mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction().add(R.id.fl_content, msgFragmene, "new").commit();
    }


}
