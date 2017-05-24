package com.hidata.mi8pro.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.hidata.mi8pro.R;

/**
 * Created by k_way on 2017/5/11.
 */

public class Mi8Alert {

    private Context mContext;
    private Dialog mDialog;
    private ViewGroup mContentViewLayout;
    private View mContentView;
    private Button mBtnOk;
    private TextView mTitleView;
    private ViewGroup mRemarkViewLayout;


    public Mi8Alert(@NonNull Context context) {
        mContext = context;
        mDialog = new Dialog(context,R.style.Mi8Dialog);
        View view = LayoutInflater.from(context).inflate(R.layout.widget_alert,null);
        mDialog.setContentView(view);
        mTitleView = (TextView) view.findViewById(R.id.dialogTitle);
        mBtnOk = (Button) view.findViewById(R.id.dialogButton);
        mContentViewLayout = (ViewGroup) view.findViewById(R.id.dialogContent);
        mRemarkViewLayout = (ViewGroup) view.findViewById(R.id.dialogRemark);

        mBtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dimiss();
            }
        });


        //设置提示框大小
        Window dialogWindow = mDialog.getWindow();
        WindowManager m = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高度
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        Point point = new Point();
        d.getSize(point);
        //p.height = (int) (point.y * 0.8); // 高度设置为屏幕的0.6，根据实际情况调整
        p.width = (int) (point.x * 0.8); // 宽度设置为屏幕的0.65，根据实际情况调整
        dialogWindow.setAttributes(p);
    }

    public Mi8Alert(Context context, CharSequence title){
        this(context);
        setTitle(title);

    }

    public void setContentView(View contentView){
        mContentView = contentView;
        mContentViewLayout.removeAllViews();
        mContentViewLayout.addView(contentView);
    }

    public void setTitle(CharSequence title){
        mTitleView.setText(title);
    }

    public void show(){
        mDialog.show();
    }

    public void dimiss(){
        mDialog.dismiss();
    }
    public void setRemark(CharSequence remark){

        if(mRemarkViewLayout.getChildCount()==1&&mRemarkViewLayout.getChildAt(0) instanceof TextView){
            ((TextView) mRemarkViewLayout.getChildAt(0)).setText(remark);

        }else{
            mRemarkViewLayout.removeAllViews();
            TextView textView = new TextView(mContext);
            textView.setText(remark);
            mRemarkViewLayout.addView(textView);
        }
    }

    public View getContentView(){
        return mContentView;
    }





}
