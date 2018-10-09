package com.tepia.main.view.maincommon.setting.worknotification.addworknotification;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.main.databinding.LvItemSelectReservorsViewBinding;
import com.tepia.main.model.detai.ReservoirBean;
import com.tepia.main.model.user.UserManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2018-10-09
 * Time            :       9:44
 * Version         :       1.0
 * 功能描述        :
 **/
public class AdapterReservorList extends BaseQuickAdapter<ReservoirBean, BaseViewHolder> {

    public List<ReservoirBean> selectResrvoirList = new ArrayList<>();

    public AdapterReservorList(int layoutResId, @Nullable List<ReservoirBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ReservoirBean item) {
//        lv_item_select_reservors_view
        LvItemSelectReservorsViewBinding mBinding = DataBindingUtil.bind(helper.itemView);
        mBinding.tvReservoirName.setText(item.getReservoir());
        if (selectResrvoirList.contains(item)) {
            mBinding.ivSelect.setVisibility(View.VISIBLE);
        } else {
            mBinding.ivSelect.setVisibility(View.GONE);
        }
    }

    public List<ReservoirBean> getSelectResrvoirList() {
        return selectResrvoirList;
    }

    public void setSelectResrvoirList(List<ReservoirBean> selectResrvoirList) {
        this.selectResrvoirList = selectResrvoirList;
    }

    public void notifyDataSetChangedNew() {
        Collections.sort(getData(), new Comparator<ReservoirBean>() {
            @Override
            public int compare(ReservoirBean o1, ReservoirBean o2) {
                if (selectResrvoirList.contains(o1) && !selectResrvoirList.contains(o2)) {
                    return -1;
                } else if (!selectResrvoirList.contains(o1) && selectResrvoirList.contains(o2)) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        notifyDataSetChanged();
    }

    public void flterResrvoir(String s) {
        setNewData(UserManager.getInstance().getLocalReservoirList());
        List<ReservoirBean> flter = new ArrayList<>();
        for (ReservoirBean bean : getData()) {
            if (selectResrvoirList.contains(bean)) {
            } else {
                if (bean.getReservoir().contains(s)){

                }else {
                    flter.add(bean);
                }
            }
        }
        getData().removeAll(flter);
        notifyDataSetChangedNew();
    }
}
