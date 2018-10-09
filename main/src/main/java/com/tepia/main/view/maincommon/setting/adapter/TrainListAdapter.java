package com.tepia.main.view.maincommon.setting.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.main.APPCostant;
import com.tepia.main.ConfigConsts;
import com.tepia.main.R;
import com.tepia.main.model.train.TrainListResponse;

import java.util.List;

/**
 * Created by      Intellij IDEA
 *
 * @author :       wwj
 * Date    :       2018-09-27
 * Time    :       16:23
 * Version :       1.0
 * Company :       北京太比雅科技(武汉研发中心)
 **/
public class TrainListAdapter extends BaseQuickAdapter<TrainListResponse.DataBean.ListBean,BaseViewHolder> {
    public TrainListAdapter(int layoutResId, @Nullable List<TrainListResponse.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TrainListResponse.DataBean.ListBean item) {
        TextView tvTitle = helper.getView(R.id.tv_title);
        TextView tvDate = helper.getView(R.id.tv_date);
        ImageView ivTrain = helper.getView(R.id.iv_train);
        tvTitle.setText(item.getTrainTitle());
        tvDate.setText(item.getTrainDate());
        List<TrainListResponse.DataBean.ListBean.FilesBean> files = item.getFiles();
        if (files!=null&&files.size()>0){
            Glide.with(mContext)
                    .load(files.get(0).getFilePath())
                    .apply(ConfigConsts.options)
                    .into(ivTrain);
        }else {
            ivTrain.setImageResource(R.mipmap.icon_empty);
        }
    }
}
