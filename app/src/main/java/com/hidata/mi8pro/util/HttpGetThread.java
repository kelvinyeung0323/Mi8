package com.hidata.mi8pro.util;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;


/**
 * Created by k_way on 2017/5/19.
 */

public class HttpGetThread extends HttpThread {

    private static final String TAG = "HttpGetThread";



    public HttpGetThread(Handler handler, String url, int action, TypeReference typeReference){
      super("get",null,handler,url,action,typeReference,false);
    }
    public HttpGetThread(Handler handler, String url, int action, TypeReference typeReference, Boolean isLoop){
        super("get",null,handler,url,action,typeReference,isLoop);
    }


}
