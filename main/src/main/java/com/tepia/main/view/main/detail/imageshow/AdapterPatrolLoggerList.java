package com.tepia.main.view.main.detail.imageshow;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.main.APPCostant;
import com.tepia.main.ConfigConsts;
import com.tepia.main.R;
import com.tepia.main.model.detai.ImageBean;

import java.util.List;

/**
 *
 * 图像站
 * @author liying
 * @date 2018/7/29
 */

public class AdapterPatrolLoggerList extends BaseQuickAdapter<ImageBean.DataBean.PicturesBean.ListBean, BaseViewHolder> {

    public String getFileServerUrl() {
        return fileServerUrl;
    }

    private String fileServerUrl;
    public AdapterPatrolLoggerList(Context context, int layoutResId, @Nullable List<ImageBean.DataBean.PicturesBean.ListBean> data) {
        super(layoutResId, data);
        this.mContext = context;
    }


    public void setFileServerUrl(String fileServerUrl) {
        this.fileServerUrl = fileServerUrl;
    }

    @Override
    protected void convert(BaseViewHolder view, ImageBean.DataBean.PicturesBean.ListBean item) {
        ImageView imageView = view.getView(R.id.imageIv);
        view.setText(R.id.serialTv,view.getAdapterPosition()+1+"");
        //上传时间
        view.setText(R.id.tmtimeTv,"上传时间："+ item.getTm());
        //拍摄时间
        view.setText(R.id.pictimeTv,"拍摄时间："+ item.getPictm());

        String path  = item.getPicpath();
        Glide.with(mContext).
                load(fileServerUrl  + path).
                thumbnail(0.5f)
                .apply(ConfigConsts.options)
                .into(imageView);

    }
}
