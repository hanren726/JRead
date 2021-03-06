package com.minglei.jread.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

public class BaseFragment extends Fragment {

    protected final String TAG = this.getClass().getSimpleName();
    protected Activity mActivity;

    public BaseFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        this.mActivity = activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
