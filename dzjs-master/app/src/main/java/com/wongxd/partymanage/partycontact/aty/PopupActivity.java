package com.wongxd.partymanage.partycontact.aty;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.wongxd.partymanage.R;
import com.wongxd.partymanage.partycontact.adapter.PopupAdapter;

import java.util.ArrayList;
import java.util.List;

import static android.view.ViewGroup.LayoutParams;

/**
 * Created by zyj on 2017/7/20.
 */

public class PopupActivity extends Activity {
    private LinearLayout itemAdd, itemYear, itemMonth;
    private PopupWindow mWindow;
    private ListView timeList;
    private List<String> monthList;
    private List<String> yearhList;
    private TextView tvAdd, tvMonth, tvYear;
    private int tvWidth;
    private ImageView yearImg, monthImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_popup);
        initView();
        initData();
        getwiht();
    }

    private void initData() {
        monthList = new ArrayList<>();
        monthList.add("第一季度");
        monthList.add("第二季度");
        monthList.add("第三季度");
        monthList.add("第四季度");
        monthList.add("所有季度");

        yearhList = new ArrayList<>();
        for (int i = 10; i < 99; i++) {
            yearhList.add("20" + i + "年度");
        }
        yearhList.add("所有年度");
    }

    private void initView() {
        itemAdd = (LinearLayout) findViewById(R.id.item_popup_add);
        itemYear = (LinearLayout) findViewById(R.id.item_popup_year);
        itemMonth = (LinearLayout) findViewById(R.id.item_popup_month);
        tvMonth = (TextView) findViewById(R.id.tv_moth);
        tvYear = (TextView) findViewById(R.id.tv_year);
        tvAdd = (TextView) findViewById(R.id.tv_add);
        yearImg = (ImageView) findViewById(R.id.item_year_img);
        monthImg = (ImageView) findViewById(R.id.item_month_img);
        itemAdd.setOnClickListener(clickListener);
        itemMonth.setOnClickListener(clickListener);
        itemYear.setOnClickListener(clickListener);
    }

    View.OnClickListener clickListener = v -> {

        switch (v.getId()) {
            case R.id.item_popup_add:
                Intent intent = new Intent(PopupActivity.this, AddContactPersonAty.class);
                startActivity(intent);
                PopupActivity.this.finish();
                break;
            case R.id.item_popup_month:
                initPopupWindow(tvMonth, 60, -30, monthList);
                monthImg.setVisibility(View.VISIBLE);
                break;
            case R.id.item_popup_year:
                initPopupWindow(tvYear, 60, -20, yearhList);
                yearImg.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    };

    private void initPopupWindow(TextView parentView, int widthOff, int heightOff, List<String> dataList) {
        View contentView = LayoutInflater.from(this).inflate(R.layout.contact_popupwindow, null, false);
        mWindow = new PopupWindow(contentView, tvWidth - 15, LayoutParams.WRAP_CONTENT, true);
        mWindow.setFocusable(true);
        mWindow.setOutsideTouchable(false);
        mWindow.setBackgroundDrawable(new ColorDrawable(0xffffff));//必须设置，否则bug
        mWindow.setOnDismissListener(dismissListener);
        timeList = (ListView) contentView.findViewById(R.id.time_list);
        PopupAdapter adapter = new PopupAdapter(this, dataList);
        timeList.setAdapter(adapter);
        mWindow.showAsDropDown(parentView, widthOff, heightOff);
        timeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                parentView.setText(dataList.get(position));
                yearImg.setVisibility(View.INVISIBLE);
                monthImg.setVisibility(View.INVISIBLE);
                mWindow.dismiss();
                mWindow = null;
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        PopupActivity.this.finish();
        return true;
    }

    public void getwiht() {
        ViewTreeObserver treeObserver = tvAdd.getViewTreeObserver();
        treeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                tvWidth = tvAdd.getWidth();
            }
        });

    }
    PopupWindow.OnDismissListener dismissListener = new PopupWindow.OnDismissListener() {
        @Override
        public void onDismiss() {
            yearImg.setVisibility(View.INVISIBLE);
            monthImg.setVisibility(View.INVISIBLE);
            mWindow = null;
        }
    };
}
