package com.wongxd.partymanage.promise.aty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.wongxd.partymanage.App;
import com.wongxd.partymanage.R;
import com.wongxd.partymanage.base.BaseBindingActivity;
import com.wongxd.partymanage.databinding.AtyPromiseDetailsBinding;
import com.wongxd.partymanage.promise.bean.PromiseBean;
import com.wongxd.partymanage.utils.SystemBarHelper;
import com.wongxd.partymanage.utils.TU;
import com.wongxd.partymanage.utils.conf.UrlConf;
import com.wongxd.partymanage.utils.net.WNetUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

/**
 * Created by json on 2017/7/12.
 */

public class PromiseDetailActivity extends BaseBindingActivity<AtyPromiseDetailsBinding> {
    private Spinner myYearSpinner;
    private String year, userName, userUnit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_promise_details);
        SystemBarHelper.tintStatusBar(this, ContextCompat.getColor(getApplicationContext(), R.color.app_red), 0f);
        initView();
        doNetRequst();
    }

    private void initView() {
        myYearSpinner = (Spinner) findViewById(R.id.year_sp);
        bindingView.yearSp.setOnItemSelectedListener(onItemSelectedListener);
        bindingView.personMessageCommit.setOnClickListener(clickListener);
        bindingView.personPromiseBackIcon.setOnClickListener(clickListener);
        bindingView.personMessageCancel.setOnClickListener(clickListener);
    }

    private void initData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle == null) {
            return;
        }
        int witchBtn = bundle.getInt("witchBtn");
        if(witchBtn == 0){//加号跳转
            bindingView.promisePartyName.setText("姓名：" + userName );
            bindingView.promisePartyDepartment.setText("支 部：" + userUnit);
            bindingView.personMessageCommit.setVisibility(View.VISIBLE);
        }else if(witchBtn == 1){//rl跳转
            PromiseBean.DataBean promiseBean = (PromiseBean.DataBean) bundle.getSerializable("personPromise");
            if(userName.equals(promiseBean.getUsername())){
                bindingView.personMessageCommit.setVisibility(View.VISIBLE);
            }
            bindingView.promisePartyName.setText("姓名：" + promiseBean.getUsername());
            bindingView.promisePartyDepartment.setText("支 部：" + promiseBean.getUnitname());
            bindingView.personStudyEt.setText(promiseBean.getWork());
            bindingView.personWorkEt.setText(promiseBean.getStudy());
            bindingView.personStyleEt.setText(promiseBean.getStyleimage());
            bindingView.personContactEt.setText(promiseBean.getContact());
            bindingView.personPlayEt.setText(promiseBean.getPartParty());
        }else{}
    }

    private void doNetRequst() {
        String url = UrlConf.AddPersonPromise;
        WNetUtil.StringCallBack(OkHttpUtils.post().url(url)
                        .addParams("token", App.token)
                , url, PromiseDetailActivity.this, "获取个人信息中", true, new WNetUtil.WNetStringCallback() {
                    @Override
                    public void success(String response, int id) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            userUnit = jsonObject.getString("user_unit");
                            userName = jsonObject.getString("user_name");
                            initData();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void error(Call call, Exception e, int id) {
                        TU.cT("获取信息失败，错误" + e.toString());
                    }
                });

    }

    View.OnClickListener clickListener = v -> {
        switch (v.getId()) {
            case R.id.person_message_commit:
                saveChanged();
                break;
            case R.id.person_promise_back_icon:
                PromiseDetailActivity.this.finish();
                break;
            case R.id.person_message_cancel:
                PromiseDetailActivity.this.finish();
                break;
            default:
                break;
        }
    };

    private void saveChanged() {
        String study = bindingView.personStudyEt.getText().toString();
        String work = bindingView.personWorkEt.getText().toString();
        String style = bindingView.personStyleEt.getText().toString();
        String contact = bindingView.personContactEt.getText().toString();
        String play = bindingView.personPlayEt.getText().toString();
        PromiseBean.DataBean bean = new PromiseBean.DataBean();
        bean.setStudy(study);
        bean.setWork(work);
        String url = UrlConf.PersonPromise;
        WNetUtil.StringCallBack(OkHttpUtils.post().url(url)
                        .addParams("token", App.token)
                        .addParams("year", year)
                        .addParams("study", study)
                        .addParams("work", work)
                        .addParams("styleimage", style)
                        .addParams("contact", contact)
                        .addParams("partParty", play)
                , url, PromiseDetailActivity.this, "提交信息中", true, new WNetUtil.WNetStringCallback() {
                    @Override
                    public void success(String response, int id) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int code = jsonObject.getInt("code");
                            String msg = jsonObject.getString("msg");
                            if(code == 100){
                                TU.cT(msg);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void error(Call call, Exception e, int id) {
                        TU.cT("修改失败，错误" + e.toString());
                    }
                });
    }


    AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            year = bindingView.yearSp.getSelectedItem().toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
