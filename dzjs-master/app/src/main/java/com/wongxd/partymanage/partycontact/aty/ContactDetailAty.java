package com.wongxd.partymanage.partycontact.aty;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.wongxd.partymanage.App;
import com.wongxd.partymanage.R;
import com.wongxd.partymanage.base.BaseBindingActivity;
import com.wongxd.partymanage.databinding.AtyContactDetailBinding;
import com.wongxd.partymanage.partycontact.bean.ContactDetailBean;
import com.wongxd.partymanage.partycontact.bean.ContactParty;
import com.wongxd.partymanage.utils.conf.UrlConf;
import com.wongxd.partymanage.utils.net.WNetUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import okhttp3.Call;

/**
 * Created by zyj on 2017/7/21.
 */

public class ContactDetailAty extends BaseBindingActivity<AtyContactDetailBinding> {
    private ContactParty.DataBean.ContactListBean contactParty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_contact_detail);
        initView();
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        contactParty  = (ContactParty.DataBean.ContactListBean) bundle.getSerializable("contactParty");
        String id = contactParty.getId();
        String  url = UrlConf.PeopleContactDetail;

        WNetUtil.StringCallBack(OkHttpUtils.post().url(url)
                        .addParams("token", App.token)
                        .addParams("id", id)
                , url, ContactDetailAty.this, "数据获取中", true, new WNetUtil.WNetStringCallback() {
                    @Override
                    public void success(String response, int id) {
                        ContactDetailBean detailBean = null;
                        try {
                            detailBean = new Gson().fromJson(response, ContactDetailBean.class);
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                        if(detailBean.getCode().equals("100")){
                            bindingView.contactHelpName.setText(detailBean.getData().getTarget());
                            bindingView.contactHelpTime.setText(detailBean.getData().getTime());
                            bindingView.contactHelpWriteDown.setText(detailBean.getData().getHelpRecord());
                            bindingView.contactHelpDo.setText(detailBean.getData().getImplement());
                        }
//                        Log.e("msg", "联系表详情" + response);
//                        JSONObject jsonObject = new JSONObject(response);
//                        String implement = jsonObject.getString("implement");
//
//                        if(contactParty.getCode().equals("100")){
//                            partyList.addAll(contactParty.getData().getContactList());
//                            adapter.notifyDataSetChanged();
//                        }
//                        Log.e("msg", response + "联系表" );
                    }

                    @Override
                    public void error(Call call, Exception e, int id) {
                        Log.e("msg", "联系表网络错误" + e.toString());

                    }
                });




    }

    private void initView() {
        bindingView.detailLeftIcon.setOnClickListener(clickListener);
        bindingView.detailRightIcon.setOnClickListener(clickListener);
    }

    View.OnClickListener clickListener = v -> {
      switch (v.getId()) {
          case R.id.detail_left_icon:
              ContactDetailAty.this.finish();
              break;
          case R.id.detail_right_icon:
//              Intent intent = new Intent(ContactDetailAty.this, PayForPartyAty.class);
//              startActivity(intent);
              break;
          default:
              break;
      }
    };

}
