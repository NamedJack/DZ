package com.wongxd.partymanage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.aty_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ImageView iv = (ImageView) findViewById(R.id.iv);
        iv.postDelayed(() -> {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(App.instance);
            String token = preferences.getString("token", null);
            String appId = preferences.getString("appid", null);
            if (TextUtils.isEmpty(token) || TextUtils.isEmpty(appId) ) {
                startActivity(new Intent(this, LoginActivity.class));
            } else {
                App.token = token;
                App.id = appId;
                startActivity(new Intent(this, AtyMainActivity.class));
            }
            overridePendingTransition(R.anim.screen_zoom_in, R.anim.screen_zoom_out);
            finish();

        }, 1000);
    }
}
