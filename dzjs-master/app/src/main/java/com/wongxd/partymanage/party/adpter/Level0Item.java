package com.wongxd.partymanage.party.adpter;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.wongxd.partymanage.party.bean.PartyBean;

/**
 * Created by luoxw on 2016/8/10.
 */
public class Level0Item extends AbstractExpandableItem<Level1Item> implements MultiItemEntity {
    public String title;
    public PartyBean.DataBean dataBean;

    public Level0Item(PartyBean.DataBean dataBean) {
        this.title = dataBean.getName();
        this.dataBean = dataBean;
    }

    @Override
    public int getItemType() {
        return ExpandableItemAdapter.TYPE_LEVEL_0;
    }

    @Override
    public int getLevel() {
        return 0;
    }
}
