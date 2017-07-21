package com.wongxd.partymanage.party.threeAndOne;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.wongxd.partymanage.App;
import com.wongxd.partymanage.R;
import com.wongxd.partymanage.base.BaseBindingActivity;
import com.wongxd.partymanage.databinding.AtyMeetingDetailBinding;
import com.wongxd.partymanage.party.threeAndOne.bean.MeetingDetailBean;
import com.wongxd.partymanage.utils.OpenFileThing;
import com.wongxd.partymanage.utils.SystemBarHelper;
import com.wongxd.partymanage.utils.TU;
import com.wongxd.partymanage.utils.conf.UrlConf;
import com.wongxd.partymanage.utils.net.WNetUtil;
import com.wongxd.partymanage.widget.numberInputView.NumberInputView;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.List;

import okhttp3.Call;

public class MeetingDetailActivity extends BaseBindingActivity<AtyMeetingDetailBinding> {

    private AppCompatActivity thisActivity;
    private Context mContext;
    private String id;
    private String signCode = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_meeting_detail);
        id = getIntent().getStringExtra("id");
        SystemBarHelper.tintStatusBar(this, getResources().getColor(R.color.app_red), 0f);
        thisActivity = this;
        mContext = this.getApplicationContext();
        initView();
        getInfo(id);
    }

    private void initView() {
        bindingView.ivReturn.setOnClickListener(v -> finish());

        bindingView.btnSignFinish.setOnClickListener(v -> endSign(id));

        bindingView.btnSignCode.setOnClickListener(v -> {
            Button btn = (Button) v;
            switch (((Button) v).getText().toString().trim()) {
                case "签到码":
                    getSignCode(id);
                    break;

                case "会议签到":
                    sign(id);
                    break;

                case "开始补课":
                    startMakingUpClasses(id);
                    break;
                case "结束补课":
                    endMakingUpClasses(id);
                    break;

                case "已结束":
                case "已签到":
                case "未开始签到":
                    TU.cT(btn.getText().toString().trim());
                    break;
            }
        });
    }

    private void getInfo(String id) {
        String url = UrlConf.MeetingDetailUrl;
        WNetUtil.StringCallBack(OkHttpUtils.post().tag(netTag).url(url)
                        .addParams("token", App.token)
                        .addParams("id", id)
                , url, MeetingDetailActivity.this, "获取通知详情", true, new WNetUtil.WNetStringCallback() {
                    @Override
                    public void success(String response, int id) {
                        Logger.e(response);
                        MeetingDetailBean detailBean = new Gson().fromJson(response, MeetingDetailBean.class);
                        if (detailBean.getCode() == 100) {
                            String title = "";
                            MeetingDetailBean.DataBean.MeetingnoticeBean meetingBean = detailBean.getData().getMeetingnotice();
                            bindingView.tvRecord.setOnClickListener(v -> {
                                Intent intent = new Intent(thisActivity, MeetingRecordActivity.class);
                                intent.putExtra("id", meetingBean.getId() + "");
                                intent.putExtra("info", meetingBean);
                                startActivity(intent);
                            });

                            /**
                             * 附件
                             */
                            List<MeetingDetailBean.DataBean.Enclosure> enclosureList = detailBean.getData().getEnclosure();
                            if (enclosureList != null && enclosureList.size() != 0) {
                                bindingView.llEnclosure.setVisibility(View.VISIBLE);
                                for (MeetingDetailBean.DataBean.Enclosure item : enclosureList) {
                                    TextView tv = new TextView(mContext);
                                    tv.setTextColor(ContextCompat.getColor(mContext, R.color.app_red));
                                    tv.setCompoundDrawables(getResources().getDrawable(R.drawable.enclose), null, null, null);
                                    tv.setText(item.getFilename());
                                    tv.setPadding(0, 30, 0, 10);
                                    tv.setOnClickListener(v -> {
                                        //try to download this
                                        File dir = new File(Environment.getExternalStorageDirectory(), "/dzjs");
                                        if (!dir.exists()) dir.mkdirs();

                                        File file = new File(dir, item.getFilename());
                                        if (file.exists()) {
                                            TU.cT("文件下载完成，请到" + dir.getAbsolutePath() + "/" + file.getName() + "查看");
                                            OpenFileThing.openAssignFolder(thisActivity, file.getAbsolutePath());
                                            return;
                                        }
                                        String url = UrlConf.HOST + item.getFileurl();
                                        WNetUtil.FileCallBack(OkHttpUtils.post().url(url).tag(netTag),
                                                url, dir.getAbsolutePath(), item.getFilename(), thisActivity, "下载附件中", new WNetUtil.WNetFileCallback() {
                                                    @Override
                                                    public void FileSuccess(File file, int id) {
                                                        TU.cT("文件下载完成，请到" + dir.getAbsolutePath() + "/" + file.getName() + "查看");
                                                    }

                                                    @Override
                                                    public void inProgress(int progress) {

                                                    }

                                                    @Override
                                                    public void error(Call call, Exception e, int id) {
                                                        TU.cT("文件下载失败  " + e.getMessage());
                                                    }
                                                });
                                    });

                                    bindingView.llEnclosure.addView(tv);
                                }

                            } else bindingView.llEnclosure.setVisibility(View.GONE);


                            switch (meetingBean.getMeetingType()) { //会议类型 1：党课 2：党委会：3：支委会4：支部大会
                                case 1:
                                    title = "党课";
                                    break;
                                case 2:
                                    title = "党委会";
                                    break;
                                case 3:
                                    title = "支委会";
                                    break;
                                case 4:
                                    title = "支部大会";
                                    break;
                            }
                            bindingView.tvTitle.setText(title);
                            bindingView.tvTime.setText("时间：" + meetingBean.getTime());
                            bindingView.tvLocation.setText("地点：" + meetingBean.getAddress());
                            bindingView.tvPeople.setText("资料员：" + detailBean.getData().getDataclerkname());
                            String allPeople = "参会人员：";
                            if (detailBean.getData().getUsername() != null && detailBean.getData().getUsername().size() != 0) {
                                for (String s : detailBean.getData().getUsername()) {
                                    allPeople += s + "、";
                                }
                            }
                            bindingView.tvAllPeople.setText(allPeople);
                            bindingView.webview.loadData(meetingBean.getContent(), "text/html; charset=UTF-8", null);//这种写法可以正确解码

                            //签到状态
                            int signState = detailBean.getData().getSignstatus();
                            if (signState == 1) {//1.未签到2.已签到
                                bindingView.btnSignCode.setText("会议签到");
                                //判断 status 签到状态 0.未开始签到 1.开始签到 2.结束签到(需补课)
                                int state = meetingBean.getStatus();
                                switch (state) {
                                    case 0:
                                        bindingView.btnSignCode.setText("未开始签到");
                                        break;
                                    case 2:
                                        bindingView.btnSignCode.setText("开始补课");
                                        break;
                                }
                            } else {
                                bindingView.btnSignCode.setText("已签到");
                            }

                            //签到状态  资料员
                            if (detailBean.getData().getIsDataclerk().equals("1")) {//是否是资料员 1：是 2：不是
                                bindingView.btnSignCode.setText("签到码");
                                bindingView.btnSignCode.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.app_red));
                                bindingView.btnSignCode.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.btn_red_shape));
                                if (detailBean.getData().getSignstatus() == 1) {//1.未签到2.已签到
                                    bindingView.llSign.setVisibility(View.VISIBLE);
                                    bindingView.btnSignCode.setVisibility(View.GONE);
                                } else {
                                    bindingView.llSign.setVisibility(View.GONE);
                                    bindingView.btnSignCode.setVisibility(View.VISIBLE);
                                    bindingView.btnSignCode.setText("已结束");
                                }
                            }

                            String tempId = meetingBean.getId() + "";
                            bindingView.ivRecord.setOnClickListener(v -> {
                                Intent i = new Intent(thisActivity, AddStudyRecordActivity.class);
                                i.putExtra("id", tempId);
                                i.putExtra("typeId", 1 + "");//心得类型(1.三课一会2.自写心得3.学习资料4.支部动态5.党务动态)
                                startActivity(i);
                            });
                        }


                    }

                    @Override
                    public void error(Call call, Exception e, int id) {

                    }
                }
        );
    }


    /**
     * 资料员获取签到码
     *
     * @param id 会议id
     */
    private void getSignCode(String id) {
        String url = UrlConf.GetSignCodelUrl;
        WNetUtil.StringCallBack(OkHttpUtils.post().tag(netTag).url(url).addParams("token", App.token)
                        .addParams("id", id)
                , url, thisActivity, "获取签到码中", true, new WNetUtil.WNetStringCallback() {
                    @Override
                    public void success(String response, int id) {
                        Logger.e(response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.optInt("code") == 100) {
                                signCode = jsonObject.optString("signcode");
                                bindingView.tvSignCode.setText(signCode);
                            } else TU.cT(jsonObject.optString("msg"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void error(Call call, Exception e, int id) {

                    }
                });

    }


    /**
     * 资料员结束签到
     *
     * @param id 会议id
     */
    private void endSign(String id) {
        String url = UrlConf.EndSignlUrl;
        WNetUtil.StringCallBack(OkHttpUtils.post().tag(netTag).url(url).addParams("token", App.token)
                        .addParams("id", id)
                , url, thisActivity, "结束签到中", true, new WNetUtil.WNetStringCallback() {
                    @Override
                    public void success(String response, int id) {
                        Logger.e(response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            TU.cT(jsonObject.optString("msg"));
                            if (jsonObject.optInt("code") == 100) {
                                bindingView.btnSignCode.setText("已结束");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void error(Call call, Exception e, int id) {

                    }
                });

    }


    /**
     * 参会人员签到
     *
     * @param id 会议id
     */

    private void sign(String id) {


        View view = View.inflate(mContext, R.layout.dialog_input_code, null);
        NumberInputView inputView = (NumberInputView) view.findViewById(R.id.number_input_box);

        view.findViewById(R.id.btn_sure).setOnClickListener(v -> {
            String code = inputView.getCurrentNumber();
            if (TextUtils.isEmpty(code)) {
                TU.cT("请输入签到码");
                return;
            }
            Logger.e(code);
            String url = UrlConf.SignlUrl;
            WNetUtil.StringCallBack(OkHttpUtils.post().tag(netTag).url(url).addParams("token", App.token)
                            .addParams("id", id)
                            .addParams("signcode", code)
                    , url, thisActivity, "签到中", true, new WNetUtil.WNetStringCallback() {
                        @Override
                        public void success(String response, int id) {
                            Logger.e(response);
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                TU.cT(jsonObject.optString("msg"));
                                if (jsonObject.optInt("code") == 100) {
                                    bindingView.btnSignCode.setText("已签到");
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void error(Call call, Exception e, int id) {

                        }
                    });
        });
        final AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(true);
        dialog.getWindow().setContentView(view);
        dialog.getWindow().clearFlags(
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                        WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.show();
    }

    private long startMakingUpClassesTime = 0;

    /**
     * 开始补课
     *
     * @param id 会议id
     */
    private void startMakingUpClasses(String id) {
        String url = UrlConf.MakingUpClassesUrl;
        WNetUtil.StringCallBack(OkHttpUtils.post().tag(netTag).url(url).addParams("token", App.token)
                        .addParams("id", id)
                , url, thisActivity, "请求补课中", true, new WNetUtil.WNetStringCallback() {


                    @Override
                    public void success(String response, int id) {
                        Logger.e(response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            TU.cT(jsonObject.optString("msg"));
                            if (jsonObject.optInt("code") == 100) {
                                startMakingUpClassesTime = SystemClock.currentThreadTimeMillis();
                                bindingView.btnSignCode.setText("结束补课");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void error(Call call, Exception e, int id) {

                    }
                });

    }


    /**
     * 结束补课
     *
     * @param id 会议id
     */
    private void endMakingUpClasses(String id) {
        if (SystemClock.currentThreadTimeMillis() - startMakingUpClassesTime < 20000) {
            TU.cT("补课满20秒才算补课成功,请再等等。");
            return;
        }
        String url = UrlConf.EndMakingUpClassesUrl;
        WNetUtil.StringCallBack(OkHttpUtils.post().tag(netTag).url(url).addParams("token", App.token)
                        .addParams("id", id)
                , url, thisActivity, "请求结束补课中", true, new WNetUtil.WNetStringCallback() {
                    @Override
                    public void success(String response, int id) {
                        Logger.e(response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            TU.cT(jsonObject.optString("msg"));
                            if (jsonObject.optInt("code") == 100) {
                                bindingView.btnSignCode.setText("已补课");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void error(Call call, Exception e, int id) {

                    }
                });

    }

}
