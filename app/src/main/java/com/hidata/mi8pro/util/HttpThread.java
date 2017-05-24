package com.hidata.mi8pro.util;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Method;


/**
 * Created by k_way on 2017/5/19.
 */

public class HttpThread extends Thread {

    private static final String TAG = "HttpThread";


    private Handler mHander;
    private String mURL;
    private int mAction;
    private Boolean isGo = true;
    private Object o;
    private Boolean isLoop = false;

    private long sleepTime = 5000;
    private TypeReference mTypeReference;
    private Object mParams;
    private String mMethod;


    public HttpThread(String mMethod, Object params, Handler handler, String url, int action, TypeReference typeReference, Boolean isLoop){
        this.mAction = action;
        this.mHander = handler;
        this.o = new Object();
        this.mURL = url;
        this.isLoop=isLoop;
        this.mParams = params;
        this.mMethod = mMethod;
        this.mTypeReference =  typeReference;
    }


    public void setSleepTime(long sleepTime){
        this.sleepTime = sleepTime;
    }
    private void sendMsg(Bundle data) {
        Message msg = new Message();
        msg.setTarget(mHander);
        msg.setData(data);
        msg.what = mAction;
        msg.sendToTarget();
    }

    public void go(Boolean b){
        isGo = b;
        if(b == true){
            synchronized (o){
                o.notify();
            }

        }
    }

    @Override
    public void run() {

       if(isLoop){
           doLoop();
       }else{
           doRequest();
       }

    }


    public void doLoop(){
        int times = 0;
        synchronized (o){
            while (!Thread.currentThread().isInterrupted()) {
                doRequest();
                times++;
                Log.d(TAG,"request time :" + times);
                try {
                    Thread.sleep(sleepTime);
                    if(!isGo){
                        o.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }

    public void doRequest(){
        ResponseResult<?> result = null;
        //数据请求
        if("post".equalsIgnoreCase(mMethod)) {
             result = HttpUtil.post(mURL, mParams, mTypeReference);
        }else if("get".equalsIgnoreCase(mMethod)){
             result = HttpUtil.get(mURL, mTypeReference);
        }
        if(result!= null){
            Log.d(TAG,result.toString());
        }else{
            Log.d(TAG,"resut is null");
        }


        Bundle bundle = new Bundle();
        if(result != null){
            bundle.putSerializable("data",result);
        }
        sendMsg(bundle);

    }
}
