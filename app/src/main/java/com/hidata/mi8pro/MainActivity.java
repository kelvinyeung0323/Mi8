package com.hidata.mi8pro;

import android.accounts.Account;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;


import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.hidata.mi8pro.Index.IndexFragment;
import com.hidata.mi8pro.account.AccountFragment;
import com.hidata.mi8pro.profile.ProfileFragment;
import com.hidata.mi8pro.recommend.RecommendFragment;
import com.hidata.mi8pro.trade.TradeFragment;

/**
 * Created by k_way on 2017/5/10.
 */

public class MainActivity extends Activity implements View.OnClickListener {



    Context mContext;
    //    Fragment管理类
    FragmentManager mFragmentManager;

    //Fragment
    IndexFragment mFrIndex;
    TradeFragment mFrTrade;
    RecommendFragment mFrRecommend;
    AccountFragment mFrAccount;
    ProfileFragment mFrProfile;


    //Tab按钮
    View mTabIndex;
    View mTabTrade;
    View mTabRecommend;
    View mTabAccount;
    View mTabProfile;


    //Tab按钮的文字
    TextView mTabIndexText;
    TextView mTabTradeText;
    TextView mTabRecommendText;
    TextView mTabAccountText;
    TextView mTabProfileText;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.ac_main);
        //初始化布局
        initViews();
        mFragmentManager = getFragmentManager();
        setTabSelected(0);

    }



    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.mainTabIndex:
                setTabSelected(0);
                break;
            case R.id.mainTabTrade:
                setTabSelected(1);
                break;
            case R.id.mainTabRecommend:
                setTabSelected(2);
                break;
            case R.id.mainTabAccount:
                setTabSelected(3);
                break;
            case R.id.mainTabProfile:
                setTabSelected(4);
                break;

        }

    }


    public void initViews() {
        //Tab按钮
        mTabIndex = findViewById(R.id.mainTabIndex);
        mTabTrade = findViewById(R.id.mainTabTrade);
        mTabRecommend = findViewById(R.id.mainTabRecommend);
        mTabAccount = findViewById(R.id.mainTabAccount);
        mTabProfile = findViewById(R.id.mainTabProfile);

        //Tab按钮文字
        mTabIndexText = (TextView) findViewById(R.id.mainTabIndexText);
        mTabTradeText = (TextView) findViewById(R.id.mainTabTradeText);
        mTabRecommendText = (TextView) findViewById(R.id.mainTabRecommendText);
        mTabAccountText = (TextView) findViewById(R.id.mainTabAccountText);
        mTabProfileText = (TextView) findViewById(R.id.mainTabProfileText);

        //添加监听
        mTabIndex.setOnClickListener(this);
        mTabTrade.setOnClickListener(this);
        mTabRecommend.setOnClickListener(this);
        mTabAccount.setOnClickListener(this);
        mTabProfile.setOnClickListener(this);

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
                // 当点击了指数tab时，改变控件的文字颜色
                mTabIndex.setBackgroundResource(R.color.mi8YellowLight);
                //TODO:字体设置为白色
                mTabIndexText.setTextColor(0xff000000);
                if (mFrIndex == null) {
                    mFrIndex = new IndexFragment();
                    transaction.add(R.id.mainContent, mFrIndex);
                } else {
                    transaction.show(mFrIndex);
                }

                break;
            case 1:
                // 当点击了交易tab时，改变控件的文字颜色
                mTabTrade.setBackgroundResource(R.color.mi8YellowLight);
                //字体设置为黑色
                mTabTradeText.setTextColor(0xff000000);
                //字体设置为黑色
                if (mFrTrade == null) {
                    mFrTrade = new TradeFragment();
                    transaction.add(R.id.mainContent, mFrTrade);
                } else {
                    transaction.show(mFrTrade);
                }

                break;
            case 2:
                // 当点击了推荐tab时，改变控件的文字颜色
                mTabRecommend.setBackgroundResource(R.color.mi8YellowLight);
                mTabRecommendText.setTextColor(0xff000000);
                //字体设置为黑色
                if (mFrRecommend == null) {
                    mFrRecommend = new RecommendFragment();
                    transaction.add(R.id.mainContent, mFrRecommend);
                } else {
                    transaction.show(mFrRecommend);
                }

                break;
            case 3:
                // 当点击了账户tab时，改变控件的文字颜色
                mTabAccount.setBackgroundResource(R.color.mi8YellowLight);
                //字体设置为黑色
                mTabAccountText.setTextColor(0xff000000);

                if (mFrAccount == null) {
                    mFrAccount = new AccountFragment();
                    transaction.add(R.id.mainContent, mFrAccount);
                } else {
                    transaction.show(mFrAccount);
                }

                break;
            case 4:
                // 当点击了我的tab时，改变控件的文字颜色
                mTabProfile.setBackgroundResource(R.color.mi8YellowLight);
                //字体设置为黑色
                mTabProfileText.setTextColor(0xff000000);

                if (mFrProfile == null) {
                    mFrProfile = new ProfileFragment();
                    transaction.add(R.id.mainContent, mFrProfile);
                } else {
                    transaction.show(mFrProfile);
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
        mTabIndex.setBackgroundResource(R.color.mi8BlueLight);
        mTabTrade.setBackgroundResource(R.color.mi8BlueLight);
        mTabRecommend.setBackgroundResource(R.color.mi8BlueLight);
        mTabAccount.setBackgroundResource(R.color.mi8BlueLight);
        mTabProfile.setBackgroundResource(R.color.mi8BlueLight);


        //文字颜色改为白色
        mTabIndexText.setTextColor(0xffffffff);
        mTabTradeText.setTextColor(0xffffffff);
        mTabRecommendText.setTextColor(0xffffffff);
        mTabAccountText.setTextColor(0xffffffff);
        mTabProfileText.setTextColor(0xffffffff);

    }


    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction 用于对Fragment执行操作的事务
     */
    public void hideFragments(FragmentTransaction transaction) {
        if (mFrIndex != null) {
            transaction.hide(mFrIndex);
        }
        if (mFrTrade != null) {
            transaction.hide(mFrTrade);
        }


        if (mFrRecommend != null) {
            transaction.hide(mFrRecommend);
        }


        if (mFrAccount != null) {
            transaction.hide(mFrAccount);
        }
        if (mFrProfile != null) {
            transaction.hide(mFrProfile);
        }
    }

}
