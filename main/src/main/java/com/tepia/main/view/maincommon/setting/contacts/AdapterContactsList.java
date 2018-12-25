package com.tepia.main.view.maincommon.setting.contacts;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.base.utils.Utils;
import com.tepia.main.R;
import com.tepia.main.databinding.LvContactListItemBinding;
import com.tepia.main.model.user.ContactBean;
import com.tepia.voice.xunfei.DemoBean;

import java.util.List;
import java.util.Random;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2018-09-19
 * Time            :       17:07
 * Version         :       1.0
 * 功能描述        :        通信录列表适配器
 **/
public class AdapterContactsList extends BaseQuickAdapter<ContactBean, BaseViewHolder> {
    public AdapterContactsList(int layoutResId, @Nullable List<ContactBean> data) {
        super(layoutResId, data);
    }

    private int[] color = {R.drawable.bg_contacts_positoin_1,R.drawable.bg_contacts_positoin_2,R.drawable.bg_contacts_positoin_3};

    @Override
    protected void convert(BaseViewHolder helper, ContactBean item) {
        LvContactListItemBinding mBingding = DataBindingUtil.bind(helper.itemView);
        String mobileStr = item.getMobile();
        String userName = item.getUserName();
        String nameOfspilt;
        if(!TextUtils.isEmpty(userName) && userName.length() > 1){
            nameOfspilt = userName.substring(0,1);
            mBingding.nameTv.setText(nameOfspilt);
        }
        Random random = new Random();
        int randNum = random.nextInt(3);

        mBingding.nameIv.setImageResource(color[randNum]);

        mBingding.tvName.setText(userName);
        if (TextUtils.isEmpty(mobileStr)) {
            mobileStr = Utils.getContext().getString(R.string.setting_t_null);
        }

        String jobname = item.getJobName();
        if (TextUtils.isEmpty(jobname)) {
            jobname = "暂无";
        }
        mBingding.tvReservoir.setText(item.getReservoir()+"");
        mBingding.tvPositoin.setText(jobname);
        mBingding.tvMobile.setText(mobileStr);

    }
}
