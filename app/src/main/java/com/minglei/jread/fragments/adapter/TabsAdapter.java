package com.minglei.jread.fragments.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by minglei on 2017/11/8.
 */

public class TabsAdapter extends FragmentPagerAdapter {

    public static final String TAG = TabsAdapter.class.getSimpleName();

    private Fragment mFragment = null;
    private List<Fragment> mFragments = new ArrayList<Fragment>();
    private final ArrayList<TabInfo> mTabs = new ArrayList<>();

    final class TabInfo {
        private final Class<?> aClass;
        private final String title;
        private final Bundle args;

        public TabInfo(Class<?> aClass, String title, Bundle args) {
            this.aClass = aClass;
            this.title = title;
            this.args = args;
        }
    }

    public TabsAdapter(Fragment fragment) {
        super(fragment.getChildFragmentManager());
        mFragment = fragment;
    }

    public void setData(ArrayList<Fragment> fragments) {
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mTabs.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    public void addTab(Class<?> clss, String title, Bundle args) {
        TabInfo tabInfo = new TabInfo(clss, title, args);
        mTabs.add(tabInfo);
        notifyDataSetChanged();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        TabInfo tabInfo = mTabs.get(position);
        return tabInfo.title;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}
