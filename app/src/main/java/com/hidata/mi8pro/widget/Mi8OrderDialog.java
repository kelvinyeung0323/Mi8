package com.hidata.mi8pro.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.hidata.mi8pro.R;

/**
 * Created by k_way on 2017/5/11.
 */

public class Mi8OrderDialog implements RadioGroup.OnCheckedChangeListener {

    private Context mContext;
    private View mContextView;
    private Dialog mDialog;
    private Button mBtnOk;
    private Button mBtnCancel;
    private TextView mTitleView;
    private ViewGroup mRemarkViewLayout;
    private RadioGroup mTimesRG1;//倍数
    private RadioGroup mTimesRG2;//
    private RadioGroup mPeriodRG1;//时间
    private RadioGroup mPeriodRG2;//时间
    private RadioGroup mPeriodRG3;//时间


    public Mi8OrderDialog(@NonNull Context context) {
        mContext = context;
        mDialog = new Dialog(context, R.style.Mi8Dialog);
        mContextView  = LayoutInflater.from(context).inflate(R.layout.widget_order_dialog,null);
        mDialog.setContentView(mContextView);
        mTitleView = (TextView) mContextView.findViewById(R.id.dialogTitle);
        mBtnOk = (Button) mContextView.findViewById(R.id.dialogBtnOK);
        mBtnCancel = (Button) mContextView.findViewById(R.id.dialogBtnCancel);
        mRemarkViewLayout = (ViewGroup) mContextView.findViewById(R.id.dialogRemark);

        //倍数单选组
        mTimesRG1 = (RadioGroup) mContextView.findViewById(R.id.timesRG1);
        mTimesRG2 = (RadioGroup) mContextView.findViewById(R.id.timesRG2);
        //时间单选组
        mPeriodRG1 = (RadioGroup) mContextView.findViewById(R.id.periodRG1);
        mPeriodRG2 = (RadioGroup) mContextView.findViewById(R.id.periodRG2);
        mPeriodRG3 = (RadioGroup) mContextView.findViewById(R.id.periodRG3);

        mTimesRG1.setOnCheckedChangeListener(this);
        mTimesRG2.setOnCheckedChangeListener(this);
        mPeriodRG1.setOnCheckedChangeListener(this);
        mPeriodRG2.setOnCheckedChangeListener(this);


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


    public Mi8OrderDialog(Context context, CharSequence title){
        this(context);
        setTitle(title);

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


    /**
     * 新建stk看涨单
     */
    public void newUpStkOrder(){
        //设置背景为红色
        mContextView.setBackgroundResource(R.drawable.dialog_bg_pink);
        mDialog.show();
    }

    public void newDownStkOrder(){
        mContextView.setBackgroundResource(R.drawable.dialog_bg_green);
        mDialog.show();
    }

    public void newUpBtcOrder(){

    }

    public void newDownBtcOrder(){

    }


    /**
     * 提交订单
     */
    private void submit(){

    }



    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        if(group != null && checkedId>0){
            if(group == mTimesRG1){
                mTimesRG2.clearCheck();

            }else if(group == mTimesRG2){
                mTimesRG1.clearCheck();

            }else if(group == mPeriodRG1){
                mPeriodRG2.clearCheck();
                mPeriodRG3.clearCheck();

            }else if(group == mPeriodRG2){
                mPeriodRG1.clearCheck();
                mPeriodRG3.clearCheck();
            }else if(group == mPeriodRG3){
                mPeriodRG1.clearCheck();
                mPeriodRG2.clearCheck();
            }
            group.check(checkedId);
        }
    }

}
