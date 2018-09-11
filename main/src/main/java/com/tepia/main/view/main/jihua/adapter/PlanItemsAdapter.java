package com.tepia.main.view.main.jihua.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.ResUtils;
import com.tepia.base.view.EasySwipeMenuLayout.EasySwipeMenuLayout;
import com.tepia.main.R;
import com.tepia.main.model.plan.PlanListResponse;

import java.util.List;

public class PlanItemsAdapter extends BaseQuickAdapter<PlanListResponse.DataBean.ListBean, BaseViewHolder> {

    private List<PlanListResponse.DataBean.ListBean> dataList;

    /**
     * 条目点击接口
     */
    public interface OnItemContentClickListener {
      void  onContentClick(View v, int adapterPosition, PlanListResponse.DataBean.ListBean item, EasySwipeMenuLayout easySwipeMenuLayout);
    }

    private OnItemContentClickListener onItemContentClickListener = null;

    public void setOnItemContentClickListener(OnItemContentClickListener onItemContentClickListener) {
        this.onItemContentClickListener = onItemContentClickListener;
    }

    /**
     * 条目编辑接口
     */
    public interface OnItemEditClickListener {
        void  onItemEditClick(View v, int adapterPosition, PlanListResponse.DataBean.ListBean item, EasySwipeMenuLayout easySwipeMenuLayout);
    }

    private OnItemEditClickListener onItemEditClickListener = null;

    public void setOnItemEditClickListener(OnItemEditClickListener onItemEditClickListener) {
        this.onItemEditClickListener = onItemEditClickListener;
    }

    /**
     * 条目删除接口
     */
    public interface OnItemDeleteClickListener{
        void onDeleteClick(View v, int adapterPosition, PlanListResponse.DataBean.ListBean item, EasySwipeMenuLayout easySwipeMenuLayout);
    }

    private OnItemDeleteClickListener onItemDeleteClickListener = null;

    public void setOnDeleteClickListener(OnItemDeleteClickListener onItemDeleteClickListener) {
        this.onItemDeleteClickListener = onItemDeleteClickListener;
    }

    /**
     * 生成工单接口
     */
    public interface OnItemCreateClickListener{
        void onCreateClick(View v, int adapterPosition, PlanListResponse.DataBean.ListBean item, EasySwipeMenuLayout easySwipeMenuLayout);
    }

    private OnItemCreateClickListener onItemCreateClickListener = null;

    public void setOnItemCreateClickListener(OnItemCreateClickListener onItemCreateClickListener) {
        this.onItemCreateClickListener = onItemCreateClickListener;
    }

    //是否显示左滑菜单
    private SparseBooleanArray isShowRights;

    public void setIsShowRights(SparseBooleanArray isShowRights) {
        this.isShowRights = isShowRights;
    }

    public PlanItemsAdapter(int layoutResId, @Nullable List<PlanListResponse.DataBean.ListBean> data) {
        super(layoutResId, data);
        dataList = data;
        isShowRights = new SparseBooleanArray();
    }

    @Override
    protected void convert(BaseViewHolder helper, PlanListResponse.DataBean.ListBean item) {
        int adapterPosition = helper.getAdapterPosition();
//        LogUtil.i("position"+adapterPosition);
        isShowRights.put(adapterPosition,"1".equals(item.getIsGenerate()));
        TextView tv_type = helper.getView(R.id.tv_type);
        TextView tv_title = helper.getView(R.id.tv_title);
        TextView tv_desc = helper.getView(R.id.tv_desc);
        TextView tv_isGenerate = helper.getView(R.id.tv_isGenerate);
        TextView tv_time = helper.getView(R.id.tv_time);
        TextView tv_addr = helper.getView(R.id.tv_addr);
        EasySwipeMenuLayout easySwipeMenuLayout = helper.getView(R.id.es);
        if (isShowRights.get(adapterPosition)){
            //已生成工单不能左滑
//            easySwipeMenuLayout.setCanRightSwipe(false);
            easySwipeMenuLayout.setCanLeftSwipe(false);
//            helper.getView(R.id.right).setVisibility(View.GONE);
        }else {
//            easySwipeMenuLayout.setCanRightSwipe(true);
            easySwipeMenuLayout.setCanLeftSwipe(true);
//            helper.getView(R.id.right).setVisibility(View.VISIBLE);
        }
        if ("1".equals(item.getIsGenerate())) {
            tv_isGenerate.setText("已生成");
            tv_isGenerate.setBackgroundColor(Color.parseColor("#009688"));
        }else {
            tv_isGenerate.setText("未生成");
            tv_isGenerate.setBackgroundColor(Color.parseColor("#CCD7D6"));
        }
//        (1：日常；2：定期；3：特别)
        switch (item.getPlanType()) {
            case "1":
                tv_type.setText("日常巡检");
                tv_type.setBackground(ResUtils.getResources().getDrawable(R.drawable.bg_lv_item_bt_01_type));
                break;
            case "2":
                tv_type.setText("定期巡检");
                tv_type.setBackground(ResUtils.getResources().getDrawable(R.drawable.bg_lv_item_bt_02_type));
                break;
            case "3":
                tv_type.setText("特别巡检");
                tv_type.setBackground(ResUtils.getResources().getDrawable(R.drawable.bg_lv_item_bt_03_type));
                break;
            default:
                break;
        }

        tv_title.setText(item.getPlanName());
        tv_desc.setText(item.getRemarks());
        tv_time.setText(item.getCreateDate());
        tv_addr.setText(item.getReservoirName());

        helper.getView(R.id.right_menu_2).setOnClickListener(v -> {
//            Toast.makeText(getContext(), "编辑", Toast.LENGTH_SHORT).show();
            onItemEditClickListener.onItemEditClick(v,adapterPosition,item,easySwipeMenuLayout);
            easySwipeMenuLayout.resetStatus();
        });
        helper.getView(R.id.content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(), "itemOnClick", Toast.LENGTH_SHORT).show();
                  onItemContentClickListener.onContentClick(v,adapterPosition,item,easySwipeMenuLayout);
                  easySwipeMenuLayout.resetStatus();
            }
        });

        helper.getView(R.id.right_menu_delete).setOnClickListener(v->{
            onItemDeleteClickListener.onDeleteClick(v,adapterPosition,item,easySwipeMenuLayout);
        });

        helper.getView(R.id.tv_right_create).setOnClickListener(v -> onItemCreateClickListener.onCreateClick(v,adapterPosition,item,easySwipeMenuLayout));
    }
}
