package com.tepia.main.common.pickview;

import android.view.View;

/*************************************************************
 * Created by OCN.YAN                                        *
 * 主要功能:TOTO                                              *
 * 项目名:贵州水务                                            *
 * 包名:com.elegant.river_system.interfaces                    *
 * 创建时间:2017年11月15日10:44                                 *                                                                 *                                                               *
 * 更新时间:2017年11月15日10:44                                *
 * 版本号:1.1.0                                              *
 *************************************************************/
public interface OnItemClickListener {
    /**
     * 当RecyclerView某个被点击的时候回调
     *
     * @param view     点击item的视图
     * @param position 点击得到的数据
     */
    void onItemClick(View view, int position);
}
