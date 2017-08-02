package com.wongxd.partymanage.doBetter.aty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wongxd.partymanage.App;
import com.wongxd.partymanage.R;
import com.wongxd.partymanage.base.BaseBindingActivity;
import com.wongxd.partymanage.databinding.AtyHonerBinding;
import com.wongxd.partymanage.doBetter.bean.HonerBean;
import com.wongxd.partymanage.utils.SystemBarHelper;
import com.wongxd.partymanage.utils.conf.UrlConf;
import com.wongxd.partymanage.utils.net.WNetUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

/**
 * Created by zyj on 2017/7/27.
 */

public class HonerAty extends BaseBindingActivity<AtyHonerBinding> {
    private HonerBean.DataBean.GoodListBean honerTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_honer);
        SystemBarHelper.tintStatusBar(this, ContextCompat.getColor(getApplicationContext(), R.color.app_red), 0f);
        bindingView.honerLeftIcon.setOnClickListener(v -> {
            this.finish();
        });
        getImage();
    }



    public void getImage() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        honerTeam = (HonerBean.DataBean.GoodListBean) bundle.getSerializable("honerId");
        String url = UrlConf.PersonImg;
        WNetUtil.StringCallBack(OkHttpUtils.post().url(url)
                        .addParams("token", App.token)
                        .addParams("id", honerTeam.getId() +"")

                , url, HonerAty.this, "数据获取中", true, new WNetUtil.WNetStringCallback() {
                    @Override
                    public void success(String response, int id) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String code = jsonObject.getString("code");
                            String img = jsonObject.getString("url");
                            Log.e("msg", code + "创优详情" + img);
                            ImageView iv = (ImageView) findViewById(R.id.team_honer_img);
                            Glide.with(iv.getContext()).load(UrlConf.HOST + img).placeholder(R.drawable.placeholder).into(iv);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        initView();
                    }

                    @Override
                    public void error(Call call, Exception e, int id) {
                        Log.e("msg", "创优网络错误" + e.toString());

                    }
                });
    }

    private void initView() {
        bindingView.honerTvTitle.setText(honerTeam.getName());
        bindingView.honerTvTime.setText(honerTeam.getTime().split(" ")[0]);
        bindingView.honerTvBelong.setText(honerTeam.getTypeName());
        bindingView.honerTvStory.setText(honerTeam.getDescribe());
    }
}
