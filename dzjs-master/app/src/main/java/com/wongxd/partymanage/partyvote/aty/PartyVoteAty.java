package com.wongxd.partymanage.partyvote.aty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.wongxd.partymanage.App;
import com.wongxd.partymanage.R;
import com.wongxd.partymanage.base.BaseBindingActivity;
import com.wongxd.partymanage.base.RecyclerAdapter.MyRecyclerViewAdapter;
import com.wongxd.partymanage.base.RecyclerAdapter.MyViewHolder;
import com.wongxd.partymanage.databinding.AtyVoteOfPartyBinding;
import com.wongxd.partymanage.partyvote.RecyclerViewDivider;
import com.wongxd.partymanage.partyvote.bean.VoteBean;
import com.wongxd.partymanage.utils.conf.UrlConf;
import com.wongxd.partymanage.utils.net.WNetUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;

/**
 * Created by zyj on 2017/7/28.
 */

public class PartyVoteAty extends BaseBindingActivity<AtyVoteOfPartyBinding> {
    private MyRecyclerViewAdapter myAdapter;
    private List<VoteBean.DataBean> voteBeanList = new ArrayList<>();
    private int vote = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_vote_of_party);
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int titleId = bundle.getInt("voteTitleId");
        String url = UrlConf.PartyVoteTicketTitle;
        WNetUtil.StringCallBack(OkHttpUtils.post().url(url)
                        .addParams("token", App.token)
                        .addParams("id", titleId + "")
                , url, PartyVoteAty.this, "数据获取中", true, new WNetUtil.WNetStringCallback() {
                    @Override
                    public void success(String response, int id) {
//                        Log.e("msg", "投票" + response);
                        VoteBean voteBean = new Gson().fromJson(response, VoteBean.class);
                        if (voteBean.getCode().equals(100 + "")) {
                            voteBeanList.addAll(voteBean.getData());
                            bindingView.ticketsOfTotal.setText(0 + "/" + voteBeanList.size());
                            initView(voteBean);
                        }
                    }

                    @Override
                    public void error(Call call, Exception e, int id) {
                        Log.e("msg", "网络错误" + e.toString());

                    }
                });
    }

    private void initView(VoteBean voteBean) {
        VoteBean.Data1Bean info = voteBean.getData1();
        String start = info.getStartTime();
        String end = info.getLastTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startTime = null;
        Date endtTime = null;
        try {
            startTime = dateFormat.parse(start);
            endtTime = dateFormat.parse(end);
        } catch (Exception e) {
            e.printStackTrace();
        }

        long spareTime = endtTime.getTime() - startTime.getTime();
        Log.e("msg", "spareTime" + spareTime);
        bindingView.voteSpareTime.start(spareTime);
        bindingView.partyVoteLeftIcon.setOnClickListener(onClickListener);
//        bindingView.voteImg.setImageResource();

        bindingView.partyVoteRl.setLayoutManager(new LinearLayoutManager(this));
        bindingView.partyVoteRl.addItemDecoration(new RecyclerViewDivider(
                this, LinearLayoutManager.HORIZONTAL, 1, ContextCompat.getColor(this, R.color.lightgray)));
        myAdapter = new MyRecyclerViewAdapter(this, R.layout.item_party_vote, voteBeanList) {
            @Override
            public void convert(MyViewHolder holder, int position) {
                holder.setText(R.id.vote_play_title, "投票ID：" + voteBeanList.get(position).getId());
                holder.setText(R.id.vote_story, voteBeanList.get(position).getName());
                holder.setOnClickListener(R.id.vote_play_do_choose, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!voteBeanList.get(position).isSelected()) {
                            voteBeanList.get(position).setSelected(true);
                            holder.setBackgroundRes(R.id.vote_play_do_choose, R.drawable.btn_down);
                            holder.setText(R.id.vote_play_do_choose, "取 消");
//                            TU.cT("选择" + voteBeanList.get(position).getName());
                             vote++;
                        }else {
                            voteBeanList.get(position).setSelected(false);
                            holder.setBackgroundRes(R.id.vote_play_do_choose, R.drawable.btn_nomal);
                            holder.setText(R.id.vote_play_do_choose, "选TA");
//                            TU.cT("取消选择" + voteBeanList.get(position).getName());
                            vote--;
                        }
                        bindingView.ticketsOfTotal.setText(vote + "/" + voteBeanList.size());
                        if(vote > 0){
                            bindingView.voteTicketBg.setBackground(getResources().getDrawable(R.drawable.vote_have_ticket));
                        }else {
                            bindingView.voteTicketBg.setBackground(getResources().getDrawable(R.drawable.vote_no_ticket));
                        }
                    }
                });
            }
        };
        bindingView.partyVoteRl.setAdapter(myAdapter);
    }


    View.OnClickListener onClickListener = v -> {
        switch (v.getId()) {
            case R.id.party_vote_left_icon:
                PartyVoteAty.this.finish();
                break;
            default:
                break;
        }
    };
}
