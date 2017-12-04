package com.minglei.jread.utils;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.minglei.jread.R;

/**
 * Created by minglei on 2017/12/1.
 */

public class FragmentUtil {

    public static void replaceFragment(@NonNull FragmentManager manager, @IdRes int layoutId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out);
        fragmentTransaction.replace(layoutId, fragment);
        fragmentTransaction.commitAllowingStateLoss();
    }
}
