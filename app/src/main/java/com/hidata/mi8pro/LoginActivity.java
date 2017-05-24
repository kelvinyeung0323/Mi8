package com.hidata.mi8pro;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.core.type.TypeReference;
import com.hidata.mi8pro.util.HttpPostThread;
import com.hidata.mi8pro.util.HttpUtil;
import com.hidata.mi8pro.util.ResponseResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by k_way on 2017/5/10.
 */

public class LoginActivity extends Activity implements View.OnClickListener {


    private static final int ACT_SHOW_MASK = 0;
    private static final int ACT_HIDE_MASK = 1;
    private static final int ACT_SHOW_TOAST = 2;
    private static final int ACT_LOGIN = 3;

    private Context mContext;
    private Button loginBtn;
    private TextView userNameTw;
    private TextView passwordTw;
    private View mLoadingMask;
    private Handler mHandler;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_login);
        loginBtn = (Button) findViewById(R.id.lg_submit);
        loginBtn.setOnClickListener(this);
        userNameTw = (TextView) findViewById(R.id.lg_name);
        passwordTw = (TextView) findViewById(R.id.lg_password);
        mLoadingMask = findViewById(R.id.loadingMask);


        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case ACT_LOGIN:
                        mLoadingMask.setVisibility(View.INVISIBLE);
                        ResponseResult responseResult = (ResponseResult) msg.getData().get("data");
                        if(null != responseResult&& "OK".equalsIgnoreCase(responseResult.getStatus())){
                            //跳转到主页面
                            Intent intent = new Intent();
                            intent.setClass(LoginActivity.this,MainActivity.class);
                            startActivity(intent);
                        }

                        break;
                    default:


                }


            }
        };
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lg_submit:
                //登录
                String userName = userNameTw.getText().toString();
                String password = passwordTw.getText().toString();
                Map<String, CharSequence> params = new HashMap();
                params.put("username", userName);
                params.put("password", password);
                mLoadingMask.setVisibility(View.VISIBLE);
                new HttpPostThread(params,mHandler,ApiUrl.LOGIN,ACT_LOGIN).start();

            default:
                break;
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        mLoadingMask.setVisibility(View.INVISIBLE);
    }

}
