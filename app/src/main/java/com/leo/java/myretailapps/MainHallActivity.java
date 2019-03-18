package com.leo.java.myretailapps;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.leo.java.myretailapps.adapter.MainHallAdapter;
import com.leo.java.myretailapps.adapter.MyPagerAdapter;
import com.leo.java.myretailapps.custom.CubeOutTransformer;
import com.leo.java.myretailapps.custom.FixedSpeedScroller;
import com.leo.java.myretailapps.custom.ParallaxTransformer;
import com.leo.java.myretailapps.custom.TextSwitcherAnimation;
import com.leo.java.myretailapps.model.BannerBean;
import com.leo.java.myretailapps.model.HallGridBean;
import com.leo.java.myretailapps.util.Util;
import com.leo.java.myretailapps.util.animation.AnimUtil;
import com.leo.java.myretailapps.view.ad.AdAc;
import com.leo.java.myretailapps.view.checkpoint.CheckpointAc;
import com.leo.java.myretailapps.view.guild_hall.GuildActivity;
import com.leo.java.myretailapps.view.login.LoginActivity;
import com.leo.java.myretailapps.view.order.OrderAc;
import com.leo.java.myretailapps.view.register.HelpOtherRegisterAc;
import com.leo.java.myretailapps.view.setting.SettingAc;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import interfaces.heweather.com.interfacesmodule.bean.Lang;
import interfaces.heweather.com.interfacesmodule.bean.Unit;
import interfaces.heweather.com.interfacesmodule.bean.weather.now.Now;
import interfaces.heweather.com.interfacesmodule.view.HeWeather;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Name: MainHallActivity
 * Author: Leo
 * Comment: //TODO 首页
 * Date: 2019/3/11 10:29
 * Modify:
 */
public class MainHallActivity extends AppCompatActivity {
    LocationManager lm;
    List<String> texts;
    private Runnable scrollRunnable;
    private MyPagerAdapter myPagerAdapter;
    private List<BannerBean> bannerList;

