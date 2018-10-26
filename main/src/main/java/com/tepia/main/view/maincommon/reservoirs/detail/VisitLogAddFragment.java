package com.tepia.main.view.maincommon.reservoirs.detail;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.tepia.base.http.BaseResponse;
import com.tepia.base.mvp.MVPBaseFragment;
import com.tepia.base.utils.DoubleClickUtil;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.NetUtil;
import com.tepia.base.utils.SPUtils;
import com.tepia.base.utils.TimeFormatUtils;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.utils.Utils;
import com.tepia.base.view.dialog.basedailog.ActionSheetDialog;
import com.tepia.base.view.dialog.basedailog.OnOpenItemClick;
import com.tepia.main.ConfigConsts;
import com.tepia.main.R;
import com.tepia.main.common.pickview.OnItemClickListener;
import com.tepia.main.common.pickview.PhotoRecycleViewAdapter;
import com.tepia.main.common.pickview.RecyclerItemClickListener;
import com.tepia.main.model.report.EmergenceListBean;
import com.tepia.main.model.user.UserManager;
import com.tepia.main.utils.TimePickerDialogUtil;
import com.tepia.main.view.main.question.TypeResponse;
import com.tepia.main.view.maincommon.reservoirs.mvpreservoir.VisitLogContract;
import com.tepia.main.view.maincommon.reservoirs.mvpreservoir.VisitLogPresenter;
import com.tepia.main.view.mainworker.report.adapter.AdapterEmergenceReport;
import com.tepia.photo_picker.PhotoPicker;
import com.tepia.photo_picker.PhotoPreview;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;


/**
 * Created by      Android studio
 *
 * @author :ly (from Center Of Wuhan)
 * Date    :2018-10-26
 * Version :1.0
 * 功能描述 :到访日志上报fragment
 **/
public class VisitLogAddFragment extends MVPBaseFragment<VisitLogContract.View,VisitLogPresenter> implements View.OnClickListener, View.OnTouchListener,OnDateSetListener {


    private EditText titleEv;
    private EditText contentEv;
    private RecyclerView rvImagePick;
    private TextView sendBtn;
    private TextView typeTv;
    private TextView resviorTv;
    private TextView visitTimeTv;
    private TextView photoTitleTv;
    private String questionType;
    private String visitCause;
    private String workContent;
    private String remark;
    private String visitTime;
    private String reservoirId;
    private PhotoRecycleViewAdapter photoAdapter;
    private ArrayList<String> selectedPhotos = new ArrayList<>();
    private List<TypeResponse> data = new ArrayList<>();
    private List<com.tepia.main.model.detai.ReservoirBean> dateBeanList;

    @Override
    protected int getLayoutId() {
        return R.layout.visitlog_add_layout;
    }

    @Override
    protected void initData() {
    }


