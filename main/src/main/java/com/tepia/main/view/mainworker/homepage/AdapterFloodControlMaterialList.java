package com.tepia.main.view.mainworker.homepage;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.main.databinding.LvFloodControlMaterialItemBinding;
import com.tepia.main.model.user.homepageinfo.MaterialBean;
import com.tepia.main.model.weather.AqiBean;

import java.util.List;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2018-09-17
 * Time            :       14:47
 * Version         :       1.0
 * 功能描述        :        主页 - 首页 防汛物质 适配器
 **/
public class AdapterFloodControlMaterialList extends BaseQuickAdapter<MaterialBean, BaseViewHolder> {
    public AdapterFloodControlMaterialList(int layoutResId, @Nullable List<MaterialBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MaterialBean item) {
        LvFloodControlMaterialItemBinding mBinding = DataBindingUtil.bind(helper.itemView);
        mBinding.tvItemCount.setText(helper.getLayoutPosition() + 1 + "");
        mBinding.tvName.setText(item.getMeName());
        mBinding.tvMaterialCount.setText(item.getMeTotals());
        mBinding.tvDesc.setText("存放位置：" + item.getPosition());
    }
}
