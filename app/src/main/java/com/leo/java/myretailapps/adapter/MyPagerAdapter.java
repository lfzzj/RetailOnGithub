package com.leo.java.myretailapps.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.leo.java.myretailapps.R;
import com.leo.java.myretailapps.custom.GlideRoundTransform;
import com.leo.java.myretailapps.model.DataBean;
import com.leo.java.myretailapps.util.DisplayUtil;

import java.util.List;


public class MyPagerAdapter extends PagerAdapter {

    private List<DataBean.HeadBean> mData;
    private Context mContext;

    public MyPagerAdapter(List<DataBean.HeadBean> data, Context context) {
        mData = data;
        mContext = context;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        View inflate = LayoutInflater.from(container.getContext()).inflate(R.layout.cardviewpager_item, container, false);
        ImageView imageView1 = inflate.findViewById(R.id.img_card_item);
        GlideRoundTransform transform = new GlideRoundTransform(mContext, DisplayUtil.dip2px(mContext,10));
        transform.setNeedCorner(true, true, true, true);
        RequestOptions options = new RequestOptions().transform(transform);
        Glide.with(mContext).asBitmap().load(mData.get(position % mData.size()).getImg()).apply(options).into(imageView1);

//        Glide.with(mContext).load(mData.get(position % mData.size()).getImg()).into(imageView1);

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        container.addView(inflate);
        return inflate;
    }

    @Override
    public int getCount() {
        if (mData.size() < 1) {
            return 0;
        }
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(((View) object));
    }
}
