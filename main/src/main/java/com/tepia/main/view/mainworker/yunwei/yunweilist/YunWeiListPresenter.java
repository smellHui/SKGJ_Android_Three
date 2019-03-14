package com.tepia.main.view.mainworker.yunwei.yunweilist;

import android.text.TextUtils;

import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.base.utils.ResUtils;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.view.floatview.CollectionsUtil;
import com.tepia.main.R;
import com.tepia.main.model.task.TaskManager;
import com.tepia.main.model.task.bean.TaskBean;
import com.tepia.main.model.task.response.TaskDetailResponse;
import com.tepia.main.model.task.response.TaskListResponse;
import com.tepia.main.model.user.UserManager;

import org.litepal.crud.DataSupport;

import java.util.Calendar;
import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class YunWeiListPresenter extends BasePresenterImpl<YunWeiListContract.View> implements YunWeiListContract.Presenter {
    int currentPage = 1;
    String pageSize = "10";
    public boolean isCanLoadMore = true;

    public void getPatrolWorkOrderList(String reservoirId, String operationType, String month) {
        String startDate = null;
        String endDate = null;
        String[] split = month.split("-");
        if (split.length == 2) {
            int dayOfMonth = getDayOfMonth(Integer.valueOf(split[0]), Integer.valueOf(split[1]));
            startDate = month + "-01 00:00:00";
            endDate = month + "-" + dayOfMonth + " 23:59:59";
        }
        currentPage = 1;
        boolean isShow = true;
        String msg = ResUtils.getString(R.string.data_loading);
        TaskManager.getInstance().getPatrolWorkOrderList(operationType, reservoirId, startDate, endDate, currentPage + "", pageSize + "")
                .subscribe(new LoadingSubject<TaskListResponse>(isShow, msg) {
                    @Override
                    protected void _onNext(TaskListResponse taskListResponse) {
                        mView.getPatrolWorkOrderListSuccess(taskListResponse.getData().getList());
                        for (TaskBean bean : taskListResponse.getData().getList()) {
                            bean.save();
                            getTaskDetail(bean.getWorkOrderId());
                        }
                        if (taskListResponse.getData().getPageNum() == taskListResponse.getData().getPages()) {
                            isCanLoadMore = false;
                        } else {
                            isCanLoadMore = true;
                        }
                        currentPage = taskListResponse.getData().getPageNum();
                    }

                    @Override
                    protected void _onError(String message) {
                        List<TaskBean> templist;
                        if (TextUtils.isEmpty(operationType)) {
                            if (TextUtils.isEmpty(reservoirId)) {
                                templist = DataSupport
                                        .where("username=? AND planStartTime like ?",
                                                UserManager.getInstance().getUserBean().getData().getUserCode()
                                                , month + "%")
                                        .order("planStartTime desc").limit(20).find(TaskBean.class);
                            } else {
                                templist = DataSupport
                                        .where("username=? AND reservoirId=?  AND planStartTime like ?",
                                                UserManager.getInstance().getUserBean().getData().getUserCode()
                                                , reservoirId
                                                , month + "%")
                                        .order("planStartTime desc").limit(20).find(TaskBean.class);
                            }
                        } else {
                            if (TextUtils.isEmpty(reservoirId)) {
                                templist = DataSupport
                                        .where("username=? AND operationType=? AND planStartTime like ?"
                                                , UserManager.getInstance().getUserBean().getData().getUserCode()
                                                , operationType
                                                , month + "%")
                                        .order("planStartTime desc").limit(20).find(TaskBean.class);
                            } else {
                                templist = DataSupport
                                        .where("username=? AND reservoirId=? AND operationType=? AND planStartTime like ? "
                                                , UserManager.getInstance().getUserBean().getData().getUserCode()
                                                , reservoirId
                                                , operationType
                                                , month + "%")
                                        .order("planStartTime desc").limit(20).find(TaskBean.class);
                            }
                        }
                        if (templist != null && templist.size() == 20) {
                            isCanLoadMore = true;
                        } else {
                            isCanLoadMore = false;
                        }
                        if (!CollectionsUtil.isEmpty(templist)) {
                            mView.getPatrolWorkOrderListSuccess(templist);
                            ToastUtils.shortToast(ResUtils.getString(R.string.local_data_tip));
                        } else {
                            ToastUtils.shortToast(message);
                        }

                    }
                });
    }

    private int getDayOfMonth(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.set(year, month, 0); //输入类型为int类型
        int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
//        LogUtil.i("一个月的天数:"+dayOfMonth);
        return dayOfMonth;
    }

    public void getPatrolWorkOrderListMore(String reservoirId, String operationType, String month) {
        String startDate = null;
        String endDate = null;
        String[] split = month.split("-");
        if (split.length == 2) {
            int dayOfMonth = getDayOfMonth(Integer.valueOf(split[0]), Integer.valueOf(split[1]));
            startDate = month + "-01 00:00:00";
            endDate = month + "-" + dayOfMonth + " 23:59:59";
        }
        boolean isShow = false;
        String msg = null;
        if (!isCanLoadMore) {
            return;
        }
        TaskManager.getInstance().getPatrolWorkOrderList(operationType, reservoirId, startDate, endDate, currentPage + 1 + "", pageSize).safeSubscribe(new LoadingSubject<TaskListResponse>(isShow, msg) {
            @Override
            protected void _onNext(TaskListResponse taskListResponse) {
                currentPage = taskListResponse.getData().getPageNum();
                for (TaskBean bean : taskListResponse.getData().getList()) {
                    bean.save();
                    getTaskDetail(bean.getWorkOrderId());
                }
                if (taskListResponse.getData().getPageNum() == taskListResponse.getData().getPages()) {
                    isCanLoadMore = false;
                } else {
                    isCanLoadMore = true;
                }
                mView.getPatrolWorkOrderListMoreSuccess(taskListResponse.getData());
            }

            @Override
            protected void _onError(String message) {
                mView.getPatrolWorkOrderListMoreFailure();
                ToastUtils.shortToast(message);
            }
        });
    }


    public void getTaskDetail(String workOrderId) {
        TaskManager.getInstance().getAppWorkByWorkId(workOrderId).subscribe(new LoadingSubject<TaskDetailResponse>() {
            @Override
            protected void _onNext(TaskDetailResponse taskDetailResponse) {
                if (taskDetailResponse.getCode() == 0) {
                    taskDetailResponse.getData().save();
                }
            }

            @Override
            protected void _onError(String message) {

                List<TaskBean> templist = DataSupport.where("workOrderId=?", workOrderId).find(TaskBean.class);
                if (!CollectionsUtil.isEmpty(templist)) {
                } else {
                }
            }
        });
    }
}
