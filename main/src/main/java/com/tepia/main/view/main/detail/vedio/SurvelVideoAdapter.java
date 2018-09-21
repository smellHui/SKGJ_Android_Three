package com.tepia.main.view.main.detail.vedio;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.main.APPCostant;
import com.tepia.main.R;
import com.tepia.main.model.detai.ImageBean;

import java.util.List;


/**
 * copy from鸭溪水务app
 * @author ly
 * @date 2018/8/1
 */
public class SurvelVideoAdapter extends BaseQuickAdapter<VideoInfo, BaseViewHolder> {

    public String getFileServerUrl() {
        return fileServerUrl;
    }

    private String fileServerUrl;

    public SurvelVideoAdapter(Context context, int layoutResId, @Nullable List<VideoInfo> data) {
        super(layoutResId, data);
        this.mContext = context;
    }


    public void setFileServerUrl(String fileServerUrl) {
        this.fileServerUrl = fileServerUrl;
    }

    @Override
    protected void convert(BaseViewHolder view, VideoInfo item) {
        ImageView imageView = view.getView(R.id.imageIv);
        view.setText(R.id.tv_video_channelname, item.getChaName());

    }
}


