package com.tepia.main.view.main.jihua;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.inpor.fastmeetingcloud.activity.SearchActivity;
import com.tepia.base.http.BaseResponse;
import com.tepia.base.mvp.MVPBaseFragment;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.view.EasySwipeMenuLayout.EasySwipeMenuLayout;
import com.tepia.base.view.dialog.permissiondialog.Px2dpUtils;
import com.tepia.main.MyApplication;
import com.tepia.main.R;
import com.tepia.main.model.plan.PlanListResponse;
import com.tepia.main.utils.EmptyLayoutUtil;
import com.tepia.main.view.main.jihua.adapter.PlanItemsAdapter;
import com.tepia.main.view.main.jihua.presenter.InspectionPlanContract;
import com.tepia.main.view.main.jihua.presenter.InspectionPlanPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * 运维计划列表
 *
 * @author 44822
 */
public class PlanItemsFragment extends MVPBaseFragment<InspectionPlanContract.View, InspectionPlanPresenter> {
    private String typeStr;
    private PlanItemsAdapter adapterPlanList;
    private RecyclerView rvPlanList;
    private int pageSize = 10;
    private int currentPage = 1;
    private int mCurrentCounter = 0;
    private boolean first;
    private boolean isloadmore;
    private SwipeRefreshLayout srl;
    private List<PlanListResponse.DataBean.ListBean> dataList = new ArrayList<>();
    private String operationType;
    //当时水库关键字
    private String currentReservoirname = "";
    private EditText mSearchText;
    private ImageView mClearText;

    public static PlanItemsFragment newInstance(String typekey) {
        PlanItemsFragment f = new PlanItemsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("TYPEKEY", typekey);
        f.setArguments(bundle);
        return f;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_plan_items;
    }

    @Override
    protected void initData() {
//        for (int i = 0; i < 20; i++) {
//            listdata.add(new PlanListResponse.DataBean.ListBean());
//        }
    }

