package com.hidata.mi8pro.recommend;

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
import com.hidata.mi8pro.trade.TradeOrderFragment;
import com.hidata.mi8pro.trade.TradeOrderHistFragment;

/**
 * Created by k_way on 2017/5/10.
 */

public class RecommendFragment extends Fragment implements View.OnClickListener {


    private View mCommisionTabBtn;
    private View mMemberTabBtn;
    private TextView mCommisionTabBtnText;
    private TextView mMemberTabBtnText;
    private Fragment mCommisionFragment;
    private Fragment mMemberFragment;
    private FragmentManager mFragmentManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_recommend,container,false);
        initViews(view);
        mFragmentManager = getFragmentManager();
        setTabSelected(0);
        return view;
    }





    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.commisionTabBtn:
                setTabSelected(0);
                break;
            case R.id.memberTabBtn:
                setTabSelected(1);
                break;

        }

    }


    public void initViews(View rootView) {
        //Tab按钮
        mCommisionTabBtn = rootView.findViewById(R.id.commisionTabBtn);
        mMemberTabBtn = rootView.findViewById(R.id.memberTabBtn);


        //Tab按钮文字
        mCommisionTabBtnText = (TextView) rootView.findViewById(R.id.commisionTabBtnText);
        mMemberTabBtnText = (TextView) rootView.findViewById(R.id.memberTabBtnText);
        //添加监听
        mCommisionTabBtn.setOnClickListener(this);
        mMemberTabBtn.setOnClickListener(this);
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
                mCommisionTabBtn.setBackgroundResource(R.color.mi8YellowLight);
                //TODO:字体设置为白色
                mCommisionTabBtnText.setTextColor(0xff000000);
                if (mCommisionFragment == null) {
                    mCommisionFragment = new RecommendCommisionFragment();
                    transaction.add(R.id.recommendContent, mCommisionFragment);
                } else {
                    transaction.show(mCommisionFragment);
                }

                break;
            case 1:
                // 当点击了交易tab时，改变控件的文字颜色
                mMemberTabBtn.setBackgroundResource(R.color.mi8YellowLight);
                //字体设置为黑色
                mMemberTabBtnText.setTextColor(0xff000000);
                //字体设置为黑色
                if (mMemberFragment == null) {
                    mMemberFragment = new RecommendMemberFragment();
                    transaction.add(R.id.recommendContent, mMemberFragment);
                } else {
                    transaction.show(mMemberFragment);
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
        mCommisionTabBtn.setBackgroundResource(R.color.mi8BlueLight);
        mMemberTabBtn.setBackgroundResource(R.color.mi8BlueLight);


        //文字颜色改为白色
        mCommisionTabBtnText.setTextColor(0xffffffff);
        mMemberTabBtnText.setTextColor(0xffffffff);

    }


    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction 用于对Fragment执行操作的事务
     */
    public void hideFragments(FragmentTransaction transaction) {
        if (mCommisionFragment != null) {
            transaction.hide(mCommisionFragment);
        }
        if (mMemberFragment != null) {
            transaction.hide(mMemberFragment);
        }

    }

}
