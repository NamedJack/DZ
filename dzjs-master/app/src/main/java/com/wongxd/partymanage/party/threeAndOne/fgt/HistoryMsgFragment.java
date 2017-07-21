package com.wongxd.partymanage.party.threeAndOne.fgt;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.wongxd.partymanage.App;
import com.wongxd.partymanage.R;
import com.wongxd.partymanage.base.BaseBindingFragment;
import com.wongxd.partymanage.databinding.FgtHistoryMsgBinding;
import com.wongxd.partymanage.party.threeAndOne.MeetingDetailActivity;
import com.wongxd.partymanage.party.threeAndOne.bean.HistoryMeetingMsgBean;
import com.wongxd.partymanage.utils.conf.UrlConf;
import com.wongxd.partymanage.utils.net.WNetUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by wxd1 on 2017/6/22.
 */

public class HistoryMsgFragment extends BaseBindingFragment<FgtHistoryMsgBinding> {

    int colorUnSelected = Color.parseColor("#252525");
    int colorSeleted = Color.parseColor("#970000");

    @Override
    public int setContent() {
        return R.layout.fgt_history_msg;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initRecycleView();
        bindingView.srlHistoryMsg.post(() -> bindingView.srlHistoryMsg.setRefreshing(true));
        getList(false);
    }

    int type = 2;

    private void initView() {
        //会议类型 1：党课 2：党委会：3：支委会4：支部大会
        bindingView.tvDang.setOnClickListener(v -> {
            bindingView.tvDang.setTextColor(colorSeleted);
            bindingView.tvKe.setTextColor(colorUnSelected);
            bindingView.tvParty.setTextColor(colorUnSelected);
            bindingView.tvZhiwei.setTextColor(colorUnSelected);
            type = 2;
            pageNo = 1;
            getList(false);
        });

        bindingView.tvKe.setOnClickListener(v -> {
            bindingView.tvKe.setTextColor(colorSeleted);
            bindingView.tvDang.setTextColor(colorUnSelected);
            bindingView.tvParty.setTextColor(colorUnSelected);
            bindingView.tvZhiwei.setTextColor(colorUnSelected);
            type = 1;
            pageNo = 1;
            getList(false);
        });

        bindingView.tvParty.setOnClickListener(v -> {
            bindingView.tvParty.setTextColor(colorSeleted);
            bindingView.tvDang.setTextColor(colorUnSelected);
            bindingView.tvKe.setTextColor(colorUnSelected);
            bindingView.tvZhiwei.setTextColor(colorUnSelected);
            type = 4;
            pageNo = 1;
            getList(false);
        });

        bindingView.tvZhiwei.setOnClickListener(v -> {
            bindingView.tvZhiwei.setTextColor(colorSeleted);
            bindingView.tvDang.setTextColor(colorUnSelected);
            bindingView.tvKe.setTextColor(colorUnSelected);
            bindingView.tvParty.setTextColor(colorUnSelected);
            type = 3;
            pageNo = 1;
            getList(false);
        });

        bindingView.ivSearch.setOnClickListener(v -> {
            String text = bindingView.etSearch.getText().toString().trim();
            if (!TextUtils.isEmpty(text))
                getList(false);
        });

        bindingView.etSearch.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    //隐藏键盘
                    ((InputMethodManager) getContext().getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    //处理搜索逻辑
                    String text = bindingView.etSearch.getText().toString().trim();
                    if (!TextUtils.isEmpty(text))
                        getList(false);
                    return true;
                }
                return false;
            }
        });

    }

    private RvAdapter adapter;

    private void initRecycleView() {
        //设置样式刷新显示的位置
        bindingView.srlHistoryMsg.setProgressViewOffset(true, 10, 150);
        bindingView.srlHistoryMsg.setColorSchemeResources(R.color.swiperefresh_color1, R.color.swiperefresh_color2, R.color.swiperefresh_color3, R.color.swiperefresh_color4);

        adapter = new RvAdapter();
        bindingView.rvHistoryMsg.setAdapter(adapter);
        bindingView.rvHistoryMsg.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.setEmptyView(R.layout.item_rv_empty, bindingView.rvHistoryMsg);
        bindingView.srlHistoryMsg.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                bindingView.etSearch.clearComposingText();
                pageNo = 1;
                getList(false);
            }
        });


        // 滑动最后一个Item的时候回调onLoadMoreRequested方法
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getList(true);
            }
        }, bindingView.rvHistoryMsg);


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

    int pageNo = 1;

    private void getList(boolean isLoadMore) {
        String url = UrlConf.HistoryMeetingMsgUrl;
        PostFormBuilder builder = OkHttpUtils.post().url(url)
                .tag(netTag)
                .addParams("token", App.token)
                .addParams("meetingType", type + "");
        String searchName = bindingView.etSearch.getText().toString().trim();
        if (!TextUtils.isEmpty(searchName)) {
            builder.addParams("search", searchName);
        } else {
            builder.addParams("pageNo", pageNo + "");
        }

        WNetUtil.StringCallBack(builder
                , url, (AppCompatActivity) getActivity(), new WNetUtil.WNetStringCallback() {
                    @Override
                    public void success(String response, int id) {
                        Logger.e(response);
                        bindingView.srlHistoryMsg.setRefreshing(false);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.optString("msg").equals("没有更多内容")) {
                                adapter.loadMoreEnd();
                                return;
                            }
                            if (jsonObject.optString("msg").equals("没有历史会议通知")) {
                                adapter.setNewData(null);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Gson gson = new Gson();
                        HistoryMeetingMsgBean bean = gson.fromJson(response, HistoryMeetingMsgBean.class);
                        if (bean.getCode() == 100) {
                            int totalPage = bean.getData().getPage().getPagetole();
                            if (isLoadMore) {
                                if (pageNo > totalPage) {
                                    adapter.loadMoreEnd();
                                } else {
                                    adapter.addData(bean.getData().getMeetinglist());
                                    adapter.loadMoreComplete();
                                }
                            } else {
                                bindingView.srlHistoryMsg.setRefreshing(false);
                                adapter.setNewData(bean.getData().getMeetinglist());
                            }
                            if (bean.getData().getMeetinglist().size() != 0)
                                pageNo++;
                        } else {
                            if (isLoadMore) adapter.loadMoreFail();
                        }

                    }

                    @Override
                    public void error(Call call, Exception e, int id) {
                        bindingView.srlHistoryMsg.setRefreshing(false);
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        getList(false);
    }

    class RvAdapter extends BaseQuickAdapter<HistoryMeetingMsgBean.DataBean.MeetinglistBean, BaseViewHolder> {
        public RvAdapter() {
            super(R.layout.item_rv_history_meeting_msg);
        }

        @Override
        protected void convert(BaseViewHolder helper, HistoryMeetingMsgBean.DataBean.MeetinglistBean item) {
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
                    .setText(R.id.tv_time, item.getTime());
        }


    }
}
