package com.hidata.mi8pro.Index;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hidata.mi8pro.ApiUrl;
import com.hidata.mi8pro.R;
import com.hidata.mi8pro.util.HttpGetThread;
import com.hidata.mi8pro.util.ResponseResult;
import com.hidata.mi8pro.widget.Mi8Alert;
import com.hidata.mi8pro.widget.Mi8OrderDialog;

/**
 * Created by k_way on 2017/5/10.
 */

public class IndexFragment extends Fragment {

    private static final int MSG_STK_INDEX = 0;
    private static final int MSG_BTC_INDEX = 1;


    private Handler mHandler;
    private Context mContext;
    private View rootView;
    private ObjectMapper mObjectMapper;

    //stk
    private View mStkHourLineBtn;
    private View mStkDayLineBtn;
    private View mStkOrderDownBtn;
    private View mStkOrderUpBtn;
    private Mi8Alert mHourLineDialog;
    private Mi8Alert mDayLineDialog;

    //stk TextView
    private TextView mStkIndexActiveView;
    private TextView mStkIndexUpDownView;


    //btc
    private View mBtcHourLineBtn;
    private View mBtcDayLineBtn;
    private View mBtcOrderDownBtn;
    private View mBtcOrderUpBtn;


    //stk TextView
    private TextView mBtcIndexActiveView;
    private TextView mBtcIndexUpDownView;


    //数据请求

    private HttpGetThread mStkIndexHttpGetThread;
    private HttpGetThread mBtcIndexHttpGetThread;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fr_index, container, false);

        return rootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mObjectMapper = new ObjectMapper();
        //STK
        mStkIndexActiveView = (TextView) rootView.findViewById(R.id.stkIndexActiveView);
        mStkIndexUpDownView = (TextView) rootView.findViewById(R.id.stkIndexUpDown);


        //STK button
        mStkHourLineBtn = rootView.findViewById(R.id.stkIndexHourLine);
        mStkDayLineBtn = rootView.findViewById(R.id.stkIndexDayLine);
        mStkOrderDownBtn = rootView.findViewById(R.id.stkIndexOrderDown);
        mStkOrderUpBtn = rootView.findViewById(R.id.stkIndexOrderUp);

        mStkHourLineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mi8Alert dialog = new Mi8Alert(mContext, "测试");
                dialog.show();
            }
        });

        mStkOrderDownBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mi8OrderDialog dialog = new Mi8OrderDialog(mContext, "看跌单");
                dialog.newDownStkOrder();
            }
        });

        mStkOrderUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mi8OrderDialog dialog = new Mi8OrderDialog(mContext, "看涨单");
                dialog.newUpStkOrder();
            }
        });



        //BTC相关
        //
        mBtcIndexActiveView = (TextView) rootView.findViewById(R.id.btcIndexActiveView);
        mBtcIndexUpDownView = (TextView) rootView.findViewById(R.id.btcIndexUpDown);


        //
        mBtcHourLineBtn = rootView.findViewById(R.id.btcIndexHourLine);
        mBtcDayLineBtn = rootView.findViewById(R.id.btcIndexDayLine);
        mBtcOrderDownBtn = rootView.findViewById(R.id.btcIndexOrderDown);
        mBtcOrderUpBtn = rootView.findViewById(R.id.btcIndexOrderUp);

        mBtcHourLineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mi8Alert dialog = new Mi8Alert(mContext, "测试");
                dialog.show();
            }
        });

        mBtcOrderDownBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mi8OrderDialog dialog = new Mi8OrderDialog(mContext, "看跌单");
                dialog.newDownStkOrder();
            }
        });

        mBtcOrderUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mi8OrderDialog dialog = new Mi8OrderDialog(mContext, "看涨单");
                dialog.newUpStkOrder();
            }
        });

        //信息处理
        mHandler = new Handler() {

            @Override
            public void handleMessage(Message msg) {

                Bundle data = msg.getData();
                ResponseResult<IndexSummary> result = null;
                switch (msg.what) {
                    case MSG_STK_INDEX:
                        result = (ResponseResult<IndexSummary>) data.getSerializable("data");
                        if(result!= null){
                            if(result.getData()!=null){
                                mStkIndexActiveView.setText(result.getData().getNowIndex());
                                mStkIndexUpDownView.setText(result.getData().getUpdown());
                            }
                        }
                        break;
                    case MSG_BTC_INDEX:
                        if(result!= null){
                            if(result.getData()!=null){
                                mBtcIndexActiveView.setText(result.getData().getNowIndex());
                                mBtcIndexUpDownView.setText(result.getData().getUpdown());
                            }
                        }
                    default:

                }
            }
        };


        mStkIndexHttpGetThread = new HttpGetThread(mHandler,ApiUrl.INDEX_STK,MSG_STK_INDEX,new TypeReference<ResponseResult<IndexSummary>>(){},true);
        mBtcIndexHttpGetThread = new HttpGetThread(mHandler,ApiUrl.INDEX_BTC,MSG_BTC_INDEX,new TypeReference<ResponseResult<IndexSummary>>(){},true);
        mStkIndexHttpGetThread.start();
        mBtcIndexHttpGetThread.start();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {

        super.onHiddenChanged(hidden);

        if (hidden) {
            mStkIndexHttpGetThread.go(false);
            mBtcIndexHttpGetThread.go(false);
        } else {
            mStkIndexHttpGetThread.go(true);
            mBtcIndexHttpGetThread.go(true);
        }

    }


}
