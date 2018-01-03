package com.minglei.jread.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.Pair;
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
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Action2;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.functions.FuncN;
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
                        return Observable.concat(getUserFromMemory(s), getUserFromNetwork(s));
                    }
                })
                .filter(new Func1<User, Boolean>() {
                    @Override
                    public Boolean call(User user) {
                        return user != null;
                    }
                })
                .doOnNext(new Action1<User>() {
                    @Override
                    public void call(User user) {
                        //TODO 存库
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
                        mAdapter.add(userEntity);
                    }
                });
//        String[] ids = getActivity().getResources().getStringArray(R.array.people_ids);
//        batchUser(ids);
    }

    private Observable<User> getUserFromNetwork(String id) {
        return ApiFactory.getZhihuZhuanlanApi().getUser(id);
    }

    private Observable getUserFromMemory(final String arg) {
        return Observable.just(DataCenter.instance().query(arg));
    }

    private Observable<UserEntity> getUserEntityFromNetwork(String id) {
        return ApiFactory.getZhihuZhuanlanApi().getUser(id)
                .filter(new Func1<User, Boolean>() {
                    @Override
                    public Boolean call(User user) {
                        return user != null;
                    }
                })
                .map(new Func1<User, UserEntity>() {
                    @Override
                    public UserEntity call(User user) {
                        JLog.i(TAG, "user is [%s]", user.toUserEntity());

                        return user.toUserEntity();
                    }
                });
    }

    private void batchUser(final String[] ids) {
        Observable.defer(new Func0<Observable<Pair<List<UserEntity>, String[]>>>() {
            @Override
            public Observable<Pair<List<UserEntity>, String[]>> call() {
                List<UserEntity> cached = new ArrayList<>();
                List<String> hitIds = new ArrayList<>();
                for (String id : ids) {
                    UserEntity userEntity = DataCenter.instance().query(id);
                    if (userEntity != null) {
                        cached.add(userEntity);
                        hitIds.add(id);
                    }
                }
                String[] missed = new String[ids.length - cached.size()];
                String[] hited = new String[hitIds.size()];
                for (int i=0; i<hitIds.size(); i++) {
                    hited[i] = hitIds.get(i);
                }
                Arrays.sort(hited);
                int pos = 0;
                for (String id : ids) {
                    if (Arrays.binarySearch(hited, id) < 0) {
                        missed[pos] = id;
                        pos++;
                    }
                }
                return Observable.just(Pair.create(cached, missed));
            }
        }).flatMap(new Func1<Pair<List<UserEntity>, String[]>, Observable<List<UserEntity>>>() {
            @Override
            public Observable<List<UserEntity>> call(Pair<List<UserEntity>, String[]> listPair) {
                Observable<UserEntity> hitObservable = Observable.from(listPair.first);

                final ArrayList<Observable<UserEntity>> Observables = new ArrayList<>();
                for (String id : listPair.second) {

                    Observables.add(getUserEntityFromNetwork(id));
                }

                Observables.add(hitObservable);

                return Observable.zip(Observables, new FuncN<List<UserEntity>>() {
                    @Override
                    public List<UserEntity> call(Object... args) {
                        ArrayList<UserEntity> userEntities = new ArrayList<>();
                        for (Object object : args) {
                            UserEntity a = (UserEntity) object;
                            userEntities.add(a);
                        }
                        return userEntities;
                    }
                });
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<UserEntity>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        JLog.i(TAG, "onError [%s]", e);
                    }

                    @Override
                    public void onNext(List<UserEntity> userEntities) {
                        mAdapter.setData(userEntities);
                    }
                });

    }
}