    @Override
    protected void initView(View view) {
        typeStr = getArguments().getString("TYPEKEY");
//        LogUtil.i("typeStr:" + typeStr);
        if (getString(R.string.zonglanstr).equals(typeStr)) {
            operationType = "";
        } else if (getString(R.string.xunjianstr).equals(typeStr)) {
            operationType = "1";
        } else if (getString(R.string.weihustr).equals(typeStr)) {
            operationType = "2";
        } else if (getString(R.string.baojiestr).equals(typeStr)) {
            operationType = "3";
        }
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(view1 -> startActivityForResult(new Intent(getContext(), AddPlanActivity.class), 1));
        mSearchText = findView(R.id.et_search);
        mClearText = findView(R.id.iv_clear);
        TextView tv_search = findView(R.id.tv_search);
        mSearchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()!=0){
                    mClearText.setVisibility(View.VISIBLE);
                }else {
                    mClearText.setVisibility(View.GONE);
                }
            }
        });
        //设置搜索监听事件
        mSearchText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH){
                //关闭软键盘
                hideKeyboard(v);
                srl.setRefreshing(true);
//                LogUtil.i("mSearchText"+v.getText().toString());
                String searchString = v.getText().toString().replace(" ", "");
                currentReservoirname = searchString;
                search(true);
                return true;
            }
            return false;
        });
        tv_search.setOnClickListener(v->{
            String searchString = mSearchText.getText().toString().replace(" ", "");
            currentReservoirname = searchString;
            search(true);
        });
        mClearText.setOnClickListener(v -> mSearchText.setText(""));
        initSwipeRefreshLayout(view);
        initRecycleView(view);
        srl.setRefreshing(true);
        srl.postDelayed(() -> {
            search(false);
        },500);
    }

    //隐藏软键盘
    public static void hideKeyboard(View v)
    {
        InputMethodManager imm = ( InputMethodManager ) v.getContext( ).getSystemService( Context.INPUT_METHOD_SERVICE );
        if ( imm.isActive( ) ) {
            imm.hideSoftInputFromWindow( v.getApplicationWindowToken( ) , 0 );

        }
    }

    private void initSwipeRefreshLayout(View view) {
        srl = findView(R.id.srl);
        srl.setOnRefreshListener(() -> {
            srl.postDelayed((Runnable) () -> {
                search(false);
//                srl.setRefreshing(false);
            }, 1000);
        });
    }

    private void search(boolean isshowloadiing) {
        adapterPlanList.setEnableLoadMore(false);
        currentPage = 1;
        first = true;
        isloadmore = false;
        //刷新
        loadDataOrMore(isshowloadiing);
    }

    private void initRecycleView(View view) {
        rvPlanList = findView(R.id.rv_plan_list);
//        isShowRights = new SparseBooleanArray();
        rvPlanList.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterPlanList = new PlanItemsAdapter(R.layout.fragment_plan_list_item, dataList);
        adapterPlanList.openLoadAnimation();
        rvPlanList.setAdapter(adapterPlanList);
        adapterPlanList.setOnLoadMoreListener(() -> {
            rvPlanList.postDelayed(() -> {
                currentPage++;
                first = false;
                isloadmore = true;
                //加载更多数据
                loadDataOrMore(false);
            }, 1000);
        }, rvPlanList);
        setListAdapterClick();
    }

    private int currentEditPositon;

    private void setListAdapterClick() {
        adapterPlanList.setOnDeleteClickListener((v, adapterPosition, item, easySwipeMenuLayout) -> {
//            LogUtil.i("删除:" + adapterPosition);
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("温馨提示");
            builder.setMessage("确定删除" + item.getPlanName() + "吗？");
            builder.setPositiveButton("确定", (dialog, which) -> {
                //模拟删除数据，刷新数据列表定位到之前删除的位置
//                adapterPlanList.notifyDataSetChanged();
//                ((LinearLayoutManager)rvPlanList.getLayoutManager()).scrollToPositionWithOffset(adapterPosition, Px2dpUtils.dip2px(getContext(),200));
                deletePlan(adapterPosition, item);
            });
            builder.setNegativeButton("取消", (dialog, which) -> {
                ToastUtils.shortToast("取消");
                easySwipeMenuLayout.resetStatus();
            });
            builder.show();
        });
        adapterPlanList.setOnItemContentClickListener((v, adapterPosition, item, easySwipeMenuLayout) -> {
//            LogUtil.i("条目:" + adapterPosition);
            Intent intent = new Intent(getContext(), PlanDetailActivity.class);
            intent.putExtra("item", item);
            startActivity(intent);
        });
        adapterPlanList.setOnItemEditClickListener((v, adapterPosition, item, easySwipeMenuLayout) -> {
//            LogUtil.i("编辑:" + adapterPosition);
            currentEditPositon = adapterPosition;
            Intent intent = new Intent(getContext(), EditPlanActivity.class);
            intent.putExtra("item", item);
            startActivityForResult(intent,2);
        });
        adapterPlanList.setOnItemCreateClickListener((v, adapterPosition, item, easySwipeMenuLayout) -> {
            currentEditPositon = adapterPosition;
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("温馨提示");
            builder.setMessage("确定生成" + item.getPlanName() + "工单吗？");
            builder.setPositiveButton("确定", (dialog, which) -> {
               createWorkOrder(adapterPosition, item,easySwipeMenuLayout);
            });
            builder.setNegativeButton("取消", (dialog, which) -> {
                ToastUtils.shortToast("取消");
                easySwipeMenuLayout.resetStatus();
            });
            builder.show();
        });
    }

    private void createWorkOrder(int adapterPosition, PlanListResponse.DataBean.ListBean item, EasySwipeMenuLayout easySwipeMenuLayout) {
        mPresenter.attachView(new InspectionPlanContract.View<BaseResponse>() {
            @Override
            public void success(BaseResponse data) {
                item.setIsGenerate("1");
                //工单生成成功
                if (adapterPosition<=dataList.size()-1){
                    dataList.set(adapterPosition,item);
                    adapterPlanList.notifyItemChanged(adapterPosition);
                }
            }

            @Override
            public void failure(String msg) {
                easySwipeMenuLayout.resetStatus();
                ToastUtils.longToast(msg);
            }

            @Override
            public Context getContext() {
                return null;
            }
        });
        mPresenter.createWorkOrder(item.getPlanId());
    }

    private boolean isDeletePlan = false;

    private void deletePlan(int position, PlanListResponse.DataBean.ListBean item) {
        mPresenter.attachView(new InspectionPlanContract.View<BaseResponse>() {
            @Override
            public void success(BaseResponse data) {
                //删除成功
                ToastUtils.longToast("删除成功");
                //刷新数据,定位到之前删除计划的地方
                pageSize = (position / 10 + 1) * 10;
                currentPage = 1;
                isDeletePlan = true;
                first = true;
                isloadmore = false;
                currentEditPositon = position;
                if (mPresenter != null) {
                    mPresenter.getPlanInfoList(currentReservoirname, "", operationType, "", "", "", String.valueOf(currentPage), String.valueOf(pageSize), false);
                }
            }

            @Override
            public void failure(String msg) {
                ToastUtils.longToast(msg);
            }

            @Override
            public Context getContext() {
                return null;
            }
        });
        mPresenter.deleteByIds(item.getPlanId());
    }

    private void loadDataOrMore(Boolean isShowLoading) {
        mPresenter.attachView(new InspectionPlanContract.View<PlanListResponse>() {
            @Override
            public void success(PlanListResponse planListResponse) {
//                LogUtil.i("code"+planListResponse.getCode());
                PlanListResponse.DataBean dataBean = planListResponse.getData();
                List<PlanListResponse.DataBean.ListBean> data = dataBean.getList();
//                LogUtil.i("data:" + data.size());
                int totalSize = dataBean.getTotal();
                if (first) {
                    //首次加载
                    if (data == null || data.size() == 0) {
                        LogUtil.i("搜索为空");
                        dataList.clear();
                        adapterPlanList.setEmptyView(EmptyLayoutUtil.show(getString(R.string.empty_tv)));
                        adapterPlanList.notifyDataSetChanged();
                    } else {
                        dataList.clear();
                        dataList.addAll(data);
                        adapterPlanList.notifyDataSetChanged();
                        first = false;
                        if (isDeletePlan||isEditPlaned) {
//                            ((LinearLayoutManager) rvPlanList.getLayoutManager()).scrollToPositionWithOffset(currentEditPositon, Px2dpUtils.dip2px(MyApplication.getContext(), 200));
                            pageSize = 10;
                            currentPage = currentEditPositon/10+1;
                            isDeletePlan = false;
                            isEditPlaned = false;
                        }
                    }
                    adapterPlanList.setEnableLoadMore(true);
                    srl.setRefreshing(false);
                } else if (isloadmore) {
                    dataList.addAll(data);
                    adapterPlanList.notifyDataSetChanged();
                    mCurrentCounter = adapterPlanList.getData().size();
//                    LogUtil.i("mCurrentCounter:" + mCurrentCounter);
                    if (mCurrentCounter >= totalSize) {
                        //数据全部加载完毕
                        adapterPlanList.loadMoreEnd();
                        return;
                    }
                    adapterPlanList.loadMoreComplete();
                }
//                LogUtil.i("dataList:" + dataList.size());
            }

            @Override
            public void failure(String msg) {
                if (first) {
                    srl.setRefreshing(false);
                }
                adapterPlanList.setEmptyView(EmptyLayoutUtil.show(msg));
                if (isloadmore) {
                    if (currentPage > 1) {
                        currentPage--;
//                        adapterPlanList.loadMoreComplete();
                        adapterPlanList.loadMoreFail();
                    }
                }
                ToastUtils.shortToast(msg);
            }

            @Override
            public Context getContext() {
                return null;
            }
        });
        if (mPresenter != null) {
            mPresenter.getPlanInfoList(currentReservoirname, "", operationType, "", "", "", String.valueOf(currentPage), String.valueOf(pageSize), isShowLoading);
        }
    }

    @Override
    protected void initRequestData() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (dataList != null) {
            dataList.clear();
            dataList = null;
        }
    }

    private boolean isEditPlaned = false;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        LogUtil.i("requestCode:" + requestCode + "---------resultCode:" + resultCode);
        switch (requestCode) {
            case 1:
                //添加计划返回
                Bundle extras = data.getExtras();
                if (extras!=null){
                    boolean isAddPlan =extras.getBoolean("isAddPlan");
//                    LogUtil.i("isAddPlan:"+isAddPlan);
                    if (isAddPlan){
                        search(false);
                    }
                }
                break;
            case 2:
                //编辑计划返回
                Bundle extrasEdit = data.getExtras();
                if (extrasEdit!=null){
                    boolean isEditPlan =extrasEdit.getBoolean("isEditPlan");
//                    LogUtil.i("isEditPlan:"+isEditPlan);
                    if (isEditPlan){
//                        search(false);
                        PlanListResponse.DataBean.ListBean item = (PlanListResponse.DataBean.ListBean) data.getSerializableExtra("item");
                       if (item!=null){
//                           LogUtil.i("eidtItem:"+item.toString());
                           pageSize = (currentEditPositon / 10 + 1) * 10;
                           currentPage = 1;
                           first = true;
                           isloadmore = false;
                           isEditPlaned = true;
//                           if (currentEditPositon<=dataList.size()-1){
//                               dataList.set(currentEditPositon,item);
//                               adapterPlanList.notifyItemChanged(currentEditPositon);
//                           }
                           if (mPresenter != null) {
                               mPresenter.getPlanInfoList(currentReservoirname, "", operationType, "", "", "", String.valueOf(currentPage), String.valueOf(pageSize), false);
                           }
                       }
                    }
                }
                break;
            default:
                break;
        }
    }
}
