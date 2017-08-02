package com.wongxd.partymanage.styleParty.aty;

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
import com.wongxd.partymanage.databinding.AtyStylePartyBinding;
import com.wongxd.partymanage.styleParty.bean.StyleParty;
import com.wongxd.partymanage.styleParty.view.RectImageView;
import com.wongxd.partymanage.utils.SystemBarHelper;
import com.wongxd.partymanage.utils.conf.UrlConf;
import com.wongxd.partymanage.utils.net.WNetUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by zyj on 2017/7/23.
 */

public class StyleOfPartyAty extends BaseBindingActivity<AtyStylePartyBinding> {
    private List<StyleParty.DataBean.MienListBean> partyList  = new ArrayList<>();
    private MyRecyclerViewAdapter myRecyclerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_style_party);
        SystemBarHelper.tintStatusBar(this, ContextCompat.getColor(getApplicationContext(), R.color.app_red), 0f);
        initView();
        initData();
    }

    private void initView() {
        bindingView.styleLeftIcon.setOnClickListener(onClickListener);
        bindingView.stylePartySearch.setOnEditorActionListener(editorActionListener);
        bindingView.styleRl.setLayoutManager(new LinearLayoutManager(this));
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(this, R.layout.item_of_style_party,partyList) {
            @Override
            public void convert(MyViewHolder holder, int position) {
                holder.setText(R.id.party_style_name, "姓 名 ：" + partyList.get(position).getUserName());
                holder.setText(R.id.party_style_story, partyList.get(position).getDescribe());
                RectImageView iv = holder.getView(R.id.party_style_img);
                Glide.with(iv.getContext()).load(UrlConf.HOST + partyList.get(position).getImg()).placeholder(R.drawable.placeholder).into(iv);
//                holder.setBackgroundRes();
            }
        };
        bindingView.styleRl.setAdapter(myRecyclerViewAdapter);
        myRecyclerViewAdapter.setOnItemClickListener(new MyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int position) {
                Intent intent = new Intent(StyleOfPartyAty.this, PersonStyleAty.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("whichPerson",partyList.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public boolean onItemLonClick(View view, RecyclerView.ViewHolder viewHolder, int position) {
                return false;
            }


        });
    }

    View.OnClickListener onClickListener = v -> {
        switch (v.getId()){
            case R.id.style_left_icon:
                StyleOfPartyAty.this.finish();
                break;
        }
    };


    private void initData() {
        partyList.clear();
        String url = UrlConf.StyleParty;
        WNetUtil.StringCallBack(OkHttpUtils.post().url(url)
                        .addParams("token", App.token)
                        .addParams("name", bindingView.stylePartySearch.getText().toString() + "")
                , url, StyleOfPartyAty.this, "数据获取中", true, new WNetUtil.WNetStringCallback() {
                    @Override
                    public void success(String response, int id) {
//                        Log.e("msg","风采 " + response);
                        StyleParty listBean = null;
                        try {
                            listBean = new Gson().fromJson(response, StyleParty.class);
                        } catch (JsonSyntaxException e) {
                            Log.e("msg","解析出错" + e.toString() );
                            e.printStackTrace();
                        }
                        if(listBean.getCode().equals("100")){
                            partyList.addAll(listBean.getData().getMienList());
                            myRecyclerViewAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void error(Call call, Exception e, int id) {
                        Log.e("msg", "网络错误" + e.toString());

                    }
                });



    }
    TextView.OnEditorActionListener editorActionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if ((actionId == 0 || actionId == 3) && event != null) {
                //点击搜索要做的操作
//                dataBeenList.clear();
                initData();
                ((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE))
                        .hideSoftInputFromWindow(StyleOfPartyAty.this.getCurrentFocus()
                                .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

            }
            return false;
        }
    };
}
