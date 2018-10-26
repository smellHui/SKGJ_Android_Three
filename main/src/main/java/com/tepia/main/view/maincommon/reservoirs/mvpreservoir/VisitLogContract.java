package com.tepia.main.view.maincommon.reservoirs.mvpreservoir;

import com.tepia.base.mvp.BasePresenter;
import com.tepia.base.mvp.BaseView;

import java.util.ArrayList;

/**
 * Created by      android studio
 *
 * @author :       ly
 * Date            :       2018-09-21
 * Time            :       下午6:01
 * Version         :       1.0
 * location        :       武汉研发中心
 * 功能描述         :
 **/
public class VisitLogContract {
    public interface View<T> extends BaseView {
        void success(T data);
        void failure(String msg);
    }

    interface  Presenter extends BasePresenter<View> {
        void getPageList(String reservoirId,String currentPage, String pageSize,boolean isshowloading);
        void detail(String id);
        /**
         * 新增到访日志
         * @param reservoirId
         * @param visitCause
         * @param workContent
         * @param remark
         * @param visitTime
         * @param selectPhotos
         */
        void insert(String reservoirId,
                    String visitCause,
                    String workContent,
                    String remark,
                    String visitTime,
                    ArrayList<String> selectPhotos

                    );

    }
}
