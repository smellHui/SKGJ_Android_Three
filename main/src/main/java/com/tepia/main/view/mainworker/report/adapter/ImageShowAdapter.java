package com.tepia.main.view.mainworker.report.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.main.ConfigConsts;
import com.tepia.main.R;
import com.tepia.main.common.RoundImageView;
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
public class ImageShowAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public ImageShowAdapter(Context context, int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder view, String photopathstr) {
        RoundImageView imageView = view.getView(R.id.item_img);
        if (photopathstr.contains("http://") || photopathstr.contains("https://")) {
            Glide.with(mContext)
                    .load(photopathstr)
                    .apply(ConfigConsts.options)
                    .into(imageView);

        }
    }
}
