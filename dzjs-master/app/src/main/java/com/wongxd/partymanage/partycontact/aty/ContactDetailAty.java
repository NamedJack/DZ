package com.wongxd.partymanage.partycontact.aty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wongxd.partymanage.R;
import com.wongxd.partymanage.base.BaseBindingActivity;
import com.wongxd.partymanage.databinding.AtyContactDetailBinding;
import com.wongxd.partymanage.partycontact.bean.ContactParty;
import com.wongxd.partymanage.payforparty.aty.PayForPartyAty;

/**
 * Created by zyj on 2017/7/21.
 */

public class ContactDetailAty extends BaseBindingActivity<AtyContactDetailBinding> {
    private ContactParty contactParty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_contact_detail);
        initView();
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("party");
        ContactParty contactParty = (ContactParty) bundle.getSerializable("contactParty");
        bindingView.contactHelpName.setText(contactParty.getPerson());
        bindingView.contactHelpTime.setText(contactParty.getTime());
        bindingView.contactHelpWriteDown.setText(contactParty.getHelpNotes());
        bindingView.contactHelpDo.setText(contactParty.getAction());
    }

    private void initView() {
        bindingView.detailLeftIcon.setOnClickListener(clickListener);
        bindingView.detailRightIcon.setOnClickListener(clickListener);
    }

    View.OnClickListener clickListener = v -> {
      switch (v.getId()) {
          case R.id.detail_left_icon:
              ContactDetailAty.this.finish();
              break;
          case R.id.detail_right_icon:
              Intent intent = new Intent(ContactDetailAty.this, PayForPartyAty.class);
              startActivity(intent);
              break;
          default:
              break;
      }
    };

}
