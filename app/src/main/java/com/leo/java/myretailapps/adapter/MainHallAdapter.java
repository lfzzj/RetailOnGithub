package com.leo.java.myretailapps.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.leo.java.myretailapps.R;
import com.leo.java.myretailapps.model.HallGridBean;

import java.util.List;

public class MainHallAdapter extends BaseAdapter {
    private Context context;
    private List<HallGridBean> channel_info;

    public MainHallAdapter(Context context, List<HallGridBean> channel_info) {
        this.context = context;
        this.channel_info = channel_info;
    }

    @Override
    public int getCount() {
        return channel_info.size();
    }

    @Override
    public Object getItem(int position) {
        return channel_info.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.hall_grid_item_channel, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        HallGridBean Bean = channel_info.get(position);
        viewHolder.tvChannel.setText(Bean.getText());
        Glide.with(context)
                .load(Bean.getImg())
                .into(viewHolder.ivChannel);
        return convertView;
    }

    class ViewHolder {
        ImageView ivChannel;
        TextView tvChannel;

        ViewHolder(View v) {
            ivChannel = v.findViewById(R.id.iv_channel);
            tvChannel = v.findViewById(R.id.tv_channel);
        }
    }
}
