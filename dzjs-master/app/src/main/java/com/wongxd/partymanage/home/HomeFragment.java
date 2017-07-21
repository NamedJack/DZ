package com.wongxd.partymanage.home;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.wongxd.partymanage.App;
import com.wongxd.partymanage.R;
import com.wongxd.partymanage.widget.BannerView.BannerView;
import com.wongxd.partymanage.databinding.FgtHomeBinding;
import com.wongxd.partymanage.home.aty.NewsDeatilActivity;
import com.wongxd.partymanage.home.bean.HomeBannerBean;
import com.wongxd.partymanage.home.bean.HomeNewsBean;
import com.wongxd.partymanage.utils.conf.UrlConf;
import com.wongxd.partymanage.utils.net.WNetUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by wxd1 on 2017/5/25.
 */

public class HomeFragment extends Fragment {
    int colorUnSelected = Color.parseColor("#afb6b5");
    int colorSeleted = Color.parseColor("#ffffff");

    protected Object netTag;
    private FgtHomeBinding bindingView;
    private BannerView banner1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        netTag = this;
    }

    @Override
    public void onDestroy() {
        OkHttpUtils.getInstance().cancelTag(netTag);
        super.onDestroy();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View ll = inflater.inflate(R.layout.fgt_home, container, false);
        bindingView = DataBindingUtil.bind(ll);
        return ll;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initRecycleView();
        banner1 = bindingView.bannerview;
        banner1.setViewFactory(new BannerViewFactory());
        banner1.setDataList(list);

        bindingView.srlHome.post(() -> bindingView.srlHome.setRefreshing(true));
        getList(false);
        getBanner();

    }

    private List<BannerItem> list = new ArrayList<>();

    private void getBanner() {
        String url = UrlConf.HomeBannerUrl;

        WNetUtil.StringCallBack(OkHttpUtils.post().tag(netTag).url(url).addParams("articletypeId", type + "")
                        .addParams("token", App.token)
                , url, (AppCompatActivity) getActivity(), new WNetUtil.WNetStringCallback() {
                    @Override
                    public void success(String response, int id) {
                        Logger.e(response);
                        HomeBannerBean homeBannerBean = new Gson().fromJson(response, HomeBannerBean.class);
                        if (homeBannerBean.getCode() != 100) return;
                        list.clear();
                        for (int i = 0; i < homeBannerBean.getData().size(); i++) {
                            BannerItem item = new BannerItem();
                            item.image = UrlConf.HOST + homeBannerBean.getData().get(i).getArticleImg();
                            item.title = homeBannerBean.getData().get(i).getArticleTitle();
                            item.id = homeBannerBean.getData().get(i).getId() + "";
                            list.add(item);
                        }

                        banner1.start();
                    }

                    @Override
                    public void error(Call call, Exception e, int id) {

                    }
                });
    }

    int type = 1;

    private void initView() {
        bindingView.rlInfo.setOnClickListener(v -> {
            bindingView.tvParty.setTextSize(14);
            bindingView.vParty.setVisibility(View.GONE);
            bindingView.tvParty.setTextColor(colorUnSelected);
            bindingView.tvInfo.setTextSize(16);
            bindingView.vInfo.setVisibility(View.VISIBLE);
            bindingView.tvInfo.setTextColor(colorSeleted);
            pageNo = 1;
            type = 1;
            getList(false);
            getBanner();
        });

        bindingView.rlParty.setOnClickListener(v -> {
            bindingView.tvParty.setTextSize(16);
            bindingView.vParty.setVisibility(View.VISIBLE);
            bindingView.tvParty.setTextColor(colorSeleted);
            bindingView.tvInfo.setTextSize(14);
            bindingView.vInfo.setVisibility(View.GONE);
            bindingView.tvInfo.setTextColor(colorUnSelected);
            pageNo = 1;
            type = 2;
            getList(false);
            getBanner();
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
        bindingView.srlHome.setProgressViewOffset(true, 10, 150);
        bindingView.srlHome.setColorSchemeResources(R.color.swiperefresh_color1, R.color.swiperefresh_color2, R.color.swiperefresh_color3, R.color.swiperefresh_color4);

        adapter = new RvAdapter();
        bindingView.rvHome.setAdapter(adapter);
        bindingView.rvHome.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.setEmptyView(R.layout.item_rv_empty, bindingView.rvHome);
        bindingView.srlHome.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
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
        }, bindingView.rvHome);


        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter3, View view, int position) {
                String id = adapter.getData().get(position).getId() + "";
                Intent intent = new Intent(getActivity(), NewsDeatilActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
    }

    int pageNo = 1;

    private void getList(boolean isLoadMore) {
        bindingView.srlHome.setRefreshing(false);
        String url = UrlConf.HomeNewsUrl;
        PostFormBuilder builder = OkHttpUtils.post().url(url)
                .tag(netTag)
                .addParams("token", App.token)
                .addParams("articletypeId", type + "")  // 1 党务  2 支部
                .addParams("pageSize", "10");
        String searchName = bindingView.etSearch.getText().toString().trim();
        if (!TextUtils.isEmpty(searchName)) {
            builder.addParams("name", searchName);
        } else {
            builder.addParams("pageNo", pageNo + "");
        }

        WNetUtil.StringCallBack(builder
                , url, (AppCompatActivity) getActivity(), new WNetUtil.WNetStringCallback() {
                    @Override
                    public void success(String response, int id) {
                        Logger.e(response);
                        bindingView.srlHome.setRefreshing(false);
                        Gson gson = new Gson();
                        HomeNewsBean bean = gson.fromJson(response, HomeNewsBean.class);
                        if (bean.getCode() == 100) {
                            int totalPage = bean.getTotalPage();
                            if (isLoadMore) {
                                if (pageNo > totalPage) {
                                    adapter.loadMoreEnd();
                                } else {
                                    adapter.addData(bean.getData());
                                    adapter.loadMoreComplete();
                                }
                            } else {
                                bindingView.srlHome.setRefreshing(false);
                                adapter.setNewData(bean.getData());
                                // 检查是否满一屏，如果不满足关闭loadMore
//                                adapter.disableLoadMoreIfNotFullPage(bindingView.rvHome);
                            }
                            if (bean.getData().size() != 0)
                                pageNo++;
                        } else {
                            if (isLoadMore)
                                adapter.loadMoreFail();
                        }

                    }

                    @Override
                    public void error(Call call, Exception e, int id) {
                        bindingView.srlHome.setRefreshing(false);
                    }
                });
    }

    class RvAdapter extends BaseQuickAdapter<HomeNewsBean.DataBean, BaseViewHolder> {
        public RvAdapter() {
            super(R.layout.item_rv_home);
        }

        @Override
        protected void convert(BaseViewHolder helper, HomeNewsBean.DataBean item) {
            helper.setText(R.id.tv_title, item.getArticleTitle())
                    .setText(R.id.tv_browse_number, item.getBrowseNumber() + "");
            Glide.with(getContext()).load(UrlConf.HOST + item.getArticleImg())
                    .into((ImageView) helper.getView(R.id.iv));
        }


    }


    public static class BannerItem {
        public String image;
        public String title;
        public String id;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return title;
        }
    }

    public class BannerViewFactory implements BannerView.ViewFactory<BannerItem> {
        @Override
        public View create(BannerItem item, final int position, ViewGroup container) {
            ImageView iv = new ImageView(container.getContext());
            iv.setOnClickListener(v -> {
                String id = item.getId();
                Intent intent = new Intent(getActivity(), NewsDeatilActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            });
            Glide.with(container.getContext().getApplicationContext()).load(item.image).centerCrop().into(iv);
            return iv;
        }
    }
}
