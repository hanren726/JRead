package com.minglei.jread.fragments;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.minglei.jread.R;
import com.minglei.jread.base.BaseFragment;
import com.minglei.jread.base.BaseViewHolder;
import com.minglei.jread.base.GroupedRecyclerViewAdapter;
import com.minglei.jread.fragments.adapter.ZhihudailyAdapter;
import com.minglei.jread.fragments.interfaces.IZhihuDailyView;
import com.minglei.jread.presenter.ZhihuDailyPresenter;
import com.minglei.jread.utils.DateUtil;
import com.minglei.jread.utils.JLog;
import com.minglei.jread.utils.NetworkUtils;
import com.minglei.jread.utils.ToastUtil;
import com.minglei.jread.widget.ChooseDateView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wdullaer.materialdatetimepicker.date.DatePickerController;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;
import java.util.Date;


public class ZhihuDailyFragment extends BaseFragment implements IZhihuDailyView,
        GroupedRecyclerViewAdapter.OnChildClickListener,
        View.OnClickListener,
        DatePickerDialog.OnDateSetListener{

    private static final String TAG = ZhihuDailyFragment.class.getSimpleName();

    private ZhihuDailyPresenter mPresenter;
    private View mRootView;
    private View mErrorView;
    private ChooseDateView mChooseDateView;
    private RecyclerView mRecyclerView;
    private ZhihudailyAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private SmartRefreshLayout mRefreshLayout;
    private DatePickerDialog dpd;

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
        checkNetwork();
    }

    private void checkNetwork() {
        if (!NetworkUtils.isNetworkAvailable()) {
            mErrorView.setVisibility(View.VISIBLE);
            mRefreshLayout.setVisibility(View.GONE);
            mChooseDateView.hideView();
        } else {
            mErrorView.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            mChooseDateView.showView();
            mPresenter.getDailyNews();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_zhihu_daily, container, false);
        mErrorView = mRootView.findViewById(R.id.error_layout);
        TextView retry = (TextView) mErrorView.findViewById(R.id.error_retry);
        TextView setting = (TextView) mErrorView.findViewById(R.id.error_network_setting);
        retry.setOnClickListener(this);
        setting.setOnClickListener(this);
        mRefreshLayout = (SmartRefreshLayout) mRootView.findViewById(R.id.refreshLayout);
        mChooseDateView = new ChooseDateView(getActivity());
        mChooseDateView.addView(getActivity());
        mChooseDateView.setId(R.id.zhihu_choose_date);
        mChooseDateView.setOnClickListener(this);

        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recycler_zhihu_daily);
        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new ZhihudailyAdapter(getActivity());
        mAdapter.setOnChildClickListener(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new SimplePaddingDecoration(getActivity()));
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                JLog.i(TAG, "onScrollStateChanged newState : [%d]", newState);
                //静止,没有滚动 SCROLL_STATE_IDLE = 0;
                //正在被外部拖拽,一般为用户正在用手指滚动 SCROLL_STATE_DRAGGING = 1;
                //自动滚动开始 SCROLL_STATE_SETTLING = 2;
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    mChooseDateView.showView();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                JLog.i(TAG, "onScrolled dx : [%d], dy : [%d]", dx, dy);
                //dx : 水平滚动距离 dy : 垂直滚动距离
                if (dy == 0) {
                    mChooseDateView.showView();
                } else {
                    mChooseDateView.hideView();
                }
            }
        });

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
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPresenter.getBeforeNews();
                mRefreshLayout.finishLoadmore();
            }
        });
        return mRootView;
    }

    @Override
    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    @Override
    public ZhihudailyAdapter getAdapter() {
        return mAdapter;
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
    public void onChildClick(GroupedRecyclerViewAdapter adapter, BaseViewHolder holder, int groupPosition, int childPosition) {
        JLog.i(TAG, "onChildClick groupPosition : [%d],childPosition : [%d]", groupPosition, childPosition);
        mPresenter.viewNews(mAdapter.getChildItem(groupPosition, childPosition));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zhihu_choose_date:
                showChooseDateDialog();
                break;
            case R.id.error_retry:
                checkNetwork();
                break;
            case R.id.error_network_setting:
                Intent intent = new Intent(android.provider.Settings.ACTION_SETTINGS);
                startActivity(intent);
                break;
        }

    }

    private void showChooseDateDialog() {
        Calendar now = Calendar.getInstance();
        if (dpd == null) {
            dpd = DatePickerDialog.newInstance(
                    ZhihuDailyFragment.this,
                    now.get(Calendar.YEAR),
                    now.get(Calendar.MONTH),
                    now.get(Calendar.DAY_OF_MONTH)
            );
        } else {
            dpd.initialize(
                    ZhihuDailyFragment.this,
                    now.get(Calendar.YEAR),
                    now.get(Calendar.MONTH),
                    now.get(Calendar.DAY_OF_MONTH)
            );
        }
        dpd.vibrate(true);
        dpd.dismissOnPause(true);
        dpd.setAccentColor(getResources().getColor(R.color.light_blue_500));
        dpd.setTitle(getResources().getString(R.string.zhihu_daily_choose_date_title));
        dpd.show(getActivity().getFragmentManager(), "Datepickerdialog");
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        //TODO fix me 30号和31号的处理
        String date = DateUtil.createFormatDate(year, monthOfYear, dayOfMonth);
        Calendar now = Calendar.getInstance();
        int thisYear = now.get(Calendar.YEAR);
        int thisMonth = now.get(Calendar.MONTH);
        int thisDay = now.get(Calendar.DAY_OF_MONTH);
        String today = DateUtil.createFormatDate(thisYear, thisMonth, thisDay);
        if (DateUtil.compareDate(date, today)) {
            ToastUtil.forceToShowToastInCenter(getResources().getString(R.string.zhihu_daily_choose_date_hint));
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(year);
            int realMonth = monthOfYear + 1;
            stringBuffer.append(realMonth);
            if (dayOfMonth + 1 < 10) {
                stringBuffer.append("0");
                stringBuffer.append(dayOfMonth + 1);
            } else {
                stringBuffer.append(dayOfMonth + 1);
            }
            mPresenter.getSelectedDayNews(stringBuffer.toString());
        }
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
