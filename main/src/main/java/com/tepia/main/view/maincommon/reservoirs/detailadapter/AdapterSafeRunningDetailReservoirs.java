package com.tepia.main.view.maincommon.reservoirs.detailadapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.base.utils.ToastUtils;
import com.tepia.main.R;
import com.tepia.main.model.reserviros.BizkeyBean;
import com.tepia.main.model.reserviros.FloodBean;
import com.tepia.main.view.maincommon.reservoirs.detail.OperationPlanActivity;
import com.tepia.main.view.maincommon.setting.DownLoadActivity;

import java.util.List;

/**
 * 主页--水库--安全运行详情附件适配器
 *
 * @author ly
 * @date 2018/9/27
 */

public class AdapterSafeRunningDetailReservoirs extends BaseQuickAdapter<BizkeyBean.DataBean, BaseViewHolder> {

    public AdapterSafeRunningDetailReservoirs(Context context, int layoutResId, @Nullable List<BizkeyBean.DataBean> data) {
        super(layoutResId, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder view, BizkeyBean.DataBean item) {

        view.setText(R.id.officeTitleTv,item.getFileName());
        String filepath = item.getFilePath();
        if(!TextUtils.isEmpty(filepath)){
            if (filepath.endsWith(".doc") || filepath.endsWith(".docx")) {
                view.setImageResource(R.id.officeIv,R.drawable.jianjie_word);
            }else if (filepath.endsWith(".xls") || filepath.endsWith(".xlsx")) {
                view.setImageResource(R.id.officeIv,R.drawable.jianjie_excel);
            }else if (filepath.endsWith(".ppt") || filepath.endsWith(".pptx")) {
                view.setImageResource(R.id.officeIv,R.drawable.jianjie_ppt);
            }else if (filepath.endsWith(".pdf") ) {
                view.setImageResource(R.id.officeIv,R.drawable.jianjie_pdf);
            }else if(filepath.endsWith(".txt")){
                view.setImageResource(R.id.officeIv,R.drawable.jianjie_txt);

            }
        }
        view.getView(R.id.officeDownloadTv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DownLoadActivity.class);
//            DownLoadActivity.setIntent(intent,"ggsg8.apk","http://rs.0.gaoshouyou.com/d/04/1e/400423a7551e1f3f0eb1812afa1f9b44.apk");
//            DownLoadActivity.setIntent(intent,"CloudMusic_official_5.5.2.826166.apk","http://d1.music.126.net/dmusic/CloudMusic_official_5.5.2.826166.apk");
                DownLoadActivity.setIntent(intent,item.getFileName(),item.getFilePath());
                mContext.startActivity(intent);
            }
        });
        view.getView(R.id.officePreviewTv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(mContext, OperationPlanActivity.class);
                intent.putExtra("select",OperationPlanActivity.value_preview);
                intent.putExtra(OperationPlanActivity.PREVIEW_PATH,item.getFilePath());
                mContext.startActivity(intent);
            }
        });

    }
}
