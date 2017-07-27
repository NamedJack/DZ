package com.wongxd.partymanage.peoplestalk.aty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wongxd.partymanage.R;
import com.wongxd.partymanage.base.BaseBindingActivity;
import com.wongxd.partymanage.databinding.AtyAddTlakpersionBinding;
import com.wongxd.partymanage.peoplestalk.bean.PeopleTalk;

import java.util.Calendar;

/**
 * Created by zyj on 2017/7/19.
 */

public class AddTlakPersionAty extends BaseBindingActivity<AtyAddTlakpersionBinding> implements DatePickerDialog.OnDateSetListener {
//    final int DATE_DIALOG = 1;
    private int mYear, mMonth, mDay;
    private String persionPoltics;
    private String tlakTime;
    private String tlakAdvice;
    private String tlakSelf;
    private String tlakPersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_add_tlakpersion);
        initData();
        setListener();
    }

    private void setListener() {
        bindingView.tlakCommit.setOnClickListener(clickListener);
        bindingView.addPersionPoltics.setOnItemSelectedListener(itemClickListener);
        bindingView.tlakLeftIcon.setOnClickListener(clickListener);
    }

    private void initData() {
        Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);

        bindingView.addTalkDays.setOnClickListener(v->{
            switch (v.getId()){
                case R.id.add_talk_days:
//                    DatePickerDialog dialog = new DatePickerDialog(AddTlakPersionAty.this, dataListener, mYear, mMonth, mDay);
//
//                    dialog.show();
                    showTimeChoose();
                    break;
                default:
                    break;
            }
        });


    }

    private void showTimeChoose() {
        DatePickerDialog dialog = DatePickerDialog.newInstance(
                this, mYear, mMonth, mDay
        );
        dialog.show(getFragmentManager(), "Datepickerdialog");
    }

    View.OnClickListener clickListener = v -> {
        switch (v.getId()){
            case R.id.tlak_left_icon:
                setResult(0,null);
                AddTlakPersionAty.this.finish();
                break;
            case R.id.tlak_commit:
                tlakPersion = bindingView.addPersionName.getText().toString();
                tlakAdvice = bindingView.addPersionAdvice.getText().toString();
                tlakSelf = bindingView.addPersionSlef.getText().toString();
                PeopleTalk peopleTalk = new PeopleTalk(tlakPersion, persionPoltics,tlakAdvice,tlakSelf,tlakTime);
                Bundle bundle = new Bundle();
                bundle.putSerializable("people",peopleTalk);
                Intent intent = new Intent();
                intent.putExtra("bundle",bundle);
                setResult(1,intent);
                AddTlakPersionAty.this.finish();
                break;
            default:
                break;
        }
    };




    private void setTime(int mYear, int mMonth, int mDay) {
        tlakTime = mYear+ "-" + mMonth  + "-" + mDay;
        bindingView.addTalkDays.setText(mYear+ "-" + ++mMonth  + "-" + mDay );
    }

    AdapterView.OnItemSelectedListener itemClickListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            persionPoltics = (String) bindingView.addPersionPoltics.getSelectedItem();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };


    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        mYear = year;
        mDay = dayOfMonth;
        mMonth = monthOfYear;
        setTime(mYear, mMonth, mDay);
    }
}
