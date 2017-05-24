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

/**
 * Created by k_way on 2017/5/10.
 */

public class TradeOrderHistFragment extends Fragment {





    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_trade_order_his,container,false);
        return view;
    }






}
