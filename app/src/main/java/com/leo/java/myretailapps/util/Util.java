package com.leo.java.myretailapps.util;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Toast;

import com.leo.java.myretailapps.MainHallActivity;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class Util {
    //设立静态变量
    private static Util mySingleton = null;
    private Context context;

    private Util(Context context) {
        this.context = context.getApplicationContext();
    }

    //开放一个公有方法，判断是否已经存在实例，有返回，没有新建一个在返回
    public static Util getInstance(Context context) {
        if (mySingleton == null) {
            synchronized (Util.class) {
                if (mySingleton == null) {
                    mySingleton = new Util(context);
                }
            }
        }
        return mySingleton;
    }

    private String judgeProvider(LocationManager locationManager) {
        List<String> prodiverlist = locationManager.getProviders(true);
        if (prodiverlist.contains(LocationManager.NETWORK_PROVIDER)) {
            return LocationManager.NETWORK_PROVIDER;//网络定位
        } else if (prodiverlist.contains(LocationManager.GPS_PROVIDER)) {
            return LocationManager.GPS_PROVIDER;//GPS定位
        }
        return null;
    }

    /**
     * 獲取經緯度
     *
     * @param lm LocationManager
     * @return Location
     */
    public Location beginLocatioon(LocationManager lm) {
        //获得位置服务
        String provider = judgeProvider(lm);
        //有位置提供器的情况
        if (provider != null) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return null;
            }
            return lm.getLastKnownLocation(provider);
        }
        return null;
    }

    /**
     * 获取权限
     *
     * @param activity activity
     */
    public void showPermission(Activity activity) {
        RxPermissions rxPermissions = new RxPermissions(activity);
        rxPermissions.requestEach(Manifest.permission.ACCESS_FINE_LOCATION)
                .subscribe(new Observer<Permission>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Permission permission) {
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 复制内容到剪贴板
     * @param v View
     */
    public void CopyToClip(View v,String text) {
        ClipboardManager cm;
        ClipData mClipData;
        //获取剪贴板管理器：
        cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        // 创建普通字符型ClipData
        mClipData = ClipData.newPlainText("Label", text);
        // 将ClipData内容放到系统剪贴板里。
        if (cm != null) {
            cm.setPrimaryClip(mClipData);
            Snackbar.make(v, "复制成功", Snackbar.LENGTH_LONG).show();
        }
    }

    /**
     * 跳轉微信
     *
     * @param v
     */
    public void jumpWX(View v) {
        try {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            ComponentName cmp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setComponent(cmp);
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            // TODO: handle exception
            Snackbar.make(v, "检查到您手机没有安装微信，请安装后使用该功能", Snackbar.LENGTH_LONG).show();
        }
    }
}
