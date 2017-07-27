package com.wongxd.partymanage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.wongxd.partymanage.utils.SystemBarHelper;
import com.wongxd.partymanage.utils.TU;
import com.wongxd.partymanage.utils.conf.UrlConf;
import com.wongxd.partymanage.utils.net.WNetUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

public class LoginActivity extends AppCompatActivity {

    private EditText etAccount;
    private EditText etPwd;
    private CheckBox cbRemenberPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_login);
        SystemBarHelper.tintStatusBar(this, getResources().getColor(R.color.app_red), 0f);
        etAccount = (EditText) findViewById(R.id.et_account);
        etPwd = (EditText) findViewById(R.id.et_pwd);
        cbRemenberPwd = (CheckBox) findViewById(R.id.cb_remenber_pwd);
        Button btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(v -> doLogin());
        cbRemenberPwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(App.instance);
                if (isChecked) {
                    String userName = etAccount.getText().toString().trim();
                    String pwd = etPwd.getText().toString().trim();
                    if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(pwd)) {
                        return;
                    }
                    preferences.edit().putString("account", userName).apply();
                    preferences.edit().putString("pwd", pwd).apply();

                } else {
                    preferences.edit().remove("account").apply();
                    preferences.edit().remove("pwd").apply();
                }
            }
        });

        if (cbRemenberPwd.isChecked()) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(App.instance);
            etAccount.setText(preferences.getString("account", ""));
            etPwd.setText(preferences.getString("pwd", ""));
        }
    }

    private void doLogin() {
        String account = etAccount.getText().toString().trim();
        String pwd = etPwd.getText().toString().trim();

        if (TextUtils.isEmpty(account)) {
            TU.cT("请输入账号");
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            TU.cT("请输入密码");
            return;
        }
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(App.instance);
        if (cbRemenberPwd.isChecked()) {
            preferences.edit().putString("account", account).apply();
            preferences.edit().putString("pwd", pwd).apply();
        } else {
            preferences.edit().clear().apply();
        }
        String url = UrlConf.LoginUrl;
        WNetUtil.StringCallBack(OkHttpUtils.post().url(url)
                        .addParams("account", account)
                        .addParams("type","2")
                        .addParams("password", pwd)
                , url, LoginActivity.this, "登录中", true, new WNetUtil.WNetStringCallback() {
                    @Override
                    public void success(String response, int id) {
                        /**
                         * {
                         "msg": "登录成功",
                         "code": 100,
                         "data": {
                         "token": "72ad72e7-5061-4a3b-81a3-64606138a9d5"
                         }
                         }
                         */
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject object = jsonObject.optJSONObject("data");
                            App.token = object.optString("token");
                            App.id = object.optString("user");
                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(App.instance);
//                            SPUtilConfig sp = SPUtilConfig.getInstance(App.instance);
                            Log.e("msg",App.id  + "登录信息" + response);
//                            sp.saveData("loginUserId", object.optString("user"));
//                            sp.saveData("loginAccount", account);
                            preferences.edit().putString("token", App.token).apply();
                            preferences.edit().putString("appid", App.id).apply();
                            startActivity(new Intent(LoginActivity.this, AtyMainActivity.class));
                            overridePendingTransition(R.anim.screen_zoom_in, R.anim.screen_zoom_out);
                            finish();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void error(Call call, Exception e, int id) {

                    }
                });

    }

}
