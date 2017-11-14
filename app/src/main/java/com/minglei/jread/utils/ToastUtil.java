package com.minglei.jread.utils;

import android.support.annotation.StringRes;
import android.view.Gravity;
import android.widget.Toast;

import com.minglei.jread.JApplication;

/**
 * Created by minglei on 2017/11/15.
 */

public class ToastUtil {
    private static Toast sToast;

    public static void showToast(String msg) {
        if (sToast == null) {
            sToast = Toast.makeText(JApplication.getAppContext(), "", Toast.LENGTH_SHORT);
        }
        sToast.setText(msg);
        sToast.setGravity(Gravity.BOTTOM, 0, 0);
        sToast.show();
    }

    public static void forceToShowToastInCenter(@StringRes int msgResId) {
        if (sToast == null) {
            sToast = Toast.makeText(JApplication.getAppContext(), "", Toast.LENGTH_SHORT);
        }
        sToast.setText(JApplication.getAppContext().getString(msgResId));
        sToast.setGravity(Gravity.CENTER, 0, 0);
        sToast.show();
    }

    public static void forceToShowToastInCenter(String msg) {
        if (sToast == null) {
            sToast = Toast.makeText(JApplication.getAppContext(), "", Toast.LENGTH_SHORT);
        }
        sToast.setText(msg);
        sToast.setGravity(Gravity.CENTER, 0, 0);
        sToast.show();
    }
}
