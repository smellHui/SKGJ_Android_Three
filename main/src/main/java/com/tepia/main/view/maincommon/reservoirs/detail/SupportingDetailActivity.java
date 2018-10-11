package com.tepia.main.view.maincommon.reservoirs.detail;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.tepia.base.mvp.BaseActivity;
import com.tepia.main.R;
import com.tepia.main.common.pickview.RecyclerItemClickListener;
import com.tepia.main.databinding.ActivitySupportingDetailBinding;
import com.tepia.main.model.dictmap.DictMapEntity;
import com.tepia.main.model.dictmap.DictMapManager;
import com.tepia.main.model.reserviros.SupportingBean;
import com.tepia.main.view.main.question.problemlist.PhotoAdapter;
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
    @Override
    public int getLayoutId() {
        return R.layout.activity_supporting_detail;
    }

    @Override
    public void initView() {
        setCenterTitle("配套设施详情");
        showBack();
        binding = DataBindingUtil.bind(mRootView);
        Bundle bundle = getIntent().getExtras();
        SupportingBean.DataBean dataBean = (SupportingBean.DataBean) bundle.getSerializable("supportingid");
        binding.nameTv.setText(dataBean.getDeName());
        binding.functionTv.setText(dataBean.getDeFunction());
        DictMapEntity dictMapEntity = DictMapManager.getInstance().getmDictMap();
        Map<String, String> mapDetype = dictMapEntity.getObject().getDe_type();
        binding.typeTv.setText(mapDetype.get(dataBean.getDeType()));
        binding.positionTv.setText(dataBean.getPosition());
        binding.timeTv.setText(dataBean.getBeginUseDate());
        binding.totalTv.setText(dataBean.getDeTotals());

        if (dataBean.getFileInfo() != null && dataBean.getFileInfo().size() > 0) {
            for (SupportingBean.DataBean.FileInfoBean fileInfoBean: dataBean.getFileInfo()){
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
        photoAdapter = new PhotoAdapter(this, R.layout.activity_problem_photo_layout,selectedPhotos);
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
}
