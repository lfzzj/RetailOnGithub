package com.leo.java.myretailapps.guild_hall;


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

public class GuildActivity extends AppCompatActivity {
    @BindView(R.id.guild_tabLayout)
    TabLayout guildTabLayout;
    @BindView(R.id.guild_viewPager)
    ViewPager guildViewPager;

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
