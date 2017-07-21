package com.wongxd.partymanage.party.adpter;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.wongxd.partymanage.party.bean.PartyBean;

public class Level1Item extends AbstractExpandableItem<PartyBean.DataBean.PwmenuListBean> implements MultiItemEntity {
    public PartyBean.DataBean.PwmenuListBean item;

    public Level1Item(PartyBean.DataBean.PwmenuListBean item) {
        this.item = item;
    }

    @Override
    public int getItemType() {
        return ExpandableItemAdapter.TYPE_LEVEL_1;
    }

    @Override
    public int getLevel() {
        return 1;
    }
}