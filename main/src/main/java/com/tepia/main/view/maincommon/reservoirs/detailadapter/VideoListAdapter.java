package com.tepia.main.view.maincommon.reservoirs.detailadapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.main.R;
import com.tepia.main.model.map.VideoResponse;
import com.tepia.main.view.main.detail.vedio.VideoInfo;

import java.util.List;


/**
 * copy from鸭溪水务app
 * @author ly
 * @date 2018/8/1
 */
public class VideoListAdapter extends BaseQuickAdapter<VideoResponse.DataBean, BaseViewHolder> {

    public String getFileServerUrl() {
        return fileServerUrl;
    }

    private String fileServerUrl;

    public VideoListAdapter(Context context, int layoutResId, @Nullable List<VideoResponse.DataBean> data) {
        super(layoutResId, data);
        this.mContext = context;
    }


    public void setFileServerUrl(String fileServerUrl) {
        this.fileServerUrl = fileServerUrl;
    }

    @Override
    protected void convert(BaseViewHolder view, VideoResponse.DataBean item) {
        view.setText(R.id.tv_video_channelname, item.getVsnm());
        view.setText(R.id.nameOfreservoirTv, item.getAddress());

    }
}



