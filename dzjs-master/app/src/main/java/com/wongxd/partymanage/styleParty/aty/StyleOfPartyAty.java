package com.wongxd.partymanage.styleParty.aty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wongxd.partymanage.R;
import com.wongxd.partymanage.base.BaseBindingActivity;
import com.wongxd.partymanage.base.RecyclerAdapter.MyRecyclerViewAdapter;
import com.wongxd.partymanage.base.RecyclerAdapter.MyViewHolder;
import com.wongxd.partymanage.databinding.AtyStylePartyBinding;
import com.wongxd.partymanage.styleParty.bean.StyleParty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyj on 2017/7/23.
 */

public class StyleOfPartyAty extends BaseBindingActivity<AtyStylePartyBinding> {
    private List<StyleParty> partyList ;
    private MyRecyclerViewAdapter myRecyclerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_style_party);
        initView();
    }

    private void initView() {
        bindingView.styleLeftIcon.setOnClickListener(onClickListener);
        partyList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            StyleParty styleParty = new StyleParty("","zhangsan","jiushiyige王八蛋");
            partyList.add(styleParty);
        }
        bindingView.styleRl.setLayoutManager(new LinearLayoutManager(this));
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(this, R.layout.item_of_style_party,partyList) {
            @Override
            public void convert(MyViewHolder holder, int position) {
                holder.setText(R.id.party_style_name, partyList.get(position).getName());
                holder.setText(R.id.party_style_story, partyList.get(position).getStory());
            }
        };
        bindingView.styleRl.setAdapter(myRecyclerViewAdapter);
        myRecyclerViewAdapter.setOnItemClickListener(new MyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int position) {
                Intent intent = new Intent(StyleOfPartyAty.this, PersonStyleAty.class);
                startActivity(intent);
            }

            @Override
            public boolean onItemLonClick(View view, RecyclerView.ViewHolder viewHolder, int position) {
                return false;
            }


        });
    }

    View.OnClickListener onClickListener = v -> {
        switch (v.getId()){
            case R.id.style_left_icon:
                StyleOfPartyAty.this.finish();
                break;
        }
    };
}
