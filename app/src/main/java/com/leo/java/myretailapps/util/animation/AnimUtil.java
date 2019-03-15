package com.leo.java.myretailapps.util.animation;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.animation.BounceInterpolator;

import com.leo.java.myretailapps.util.Util;

public class AnimUtil {
    //设立静态变量
    @SuppressLint("StaticFieldLeak")
    private static AnimUtil mySingleton = null;
    private Context context;

    private AnimUtil(Context context) {
        this.context = context.getApplicationContext();
    }

    //开放一个公有方法，判断是否已经存在实例，有返回，没有新建一个在返回
    public static AnimUtil getInstance(Context context) {
        if (mySingleton == null) {
            synchronized (AnimUtil.class) {
                if (mySingleton == null) {
                    mySingleton = new AnimUtil(context);
                }
            }
        }
        return mySingleton;
    }

    public void RotationAnim(View v) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(v, "rotation", 0, 360);
        animator.setInterpolator(new BounceInterpolator());
        animator.setStartDelay(2000);
        animator.setDuration(2000);
        animator.start();
    }

}
