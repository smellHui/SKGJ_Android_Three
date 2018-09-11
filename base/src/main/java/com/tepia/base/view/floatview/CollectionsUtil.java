package com.tepia.base.view.floatview;

import java.util.List;
import java.util.Map;

/**
 * Created by thm on 2016/10/9.
 */
public class CollectionsUtil {
    public static boolean isNotEmpty(Object[] array) {
        if(array == null)
            return false;
        return array.length != 0;
    }

    public static boolean isNotEmpty(String str) {
        if(str == null)
            return false;
        return !str.equals("");
    }

    public static boolean isEmpty(String str) {
        return  !isNotEmpty(str);
    }

    public static boolean isEmpty(Object[] array) {
        return !isNotEmpty(array);
    }

    public static boolean isNotEmpty(List<?> list) {
        if(list == null)
            return false;
        return list.size() != 0;
    }

    public static boolean isEmpty(List<?> list) {
        return  !isNotEmpty(list);
    }

    public static boolean isNotEmpty(Map<?, ?> map) {
        if(map == null)
            return false;
        return map.size() != 0;
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return !isNotEmpty(map);
    }
}
