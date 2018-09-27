package com.tepia.main.model.train;

import com.tepia.base.mvp.BasePresenter;
import com.tepia.base.mvp.BaseView;

/**
 * Created by      Intellij IDEA
 *
 * @author :       wwj
 * Date    :       2018-09-25
 * Time    :       11:53
 * Version :       1.0
 * Company :       北京太比雅科技(武汉研发中心)
 **/
public class TrainContract {
    public interface View<T> extends BaseView {
        void success(T data);
        void failure(String msg);
    }
    interface Presenter extends BasePresenter<TrainContract.View>{
        void getTrainList(String currentPage,String pageSize);
        void getTrainDetail(String id);
    }
}
