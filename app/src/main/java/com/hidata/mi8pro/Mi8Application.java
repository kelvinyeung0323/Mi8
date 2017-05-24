package com.hidata.mi8pro;

import android.app.Application;
import android.webkit.CookieManager;
import com.hidata.mi8pro.util.Mi8DBHelper;
import java.net.CookieHandler;

/**
 * Created by k_way on 2017/5/15.
 */

public class Mi8Application extends Application {


    private Mi8DBHelper mi8DBHelper;
    private  CookieManager mCookieManager;

    @Override
    public void onCreate() {
        super.onCreate();
        mi8DBHelper= new Mi8DBHelper(this);
        mCookieManager = CookieManager.getInstance();
        mCookieManager.setAcceptCookie(true);
        CookieHandler.setDefault(new java.net.CookieManager());
    }





    public Mi8DBHelper getMi8DBHelper(){
        return this.mi8DBHelper;
    }
}