    @Override
    protected void initView(View view) {
        setCenterTitle("新增到访日志");
        showBack();


        typeTv = findView(R.id.typeTv);
        resviorTv = findView(R.id.resviorTv);
        visitTimeTv = findView(R.id.visitTimeTv);
        photoTitleTv = findView(R.id.photoTitleTv);
        photoTitleTv.setText(getString(R.string.picstr, selectedPhotos.size()));
        resviorTv.setOnClickListener(this);
        visitTimeTv.setOnClickListener(this);
        typeTv.setOnClickListener(this);

        dateBeanList = UserManager.getInstance().getLocalReservoirList();

        titleEv = findView(R.id.titleEv);
        titleEv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                visitCause = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        contentEv = findView(R.id.contentEv);
        //设置EditText的显示方式为多行文本输入
        contentEv.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        //文本显示的位置在EditText的最上方
        contentEv.setGravity(Gravity.TOP);
        //改变默认的单行模式
        contentEv.setSingleLine(false);
         //水平滚动设置为False
        contentEv.setHorizontallyScrolling(false);
        contentEv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                workContent = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        contentEv.setOnTouchListener(this);
        rvImagePick = findView(R.id.rv_image_pick);
        sendBtn = findView(R.id.sendBtn);
        sendBtn.setOnClickListener(this);
        rvImagePick.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                if (photoAdapter.getItemViewType(position) == PhotoRecycleViewAdapter.TYPE_ADD) {
                    PhotoPicker.builder()
                            .setPhotoCount(PhotoRecycleViewAdapter.MAX)
                            .setShowCamera(true)
                            .setPreviewEnabled(true)
                            .setSelected(selectedPhotos)
                            .start(getBaseActivity(), VisitLogAddFragment.this);
                } else {
                    PhotoPreview.builder()
                            .setPhotos(selectedPhotos)
                            .setCurrentItem(position)
                            .start(getBaseActivity(), VisitLogAddFragment.this);
                }

            }
        }));


        photoAdapter = new PhotoRecycleViewAdapter(getBaseActivity(), selectedPhotos);
        rvImagePick.setLayoutManager(new StaggeredGridLayoutManager(4, OrientationHelper.VERTICAL));
        rvImagePick.setAdapter(photoAdapter);
        initTimePicker();
        getSP();
    }


    /**
     * 保存数据
     */
    private void getSP() {
        visitCause = SPUtils.getInstance().getString(VisitLogAddActivity.key_Title, "");
        LogUtil.e("visitCause", visitCause + "--");

        titleEv.setText(visitCause);
        workContent = SPUtils.getInstance().getString(VisitLogAddActivity.key_Content, "");
        LogUtil.e("workContent", workContent);

        contentEv.setText(workContent);

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
            photoTitleTv.setText(getString(R.string.picstr, selectedPhotos.size()));
        }


    }


    /**
     * 展示水库
     *
     * @param mDateBeanList
     */
    private void showRiverDialog(List<com.tepia.main.model.detai.ReservoirBean> mDateBeanList) {

        if (mDateBeanList != null && mDateBeanList.size() > 0) {
            String[] stringItems = new String[mDateBeanList.size()];
            for (int i = 0; i < mDateBeanList.size(); i++) {
                stringItems[i] = mDateBeanList.get(i).getReservoir();
            }
            final ActionSheetDialog dialog = new ActionSheetDialog(getBaseActivity(), stringItems, null);
            dialog.title("请选择水库")
                    .titleTextSize_SP(14.5f)
                    .widthScale(0.8f)
                    .show();
            dialog.setOnOpenItemClickL(new OnOpenItemClick() {
                @Override
                public void onOpenItemClick(AdapterView<?> parent, View view, int position, long id) {
                    resviorTv.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
                    resviorTv.setText(stringItems[position]);
                    reservoirId = mDateBeanList.get(position).getReservoirId();
                    dialog.dismiss();


                }
            });
        }
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        //触摸的是EditText并且当前EditText可以滚动则将事件交给EditText处理；否则将事件交由其父类处理
        if ((view.getId() == R.id.contentEv && canVerticalScroll(contentEv))) {
            //告诉父view，我的事件自己处理
            view.getParent().requestDisallowInterceptTouchEvent(true);
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                //告诉父view，你可以处理了
                view.getParent().requestDisallowInterceptTouchEvent(false);
            }
        }
        return false;
    }

    /**
     * EditText竖直方向是否可以滚动
     *
     * @param editText 需要判断的EditText
     * @return true：可以滚动   false：不可以滚动
     */
    private boolean canVerticalScroll(EditText editText) {
        //滚动的距离
        int scrollY = editText.getScrollY();
        //控件内容的总高度
        int scrollRange = editText.getLayout().getHeight();
        //控件实际显示的高度
        int scrollExtent = editText.getHeight() - editText.getCompoundPaddingTop() - editText.getCompoundPaddingBottom();
        //控件内容总高度与实际显示高度的差值
        int scrollDifference = scrollRange - scrollExtent;

        if (scrollDifference == 0) {
            return false;
        }

        return (scrollY > 0) || (scrollY < scrollDifference - 1);
    }


    @Override
    public void onClick(View view) {

        int viewID = view.getId();
        if (viewID == R.id.sendBtn) {
            visitCause = titleEv.getText().toString();
            workContent = contentEv.getText().toString();
            if (!NetUtil.isNetworkConnected(Utils.getContext())) {
                ToastUtils.shortToast(R.string.no_network);
                return;
            }

            if(DoubleClickUtil.isFastDoubleClick()){
                return;
            }
            if (TextUtils.isEmpty(visitCause)) {
                ToastUtils.shortToast("请输入" + getString(R.string.titlestr));
                return;
            }

            if (TextUtils.isEmpty(visitTime)) {
                ToastUtils.shortToast("请选择到访时间");
                return;
            }

            if (TextUtils.isEmpty(reservoirId)) {
                ToastUtils.shortToast("请选择" + getString(R.string.resviorstr));
                return;
            }
            if (TextUtils.isEmpty(workContent)) {
                ToastUtils.shortToast("请输入事件内容");
                return;
            }


            mPresenter.attachView(new VisitLogContract.View<BaseResponse>() {

                @Override
                public Context getContext() {
                    return null;
                }

                @Override
                public void success(BaseResponse data) {
                    ToastUtils.shortToast("提交成功");
                    Intent intent = new Intent();
                    getBaseActivity().setResult(VisitLogActivity.REQUEST_DELETE_CODE, intent);
                    getBaseActivity().finish();
                }

                @Override
                public void failure(String msg) {
                    LogUtil.e(msg + "---");

                }
            });

            mPresenter.insert(reservoirId, visitCause, workContent, "",
                    visitTime, selectedPhotos);


        } else if (viewID == R.id.resviorTv) {
            if (dateBeanList != null && dateBeanList.size() > 0) {
                showRiverDialog(dateBeanList);
            }
        }else if (viewID == R.id.visitTimeTv) {
            if (timePickerDialogUtil.startDialog != null) {
                timePickerDialogUtil.startDialog = null;
            }
            startflag = true;
            if (last_millseconds_start != 0) {
                timePickerDialogUtil.builder.setCurrentMillseconds(last_millseconds_start);
            }
            timePickerDialogUtil.builder.setTitleStringId("请选择时间");
            timePickerDialogUtil.startDialog = timePickerDialogUtil.builder.build();
            timePickerDialogUtil.startDialog.show(getFragmentManager(), "all");
        }
    }



    // 起始时间选择器
    private boolean startflag = false;
    // 终止时间选择器
    private boolean endflag = false;
    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    private long last_millseconds_start = 0;

    private TimePickerDialogUtil timePickerDialogUtil;

    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {

        if (startflag) {
            last_millseconds_start = millseconds;
            String text = timePickerDialogUtil.getDateToString(millseconds);
            visitTime = text;
            visitTimeTv.setText(visitTime);
            startflag = false;
            LogUtil.e("startTime", text);

        }

    }

    /**
     * 初始化时间选择器
     */
    private void initTimePicker() {
        timePickerDialogUtil = new TimePickerDialogUtil(getContext(), sf);
        timePickerDialogUtil.initTimePicker(this, Type.ALL);

    }


    /**
     * 还原页面
     */
    private void clear() {
        for (int i = 0; i < data.size(); i++) {

            TypeResponse typeResponse = data.get(i);
            typeResponse.setShowIcon(false);

        }

        //图片选择器还原
        if (selectedPhotos.size() > 0) {
            selectedPhotos.clear();
            photoAdapter.notifyDataSetChanged();
        }
        questionType = "";
        visitCause = "";
        workContent = "";
        reservoirId = "";
        titleEv.setText("");
        contentEv.setText("");

        typeTv.setText(getString(R.string.selecttype));
        typeTv.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
        resviorTv.setText(getString(R.string.selectresviorstr));
        resviorTv.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
        photoTitleTv.setText(getString(R.string.picstr, selectedPhotos.size()));

    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();

        clear();
        SPUtils.getInstance().putString(VisitLogAddActivity.key_Title, visitCause);
        SPUtils.getInstance().putString(VisitLogAddActivity.key_Content, workContent);
        if (dateBeanList != null) {
            dateBeanList.clear();
        }
        if (selectedPhotos != null) {
            selectedPhotos.clear();
        }
        if (data != null) {
            data.clear();
        }
    }
}
