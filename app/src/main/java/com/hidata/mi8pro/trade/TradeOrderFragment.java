package com.hidata.mi8pro.trade;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.hidata.mi8pro.Index.IndexFragment;
import com.hidata.mi8pro.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by k_way on 2017/5/10.
 */

public class TradeOrderFragment extends Fragment {


    private Context mContext;

    private ListView mCurListView;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_trade_order, container, false);
        mCurListView = (ListView) view.findViewById(R.id.tradeCurList);
        initCurList();
        return view;
    }


    public void initCurList() {

        List<Map<String, Object>> listems = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 1; i++) {
            Map<String, Object> listem = new HashMap<String, Object>();
            listem.put("orderTypeDesc", "沪市指数");
            listem.put("exDate", "0930-0935");
            listem.put("orderNo", "S12323k23");
            listem.put("direction", "看跌");
            listem.put("times", "20");
            listem.put("startIndex", "3210.55");
            listem.put("curIndex", "3211.55");
            listem.put("differ", "21.00");
            listem.put("profit", "32.32");
            listems.add(listem);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(mContext, listems, R.layout.item_trade_list_item, new String[]{
                "orderTypeDesc",
                "exDate",
                "orderNo",
                "direction",
                "times",
                "startIndex",
                "curIndex",
                "differ",
                "profit"

        }, new int[]{R.id.orderTypeDesc, R.id.exDate, R.id.orderNo, R.id.direction, R.id.times, R.id.startIndex, R.id.curIndex, R.id.differ, R.id.profit});

        mCurListView.setAdapter(simpleAdapter);
    }


}
