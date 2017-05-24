package com.hidata.mi8pro.trade;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hidata.mi8pro.Index.IndexFragment;
import com.hidata.mi8pro.R;
import com.hidata.mi8pro.account.AccountFragment;
import com.hidata.mi8pro.profile.ProfileFragment;
import com.hidata.mi8pro.recommend.RecommendFragment;

/**
 * Created by k_way on 2017/5/10.
 */

public class TradeFragment extends Fragment implements View.OnClickListener {



    private View mTradeTabBtn;
    private View mTradeHistTabBtn;
    private TextView mTradeTabBtnText;
    private TextView mTradeHistTabBtnText;
    private Fragment mTradeFragment;
    private Fragment mTradeHistFragment;
    private FragmentManager mFragmentManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_trade,container,false);
        initViews(view);
        mFragmentManager = getFragmentManager();
        setTabSelected(0);
        return view;
    }





    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tradeTabBtn:
                setTabSelected(0);
                break;
            case R.id.tradeHistTabBtn:
                setTabSelected(1);
                break;

        }

    }


    public void initViews(View rootView) {
        //Tab按钮
        mTradeTabBtn = rootView.findViewById(R.id.tradeTabBtn);
        mTradeHistTabBtn = rootView.findViewById(R.id.tradeHistTabBtn);


        //Tab按钮文字
        mTradeTabBtnText = (TextView) rootView.findViewById(R.id.tradeTabBtnText);
        mTradeHistTabBtnText = (TextView) rootView.findViewById(R.id.tradeHistTabBtnText);
        //添加监听
        mTradeTabBtn.setOnClickListener(this);
        mTradeHistTabBtn.setOnClickListener(this);
    }


    /**
     * 根据传入的index参数来设置选中的tab页
     *
     * @param index 每个tab页对应的下标。0表示指数，1表示交易，2表示推荐，3表示账户，4表示我的。
     */
    public void setTabSelected(int index) {
        // 每次选中之前先清楚掉上次的选中状态
        clearSelected();
        // 开启一个Fragment事务
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (index) {
            case 0:
                // 当点击了订单tab时，改变控件的文字颜色
                mTradeTabBtn.setBackgroundResource(R.color.mi8YellowLight);
                //TODO:字体设置为白色
                mTradeTabBtnText.setTextColor(0xff000000);
                if (mTradeFragment == null) {
                    mTradeFragment = new TradeOrderFragment();
                    transaction.add(R.id.tradeContentView, mTradeFragment);
                } else {
                    transaction.show(mTradeFragment);
                }

                break;
            case 1:
                // 当点击了交易tab时，改变控件的文字颜色
                mTradeHistTabBtn.setBackgroundResource(R.color.mi8YellowLight);
                //字体设置为黑色
                mTradeHistTabBtnText.setTextColor(0xff000000);
                //字体设置为黑色
                if (mTradeHistFragment == null) {
                    mTradeHistFragment = new TradeOrderHistFragment();
                    transaction.add(R.id.tradeContentView, mTradeHistFragment);
                } else {
                    transaction.show(mTradeHistFragment);
                }

                break;

            default:
                break;

        }

        transaction.commit();


    }

    /**
     * 清除选中状态
     */
    public void clearSelected() {
        mTradeTabBtn.setBackgroundResource(R.color.mi8BlueLight);
        mTradeHistTabBtn.setBackgroundResource(R.color.mi8BlueLight);


        //文字颜色改为白色
        mTradeTabBtnText.setTextColor(0xffffffff);
        mTradeHistTabBtnText.setTextColor(0xffffffff);

    }


    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction 用于对Fragment执行操作的事务
     */
    public void hideFragments(FragmentTransaction transaction) {
        if (mTradeFragment != null) {
            transaction.hide(mTradeFragment);
        }
        if (mTradeHistFragment != null) {
            transaction.hide(mTradeHistFragment);
        }

    }


}
