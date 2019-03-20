package com.tepia.main.view.maincommon.setting.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.main.R;
import com.tepia.main.model.detai.ReservoirBean;
import com.tepia.main.model.user.ContactBean;

import java.util.List;
import java.util.Random;

/**
  * Created by      Android studio
  *
  * @author :ly (from Center Of Wuhan)
  * Date    :2018-9-28
  * Version :1.0
  * 功能描述 :工作职责页面适配器
 **/
public class ListChildBookAdapter extends BaseQuickAdapter<ContactBean,BaseViewHolder> {
    public ListChildBookAdapter(int layoutResId, @Nullable List<ContactBean> data) {
        super(layoutResId, data);
    }

    private int[] color = {R.drawable.bg_contacts_positoin_1,R.drawable.bg_contacts_positoin_2,R.drawable.bg_contacts_positoin_3};

    @Override
    protected void convert(BaseViewHolder view, ContactBean item) {
        String userName = item.getUserName();
        view.setText(R.id.first_tv_name,item.getUserName());
        view.setText(R.id.first_tv_positoin,item.getJobName());

        String nameOfspilt;
        if(!TextUtils.isEmpty(userName) && userName.length() > 1){
            nameOfspilt = userName.substring(0,1);
            view.setText(R.id.first_nameTv,nameOfspilt);
        }
        Random random = new Random();
        int randNum = random.nextInt(3);
        view.setImageResource(R.id.first_nameIv,color[randNum]);



    }
}
