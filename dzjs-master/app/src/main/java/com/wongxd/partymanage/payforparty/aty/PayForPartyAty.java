package com.wongxd.partymanage.payforparty.aty;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.wongxd.partymanage.App;
import com.wongxd.partymanage.R;
import com.wongxd.partymanage.base.BaseBindingActivity;
import com.wongxd.partymanage.databinding.AtyPayforPartyBinding;
import com.wongxd.partymanage.partycontact.adapter.PopupAdapter;
import com.wongxd.partymanage.payforparty.adapter.PayMoneyAdapter;
import com.wongxd.partymanage.payforparty.bean.MoneyOfParty;
import com.wongxd.partymanage.utils.SystemBarHelper;
import com.wongxd.partymanage.utils.conf.UrlConf;
import com.wongxd.partymanage.utils.net.WNetUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by zyj on 2017/7/22.
 */

public class PayForPartyAty extends BaseBindingActivity<AtyPayforPartyBinding> {
    private List<MoneyOfParty> payList = new ArrayList<>();
    private PopupWindow mWindow;
    private ListView time;
    private List<String> yearList;
    private ImageView rightImg;

    private PayMoneyAdapter adapter;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            if (bundle != null) {
                String response = bundle.getString("data");
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String resultCode = jsonObject.getString("code");
                    if(resultCode.equals("100")){
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            String payable = jsonArray.getJSONObject(i).getString("payable");
                            String paid = jsonArray.getJSONObject(i).getString("paid");
                            String time = jsonArray.getJSONObject(i).getString("time");
                            String payTime = jsonArray.getJSONObject(i).getString("payTime");
                            int state = jsonArray.getJSONObject(i).getInt("state");
                            String address = jsonArray.getJSONObject(i).getString("address");
                            MoneyOfParty moneyOfParty = new MoneyOfParty(payable, paid, state, payTime, time, address);
                            payList.add(moneyOfParty);
                        }
                    }
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_payfor_party);
        SystemBarHelper.tintStatusBar(this, ContextCompat.getColor(getApplicationContext(), R.color.app_red), 0f);
        initYearList();
        initView();
        initData("");

    }

    private void initYearList() {
        yearList = new ArrayList<>();
        for (int i = 10; i < 18; i++) {
            yearList.add("20" + i);
        }
    }

    private void initData(String searchTime) {
        String url = UrlConf.PayForPartyUrl;
        WNetUtil.StringCallBack(OkHttpUtils.post().url(url)
                        .addParams("token", App.token)
                        .addParams("time", searchTime)
                , url, PayForPartyAty.this, "数据获取中", true, new WNetUtil.WNetStringCallback() {
                    @Override
                    public void success(String response, int id) {
                            Bundle bundle = new Bundle();
                            bundle.putString("data", response);
                            Message message = new Message();
                            message.setData(bundle);
                            handler.sendMessage(message);
                    }

                    @Override
                    public void error(Call call, Exception e, int id) {
                        Log.e("msg", "网络错误" + e.toString());
                    }
                });
    }

    private void initView() {
        bindingView.payforLeftIcon.setOnClickListener(clickListener);
        bindingView.payforRightIcon.setOnClickListener(clickListener);
        rightImg = (ImageView) findViewById(R.id.payfor_right_img);
        adapter = new PayMoneyAdapter(payList, this);
        bindingView.payForList.setAdapter(adapter);
        bindingView.payForList.setOnItemClickListener(itemClickListener);
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
        initPopupWindow(rightImg, -100, 0, yearList);
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
                payList.clear();
                initData(dataList.get(position));
                mWindow.dismiss();
                mWindow = null;
            }
        });
    }

    AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(PayForPartyAty.this, PayForPartyDetailAty.class);
            MoneyOfParty moneyOfParty = new MoneyOfParty(payList.get(position).getPayable(),
                    payList.get(position).getPaid(), payList.get(position).getState(),
                    payList.get(position).getPayTime(), payList.get(position).getTime(),
                    payList.get(position).getAddress());

            Bundle bundle = new Bundle();
            bundle.putSerializable("pay", moneyOfParty);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    };


}
