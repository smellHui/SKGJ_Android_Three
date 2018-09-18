package com.tepia.main.view.mainworker.homepage;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.main.model.user.UserInfoBean;

import java.util.List;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2018-09-17
 * Time            :       14:33
 * Version         :       1.0
 * 功能描述        :       主页 - 首页 水库人员 适配器
 **/
public class AdapterWorker extends BaseQuickAdapter<UserInfoBean, BaseViewHolder> {
    public AdapterWorker(int layoutResId, @Nullable List<UserInfoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserInfoBean item) {

    }

}
