package com.tepia.main.utils;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.tepia.base.utils.Utils;
import com.tepia.main.R;

/**
 *
 * @author liying
 * @date 2018/7/26
 */

public class EmptyLayoutUtil {
    public static View show(String msg){
        View view = LayoutInflater.from(Utils.getContext()).inflate(R.layout.view_empty_list_view,null);
        TextView tv_empty_view_text = view.findViewById(R.id.tv_empty_view_text);
        tv_empty_view_text.setGravity(Gravity.CENTER);
        tv_empty_view_text.setText(msg);
        return view;
    }
}
