package com.tepia.main.view.main.map.adapter.search;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.main.R;

import java.util.List;

/**
 * 地图分类搜索
 * @author 44822
 */
public class MapSearchAdapter extends BaseQuickAdapter<SearchModel, BaseViewHolder> {

    public MapSearchAdapter(int layoutResId, @Nullable List<SearchModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchModel item) {
        helper.setText(R.id.tv_title, item.getName());
        helper.setText(R.id.tv_type,item.getType());
    }
}
