package com.wongxd.partymanage.promise.aty;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.wongxd.partymanage.R;
import com.wongxd.partymanage.base.BaseBindingActivity;
import com.wongxd.partymanage.databinding.AtyPromiseOfPartyBinding;
import com.wongxd.partymanage.promise.adapter.PromiseRecycleAdapter;
import com.wongxd.partymanage.promise.bean.PromiseBean;
import com.wongxd.partymanage.utils.TU;

import java.util.ArrayList;
import java.util.List;

public class PromiseOfPartyActivity extends BaseBindingActivity<AtyPromiseOfPartyBinding> {

    private List<PromiseBean> promiseBeanList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_promise_of_party);
//        bindingView.tvTitle.setText("党员承诺");
        initview();
        initData();
//
//        WNetUtil.StringCallBack();
//        WNetUtil.StringCallBack(OkHttpUtils.post().url(UrlConf.OutLoginUrl).tag(netTag).addParams("0", ""), "", this, "", true, new WNetUtil.WNetStringCallback() {
//                    @Override
//                    public void success(String response, int id) {
//
//                    }
//
//                    @Override
//                    public void error(Call call, Exception e, int id) {
//
//                    }
//                }
//        );
    }

    private void initview() {
        bindingView.promiseSrl.setProgressViewOffset(true, 0, 100 );
        bindingView.promiseSrl.setColorSchemeResources(R.color.swiperefresh_color1, R.color.swiperefresh_color2, R.color.swiperefresh_color3, R.color.swiperefresh_color4);
        bindingView.leftIcon.setOnClickListener(clickListener);
        bindingView.rightIcon.setOnClickListener(clickListener);
    }

    private void initData() {
        promiseBeanList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            promiseBeanList.add(new PromiseBean("张三"+i,"第一党支部","深入学习党章党规。。。。。"));
        }
        PromiseRecycleAdapter adapter = new PromiseRecycleAdapter(promiseBeanList, PromiseOfPartyActivity.this);
        bindingView.rlPromise.setLayoutManager(new LinearLayoutManager(this));
        adapter.setOnItemClickListener(new PromiseRecycleAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int postion) {
                TU.cT("" + promiseBeanList.get(postion).getName() );
            }
        });
        bindingView.rlPromise.setAdapter(adapter);
    }
    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.left_icon:
                    PromiseOfPartyActivity.this.finish();
                    break;
            }
        }
    };

}
