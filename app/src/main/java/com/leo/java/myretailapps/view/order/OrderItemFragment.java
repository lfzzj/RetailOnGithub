package com.leo.java.myretailapps.view.order;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leo.java.myretailapps.R;
import com.leo.java.myretailapps.model.UserEntity;
import com.leo.java.myretailapps.recyclerview_adapter.OrderRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

@SuppressLint("ValidFragment")
public class OrderItemFragment extends Fragment {
    Unbinder unbinder;
    private String typeValue;
    List<UserEntity> users;
    OrderRecyclerAdapter adapter;

    @BindView(R.id.order_item_frg_recyclerview)
    RecyclerView orderItemFrgRecyclerview;

    public OrderItemFragment(String typeValue) {
        this.typeValue = typeValue;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.order_item_fragment, container,
                false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initRecyclerView();
    }

    private void initRecyclerView() {
        users = new ArrayList<>();

        /*模拟数据*/
        for (int i = 0; i < 15; i++) {
            users.add(new UserEntity("0", "0", "www", "库里" + i, "", "", "", "", "", ""));
        }

        orderItemFrgRecyclerview.setNestedScrollingEnabled(false);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        orderItemFrgRecyclerview.setLayoutManager(manager);

        adapter = new OrderRecyclerAdapter(getActivity(), users);
        orderItemFrgRecyclerview.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
