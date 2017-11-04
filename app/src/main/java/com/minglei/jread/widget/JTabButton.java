package com.minglei.jread.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.minglei.jread.JApplication;
import com.minglei.jread.R;
import com.minglei.jread.utils.ScreenSizeUtil;

/**
 * Created by minglei on 2017/11/4.
 */

public class JTabButton extends RelativeLayout {

    public ImageView mainImage;
    int middleGap = (int)(16*(getResources().getDisplayMetrics().density));
    private final static int TAB_BUTTON_SUM = 3;

    public JTabButton(Context context) {
        super(context);
    }

    public JTabButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public JTabButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public JTabButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public static JTabButton ZhihuTabBtn(ViewGroup tabBar){
        JTabButton btn = JTabButton.buttonStyleVerticalImageLabel(tabBar);
        int width = (ScreenSizeUtil.getScreenSize().widthPixels - btn.middleGap) / TAB_BUTTON_SUM;
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, LinearLayout.LayoutParams.MATCH_PARENT);
        btn.mainImage.setImageDrawable(JApplication.getAppContext().getDrawable(R.drawable.v6_common_tabbar_dialer));
        tabBar.addView(btn, lp);
        return btn;
    }

    public static JTabButton GuokeTabBtn(ViewGroup tabBar){
        JTabButton btn = JTabButton.buttonStyleVerticalImageLabel(tabBar);
        int width = (ScreenSizeUtil.getScreenSize().widthPixels - btn.middleGap) / TAB_BUTTON_SUM;
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, LinearLayout.LayoutParams.MATCH_PARENT);
        btn.mainImage.setImageDrawable(JApplication.getAppContext().getDrawable(R.drawable.v6_common_tabbar_dialer));
        tabBar.addView(btn, lp);
        return btn;
    }

    public static JTabButton QiubaiTabBtn(ViewGroup tabBar){
        JTabButton btn = JTabButton.buttonStyleVerticalImageLabel(tabBar);
        int width = (ScreenSizeUtil.getScreenSize().widthPixels - btn.middleGap) / TAB_BUTTON_SUM;
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, LinearLayout.LayoutParams.MATCH_PARENT);
        btn.mainImage.setImageDrawable(JApplication.getAppContext().getDrawable(R.drawable.v6_common_tabbar_dialer));
        tabBar.addView(btn, lp);
        return btn;
    }

    public final static JTabButton buttonStyleVerticalImageLabel(ViewGroup parent){
        JTabButton ret = (JTabButton) LayoutInflater.from(JApplication.getAppContext()).inflate(R.layout.layout_tab_bar_btn, null);
        return ret;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mainImage = (ImageView) findViewById(R.id.tpd_tab_btn_image);
    }
}

