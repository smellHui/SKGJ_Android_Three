package com.tepia.main.view.mainworker.report.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.main.ConfigConsts;
import com.tepia.main.R;
import com.tepia.main.common.RoundImageView;
import com.tepia.main.model.jishu.feedback.ImgFile;

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
public class ImageFeedbackAdapter extends BaseQuickAdapter<ImgFile, BaseViewHolder> {

    public ImageFeedbackAdapter(List<ImgFile> data) {
        super(R.layout.feedback_item_photo, data);
    }

    @Override
    protected void convert(BaseViewHolder view, ImgFile imgFile) {
        ImageView imageView = view.getView(R.id.iv_photo);
        String filePath = imgFile.getFilePath();
        if (filePath.contains("http://") || filePath.contains("https://")) {
            Glide.with(mContext)
                    .load(filePath)
                    .apply(ConfigConsts.options)
                    .into(imageView);

        }
    }
}
