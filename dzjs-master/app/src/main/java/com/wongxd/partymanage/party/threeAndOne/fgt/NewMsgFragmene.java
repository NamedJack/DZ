package com.wongxd.partymanage.party.threeAndOne.fgt;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.wongxd.partymanage.App;
import com.wongxd.partymanage.R;
import com.wongxd.partymanage.base.BaseBindingFragment;
import com.wongxd.partymanage.databinding.FgtNewMsgBinding;
import com.wongxd.partymanage.party.threeAndOne.MeetingDetailActivity;
import com.wongxd.partymanage.party.threeAndOne.bean.NewMeetimgMsgBean;
import com.wongxd.partymanage.utils.conf.UrlConf;
import com.wongxd.partymanage.utils.net.WNetUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;

import okhttp3.Call;

public class NewMsgFragmene extends BaseBindingFragment<FgtNewMsgBinding> {


    @Override
    public int setContent() {
        return R.layout.fgt_new_msg;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initRecycleView();
        getList();
        bindingView.srlNewMsg.post(() -> bindingView.srlNewMsg.setRefreshing(true));
    }

    RvAdapter adapter;

    private void initRecycleView() {
        //设置样式刷新显示的位置
        bindingView.srlNewMsg.setProgressViewOffset(true, 10, 150);
        bindingView.srlNewMsg.setColorSchemeResources(R.color.swiperefresh_color1, R.color.swiperefresh_color2, R.color.swiperefresh_color3, R.color.swiperefresh_color4);

        adapter = new RvAdapter();
        bindingView.rvNewMsg.setAdapter(adapter);
        bindingView.rvNewMsg.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.setEmptyView(R.layout.item_rv_empty, bindingView.rvNewMsg);

        bindingView.srlNewMsg.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getList();
            }
        });

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter3, View view, int position) {
                String id = adapter.getData().get(position).getId() + "";
                Intent intent = new Intent(getActivity(), MeetingDetailActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();
        getList();
    }

    private void getList() {
        String url = UrlConf.NewMeetingMsgUrl;
        PostFormBuilder builder = OkHttpUtils.post().url(url)
                .tag(netTag)
                .addParams("token", App.token);
        WNetUtil.StringCallBack(builder
                , url, (AppCompatActivity) getActivity(), new WNetUtil.WNetStringCallback() {
                    @Override
                    public void success(String response, int id) {
                        Logger.e(response);
                        bindingView.srlNewMsg.setRefreshing(false);
                        Gson gson = new Gson();
                        NewMeetimgMsgBean bean = gson.fromJson(response, NewMeetimgMsgBean.class);
                        if (bean.getCode() == 100) {
                            bindingView.srlNewMsg.setRefreshing(false);
                            adapter.setNewData(bean.getData());
                        }
                    }

                    @Override
                    public void error(Call call, Exception e, int id) {
                        bindingView.srlNewMsg.setRefreshing(false);
                    }
                });
    }

    class RvAdapter extends BaseQuickAdapter<NewMeetimgMsgBean.DataBean, BaseViewHolder> {
        public RvAdapter() {
            super(R.layout.item_rv_new_meeting_msg);
        }

        @Override
        protected void convert(BaseViewHolder helper, NewMeetimgMsgBean.DataBean item) {
            int imgRes = 0;
            switch (item.getMeetingType()) { //会议类型 1：党课 2：党委会：3：支委会4：支部大会
                case 1:
                    imgRes = R.drawable.ke;
                    break;
                case 2:
                    imgRes = R.drawable.dang;
                    break;
                case 3:
                    imgRes = R.drawable.zhi;
                    break;
                case 4:
                    imgRes = R.drawable.zhibuhui;
                    break;
            }

            if (item.getIsRead() == 1) { //是否已读 1：未读，2：已读
                helper.setText(R.id.tv_state, "未读");
            } else helper.setText(R.id.tv_state, "已读");
            helper.setBackgroundRes(R.id.iv_type, imgRes)
                    .setText(R.id.tv_title, item.getName())
                    .setText(R.id.tv_time, "参会时间：" + item.getTime());
        }


    }

}
