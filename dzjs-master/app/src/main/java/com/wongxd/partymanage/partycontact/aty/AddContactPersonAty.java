package com.wongxd.partymanage.partycontact.aty;


import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wongxd.partymanage.App;
import com.wongxd.partymanage.R;
import com.wongxd.partymanage.base.BaseBindingActivity;
import com.wongxd.partymanage.databinding.AtyAddContactPartyBinding;
import com.wongxd.partymanage.partycontact.bean.ContactParty;
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
 * Created by zyj on 2017/7/22.
 */

public class AddContactPersonAty extends BaseBindingActivity<AtyAddContactPartyBinding> implements DatePickerDialog.OnDateSetListener {
    private int mYear, mMonth, mDay;
    private String contactTime;
    private String contactName;
    private String contactAction;
    private String contactNotes;
    private ContactParty contactParty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_add_contact_party);
        SystemBarHelper.tintStatusBar(this, ContextCompat.getColor(getApplicationContext(), R.color.app_red), 0f);
        initView();
    }

    private void initView() {
        Calendar now = Calendar.getInstance();
        mYear = now.get(Calendar.YEAR);
        mMonth = now.get(Calendar.MONTH);
        mDay = now.get(Calendar.DAY_OF_MONTH);
        bindingView.addContactDays.setOnClickListener(clickListener);
        bindingView.addContactLeftIcon.setOnClickListener(clickListener);
        bindingView.addContactCommit.setOnClickListener(clickListener);
    }

    View.OnClickListener clickListener = v -> {
        switch (v.getId()){
            case R.id.add_contact_days:
                showDayChoose();
                break;
            case R.id.add_contact_left_icon:
                AddContactPersonAty.this.finish();
                break;
            case R.id.add_contact_commit:
                getAddContactParty();
                break;
            default:
                break;
        }
    };

    private void getAddContactParty() {
        contactName = bindingView.addContactName.getText().toString();
        contactNotes = bindingView.addContactNotes.getText().toString();
        contactAction = bindingView.addContactAction.getText().toString();
//        contactParty = new ContactParty(contactName, contactTime, contactNotes, contactAction);
        if( contactTime == null || TextUtils.isEmpty(contactName)
                || TextUtils.isEmpty(contactNotes) || TextUtils.isEmpty(contactAction) ){
            TU.cT("请填写完成信息");
        }else{
            commitInfo(contactTime, contactName, contactNotes, contactAction);
            AddContactPersonAty.this.finish();
        }

    }

    private void commitInfo(String time, String target, String helpRecord, String implement) {
        String url = UrlConf.AddPeopleContact;

        WNetUtil.StringCallBack(OkHttpUtils.post().url(url)
                        .addParams("token", App.token)
                        .addParams("time", time + "")
                        .addParams("target", target)
                        .addParams("helpRecord", helpRecord)
                        .addParams("implement", implement)
                , url, AddContactPersonAty.this, "数据获取中", true, new WNetUtil.WNetStringCallback() {
                    @Override
                    public void success(String response, int id) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String code = jsonObject.getString("code");
                            if(code.equals("100")){
                                TU.cT("添加成功");
                            }else{
                                TU.cT("添加失败");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void error(Call call, Exception e, int id) {
                        Log.e("msg", "提交联系表错误" + e.toString());

                    }
                });
    }

    private void showDayChoose() {
        DatePickerDialog dialog = DatePickerDialog.newInstance(
                this, mYear, mMonth, mDay
        );
        dialog.show(getFragmentManager(), "Datepickerdialog");
    }


    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        mYear = year;
        mDay = dayOfMonth;
        mMonth = monthOfYear;
        bindingView.addContactDays.setText(mYear + "-" + ++mMonth + "-" + mDay);
        contactTime = mYear+ "-" + ++mMonth  + "-" + mDay;
    }


}
