package com.hidata.mi8pro.account;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hidata.mi8pro.R;

/**
 * Created by k_way on 2017/5/14.
 */

public class AccountEnchashmentFragment extends Fragment {


    private Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_account_enchashment, container, false);
        return view;
    }


}
