package com.tepia.main.view.maincommon.setting.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.base.utils.Utils;
import com.tepia.main.R;

import java.util.List;
import java.util.Map;

/**
  * Created by      Android studio
  *
  * @author :ly (from Center Of Wuhan)
  * Date    :2018-9-28
  * Version :1.0
  * 功能描述 :工作职责页面适配器
 **/
public class DutyJobAdapter extends BaseQuickAdapter<Map<String,String>,BaseViewHolder> {
    private Context context;
    public DutyJobAdapter(Context context, int layoutResId, @Nullable List<Map<String,String>> data) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder view, Map<String,String> item) {
        TextView textView = view.getView(R.id.reserviorJobNameTv);
        ImageView ivIv = view.getView(R.id.ivIv);
        LinearLayout bgLy = view.getView(R.id.bgLy);
        textView.setText(item.get("jobName").toString());
        if("1".equals(item.get("check"))){
            bgLy.setBackground(ContextCompat.getDrawable(context,R.drawable.selector_dutytextview_blue_bg));
            textView.setTextColor(ContextCompat.getColor(context,R.color.white));
            ivIv.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.duty_select));
        }else{
            bgLy.setBackground(ContextCompat.getDrawable(Utils.getContext(),R.drawable.selector_dutytextview_white_bg));
            textView.setTextColor(ContextCompat.getColor(context,R.color.colorAccent));
            ivIv.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.duty_unselect));


        }
    }
}
