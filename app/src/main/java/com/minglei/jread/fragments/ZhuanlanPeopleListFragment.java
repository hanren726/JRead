package com.minglei.jread.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.minglei.jread.R;
import com.minglei.jread.base.BaseFragment;
import com.minglei.jread.beans.zhihu.zhuanlan.User;
import com.minglei.jread.beans.zhihu.zhuanlan.UserEntity;
import com.minglei.jread.fragments.adapter.PeopleListAdapter;
import com.minglei.jread.net.ApiFactory;
import com.minglei.jread.utils.DataCenter;
import com.minglei.jread.utils.DateUtil;
import com.minglei.jread.utils.JLog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by minglei on 2017/12/1.
 */

public class ZhuanlanPeopleListFragment extends BaseFragment {

    private static final String TAG = ZhuanlanPeopleListFragment.class.getSimpleName();

    private PeopleListAdapter mAdapter;

    public static ZhuanlanPeopleListFragment newInstance() {
        ZhuanlanPeopleListFragment fragment = new ZhuanlanPeopleListFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        JLog.i(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.zhuanlan_people_list, container, false);
        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.peoplelist_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new PeopleListAdapter();
        mRecyclerView.setAdapter(mAdapter);
        DataCenter.instance().init(getActivity());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getUserIdList();
    }

    private void getUserIdList() {
        Observable.from(getActivity().getResources().getStringArray(R.array.people_ids))
                .flatMap(new Func1<String, Observable<User>>() {
                    @Override
                    public Observable<User> call(String s) {
//                        return Observable.concat(getUserFromMemory(s), getUserFromNetwork(s)).first();
                        return getUserFromNetwork(s);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<User>() {

                    @Override
                    public void onCompleted() {
                        JLog.i(TAG, "getUserIdList onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        JLog.i(TAG, "getUserIdList onError : [%s]", e);
                    }

                    @Override
                    public void onNext(User user) {
                        JLog.i(TAG, "getUserIdList user : [%s]", user);
                        UserEntity userEntity = user.toUserEntity();
                        JLog.i(TAG, "getUserIdList userEntity : [%s]", userEntity);
                        mAdapter.add(userEntity);
                    }
                });
    }

    private Observable getUserFromNetwork(String id) {
        return ApiFactory.getZhihuZhuanlanApi().getUser(id);
    }

    private Observable getUserFromMemory(final String arg) {
        return Observable.just(DataCenter.instance().query(arg));
    }
}
