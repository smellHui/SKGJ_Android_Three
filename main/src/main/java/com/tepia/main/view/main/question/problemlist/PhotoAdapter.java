package com.tepia.main.view.main.question.problemlist;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.main.APPCostant;
import com.tepia.main.R;
import com.tepia.main.common.pickview.AndroidLifecycleUtils;
import com.tepia.main.view.main.detail.vedio.VideoInfo;

import java.util.List;


/**
 * 图片显示
 *
 * @author ly
 * @date 2018/8/1
 */
public class PhotoAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public PhotoAdapter(Context context, int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
        this.mContext = context;
    }


    @Override
    protected void convert(BaseViewHolder view, String photopathstr) {
        ImageView imageView = view.getView(R.id.imageIv);
        if (photopathstr.contains("http://") || photopathstr.contains("https://")) {
            Glide.with(mContext)
                    .load(photopathstr)
                    .apply(APPCostant.options)
                    .into(imageView);

        }

    }
}



