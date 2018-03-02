package com.vixkw.pubgmhd;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


import android.support.v4.app.ActivityCompat;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }


    private static final int MY_PERMISSION_REQUEST_CODE = 1024;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getExternalCacheDir();

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        linearLayout.addView(new FitStatusBarView(this));
        setContentView(linearLayout);
        {
            ViewGroup.LayoutParams btnParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            {
                Button btn1 = new Button(this);
                btn1.setLayoutParams(btnParams);
                btn1.setText("开启除草");
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        destroyGrass();
                        Toast.makeText(MainActivity.this,"成功,重启游戏生效",Toast.LENGTH_SHORT).show();
                    }
                });
                linearLayout.addView(btn1);
            }
            {
                Button btn2 = new Button(this);
                btn2.setLayoutParams(btnParams);
                btn2.setText("关闭除草");
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        grass();
                        Toast.makeText(MainActivity.this,"成功,重启游戏生效",Toast.LENGTH_SHORT).show();
                    }
                });
                linearLayout.addView(btn2);
            }


            {
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(DensityUtil.dip2px(this,10),0,0,0);
                TextView textView = new TextView(this);
                textView.setLayoutParams(layoutParams);
                textView.setTextColor(Color.WHITE);
                textView.setText("By 我思故我在\n"+
                "群：725574649\n"+
                "仅供爱好者交流使用！\n"+
                "源码地地址： https://github.com/zhangweiqwe/PubgDestroyGrass/tree/master");
                textView.setTextIsSelectable(true);
                linearLayout.addView(textView);
            }
        }



      /*  String[] permissions = new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                // 只要有一个权限没有被授予, 则直接返回 false
            }
        }*/


        /**
         * 第 2 步: 请求权限
         */
        // 一次请求多个权限, 如果其他有权限是已经授予的将会自动忽略掉
        ActivityCompat.requestPermissions(
                this,
                new String[] {
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                },
                MY_PERMISSION_REQUEST_CODE
        );

    }


    private void hintPermissionDiend(){
        new AlertDialog.Builder(this).setMessage("权限不足！").
                setCancelable(false)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).create().show();
    }




    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == MY_PERMISSION_REQUEST_CODE) {
            boolean isAllGranted = true;

            // 判断是否所有的权限都已经授予了
            for (int grant : grantResults) {
                if (grant != PackageManager.PERMISSION_GRANTED) {
                    isAllGranted = false;
                    break;
                }
            }

            if (!isAllGranted) {
                hintPermissionDiend();
            }
        }

    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */


    public static native void grass();
    public static native void destroyGrass();

}
