package com.minglei.jread.fragments.basefragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.minglei.jread.R;
import com.minglei.jread.base.BaseFragment;

public class NoDataFragment extends BaseFragment {

    public static NoDataFragment newInstance(String pageName) {
        NoDataFragment fragment = new NoDataFragment();
        Bundle args = new Bundle();
        args.putString("pageName", pageName);
        fragment.setArguments(args);
        return fragment;
    }

    private ImageView mImage;
    private TextView mText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_no_data, container, false);
        mImage = (ImageView) view.findViewById(R.id.frag_no_data_image);
        mText = (TextView) view.findViewById(R.id.frag_no_data_text);
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
        String pageName = args.getString("pageName");
    }
}
