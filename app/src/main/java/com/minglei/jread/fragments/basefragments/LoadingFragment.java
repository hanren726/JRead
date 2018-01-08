package com.minglei.jread.fragments.basefragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.minglei.jread.R;
import com.minglei.jread.base.BaseFragment;
import com.minglei.jread.utils.JLog;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;


/**
 * Created by wangxinarhat on 17/7/25.
 */

public class LoadingFragment extends BaseFragment {

    private GifImageView mGif;
    private TextView mText;

    public static LoadingFragment newInstance(String pageName) {
        LoadingFragment fragment = new LoadingFragment();
        Bundle args = new Bundle();
        args.putString("pageName", pageName);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.frag_loading, container, false);
        mGif = (GifImageView) view.findViewById(R.id.frag_loading_gif);
        mText = (TextView) view.findViewById(R.id.frag_loading_text);
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
        try {
            GifDrawable gifDrawable = new GifDrawable(mActivity.getAssets(), "feeds_loading.gif");
            mGif.setImageDrawable(gifDrawable);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        startGifAnimation();
    }

    @Override
    public void onPause() {
        super.onPause();
        stopGifAnimation();
    }

    private void stopGifAnimation() {
        if (mGif.getDrawable() instanceof GifDrawable) {
            GifDrawable gif = (GifDrawable) mGif.getDrawable();
            if (gif.isRunning()) {
                gif.stop();
                gif.seekToFrame(0);
            }
        }
    }

    private void startGifAnimation() {
        if (mGif.getDrawable() instanceof GifDrawable) {
            GifDrawable gif = (GifDrawable) mGif.getDrawable();
            gif.start();
        }
    }

}
