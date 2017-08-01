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

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import okhttp3.Call;

/**
 * Created by zyj on 2017/7/19.
 */

public class ContactOfPartyAty extends BaseBindingActivity<AtyContactPartyBinding> {
    private List<ContactParty.DataBean.ContactListBean> partyList = new ArrayList<>();
    private MyRecyclerViewAdapter adapter;
    private String yearString = "";
    private String monthString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_contact_party);
        SystemBarHelper.tintStatusBar(this, ContextCompat.getColor(getApplicationContext(), R.color.app_red), 0f);
        initData(yearString, monthString);
        initTitleBar();
//        RxBus.getDefault().register(this);
        RxBus.getDefault().toObservable(RxEventCodeType.CONTACT_REFRESH, String.class)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        filterRefresh(s);
                    }
                });
    }

    @Override
    protected void onDestroy() {
//        RxBus.getDefault().unRegister(this);
//        SPUtil.getInstance(this).saveInfo("searchYear", "");
//        SPUtil.getInstance(this).saveInfo("searchMonth", "");
//        Log.e("msg",yearString +"结束" +monthString);
        Log.e("msg","onDestroy");
        super.onDestroy();
    }


    //    @Subscribe(code = RxEventCodeType.CONTACT_REFRESH)
    public void filterRefresh(String s) {
        Log.e("msg", "" + s);
        int length = s.split("_").length;
        String year = s.split("_")[0];
        String month = "";
        if (!year.equals("")) {
            yearString = getNumberFormString(year);
            initData(yearString, "");
            TU.cT(yearString);
        }
        if (length > 1) {
            month = s.split("_")[1];
            if (!yearString.equals("") && month.equals("第一季度")) {
                initData(yearString, "1");
                TU.cT(yearString + month);
            } else if (!yearString.equals("") && month.equals("第二季度")) {
                TU.cT(yearString + month);
                initData(yearString, "2");
            } else if (!yearString.equals("") && month.equals("第三季度")) {
                TU.cT(yearString + month);
                initData(yearString, "3");
            } else if (!yearString.equals("") && month.equals("第四季度")) {
                TU.cT(yearString + month);
                initData(yearString, "4");
            } else if (!yearString.equals("") && month.equals("所有季度")) {
                TU.cT(yearString + month);
                initData(yearString, "");
            }
        }


    }

    @Override
    protected void onStart() {
        Log.e("msg","onStart");
        super.onStart();
    }

    @Override
    protected void onPause() {
        Log.e("msg","onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.e("msg","onStop");
        super.onStop();
    }


    @Override
    protected void onResume() {
        Log.e("msg","onResume");
        super.onResume();
    }

    private void initData(String year, String month) {
        Log.e("msg", year + "传入" + month);
        partyList.clear();
//        if(year == null){
//            year = "";
//        }if(month == null){
//            month = "";
//        }
        String url = UrlConf.PeopleContact;
        WNetUtil.StringCallBack(OkHttpUtils.post().url(url)
                        .addParams("token", App.token)
                        .addParams("year", year)
                        .addParams("quarter", month)
                        .addParams("pageSize", "10")
                , url, ContactOfPartyAty.this, "数据获取中", false, new WNetUtil.WNetStringCallback() {
                    @Override
                    public void success(String response, int id) {
                        ContactParty contactParty = null;
                        try {
                            contactParty = new Gson().fromJson(response, ContactParty.class);
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                        if (contactParty.getCode().equals("100")) {
                            partyList.addAll(contactParty.getData().getContactList());
                            adapter.notifyDataSetChanged();
                        }
                        Log.e("msg", response + "联系表");
                    }

                    @Override
                    public void error(Call call, Exception e, int id) {
                        Log.e("msg", "联系表网络错误" + e.toString());

                    }
                });
    }

    private void initTitleBar() {
        bindingView.contactTitle.setText("党员群众联系表");
        bindingView.contactLeftIcon.setOnClickListener(clickListener);
        bindingView.contactRightIcon.setOnClickListener(clickListener);
        bindingView.contactList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, R.layout.item_contact_party, partyList) {
            @Override
            public void convert(MyViewHolder holder, int position) {
                holder.setText(R.id.list_month, partyList.get(position).getTime());
                holder.setText(R.id.list_persion, "联系对象：" + partyList.get(position).getTarget());
                holder.setText(R.id.list_action, "互相帮助措施记录:" + partyList.get(position).getHelpRecord());
            }
        };
        bindingView.contactList.setAdapter(adapter);
        if (partyList.size() > 0) {
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
