package com.hidata.mi8pro.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hidata.mi8pro.model.UserInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by k_way on 2017/4/28.
 */

public class Mi8DBHelper extends SQLiteOpenHelper {


    private static final String dbName="mi8.db";
    private static final int version = 1;



    public Mi8DBHelper(Context context){
        super(context,dbName,null,version);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS user (user_name varchar(50), password varchar(100),cookies text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void saveUserInfo(UserInfo userInfo){
        SQLiteDatabase db = getWritableDatabase();
        //选delete 后insert,然后改标志为1,改其他的标志为0
       // db.delete("user","user_name=?",new String[]{userName});
        db.delete("user",null,null);

        ContentValues contentValues = new ContentValues();
        contentValues.put("user_name",userInfo.getUserName());
        contentValues.put("password",userInfo.getPassword());
        db.insert("user",null,contentValues);
        db.close();
    }

    public UserInfo getUserInfo(){
        SQLiteDatabase db = getReadableDatabase();
        UserInfo userInfo = new UserInfo();
        Cursor cursor =  db.query("user",new String[]{"user_name","password","cookies"},null,null,null,null,null,"1");
        if(cursor.moveToNext()){

            userInfo.setUserName(cursor.getString(0));
            userInfo.setPassword(cursor.getString(1));
            cursor.close();
            db.close();
            return userInfo;
        }
        return null;
    }




}
