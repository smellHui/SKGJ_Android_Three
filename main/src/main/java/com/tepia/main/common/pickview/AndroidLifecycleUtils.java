package com.tepia.main.common.pickview;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

/*************************************************************
 * Created by OCN.YAN                                        *
 * 主要功能:TOTO                                             *
 * 项目名:photopicker                                        *
 * 包名:com.tepia.photo_picker utils                         *
 * 创建时间:2016年06月14日14:40                              *
 * 更新人:yanqiuqiu                                          *
 * 邮箱:yanqqkongmi@gmail.com                                *
 * QQ:2361167552                                             *
 * 更新时间:2017年09月14日14:40                              *
 * 版权:个人版权所有                                         *
 * 版本号:1.1.0                                              *
 *************************************************************/
public class AndroidLifecycleUtils {
    public static boolean canLoadImage(Fragment fragment) {
        if (fragment == null) {
            return true;
        }
        FragmentActivity activity = fragment.getActivity();
        return canLoadImage(activity);
    }

    public static boolean canLoadImage(Context context) {
        if (context == null) {
            return true;
        }

        if (!(context instanceof Activity)) {
            return true;
        }

        Activity activity = (Activity) context;
        return canLoadImage(activity);
    }

    public static boolean canLoadImage(Activity activity) {
        if (activity == null) {
            return true;
        }
        boolean destroyed = Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 &&
                activity.isDestroyed();
        return !(destroyed || activity.isFinishing());
    }
}
