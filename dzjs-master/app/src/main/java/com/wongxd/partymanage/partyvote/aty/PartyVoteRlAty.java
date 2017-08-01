package com.wongxd.partymanage.partyvote.aty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.wongxd.partymanage.App;
import com.wongxd.partymanage.R;
import com.wongxd.partymanage.base.BaseBindingActivity;
import com.wongxd.partymanage.base.RecyclerAdapter.MyRecyclerViewAdapter;
import com.wongxd.partymanage.base.RecyclerAdapter.MyViewHolder;
import com.wongxd.partymanage.databinding.AtyVoteListBinding;
import com.wongxd.partymanage.partyvote.RecyclerViewDivider;
import com.wongxd.partymanage.partyvote.bean.VoteListBean;
import com.wongxd.partymanage.utils.SystemBarHelper;
import com.wongxd.partymanage.utils.TU;
import com.wongxd.partymanage.utils.conf.UrlConf;
import com.wongxd.partymanage.utils.net.WNetUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by zyj on 2017/7/28.
 */

public class PartyVoteRlAty extends BaseBindingActivity<AtyVoteListBinding>{
    private List<VoteListBean.DataBean> list = new ArrayList<>();
    private MyRecyclerViewAdapter myRecyclerViewAdapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_vote_list);
        SystemBarHelper.tintStatusBar(this, ContextCompat.getColor(getApplicationContext(), R.color.app_red), 0f);
        initData();
        initView();
    }

    private void initData() {
        String url = UrlConf.PartyVoteTicket;
        WNetUtil.StringCallBack(OkHttpUtils.post().url(url)
                        .addParams("token", App.token)
                , url, PartyVoteRlAty.this, "数据获取中", true, new WNetUtil.WNetStringCallback() {
                    @Override
                    public void success(String response, int id) {
//                        Log.e("msg", "投票liebiao" + response);
                        VoteListBean voteListBean = new Gson().fromJson(response, VoteListBean.class);
                        if (voteListBean.getCode().equals(100+"")) {
//                            Log.e("msg",voteListBean.getData().size() + "aa");
                            if(voteListBean.getData().size() == 0){
                                TU.cT("暂无投票数据");
                            }else {
                                list.addAll(voteListBean.getData());
                                myRecyclerViewAdapter.notifyDataSetChanged();
                            }
                        }
                    }

                    @Override
                    public void error(Call call, Exception e, int id) {
                        Log.e("msg", "网络错误" + e.toString());

                    }
                });
    }

    private void initView() {
        bindingView.voteListLeftIcon.setOnClickListener(v -> {
            PartyVoteRlAty.this.finish();
        });
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(this, R.layout.item_list_vote_list, list) {
            @Override
            public void convert(MyViewHolder holder, int position) {
                holder.setText(R.id.vote_title, list.get(position).getTitle() + "评选活动");
                if(list.get(position).getIsReception() == 2){
                    holder.setText(R.id.vote_start_person, "发起人：" + list.get(position).getLogin());
                }else if(list.get(position).getIsReception() == 1){
                    holder.setText(R.id.vote_start_person, "发起人：" + list.get(position).getRealName());
                }

                holder.setText(R.id.vote_last_time, "截止：" + list.get(position).getLastTime());
            }

        };
        bindingView.voteListRl.setLayoutManager(new LinearLayoutManager(this));
        bindingView.voteListRl.setAdapter(myRecyclerViewAdapter);
        bindingView.voteListRl.addItemDecoration(new RecyclerViewDivider(
                this, LinearLayoutManager.HORIZONTAL, 1, ContextCompat.getColor(this, R.color.lightgray)));
        myRecyclerViewAdapter.setOnItemClickListener(new MyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int position) {
                Intent intent = new Intent(PartyVoteRlAty.this, PartyVoteAty.class);
                Bundle bundle = new Bundle();
                bundle.putInt("voteTitleId", list.get(position).getId());
                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public boolean onItemLonClick(View view, RecyclerView.ViewHolder viewHolder, int position) {
                return false;
            }
        });
    }
}
