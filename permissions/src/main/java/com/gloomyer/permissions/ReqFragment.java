package com.gloomyer.permissions;

import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.util.Collections;
import java.util.List;

/**
 * @Classname ReqFragment
 * @Description 权限请求 fragment
 * @Date 2019-10-08 15:36
 * @Created by gloomy
 */
public class ReqFragment extends Fragment {


    private static final int REQ_CODE = 0xFFF;
    ReqCallback callback;
    List<String> pers;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView = new TextView(getContext());
        textView.setHeight(1);
        textView.setWidth(1);
        return textView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        GPR.log("onActivityCreated");

        String[] strings = new String[pers.size()];
        for (int i = 0; i < pers.size(); i++) {
            strings[i] = pers.get(i);
        }
        pers.clear();
        requestPermissions(strings, REQ_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (int grant : grantResults) {
            if (grant != PackageManager.PERMISSION_GRANTED) {
                cb(false);
                return;
            }
        }

        cb(true);
    }

    private void cb(boolean isGrant) {
        if (callback != null) {
            callback.callback(isGrant);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        callback = null;
        pers.clear();
    }
}
