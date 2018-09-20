package com.tepia.main.view.mainworker.shangbao;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tepia.main.R;

/**
  * Created by      Android studio
  *
  * @author :ly (from Center Of Wuhan)
  * Date    : 2018-9-20
  * Version :1.0
  * 功能描述 :水位
 **/
public class ShuiweiFragment extends Fragment {


    public ShuiweiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shuiwei, container, false);
    }

}
