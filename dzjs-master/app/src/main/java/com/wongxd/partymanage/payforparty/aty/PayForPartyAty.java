package com.wongxd.partymanage.payforparty.aty;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.wongxd.partymanage.R;
import com.wongxd.partymanage.base.BaseBindingActivity;
import com.wongxd.partymanage.databinding.AtyPayforPartyBinding;
import com.wongxd.partymanage.partycontact.adapter.PopupAdapter;
import com.wongxd.partymanage.payforparty.adapter.PayMoneyAdapter;
import com.wongxd.partymanage.payforparty.bean.MoneyOfParty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyj on 2017/7/22.
 */

public class PayForPartyAty extends BaseBindingActivity<AtyPayforPartyBinding> {
    private List<MoneyOfParty> payList = new ArrayList<>();
    private PopupWindow mWindow;
    private ListView time;
    private List<String> yearList;
    private ImageView rightImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_payfor_party);
        initData();
        initView();
    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            MoneyOfParty moneyOfParty = new MoneyOfParty("500", "200", "一月", "2017.01", true, "北三环西路48号北京科技会展");
            payList.add(moneyOfParty);
        }
        payList.add(new MoneyOfParty("500", "200", "十一月", "2017.01", false ,"北三环西路48号北京科技会展" ));
        payList.add(new MoneyOfParty("300", "200", "十二月", "2017.01", false, "北三环西路48号北京科技会展"));
        yearList = new ArrayList<>();
        for (int i = 10; i < 20; i++) {
            yearList.add("20" + i);
        }
    }

    private void initView() {
        bindingView.payforLeftIcon.setOnClickListener(clickListener);
        bindingView.payforRightIcon.setOnClickListener(clickListener);
        PayMoneyAdapter adapter = new PayMoneyAdapter(payList, this);
        bindingView.payForList.setAdapter(adapter);
        bindingView.payForList.setOnItemClickListener(itemClickListener);
        rightImg = (ImageView) findViewById(R.id.payfor_right_img);
    }

    View.OnClickListener clickListener = v -> {
        switch (v.getId()) {
            case R.id.payfor_left_icon:
                PayForPartyAty.this.finish();
                break;
            case R.id.payfor_right_icon:
                showYearChooseWindow();
                break;
            default:
                break;
        }
    };

    private void showYearChooseWindow() {
        initPopupWindow(rightImg, -100 ,0 ,yearList);
    }
    private void initPopupWindow(View parentView, int widthOff, int heightOff, List<String> dataList) {
        View contentView = LayoutInflater.from(this).inflate(R.layout.contact_popupwindow, null, false);
        mWindow = new PopupWindow(contentView, 150, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mWindow.setFocusable(true);
        mWindow.setOutsideTouchable(false);
        mWindow.setBackgroundDrawable(new ColorDrawable(0xffffff));//必须设置，否则bug
//        mWindow.setOnDismissListener(dismissListener);
        time = (ListView) contentView.findViewById(R.id.time_list);
        PopupAdapter adapter = new PopupAdapter(this, dataList);
        time.setAdapter(adapter);
        mWindow.showAsDropDown(parentView, widthOff, heightOff);
//        mWindow.showAtLocation(parentView, Gravity.RIGHT,0,0);
        time.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                parentView.setText(dataList.get(position));
                mWindow.dismiss();
                mWindow = null;
            }
        });
    }

    AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(PayForPartyAty.this, PayForPartyDetailAty.class);
            MoneyOfParty moneyOfParty = new MoneyOfParty(payList.get(position).getTotalMoney(),
                    payList.get(position).getPaidMoney(), payList.get(position).getMonth(),
                    payList.get(position).getDay(), payList.get(position).isHavePaid(),
                    payList.get(position).getAddress());

            Bundle bundle = new Bundle();
            bundle.putSerializable("pay", moneyOfParty);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    };
}
