package com.wongxd.partymanage.party;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.orhanobut.logger.Logger;
import com.wongxd.partymanage.App;
import com.wongxd.partymanage.R;
import com.wongxd.partymanage.base.BaseBindingFragment;
import com.wongxd.partymanage.base.rx.RxBus;
import com.wongxd.partymanage.base.rx.RxEventCodeType;
import com.wongxd.partymanage.base.rx.Subscribe;
import com.wongxd.partymanage.base.rx.ThreadMode;
import com.wongxd.partymanage.databinding.FgtPartyBinding;
import com.wongxd.partymanage.doBetter.aty.DoBetterAty;
import com.wongxd.partymanage.party.aty.ThreeAndOneActivity;
import com.wongxd.partymanage.party.bean.PartyBean;
import com.wongxd.partymanage.party.bean.PartySectionBean;
import com.wongxd.partymanage.party.threeAndOne.StudyRecordActivity;
import com.wongxd.partymanage.partycontact.aty.ContactOfPartyAty;
import com.wongxd.partymanage.partyvote.aty.PartyVoteRlAty;
import com.wongxd.partymanage.payforparty.aty.PayForPartyAty;
import com.wongxd.partymanage.peoplestalk.aty.PeoplesTlakAty;
import com.wongxd.partymanage.promise.aty.PromiseOfPartyActivity;
import com.wongxd.partymanage.styleParty.aty.StyleOfPartyAty;
import com.wongxd.partymanage.utils.conf.UrlConf;
import com.wongxd.partymanage.utils.net.WNetUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by wxd1 on 2017/5/25.
 */

public class PartyFragment extends BaseBindingFragment<FgtPartyBinding> {

    private WGridLayoutManager manager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RxBus.getDefault().register(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        RxBus.getDefault().unRegister(this);
    }

    @Override
    public int setContent() {
        return R.layout.fgt_party;
    }

    //    ExpandableItemAdapter adapter;
//    ArrayList<MultiItemEntity> list = new ArrayList<>();
    SectionAdapter adapter;
    List<PartySectionBean> list = new ArrayList<>();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String title = getArguments().getString("title");
        bindingView.setTitle(title);

//        adapter = new ExpandableItemAdapter(list);

//        final GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
//        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                return adapter.getItemViewType(position) == ExpandableItemAdapter.TYPE_LEVEL_1 ? 1 : manager.getSpanCount();
//            }
//        });

//        bindingView.rvParty.setAdapter(adapter);
//        // important! setLayoutManager should be called after setAdapter
//        bindingView.rvParty.setLayoutManager(manager);

