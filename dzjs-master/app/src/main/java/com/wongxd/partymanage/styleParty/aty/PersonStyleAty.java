package com.wongxd.partymanage.styleParty.aty;

import android.os.Bundle;
import android.view.View;

import com.wongxd.partymanage.R;
import com.wongxd.partymanage.base.BaseBindingActivity;
import com.wongxd.partymanage.databinding.AtyStylePersonBinding;

/**
 * Created by zyj on 2017/7/23.
 */

public class PersonStyleAty extends BaseBindingActivity<AtyStylePersonBinding> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_style_person);

        bindingView.stylePersonLeftIcon.setOnClickListener(onClickListener);
    }
    View.OnClickListener onClickListener = v -> {
        switch (v.getId()){
            case R.id.style_person_left_icon:
                PersonStyleAty.this.finish();
                break;
        }
    };
}
