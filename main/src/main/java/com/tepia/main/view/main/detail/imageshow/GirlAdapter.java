package com.tepia.main.view.main.detail.imageshow;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tepia.main.APPCostant;
import com.tepia.main.ConfigConsts;
import com.tepia.main.R;
import com.tepia.main.model.detai.ImageBean;
import com.tepia.photo_picker.widget.TouchImageView;

import java.util.ArrayList;


/**
 * Created by oracleen on 2016/7/4.
 */
public class GirlAdapter extends PagerAdapter {

    private Context mContext;
    private ArrayList<ImageBean.DataBean.PicturesBean.ListBean> mDatas;
    private View mCurrentView;
    private SparseArray<View> cacheView;
    private String fileurl;

    public GirlAdapter(Context context, ArrayList<ImageBean.DataBean.PicturesBean.ListBean> datas,String fileurl) {
        mContext = context;
        mDatas = datas;
        cacheView = new SparseArray<>(datas.size());
        this.fileurl = fileurl;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        mCurrentView = (View) object;
    }

    public View getPrimaryItem() {
        return mCurrentView;
    }

    @Override
    public View instantiateItem(ViewGroup container, int position) {
        final String imageUrl = fileurl + mDatas.get(position).getPicpath();
        View view = cacheView.get(position);
        if (view == null) {

            view = LayoutInflater.from(this.mContext).inflate(R.layout.item_girl_detail, container, false);
            view.setTag(position);
            final ImageView imageView =  view.findViewById(R.id.img);
//        PhotoView imageView = new PhotoView(mContext);
//            PhotoView imageView = view.findViewById(R.id.img);

            Glide.with(mContext)
                    .load(imageUrl)
                    .thumbnail(0.5f)
                    .apply(ConfigConsts.options)
                    .into(imageView);
            cacheView.put(position, view);
        }

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}
