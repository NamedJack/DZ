package com.wongxd.partymanage.partycontact.aty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.wongxd.partymanage.App;
import com.wongxd.partymanage.R;
import com.wongxd.partymanage.base.BaseBindingActivity;
import com.wongxd.partymanage.base.RecyclerAdapter.EndLessOnScrollListener;
import com.wongxd.partymanage.base.RecyclerAdapter.MyRecyclerViewAdapter;
import com.wongxd.partymanage.base.RecyclerAdapter.MyViewHolder;
import com.wongxd.partymanage.base.rx.RxBus;
import com.wongxd.partymanage.base.rx.RxEventCodeType;
import com.wongxd.partymanage.databinding.AtyContactPartyBinding;
import com.wongxd.partymanage.partycontact.bean.ContactParty;
import com.wongxd.partymanage.utils.SystemBarHelper;
import com.wongxd.partymanage.utils.TU;
import com.wongxd.partymanage.utils.conf.UrlConf;
import com.wongxd.partymanage.utils.net.WNetUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.Call;

/**
 * Created by zyj on 2017/7/19.
 */

public class ContactOfPartyAty extends BaseBindingActivity<AtyContactPartyBinding> {
    private List<ContactParty.DataBean.ContactListBean> partyList = new ArrayList<>();
    private MyRecyclerViewAdapter adapter;
    private String yearString = "";
    private String monthString = "";
    Disposable dis;
    private LinearLayoutManager linearLayoutManager;
    private boolean isLoadMore = false;
    private int currentPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_contact_party);
        SystemBarHelper.tintStatusBar(this, ContextCompat.getColor(getApplicationContext(), R.color.app_red), 0f);

        initTitleBar();

//        RxBus.getDefault().register(this);
        RxBus.getDefault().toObservable(RxEventCodeType.CONTACT_REFRESH, String.class)
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        dis = d;
                    }

                    @Override
                    public void onNext(String s) {
                        filterRefresh(s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData(yearString, monthString, 1);
    }

    @Override
    protected void onDestroy() {
        if (!dis.isDisposed()) dis.dispose();
        super.onDestroy();
    }


    public void filterRefresh(String s) {
        int length = s.split("_").length;
        String year = s.split("_")[0];
        String month = "";
        if (!year.equals("")) {
            partyList.clear();
            yearString = getNumberFormString(year);
            initData(yearString, "", 1);
        }
        if (length > 1) {
            month = s.split("_")[1];
            if (!yearString.equals("") && month.equals("第一季度")) {
                partyList.clear();
                monthString = "1";
                initData(yearString, monthString, 1);
            } else if (!yearString.equals("") && month.equals("第二季度")) {
                partyList.clear();
                monthString = "2";
                initData(yearString, monthString, 1);
            } else if (!yearString.equals("") && month.equals("第三季度")) {
                partyList.clear();
                monthString = "3";
                initData(yearString, monthString, 1);
            } else if (!yearString.equals("") && month.equals("第四季度")) {
                partyList.clear();
                monthString = "4";
                initData(yearString, monthString, 1);
            } else if (!yearString.equals("") && month.equals("所有季度")) {
                partyList.clear();
                initData(yearString, "", 1);
            }
        }


    }

    private void initData(String year, String month, int pageNo) {
//        partyList.clear();
        String url = UrlConf.PeopleContact;
        WNetUtil.StringCallBack(OkHttpUtils.post().url(url)
                        .addParams("token", App.token)
                        .addParams("year", year)
                        .addParams("quarter", month)
                        .addParams("pageNo", pageNo + "")
                , url, ContactOfPartyAty.this, "数据获取中", false, new WNetUtil.WNetStringCallback() {
                    @Override
                    public void success(String response, int id) {
                        ContactParty contactParty = null;
                        try {
                            contactParty = new Gson().fromJson(response, ContactParty.class);
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                        if (contactParty.getCode() == 100) {
                            if (contactParty.getData().getPage().getTotalPage() > contactParty.getData().getPage().getPageNo()) {
                                currentPage = contactParty.getData().getPage().getPageNo();
                                isLoadMore = true;
                            } else {
                                isLoadMore = false;
                            }

                            partyList.addAll(contactParty.getData().getContactList());
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void error(Call call, Exception e, int id) {
                        Log.e("msg", "联系表网络错误" + e.toString());

                    }
                });
    }

    private void initTitleBar() {
        linearLayoutManager = new LinearLayoutManager(this);
        bindingView.contactTitle.setText("党员群众联系表");
        bindingView.contactLeftIcon.setOnClickListener(clickListener);
        bindingView.contactRightIcon.setOnClickListener(clickListener);
        bindingView.contactList.setLayoutManager(linearLayoutManager);
        adapter = new MyRecyclerViewAdapter(this, R.layout.item_contact_party, partyList) {
            @Override
            public void convert(MyViewHolder holder, int position) {
                holder.setText(R.id.list_month, partyList.get(position).getTime());
                holder.setText(R.id.list_persion, "联系对象：" + partyList.get(position).getTarget());
                holder.setText(R.id.list_action, "互相帮助措施记录:" + partyList.get(position).getHelpRecord());
                if (position % 4 == 0) {
                    holder.setBackgroundRes(R.id.list_month, R.drawable.icon_contact_blue);
                } else if (position % 4 == 1) {
                    holder.setBackgroundRes(R.id.list_month, R.drawable.icon_contact_green);
                } else if (position % 4 == 2) {
                    holder.setBackgroundRes(R.id.list_month, R.drawable.icon_contact_qin);
                } else if (position % 4 == 3) {
                    holder.setBackgroundRes(R.id.list_month, R.drawable.icon_contact_yellow);
                }
            }
        };
        bindingView.contactList.setAdapter(adapter);
        adapter.setOnItemClickListener(new MyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int position) {
                Intent intent = new Intent(ContactOfPartyAty.this, ContactDetailAty.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("contactParty", partyList.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public boolean onItemLonClick(View view, RecyclerView.ViewHolder viewHolder, int position) {
                return false;
            }
        });
        bindingView.contactList.addOnScrollListener(new EndLessOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore() {
                loadMoreDate();
            }
        });
    }

    private void loadMoreDate() {
        if (isLoadMore) {
            initData(yearString, monthString, ++currentPage);
        } else {
            TU.cT("没有更多数据啦");
        }
    }

    View.OnClickListener clickListener = v -> {
        switch (v.getId()) {
            case R.id.contact_left_icon:
                ContactOfPartyAty.this.finish();
                break;
            case R.id.contact_right_icon:
                Intent intent = new Intent(ContactOfPartyAty.this, PopupActivity.class);
                startActivity(intent);
                break;
        }
    };

    public String getNumberFormString(String s) {
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(s);
        String number = m.replaceAll("").trim();
        return number;
    }
}
