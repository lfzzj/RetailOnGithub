package com.leo.java.myretailapps.view.register;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.leo.java.myretailapps.R;
import com.leo.java.myretailapps.photocode.CodeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HelpOtherRegisterAc extends AppCompatActivity {
    @BindView(R.id.regis_for_other_phone)
    EditText regisForOtherPhone;
    @BindView(R.id.regis_for_other_password)
    EditText regisForOtherPassword;
    @BindView(R.id.regis_for_other_password_again)
    EditText regisForOtherPasswordAgain;
    @BindView(R.id.regis_for_other_nick)
    EditText regisForOtherNick;
    @BindView(R.id.regis_call_phone)
    EditText regisCallPhone;
    @BindView(R.id.regis_for_other_wx)
    EditText regisForOtherWx;
    @BindView(R.id.regis_for_other_zfb)
    EditText regisForOtherZfb;
    @BindView(R.id.register_na_btn)
    TextView registerNaBtn;
    private CodeUtils codeUtils;
    @BindView(R.id.regis_for_other_code)
    EditText regisForOtherCode;
    @BindView(R.id.regis_for_other_code_image)
    ImageView regisForOtherCodeImage;

    private boolean registerMyself = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_other_register);
        ButterKnife.bind(this);

        registerMyself = getIntent().getBooleanExtra("who_register", false);
        initUI();

        Toolbar mToolbarTb = findViewById(R.id.tb_hp_regis_toolbar);
        setSupportActionBar(mToolbarTb);
        //这句代码使启用Activity回退功能，并显示Toolbar上的左侧回退图标
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            if (registerMyself)
                getSupportActionBar().setTitle(getResources().getString(R.string.regis));
        }

        initCode();
    }

    private void initCode() {
        codeUtils = CodeUtils.getInstance();
        Bitmap bitmap = codeUtils.createBitmap();
        Glide.with(this).load(bitmap).into(regisForOtherCodeImage);
    }

    private void initUI() {
        if (registerMyself) {
            regisCallPhone.setVisibility(View.VISIBLE);
            regisForOtherPhone.setHint(getResources().getString(R.string.register_phone));
            regisForOtherNick.setHint(getResources().getString(R.string.register_nick));
            registerNaBtn.setText(getResources().getString(R.string.regis));
        }
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

    @OnClick(R.id.register_na_btn)
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.register_na_btn:
                String codeStr = regisForOtherCode.getText().toString().trim();
                if (TextUtils.isEmpty(codeStr)) {
                    Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
                    return;
                }
                String code = codeUtils.getCode();
                if (code.equalsIgnoreCase(codeStr)) {
                    Toast.makeText(this, "验证码正确", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "验证码错误", Toast.LENGTH_SHORT).show();
                    initCode();
                }
                break;
        }
    }
}
