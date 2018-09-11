package com.tepia.main.view.main.work;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.base.view.BadgeView;
import com.tepia.main.R;
import com.tepia.main.model.MainItemBean;

import java.util.List;

/**
 * Created by Joeshould on 2018/3/22.
 */
/*
 * The MIT License (MIT)
 *
 * Copyright 2016 by OCN.YAN
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND TORT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

public class AdapterMainModellist extends BaseQuickAdapter<MainItemBean, BaseViewHolder> {


    private Context context;
    private ImageView ivImage;
    private TextView tvName;

    public AdapterMainModellist(Context context, int layoutResId, List<MainItemBean> data) {
        super(layoutResId, data);
        this.context = context;
    }


    @Override
    protected void convert(BaseViewHolder helper, MainItemBean item) {

        ivImage = helper.getView(R.id.iv_image);
        tvName = helper.getView(R.id.tv_name);
        ivImage.setImageResource(item.getIconRes());
        tvName.setText(item.getIconName());
        if (!TextUtils.isEmpty(item.getNum()) && !"0".equals(item.getNum())) {
            BadgeView badgeView = new BadgeView(context, ivImage);
            badgeView.setText(item.getNum());
            badgeView.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
            badgeView.setBadgeBackgroundColor(0xFFff0015);
            badgeView.show();
        }

    }

}


