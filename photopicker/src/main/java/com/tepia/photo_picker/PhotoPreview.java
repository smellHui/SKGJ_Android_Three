package com.tepia.photo_picker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import java.util.ArrayList;

/*************************************************************
 * Created by OCN.YAN                                        *
 * 主要功能:TOTO                                             *
 * 项目名:photopicker                                        *
 * 包名:com.tepia.photo_picker                               *
 * 创建时间:2016年06月14日14:40                              *
 * 更新人:yanqiuqiu                                          *
 * 邮箱:yanqqkongmi@gmail.com                                *
 * QQ:2361167552                                             *
 * 更新时间:2017年09月14日14:40                              *
 * 版权:个人版权所有                                         *
 * 版本号:1.1.0                                              *
 *************************************************************/
public class PhotoPreview {

  public final static int REQUEST_CODE = 666;
  public final static String EXTRA_CURRENT_ITEM = "current_item";
  public final static String EXTRA_PHOTOS       = "photos";
  public final static String EXTRA_SHOW_DELETE  = "show_delete";


  public static PhotoPreviewBuilder builder() {
    return new PhotoPreviewBuilder();
  }


  public static class PhotoPreviewBuilder {
    private Bundle mPreviewOptionsBundle;
    private Intent mPreviewIntent;

    private PhotoPreviewBuilder() {
      mPreviewOptionsBundle = new Bundle();
      mPreviewIntent = new Intent();
    }

    /**
     * Send the Intent from an Activity with a custom request code
     *
     * @param activity    Activity to receive result
     * @param requestCode requestCode for result
     */
    public void start(Activity activity, int requestCode) {
      activity.startActivityForResult(getIntent(activity), requestCode);
    }

    /**
     * Send the Intent with a custom request code
     *
     * @param fragment    Fragment to receive result
     * @param requestCode requestCode for result
     */
    public void start(Context context, Fragment fragment, int requestCode) {
      fragment.startActivityForResult(getIntent(context), requestCode);
    }

    /**
     * Send the Intent with a custom request code
     *
     * @param fragment    Fragment to receive result
     */
    public void start( Context context, Fragment fragment) {
      fragment.startActivityForResult(getIntent(context), REQUEST_CODE);
    }

    /**
     * Send the crop Intent from an Activity
     *
     * @param activity Activity to receive result
     */
    public void start(Activity activity) {
      start(activity, REQUEST_CODE);
    }

    /**
     * Get Intent to start {@link PhotoPickerActivity}
     *
     * @return Intent for {@link PhotoPickerActivity}
     */
    public Intent getIntent(Context context) {
      mPreviewIntent.setClass(context, PhotoPagerActivity.class);
      mPreviewIntent.putExtras(mPreviewOptionsBundle);
      return mPreviewIntent;
    }

    public PhotoPreviewBuilder setPhotos(ArrayList<String> photoPaths) {
      mPreviewOptionsBundle.putStringArrayList(EXTRA_PHOTOS, photoPaths);
      return this;
    }

    public PhotoPreviewBuilder setCurrentItem(int currentItem) {
      mPreviewOptionsBundle.putInt(EXTRA_CURRENT_ITEM, currentItem);
      return this;
    }

    public PhotoPreviewBuilder setShowDeleteButton(boolean showDeleteButton) {
      mPreviewOptionsBundle.putBoolean(EXTRA_SHOW_DELETE, showDeleteButton);
      return this;
    }
  }
}
