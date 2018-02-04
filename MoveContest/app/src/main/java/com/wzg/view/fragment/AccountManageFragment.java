package com.wzg.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wzg.R;
import com.wzg.model.Account;
import com.wzg.model.adapter.AccountAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangZhiGang on 2018/2/2.
 */

public class AccountManageFragment extends Fragment {

    private List<Account> mAccountList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private AccountAdapter mAccountAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        initData();


        View view = inflater.inflate(R.layout.accout_manage, container,false);

        mRecyclerView = view.findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAccountAdapter = new AccountAdapter(mAccountList);
        mRecyclerView.setAdapter(mAccountAdapter);

        return view;
    }


    /**
     *
     * 初始化数据
     *
     */
    private void initData() {
        Account account1 = new Account("1", R.drawable.car1, "辽A10001", "车主:张三", "余额:100元");
        mAccountList.add(account1);

        Account account2 = new Account("2", R.drawable.car2, "辽A10002", "车主:李四", "余额:99元");
        mAccountList.add(account2);

        Account account3 = new Account("3", R.drawable.car3, "辽A10003", "车主:王五", "余额:103元");
        mAccountList.add(account3);

        Account account4 = new Account("4", R.drawable.car4, "辽A10004", "车主:赵六", "余额:1元");
        mAccountList.add(account4);
    }







}
