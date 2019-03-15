package com.leo.java.myretailapps.view.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.leo.java.myretailapps.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingAc extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);

        Toolbar mToolbarTb = findViewById(R.id.tb_setting_toolbar);
        setSupportActionBar(mToolbarTb);
        //这句代码使启用Activity回退功能，并显示Toolbar上的左侧回退图标
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

    @OnClick(R.id.setting_exit_btn)
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.setting_exit_btn:

                break;
        }
    }
}
