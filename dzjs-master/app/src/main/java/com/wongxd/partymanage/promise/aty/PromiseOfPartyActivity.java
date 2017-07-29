package com.wongxd.partymanage.promise.aty;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.google.gson.Gson;
import com.wongxd.partymanage.App;
import com.wongxd.partymanage.R;
import com.wongxd.partymanage.base.BaseBindingActivity;
import com.wongxd.partymanage.base.RecyclerAdapter.MyRecyclerViewAdapter;
import com.wongxd.partymanage.base.RecyclerAdapter.MyViewHolder;
import com.wongxd.partymanage.databinding.AtyPromiseOfPartyBinding;
import com.wongxd.partymanage.promise.bean.PromiseBean;
import com.wongxd.partymanage.utils.conf.UrlConf;
import com.wongxd.partymanage.utils.net.WNetUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class PromiseOfPartyActivity extends BaseBindingActivity<AtyPromiseOfPartyBinding> {

    private List<PromiseBean.DataBean> dataBeenList = new ArrayList<>();
    private MyRecyclerViewAdapter adapter;
    private PromiseBean.DataBean loginUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_promise_of_party);
        initview();
    }

    @Override
    protected void onResume() {

//        dataBeenList.clear();
        getNetData( "" + 1);
        super.onResume();
    }

    private List<PromiseBean.DataBean> getNetData( String pageNo) {
        String url = UrlConf.PromiseOfParty;
        String message = bindingView.promiseSearch.getText().toString();
        dataBeenList.clear();
        WNetUtil.StringCallBack(OkHttpUtils.post().url(url)
                        .addParams("token", App.token)
                        .addParams("search", message+"")
                        .addParams("pageNo", pageNo)
                , url, PromiseOfPartyActivity.this, "数据获取中", true, new WNetUtil.WNetStringCallback() {
                    @Override
                    public void success(String response, int id) {
//                        Log.e("msg", "搜索返回" + response);
                        PromiseBean promiseBean = new Gson().fromJson(response, PromiseBean.class);
                        if (promiseBean.getCode() == 100) {
                            dataBeenList.addAll(promiseBean.getData());
                        }
                        adapter.notifyDataSetChanged();

                    }

                    @Override
                    public void error(Call call, Exception e, int id) {
                        Log.e("msg", "网络错误" + e.toString());

                    }
                });
        return dataBeenList;
    }

    private void initview() {
        bindingView.promiseSrl.setProgressViewOffset(true, 0, 100);
        bindingView.promiseSrl.setColorSchemeResources(R.color.swiperefresh_color1, R.color.swiperefresh_color2, R.color.swiperefresh_color3, R.color.swiperefresh_color4);
        bindingView.promiseSrl.setOnRefreshListener(refreshListener);
        bindingView.promiseSearch.setOnEditorActionListener(editorActionListener);
        bindingView.rlPromise.setLayoutManager(new LinearLayoutManager(this));
//        Log.e("msg","传入集合"+dataBeenList.size());
        adapter = new MyRecyclerViewAdapter(this, R.layout.item_recycle_view, dataBeenList) {
            @Override
            public void convert(MyViewHolder holder, int position) {
                holder.setText(R.id.party_name, dataBeenList.get(position).getUsername());
                holder.setText(R.id.party_department, dataBeenList.get(position).getUnitname());
                holder.setText(R.id.party_study, "学习目标：" + dataBeenList.get(position).getStudy());
            }
        };
        bindingView.rlPromise.setAdapter(adapter);
        adapter.setOnItemClickListener(onItemClickListener);
        bindingView.leftIcon.setOnClickListener(clickListener);
        bindingView.rightIcon.setOnClickListener(clickListener);
        bindingView.promiseAdd.setOnClickListener(clickListener);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.left_icon:
                    PromiseOfPartyActivity.this.finish();
                    break;
                case R.id.promise_add:
                    Intent intent = new Intent(PromiseOfPartyActivity.this, PromiseDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("witchBtn", 0);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    break;
                default:
                    break;
            }
        }
    };

    MyRecyclerViewAdapter.OnItemClickListener onItemClickListener = new MyRecyclerViewAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int position) {
            Intent intent = new Intent(PromiseOfPartyActivity.this, PromiseDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("witchBtn", 1);
            bundle.putSerializable("personPromise", dataBeenList.get(position));
            intent.putExtras(bundle);
            startActivity(intent);
        }

        @Override
        public boolean onItemLonClick(View view, RecyclerView.ViewHolder viewHolder, int position) {
            return false;
        }
    };


    TextView.OnEditorActionListener editorActionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if ((actionId == 0 || actionId == 3) && event != null) {
                //点击搜索要做的操作
//                dataBeenList.clear();
                getNetData("" + 1);
                ((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE))
                        .hideSoftInputFromWindow(PromiseOfPartyActivity.this.getCurrentFocus()
                                .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

            }
            return false;
        }
    };
    SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {

//            dataBeenList.clear();
            getNetData("");
        }
    };

}
