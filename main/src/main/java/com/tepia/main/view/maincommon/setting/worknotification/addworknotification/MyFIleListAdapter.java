package com.tepia.main.view.maincommon.setting.worknotification.addworknotification;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.main.R;
import com.tepia.main.model.image.ImageInfoBean;
import com.tepia.main.model.train.TrainDetailResponse;
import com.tepia.main.view.maincommon.setting.DownLoadActivity;

import java.util.List;

/**
 * Created by      Intellij IDEA
 *
 * @author :       wwj
 * Date    :       2018-10-08
 * Time    :       19:51
 * Version :       1.0
 * Company :       北京太比雅科技(武汉研发中心)
 **/
public class MyFIleListAdapter extends BaseQuickAdapter<ImageInfoBean,BaseViewHolder>{
    public MyFIleListAdapter(int layoutResId, @Nullable List<ImageInfoBean> data) {
        super(layoutResId, data);
    }

    public interface OnPreviewTvClickListener{
        void onPreviewTvClick(View v, int adapterPosition, ImageInfoBean item);
    }

    private OnPreviewTvClickListener onPreviewTvClickListener = null;

    public void setOnPreviewTvClickListener(OnPreviewTvClickListener onPreviewTvClickListener) {
        this.onPreviewTvClickListener = onPreviewTvClickListener;
    }


    @Override
    protected void convert(BaseViewHolder helper, ImageInfoBean item) {
        helper.setText(R.id.officeTitleTv,item.getFileName());
        int adapterPosition = helper.getAdapterPosition();
        String filepath = item.getFilePath();
        if(!TextUtils.isEmpty(filepath)){
            if (filepath.endsWith(".doc") || filepath.endsWith(".docx")) {
                helper.setImageResource(R.id.officeIv,R.drawable.jianjie_word);
            }else if (filepath.endsWith(".xls") || filepath.endsWith(".xlsx")) {
                helper.setImageResource(R.id.officeIv,R.drawable.jianjie_excel);
            }else if (filepath.endsWith(".ppt") || filepath.endsWith(".pptx")) {
                helper.setImageResource(R.id.officeIv,R.drawable.jianjie_ppt);
            }else if (filepath.endsWith(".pdf") ) {
                helper.setImageResource(R.id.officeIv,R.drawable.jianjie_pdf);
            }else if(filepath.endsWith(".txt")){
                helper.setImageResource(R.id.officeIv,R.drawable.jianjie_txt);

            }
        }
        helper.getView(R.id.officeDownloadTv).setOnClickListener(v -> {
            Intent intent = new Intent(mContext, DownLoadActivity.class);
//            DownLoadActivity.setIntent(intent,"ggsg8.apk","http://rs.0.gaoshouyou.com/d/04/1e/400423a7551e1f3f0eb1812afa1f9b44.apk");
//            DownLoadActivity.setIntent(intent,"CloudMusic_official_5.5.2.826166.apk","http://d1.music.126.net/dmusic/CloudMusic_official_5.5.2.826166.apk");
            DownLoadActivity.setIntent(intent,item.getFileName(),item.getFilePath());
            mContext.startActivity(intent);
        });
        helper.getView(R.id.officePreviewTv).setOnClickListener(v -> {
//            Intent intent = new Intent();
//            intent.setClass(mContext, OperationPlanActivity.class);
//            intent.putExtra("select",OperationPlanActivity.value_preview);
//            intent.putExtra(OperationPlanActivity.PREVIEW_PATH,item.getFilePath());
//            mContext.startActivity(intent);
            onPreviewTvClickListener.onPreviewTvClick(v,adapterPosition,item);
        });
    }

}
