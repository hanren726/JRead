package com.minglei.jread.activity;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.minglei.jread.JApplication;
import com.minglei.jread.R;
import com.minglei.jread.beans.zhihu.zhuanlan.Post;
import com.minglei.jread.beans.zhihu.zhuanlan.UserEntity;
import com.minglei.jread.fragments.adapter.ZhuanlanDetailAdapter;
import com.minglei.jread.fragments.interfaces.RecyclerEndlessScrollListener;
import com.minglei.jread.net.ApiFactory;
import com.minglei.jread.utils.JLog;
import com.minglei.jread.utils.ToastUtil;
import com.minglei.jread.utils.ZhuanlanUtil;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


public class ZhuanlanDetailActivity extends AppCompatActivity {

    public static final String TAG = ZhuanlanDetailActivity.class.getSimpleName();
    private static final String KEY_USER = "_user";

    private static final int DEFAULT_COUNT = 10;

    private UserEntity mUserEntity;

    private RecyclerView mRecyclerView;
    private AppBarLayout mAppBar;
    private LinearLayout mHeaderLayout;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private TextView mNameView;
    private TextView mBidView;
    private TextView mDescriptionView;
    private CircleImageView mAvatar;

    private ZhuanlanDetailAdapter mAdapter;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();

    public static void startActivity(UserEntity userEntity) {
        Intent intent = new Intent(JApplication.getAppContext(), ZhuanlanDetailActivity.class);
        intent.putExtra(KEY_USER, userEntity);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        JApplication.getAppContext().startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuanlan_detail);
        mUserEntity = (UserEntity) getIntent().getSerializableExtra(KEY_USER);
        initViews();
        JLog.i(TAG, "onCreate mUserEntity is [%s]", mUserEntity);
        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(mUserEntity.getZhuanlanName());
        }
        mAdapter = new ZhuanlanDetailAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerView.addOnScrollListener(new RecyclerEndlessScrollListener() {
            @Override
            public void onLoadMore(int page, int var2) {
                requestPostList(page - 1);
            }
        });
        requestPostList(0);
    }

    private void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAppBar = (AppBarLayout) findViewById(R.id.app_bar);
        mHeaderLayout = (LinearLayout) findViewById(R.id.head_layout);
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        mNameView = (TextView) findViewById(R.id.tv_name);
        mBidView = (TextView) findViewById(R.id.tv_bio);
        mDescriptionView = (TextView) findViewById(R.id.tv_description);
        mAvatar = (CircleImageView) findViewById(R.id.iv_avatar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    ZhuanlanDetailActivity.this.finish();
                }
            });
        }

        mAppBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset <= -mHeaderLayout.getHeight() / 2) {
                    mCollapsingToolbarLayout.setTitle(mUserEntity.getZhuanlanName());
                } else {
                    mCollapsingToolbarLayout.setTitle(" ");
                }
            }
        });
        mNameView.setText(mUserEntity.getZhuanlanName());
        mDescriptionView.setText(mUserEntity.getDescription());
        String avatarUrl = ZhuanlanUtil.getAuthorAvatarUrl(mUserEntity.getAvatarTemplate(), mUserEntity.getAvatarId(), ZhuanlanUtil.PIC_SIZE_XL);
        Glide.with(this).load(avatarUrl).centerCrop().into(mAvatar);
    }

    public void requestPostList(final int page) {
        final int offset = page * DEFAULT_COUNT;
        Subscription subscription = ApiFactory.getZhihuZhuanlanApi().getPosts(mUserEntity.getSlug(), DEFAULT_COUNT, offset)
                .filter(new Func1<List<Post>, Boolean>() {
                    @Override
                    public Boolean call(List<Post> posts) {
                        return posts != null;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Post>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        JLog.e(TAG, "requestPostList onError is [%s]", e);
                    }

                    @Override
                    public void onNext(List<Post> posts) {
                        JLog.i(TAG, "requestPostList posts size is [%d]", posts.size());
                        addPosts(posts);
                    }
                });
        mSubscriptions.add(subscription);
    }

    private void addPosts(List<Post> posts) {
        if (posts.size() == 0) {
            ToastUtil.showToast("没有数据了");
        }
        mAdapter.addAllItems(posts);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSubscriptions.clear();
    }
}
