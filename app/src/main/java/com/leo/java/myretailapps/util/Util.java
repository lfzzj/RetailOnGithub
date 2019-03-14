package com.leo.java.myretailapps.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
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
     * @param activity activity
     */
    public void showPermission(Activity activity){
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
}
