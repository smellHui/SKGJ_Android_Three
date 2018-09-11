package com.tepia.main.view.main.question;

import android.content.Context;

import com.tepia.base.mvp.BasePresenter;
import com.tepia.base.mvp.BaseView;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页问题反馈
 * by ly on 2018/5
 */

public class QuestionContract {
    public interface View<T> extends BaseView {
        void success(T data);
        void failure(String msg);

    }

    interface  Presenter extends BasePresenter<View> {
        void feedback(String questionType,String questionTitle,String questionContent,String reservoirId,ArrayList<String> selectPhotos);
        void listReservoirInCenter(String sparent);

        void appFeedBack(String msgContent,String mobile,ArrayList<String> files);
        void listAllProblem(String reservoirName, String problemTitle,  String problemType,  String currentPage, String pageSize,boolean isrefresh);
        void getDetailedProblemInfoByProblemId(String problemId);
        void getProblemCallList(String reservoirName, String problemCofirmType,  String problemStatus,  String problemSource,
                                String startDate,String endDate,String currentPage, String pageSize,boolean isshowloading);
        void getProblemExamineList(String reservoirName, String problemType,String problemTitle,String problemCofirmType,  String problemStatus,
                                String startDate,String endDate,String currentPage, String pageSize,boolean isshowloading);
    }
}
