package com.wongxd.partymanage.partycontact.aty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.wongxd.partymanage.R;
import com.wongxd.partymanage.base.BaseBindingActivity;
import com.wongxd.partymanage.databinding.AtyContactPartyBinding;
import com.wongxd.partymanage.partycontact.adapter.ContactPartyAdapter;
import com.wongxd.partymanage.partycontact.bean.ContactParty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyj on 2017/7/19.
 */

public class ContactOfPartyAty extends BaseBindingActivity<AtyContactPartyBinding> {
    private List<ContactParty> partyList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_contact_party);
        initData();
        initTitleBar();
    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            partyList.add(new ContactParty("张三", "2017-07-21","aaaaaaaaaaaaaaaaaaa", "bbbbbbbbbbbbbbbbbbb" ));

        }
    }

    private void initTitleBar() {
        bindingView.contactTitle.setText("党员群众联系表");
        bindingView.contactLeftIcon.setOnClickListener(clickListener);
        bindingView.contactRightIcon.setOnClickListener(clickListener);
        ContactPartyAdapter adapter = new ContactPartyAdapter(partyList, this);
        bindingView.contactList.setAdapter(adapter);
        bindingView.contactList.setOnItemClickListener(ItemClickListener);
    }

    View.OnClickListener clickListener = v -> {
        switch (v.getId()) {
            case R.id.contact_left_icon:
                ContactOfPartyAty.this.finish();
                break;
            case R.id.contact_right_icon:
                Intent intent = new Intent(ContactOfPartyAty.this, PopupActivity.class);
                startActivity(intent);
                break;
        }
    };


    AdapterView.OnItemClickListener ItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(ContactOfPartyAty.this, ContactDetailAty.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("contactParty",partyList.get(position));
            intent.putExtra("party",bundle);
            startActivity(intent);
        }
    };

}
