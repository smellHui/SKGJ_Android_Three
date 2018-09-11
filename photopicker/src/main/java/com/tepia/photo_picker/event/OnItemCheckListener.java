package com.tepia.photo_picker.event;


import com.tepia.photo_picker.entity.Photo;

/*************************************************************
 * Created by OCN.YAN                                        *
 * 主要功能:TOTO                                             *
 * 项目名:photopicker                                        *
 * 包名:com.tepia.photo_picker event                         *
 * 创建时间:2016年06月14日14:40                              *
 * 更新人:yanqiuqiu                                          *
 * 邮箱:yanqqkongmi@gmail.com                                *
 * QQ:2361167552                                             *
 * 更新时间:2017年09月14日14:40                              *
 * 版权:个人版权所有                                         *
 * 版本号:1.1.0                                              *
 *************************************************************/
public interface OnItemCheckListener {

  /***
   *
   * @param position 所选图片的位置
   * @param path     所选的图片
   * @param selectedItemCount  已选数量
   * @return enable check
   */
  boolean onItemCheck(int position, Photo path, int selectedItemCount);

}
