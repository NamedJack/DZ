package com.wongxd.partymanage.promise.aty;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;

import com.wongxd.partymanage.R;
import com.wongxd.partymanage.base.BaseBindingActivity;
import com.wongxd.partymanage.databinding.AtyPromiseDetailsBinding;
import com.wongxd.partymanage.promise.bean.PromiseBean;

/**
 * Created by json on 2017/7/12.
 */

public class PromiseDetailActivity extends BaseBindingActivity <AtyPromiseDetailsBinding>{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        PromiseBean promiseBean = (PromiseBean) bundle.getSerializable("user");

        bindingView.promisePartyName.setText("姓名：" + promiseBean.getName());
        bindingView.promisePartyDepartment.setText("支 部：" + promiseBean.getDepartment());
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(R.layout.aty_promise_details);
    }
}
