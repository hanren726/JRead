package com.minglei.jread.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.minglei.jread.R;
import com.minglei.jread.utils.DimentionUtil;
import com.minglei.jread.utils.ScreenSizeUtil;

/**
 * Created by minglei on 2017/11/8.
 */

public class TopTabsView extends RelativeLayout {

    public static final String TAG = TopTabsView.class.getSimpleName();

    private LinearLayout mTabsContainer;
    private View mIndicator;
    private TabTitleView mTabTitleView;

    private int mTabItemWidth;

    private int mIndicatorColor = getResources().getColor(R.color.light_blue_500);
    private int mNotSelectedColor = getResources().getColor(R.color.grey_900);
    private int mSelectedColor = getResources().getColor(R.color.light_blue_500);

    private onTabsItemClickListener listener;

    public TopTabsView(Context context) {
        this(context, null);
    }

    public TopTabsView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TopTabsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //初始化容器
        mTabsContainer = new LinearLayout(getContext());
        mTabsContainer.setOrientation(LinearLayout.HORIZONTAL);
        mTabsContainer.setGravity(Gravity.CENTER);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.addRule(CENTER_IN_PARENT, TRUE);
        addView(mTabsContainer, layoutParams);

        //初始化指示器
        mIndicator = new View(getContext());
        mIndicator.setBackgroundColor(mIndicatorColor);
        RelativeLayout.LayoutParams indicatorLayoutParams = new RelativeLayout.LayoutParams(DimentionUtil.getDimen(R.dimen.top_tab_indicator_width), DimentionUtil.getDimen(R.dimen.top_tab_indicator_height));
        indicatorLayoutParams.addRule(ALIGN_PARENT_BOTTOM, TRUE);
        addView(mIndicator, indicatorLayoutParams);

        mTabItemWidth = DimentionUtil.getDimen(R.dimen.top_tab_title_width);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    public void setRedDotVisible(int position) {
        int childCount = mTabsContainer.getChildCount();
        if (position < 0 || position >= childCount) {
            return;
        }
        for (int i=0; i<childCount; i++) {
            TextView childRedDotView = (TextView) mTabsContainer.getChildAt(i).findViewById(R.id.reddot);
            if (i == position) {
                childRedDotView.setVisibility(VISIBLE);
            } else {
                childRedDotView.setVisibility(GONE);
            }
        }

    }

    /**
     * 设置选项卡
     * @param titles
     */
    public void setTabs(String... titles) {
        mTabsContainer.removeAllViews();
        if (titles == null) {
            return;
        }
        final int count = titles.length;
        int textSize = 18;
        if (count > 3) {
            if (ScreenSizeUtil.getScreenSize().widthPixels < 600) {
                mTabItemWidth = mTabItemWidth * 11 / 14;
                textSize = 16;
            } else {
                mTabItemWidth = mTabItemWidth * 13 / 14;
            }
        }

        for (int index=0; index<titles.length; index++) {

            mTabTitleView = new TabTitleView(getContext());
            mTabTitleView.setTag(index);
            TextView title = (TextView) mTabTitleView.findViewById(R.id.title);
            title.setText(titles[index]);
            title.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
            mTabTitleView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = (int) view.getTag();
                    setCurrentTab(pos, true);
                    if (listener != null) {
                        listener.onClick(view, pos);
                    }
                }
            });

            LinearLayout.LayoutParams containerLayout = new LinearLayout.LayoutParams(mTabItemWidth, LayoutParams.WRAP_CONTENT);
            containerLayout.gravity = Gravity.CENTER_VERTICAL;
            mTabsContainer.addView(mTabTitleView, containerLayout);
        }
    }

    public void setCurrentTab(int position, boolean anim) {
        int childCount = mTabsContainer.getChildCount();
        if (position < 0 || position >= childCount) {
            return;
        }
        for (int i=0; i<childCount; i++) {
            TextView childTitleView = (TextView) mTabsContainer.getChildAt(i).findViewById(R.id.title);
            TextView childRedDotView = (TextView) mTabsContainer.getChildAt(i).findViewById(R.id.reddot);
            if (i == position) {
                childTitleView.setTextColor(mSelectedColor);
                childRedDotView.setVisibility(GONE);
            } else {
                childTitleView.setTextColor(mNotSelectedColor);
            }
        }

        int tabWidth = mTabItemWidth;
        int indicatorWidth = DimentionUtil.getDimen(R.dimen.top_tab_indicator_width);
        int distance = ScreenSizeUtil.getScreenSize().widthPixels / 2
                - (int) ((childCount / 2f) * tabWidth)
                + position * tabWidth
                + (tabWidth - indicatorWidth) / 2;
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mIndicator, "x", distance);
        if (anim) {
            objectAnimator.setDuration(200).start();
        } else {
            objectAnimator.setDuration(0).start();
        }
    }

    /**
     * Tabs点击的监听事件
     */
    public interface onTabsItemClickListener {
        void onClick(View view, int position);
    }

    public void setOnTabsItemClickListener(onTabsItemClickListener listener) {
        this.listener = listener;
    }

}
