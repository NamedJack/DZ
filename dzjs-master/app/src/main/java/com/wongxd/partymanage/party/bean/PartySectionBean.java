package com.wongxd.partymanage.party.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by wxd1 on 2017/6/22.
 */

public class PartySectionBean extends SectionEntity<PartyBean.DataBean.PwmenuListBean> {
    public PartySectionBean(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public PartySectionBean(PartyBean.DataBean.PwmenuListBean pwmenuListBean) {
        super(pwmenuListBean);
    }

    public boolean isMoreClicked = false;

    public void setVisble(boolean visble) {
        t.setIsshow(visble ? 1 : 0);
    }
}
