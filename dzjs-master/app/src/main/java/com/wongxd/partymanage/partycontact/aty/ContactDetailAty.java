package com.wongxd.partymanage.partycontact.aty;

import android.os.Bundle;
import android.view.View;

import com.wongxd.partymanage.R;
import com.wongxd.partymanage.base.BaseBindingActivity;
import com.wongxd.partymanage.databinding.AtyContactDetailBinding;

/**
 * Created by zyj on 2017/7/21.
 */

public class ContactDetailAty extends BaseBindingActivity<AtyContactDetailBinding> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_contact_detail);
        initView();
    }

    private void initView() {
        bindingView.detailLeftIcon.setOnClickListener(clickListener);
    }

    View.OnClickListener clickListener = v -> {
      switch (v.getId()) {
          case R.id.detail_left_icon:
              ContactDetailAty.this.finish();
              break;
      }
    };

}
