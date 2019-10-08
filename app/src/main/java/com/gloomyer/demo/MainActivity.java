package com.gloomyer.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;

import com.gloomyer.permissions.GPR;
import com.gloomyer.permissions.ReqCallback;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GPR.request(this)
                .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .setCallback(new ReqCallback() {
                    @Override
                    public void callback(boolean isGet) {
                        Log.e("MainActivity", "callback:" + isGet);
                    }
                })
                .req();
    }
}
