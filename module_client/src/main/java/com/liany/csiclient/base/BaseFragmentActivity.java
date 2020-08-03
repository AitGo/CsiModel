package com.liany.csiclient.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * @创建者 ly
 * @创建时间 2019/3/15
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public abstract class BaseFragmentActivity extends FragmentActivity {
    public FragmentManager fragmentManager;
    public FragmentTransaction transaction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId()); //设置布局id
        initData();  //setData
        initView();  //初始化view
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 初始化布局文件,设置布局ID
     */
    protected abstract int getLayoutId();

    /**
     * 初始化控件
     */
    protected abstract void initView();


    /**
     * 初始化数据
     */
    protected abstract void initData();
}
