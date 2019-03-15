package com.leo.java.myretailapps.checkpoint;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.leo.java.myretailapps.R;
import com.leo.java.myretailapps.dialog.JumpWXDialog;
import com.leo.java.myretailapps.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Name: CheckpointAc
 * Author: Leo
 * Comment: // 闯关
 * Date: 2019/3/15 10:04
 * Modify:
 */
public class CheckpointAc extends AppCompatActivity {
    @BindView(R.id.root)
    LinearLayout root;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkpoint);
        ButterKnife.bind(this);

        Toolbar mToolbarTb = findViewById(R.id.tb_checkpoint_toolbar);
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

    @OnClick(R.id.start_check_btn)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.start_check_btn:
                Util.getInstance(this).CopyToClip(root,"ql955927");
                JumpWXDialog jumpWXDialog = JumpWXDialog.newInstance("ql955927");
                jumpWXDialog.setOnClicked(new JumpWXDialog.onClickInterface() {
                    @Override
                    public void setClicked() {
                        Util.getInstance(CheckpointAc.this).jumpWX(root);
                    }
                });
                jumpWXDialog.show(this);
                break;
        }
    }
}
