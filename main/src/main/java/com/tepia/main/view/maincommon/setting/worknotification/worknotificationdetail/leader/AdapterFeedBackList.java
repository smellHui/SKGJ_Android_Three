package com.tepia.main.view.maincommon.setting.worknotification.worknotificationdetail.leader;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.main.model.worknotification.FeedBackWorkNoticeBean;
import com.tepia.main.model.worknotification.WorkNoticeFeedbackBean;

import java.util.List;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2018-09-30
 * Time            :       14:02
 * Version         :       1.0
 * 功能描述        :
 **/
public class AdapterFeedBackList extends BaseQuickAdapter<WorkNoticeFeedbackBean, BaseViewHolder> {
    public AdapterFeedBackList(int layoutResId, @Nullable List<WorkNoticeFeedbackBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WorkNoticeFeedbackBean item) {
//        LvW
    }
}
