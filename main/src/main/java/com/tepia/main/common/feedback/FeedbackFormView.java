package com.tepia.main.common.feedback;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.leon.lfilepickerlibrary.LFilePicker;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.view.dialog.basedailog.ActionSheetDialog;
import com.tepia.base.view.floatview.CollectionsUtil;
import com.tepia.main.R;
import com.tepia.main.common.ViewBase;
import com.tepia.main.common.pickview.PhotoRecycleViewAdapter;
import com.tepia.main.common.pickview.RecyclerItemClickListener;
import com.tepia.main.model.dictmap.DictMapEntity;
import com.tepia.main.model.dictmap.DictMapEntity.ArrayBean.NameValueBean;
import com.tepia.main.model.dictmap.DictMapManager;
import com.tepia.main.view.maincommon.setting.adapter.FileRecycleViewAdapter;
import com.tepia.main.view.maincommon.setting.train.FileBean;
import com.tepia.photo_picker.PhotoPicker;
import com.tepia.photo_picker.PhotoPreview;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:xch
 * Date:2019/12/27
 * Description:
 */
public class FeedbackFormView extends ViewBase {

    private Button uploadBtn;
    private EditText msgEt;
    private TextView typeTv;
    private TextView photoTitleTv;
    private RecyclerView rvImagePick;
    private RecyclerView rvFilePick;
    private PhotoRecycleViewAdapter photoAdapter;
    private FileRecycleViewAdapter fileAdapter;
    private List<NameValueBean> valueBeanList;
    private List<String> typeStrs;
    private String feedbackType;
    private ArrayList<String> selectedPhotos;
    private List<FileBean> selectedFiles;

    private UploadFeedbackListener uploadFeedbackListener;

    public void setUploadFeedbackListener(UploadFeedbackListener uploadFeedbackListener) {
        this.uploadFeedbackListener = uploadFeedbackListener;
    }

    public FeedbackFormView(Context context) {
        super(context);
    }

    public FeedbackFormView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FeedbackFormView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int getViewId() {
        return R.layout.view_feedback_form;
    }

    @Override
    public void initData() {
        DictMapEntity dictMapEntity = DictMapManager.getInstance().getmDictMap();
        valueBeanList = dictMapEntity.getArray().getFeedback_type();
        typeStrs = new ArrayList<>();
        if (CollectionsUtil.isNotEmpty(valueBeanList)) {
            for (NameValueBean nameValueBean : valueBeanList) {
                typeStrs.add(nameValueBean.getName());
            }
        }

        typeTv = findViewById(R.id.tv_type);
        typeTv.setOnClickListener(v -> showFeedTypeDialog());
        msgEt = findViewById(R.id.edit_msg);
        uploadBtn = findViewById(R.id.btn_upload);
        uploadBtn.setOnClickListener(v -> {
            String msg = msgEt.getText().toString().trim();
            if (CollectionsUtil.isEmpty(feedbackType)) {
                ToastUtils.shortToast("处理类型不能为空");
                return;
            }
            if (CollectionsUtil.isEmpty(msg)) {
                ToastUtils.shortToast("处理意见不能为空");
                return;
            }
            List<String> filePaths = new ArrayList<>();
            if (CollectionsUtil.isNotEmpty(selectedFiles)) {
                for (FileBean filePath : selectedFiles) {
                    filePaths.add(filePath.filePath);
                }
            }
            if (uploadFeedbackListener != null)
                uploadFeedbackListener.uploadFeedback(feedbackType, msg, selectedPhotos, filePaths);
        });
        selectedPhotos = new ArrayList<>();
        selectedFiles = new ArrayList<>();
        photoTitleTv = findViewById(R.id.photoTitleTv);
        photoTitleTv.setText(mContext.getString(R.string.picstr, 0));
        rvImagePick = findViewById(R.id.rv_image_pick);
        rvFilePick = findViewById(R.id.rv_file_pick);

        initImagePick();
        initFilePick();
    }

