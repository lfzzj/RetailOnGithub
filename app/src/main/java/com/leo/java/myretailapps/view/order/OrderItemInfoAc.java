package com.leo.java.myretailapps.view.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.leo.java.myretailapps.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderItemInfoAc extends AppCompatActivity {
    @BindView(R.id.order_info_name)
    TextView orderInfoName;
    @BindView(R.id.order_info_phone)
    TextView orderInfoPhone;
    @BindView(R.id.order_info_phone_more)
    TextView orderInfoPhoneMore;
    @BindView(R.id.order_info_wx)
    TextView orderInfoWx;
    @BindView(R.id.order_info_zfb)
    TextView orderInfoZfb;
    @BindView(R.id.order_info_user_level)
    TextView orderInfoUserLevel;
    @BindView(R.id.order_info_good_way)
    TextView orderInfoGoodWay;
    @BindView(R.id.order_info_good_states)
    TextView orderInfoGoodStates;
    @BindView(R.id.order_info_good_rece_adress)
    TextView orderInfoGoodReceAdress;
    @BindView(R.id.order_info_receiver)
    TextView orderInfoReceiver;
    @BindView(R.id.order_info_rece_phone)
    TextView orderInfoRecePhone;
    @BindView(R.id.order_info_express_company)
    TextView orderInfoExpressCompany;
    @BindView(R.id.order_info_express_number)
    TextView orderInfoExpressNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_info);
        ButterKnife.bind(this);

        Toolbar mToolbarTb = findViewById(R.id.tb_order_info_toolbar);
        setSupportActionBar(mToolbarTb);
        //这句代码使启用Activity回退功能，并显示Toolbar上的左侧回退图标
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initUI();

    }

    private void initUI() {
        orderInfoUserLevel.setText(getResources().getString(R.string.vip_13));
        orderInfoName.setText(String.format("%s%s", getResources().getString(R.string.order_info_name),"的互"));
        orderInfoPhone.setText(String.format("%s%s", getResources().getString(R.string.order_info_phone),"138888888"));
        orderInfoPhoneMore.setText(String.format("%s%s", getResources().getString(R.string.order_info_phone_more),"1399999"));
        orderInfoWx.setText(String.format("%s%s", getResources().getString(R.string.order_info_wx),"ql33wew"));
        orderInfoZfb.setText(String.format("%s%s", getResources().getString(R.string.order_info_zfb),"ewrewfs@ds.com"));
        orderInfoGoodWay.setText(String.format("%s%s", getResources().getString(R.string.order_info_good_way),"快递"));
        orderInfoGoodStates.setText(String.format("%s%s", getResources().getString(R.string.order_info_good_states),"快递"));
        orderInfoGoodReceAdress.setText(String.format("%s%s", getResources().getString(R.string.order_info_good_adress),"深圳"));
        orderInfoReceiver.setText(String.format("%s%s", getResources().getString(R.string.order_info_good_receiver),"饿顶顶顶"));
        orderInfoRecePhone.setText(String.format("%s%s", getResources().getString(R.string.order_info_good_phone),"138888888"));
        orderInfoExpressCompany.setText(String.format("%s%s", getResources().getString(R.string.order_info_good_company),"中通"));
        orderInfoExpressNumber.setText(String.format("%s%s", getResources().getString(R.string.order_info_good_number),"23412142212"));
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
