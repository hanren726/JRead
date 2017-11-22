package com.minglei.jread.fragments.interfaces;

import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by minglei on 2017/11/22.
 */

public interface IZhihuDailyWebView {

    ImageView getWebImage();

    TextView getMainTitle();

    TextView getSubTitle();

    WebView getWebView();

    Toolbar getToolbar();
}
