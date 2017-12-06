package com.minglei.jread.utils;

import android.app.Activity;

import com.minglei.jread.JApplication;
import com.minglei.jread.beans.zhihu.zhuanlan.UserEntity;
import com.minglei.jread.beans.zhihu.zhuanlan.UserEntity_;

import io.objectbox.Box;

/**
 * Created by minglei on 2017/12/4.
 */

public class DataCenter {

    public Box box;

    private static DataCenter instance;

    public static DataCenter instance() {
        if (instance == null) {
            synchronized (DataCenter.class) {
                if (instance == null) {
                    instance = new DataCenter();
                }
            }
        }
        return instance;
    }

    public void init(Activity activity) {
        box = ((JApplication) activity.getApplication()).getBoxStore().boxFor(UserEntity.class);
    }

    public void insert(UserEntity userEntity) {
        box.put(userEntity);
    }

    public void delete(long id) {
        box.remove(id);
    }

    public UserEntity query(String arg) {
        UserEntity item = (UserEntity) box.query()
                .equal(UserEntity_.slug, arg)
                .build()
                .findUnique();
        return item;
    }
}
