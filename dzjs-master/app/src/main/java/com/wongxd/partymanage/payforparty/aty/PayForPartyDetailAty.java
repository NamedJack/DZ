package com.wongxd.partymanage.payforparty.aty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.wongxd.partymanage.R;
import com.wongxd.partymanage.base.BaseBindingActivity;
import com.wongxd.partymanage.databinding.AtyPayforPartyDetailBinding;
import com.wongxd.partymanage.payforparty.bean.MoneyOfParty;
import com.wongxd.partymanage.utils.SystemBarHelper;

/**
 * Created by zyj on 2017/7/22.
 */

public class PayForPartyDetailAty extends BaseBindingActivity<AtyPayforPartyDetailBinding> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_payfor_party_detail);
        SystemBarHelper.tintStatusBar(this, ContextCompat.getColor(getApplicationContext(), R.color.app_red), 0f);
        initView();
    }


    private void initView() {
        bindingView.payLeftIcon.setOnClickListener(clickListener);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        MoneyOfParty moneyOfParty = (MoneyOfParty) bundle.getSerializable("pay");
        bindingView.tvPayOfMonth.setText(moneyOfParty.getTime().split("-")[1] + "月党费");
        bindingView.tvPayHowMuchTop.setText(moneyOfParty.getPayable() + "元");
        if( 1 == moneyOfParty.getState()){
            bindingView.tvPayHowMuchDown.setText(  " 0 元");
        }else {
            bindingView.tvPayHowMuchDown.setText(moneyOfParty.getPaid() + "元");
        }

        bindingView.payMoneyAddress.setText("缴费地点：" + moneyOfParty.getAddress());
    }

    View.OnClickListener clickListener = v -> {
        switch (v.getId()) {
            case R.id.pay_left_icon:
                PayForPartyDetailAty.this.finish();
                break;
        }
    };
}
