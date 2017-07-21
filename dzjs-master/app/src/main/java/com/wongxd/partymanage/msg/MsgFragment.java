package com.wongxd.partymanage.msg;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wongxd.partymanage.App;
import com.wongxd.partymanage.R;
import com.wongxd.partymanage.base.BaseBindingFragment;
import com.wongxd.partymanage.databinding.FgtMsgBinding;
import com.wongxd.partymanage.utils.conf.UrlConf;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.type;

/**
 * Created by wxd1 on 2017/5/25.
 */

public class MsgFragment extends BaseBindingFragment<FgtMsgBinding> {
    @Override
    public int setContent() {
        return R.layout.fgt_msg;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String title = getArguments().getString("title");
        bindingView.setTitle(title);
        initRecycleView();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i + "");
        }
        adapter.setNewData(list);
//        bindingView.srlMsg.post(() -> bindingView.srlMsg.setRefreshing(true));
        getList(false);
    }

    private RvAdapter adapter;

    private void initRecycleView() {
        //设置样式刷新显示的位置
        bindingView.srlMsg.setProgressViewOffset(true, 10, 150);
        bindingView.srlMsg.setColorSchemeResources(R.color.swiperefresh_color1, R.color.swiperefresh_color2, R.color.swiperefresh_color3, R.color.swiperefresh_color4);

        adapter = new RvAdapter();
        bindingView.rvMsg.setAdapter(adapter);
        bindingView.rvMsg.setLayoutManager(new LinearLayoutManager(getContext()));

        bindingView.srlMsg.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
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
        }, bindingView.rvMsg);


        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter3, View view, int position) {
//                String id = adapter.getData().get(position).getId() + "";
//                Intent intent = new Intent(getActivity(), NewsDeatilActivity.class);
//                intent.putExtra("id", id);
//                startActivity(intent);
            }
        });
    }

    int pageNo = 1;

    private void getList(boolean isLoadMore) {
        String url = UrlConf.HomeNewsUrl;
        PostFormBuilder builder = OkHttpUtils.post().url(url)
                .tag(netTag)
                .addParams("token", App.token)
                .addParams("articletypeId", type + "")  // 1 党务  2 支部
                .addParams("pageSize", "10")
                .addParams("pageNo", pageNo + "");

        bindingView.srlMsg.setRefreshing(false);
        if (isLoadMore) adapter.loadMoreEnd();
        // TODO: 2017/6/21

//        WNetUtil.StringCallBack(builder
//                , url, (AppCompatActivity) getActivity(), new WNetUtil.WNetStringCallback() {
//                    @Override
//                    public void success(String response, int id) {
//                        Logger.e(response);
//                        bindingView.srlHome.setRefreshing(false);
//                        Gson gson = new Gson();
//                        HomeNewsBean bean = gson.fromJson(response, HomeNewsBean.class);
//                        if (bean.getCode() == 100) {
//                            bindingView.clLoadData.setStatus(LoadDataLayout.SUCCESS);
//                            int totalPage = bean.getTotalPage();
//                            if (isLoadMore) {
//                                if (pageNo > totalPage) {
//                                    adapter.loadMoreEnd();
//                                } else {
//                                    adapter.addData(bean.getData());
//                                    adapter.loadMoreComplete();
//                                }
//                            } else {
//                                bindingView.srlHome.setRefreshing(false);
//                                adapter.setNewData(bean.getData());
//                                // 检查是否满一屏，如果不满足关闭loadMore
////                                adapter.disableLoadMoreIfNotFullPage(bindingView.rvHome);
//                            }
//                            if (bean.getData().size() != 0)
//                                pageNo++;
//                            else if (!isLoadMore)
//                                bindingView.clLoadData.setStatus(LoadDataLayout.EMPTY);
//                        } else {
//                            if (isLoadMore) {
//                                adapter.loadMoreFail();
//                            } else bindingView.clLoadData.setStatus(LoadDataLayout.ERROR);
//                        }
//
//                    }
//
//                    @Override
//                    public void error(Call call, Exception e, int id) {
//                        bindingView.clLoadData.setStatus(LoadDataLayout.ERROR);
//                        bindingView.srlHome.setRefreshing(false);
//                    }
//                });
    }


    class RvAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
        public RvAdapter() {
            super(R.layout.item_rv_msg);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.tv_title, item);
        }
    }
}
