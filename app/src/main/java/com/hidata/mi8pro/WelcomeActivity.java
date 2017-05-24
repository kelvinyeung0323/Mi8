package com.hidata.mi8pro;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hidata.mi8pro.model.UserInfo;
import com.hidata.mi8pro.util.HttpUtil;
import com.hidata.mi8pro.util.Mi8DBHelper;
import com.hidata.mi8pro.util.ResponseResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by k_way on 2017/5/17.
 */

public class WelcomeActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageView imageView = new ImageView(this);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(layoutParams);
        imageView.setImageResource(R.drawable.welcome);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        setContentView(imageView);

        new Thread(new WelComeThread()).start();
    }


    class WelComeThread implements Runnable {


        @Override
        public void run() {

            //检查是否登录
            ResponseResult responseResult = HttpUtil.get(ApiUrl.INDEX_STK);
            if (responseResult != null) {
                String status = responseResult.getStatus();

                if ("OK".equalsIgnoreCase(status)) {
                    //跳转到主页
                    Intent intent = new Intent();
                    intent.setClass(WelcomeActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            //TODO:自动登录
            //获取用名
            Mi8DBHelper mi8DBHelper = ((Mi8Application) getApplication()).getMi8DBHelper();
            UserInfo userInfo = mi8DBHelper.getUserInfo();

            if (userInfo != null) {
                Map<String, String> params = new HashMap<>();
                params.put("username", userInfo.getUserName());
                params.put("password", userInfo.getPassword());
                ResponseResult loginResult = HttpUtil.post(ApiUrl.LOGIN, params);
                if(loginResult!=null&&"OK".equalsIgnoreCase(loginResult.getStatus())){
                    //跳转到主页
                    Intent intent = new Intent();
                    intent.setClass(WelcomeActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            //跳转到登录页面
            Intent intent = new Intent();
            intent.setClass(WelcomeActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();


        }
    }
}
