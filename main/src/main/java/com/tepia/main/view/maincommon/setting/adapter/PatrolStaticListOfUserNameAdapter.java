package com.tepia.main.view.maincommon.setting.adapter;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.base.utils.DoubleClickUtil;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.view.dialog.permissiondialog.Px2dpUtils;
import com.tepia.main.ConfigConsts;
import com.tepia.main.R;
import com.tepia.main.model.user.ContactBean;
import com.tepia.main.model.user.ReserviorBookBean;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by      Android studio
 *
 * @author :ly (from Center Of Wuhan)
 * 创建时间 :2018-12-29
 * 更新时间 :2019-1-3
 * Version :1.0
 * 功能描述 :巡河统计用户列表适配器
 **/
public class PatrolStaticListOfUserNameAdapter extends BaseQuickAdapter<ReserviorBookBean.DataBean, BaseViewHolder> {
//     private RiverLogStaticlistItemBinding mBingding ;

    /**
     * 是否展开列表缓存记录
     */
    private SparseBooleanArray mBooleanMap;


    /**
     * 子item对应的网络数据实体
     */
    private Map<Integer, ReserviorBookBean.DataBean> resultBeanHashMap;



    public SparseBooleanArray getmBooleanMap() {
        return mBooleanMap;
    }


    private Context mContext;


    public PatrolStaticListOfUserNameAdapter(Context context, int layoutResId, @Nullable List<ReserviorBookBean.DataBean> data) {
        super(layoutResId, data);
        mContext = context;
        mBooleanMap = new SparseBooleanArray();
        resultBeanHashMap = new HashMap<>();
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

    /**
     * 默认展开第一个
     */
    public void expendFirst() {
        mBooleanMap.clear();
        mBooleanMap.put(0, true);
        notifyDataSetChanged();
    }

    private void startRightIvAnimation(View view, float start, float end, int duration) {
        ObjectAnimator rotation = ObjectAnimator.ofFloat(view, "rotation", start, end);
        rotation.setDuration(duration);
        rotation.start();
    }

    @Override
    protected void convert(BaseViewHolder helper, ReserviorBookBean.DataBean listBean) {
        int position = helper.getAdapterPosition();
        helper.setText(R.id.patroluserNameTv, listBean.getReservoir());
        ExpandableLayout expandableLayout = helper.getView(R.id.expandable_layout);
        View vLeft = helper.getView(R.id.v_left);
        ImageView ivRiverCourseExpend = helper.getView(R.id.iv_river_course_expend);
        View rl_title = helper.getView(R.id.rl_river_patrol_title);
        if (mBooleanMap.get(position)) {
            expandableLayout.expand(false);
            startRightIvAnimation(ivRiverCourseExpend, 0f, 180f, 0);

            startLeftViewAnimation(Px2dpUtils.dip2px(mContext, 60), Px2dpUtils.dip2px(mContext, 20), 0, vLeft);
            getResuluBean(helper.getView(R.id.nameOfRec), listBean, position);

        } else {
            expandableLayout.collapse(false);
            startRightIvAnimation(ivRiverCourseExpend, 180f, 0f, 0);
            startLeftViewAnimation(Px2dpUtils.dip2px(mContext, 20), Px2dpUtils.dip2px(mContext, 60), 0, vLeft);
        }

        rl_title.setOnClickListener(v -> {

            if (expandableLayout.isExpanded()) {
                startRightIvAnimation(ivRiverCourseExpend, 180f, 0f, ConfigConsts.duration);
                expandableLayout.collapse();
                mBooleanMap.put(position, false);
                startLeftViewAnimation(Px2dpUtils.dip2px(mContext, 20), Px2dpUtils.dip2px(mContext, 60), ConfigConsts.duration, vLeft);
            } else {
                expandableLayout.expand();
                mBooleanMap.put(position, true);
                startRightIvAnimation(ivRiverCourseExpend, 0f, 180f, ConfigConsts.duration);
                startLeftViewAnimation(Px2dpUtils.dip2px(mContext, 60), Px2dpUtils.dip2px(mContext, 20), ConfigConsts.duration, vLeft);
                getResuluBean(helper.getView(R.id.nameOfRec), listBean, position);


            }


        });


    }

    /**
     * 更新子item
     *
     * @param listBean
     * @param position
     */
    private void getResuluBean(RecyclerView nameOfRec, ReserviorBookBean.DataBean listBean, int position) {
        List<ContactBean> contactBeanList = listBean.getUserList();
        if (listBean != null && contactBeanList.size() > 0) {
            ListChildBookAdapter listChildBookAdapter = new ListChildBookAdapter(R.layout.lv_contact_list_item_first, contactBeanList);

            nameOfRec.setLayoutManager(new LinearLayoutManager(mContext));
            nameOfRec.setAdapter(listChildBookAdapter);
            listChildBookAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                    if (DoubleClickUtil.isFastDoubleClick()){
                        return;
                    }
                    String mobileStr = listChildBookAdapter.getData().get(position).getMobile();
                    if (!TextUtils.isEmpty(mobileStr)) {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        Uri data = Uri.parse("tel:" + mobileStr);
                        intent.setData(data);
                        mContext.startActivity(intent);
                    }else{
                        ToastUtils.shortToast("手机号码为空");
                    }



                }
            });


        }


    }


}
