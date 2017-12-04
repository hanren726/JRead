package com.minglei.jread.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.minglei.jread.R;
import com.minglei.jread.base.BaseFragment;
import com.minglei.jread.utils.FragmentUtil;
import com.minglei.jread.utils.JLog;
import com.minglei.jread.utils.NetworkUtils;

public class ZhihuProfFragment extends BaseFragment {


    public ZhihuProfFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (NetworkUtils.isNetworkAvailable()) {
            ZhuanlanPeopleListFragment zhuanlanPeopleListFragment = ZhuanlanPeopleListFragment.newInstance();
            FragmentUtil.replaceFragment(getFragmentManager(), R.id.container, zhuanlanPeopleListFragment);
        } else {
            ErrorFragment errorFragment = ErrorFragment.newInstance("error");
            FragmentUtil.replaceFragment(getFragmentManager(), R.id.container, errorFragment);
        }
        return inflater.inflate(R.layout.fragment_zhihu_prof, container, false);
    }

}
