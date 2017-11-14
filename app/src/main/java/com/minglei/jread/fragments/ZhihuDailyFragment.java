package com.minglei.jread.fragments;


import android.content.Context;
import android.graphics.Rect;
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
import com.minglei.jread.fragments.interfaces.MyItemClickListener;
import com.minglei.jread.presenter.ZhihuDailyPresenter;
import com.minglei.jread.utils.NetworkUtils;
import com.minglei.jread.utils.ToastUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnMultiPurposeListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZhihuDailyFragment extends BaseFragment implements IZhihuDailyView, MyItemClickListener{

    private ZhihuDailyPresenter mPresenter;
    private View mRootView;
    private View mErrorView;
    private RecyclerView mRecyclerView;
    private ZhihuDailyListAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private SmartRefreshLayout mRefreshLayout;

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
    public void onResume() {
        super.onResume();
        if (!NetworkUtils.isNetworkAvailable()) {
            mErrorView.setVisibility(View.VISIBLE);
            mRefreshLayout.setVisibility(View.GONE);
        } else {
            mErrorView.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_zhihu_daily, container, false);
        mErrorView = mRootView.findViewById(R.id.error_layout);
        mRefreshLayout = (SmartRefreshLayout) mRootView.findViewById(R.id.refreshLayout);

        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recycler_zhihu_daily);
        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new ZhihuDailyListAdapter(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new SimplePaddingDecoration(getActivity()));
        mAdapter.setOnItemClickListener(this);

        mRefreshLayout.setEnableAutoLoadmore(false);
        mRefreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        mRefreshLayout.setRefreshFooter(new ClassicsFooter(getActivity()).setSpinnerStyle(SpinnerStyle.Scale));

        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.getDailyNews();
                mRefreshLayout.finishRefresh();
            }
        });

        mPresenter.getDailyNews();

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

    @Override
    public void onItemClick(View view, int postion) {
        ToastUtil.forceToShowToastInCenter("onclick!!!!!!!!!!!");
        mPresenter.viewNews(mAdapter.getItem(postion));
    }

    private class SimplePaddingDecoration extends RecyclerView.ItemDecoration {

        private int dividerHeight;


        public SimplePaddingDecoration(Context context) {
            dividerHeight = context.getResources().getDimensionPixelSize(R.dimen.divider_height);
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.bottom = dividerHeight;//类似加了一个bottom padding
        }
    }
}
