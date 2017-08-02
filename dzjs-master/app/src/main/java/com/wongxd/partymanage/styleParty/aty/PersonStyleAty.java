package com.wongxd.partymanage.styleParty.aty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.bumptech.glide.Glide;
import com.wongxd.partymanage.R;
import com.wongxd.partymanage.base.BaseBindingActivity;
import com.wongxd.partymanage.databinding.AtyStylePersonBinding;
import com.wongxd.partymanage.styleParty.bean.StyleParty;
import com.wongxd.partymanage.styleParty.view.RectImageView;
import com.wongxd.partymanage.utils.SystemBarHelper;
import com.wongxd.partymanage.utils.conf.UrlConf;

/**
 * Created by zyj on 2017/7/23.
 */

public class PersonStyleAty extends BaseBindingActivity<AtyStylePersonBinding> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_style_person);
        SystemBarHelper.tintStatusBar(this, ContextCompat.getColor(getApplicationContext(), R.color.app_red), 0f);
        getData();
        bindingView.stylePersonLeftIcon.setOnClickListener(onClickListener);
    }

    private void getData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        StyleParty.DataBean.MienListBean  mienListBean = (StyleParty.DataBean.MienListBean) bundle.getSerializable("whichPerson");
        bindingView.personDescribe.setText(mienListBean.getDescribe());
        bindingView.stylePersonName.setText(mienListBean.getUserName());
        RectImageView iv = (RectImageView) findViewById(R.id.person_photo);
        Glide.with(iv.getContext()).load(UrlConf.HOST + mienListBean.getImg()).placeholder(R.drawable.placeholder).into(iv);
    }

    View.OnClickListener onClickListener = v -> {
        switch (v.getId()){
            case R.id.style_person_left_icon:
                PersonStyleAty.this.finish();
                break;
        }
    };
}
