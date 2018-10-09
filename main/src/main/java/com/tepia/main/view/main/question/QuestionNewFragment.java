package com.tepia.main.view.main.question;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tepia.base.http.BaseResponse;
import com.tepia.base.mvp.MVPBaseFragment;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.NetUtil;
import com.tepia.base.utils.SPUtils;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.utils.Utils;
import com.tepia.base.view.dialog.basedailog.ActionSheetDialog;
import com.tepia.base.view.dialog.basedailog.OnOpenItemClick;
import com.tepia.main.R;
import com.tepia.main.common.pickview.OnItemClickListener;
import com.tepia.main.common.pickview.PhotoRecycleViewAdapter;
import com.tepia.main.common.pickview.RecyclerItemClickListener;
import com.tepia.main.model.dictmap.DictMapEntity;
import com.tepia.main.model.dictmap.DictMapManager;
import com.tepia.main.model.question.ReservoirBean;
import com.tepia.main.model.question.ReservoirDateBean;
import com.tepia.main.model.user.UserInfoBean;
import com.tepia.main.model.user.UserManager;
import com.tepia.photo_picker.PhotoPicker;
import com.tepia.photo_picker.PhotoPreview;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.functions.Function;

import static android.app.Activity.RESULT_OK;


/**
 * 首页问题反馈
 *
 * @author ly on 2018/5
 */
public class QuestionNewFragment extends MVPBaseFragment<QuestionContract.View, QuestionPresenter> implements View.OnClickListener, View.OnTouchListener {



    private EditText titleEv;
    private EditText contentEv;
    private RecyclerView rvImagePick;
    private TextView sendBtn;
    private TextView typeTv;
    private TextView resviorTv;
    private TextView photoTitleTv;
    private String questionType;
    private String questionTitle;
    private String questionContent;
    private String reservoirId;
    private PhotoRecycleViewAdapter photoAdapter;
    private ArrayList<String> selectedPhotos = new ArrayList<>();
    private List<TypeResponse> data = new ArrayList<>();
    private List<ReservoirDateBean> dateBeanList;
    private List<ReservoirDateBean> dateBeanListChangyong = new ArrayList<>(6);
    private String userCode = "";
    private RecyclerView recycleChangyong;
    private AdapterQuestion adapterQuestion;
    //标签个数
    private static int labelcount= 8;

    @Override
    protected int getLayoutId() {
        return R.layout.question_main_layout_new;
    }

    @Override
    protected void initData() {

    }

    private void setType(String typestr, String typeid, boolean isshowIcon) {
        TypeResponse typeResponse = new TypeResponse();
        typeResponse.setShowIcon(isshowIcon);
        typeResponse.setTypeId(typeid);
        typeResponse.setType(typestr);
        data.add(typeResponse);
    }

