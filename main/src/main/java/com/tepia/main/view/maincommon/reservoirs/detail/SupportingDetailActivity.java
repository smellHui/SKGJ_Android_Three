package com.tepia.main.view.maincommon.reservoirs.detail;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.tepia.base.AppRoutePath;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.Utils;
import com.tepia.main.R;
import com.tepia.main.common.pickview.RecyclerItemClickListener;
import com.tepia.main.databinding.ActivitySupportingDetailBinding;
import com.tepia.main.model.dictmap.DictMapEntity;
import com.tepia.main.model.dictmap.DictMapManager;
import com.tepia.main.model.image.ImageInfoBean;
import com.tepia.main.model.reserviros.ReservirosManager;
import com.tepia.main.model.reserviros.SupportingBean;
import com.tepia.main.view.main.question.problemlist.PhotoAdapter;
import com.tepia.main.view.maincommon.setting.train.AddTrainActivity;
import com.tepia.main.view.maincommon.setting.train.TrainActivity;
import com.tepia.photo_picker.PhotoPreview;

import java.util.ArrayList;
import java.util.Map;

/**
  * Created by      Android studio
  *
  * @author :ly (from Center Of Wuhan)
  * Date    : 2018-9-25
  * Version :1.0
  * 功能描述 : 配套设施详情
 **/
public class SupportingDetailActivity extends BaseActivity {
    ActivitySupportingDetailBinding binding;
    private PhotoAdapter photoAdapter;
    private ArrayList<String> selectedPhotos = new ArrayList<>();
    private SupportingBean.DataBean dataBean;
    @Override
    public int getLayoutId() {
        return R.layout.activity_supporting_detail;
    }

    @Override
    public void initView() {
        setCenterTitle("配套设施详情");
        showBack();
        binding = DataBindingUtil.bind(mRootView);
        getRithtTv().setText("编辑");
        getRithtTv().setTextSize(16f);
        getRithtTv().setVisibility(View.VISIBLE);
        getRithtTv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogUtil.e("编辑");
                Bundle bundle = new Bundle();
                bundle.putSerializable("support_change",dataBean);
               /* ARouter.getInstance().build(AppRoutePath.app_support_change)
                        .withBundle("arout_support",bundle)
                        .navigation();*/
                Intent intent = new Intent(SupportingDetailActivity.this, SupportingChangeActivity.class);
                intent.putExtras(bundle);
                startActivityForResult(intent, 1);

            }
        });

        Bundle bundle = getIntent().getExtras();
        dataBean = (SupportingBean.DataBean) bundle.getSerializable("supportingid");
        show(dataBean);
    }

    private void show(SupportingBean.DataBean dataBean){
        binding.nameTv.setText(dataBean.getDeName());
        binding.functionTv.setText(dataBean.getDeFunction());
        DictMapEntity dictMapEntity = DictMapManager.getInstance().getmDictMap();
        Map<String, String> mapDetype = dictMapEntity.getObject().getDe_type();
        binding.typeTv.setText(mapDetype.get(dataBean.getDeType()));

        Map<String, String> mapDeStatus = dictMapEntity.getObject().getDe_status();
        binding.destatusTv.setText(mapDeStatus.get(dataBean.getDeStatus()));

        binding.positionTv.setText(dataBean.getPosition());
        binding.timeTv.setText(dataBean.getBeginUseDate());
        binding.totalTv.setText(dataBean.getDeTotals());
        selectedPhotos.clear();
        if (dataBean.getFileInfo() != null && dataBean.getFileInfo().size() > 0) {
            for (ImageInfoBean fileInfoBean: dataBean.getFileInfo()){
                if(fileInfoBean != null) {
                    selectedPhotos.add(fileInfoBean.getFilePath());
                }
            }
        }else{
            binding.imageLy.setVisibility(View.GONE);
        }

        initRec();
    }

    @Override
    public void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initRequestData() {

    }

    /**
     * 初始化recycleview
     */
    private void initRec(){
        if (photoAdapter == null) {
            photoAdapter = new PhotoAdapter(this, R.layout.activity_problem_photo_layout,selectedPhotos);
        }
        binding.bizRy.setLayoutManager(new GridLayoutManager(this,4));
        binding.bizRy.setAdapter(photoAdapter);
        binding.bizRy.addOnItemTouchListener(new RecyclerItemClickListener(this, (view, position) -> {
            PhotoPreview.builder()
                    .setPhotos(selectedPhotos)
                    .setCurrentItem(position)
                    .setShowDeleteButton(false)
                    .start(SupportingDetailActivity.this);

        }));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case 1:
                Bundle extras = data.getExtras();
                if (null != extras){
                   /* SupportingBean.DataBean dataBean = (SupportingBean.DataBean)extras.getSerializable("dataBean");
                   show(dataBean);*/
                   finish();
                }
                break;
                default:
        }
    }
}
