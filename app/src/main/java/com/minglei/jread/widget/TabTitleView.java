package com.minglei.jread.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.minglei.jread.R;

/**
 * Created by minglei on 2017/11/8.
 */

public class TabTitleView extends RelativeLayout {
    public TabTitleView(Context context) {
        this(context, null);
    }

    public TabTitleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(getContext()).inflate(R.layout.view_tab_title,this);
    }
}
