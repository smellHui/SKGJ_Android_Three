package com.tepia.main.view.maincommon.setting.train;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.base.utils.ToastUtils;
import com.tepia.main.R;
import com.tepia.main.model.train.TrainContract;
import com.tepia.main.model.train.TrainDetailResponse;
import com.tepia.main.model.train.TrainListResponse;
import com.tepia.main.model.train.TrainPresenter;
import com.tepia.main.view.maincommon.reservoirs.detail.OperationPlanActivity;
import com.tepia.main.view.maincommon.setting.adapter.MyFIleListAdapter;
import com.tepia.main.view.mainworker.report.EmergenceSearchDetailActivity;
import com.tepia.main.view.mainworker.report.adapter.ImageShowAdapter;
import com.tepia.photo_picker.PhotoPreview;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by      Android studio
 *
 * @author :wwj (from Center Of Wuhan)
 * Date    :2018-9-30
 * Version :1.0
 * 功能描述 : 培训详情页面展示
 **/
public class TrainDetailActivity extends BaseActivity {
    private ArrayList<String> images = new ArrayList<>();
    private ImageShowAdapter imageShowAdapter;
    private TextView tvTrainTitle;
    private RecyclerView peixunRec;
    private TextView tvTrainDate;
    private TextView tvTrainPosition;
    private TextView tvTrainOrganizecompany;
    private TextView tvTrainContent;
    private TrainPresenter trainPresenter;
    private RecyclerView rvFilePick;
    private List<TrainDetailResponse.DataBean.FilesBean> filesBeanList = new ArrayList<>();
    private MyFIleListAdapter myFIleListAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_train;
    }

    @Override
    public void initView() {
        setCenterTitle("培训详情");
        showBack();
        Intent intent = getIntent();
        TrainListResponse.DataBean.ListBean item = (TrainListResponse.DataBean.ListBean)intent.getSerializableExtra("item");
        tvTrainTitle = findViewById(R.id.tv_train_title);
        peixunRec = findViewById(R.id.peixunRec);
        tvTrainDate = findViewById(R.id.tv_train_date);
        tvTrainPosition = findViewById(R.id.tv_train_position);
        tvTrainOrganizecompany = findViewById(R.id.tv_train_organizeCompany);
        tvTrainContent = findViewById(R.id.tv_train_content);
        rvFilePick = findViewById(R.id.rv_file_pick);
        if (item!=null){
            initDetail(item);
        }
        initRecycle();
    }

    private void initRecycle() {
        //创建LinearLayoutManager
        LinearLayoutManager manager = new LinearLayoutManager(this);
        //设置为横向滑动
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //设置
        peixunRec.setLayoutManager(manager);
        imageShowAdapter = new ImageShowAdapter(this,R.layout.activity_pei_xun_item,images);
        peixunRec.setAdapter(imageShowAdapter);
        //设置附件详情
        rvFilePick.setLayoutManager(new LinearLayoutManager(this));
        myFIleListAdapter = new MyFIleListAdapter(R.layout.common_office_layout, filesBeanList);
        rvFilePick.setAdapter(myFIleListAdapter);
        myFIleListAdapter.setOnPreviewTvClickListener((v, adapterPosition, item) -> {
            Intent intent = new Intent();
            intent.setClass(TrainDetailActivity.this, OperationPlanActivity.class);
            intent.putExtra("select",OperationPlanActivity.value_preview);
            intent.putExtra(OperationPlanActivity.PREVIEW_PATH,item.getFilePath());
            startActivity(intent);
        });
    }

    private void initDetail(TrainListResponse.DataBean.ListBean item) {
        tvTrainTitle.setText(item.getTrainTitle());
        tvTrainDate.setText("培训时间:"+item.getTrainDate());
        tvTrainPosition.setText("培训地点:"+item.getPosition());
        tvTrainOrganizecompany.setText("培训组织:"+item.getOrganizeCompany());
        tvTrainContent.setText(item.getTrainContent());
        trainPresenter.getTrainDetail(item.getId());
    }

    @Override
    public void initData() {
        trainPresenter = new TrainPresenter();
        trainPresenter.attachView(new TrainContract.View<TrainDetailResponse>() {
            @Override
            public void success(TrainDetailResponse trainDetailResponse) {
                TrainDetailResponse.DataBean data = trainDetailResponse.getData();
                if (data!=null){
                    //刷新图片
                    List<TrainDetailResponse.DataBean.ImagesBean> imageFiles = data.getImages();
                    if (imageFiles!=null&&imageFiles.size()>0){
                        images.clear();
                        for (int i = 0; i < imageFiles.size(); i++) {
                            images.add(imageFiles.get(i).getFilePath());
                        }
                        imageShowAdapter.notifyDataSetChanged();
                        imageShowAdapter.setOnItemClickListener((adapter, view, position) -> PhotoPreview.builder()
                                .setPhotos(images)
                                .setShowDeleteButton(false)
                                .setCurrentItem(position)
                                .start(TrainDetailActivity.this));
                    }
                    //刷新附件
                    List<TrainDetailResponse.DataBean.FilesBean> files1 = data.getFiles();
                    if (files1!=null&&files1.size()>0){
                        filesBeanList.clear();
                        for (int i = 0; i < files1.size(); i++) {
                            filesBeanList.add(files1.get(i));
                        }
                        myFIleListAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void failure(String msg) {
                ToastUtils.shortToast(msg);
            }

            @Override
            public Context getContext() {
                return null;
            }
        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initRequestData() {

    }
}
