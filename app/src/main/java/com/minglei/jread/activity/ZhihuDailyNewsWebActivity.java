package com.minglei.jread.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.minglei.jread.JApplication;
import com.minglei.jread.R;
import com.minglei.jread.fragments.interfaces.IZhihuDailyWebView;
import com.minglei.jread.presenter.ZhihuDailyWebPresenter;

public class ZhihuDailyNewsWebActivity extends AppCompatActivity implements IZhihuDailyWebView {

    public static final String TAG = ZhihuDailyNewsWebActivity.class.getSimpleName();
    public static final String BUNDLE_ID = "bundle_id";

    private ImageView mWebImage;
    private TextView mMainTitle;
    private TextView mSubTitle;
    private WebView mWebView;
    private ZhihuDailyWebPresenter mZhihuDailyWebPresenter;
    private Toolbar mToolbar;

    private int mId;

    public static Intent getStartIntent() {
        return new Intent(JApplication.getAppContext(), ZhihuDailyNewsWebActivity.class);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mZhihuDailyWebPresenter.onUnInit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_web);

        mWebImage = (ImageView) findViewById(R.id.iv_web_img);
        mMainTitle = (TextView) findViewById(R.id.tv_img_title);
        mSubTitle = (TextView) findViewById(R.id.tv_img_source);
        mWebView = (WebView) findViewById(R.id.web_view);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
//        mToolbar.setNavigationIcon(R.drawable.webactivity_back);
//        setSupportActionBar(mToolbar);

        processIntent();

        mZhihuDailyWebPresenter = new ZhihuDailyWebPresenter(this, this);
        mZhihuDailyWebPresenter.getZhihuNewsContent(mId);
    }

    private void processIntent() {
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        mId = intent.getIntExtra(BUNDLE_ID, 0);
        Log.i(TAG, String.format("mId : [%d]" , mId));
    }

    @Override
    public ImageView getWebImage() {
        return mWebImage;
    }

    @Override
    public TextView getMainTitle() {
        return mMainTitle;
    }

    @Override
    public TextView getSubTitle() {
        return mSubTitle;
    }

    @Override
    public WebView getWebView() {
        return mWebView;
    }

    @Override
    public Toolbar getToolbar() {
        return mToolbar;
    }

}
