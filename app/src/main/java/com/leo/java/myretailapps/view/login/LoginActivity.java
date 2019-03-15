package com.leo.java.myretailapps.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.leo.java.myretailapps.R;
import com.leo.java.myretailapps.view.register.HelpOtherRegisterAc;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.login_user_account)
    EditText loginUserAccount;
    @BindView(R.id.login_user_pass)
    EditText loginUserPass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        ButterKnife.bind(this);

        Toolbar mToolbarTb = findViewById(R.id.tb_login_toolbar);
        setSupportActionBar(mToolbarTb);
    }

    @OnClick({R.id.login_register, R.id.login_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_register:
                Intent intent = new Intent(LoginActivity.this, HelpOtherRegisterAc.class);
                intent.putExtra("who_register", true);
                startActivity(intent);
                break;
            case R.id.login_btn:
                break;
        }
    }
}
