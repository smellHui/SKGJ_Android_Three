package com.tepia.main.view.main.question.problemlist;

import android.graphics.Color;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.tepia.base.http.BaseResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.utils.Utils;
import com.tepia.base.view.dialog.basedailog.ActionSheetDialog;
import com.tepia.base.view.dialog.basedailog.OnOpenItemClick;
import com.tepia.main.R;
import com.tepia.main.common.pickview.RecyclerItemClickListener;
import com.tepia.main.model.dictmap.DictMapEntity;
import com.tepia.main.model.dictmap.DictMapManager;
import com.tepia.main.model.question.ProblemDetailBean;
import com.tepia.main.model.question.QuestionManager;
import com.tepia.main.utils.EmptyLayoutUtil;
import com.tepia.main.view.main.question.QuestionContract;
import com.tepia.main.view.main.question.QuestionPresenter;
import com.tepia.main.view.main.question.problemconfigure.ProblemCallListActivity;
import com.tepia.main.view.main.question.problemconfigure.ProblemConfigureListActivity;
import com.tepia.photo_picker.PhotoPicker;
import com.tepia.photo_picker.PhotoPreview;

import java.util.ArrayList;
import java.util.List;

/**
 * 事件上报详情
 *
 * @author ly
 * @date 2018/7/31
 * @link
 */
public class ProblemDetailActivity extends MVPBaseActivity<QuestionContract.View, QuestionPresenter> implements QuestionContract.View<ProblemDetailBean>,View.OnClickListener {
    private final static String TAG = ProblemDetailActivity.class.getName();

    private TextView nameTv;
    private TextView typeTv;
    private TextView resviorTv;
    private TextView updatemanTv;
    private TextView updatetimeTv;
    private TextView contentTv;
    private TextView excuteStatusTv;
    private TextView photoTitleTv;
    private LinearLayout imageLy;
    private RecyclerView rvImagePick;
    private RecyclerView bizRy;
    private LinearLayout fankuiLy;
    private LinearLayout querenLy;
    private EditText excuteDesEv;
    private TextView sendQuerenBtn;
    private TextView sendFankuiBtn;
    private TextView statusUpTv;
    private TextView typeUpTv;
    private FrameLayout typeUpFrlayout;
    private ScrollView rootScroll;
    private LinearLayout rootLayout;
    private PhotoAdapter photoAdapter;
    private AdapterBizProblem adapterBizProblem;
    private ArrayList<String> selectedPhotos = new ArrayList<>();
    private ProblemDetailBean.DataBean dataBean;
    private String problemStatus;

    private LinearLayout shenheLy;
    private FrameLayout isAniamlFl;
    private RadioButton radio_shen_yes;
    private RadioButton radio_shen_no;
    private RadioButton radio_isAnimal_yes;
    private RadioButton radio_isAnimal_no;
    private EditText  shenheEv;
    private TextView  sendShenheBtn;
    //executeType 1通过，0拒绝 isAnimal是否通知业主 0-否 1-是
    private String executeType = "1", executeDes = "", isAnimal = "1";
    /**
     * 问题确认类型1一般问题 2 重大问题 3.不属于问题
     */
    private String problemConfirmTypeStr = "";
    /**
     * 事件类型
     */
    private String typeStr = "";
    private String problemId = "";

