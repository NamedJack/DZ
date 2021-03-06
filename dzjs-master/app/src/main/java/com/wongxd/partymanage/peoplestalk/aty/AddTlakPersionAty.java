package com.wongxd.partymanage.peoplestalk.aty;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wongxd.partymanage.App;
import com.wongxd.partymanage.R;
import com.wongxd.partymanage.base.BaseBindingActivity;
import com.wongxd.partymanage.databinding.AtyAddTlakpersionBinding;
import com.wongxd.partymanage.utils.SystemBarHelper;
import com.wongxd.partymanage.utils.TU;
import com.wongxd.partymanage.utils.conf.UrlConf;
import com.wongxd.partymanage.utils.net.WNetUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

import okhttp3.Call;

/**
 * Created by zyj on 2017/7/19.
 */

public class AddTlakPersionAty extends BaseBindingActivity<AtyAddTlakpersionBinding> implements DatePickerDialog.OnDateSetListener {
    //    final int DATE_DIALOG = 1;
    private int mYear, mMonth, mDay;
    private String persionPoltics;
    private String tlakTime ;
    private String tlakAdvice;
    private String tlakSelf;
    private String tlakPersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_add_tlakpersion);
        SystemBarHelper.tintStatusBar(this, ContextCompat.getColor(getApplicationContext(), R.color.app_red), 0f);
        initData();
        setListener();

    }

    private void commitData(String time, String target, String politics, String proposal, String autognosis) {
        String url = UrlConf.AddPeopleTlak;
//        Log.e("msg", "数据提交 " + time + target + politics + proposal + autognosis);
        WNetUtil.StringCallBack(OkHttpUtils.post().url(url)
                        .addParams("token", App.token)
                        .addParams("time", time + "")
                        .addParams("target", target + "")
                        .addParams("politics", politics + "")
                        .addParams("proposal", proposal + "")
                        .addParams("autognosis", autognosis + "")
                        .addParams("id", App.id)
                , url, AddTlakPersionAty.this, "数据获取中", true, new WNetUtil.WNetStringCallback() {
                    @Override
                    public void success(String response, int id) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String code = jsonObject.getString("code");
                            if ("100".equals(code)) {
                                TU.cT("添加成功");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void error(Call call, Exception e, int id) {
                        Log.e("msg", "网络错误" + e.toString());

                    }
                });
    }

    private void setListener() {
        bindingView.addTlakCommit.setOnClickListener(clickListener);
        bindingView.addPersionPoltics.setOnItemSelectedListener(itemClickListener);
        bindingView.tlakLeftIcon.setOnClickListener(clickListener);
    }

    private void initData() {
        Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);

        bindingView.addTalkDays.setOnClickListener(v -> {
            switch (v.getId()) {
                case R.id.add_talk_days:
//                    DatePickerDialog dialog = new DatePickerDialog(AddTlakPersionAty.this, dataListener, mYear, mMonth, mDay);
//
//                    dialog.show();
                    showTimeChoose();
                    break;
                default:
                    break;
            }
        });


    }

    private void showTimeChoose() {
        DatePickerDialog dialog = DatePickerDialog.newInstance(
                this, mYear, mMonth, mDay
        );
        dialog.show(getFragmentManager(), "Datepickerdialog");
    }

    View.OnClickListener clickListener = v -> {
        switch (v.getId()) {
            case R.id.tlak_left_icon:
//                setResult(0,null);
                AddTlakPersionAty.this.finish();
                break;
            case R.id.add_tlak_commit:
                tlakPersion = bindingView.addPersionName.getText().toString();
                tlakAdvice = bindingView.addPersionAdvice.getText().toString();
                tlakSelf = bindingView.addPersionSlef.getText().toString();
                if (tlakTime == null ||tlakPersion.equals("") || tlakAdvice.equals("") || tlakSelf.equals("") ) {
                    TU.cT("请填写完整信息");
                } else {
                    commitData(tlakTime, tlakPersion, persionPoltics, tlakAdvice, tlakSelf);
                    AddTlakPersionAty.this.finish();
                }
                break;
            default:
                break;
        }
    };

    /**
     * 要求上传时间格式为 xxxx - xx -xx
     * 本地获取月份为 0 - 11 要求上传月份为 01 - 12 月
     * @param mYear
     * @param mMonth
     * @param mDay
     */
    private void setTime(int mYear, int mMonth, int mDay) {
        String m= "";String d ="";
        if(++mMonth < 10){
            m = "0" + mMonth;
        }else {
            m  = "" + mMonth;
        }
        if(mDay < 10){
            d = "0" + mDay;
        }else {
            d = mDay +"";
        }
        tlakTime = mYear + "-" + mMonth + "-" + mDay;
//        Log.e("msg", "tlakTime"+tlakTime);
        bindingView.addTalkDays.setText(mYear + "-" + m + "-" + d);
    }

    AdapterView.OnItemSelectedListener itemClickListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (bindingView.addPersionPoltics.getSelectedItem().equals("党员")) {
                persionPoltics = 1 + "";
            } else if (bindingView.addPersionPoltics.getSelectedItem().equals("预备党员")) {
                persionPoltics = 2 + "";
            } else if (bindingView.addPersionPoltics.getSelectedItem().equals("党员发展对象")) {
                persionPoltics = 3 + "";
            } else if (bindingView.addPersionPoltics.getSelectedItem().equals("入党积极分子")) {
                persionPoltics = 4 + "";
            } else if (bindingView.addPersionPoltics.getSelectedItem().equals("共青团员")) {
                persionPoltics = 5 + "";
            } else if (bindingView.addPersionPoltics.getSelectedItem().equals("群众")) {
                persionPoltics = 6 + "";
            } else if (bindingView.addPersionPoltics.getSelectedItem().equals("其他")) {
                persionPoltics = 7 + "";
            }
//            persionPoltics = (String) bindingView.addPersionPoltics.getSelectedItem();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };


    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        mYear = year;
        mDay = dayOfMonth;
        mMonth = monthOfYear;
        setTime(mYear, mMonth, mDay);
    }


}
