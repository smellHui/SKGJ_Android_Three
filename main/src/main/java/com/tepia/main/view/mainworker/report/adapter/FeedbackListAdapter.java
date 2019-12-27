package com.tepia.main.view.mainworker.report.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.base.view.floatview.CollectionsUtil;
import com.tepia.main.R;
import com.tepia.main.common.feedback.SpacesItemDecoration;
import com.tepia.main.model.jishu.feedback.FeedbackDetailResponse;
import com.tepia.main.model.jishu.feedback.FileFile;
import com.tepia.main.model.jishu.feedback.ImgFile;
import com.tepia.main.model.train.TrainDetailResponse;
import com.tepia.main.view.maincommon.reservoirs.detail.OperationPlanActivity;
import com.tepia.main.view.maincommon.setting.adapter.MyFIleListAdapter;
import com.tepia.photo_picker.PhotoPreview;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:xch
 * Date:2019/12/27
 * Description:
 */
public class FeedbackListAdapter extends BaseQuickAdapter<FeedbackDetailResponse, BaseViewHolder> {


    public FeedbackListAdapter() {
        super(R.layout.view_feed_back);
    }

    @Override
    protected void convert(BaseViewHolder helper, FeedbackDetailResponse item) {
        helper.setText(R.id.tv_feedback_time, item.getFeedbackTime());
        helper.setText(R.id.tv_feedback_type, item.getFeedbackTypeName());
        helper.setText(R.id.tv_user_name, item.getUserName());
        helper.setText(R.id.tv_feedback_des, String.format("反馈意见：%s", item.getFeedbackContent()));
        List<ImgFile> imgFiles = item.getImgFile();
        ArrayList<String> imgs = new ArrayList<>();
        if (CollectionsUtil.isNotEmpty(imgFiles)) {
            for (ImgFile imgPath : imgFiles) {
                imgs.add(imgPath.getFilePath());
            }
        }
        ImageView typeImg = helper.getView(R.id.img_status);
        String feedbackType = item.getFeedbackType();
        if (feedbackType.equals("1")) {
            typeImg.setImageResource(R.mipmap.ic_leader);
        } else if (feedbackType.equals("9")) {
            typeImg.setImageResource(R.mipmap.ic_finish);
        } else {
            typeImg.setImageResource(R.mipmap.ic_feedback);
        }
        RecyclerView imgRv = helper.getView(R.id.rv_feed_back_img);
        RecyclerView fileRv = helper.getView(R.id.rv_feed_back_file);
        initRecycle(fileRv, item.getFileFile());
        imgRv.setLayoutManager(getLinearLayoutManager());
        imgRv.addItemDecoration(new SpacesItemDecoration(10));
        ImageFeedbackAdapter imageShowAdapter = new ImageFeedbackAdapter(imgFiles);
        imageShowAdapter.setOnItemClickListener((adapter, view, position) -> PhotoPreview.builder()
                .setPhotos(imgs)
                .setShowDeleteButton(false)
                .setCurrentItem(position)
                .start((Activity) mContext));
        imgRv.setAdapter(imageShowAdapter);
    }

    private LinearLayoutManager getLinearLayoutManager() {
        //创建LinearLayoutManager
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 4) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        return gridLayoutManager;
    }

    private void initRecycle(RecyclerView rv, List<FileFile> filesBeanList) {
        //创建LinearLayoutManager
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        //设置为横向滑动
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //设置附件详情
        rv.setLayoutManager(new LinearLayoutManager(mContext));
        List<TrainDetailResponse.DataBean.FilesBean> filesBeans = new ArrayList<>();
        if (CollectionsUtil.isNotEmpty(filesBeanList)) {
            for (FileFile fileFile : filesBeanList) {
                TrainDetailResponse.DataBean.FilesBean filesBean = new TrainDetailResponse.DataBean.FilesBean();
                filesBean.setBizType(fileFile.getBizType());
                filesBean.setFileId(fileFile.getId());
                filesBean.setFileName(fileFile.getFileName());
                filesBean.setFilePath(fileFile.getFilePath());
                filesBeans.add(filesBean);
            }
        }
        MyFIleListAdapter myFIleListAdapter = new MyFIleListAdapter(R.layout.common_office_layout, filesBeans);
        rv.setAdapter(myFIleListAdapter);
        myFIleListAdapter.setOnPreviewTvClickListener((v, adapterPosition, item) -> {
            Intent intent = new Intent();
            intent.setClass(mContext, OperationPlanActivity.class);
            intent.putExtra("select", OperationPlanActivity.value_preview);
            intent.putExtra(OperationPlanActivity.PREVIEW_PATH, item.getFilePath());
            mContext.startActivity(intent);
        });
    }
}
