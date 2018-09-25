package com.tepia.main.view.maincommon.setting.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.main.APPCostant;
import com.tepia.main.ConfigConsts;
import com.tepia.main.R;
import com.tepia.main.common.RoundImageView;
import com.tepia.main.view.maincommon.reservoirs.MyReservoirsItemBean;

import java.util.List;

/**
 * 主页--水库--培训详情图片适配器
 *
 * @author ly
 * @date 2018/9/20
 */

public class AdapterTrainDetail extends BaseQuickAdapter<Integer, BaseViewHolder> {

    public AdapterTrainDetail(Context context, int layoutResId, @Nullable List<Integer> data) {
        super(layoutResId, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder view, Integer item) {
        RoundImageView imageView = view.getView(R.id.item_img);
        /*if (photopathstr.contains("http://") || photopathstr.contains("https://")) {
            Glide.with(mContext)
                    .load(photopathstr)
                    .apply(APPCostant.options)
                    .into(imageView);

        }*/
        Glide.with(mContext)
                .load(item)
                .apply(ConfigConsts.options)
                .into(imageView);
    }
}
