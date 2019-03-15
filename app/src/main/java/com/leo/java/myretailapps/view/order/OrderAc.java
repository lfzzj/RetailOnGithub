package com.leo.java.myretailapps.view.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.leo.java.myretailapps.R;
import com.leo.java.myretailapps.adapter.MyFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderAc extends AppCompatActivity {
    @BindView(R.id.order_tabLayout)
    TabLayout orderTabLayout;
    @BindView(R.id.order_viewPager)
    ViewPager orderViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);

        Toolbar mToolbarTb = findViewById(R.id.tb_order_toolbar);
        setSupportActionBar(mToolbarTb);
        //这句代码使启用Activity回退功能，并显示Toolbar上的左侧回退图标
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initUI();
    }

    private void initUI() {
        List<Fragment> mFragments = new ArrayList<>();
        OrderItemFragment allFr = new OrderItemFragment("all");
        mFragments.add(allFr);
        OrderItemFragment needFr = new OrderItemFragment("need");
        mFragments.add(needFr);
        OrderItemFragment successFr = new OrderItemFragment("success");
        mFragments.add(successFr);
        OrderItemFragment failFr = new OrderItemFragment("fail");
        mFragments.add(failFr);

        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(), mFragments, new String[]{"全部", "待审核", "审核成功", "审核失败"});
        orderViewPager.setAdapter(adapter);
        orderTabLayout.setupWithViewPager(orderViewPager);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
