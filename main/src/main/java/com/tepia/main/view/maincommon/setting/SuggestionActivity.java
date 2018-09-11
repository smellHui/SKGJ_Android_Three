package com.tepia.main.view.maincommon.setting;

import android.content.Intent;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.tepia.base.http.BaseResponse;
import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.base.utils.NetUtil;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.utils.Utils;
import com.tepia.main.R;
import com.tepia.main.common.pickview.PhotoRecycleViewAdapter;
import com.tepia.main.common.pickview.RecyclerItemClickListener;
import com.tepia.main.view.main.question.QuestionContract;
import com.tepia.main.view.main.question.QuestionPresenter;
import com.tepia.photo_picker.PhotoPicker;
import com.tepia.photo_picker.PhotoPreview;

import java.util.ArrayList;
import java.util.List;
/**
 * 意见反馈
 * @author ly on 2018/7/29
 */
public class SuggestionActivity extends MVPBaseActivity<QuestionContract.View, QuestionPresenter> implements QuestionContract.View<BaseResponse>{

    private EditText suggestEt;

    private TextView countNum;

    private TextView pTv;

    private RecyclerView rvImagePick;

    private EditText phoneEv;

    private TextView updateBtn;

    private ArrayList<String> selectedPhotos = new ArrayList<>();
    private PhotoRecycleViewAdapter photoAdapter;
    @Override
    public int getLayoutId() {
        return R.layout.activity_suggestion;
    }

    @Override
    public void initView() {
        setCenterTitle(getString(R.string.suggetionstr));
        showBack();
        suggestEt = findViewById(R.id.suggestEt);
        countNum = findViewById(R.id.countNum);
        rvImagePick = findViewById(R.id.rv_image_pick);
        pTv = findViewById(R.id.pTv);
        phoneEv = findViewById(R.id.phoneEv);
        updateBtn = findViewById(R.id.sendBtn);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = view.getId();
                if (i == R.id.sendBtn) {
                    eventSuggestion();

                }
            }
        };
        pTv.setOnClickListener(onClickListener);
        updateBtn.setOnClickListener(onClickListener);

        initPresent();
    }

    /**
     * 提交
     */
    private void eventSuggestion() {
        if(!NetUtil.isNetworkConnected(Utils.getContext())){
            ToastUtils.shortToast(R.string.no_network);
            return;
        }
        String contentStr = suggestEt.getText().toString();
        String phoneStr = phoneEv.getText().toString();
        if (TextUtils.isEmpty(contentStr)) {
            ToastUtils.shortToast(getString(R.string.setting_v_content));
            return;
        }

        mPresenter.appFeedBack(contentStr,phoneStr,selectedPhotos);
    }

    private void initPresent() {

        rvImagePick.addOnItemTouchListener(new RecyclerItemClickListener(this, (view, position) -> {
            if (photoAdapter.getItemViewType(position) == PhotoRecycleViewAdapter.TYPE_ADD) {
                PhotoPicker.builder()
                        .setPhotoCount(PhotoRecycleViewAdapter.MAX)
                        .setShowCamera(true)
                        .setPreviewEnabled(true)
                        .setSelected(selectedPhotos)
                        .start(SuggestionActivity.this);
            } else {
                PhotoPreview.builder()
                        .setPhotos(selectedPhotos)
                        .setCurrentItem(position)
                        .start(SuggestionActivity.this);
            }

        }));
        photoAdapter = new PhotoRecycleViewAdapter(this, selectedPhotos);
        rvImagePick.setLayoutManager(new StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL));
        rvImagePick.setAdapter(photoAdapter);

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
        if (resultCode == RESULT_OK && (requestCode == PhotoPicker.REQUEST_CODE || requestCode == PhotoPreview.REQUEST_CODE)) {
            List<String> photos = null;
            if (data != null) {
                photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
            }

            if (photos != null) {
                selectedPhotos.clear();
                selectedPhotos.addAll(photos);
                photoAdapter.notifyDataSetChanged();
            }

        }
    }

    @Override
    public void success(BaseResponse data) {
        ToastUtils.shortToast(Utils.getContext().getString(R.string.question_success));
        finish();
    }

    @Override
    public void failure(String msg) {
        ToastUtils.shortToast("提交失败");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (selectedPhotos != null) {
            selectedPhotos.clear();
        }
    }
}
