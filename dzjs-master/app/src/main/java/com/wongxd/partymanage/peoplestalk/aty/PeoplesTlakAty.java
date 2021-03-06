package com.wongxd.partymanage.peoplestalk.aty;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.wongxd.partymanage.App;
import com.wongxd.partymanage.R;
import com.wongxd.partymanage.base.BaseBindingActivity;
import com.wongxd.partymanage.base.RecyclerAdapter.EndLessOnScrollListener;
import com.wongxd.partymanage.base.RecyclerAdapter.MyRecyclerViewAdapter;
import com.wongxd.partymanage.base.RecyclerAdapter.MyViewHolder;
import com.wongxd.partymanage.databinding.AtyPeoplestalkBinding;
import com.wongxd.partymanage.peoplestalk.bean.PeopleTalk;
import com.wongxd.partymanage.utils.SystemBarHelper;
import com.wongxd.partymanage.utils.TU;
import com.wongxd.partymanage.utils.conf.UrlConf;
import com.wongxd.partymanage.utils.net.WNetUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by zyj on 2017/7/19.
 */

public class PeoplesTlakAty extends BaseBindingActivity<AtyPeoplestalkBinding> {
    private List<PeopleTalk.DataBean.ConversationListBean> talkList = new ArrayList<>();
    ;
    private MyRecyclerViewAdapter talkAdapter;
    private LinearLayoutManager linearLayoutManager;
    private boolean isLoadMore = false;
    private int currentPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_peoplestalk);
        SystemBarHelper.tintStatusBar(this, ContextCompat.getColor(getApplicationContext(), R.color.app_red), 0f);
        initView();

    }

    @Override
    protected void onResume() {
        talkList.clear();
        initData(1);
        super.onResume();
    }

    private void initView() {
        linearLayoutManager = new LinearLayoutManager(this);
        bindingView.peopleTlakLeftIcon.setOnClickListener(clickListener);
        bindingView.tlakRightIcon.setOnClickListener(clickListener);
        bindingView.peopleTlakSearch.setOnEditorActionListener(editorActionListener);

        talkAdapter = new MyRecyclerViewAdapter(this, R.layout.item_tlak_list, talkList) {
            @Override
            public void convert(MyViewHolder holder, int position) {
                holder.setText(R.id.talk_persion, "谈心对象：" + talkList.get(position).getTarget());
                if (talkList.get(position).getPolitics().equals("" + 1)) {
                    holder.setText(R.id.poltics_persion, "政治面貌：党员");
                } else if (talkList.get(position).getPolitics().equals("" + 2)) {
                    holder.setText(R.id.poltics_persion, "政治面貌：预备党员");
                } else if (talkList.get(position).getPolitics().equals("" + 3)) {
                    holder.setText(R.id.poltics_persion, "政治面貌：党员发展对象");
                } else if (talkList.get(position).getPolitics().equals("" + 4)) {
                    holder.setText(R.id.poltics_persion, "政治面貌：入党积极分子");
                } else if (talkList.get(position).getPolitics().equals("" + 5)) {
                    holder.setText(R.id.poltics_persion, "政治面貌：共青团员");
                } else if (talkList.get(position).getPolitics().equals("" + 6)) {
                    holder.setText(R.id.poltics_persion, "政治面貌：群众");
                } else if (talkList.get(position).getPolitics().equals("" + 17)) {
                    holder.setText(R.id.poltics_persion, "政治面貌：其他");
                }

                holder.setText(R.id.advice_persion, "意见和建议：" + talkList.get(position).getProposal());
                holder.setText(R.id.slft_persion, "自我认识及措施" + talkList.get(position).getAutognosis());
                holder.setText(R.id.tlak_years, talkList.get(position).getTime().split("-")[0]);
                holder.setText(R.id.tlak_day, talkList.get(position).getTime().split("-")[1] + "-" + talkList.get(position).getTime().split("-")[2]);
            }
        };
        bindingView.peopleLv.setLayoutManager(linearLayoutManager);
        bindingView.peopleLv.setAdapter(talkAdapter);
        bindingView.peopleLv.addOnScrollListener(new EndLessOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore() {
                loadMoreData();
            }
        });
    }

    private void loadMoreData() {
//        Log.e("msg","isLoadMore" + isLoadMore );
        if (isLoadMore) {
            initData( ++currentPage);
        }else {
            TU.cT("没有更多数据啦");
        }
    }


    private void initData(int pageNo) {
//        talkList.clear();
        String url = UrlConf.PeopleTlak;
        WNetUtil.StringCallBack(OkHttpUtils.post().url(url)
                        .addParams("token", App.token)
                        .addParams("search", bindingView.peopleTlakSearch.getText().toString() + "")
                        .addParams("pageNo", pageNo + "")
                , url, PeoplesTlakAty.this, "数据获取中", true, new WNetUtil.WNetStringCallback() {
                    @Override
                    public void success(String response, int id) {
//                        Log.e("msg", "谈心返回 " + response);
                        PeopleTalk peopleTalk = null;
                        try {
                            peopleTalk = new Gson().fromJson(response, PeopleTalk.class);
                        } catch (JsonSyntaxException e) {
                            Log.e("msg", " 解析出错 " + e.toString());
                            e.printStackTrace();
                        }
                        if (peopleTalk.getCode().equals(100 + "")) {
                            if (peopleTalk.getData().getPage().getTotalPage() > peopleTalk.getData().getPage().getPageNo()) {
                                currentPage = peopleTalk.getData().getPage().getPageNo();
                                isLoadMore = true;
                            } else {
                                isLoadMore = false;
                            }
                            if(peopleTalk.getData().getConversationList().size() == 0){
                                TU.cT("暂无数据");
                            }else {
                                talkList.addAll(peopleTalk.getData().getConversationList());
                                talkAdapter.notifyDataSetChanged();
                            }

                        }

                    }

                    @Override
                    public void error(Call call, Exception e, int id) {
                        Log.e("msg", "网络错误" + e.toString());

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
                startActivity(intent);
                break;
            default:
                break;
        }

    };

    TextView.OnEditorActionListener editorActionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if ((actionId == 0 || actionId == 3) && event != null) {
                //点击搜索要做的操作
                talkList.clear();
                initData(currentPage);
                ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                        .hideSoftInputFromWindow(PeoplesTlakAty.this.getCurrentFocus()
                                .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

            }
            return false;
        }
    };


}
