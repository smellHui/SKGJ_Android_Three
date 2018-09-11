package com.tepia.base.view.dialog.basedailog;

import android.view.View;
import android.widget.AdapterView;

/*************************************************************
 * Created by OCN.YAN                                        *
 * 主要功能:接口                                             *
 * 项目名:贵州水务                                            *
 * 包名:com.elegant.river_system.interfaces                   *
 * 创建时间:2017年11月10日17:02                                 *                                                                 *                                                               *
 * 更新时间:2017年11月10日17:02                                *
 * 版本号:1.1.0                                              *
 *************************************************************/
public interface OnOpenItemClick {
    void onOpenItemClick(AdapterView<?> parent, View view, int position, long id);
}
