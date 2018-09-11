package com.tepia.main.view.main.detail.liuliangzhanandrainfull;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.main.APPCostant;
import com.tepia.main.R;
import com.tepia.main.view.main.detail.imageshow.ImageFragment;
import com.tepia.photo_picker.PhotoPreview;

import java.util.ArrayList;
import java.util.List;

/**
 * 监测信息--水质站等数据结构相同的站点详细信息适配器
 *
 * @author ly
 * @date 2018/4/17
 */

public class AdapterStationDetailList extends BaseQuickAdapter<StationDetailResponse, BaseViewHolder> {

    private BaseActivity activity;
    private Fragment fragment;
    public AdapterStationDetailList(Context context, int layoutResId, @Nullable List<StationDetailResponse> data) {
        super(layoutResId, data);
        this.mContext = context;
    }

    public void setActivtyandFragment(BaseActivity activity, Fragment fragment) {
        this.activity = activity;
        this.fragment = fragment;
    }

    private  ImageView showIconIv;
    @Override
    protected void convert(BaseViewHolder view, StationDetailResponse item) {

        showIconIv = view.getView(R.id.showIconIv);
        view.setText(R.id.titleTv,item.getTitleLeft());
        String imgPath = item.getImageIvPath();
        if(TextUtils.isEmpty(imgPath)) {
            view.setText(R.id.rightTextV, item.getTitleRight());
            showIconIv.setVisibility(View.GONE);
        }else {
            showIconIv.setVisibility(View.VISIBLE);
            Glide.with(mContext).
                    load(imgPath)
                    .apply(APPCostant.options)
                    .thumbnail(0.5f)
                    .into(showIconIv);
            showIconIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ArrayList<String> list = new ArrayList<>();
                    list.add(imgPath);
                    PhotoPreview.builder()
                            .setPhotos(list)
                            .setShowDeleteButton(false)
                            .setCurrentItem(0)
                            .start(activity, fragment);
                }
            });
        }
    }
}
