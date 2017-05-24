package com.hidata.mi8pro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;





/**
 * Created by k_way on 2017/5/15.
 */

public class BaseActivity extends Activity {

    private Mi8Application mi8Application = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mi8Application =(Mi8Application)getApplication();
    }
}
