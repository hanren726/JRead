package com.minglei.jread;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.minglei.jread.base.JCustomViewPager;
import com.minglei.jread.base.JTabFragment;
import com.minglei.jread.fragments.GuokeFragment;
import com.minglei.jread.fragments.QiubaiFragment;
import com.minglei.jread.fragments.ZhihuFragment;
import com.minglei.jread.widget.JTabButton;
import com.minglei.jread.widget.LeftDrawer;

import java.util.ArrayList;
import java.util.List;

public class JMainActivity extends AppCompatActivity {

    private static final String TAG = JMainActivity.class.getSimpleName();

    private LeftDrawer mLeftDrawer;
    private JCustomViewPager mViewPager;
    private RadioGroup mTabBar;
    private List<JTabFragment> mJtabFragments = new ArrayList<>();
    private List<JTabButton> mJtabButton = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jmain);

        initViews();
    }

    private void initViews() {

        mLeftDrawer = (LeftDrawer) findViewById(R.id.menu_layout_left);
        mLeftDrawer.setParentActivity(this);

        View rootView = findViewById(R.id.tab_activity_main_view);
        mViewPager = (JCustomViewPager) rootView.findViewById(R.id.viewpager);
        mViewPager.setCanScroll(false);
        mTabBar = (RadioGroup) findViewById(R.id.tabs_container);

        TabBarInfo f1 = new TabBarInfo();
        ZhihuFragment zhihuFragment = new ZhihuFragment();
        f1.fragment = zhihuFragment;
        f1.btn = JTabButton.ZhihuTabBtn(mTabBar);
        f1.btn.mainImage.setSelected(true);

        TabBarInfo f2 = new TabBarInfo();
        GuokeFragment guokeFragment = new GuokeFragment();
        f2.fragment = guokeFragment;
        f2.btn = JTabButton.DoubanTabBtn(mTabBar);

        TabBarInfo f3 = new TabBarInfo();
        QiubaiFragment qiubaiFragment = new QiubaiFragment();
        f3.fragment = qiubaiFragment;
        f3.btn = JTabButton.GuokeTabBtn(mTabBar);

        TabBarInfo tabinfos[] = {f1, f2, f3};
        addTabs(tabinfos);

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mJtabFragments.get(position);
            }

            @Override
            public int getCount() {
                return mJtabFragments.size();
            }
        });
    }

    private void addTabs(TabBarInfo[] infos) {
        for (int i = 0; i < infos.length; i++) {
            mJtabFragments.add(infos[i].fragment);
            JTabButton btn = infos[i].btn;
            btn.setTag(i);
            btn.setOnClickListener(mSwitchListener);
            mJtabButton.add(btn);
            infos[i].fragment.tabBarButton = btn;
        }
    }

    private class TabBarInfo {
        public JTabFragment fragment;
        public JTabButton btn;
    }

    private View.OnClickListener mSwitchListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int oldIndex = mViewPager.getCurrentItem();
            int index = Integer.parseInt(view.getTag().toString());
            if (index != oldIndex) {
                mViewPager.setCurrentItem(index);
                for (int i = 0; i < mJtabFragments.size(); i++) {
                    JTabButton btn = mJtabButton.get(i);
                    if (i == index) {
                        btn.mainImage.setSelected(true);
                    } else {
                        btn.mainImage.setSelected(false);
                    }
                }
            }
        }
    };
}