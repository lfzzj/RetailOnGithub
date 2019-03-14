package com.leo.java.myretailapps.recyclerview_adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.leo.java.myretailapps.R;
import com.leo.java.myretailapps.custom.GlideRoundTransform;
import com.leo.java.myretailapps.model.UserEntity;
import com.leo.java.myretailapps.util.DisplayUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderRecyclerAdapter extends RecyclerView.Adapter<OrderRecyclerAdapter.OrderViewHolder> {
    private Context context;
    private List<UserEntity> list;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS", Locale.getDefault());

    public OrderRecyclerAdapter(Context context, List<UserEntity> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.order_list_item, viewGroup, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder viewHolder, int i) {
        UserEntity u = list.get(i);
        viewHolder.itemName.setText(u.getNick());
        viewHolder.itemPhone.setText("13888888888");
        viewHolder.itemLevel.setText(String.format("%s%s", context.getResources().getString(R.string.order_check_level), context.getResources().getString(R.string.vip_1)));
        viewHolder.itemZfb.setText(String.format("%s%s", context.getResources().getString(R.string.order_check_zfb), "464546@qq.com"));
        viewHolder.itemWx.setText(String.format("%s%s", context.getResources().getString(R.string.order_check_wx), "wx_id452312154"));
        viewHolder.itemTime.setText(String.format("%s%s", context.getResources().getString(R.string.order_start_time), sdf.format(new Date())));
        viewHolder.itemWay.setText("上门自提");
        viewHolder.itemTalk.setText("暂无评论");

        GlideRoundTransform transform = new GlideRoundTransform(context, DisplayUtil.dip2px(context, 33));
        transform.setNeedCorner(true, true, true, true);
        RequestOptions options = new RequestOptions().transform(transform);
        Glide.with(context).asBitmap().load(R.mipmap.retail_logo).apply(options).into(viewHolder.itemUserIcon);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class OrderViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_time)
        TextView itemTime;
        @BindView(R.id.item_states)
        TextView itemStates;
        @BindView(R.id.item_user_icon)
        ImageView itemUserIcon;
        @BindView(R.id.item_name)
        TextView itemName;
        @BindView(R.id.item_phone)
        TextView itemPhone;
        @BindView(R.id.item_level)
        TextView itemLevel;
        @BindView(R.id.item_wx)
        TextView itemWx;
        @BindView(R.id.item_zfb)
        TextView itemZfb;
        @BindView(R.id.item_way)
        TextView itemWay;
        @BindView(R.id.item_talk)
        TextView itemTalk;
        @BindView(R.id.item_more_btn)
        TextView itemMoreBtn;

        OrderViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
