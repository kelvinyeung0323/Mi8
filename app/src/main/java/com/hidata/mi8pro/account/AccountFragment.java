package com.hidata.mi8pro.account;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hidata.mi8pro.R;
import com.hidata.mi8pro.recommend.RecommendCommisionFragment;
import com.hidata.mi8pro.recommend.RecommendMemberFragment;

/**
 * Created by k_way on 2017/5/10.
 */

public class AccountFragment extends Fragment implements View.OnClickListener {

    private View mBalanceTabBtn;
    private View mRechargeTabBtn;
    private View mEnchashmentTabBtn;
    private TextView mBalanceTabBtnText;
    private TextView mRechargeTabBtnText;
    private TextView mEnchashmentTabBtnText;
    private Fragment mBalanceFragment;
    private Fragment mRechargeFragment;
    private Fragment mEnchashmentFragment;
    private FragmentManager mFragmentManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_account,container,false);
        initViews(view);
        mFragmentManager = getFragmentManager();
        setTabSelected(0);
        return view;
    }





    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.accBalanceBtn:
                setTabSelected(0);
                break;
            case R.id.accRechargeBtn:
                setTabSelected(1);
                break;
            case R.id.accEnchashmentBtn:
                setTabSelected(2);
                break;

        }

    }


    public void initViews(View rootView) {
        //Tab按钮
        mBalanceTabBtn = rootView.findViewById(R.id.accBalanceBtn);
        mRechargeTabBtn = rootView.findViewById(R.id.accRechargeBtn);
        mEnchashmentTabBtn = rootView.findViewById(R.id.accEnchashmentBtn);


        //Tab按钮文字
        mBalanceTabBtnText = (TextView) rootView.findViewById(R.id.accBalanceBtnText);
        mRechargeTabBtnText = (TextView) rootView.findViewById(R.id.accRechargeBtnText);
        mEnchashmentTabBtnText = (TextView) rootView.findViewById(R.id.accEnchashmentBtnText);
        //添加监听
        mBalanceTabBtn.setOnClickListener(this);
        mRechargeTabBtn.setOnClickListener(this);
        mEnchashmentTabBtn.setOnClickListener(this);
    }


    /**
     * 根据传入的index参数来设置选中的tab页
     *
     * @param index 每个tab页对应的下标
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
                mBalanceTabBtn.setBackgroundResource(R.color.mi8YellowLight);
                //TODO:字体设置为白色
                mBalanceTabBtnText.setTextColor(0xff000000);
                if (mBalanceFragment == null) {
                    mBalanceFragment = new AccountBalanceFragmemt();
                    transaction.add(R.id.accContent, mBalanceFragment);
                } else {
                    transaction.show(mBalanceFragment);
                }

                break;
            case 1:
                // 当点击了交易tab时，改变控件的文字颜色
                mRechargeTabBtn.setBackgroundResource(R.color.mi8YellowLight);
                //字体设置为黑色
                mRechargeTabBtnText.setTextColor(0xff000000);
                //字体设置为黑色
                if (mRechargeFragment == null) {
                    mRechargeFragment = new AccountRechargeFragmemt();
                    transaction.add(R.id.accContent, mRechargeFragment);
                } else {
                    transaction.show(mRechargeFragment);
                }

                break;
            case 2:
                // 当点击了交易tab时，改变控件的文字颜色
                mEnchashmentTabBtn.setBackgroundResource(R.color.mi8YellowLight);
                //字体设置为黑色
                mEnchashmentTabBtnText.setTextColor(0xff000000);
                //字体设置为黑色
                if (mEnchashmentFragment == null) {
                    mEnchashmentFragment = new AccountEnchashmentFragment();
                    transaction.add(R.id.accContent, mEnchashmentFragment);
                } else {
                    transaction.show(mEnchashmentFragment);
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
        mBalanceTabBtn.setBackgroundResource(R.color.mi8BlueLight);
        mRechargeTabBtn.setBackgroundResource(R.color.mi8BlueLight);
        mEnchashmentTabBtn.setBackgroundResource(R.color.mi8BlueLight);


        //文字颜色改为白色
        mBalanceTabBtnText.setTextColor(0xffffffff);
        mRechargeTabBtnText.setTextColor(0xffffffff);
        mEnchashmentTabBtnText.setTextColor(0xffffffff);

    }


    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction 用于对Fragment执行操作的事务
     */
    public void hideFragments(FragmentTransaction transaction) {
        if (mBalanceFragment != null) {
            transaction.hide(mBalanceFragment);
        }
        if (mRechargeFragment != null) {
            transaction.hide(mRechargeFragment);
        }
        if (mEnchashmentFragment != null) {
            transaction.hide(mEnchashmentFragment);
        }

    }
}
