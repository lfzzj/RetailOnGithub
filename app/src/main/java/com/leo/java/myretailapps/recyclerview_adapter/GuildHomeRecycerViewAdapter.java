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

/**
 * Name: GuildHomeRecycerViewAdapter
 * Author: Leo
 * Comment: //蚂蚁之家
 * Date: 2019/3/15 10:03
 * Modify:
 */
public class GuildHomeRecycerViewAdapter extends RecyclerView.Adapter<GuildHomeRecycerViewAdapter.GuildViewHolder> {
    private List<UserEntity> userEntities;
    private Context context;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS", Locale.getDefault());

    public GuildHomeRecycerViewAdapter(Context context, List<UserEntity> userEntities) {
        this.userEntities = userEntities;
        this.context = context;
    }

    @NonNull
    @Override
    public GuildViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.guide_frg_item_view, viewGroup, false);
        return new GuildViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GuildViewHolder viewHolder, int i) {
        viewHolder.guildItemUserPhone.setText("13888888888");
        viewHolder.guildItemUserEnterTime.setText(String.format("%s%s", context.getResources().getString(R.string.guild_enter_time), sdf.format(new Date())));
        viewHolder.guildItemUserLevel.setText(String.format("%s%s", context.getResources().getString(R.string.order_check_level), context.getResources().getString(R.string.vip_1)));
        viewHolder.guildItemUserPost.setText(String.format("%s%s", context.getResources().getString(R.string.guild_post), "5人"));

        GlideRoundTransform transform = new GlideRoundTransform(context, DisplayUtil.dip2px(context, 24));
        transform.setNeedCorner(true, true, true, true);
        RequestOptions options = new RequestOptions().transform(transform);
        Glide.with(context).asBitmap().load(R.mipmap.retail_logo).apply(options).into(viewHolder.guildItemUserIcon);
    }

    @Override
    public int getItemCount() {
        return userEntities.size();
    }

    static class GuildViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.guild_item_user_icon)
        ImageView guildItemUserIcon;
        @BindView(R.id.guild_item_user_phone)
        TextView guildItemUserPhone;
        @BindView(R.id.guild_item_user_level)
        TextView guildItemUserLevel;
        @BindView(R.id.guild_item_user_enter_time)
        TextView guildItemUserEnterTime;
        @BindView(R.id.guild_item_user_post)
        TextView guildItemUserPost;

        GuildViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
