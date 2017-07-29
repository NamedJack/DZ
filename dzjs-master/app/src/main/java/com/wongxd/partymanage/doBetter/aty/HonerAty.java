package com.wongxd.partymanage.doBetter.aty;

import android.support.annotation.LayoutRes;

import com.wongxd.partymanage.R;
import com.wongxd.partymanage.base.BaseBindingActivity;
import com.wongxd.partymanage.databinding.AtyHonerBinding;

/**
 * Created by zyj on 2017/7/27.
 */

public class HonerAty extends BaseBindingActivity<AtyHonerBinding> {
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        setContentView(R.layout.aty_honer);
    }
}
