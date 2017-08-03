package com.wongxd.partymanage.base.RecyclerAdapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by zyj on 2017/8/3.
 */

public abstract class EndLessOnScrollListener extends RecyclerView.OnScrollListener {
    //声明一个LinearLayoutManager
    private LinearLayoutManager mLinearLayoutManager;


    //当前页，从0开始
    private int currentPage = 0;


    //已经加载出来的Item的数量
    private int totalItemCount;


    //主要用来存储上一个totalItemCount
    private int previousTotal = 0;


    //在屏幕上可见的item数量
    private int visibleItemCount;


    //在屏幕可见的Item中的第一个
    private int firstVisibleItem;

    //屏幕可见的最后一个 Item
    private int lastVisibleItem;

    //是否正在上拉数据
    private boolean loading = true;


    public EndLessOnScrollListener(LinearLayoutManager linearLayoutManager) {
        this.mLinearLayoutManager = linearLayoutManager;
    }


    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = mLinearLayoutManager.getItemCount();
        firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();

        lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();

        int state = recyclerView.getScrollState();
//Log.e("msg",visibleItemCount +" " +lastVisibleItem + " " + totalItemCount  +" " +state);
//Log.e("msg","dy"+ dy);
        if (visibleItemCount > 0 && state == 1 && lastVisibleItem == totalItemCount - 1 && dy > 0) {
//            Log.e("msg","load");
            onLoadMore();
        }

    }


    /**
     * 提供一个抽闲方法，在Activity中监听到这个EndLessOnScrollListener
     * 并且实现这个方法
     */
    public abstract void onLoadMore();

}
