package com.tepia.main.view.mainworker.setting.voiceassistant;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.main.databinding.LvItemReadmeListBinding;

import java.util.List;

public class AdapterReadmeList extends BaseQuickAdapter<ReadMeBean, BaseViewHolder> {
    public AdapterReadmeList(int layoutResId, @Nullable List<ReadMeBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ReadMeBean item) {
        LvItemReadmeListBinding mBinding = DataBindingUtil.bind(helper.itemView);
        mBinding.tvItemCount.setText(helper.getLayoutPosition() + 1 + "");
        mBinding.tvContent.setText(item.getContent());
        mBinding.tvTitle.setText(item.getTitle());
        mBinding.tvDescribe.setText(item.getDesc());
    }
}
