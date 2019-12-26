package com.tepia.main.view.main.work.task2.taskedit;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.base.view.dialog.permissiondialog.Px2dpUtils;
import com.tepia.main.R;
import com.tepia.main.model.task.bean.TaskItemBean;
import com.tepia.main.model.task.bean.TaskItemConfigBean;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.List;

/**
 * Created by Android Studio
 *
 * @author : Arthur
 * Date :    2019/12/23
 * Time :    13:56
 * Describe :
 */
public class AdapterTaskItemParent extends BaseQuickAdapter<TaskItemConfigBean, BaseViewHolder> {

    /**
     * 是否展开列表缓存记录
     */
    private SparseBooleanArray mBooleanMap;

    public AdapterTaskItemParent(int layoutResId, @Nullable List<TaskItemConfigBean> data) {
        super(layoutResId, data);
        mBooleanMap = new SparseBooleanArray();
    }

    private void startLeftViewAnimation(int start, int end, int duration, View view) {
        // 创建动画作用对象：此处以Button为例

        // 步骤1：设置属性数值的初始值 & 结束值
        ValueAnimator valueAnimator = ValueAnimator.ofInt(start, end);
        // 初始值 = 当前按钮的宽度，此处在xml文件中设置为150
        // 结束值 = 500
        // ValueAnimator.ofInt()内置了整型估值器,直接采用默认的.不需要设置
        // 即默认设置了如何从初始值150 过渡到 结束值500

        // 步骤2：设置动画的播放各种属性
        valueAnimator.setDuration(duration);
        // 设置动画运行时长:1s

        // 步骤3：将属性数值手动赋值给对象的属性:此处是将 值 赋给 按钮的宽度
        // 设置更新监听器：即数值每次变化更新都会调用该方法
        valueAnimator.addUpdateListener(animator -> {

            int currentValue = (Integer) animator.getAnimatedValue();
            // 获得每次变化后的属性值
            System.out.println(currentValue);
            // 输出每次变化后的属性值进行查看

            view.getLayoutParams().height = currentValue;
            // 每次值变化时，将值手动赋值给对象的属性
            // 即将每次变化后的值 赋 给按钮的宽度，这样就实现了按钮宽度属性的动态变化

            // 步骤4：刷新视图，即重新绘制，从而实现动画效果
            view.requestLayout();

        });

        valueAnimator.start();
    }

    private void startRightIvAnimation(View view, float start, float end, int duration) {
        ObjectAnimator rotation = ObjectAnimator.ofFloat(view, "rotation", start, end);
        rotation.setDuration(duration);
        rotation.start();
    }

    @Override
    protected void convert(BaseViewHolder helper, TaskItemConfigBean item) {
        int position = helper.getAdapterPosition();

        helper.setText(R.id.tv_tittle, item.getTittle().replace("/", ">"));
        ExpandableLayout expandableLayout = helper.getView(R.id.expandable_layout);
        View vLeft = helper.getView(R.id.v_left);
        ImageView ivRiverCourseExpend = helper.getView(R.id.iv_arrow_expend);
        View rltitle = helper.getView(R.id.rl_item_title);

        if (mBooleanMap.get(position)) {
            expandableLayout.expand(false);
            startRightIvAnimation(ivRiverCourseExpend, 0f, 180f, 0);
            startLeftViewAnimation(Px2dpUtils.dip2px(mContext, 60), Px2dpUtils.dip2px(mContext, 20), 0, vLeft);
            getResuluBean(helper.getView(R.id.nameOfRec), item, position);

        } else {
            expandableLayout.collapse(false);
            startRightIvAnimation(ivRiverCourseExpend, 180f, 0f, 0);
            startLeftViewAnimation(Px2dpUtils.dip2px(mContext, 20), Px2dpUtils.dip2px(mContext, 60), 0, vLeft);
        }

        rltitle.setOnClickListener(v -> {

            if (expandableLayout.isExpanded()) {
                startRightIvAnimation(ivRiverCourseExpend, 180f, 0f, 100);
                expandableLayout.collapse();
                mBooleanMap.put(position, false);
                startLeftViewAnimation(Px2dpUtils.dip2px(mContext, 20), Px2dpUtils.dip2px(mContext, 60), 100, vLeft);
            } else {
                expandableLayout.expand();
                mBooleanMap.put(position, true);
                startRightIvAnimation(ivRiverCourseExpend, 0f, 180f, 100);
                startLeftViewAnimation(Px2dpUtils.dip2px(mContext, 60), Px2dpUtils.dip2px(mContext, 20), 100, vLeft);
                getResuluBean(helper.getView(R.id.nameOfRec), item, position);
            }
        });
    }


    /**
     * 更新子item
     */
    private void getResuluBean(RecyclerView recyclerView, TaskItemConfigBean taskItemConfigBean, int position) {
        // 查找子集
        List<TaskItemBean> taskItemBeans = taskItemConfigBean.getList();
        if (taskItemBeans.size() > 0) {
            AdapterTaskItemChild adapterTaskItemChild = new AdapterTaskItemChild(mContext, taskItemBeans);
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            recyclerView.setAdapter(adapterTaskItemChild);
        }
    }

    public void noSelectAll() {
        List<TaskItemConfigBean> mlist = getData();
        for (TaskItemConfigBean taskItemConfigBean : mlist) {
            List<TaskItemBean> list = taskItemConfigBean.getList();
            for (TaskItemBean taskItemBean : list) {
                taskItemBean.setSelected(false);
            }
        }
        notifyDataSetChanged();
    }


    public void selectAll() {
        List<TaskItemConfigBean> mlist = getData();
        for (TaskItemConfigBean taskItemConfigBean : mlist) {
            List<TaskItemBean> list = taskItemConfigBean.getList();
            for (TaskItemBean taskItemBean : list) {
                taskItemBean.setSelected(true);
            }
        }
        notifyDataSetChanged();
    }


    public String getItemConfigString2() {
        String superviseIds = "";
        List<TaskItemConfigBean> mlist = getData();
        for (TaskItemConfigBean bean : mlist) {
            List<TaskItemBean> taskItemBeans = bean.getList();
            for (TaskItemBean taskItemBean : taskItemBeans) {
                if (taskItemBean.isSelected()) {
                    superviseIds += taskItemBean.getReservoirSuperviseId() + ",";
                }
            }
        }
        if (superviseIds.endsWith(",")) {
            superviseIds = superviseIds.substring(0, superviseIds.lastIndexOf(','));
        }
        return superviseIds;
    }
}
