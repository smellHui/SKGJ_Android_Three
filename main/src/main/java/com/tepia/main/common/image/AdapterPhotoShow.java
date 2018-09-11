package com.tepia.main.common.image;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.main.R;


import java.util.List;

/**
 * Created by Joeshould on 2018/3/30.
 */

public class AdapterPhotoShow extends BaseQuickAdapter<String, BaseViewHolder> {
    private final Context context;

    public AdapterPhotoShow(Context context, int layoutResId, List<String> data) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageView view = helper.getView(R.id.iv_image);
        Glide.with(context).
                load(item).
                thumbnail(0.5f)
                .apply(new RequestOptions()
                        .centerCrop()
                        .placeholder(R.drawable.icon_empty)
                        .error(R.drawable.icon_empty)
                        .priority(Priority.HIGH)
                        .diskCacheStrategy(DiskCacheStrategy.NONE))
                .into(view);
    }
}
