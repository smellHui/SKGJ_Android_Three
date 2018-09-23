package com.tepia.main.view.mainworker.report;

import android.content.Intent;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.main.R;
import com.tepia.main.common.pickview.OnItemClickListener;
import com.tepia.main.common.pickview.PhotoRecycleViewAdapter;
import com.tepia.main.common.pickview.RecyclerItemClickListener;
import com.tepia.photo_picker.PhotoPicker;
import com.tepia.photo_picker.PhotoPreview;

import java.util.ArrayList;
import java.util.List;

/**
  * Created by      Android studio
  *
  * @author :ly (from Center Of Wuhan)
  * Date    :2018-9-20
  * Version :1.0
  * 功能描述 : 应急上报页面
 **/
public class EmergencyDetailActivity extends MVPBaseActivity<ReportContract.View,ReportPresenter> {

    private ArrayList<String> selectedPhotos = new ArrayList<>();
    private PhotoRecycleViewAdapter photoAdapter;
    private RecyclerView rvImagePick;


    @Override
    public int getLayoutId() {
        return R.layout.activity_emergency_detail;
    }

    @Override
    public void initView() {
        setCenterTitle("应急上报");
        showBack();
        rvImagePick = findViewById(R.id.rvImagePick);
        photoAdapter = new PhotoRecycleViewAdapter(this, selectedPhotos);
        rvImagePick.setLayoutManager(new StaggeredGridLayoutManager(4, OrientationHelper.VERTICAL));
        rvImagePick.setAdapter(photoAdapter);
        rvImagePick.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                if (photoAdapter.getItemViewType(position) == PhotoRecycleViewAdapter.TYPE_ADD) {
                    PhotoPicker.builder()
                            .setPhotoCount(PhotoRecycleViewAdapter.MAX)
                            .setShowCamera(true)
                            .setPreviewEnabled(true)
                            .setSelected(selectedPhotos)
                            .start(EmergencyDetailActivity.this);
                } else {
                    PhotoPreview.builder()
                            .setPhotos(selectedPhotos)
                            .setCurrentItem(position)
                            .start(EmergencyDetailActivity.this);
                }

            }
        }));

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && (requestCode == PhotoPicker.REQUEST_CODE)) {
            List<String> photos = null;
            if (data != null) {
                photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
            }
            selectedPhotos.clear();
            if (photos != null) {
                selectedPhotos.addAll(photos);
            }
            photoAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (selectedPhotos != null) {
            selectedPhotos.clear();
        }
    }
}
