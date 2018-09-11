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
public interface Selectable {


  /**
   * Indicates if the item at position position is selected
   *
   * @param photo Photo of the item to check
   * @return true if the item is selected, false otherwise
   */
  boolean isSelected(Photo photo);

  /**
   * Toggle the selection status of the item at a given position
   *
   * @param photo Photo of the item to toggle the selection status for
   */
  void toggleSelection(Photo photo);

  /**
   * Clear the selection status for all items
   */
  void clearSelection();

  /**
   * Count the selected items
   *
   * @return Selected items count
   */
  int getSelectedItemCount();

}
