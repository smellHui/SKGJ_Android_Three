package com.tepia.main.utils;

import android.Manifest;

import com.tepia.base.utils.Utils;
import com.tepia.main.R;
import com.yanzhenjie.permission.AndPermission;

/**
 * Created by liying on 2018/8/22.
 */

public class PermissionUtil {
    /**
     * 拼接权限判断提示结果
     *
     * @return
     */
    public static String shouldHavePermission(boolean isSHowRationale,String... permissions) {
        StringBuilder meaasge = new StringBuilder();
        boolean haveSD = AndPermission.hasPermission(Utils.getContext(), permissions);
        if (isSHowRationale) {

            if (!haveSD) {
                meaasge.append(Utils.getContext().getString(R.string.permission_save_message) + "\n" + " \n");
            }


        } else {

            if (!haveSD) {
                meaasge.append(Utils.getContext().getString(R.string.permission_save_no) + "\n" + " \n");
            }

            meaasge.append(Utils.getContext().getString(R.string.permission_path));
        }
        return meaasge.toString();

    }
}