    @BindView(R.id.hall_grid)
    GridView hallGrid;
    @BindView(R.id.city)
    TextView city;
    @BindView(R.id.weather)
    TextView weather;
    @BindView(R.id.weather_img)
    ImageView weatherImg;
    @BindView(R.id.temp)
    TextView temp;
    @BindView(R.id.main_hall_register_img)
    ImageView mainHallRegisterImg;
    @BindView(R.id.ad_textSwitcher)
    TextSwitcher adTextSwitcher;
    @BindView(R.id.user_level)
    TextView userLevel;
    @BindView(R.id.banner_vp)
    ViewPager bannerVp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_hall);
        ButterKnife.bind(this);

        Toolbar mToolbarTb = findViewById(R.id.tb_toolbar);
        setSupportActionBar(mToolbarTb);
        initPager();
        getLocatioin();
        initUI();
    }

    private void initUI() {
        userLevel.setText(getResources().getString(R.string.vip_13));
        Util.getInstance(this).showPermission(this);
        AnimUtil.getInstance(this).RotationAnim(mainHallRegisterImg);

        adTextSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView t = new TextView(MainHallActivity.this);
                t.setTextColor(getResources().getColor(R.color.color_ffa200));
                t.setTextSize(14f);
                return t;
            }
        });
        texts = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            texts.add("循环....." + i);
        }
        new TextSwitcherAnimation(adTextSwitcher, texts).create();

        initBannerView();
        bannerList = new ArrayList<>();
        BannerBean b = new BannerBean("http://img2.imgtn.bdimg.com/it/u=4162468462,2356552027&fm=214&gp=0.jpg", "https://www.baidu.com");
        bannerList.add(b);
        BannerBean b2 = new BannerBean("http://img2.imgtn.bdimg.com/it/u=4162468462,2356552027&fm=214&gp=0.jpg", "https://www.baidu.com");
        bannerList.add(b2);
        BannerBean b3 = new BannerBean("http://img2.imgtn.bdimg.com/it/u=4162468462,2356552027&fm=214&gp=0.jpg", "https://www.baidu.com");
        bannerList.add(b3);

        myPagerAdapter = new MyPagerAdapter(bannerList, this);
        bannerVp.setAdapter(myPagerAdapter);
        startScroll((1000 * bannerList.size()), true);
    }


    private void initPager() {
        List<HallGridBean> halls = new ArrayList<>();
        TypedArray hall_img = getResources().obtainTypedArray(R.array.hall_grid_img);
        String[] hall_text = getResources().getStringArray(R.array.hall_grid_text);
        for (int i = 0; i < hall_text.length; i++) {
            halls.add(new HallGridBean(hall_img.getResourceId(i, 0), hall_text[i]));
        }
        hall_img.recycle();
        MainHallAdapter hallAdapter = new MainHallAdapter(this, halls);
        hallGrid.setAdapter(hallAdapter);

        hallGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        Intent intent = new Intent(MainHallActivity.this, HelpOtherRegisterAc.class);
                        intent.putExtra("who_register", false);
                        startActivity(intent);
                        break;
                    case 1:
                        startActivity(new Intent(MainHallActivity.this, CheckpointAc.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainHallActivity.this, OrderAc.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainHallActivity.this, GuildActivity.class));
                        break;
                }
            }
        });
    }


    @OnClick({R.id.user_info, R.id.register_now_btn, R.id.main_hall_ad})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.user_info:
                startActivity(new Intent(MainHallActivity.this, SettingAc.class));
                break;
            case R.id.register_now_btn:
                startActivity(new Intent(MainHallActivity.this, LoginActivity.class));
                break;
            case R.id.main_hall_ad:
                startActivity(new Intent(MainHallActivity.this, AdAc.class));
                break;
        }
    }

    /**
     * 定位實現
     */
    private void getLocatioin() {
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        final Location L = Util.getInstance(this).beginLocatioon(lm);
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) {
                String result = "暂无数据";
                try {
                    if (L != null) {
                        Geocoder gc = new Geocoder(MainHallActivity.this, Locale.getDefault());
                        result = gc.getFromLocation(L.getLatitude(),
                                L.getLongitude(), 1).get(0).getLocality();
                        emitter.onNext(result);
                        emitter.onComplete();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    emitter.onNext(result);
                    emitter.onComplete();
                }
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        city.setText(s);
                        initWeather(s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    private void initWeather(String city) {
        if (city == null) {
            return;
        }
        HeWeather.getWeatherNow(this, city, Lang.CHINESE_SIMPLIFIED, Unit.METRIC,
                new HeWeather.OnResultWeatherNowBeanListener() {
                    @Override
                    public void onError(Throwable e) {
                        Log.i("heheLog", "onError: ", e);
                        weather.setText("暂无数据");
                        Glide.with(MainHallActivity.this).load(getResources().getIdentifier("retail_he_999", "mipmap", getPackageName())).into(weatherImg);
                        weatherImg.setColorFilter(getResources().getColor(R.color.white));
                    }

                    @Override
                    public void onSuccess(List<Now> dataObject) {
                        Log.i("heheLog", "onSuccess: " + new Gson().toJson(dataObject));
                        weather.setText(dataObject.get(0).getNow().getCond_txt());
                        Glide.with(MainHallActivity.this).load(getResources().getIdentifier("retail_he_" + dataObject.get(0).getNow().getCond_code(), "mipmap", getPackageName())).into(weatherImg);
                        weatherImg.setColorFilter(getResources().getColor(R.color.white));
                        temp.setText(String.format("%s℃", dataObject.get(0).getNow().getTmp()));
                    }
                });
    }

    private void initBannerView() {
        bannerVp.setPageTransformer(true, new ParallaxTransformer());
        bannerVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == bannerList.size() - 1) {
                    bannerVp.setCurrentItem(0, true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 开始滚动
     *
     * @param pager        页
     * @param smoothScroll 是否自动滚动
     */
    public void startScroll(int pager, boolean smoothScroll) {
        stopScroll();//防止重复启动
        if (bannerVp == null || bannerList.size() <= 0)
            return;
        pager = pager >= myPagerAdapter.getCount() - 1 ? 0 : pager;//滚动到最后一页时，从头开始
        bannerVp.setCurrentItem(pager, smoothScroll);
        long delayMillis = 10000;

        bannerVp.postDelayed(scrollRunnable = new Runnable() {
            @Override
            public void run() {
                setViewPagerSpeed();
                startScroll(bannerVp.getCurrentItem() + 1, true);
            }
        }, delayMillis);
    }

    /**
     * 设置滚动速度
     */
    private void setViewPagerSpeed() {
        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller(this, new LinearInterpolator());
            field.set(bannerVp, scroller);
            scroller.setmDuration(600);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 停止滚动
     */
    public void stopScroll() {
        if (scrollRunnable != null)
            bannerVp.removeCallbacks(scrollRunnable);
    }

}
