package com.minglei.jread.fragments.interfaces;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.minglei.jread.fragments.adapter.ZhihudailyAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

/**
 * Created by minglei on 2017/11/13.
 */

public interface IZhihuDailyView {
    RecyclerView getRecyclerView();

    ZhihudailyAdapter getAdapter();

    RefreshLayout getRefreshLayout();

    LinearLayoutManager getLayoutManager();
}