        adapter = new SectionAdapter(R.layout.item_expandable_lv1, R.layout.item_expandable_lv0, list);
        manager = new WGridLayoutManager(getContext(), 4);
        bindingView.rvParty.setLayoutManager(manager);
        bindingView.rvParty.setAdapter(adapter);
    }


    @Override
    protected void loadData() {
        getInfo();
    }

    private void getInfo() {
        if (list.size() != 0)
            bindingView.rvParty.scrollToPosition(0);
        manager.setScrollEnabled(false);
        String url = UrlConf.PartyWorkListUrl;

        WNetUtil.StringCallBack(OkHttpUtils.post().url(url).tag(netTag).addParams("token", App.token), url, (AppCompatActivity) getActivity(),
                "获取党务工作列表中", true, new WNetUtil.WNetStringCallback() {
                    @Override
                    public void success(String response, int id) {
                        Logger.e(response);
                        try {
                            PartyBean partyBean = new Gson().fromJson(response, PartyBean.class);
//                            list.clear();
//                            for (int i = 0; i < partyBean.getData().size(); i++) {
//                                Level0Item lv0 = new Level0Item(partyBean.getData().get(i));
//                                for (int j = 0; j < lv0.dataBean.getPwmenuList().size(); j++) {
//                                    Level1Item lv1 = new Level1Item(lv0.dataBean.getPwmenuList().get(j));
//                                    lv0.addSubItem(lv1);
//                                }
//                                list.add(lv0);
//                            }
//                            adapter.setNewData(list);
//                            adapter.expand(0);

                            List<PartyBean.DataBean> dataBean = partyBean.getData();
                            list.clear();
                            for (int i = 0; i < dataBean.size(); i++) {
                                list.add(new PartySectionBean(true, dataBean.get(i).getName()));
                                List<PartyBean.DataBean.PwmenuListBean> listBeen = dataBean.get(i).getPwmenuList();
                                for (int j = 0; j < listBeen.size(); j++) {
                                    if (i == 0 && j == 6) {
                                        list.add(new PartySectionBean(listBeen.get(j)));
                                        PartyBean.DataBean.PwmenuListBean moreBean = new PartyBean.DataBean.PwmenuListBean();
                                        moreBean.setName("更多");
                                        moreBean.setIcon("");
                                        moreBean.setId(11111);
                                        moreBean.setPid(listBeen.get(j).getPid());
                                        moreBean.setIsshow(1);
                                        moreBean.setPwmenuList(null);
                                        moreBean.setUrl("");
                                        list.add(new PartySectionBean(moreBean));
                                    } else
                                        list.add(new PartySectionBean(listBeen.get(j)));
                                }
                            }
                            adapter.setNewData(list);

                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void error(Call call, Exception e, int id) {

                    }
                });
    }


    @Subscribe(code = RxEventCodeType.PARTY_GOTO, threadMode = ThreadMode.MAIN)
    public void goTo(String name) {
        Intent intent = null;
        switch (name) {
            case "三会一课":
                intent = new Intent(getActivity(), ThreeAndOneActivity.class);
                break;
            case "学习心得":
                intent = new Intent(getActivity(), StudyRecordActivity.class);
                break;
            case "党员承诺":
                intent = new Intent(getActivity(), PromiseOfPartyActivity.class);
                break;
            case "民主评议谈心":
                intent = new Intent(getActivity(), PeoplesTlakAty.class);
                break;
            case "党费":
                intent = new Intent(getActivity(), PayForPartyAty.class);
                break;
            case "群众联系表":
                intent = new Intent(getActivity(), ContactOfPartyAty.class);
                break;
            case "党员风采":
                intent = new Intent(getActivity(), StyleOfPartyAty.class);
                break;
            case "创先争优":
                intent = new Intent(getActivity(), DoBetterAty.class);
                break;
            case "投票":
                intent = new Intent(getActivity(), PartyVoteRlAty.class);
                break;
            case "支部架构":
                break;
            case "党员发展":
                break;
        }

        if (null != intent)
            startActivity(intent);
    }

    class SectionAdapter extends BaseSectionQuickAdapter<PartySectionBean, BaseViewHolder> {

        /**
         * Same as QuickAdapter#QuickAdapter(Context,int) but with
         * some initialization data.
         *
         * @param layoutResId      The layout resource id of each item.
         * @param sectionHeadResId The section head layout id for each item
         * @param data             A new list is created out of this one to avoid mutable list
         */
        public SectionAdapter(int layoutResId, int sectionHeadResId, List<PartySectionBean> data) {
            super(layoutResId, sectionHeadResId, data);
        }

        @Override
        protected void convertHead(BaseViewHolder helper, PartySectionBean item) {
            helper.setText(R.id.title, item.header);
            if (helper.getAdapterPosition() != 0) {
                helper.itemView.setVisibility(item.isMoreClicked ? View.VISIBLE : View.GONE);
            } else helper.itemView.setVisibility(View.VISIBLE);
        }

        @Override
        protected void convert(BaseViewHolder helper, PartySectionBean item) {
            PartyBean.DataBean.PwmenuListBean bean = item.t;
            ImageView iv = helper.getView(R.id.iv);
            if (helper.getAdapterPosition() > 8)
                helper.itemView.setVisibility(item.isMoreClicked ? View.VISIBLE : View.GONE);
            else helper.itemView.setVisibility(View.VISIBLE);
            if (bean.getName().equals("更多"))
                Glide.with(iv.getContext()).load(R.drawable.icon_more).centerCrop().into(iv);
            else
                Glide.with(iv.getContext()).load(UrlConf.HOST + bean.getIcon()).centerCrop().into(iv);
            helper.setText(R.id.tv, bean.getName());
            helper.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (bean.getName().equals("更多")) {
                        List<PartySectionBean> list = getData();
                        for (PartySectionBean ite :
                                list) {
                            ite.isMoreClicked = !ite.isMoreClicked;
                        }
                        manager.setScrollEnabled(list.get(0).isMoreClicked);
                        bindingView.rvParty.scrollToPosition(0);
                        notifyDataSetChanged();
                    } else
                        RxBus.getDefault().post(RxEventCodeType.PARTY_GOTO, bean.getName());
                }
            });
        }
    }

    class WGridLayoutManager extends GridLayoutManager {
        public WGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
            super(context, attrs, defStyleAttr, defStyleRes);
        }

        public WGridLayoutManager(Context context, int spanCount) {
            super(context, spanCount);
        }

        public WGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
            super(context, spanCount, orientation, reverseLayout);
        }


        private boolean isScrollEnabled = true;

        public void setScrollEnabled(boolean flag) {
            this.isScrollEnabled = flag;
        }

        @Override
        public boolean canScrollVertically() {
            //Similarly you can customize "canScrollHorizontally()" for managing horizontal scroll
            return isScrollEnabled && super.canScrollVertically();
        }
    }
}
