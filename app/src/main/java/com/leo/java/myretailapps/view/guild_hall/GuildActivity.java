package com.leo.java.myretailapps.view.guild_hall;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.leo.java.myretailapps.R;
import com.leo.java.myretailapps.adapter.MyFragmentAdapter;
import com.leo.java.myretailapps.custom.GlideRoundTransform;
import com.leo.java.myretailapps.util.DisplayUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Name: GuildActivity
 * Author: Leo
 * Comment: //蚂蚁之家
 * Date: 2019/3/15 15:59
 * Modify:
 */
public class GuildActivity extends AppCompatActivity {
    @BindView(R.id.guild_tabLayout)
    TabLayout guildTabLayout;
    @BindView(R.id.guild_viewPager)
    ViewPager guildViewPager;
    @BindView(R.id.guild_user_icon)
    ImageView guildUserIcon;
    @BindView(R.id.guild_user_phone)
    TextView guildUserPhone;
    @BindView(R.id.guild_user_level)
    TextView guildUserLevel;
    @BindView(R.id.guild_my_post)
    TextView guildMyPost;
    @BindView(R.id.guild_my_hall)
    TextView guildMyHall;
    @BindView(R.id.guild_my_level_up_1)
    TextView guildMyLevelUp1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guild_hall);
        ButterKnife.bind(this);

        Toolbar mToolbarTb = findViewById(R.id.tb_guild_toolbar);
        setSupportActionBar(mToolbarTb);
        //这句代码使启用Activity回退功能，并显示Toolbar上的左侧回退图标
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initUI();
        DataLoad();
    }

    private void DataLoad() {
        guildUserPhone.setText("13888888888");
        guildUserLevel.setText(String.format("%s%s", getResources().getString(R.string.order_check_level), getResources().getString(R.string.vip_1)));
        guildMyPost.setText(String.format("%s%s", getResources().getString(R.string.guild_my_post), "58人"));
        guildMyHall.setText(String.format("%s%s", getResources().getString(R.string.guild_hall_info), "99人"));
        guildMyLevelUp1.setText(String.format("%s%s", getResources().getString(R.string.guild_level_up_1), "56人"));

        GlideRoundTransform transform = new GlideRoundTransform(this, DisplayUtil.dip2px(this, 24));
        transform.setNeedCorner(true, true, true, true);
        RequestOptions options = new RequestOptions().transform(transform);
        Glide.with(this).asBitmap().load(R.mipmap.retail_logo).apply(options).into(guildUserIcon);

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

    private void initUI() {
        List<Fragment> mFragments = new ArrayList<>();
        GuildHallFragment levelFr = new GuildHallFragment("level");
        mFragments.add(levelFr);

        GuildHallFragment timeFr = new GuildHallFragment("time");
        mFragments.add(timeFr);

        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(), mFragments, new String[]{"按等级", "按时间"});
        guildViewPager.setAdapter(adapter);
        guildTabLayout.setupWithViewPager(guildViewPager);

    }
}
