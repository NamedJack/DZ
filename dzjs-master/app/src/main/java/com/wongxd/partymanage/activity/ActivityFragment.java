package com.wongxd.partymanage.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wongxd.partymanage.R;
import com.wongxd.partymanage.base.BaseBindingFragment;
import com.wongxd.partymanage.databinding.FgtActivityBinding;

/**
 * Created by wxd1 on 2017/5/25.
 */

public class ActivityFragment extends BaseBindingFragment<FgtActivityBinding> {
    @Override
    public int setContent() {
        return R.layout.fgt_activity;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String title = getArguments().getString("title");
        bindingView.setTitle(title);
    }
}