    @Override
    protected void initView(View view) {
        setCenterTitle(getString(R.string.question_fragment_title));
        showBack();
        UserInfoBean reservoirBean = UserManager.getInstance().getUserBean();
        if( reservoirBean != null && reservoirBean.getData() != null){
            userCode = reservoirBean.getData().getUserCode();
        }

        typeTv = findView(R.id.typeTv);
        resviorTv = findView(R.id.resviorTv);
        photoTitleTv = findView(R.id.photoTitleTv);
        photoTitleTv.setText(getString(R.string.picstr, selectedPhotos.size()));
        resviorTv.setOnClickListener(this);
        typeTv.setOnClickListener(this);


        setType(getString(R.string.question_xunjian), "1", false);
        setType(getString(R.string.question_baojie), "3", true);
        setType(getString(R.string.question_weixiu), "2", false);
        setType(getString(R.string.question_qita), "4", false);

        recycleChangyong = findView(R.id.recycleChangyong);
        recycleChangyong.setLayoutManager(new GridLayoutManager(getContext(),4));
        //item居中显示
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recycleChangyong);
        adapterQuestion = new AdapterQuestion(R.layout.tag_text_layout,dateBeanListChangyong);
        recycleChangyong.setAdapter(adapterQuestion);
        adapterQuestion.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                resviorTv.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
                ReservoirDateBean reservoirDateBean = dateBeanListChangyong.get(position);
                if(reservoirDateBean != null) {
                    resviorTv.setText(reservoirDateBean.getReservoir());
                    reservoirId = reservoirDateBean.getReservoirId();
                }
            }
        });

        initFlowAdapte();



        titleEv = findView(R.id.titleEv);
        titleEv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                questionTitle = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        contentEv = findView(R.id.contentEv);
        contentEv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                questionContent = charSequence.toString();
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
                            .start(getBaseActivity(), QuestionNewFragment.this);
                } else {
                    PhotoPreview.builder()
                            .setPhotos(selectedPhotos)
                            .setCurrentItem(position)
                            .start(getBaseActivity(), QuestionNewFragment.this);
                }

            }
        }));
        photoAdapter = new PhotoRecycleViewAdapter(getBaseActivity(), selectedPhotos);
        rvImagePick.setLayoutManager(new StaggeredGridLayoutManager(4, OrientationHelper.VERTICAL));
        rvImagePick.setAdapter(photoAdapter);
        getSP();
    }

    /**
     * 保存数据
     */
    private void getSP(){
            questionTitle = SPUtils.getInstance().getString(QuestionActivity.key_Title,"");
            LogUtil.e("questionTitle",questionTitle+"--");

            titleEv.setText(questionTitle);
            questionContent = SPUtils.getInstance().getString(QuestionActivity.key_Content,"");
            LogUtil.e("questionContent",questionContent);

            contentEv.setText(questionContent);

    }

    /**
     * 初始化常用标签
     */
    private void initFlowAdapte(){


        //降序查找
        List<ReservoirDateBean> dateBeanList = DataSupport.where("userCode = ? and count > ?",userCode, "0").order("count DESC").find(ReservoirDateBean.class);
        if (dateBeanList != null) {
            dateBeanListChangyong.clear();
            for (int i = 0; i < dateBeanList.size(); i++) {
                //取出前五条数据作为常用标签
                if(i < labelcount){
                    ReservoirDateBean dateBean = dateBeanList.get(i);
                    dateBeanListChangyong.add(dateBean);
                }
            }
        }

        if(dateBeanListChangyong != null && dateBeanListChangyong.size() > 0){
            recycleChangyong.setVisibility(View.VISIBLE);
            adapterQuestion.notifyDataSetChanged();
        }else{
            recycleChangyong.setVisibility(View.GONE);
        }

    }

    @Override
    protected void initRequestData() {

        //水库接口  //dba41afabdbc484c879faf07411e2a18
        mPresenter.listReservoirInCenter("");
        mPresenter.attachView(new QuestionContract.View<ReservoirBean>() {

            @Override
            public Context getContext() {
                return null;
            }

            @Override
            public void success(ReservoirBean reservoirBean) {
                if (reservoirBean != null) {
                    dateBeanList = reservoirBean.getData();
                }

            }

            @Override
            public void failure(String msg) {
                ToastUtils.shortToast(msg);
                LogUtil.e("listReservoirInCenter", msg);
            }
        });

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
            photoTitleTv.setText(getString(R.string.picstr, selectedPhotos.size()));
            photoAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 展示水库
     *
     * @param mDateBeanList
     */
    private void showRiverDialog(List<ReservoirDateBean> mDateBeanList) {
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
                    List<ReservoirDateBean> dateBeanList = DataSupport.where("userCode = ? and reservoirId = ?",userCode, reservoirId).find(ReservoirDateBean.class);
                    if(dateBeanList != null && dateBeanList.size() > 0){
                        ReservoirDateBean dateBean = dateBeanList.get(0);
                        int count = dateBean.getCount();
                        if (count < labelcount) {
                            dateBean.setCount(count + 1);
                            dateBean.save();
                            initFlowAdapte();
                        }
                    }else {
                        //保存
                        ReservoirDateBean dateBean = mDateBeanList.get(position);
                        if(dateBean.getCount() != null){
                            int count = dateBean.getCount();
                            if (count < labelcount) {
                                dateBean.setCount(count + 1);
                                dateBean.setUserCode(userCode);
                                dateBean.save();
                                initFlowAdapte();
                            }
                        }else {
                            dateBean.setCount(1);
                            dateBean.setUserCode(userCode);
                            dateBean.save();
                            initFlowAdapte();
                        }
                    }

                }
            });
        }
    }

    /**
     * 展示类型
     *
     * @param typeResponseList
     */
    private void showTypeDialog(List<TypeResponse> typeResponseList) {
        if (typeResponseList != null) {
            String[] stringItems = new String[typeResponseList.size()];
            for (int i = 0; i < typeResponseList.size(); i++) {
                stringItems[i] = typeResponseList.get(i).getType();
            }
            final ActionSheetDialog dialog = new ActionSheetDialog(getBaseActivity(), stringItems, null);
            dialog.title("请选择类型")
                    .titleTextSize_SP(14.5f)
                    .widthScale(0.8f)
                    .show();
            dialog.setOnOpenItemClickL(new OnOpenItemClick() {
                @Override
                public void onOpenItemClick(AdapterView<?> parent, View view, int position, long id) {
                    typeTv.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
                    typeTv.setText(stringItems[position]);
                    questionType = typeResponseList.get(position).getTypeId();
                    dialog.dismiss();
                }
            });
        }
    }


    private void stopgo(String type, String toastStr) {
        if (TextUtils.isEmpty(type)) {
            ToastUtils.shortToast(toastStr);
            return;
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
            questionTitle = titleEv.getText().toString();
            questionContent = contentEv.getText().toString();
            if (TextUtils.isEmpty(questionTitle)) {
                ToastUtils.shortToast("请输入" + getString(R.string.titlestr));
                return;
            }
            if (TextUtils.isEmpty(questionType)) {
                ToastUtils.shortToast("请选择" + getString(R.string.typestr));
                return;
            }
            if (TextUtils.isEmpty(reservoirId)) {
                ToastUtils.shortToast("请选择" + getString(R.string.resviorstr));
                return;
            }
            if (TextUtils.isEmpty(questionContent)) {
                ToastUtils.shortToast("请输入事件内容");
                return;
            }
            if (!NetUtil.isNetworkConnected(Utils.getContext())) {
                ToastUtils.shortToast(R.string.no_network);
                return;
            }

            mPresenter.attachView(new QuestionContract.View<BaseResponse>() {

                @Override
                public Context getContext() {
                    return null;
                }

                @Override
                public void success(BaseResponse data) {
                    clear();
                }

                @Override
                public void failure(String msg) {

                }
            });
            mPresenter.feedback(questionType, questionTitle, questionContent,
                    reservoirId, selectedPhotos);


        } else if (viewID == R.id.resviorTv) {
            if (dateBeanList != null && dateBeanList.size() > 0) {
                showRiverDialog(dateBeanList);
            }
        } else if (viewID == R.id.typeTv) {
            if (null != data && data.size() > 0) {
                showTypeDialog(data);
            }
        }
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
        questionTitle = "";
        questionContent = "";
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
        SPUtils.getInstance().putString(QuestionActivity.key_Title,questionTitle);
        SPUtils.getInstance().putString(QuestionActivity.key_Content,questionContent);
        if (dateBeanListChangyong != null) {
            dateBeanListChangyong.clear();
        }
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
