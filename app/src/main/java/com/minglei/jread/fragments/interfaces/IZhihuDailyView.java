package com.minglei.jread.fragments.interfaces;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.minglei.jread.fragments.adapter.ZhihudailyAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

/**
 * Created by minglei on 2017/11/13.
 */

public interface IZhihuDailyView {
    RecyclerView getRecyclerView();

    ZhihudailyAdapter getAdapter();

    SmartRefreshLayout getRefreshLayout();

    LinearLayoutManager getLayoutManager();

}
