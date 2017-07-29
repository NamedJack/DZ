package com.wongxd.partymanage.doBetter.aty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wongxd.partymanage.R;
import com.wongxd.partymanage.base.BaseBindingActivity;
import com.wongxd.partymanage.base.RecyclerAdapter.MyRecyclerViewAdapter;
import com.wongxd.partymanage.base.RecyclerAdapter.MyViewHolder;
import com.wongxd.partymanage.databinding.AtyDoBetterListBinding;
import com.wongxd.partymanage.doBetter.bean.HonerBean;

import java.util.List;

/**
 * Created by zyj on 2017/7/27.
 */

public class DoBetterAty extends BaseBindingActivity<AtyDoBetterListBinding> {

    private MyRecyclerViewAdapter adapter;
    private List<HonerBean> honerBeanList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_do_better_list);
        initView();
    }

    private void initView() {
        bindingView.doBetterLeftIcon.setOnClickListener(clickListener);
        adapter = new MyRecyclerViewAdapter(this, R.layout.item_list_do_better,honerBeanList) {
            @Override
            public void convert(MyViewHolder holder, int position) {
                holder.setText(R.id.person_honer_title, honerBeanList.get(position).getTitle());
                holder.setText(R.id.person_honer_time, honerBeanList.get(position).getTime());
                holder.setText(R.id.person_honer_story, honerBeanList.get(position).getStory());
                holder.setText(R.id.personal_honer_text, honerBeanList.get(position).getBelong());

            }
        };

        bindingView.doBetterRl.setAdapter(adapter);
        adapter.setOnItemClickListener(new MyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int position) {
                Intent intent = new Intent(DoBetterAty.this, HonerAty.class);
                startActivity(intent);
            }

            @Override
            public boolean onItemLonClick(View view, RecyclerView.ViewHolder viewHolder, int position) {
                return false;
            }
        });
    }


    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.do_better_left_icon:
                    DoBetterAty.this.finish();
                    break;
                default:
                    break;
            }
        }
    };
}
