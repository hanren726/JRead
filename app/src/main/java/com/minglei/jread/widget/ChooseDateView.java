package com.minglei.jread.widget;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.minglei.jread.R;
import com.minglei.jread.utils.DimentionUtil;

/**
 * Created by minglei on 2017/11/28.
 */

public class ChooseDateView extends FrameLayout {

    private static final String TAG = ChooseDateView.class.getSimpleName();

    public ChooseDateView(@NonNull Context context) {
        this(context, null);
    }

    public ChooseDateView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChooseDateView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.view_choose_date, this);
    }

    public void addView(Activity activity) {
        setVisibility(VISIBLE);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams
                (FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        params.gravity= Gravity.BOTTOM|Gravity.LEFT;
        params.bottomMargin = DimentionUtil.dp2px(110);
        params.leftMargin = DimentionUtil.dp2px(10);
        activity.addContentView(this, params);
    }

    public void showView() {
        ObjectAnimator.ofFloat(this, "alpha", 0.0f, 1.0f)
                .setDuration(300)
                .start();
        setVisibility(VISIBLE);
    }

    public void hideView() {
        setVisibility(INVISIBLE);
    }
}
