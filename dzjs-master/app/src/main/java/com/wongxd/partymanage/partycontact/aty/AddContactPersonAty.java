package com.wongxd.partymanage.partycontact.aty;


import android.os.Bundle;
import android.view.View;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wongxd.partymanage.R;
import com.wongxd.partymanage.base.BaseBindingActivity;
import com.wongxd.partymanage.databinding.AtyAddContactPartyBinding;
import com.wongxd.partymanage.partycontact.bean.ContactParty;

import java.util.Calendar;

/**
 * Created by zyj on 2017/7/22.
 */

public class AddContactPersonAty extends BaseBindingActivity<AtyAddContactPartyBinding> implements DatePickerDialog.OnDateSetListener {
    private int mYear, mMonth, mDay;
    private String contactTime;
    private String contactName;
    private String contactAction;
    private String contactNotes;
    private ContactParty contactParty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_add_contact_party);
        initView();
    }

    private void initView() {
        bindingView.addContactDays.setOnClickListener(clickListener);
        bindingView.addContactLeftIcon.setOnClickListener(clickListener);
        bindingView.addContactCommit.setOnClickListener(clickListener);
    }

    View.OnClickListener clickListener = v -> {
        switch (v.getId()){
            case R.id.add_contact_days:
                showDayChoose();
                break;
            case R.id.add_contact_left_icon:
                AddContactPersonAty.this.finish();
                break;
            case R.id.add_contact_commit:
                getAddContactParty();

                break;
            default:
                break;
        }
    };

    private void getAddContactParty() {
        contactName = bindingView.addContactName.getText().toString();
        contactNotes = bindingView.addContactNotes.getText().toString();
        contactAction = bindingView.addContactAction.getText().toString();
        contactParty = new ContactParty(contactName, contactTime, contactNotes, contactAction);
        AddContactPersonAty.this.finish();
    }

    private void showDayChoose() {
        Calendar now = Calendar.getInstance();
        mYear = now.get(Calendar.YEAR);
        mMonth = now.get(Calendar.MONTH);
        mDay = now.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = DatePickerDialog.newInstance(
                this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dialog.show(getFragmentManager(), "Datepickerdialog");
    }


    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        bindingView.addContactDays.setText(mYear+ "-" + ++mMonth  + "-" + mDay);
        contactTime = mYear+ "-" + ++mMonth  + "-" + mDay;
    }
}
