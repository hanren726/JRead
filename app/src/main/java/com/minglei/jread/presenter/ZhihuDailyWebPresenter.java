package com.minglei.jread.presenter;

import android.content.Context;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.minglei.jread.beans.zhihu.ZhihuDailyContentBean;
import com.minglei.jread.fragments.interfaces.IZhihuDailyWebView;
import com.minglei.jread.net.ApiFactory;
import com.minglei.jread.net.ZhihuDailyApi;
import com.minglei.jread.presenter.interfaces.IZhihuDailyWebPresenter;

import okhttp3.TlsVersion;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by minglei on 2017/11/22.
 */

public class ZhihuDailyWebPresenter implements IZhihuDailyWebPresenter{

    public static final String TAG = ZhihuDailyWebPresenter.class.getSimpleName();

    private IZhihuDailyWebView mIZhihuDailyWebView;
    private Context mContext;

    private CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    public static final ZhihuDailyApi mZhihuApi = ApiFactory.getZhihuDailyApi();

    public ZhihuDailyWebPresenter(Context context, IZhihuDailyWebView iZhihuDailyWebView) {
        this.mContext = context;
        this.mIZhihuDailyWebView = iZhihuDailyWebView;
    }

    @Override
    public void getZhihuNewsContent(int id) {
        Subscription subscription = mZhihuApi.getDetailNews(id)
                .filter(new Func1<ZhihuDailyContentBean, Boolean>() {
                    @Override
                    public Boolean call(ZhihuDailyContentBean zhihuDailyContentBean) {
                        return zhihuDailyContentBean != null;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ZhihuDailyContentBean>() {
                    @Override
                    public void onCompleted() {
                        Log.e(TAG, "getZhihuNewsContent onCompleted:");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "getZhihuNewsContent onError:");
                    }

                    @Override
                    public void onNext(ZhihuDailyContentBean zhihuDailyContentBean) {
                        setWebView(zhihuDailyContentBean);
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    private void setWebView(ZhihuDailyContentBean news){
        WebView webView = mIZhihuDailyWebView.getWebView();
        ImageView webImg = mIZhihuDailyWebView.getWebImage();
        TextView imgTitle = mIZhihuDailyWebView.getMainTitle();
        TextView imgSource = mIZhihuDailyWebView.getSubTitle();

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        String head = "<head>\n" +
                "\t<link rel=\"stylesheet\" href=\""+news.getCss().get(0)+"\"/>\n" +
                "</head>";
        String img = "<div class=\"headline\">";
        String html =head + news.getBody().replace(img," ");
        webView.loadDataWithBaseURL(null,html,"text/html","utf-8",null);
        Glide.with(mContext).load(news.getImage()).centerCrop().into(webImg);
//
        imgTitle.setText(news.getTitle());
        imgSource.setText(news.getImage_source());
    }

    public void onUnInit() {
        Log.i(TAG, "onUnInit :");
        this.mCompositeSubscription.clear();
    }
}