    @Override
    public int getLayoutId() {
        return R.layout.activity_problem_detail;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCenterTitle("事件详情");
        showBack();
        initProblemView();
        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(ProblemListActivity.key_problem)) {
            fankuiAndquerenInit();
            String problemId = getIntent().getExtras().getString(ProblemListActivity.key_problem);
            getDetailedProblemInfoByProblemId(problemId);
        }
        else if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(ProblemCallListActivity.key_call)) {
            dataBean = (ProblemDetailBean.DataBean) getIntent().getExtras().getSerializable(ProblemCallListActivity.key_call);
            problemStatus = dataBean.getProblemStatus();
            problemId = dataBean.getProblemId();
            show(dataBean);
            if("0".equals(problemStatus)){
                //待确认
                querenLy.setVisibility(View.VISIBLE);


            }else if("4".equals(problemStatus)){
                //待反馈
                fankuiLy.setVisibility(View.VISIBLE);
            }
        }else if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(ProblemConfigureListActivity.key_configure)) {
            shenheInit();
            dataBean = (ProblemDetailBean.DataBean) getIntent().getExtras().getSerializable(ProblemConfigureListActivity.key_configure);
            problemId = dataBean.getProblemId();
            if("2".equals(dataBean.getProblemCofirmType())){
                isAniamlFl.setVisibility(View.VISIBLE);
            }else{
                isAniamlFl.setVisibility(View.GONE);

            }

            show(dataBean);
            shenheLy.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void initView() {


    }

    private void initProblemView() {
        rootLayout = findViewById(R.id.rootLayout);
        rootScroll = findViewById(R.id.rootScroll);
        nameTv = findViewById(R.id.nameTv);
        typeTv = findViewById(R.id.typeTv);
        resviorTv = findViewById(R.id.resviorTv);
        updatemanTv = findViewById(R.id.updatemanTv);
        updatetimeTv = findViewById(R.id.updatetimeTv);
        contentTv = findViewById(R.id.contentTv);
        excuteStatusTv = findViewById(R.id.excuteStatusTv);
        photoTitleTv = findViewById(R.id.photoTitleTv);
        rvImagePick = findViewById(R.id.rv_image_pick);
        imageLy = findViewById(R.id.imageLy);
        bizRy = findViewById(R.id.bizRy);

        photoAdapter = new PhotoAdapter(getContext(), R.layout.activity_problem_photo_layout,selectedPhotos);
        rvImagePick.setLayoutManager(new GridLayoutManager(getContext(),4));
        rvImagePick.setAdapter(photoAdapter);
        imageLy.setVisibility(View.GONE);
        rvImagePick.addOnItemTouchListener(new RecyclerItemClickListener(this, (view, position) -> {
            PhotoPreview.builder()
                    .setPhotos(selectedPhotos)
                    .setCurrentItem(position)
                    .setShowDeleteButton(false)
                    .start(ProblemDetailActivity.this);

        }));
        fankuiAndquerenInit();


    }

    /**
     * 意见返回和确认
     */
    private void fankuiAndquerenInit(){
        fankuiLy = findViewById(R.id.fankuiLy);
        querenLy = findViewById(R.id.querenLy);
        fankuiLy.setVisibility(View.GONE);
        querenLy.setVisibility(View.GONE);
        excuteDesEv = findViewById(R.id.excuteDesEv);
        sendFankuiBtn = findViewById(R.id.sendFankuiBtn);
        statusUpTv = findViewById(R.id.statusUpTv);
        typeUpTv = findViewById(R.id.typeUpTv);
        typeUpFrlayout = findViewById(R.id.typeUpFrlayout);
        typeUpFrlayout.setVisibility(View.GONE);
        sendQuerenBtn = findViewById(R.id.sendQuerenBtn);
        sendFankuiBtn.setOnClickListener(this);
        sendQuerenBtn.setOnClickListener(this);
        statusUpTv.setOnClickListener(this);
        typeUpTv.setOnClickListener(this);
        bizRy.setLayoutManager(new LinearLayoutManager(this));
        controlKeyboardLayout(rootLayout,sendQuerenBtn);
        controlKeyboardLayout(rootLayout,sendFankuiBtn);
    }

    /**
     * 审核
     */
    private void shenheInit(){
        shenheLy = findViewById(R.id.shenheLy);
        shenheLy.setVisibility(View.GONE);
        radio_shen_yes = findViewById(R.id.radio_shen_yes);
        radio_shen_no = findViewById(R.id.radio_shen_no);
        radio_isAnimal_no = findViewById(R.id.radio_isadmin_no);
        radio_isAnimal_yes = findViewById(R.id.radio_isadmin_yes);
        isAniamlFl = findViewById(R.id.isAniamlFl);

        if(radio_shen_no.isChecked()){
            executeType = "0";
        }else{
            executeType = "1";
        }
        if(radio_isAnimal_no.isChecked()){
            isAnimal = "1";
        }else{
            isAnimal = "0";
        }

        RadioGroup shenRg = findViewById(R.id.shenRg);
        RadioGroup isAdminRg = findViewById(R.id.isAdminRg);

        shenheEv = findViewById(R.id.shenheEv);
        sendShenheBtn = findViewById(R.id.sendShenheBtn);
        sendShenheBtn.setOnClickListener(this);

        shenRg.setOnCheckedChangeListener((group, checkedId) -> {
            if(radio_shen_no.getId() == checkedId){
                executeType = "0";
            }
            if(radio_shen_yes.getId() == checkedId){
                executeType = "1";

            }
        });

        isAdminRg.setOnCheckedChangeListener((group, checkedId) -> {
            if(radio_isAnimal_no.getId() == checkedId){
                isAnimal = "1";
            }
            if(radio_isAnimal_yes.getId() == checkedId){
                isAnimal = "0";

            }
        });
        controlKeyboardLayout(rootLayout,sendShenheBtn);
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
    public void success(ProblemDetailBean problemDetailBean) {
        ProblemDetailBean.DataBean dataBean = problemDetailBean.getData();
        show(dataBean);
    }

    private void show(ProblemDetailBean.DataBean dataBean){
        if (dataBean != null) {
            nameTv.setText(dataBean.getProblemTitle());
            resviorTv.setText(dataBean.getReservoirName());
            switch (dataBean.getProblemType()){
                case "1":
                    typeTv.setText(getString(R.string.question_xunjian));
                    break;
                case "2":
                    typeTv.setText(getString(R.string.question_weixiu));
                    break;
                case "3":
                    typeTv.setText(getString(R.string.question_baojie));
                    break;
                case "4":
                    typeTv.setText(getString(R.string.question_qita));
                    break;
                default:
            }

            updatemanTv.setText(dataBean.getUserName());
            updatetimeTv.setText(dataBean.getCreateDate());
            contentTv.setText(dataBean.getProblemDescription());
            //图片
            List<ProblemDetailBean.DataBean.ISysFileUploadsBean> iSysFileUploads = dataBean.getISysFileUploads();
            selectedPhotos.clear();
            if (iSysFileUploads != null) {
                for (ProblemDetailBean.DataBean.ISysFileUploadsBean iSysFileUploadsBean : iSysFileUploads) {
                    selectedPhotos.add(iSysFileUploadsBean.getFilePath());
                }
            }

            photoTitleTv.setText(getString(R.string.picstr_problem, selectedPhotos.size()));
            if(selectedPhotos.size() > 0) {
                imageLy.setVisibility(View.VISIBLE);
                photoAdapter.notifyDataSetChanged();
            }

            PhotoPicker.builder()
                    .setPhotoCount(selectedPhotos.size());


            String configtypeStr = dataBean.getProblemCofirmType();
            if("0".equals(configtypeStr)){
                excuteStatusTv.setText("不是问题");
            }else if("1".equals(configtypeStr)){
                excuteStatusTv.setText("一般问题");
            }else if("2".equals(configtypeStr)){
                excuteStatusTv.setText("严重问题");
            }

            if ("2".equals(configtypeStr)) {
                excuteStatusTv.setTextColor(Color.RED);
            }



            List<ProblemDetailBean.DataBean.BizProblemFlowsBean> bizProblemFlowsBeanList = dataBean.getBizProblemFlows();
            if(bizProblemFlowsBeanList != null && bizProblemFlowsBeanList.size() > 0) {
                adapterBizProblem = new AdapterBizProblem(getContext(), R.layout.layout_problem_biz_item, bizProblemFlowsBeanList);
                bizRy.setAdapter(adapterBizProblem);
                adapterBizProblem.notifyDataSetChanged();
            }else{
                bizProblemFlowsBeanList = new ArrayList<>(1);
                adapterBizProblem = new AdapterBizProblem(getContext(), R.layout.layout_problem_biz_item, bizProblemFlowsBeanList);
                bizRy.setAdapter(adapterBizProblem);
                adapterBizProblem.setEmptyView(EmptyLayoutUtil.show("还未开始审核"));
            }

        }
    }

    @Override
    public void failure(String msg) {
        ToastUtils.shortToast(msg + " ");
    }

    private void getDetailedProblemInfoByProblemId(String problemId) {
        mPresenter.getDetailedProblemInfoByProblemId(problemId);
    }

    /**
     * 展示问题状态类型
     *
     * @param nameValueBeans
     */
    private void showStatusUpTvDialog(List<DictMapEntity.ArrayBean.NameValueBean> nameValueBeans) {
        if (nameValueBeans != null) {
            String[] stringItems = new String[nameValueBeans.size()];
            for (int i = 0; i < nameValueBeans.size(); i++) {
                stringItems[i] = nameValueBeans.get(i).getName();
            }
            final ActionSheetDialog dialog = new ActionSheetDialog(this, stringItems, null);
            dialog.title("请选择类型")
                    .titleTextSize_SP(14.5f)
                    .widthScale(0.8f)
                    .show();
            dialog.setOnOpenItemClickL(new OnOpenItemClick() {
                @Override
                public void onOpenItemClick(AdapterView<?> parent, View view, int position, long id) {
                    statusUpTv.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
                    statusUpTv.setText(stringItems[position]);
                    problemConfirmTypeStr = nameValueBeans.get(position).getValue();
                    dialog.dismiss();

                    if("0".equals(problemConfirmTypeStr)){
                        //不是问题时隐藏问题分配
                        typeUpFrlayout.setVisibility(View.GONE);
                        typeUpTv.setText("请选择>");
                        typeStr = "";
                    }else{
                        typeUpFrlayout.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
    }

    /**
     * 展示指派给哪一类事件
     * @param nameValueBeans
     */
    private void showTypeDialog(List<DictMapEntity.ArrayBean.NameValueBean> nameValueBeans) {
        if (nameValueBeans != null) {
            String[] stringItems = new String[nameValueBeans.size()];
            for (int i = 0; i < nameValueBeans.size(); i++) {
                stringItems[i] = nameValueBeans.get(i).getName()+"处理";
            }
            final ActionSheetDialog dialog = new ActionSheetDialog(this, stringItems, null);
            dialog.title("请选择指派给")
                    .titleTextSize_SP(14.5f)
                    .widthScale(0.8f)
                    .show();
            dialog.setOnOpenItemClickL(new OnOpenItemClick() {
                @Override
                public void onOpenItemClick(AdapterView<?> parent, View view, int position, long id) {
                    typeUpTv.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
                    typeUpTv.setText(stringItems[position]);
                    typeStr = nameValueBeans.get(position).getValue();
                    dialog.dismiss();
                }
            });
        }
    }

    /**
     * 意见反馈
     * @param problemId
     * @param excuteDes
     */
    private void  feedback(String problemId,String excuteDes) {
        QuestionManager.getInstance().feedback(problemId,excuteDes)
                .subscribe(new LoadingSubject<BaseResponse>(true,Utils.getContext().getString(R.string.data_loading)) {
                    @Override
                    protected void _onNext(BaseResponse baseResponse) {
                        if (baseResponse != null) {
                            if (baseResponse.getCode() == 0) {
                                LogUtil.e(TAG,"feedback--意见详情请求成功 ");
                                ToastUtils.shortToast("提交成功");
                                ProblemCallListActivity.has_success_call = true;
                                finish();
                            }
                        }
                    }

                    @Override
                    protected void _onError(String message) {
                        LogUtil.e(TAG,"feedback--意见详情请求失败 ");
                        ToastUtils.shortToast("提交失败");

                    }
                });
    }

    /**
     * @param root         最外层布局，需要调整的布局
     * @param scrollToView 被键盘遮挡的scrollToView，滚动root,使scrollToView在root可视区域的底部
     */
    private void controlKeyboardLayout(final View root, final View scrollToView) {
        // 注册一个回调函数，当在一个视图树中全局布局发生改变或者视图树中的某个视图的可视状态发生改变时调用这个回调函数。
        root.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        Rect rect = new Rect();
                        // 获取root在窗体的可视区域
                        root.getWindowVisibleDisplayFrame(rect);
                        // 当前视图最外层的高度减去现在所看到的视图的最底部的y坐标
                        int rootInvisibleHeight = root.getRootView()
                                .getHeight() - rect.bottom;

                        // 若rootInvisibleHeight高度大于150(这个值有待动态计算)，则说明当前视图上移了，说明软键盘弹出了
                        if (rootInvisibleHeight > 350) {
                            //软键盘弹出来的时候
                            int[] location = new int[2];
                            // 获取scrollToView在窗体的坐标
                            scrollToView.getLocationInWindow(location);
                            // 计算root滚动高度，使scrollToView在可见区域的底部
                            int srollHeight = (location[1] + scrollToView
                                    .getHeight()) - rect.bottom;
                            if (srollHeight > 0) {
                                root.scrollTo(0, srollHeight);
                            }

                        } else {
                            // 软键盘没有弹出来的时候
                            root.scrollTo(0, 0);
                            LogUtil.i("----------");
                        }
                    }
                });
    }

    /**
     * 提交确认问题操作
     * @param problemId
     * @param planType
     * @param problemConfirmType
     * @return
     */
    private void confirmProblem(String problemId,String planType,String problemConfirmType) {
        QuestionManager.getInstance().confirmProblem(problemId,planType,problemConfirmType)
                .subscribe(new LoadingSubject<BaseResponse>(true,Utils.getContext().getString(R.string.data_loading)) {
                    @Override
                    protected void _onNext(BaseResponse baseResponse) {
                        if (baseResponse != null) {
                            if (baseResponse.getCode() == 0) {
                                LogUtil.e(TAG,"confirmProblem--请求成功 ");
                                ToastUtils.shortToast("提交成功");
                                ProblemCallListActivity.has_success_call = true;
                                finish();

                            }
                        }
                    }

                    @Override
                    protected void _onError(String message) {
                        LogUtil.e(TAG,"confirmProblem--请求失败 ");
                        ToastUtils.shortToast("提交失败");

                    }
                });
    }

    /**
     * 提交审核问题操作
     * @param problemId
     * @param executeType
     * @param executeDes
     * @param isAnimal
     */
    private void reviewProblem(String problemId,String executeType,String executeDes,String isAnimal) {
        QuestionManager.getInstance().reviewProblem(problemId,executeType,executeDes,isAnimal)
                .subscribe(new LoadingSubject<BaseResponse>(true,Utils.getContext().getString(R.string.data_loading)) {
                    @Override
                    protected void _onNext(BaseResponse baseResponse) {
                        if (baseResponse != null) {
                            if (baseResponse.getCode() == 0) {
                                LogUtil.e(TAG,"reviewProblem--请求成功 ");
                                ToastUtils.shortToast("提交成功");
                                ProblemConfigureListActivity.has_success_configure = true;
                                finish();

                            }
                        }
                    }

                    @Override
                    protected void _onError(String message) {
                        LogUtil.e(TAG,"reviewProblem--请求失败 ");
                        ToastUtils.shortToast("提交失败");

                    }
                });
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if(da)
        if (selectedPhotos != null) {
            selectedPhotos.clear();
        }
    }



    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.sendFankuiBtn){
            String excutestr = excuteDesEv.getText().toString();
            if (TextUtils.isEmpty(excutestr)) {
                ToastUtils.shortToast("请输入意见");
                return;
            }
            feedback(problemId,excutestr);

        }else if(v.getId() == R.id.sendQuerenBtn){
            if (TextUtils.isEmpty(problemConfirmTypeStr)) {
                ToastUtils.shortToast("请选择问题标记");
                return;
            }

            if (!"0".equals(problemConfirmTypeStr)) {
                if (TextUtils.isEmpty(typeStr)) {
                    ToastUtils.shortToast("请选择问题分配类型");
                    return;
                }
            }

            confirmProblem(problemId,typeStr, problemConfirmTypeStr);
        }else if(v.getId() == R.id.statusUpTv){
            // TODO: 2018/9/5 数据字典
            DictMapEntity dictMapEntity = DictMapManager.getInstance().getmDictMap();
            List<DictMapEntity.ArrayBean.NameValueBean> valueBeanList = dictMapEntity.getArray().getProblemCofirmType();
            showStatusUpTvDialog(valueBeanList);
        } else if(v.getId() == R.id.typeUpTv){
            // TODO: 2018/9/5 数据字典
            DictMapEntity dictMapEntity = DictMapManager.getInstance().getmDictMap();
            List<DictMapEntity.ArrayBean.NameValueBean> valueBeanList = dictMapEntity.getArray().getOperationType();
            showTypeDialog(valueBeanList);
        }else if(v.getId() == R.id.sendShenheBtn){
            executeDes = shenheEv.getText().toString();
            if (radio_shen_no.isChecked() && TextUtils.isEmpty(executeDes)) {
                ToastUtils.shortToast("请输入审核意见");
                return;
            }
            reviewProblem(problemId,executeType,executeDes,isAnimal);
        }
    }
}
