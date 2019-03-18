package com.leo.java.myretailapps.view.changepass;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.leo.java.myretailapps.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangePassAc extends AppCompatActivity {

    @BindView(R.id.et_old_password)
    EditText etOldPassword;
    @BindView(R.id.et_new_password)
    EditText etNewPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);
        ButterKnife.bind(this);

        Toolbar mToolbarTb = findViewById(R.id.tb_changepass_toolbar);
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


    private boolean IsInputOK() {
        if (etOldPassword.getEditableText().toString().trim().equals("")) {
            Toast.makeText(this, R.string.change_password_not_empty, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etOldPassword.getEditableText().toString().trim().length() < 6) {
            Toast.makeText(this, R.string.change_password_beyond_six, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (IsPasswordStrOK(etOldPassword.getEditableText().toString().trim())) {
            Toast.makeText(this, R.string.change_password_consist, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etNewPassword.getEditableText().toString().trim().equals("")) {
            Toast.makeText(this, R.string.change_password_not_empty, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etNewPassword.getEditableText().toString().trim().length() < 6) {
            Toast.makeText(this, R.string.change_password_beyond_six, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etNewPassword.getEditableText().toString().trim().length() > 16) {
            Toast.makeText(this, R.string.change_password_over_six, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (IsPasswordStrOK(etNewPassword.getEditableText().toString().trim())) {
            Toast.makeText(this, R.string.change_password_consist, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public boolean IsPasswordStrOK(String pwd) {
        char[] a = pwd.toCharArray();
        for (char anA : a) {
            boolean b1 = (anA >= 'a' && anA <= 'z') || (anA >= 'A' && anA <= 'Z');
            boolean b2 = (anA >= '0' && anA <= '9');
            if (!b1 && !b2) {
                return true;
            }
        }
        return false;
    }

    @OnClick(R.id.change_passbtn_ok)
    public void onViewClicked() {
        if (IsInputOK()) {

        }
    }
}
