package com.wongxd.partymanage.me;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

import com.flyco.animation.Attention.Tada;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.orhanobut.logger.Logger;
import com.wongxd.partymanage.App;
import com.wongxd.partymanage.LoginActivity;
import com.wongxd.partymanage.R;
import com.wongxd.partymanage.base.BaseBindingFragment;
import com.wongxd.partymanage.databinding.FgtMeBinding;
import com.wongxd.partymanage.utils.TU;
import com.wongxd.partymanage.utils.conf.UrlConf;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by wxd1 on 2017/5/25.
 */

public class MeFragment extends BaseBindingFragment<FgtMeBinding> {
    @Override
    public int setContent() {
        return R.layout.fgt_me;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String title = getArguments().getString("title");
        bindingView.setTitle(title);

        bindingView.llLogout.setOnClickListener(v -> {

            final NormalDialog dialog = new NormalDialog(getContext());
            dialog.title("确定退出吗？")//
                    .style(NormalDialog.STYLE_TWO)//
                    .titleTextColor(getResources().getColor(R.color.app_red))
                    .titleTextSize(23)//
                    .showAnim(new Tada())//
                    .show();

            dialog.setOnBtnClickL(
                    new OnBtnClickL() {
                        @Override
                        public void onBtnClick() {
                            dialog.dismiss();
                        }
                    },
                    new OnBtnClickL() {
                        @Override
                        public void onBtnClick() {

                            OkHttpUtils.post().url(UrlConf.OutLoginUrl).addParams("token", App.token).tag(netTag).build()
                                    .execute(new StringCallback() {
                                        @Override
                                        public void onError(Call call, Exception e, int id) {
                                            TU.cT(e.getMessage());
                                            dialog.dismiss();
                                        }

                                        @Override
                                        public void onResponse(String response, int id) {
                                            Logger.e(response);
                                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(App.instance);
                                            preferences.edit().remove("token").apply();
                                            Intent intent = new Intent(getActivity(), LoginActivity.class)
                                                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intent);
                                            dialog.dismiss();
                                        }
                                    });
                        }
                    });

        });
    }
}
