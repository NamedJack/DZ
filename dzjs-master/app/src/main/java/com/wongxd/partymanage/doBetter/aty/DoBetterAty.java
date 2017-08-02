package com.wongxd.partymanage.doBetter.aty;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.wongxd.partymanage.App;
import com.wongxd.partymanage.R;
import com.wongxd.partymanage.base.BaseBindingActivity;
import com.wongxd.partymanage.base.RecyclerAdapter.MyRecyclerViewAdapter;
import com.wongxd.partymanage.base.RecyclerAdapter.MyViewHolder;
import com.wongxd.partymanage.databinding.AtyDoBetterListBinding;
import com.wongxd.partymanage.doBetter.bean.HonerBean;
import com.wongxd.partymanage.styleParty.view.RectImageView;
import com.wongxd.partymanage.utils.SystemBarHelper;
import com.wongxd.partymanage.utils.conf.UrlConf;
import com.wongxd.partymanage.utils.net.WNetUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by zyj on 2017/7/27.
 */

public class DoBetterAty extends BaseBindingActivity<AtyDoBetterListBinding> {

    private MyRecyclerViewAdapter adapter;
    private List<HonerBean.DataBean.GoodListBean> honerBeanList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_do_better_list);
        SystemBarHelper.tintStatusBar(this, ContextCompat.getColor(getApplicationContext(), R.color.app_red), 0f);
        initView();
        initData();
    }

    private void initData() {
        honerBeanList.clear();
        String url = UrlConf.DoBetter;
        WNetUtil.StringCallBack(OkHttpUtils.post().url(url)
                        .addParams("token", App.token)
                        .addParams("name", bindingView.doBetterSearch.getText().toString() + "")

                , url, DoBetterAty.this, "数据获取中", true, new WNetUtil.WNetStringCallback() {
                    @Override
                    public void success(String response, int id) {
                        HonerBean honerBean = null;
                        try {
                            honerBean = new Gson().fromJson(response, HonerBean.class);
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                        if(honerBean.getCode().equals("100")){
                            honerBeanList.addAll(honerBean.getData().getGoodList());
                        }
                        adapter.notifyDataSetChanged();
                        Log.e("msg", honerBean.getData().getGoodList().size() + "创优" );
                    }

                    @Override
                    public void error(Call call, Exception e, int id) {
                        Log.e("msg", "创优网络错误" + e.toString());

                    }
                });
    }

    private void initView() {
        bindingView.doBetterLeftIcon.setOnClickListener(clickListener);
        adapter = new MyRecyclerViewAdapter(this, R.layout.item_list_do_better, honerBeanList) {
            @Override
            public void convert(MyViewHolder holder, int position) {
                holder.setText(R.id.person_honer_title, honerBeanList.get(position).getName());
                holder.setText(R.id.person_honer_time, honerBeanList.get(position).getTime().split(" ")[0]);
                holder.setText(R.id.personal_honer_text, honerBeanList.get(position).getTypeName());

                if(position % 4 == 0){
                    holder.setBackgroundRes(R.id.better_base_ll_bg, R.drawable.person_honer_bg_qin);
                }else if(position % 4 == 1){
                    holder.setBackgroundRes(R.id.better_base_ll_bg, R.drawable.person_honer_bg_blue);
                }else if(position % 4 == 2){
                    holder.setBackgroundRes(R.id.better_base_ll_bg, R.drawable.person_honer_bg_red);
                }else if(position % 4 == 3){
                    holder.setBackgroundRes(R.id.better_base_ll_bg, R.drawable.person_honer_bg_yellow);
                }
                RectImageView iv = holder.getView(R.id.personal_honer_img);
                Glide.with(iv.getContext()).load(UrlConf.HOST + honerBeanList.get(position).getImg()).centerCrop().placeholder(R.drawable.placeholder).into(iv);
            }

        };
        bindingView.doBetterSearch.setOnEditorActionListener(editorActionListener);
        bindingView.doBetterRl.setLayoutManager(new LinearLayoutManager(this));
        bindingView.doBetterRl.setAdapter(adapter);
        adapter.setOnItemClickListener(new MyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int position) {
                Intent intent = new Intent(DoBetterAty.this, HonerAty.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("honerId", honerBeanList.get(position));
                intent.putExtras(bundle);
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
            switch (v.getId()) {
                case R.id.do_better_left_icon:
                    DoBetterAty.this.finish();
                    break;
                default:
                    break;
            }
        }
    };

    TextView.OnEditorActionListener editorActionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if ((actionId == 0 || actionId == 3) && event != null) {
                //点击搜索要做的操作
//                dataBeenList.clear();
                initData();
                ((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE))
                        .hideSoftInputFromWindow(DoBetterAty.this.getCurrentFocus()
                                .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

            }
            return false;
        }
    };
}
