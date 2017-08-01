package com.wongxd.partymanage.party.adpter;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.wongxd.partymanage.R;
import com.wongxd.partymanage.base.rx.RxBus;
import com.wongxd.partymanage.base.rx.RxEventCodeType;
import com.wongxd.partymanage.utils.conf.UrlConf;

import java.util.List;

public class ExpandableItemAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    private final String TAG = ExpandableItemAdapter.class.getSimpleName();

    public static final int TYPE_LEVEL_0 = 0;
    public static final int TYPE_LEVEL_1 = 1;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ExpandableItemAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(TYPE_LEVEL_0, R.layout.item_expandable_lv0);
        addItemType(TYPE_LEVEL_1, R.layout.item_expandable_lv1);
    }


    @Override
    protected void convert(final BaseViewHolder holder, final MultiItemEntity item) {
        switch (holder.getItemViewType()) {
            case TYPE_LEVEL_0:
                final Level0Item lv0 = (Level0Item) item;
                holder.itemView.setVisibility(lv0.dataBean.getIsshow() == 1 ? View.VISIBLE : View.GONE);
                holder.setText(R.id.title, lv0.title)
                        .setImageResource(R.id.iv, lv0.isExpanded() ? R.drawable.arrow_b : R.drawable.arrow_r);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getAdapterPosition();
                        if (lv0.isExpanded()) {
                            collapse(pos);
                        } else {
                            expand(pos);
                        }
                    }
                });
                break;
            case TYPE_LEVEL_1:
                ImageView iv = holder.getView(R.id.iv);
                final Level1Item lv1 = (Level1Item) item;
                holder.itemView.setVisibility(lv1.item.getIsshow() == 1 ? View.VISIBLE : View.GONE);
                Glide.with(iv.getContext()).load(UrlConf.HOST + lv1.item.getIcon()).centerCrop().into(iv);
                holder.setText(R.id.tv, lv1.item.getName());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RxBus.getDefault().post(RxEventCodeType.PARTY_GOTO, lv1.item.getName());
                    }
                });
                break;

        }
    }
}