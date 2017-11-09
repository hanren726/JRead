package com.minglei.jread.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.minglei.jread.R;
import com.minglei.jread.base.JCustomViewPager;
import com.minglei.jread.base.JTabFragment;
import com.minglei.jread.fragments.adapter.TabsAdapter;
import com.minglei.jread.widget.TopTabsView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ZhihuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ZhihuFragment extends JTabFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private View mRootView;
    private JCustomViewPager mCustomViewPager;
    private TopTabsView mTopTabs;
    private ArrayList mFragments = new ArrayList<Fragment>();
    private TabsAdapter mAdapter;

    public ZhihuFragment() {
    }

    public static ZhihuFragment newInstance(String param1, String param2) {
        ZhihuFragment fragment = new ZhihuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_zhihu, container, false);

        ZhihuDailyFragment zhihuDailyFragment = new ZhihuDailyFragment();
        ZhihuProfFragment zhihuProfFragment = new ZhihuProfFragment();
        mFragments.add(zhihuDailyFragment);
        mFragments.add(zhihuProfFragment);

        mAdapter = new TabsAdapter(this);
        mAdapter.setData(mFragments);
        mAdapter.addTab(ZhihuDailyFragment.class, "知乎日报", null);
        mAdapter.addTab(ZhihuProfFragment.class, "知乎专栏", null);

        mCustomViewPager = mRootView.findViewById(R.id.viewpager);
        mCustomViewPager.setCanScroll(true);
        mCustomViewPager.addOnPageChangeListener(mViewPageChangeListener);
        mCustomViewPager.setAdapter(mAdapter);

        mTopTabs = mRootView.findViewById(R.id.custom_indicator);
        mTopTabs.setTabs("知乎日报", "知乎专栏");
        mTopTabs.setOnTabsItemClickListener(new TopTabsView.onTabsItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                mCustomViewPager.setCurrentItem(position);
            }
        });
        mCustomViewPager.setCurrentItem(0);
        mTopTabs.setCurrentTab(0,false);
        //mTopTabs.setRedDotVisible(0); //控制小红点显示
        return mRootView;

    }

    private ViewPager.OnPageChangeListener mViewPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            mTopTabs.setCurrentTab(position, true);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

}
