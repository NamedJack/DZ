package com.wongxd.partymanage.peoplestalk.aty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.wongxd.partymanage.R;
import com.wongxd.partymanage.base.BaseBindingActivity;
import com.wongxd.partymanage.databinding.AtyPeoplestalkBinding;
import com.wongxd.partymanage.peoplestalk.adapter.PeopleTalkAdapter;
import com.wongxd.partymanage.peoplestalk.bean.PeopleTalk;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyj on 2017/7/19.
 */

public class PeoplesTlakAty extends BaseBindingActivity<AtyPeoplestalkBinding> {
    private List<PeopleTalk> talkList;
    private PeopleTalkAdapter talkAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_peoplestalk);
        initData();
        setListener();
    }

    private void setListener() {
        bindingView.peopleTlakLeftIcon.setOnClickListener(clickListener);
        bindingView.tlakRightIcon.setOnClickListener(clickListener);
    }

    private void initData() {
        talkList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            PeopleTalk peopleTalk = new PeopleTalk("张三", "团员", "先谢谢先谢谢先谢谢先谢谢先谢谢先谢谢先谢谢先谢谢先谢谢先谢谢先谢谢先谢谢先谢谢先谢谢先谢谢先谢谢", "fff fff fff fff fff fff fff fff fff fff fff fff fff fff fff fff fff fff fff fff fff fff fff fff ", "2017");
            talkList.add(peopleTalk);
        }
        talkAdapter = new PeopleTalkAdapter(talkList, this);
        bindingView.peopleLv.setAdapter(talkAdapter);
        bindingView.peopleLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(PeoplesTlakAty.this, ContactOfPartyAty.class);
//                startActivity(intent);
            }
        });
    }

    View.OnClickListener clickListener = v -> {

        switch (v.getId()) {
            case R.id.people_tlak_left_icon:
                PeoplesTlakAty.this.finish();
                break;
            case R.id.tlak_right_icon:
                Intent intent = new Intent(PeoplesTlakAty.this, AddTlakPersionAty.class);
                startActivityForResult(intent, 1);
                break;
            default:
                break;
        }

    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case 1:
                Bundle bundle = data.getBundleExtra("bundle");
                PeopleTalk peopleTalk = (PeopleTalk) bundle.getSerializable("people");
                talkList.add(peopleTalk);
                talkAdapter.notifyDataSetChanged();
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}
