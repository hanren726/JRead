package com.minglei.jread.base;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.HorizontalScrollView;

import java.lang.reflect.Field;

/**
 * Created by minglei on 2017/11/4.
 */

public class JCustomViewPager extends ViewPager {

    private boolean mIsCanScroll = true;

    public JCustomViewPager(Context context) {
        super(context);
    }

    public JCustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(null);

    }
    public void setCanScroll(boolean isCanScroll) {
        mIsCanScroll = isCanScroll;
        if (!isCanScroll) {
            setMyScroller();
        }
    }

    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        if(mIsCanScroll) {
            return super.onTouchEvent(arg0);
        } else {
            return false;
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(mIsCanScroll) {
            return super.onInterceptTouchEvent(ev);
        } else {
            return false;
        }
    }

    private void setMyScroller() {
        try {
            Class<?> viewpager = ViewPager.class;
            Field scroller = viewpager.getDeclaredField("mScroller");
            scroller.setAccessible(true);
            scroller.set(this, new MyScroller(getContext()));
        } catch (Exception e) {
            //TODO 自定义log类
        }
    }

    public class MyScroller extends android.widget.Scroller {
        public MyScroller(Context context) {
            super(context, new DecelerateInterpolator());
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            super.startScroll(startX, startY, dx, dy, 0);
        }
    }
    @Override
    protected boolean canScroll(View v, boolean checkV, int dx, int x, int y) {
        if (v instanceof HorizontalScrollView) {
            return true;
        }
        return super.canScroll(v, checkV, dx, x, y);
    }
}
