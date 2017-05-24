package com.hidata.mi8pro.util;

import android.os.Handler;

import com.fasterxml.jackson.core.type.TypeReference;


/**
 * Created by k_way on 2017/5/19.
 */

public class HttpPostThread extends HttpThread {

    private static final String TAG = "HttpGetThread";

    public HttpPostThread(Object params,Handler handler, String url, int action){
        super("post",params,handler,url,action,new TypeReference<ResponseResult<Object>>(){},false);
    }

    public HttpPostThread(Object params,Handler handler, String url, int action, TypeReference typeReference){
      super("post",params,handler,url,action,typeReference,false);
    }
    public HttpPostThread(Object params,Handler handler, String url, int action, TypeReference typeReference, Boolean isLoop){
        super("post",null,handler,url,action,typeReference,isLoop);
    }


}