    private void initImagePick() {
        rvImagePick.addOnItemTouchListener(new RecyclerItemClickListener(mContext, (view, position) -> {

            if (photoAdapter.getItemViewType(position) == PhotoRecycleViewAdapter.TYPE_ADD) {
                PhotoPicker.builder()
                        .setPhotoCount(PhotoRecycleViewAdapter.MAX)
                        .setShowCamera(true)
                        .setPreviewEnabled(true)
                        .setSelected(selectedPhotos)
                        .start((Activity) mContext);
            } else {
                PhotoPreview.builder()
                        .setPhotos(selectedPhotos)
                        .setCurrentItem(position)
                        .start((Activity) mContext);
            }

        }));
        photoAdapter = new PhotoRecycleViewAdapter(mContext, selectedPhotos);
        rvImagePick.setLayoutManager(new StaggeredGridLayoutManager(4, OrientationHelper.VERTICAL));
        rvImagePick.setAdapter(photoAdapter);
    }

    private void initFilePick() {
        fileAdapter = new FileRecycleViewAdapter(mContext, (ArrayList<FileBean>) selectedFiles);
        rvFilePick.setLayoutManager(new LinearLayoutManager(mContext));
        rvFilePick.setAdapter(fileAdapter);
        fileAdapter.setOnAddItemClickListener((view, position) -> {
            new LFilePicker()
                    .withActivity((Activity) mContext)
                    .withRequestCode(100)
                    .withMutilyMode(true)
                    .withFileFilter(new String[]{".doc", ".ppt", ".xls", ".docx", ".pptx", ".xlsx", ".pdf", ".txt"})
                    .start();
        });
        fileAdapter.setOnAddDeleteClickListener((view, position) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setTitle("温馨提示");
            builder.setMessage("确定删除" + selectedFiles.get(position).fileName + "吗？");
            builder.setPositiveButton("确定", (dialog, which) -> {
                //模拟删除数据，刷新数据列表定位到之前删除的位置
//                adapterPlanList.notifyDataSetChanged();
//                ((LinearLayoutManager)rvPlanList.getLayoutManager()).scrollToPositionWithOffset(adapterPosition, Px2dpUtils.dip2px(getContext(),200));
                selectedFiles.remove(position);
                fileAdapter.notifyDataSetChanged();
            });
            builder.setNegativeButton("取消", (dialog, which) -> {
            });
            builder.show();
        });
    }

    public void setPhotoImgs(List<String> photos) {
        selectedPhotos.clear();
        selectedPhotos.addAll(photos);
        photoAdapter.notifyDataSetChanged();
        photoTitleTv.setText(mContext.getString(R.string.picstr, selectedPhotos.size()));
    }

    public void setFilePaths(List<String> list) {
        FileBean fileBean;
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                String path = list.get(i);
                String fileName = path.substring(path.lastIndexOf("/") + 1);
                fileBean = new FileBean();
                fileBean.fileName = fileName;
                fileBean.filePath = path;
                if (!isFilesContain(path)) {
                    selectedFiles.add(fileBean);
                }
            }
            fileAdapter.notifyDataSetChanged();
        }
    }

    private boolean isFilesContain(String path) {
        boolean isContain = false;
        if (selectedFiles != null && selectedFiles.size() > 0) {
            for (int i = 0; i < selectedFiles.size(); i++) {
                FileBean fileBean = selectedFiles.get(i);
                if (path.equals(fileBean.filePath)) {
                    isContain = true;
                }
            }
        }
        return isContain;
    }

    private void showFeedTypeDialog() {
        if (CollectionsUtil.isEmpty(typeStrs)) return;
        String[] strings = new String[typeStrs.size()];
        final ActionSheetDialog dialog = new ActionSheetDialog(mContext, typeStrs.toArray(strings), null);
        dialog.title("请选择运维类型")
                .titleTextSize_SP(14.5f)
                .widthScale(0.8f)
                .show();
        dialog.setOnOpenItemClickL((parent, view, position, id) -> {
            if (position == -1) return;
            NameValueBean nameValueBean = valueBeanList.get(position);
            ToastUtils.shortToast(nameValueBean.getName());
            feedbackType = nameValueBean.getValue();
            typeTv.setText(nameValueBean.getName());
            dialog.dismiss();
        });
    }

    public interface UploadFeedbackListener {
        void uploadFeedback(String feedbackType, String msg, List<String> imgPaths, List<String> filePaths);
    }
}
