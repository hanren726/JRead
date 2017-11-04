package com.minglei.jread.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.minglei.jread.JMainActivity;
import com.minglei.jread.R;

/**
 * Created by minglei on 2017/11/4.
 */

public class LeftDrawer extends RelativeLayout {

    private Activity mParentActivity;

    public LeftDrawer(Context context) {
        this(context, null);
    }

    public LeftDrawer(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LeftDrawer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.layout_left_drawer, this);
    }

    public void setParentActivity(JMainActivity parentActivity) {
        mParentActivity = parentActivity;
    }

}
