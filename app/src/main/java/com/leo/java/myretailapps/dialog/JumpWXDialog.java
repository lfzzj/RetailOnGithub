package com.leo.java.myretailapps.dialog;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.leo.java.myretailapps.R;

public class JumpWXDialog extends BaseDialog {
    public static final String WX = "wechart";

    TextView wechart_number;
    TextView start_wx_btn;

    public static JumpWXDialog newInstance(String wechart) {
        JumpWXDialog mDialog;
        mDialog = new JumpWXDialog();
        Bundle mBundle = new Bundle();
        mBundle.putString(WX, wechart);
        mDialog.setArguments(mBundle);
        return mDialog;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.jump_wx_dialog;
    }

    @Override
    protected void initView(View view) {
        findView(view);
        start_wx_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClicked != null) {
                    onClicked.setClicked();
                }
            }
        });
    }

    private void findView(View view) {
        wechart_number = view.findViewById(R.id.wechart_number);
        start_wx_btn = view.findViewById(R.id.start_wx_btn);
    }

    @Override
    protected void loadData(Bundle bundle) {
        if (bundle != null) {
            String wechart = bundle.getString(WX, "");
            wechart_number.setText(String.format("%s%s", getResources().getString(R.string.vip_now_wx), wechart));
        }
    }

    public void show(Activity activity) {
        show(activity, "AskWayDialog");
    }

    /**
     * 按钮点击回调
     */
    public onClickInterface onClicked;

    public void setOnClicked(onClickInterface onClicked) {
        this.onClicked = onClicked;
    }

    public interface onClickInterface {
        void setClicked();
    }
}
