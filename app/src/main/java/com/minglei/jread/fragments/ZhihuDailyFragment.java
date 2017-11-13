package com.minglei.jread.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.minglei.jread.R;
import com.minglei.jread.base.BaseFragment;
import com.minglei.jread.fragments.adapter.ZhihuDailyListAdapter;
import com.minglei.jread.fragments.interfaces.IZhihuDailyView;
import com.minglei.jread.presenter.ZhihuDailyPresenter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZhihuDailyFragment extends BaseFragment implements IZhihuDailyView{

    private ZhihuDailyPresenter mPresenter;
    private View mRootView;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private RefreshLayout mRefreshLayout;

    public static ZhihuDailyFragment newInstance() {

        Bundle args = new Bundle();

        ZhihuDailyFragment fragment = new ZhihuDailyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new ZhihuDailyPresenter(getActivity(), this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_zhihu_daily, container, false);
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recycler_zhihu_daily);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mPresenter.getDailyNews();

        mRefreshLayout = (RefreshLayout)mRootView.findViewById(R.id.refreshLayout);
        mRefreshLayout.setEnableAutoLoadmore(false);
        mRefreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        mRefreshLayout.setRefreshFooter(new ClassicsFooter(getActivity()).setSpinnerStyle(SpinnerStyle.Scale));

        return mRootView;
    }

    @Override
    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    @Override
    public RefreshLayout getRefreshLayout() {
        return mRefreshLayout;
    }

    @Override
    public LinearLayoutManager getLayoutManager() {
        return mLayoutManager;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onUnInit();
    }
}
