package com.gloomyer.permissions;


import android.text.TextUtils;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @Classname Gloomy permissions request
 * @Description 请求类
 * @Date 2019-10-08 15:33
 * @Created by gloomy
 */
public class GPR {

    private static final String TAG = GPR.class.getSimpleName();


    public static GPR request(FragmentActivity activity) {
        return new GPR(activity);
    }

    public static GPR request(Fragment fragment) {
        return new GPR(fragment);
    }

    private ReqFragment mReqFragment;
    private List<String> pers;
    private FragmentManager manager;

    private GPR(FragmentActivity activity) {
        manager = activity.getSupportFragmentManager();
        init();
    }

    private GPR(Fragment fragment) {
        manager = fragment.getChildFragmentManager();
        init();
    }


    private void init() {
        pers = new ArrayList<>();
        mReqFragment = new ReqFragment();
    }

    /**
     * 设置需要请求的权限
     *
     * @param pers 权限列表
     */
    public GPR setPermissions(String... pers) {
        if (pers.length <= 0) {
            throw new RuntimeException("需要的权限不能为0!");
        }
        this.pers.addAll(Arrays.asList(pers));
        return this;
    }

    /**
     * 设置需要请求的权限
     *
     * @param per 权限
     */
    public GPR addPermission(String per) {
        if (TextUtils.isEmpty(per)) {
            throw new RuntimeException("需要的权限不能为null!");
        }
        this.pers.add(per);
        return this;
    }

    /**
     * 设置请求回调
     *
     * @param callback 请求回调
     */
    public GPR setCallback(ReqCallback callback) {
        mReqFragment.callback = callback;
        return this;
    }

    /**
     * 开始请求权限
     */
    public void req() {
        mReqFragment.pers = pers;
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(mReqFragment, "key:" + mReqFragment.hashCode());
        transaction.show(mReqFragment);
        transaction.commit();
        manager = null;
        pers = null;
    }

    static void log(String log) {
        if (BuildConfig.DEBUG) {
            Log.e(TAG, log);
        }
    }
}
