package com.tepia.main.view.maintechnology.threekeypoint.adapter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.main.R;
import com.tepia.main.model.reserviros.OperationPlanBean;
import com.tepia.main.view.maincommon.reservoirs.detail.OperationPlanActivity;

import java.util.List;

/**
 * Created by      Intellij IDEA
 *
 * @author :       wwj
 * Date    :       2018-09-27
 * Time    :       11:17
 * Version :       1.0
 * Company :       北京太比雅科技(武汉研发中心)
 **/
public class MyTabListAdapter extends BaseQuickAdapter<OperationPlanBean.DataBean,BaseViewHolder>{
    public MyTabListAdapter(int layoutResId, @Nullable List<OperationPlanBean.DataBean> data) {
        super(layoutResId, data);
    }

    public interface OnPreviewTvClickListener{
        void onPreviewTvClick(View v, int adapterPosition, OperationPlanBean.DataBean item);
    }

    private OnPreviewTvClickListener  onPreviewTvClickListener = null;

    public void setOnPreviewTvClickListener(OnPreviewTvClickListener onPreviewTvClickListener) {
        this.onPreviewTvClickListener = onPreviewTvClickListener;
    }

    @Override
    protected void convert(BaseViewHolder helper, OperationPlanBean.DataBean item) {
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
                helper.setImageResource(R.id.officeIv,R.drawable.jianjie_ppt);
            }
        }
        helper.getView(R.id.officeDownloadTv).setOnClickListener(v -> {

        });
        helper.getView(R.id.officePreviewTv).setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(mContext, OperationPlanActivity.class);
            intent.putExtra("select",OperationPlanActivity.value_preview);
            intent.putExtra(OperationPlanActivity.PREVIEW_PATH,item.getFilePath());
            mContext.startActivity(intent);
            onPreviewTvClickListener.onPreviewTvClick(v,adapterPosition,item);
        });

    }
}
