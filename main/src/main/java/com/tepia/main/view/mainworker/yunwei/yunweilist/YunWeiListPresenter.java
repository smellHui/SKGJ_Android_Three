package com.tepia.main.view.mainworker.yunwei.yunweilist;

import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.base.utils.ResUtils;
import com.tepia.base.utils.ToastUtils;
import com.tepia.main.R;
import com.tepia.main.model.task.TaskManager;
import com.tepia.main.model.task.response.TaskListResponse;

import java.util.Calendar;

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
        TaskManager.getInstance().getPatrolWorkOrderList(operationType, reservoirId, startDate, endDate, currentPage + "", pageSize + "").safeSubscribe(new LoadingSubject<TaskListResponse>(isShow, msg) {
            @Override
            protected void _onNext(TaskListResponse taskListResponse) {
                mView.getPatrolWorkOrderListSuccess(taskListResponse.getData().getList());
                if (taskListResponse.getData().getPageNum() == taskListResponse.getData().getPages()) {
                    isCanLoadMore = false;
                } else {
                    isCanLoadMore = true;
                }
                currentPage = taskListResponse.getData().getPageNum();
            }

            @Override
            protected void _onError(String message) {
                ToastUtils.shortToast(message);
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
                if (taskListResponse.getData().getPageNum() == taskListResponse.getData().getPages()) {
                    isCanLoadMore = false;
                } else {
                    isCanLoadMore = true;
                }
                mView.getPatrolWorkOrderListMoreSuccess(taskListResponse.getData());
            }

            @Override
            protected void _onError(String message) {
                ToastUtils.shortToast(message);
            }
        });
    }


}
