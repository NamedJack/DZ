package com.wongxd.partymanage.structure.aty;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.wongxd.partymanage.App;
import com.wongxd.partymanage.R;
import com.wongxd.partymanage.base.BaseBindingActivity;
import com.wongxd.partymanage.databinding.AtyStructureBinding;
import com.wongxd.partymanage.structure.bean.StructureBean;
import com.wongxd.partymanage.utils.SystemBarHelper;
import com.wongxd.partymanage.utils.conf.UrlConf;
import com.wongxd.partymanage.utils.net.WNetUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import okhttp3.Call;

/**
 * Created by zyj on 2017/8/1.
 * 组织架构图
 */

public class StructureAty extends BaseBindingActivity<AtyStructureBinding>{
    private String imgStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_structure);
        SystemBarHelper.tintStatusBar(this, ContextCompat.getColor(getApplicationContext(), R.color.app_red), 0f);
        bindingView.structureLeftIcon.setOnClickListener(v -> {
            StructureAty.this.finish();
        });
        getImgStr();
    }

    private void getImgStr() {
        String url = UrlConf.Getstructure;
        WNetUtil.StringCallBack(OkHttpUtils.post().url(url)
                        .addParams("token", App.token)
                , url, StructureAty.this, "数据获取中", true, new WNetUtil.WNetStringCallback() {
                    @Override
                    public void success(String response, int id) {
                        StructureBean bean =null;
                        try {
                            bean = new Gson().fromJson(response, StructureBean.class);
                        } catch (JsonSyntaxException e) {
                            Log.e("msg","解析出错" + e.toString() );
                            e.printStackTrace();
                        }
                        if(bean.getCode() == 100){
                            imgStr =  bean.getData().getUrl();
                            setImg();
                        }
                    }

                    @Override
                    public void error(Call call, Exception e, int id) {
                        Log.e("msg", "网络错误" + e.toString());

                    }
                });
    }

    private void setImg() {
        ImageView iv = (ImageView) findViewById(R.id.structure_img);
        Glide.with(this).load(UrlConf.HOST + imgStr).placeholder(R.drawable.placeholder).into(iv);
//                Glide.with(iv.getContext()).load(UrlConf.HOST + bean.getIcon()).centerCrop().into(iv);
//                Glide.with(iv.getContext()).load(R.drawable.icon_more).centerCrop().into(iv);
    }
}
