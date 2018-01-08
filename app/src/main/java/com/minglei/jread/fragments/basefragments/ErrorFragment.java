package com.minglei.jread.fragments.basefragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.minglei.jread.R;
import com.minglei.jread.base.BaseFragment;
import com.minglei.jread.pages.RetryListener;

/**
 * Created by wangxinarhat on 17/7/25.
 */

public class ErrorFragment extends BaseFragment implements View.OnClickListener {

    private RetryListener mListener;
    private String mPageName;

    public static ErrorFragment newInstance(String pageName) {
        return newInstance(pageName, null);
    }

    public static ErrorFragment newInstance(String pageName, RetryListener listener) {
        ErrorFragment fragment = new ErrorFragment();
        fragment.mListener = listener;
        Bundle args = new Bundle();
        args.putString("pageName", pageName);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_error, container, false);
        view.findViewById(R.id.frag_error_retry).setOnClickListener(this);
        view.findViewById(R.id.frag_error_network_setting).setOnClickListener(this);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getView() != null) {
            getView().setBackgroundColor(getResources().getColor(android.R.color.transparent));
        }
        Bundle args = getArguments();
        if (args == null) {
            return;
        }
        mPageName = args.getString("pageName");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.frag_error_retry:
                retry();
                break;
            case R.id.frag_error_network_setting:
                gotoSetting();
                break;
        }
    }

    public void setRetryListener(RetryListener listener) {
        this.mListener = listener;
    }

    private void retry() {
        if (mListener != null) {
            mListener.retry();
        }
    }

    private void gotoSetting() {
        Intent intent = new Intent(android.provider.Settings.ACTION_SETTINGS);
        startActivity(intent);
    }
}
