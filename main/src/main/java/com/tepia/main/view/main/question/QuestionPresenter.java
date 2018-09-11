package com.tepia.main.view.main.question;

import com.alibaba.android.arouter.launcher.ARouter;
import com.just.library.LogUtils;
import com.tepia.base.AppRoutePath;
import com.tepia.base.http.BaseResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.base.utils.AppManager;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.utils.Utils;
import com.tepia.base.view.dialog.loading.SimpleLoadDialog;
import com.tepia.main.R;
import com.tepia.main.model.question.AllproblemBean;
import com.tepia.main.model.question.ProblemCallListBean;
import com.tepia.main.model.question.ProblemDetailBean;
import com.tepia.main.model.question.QuestionManager;
import com.tepia.main.model.question.ReservoirBean;
import com.zxy.tiny.Tiny;
import com.zxy.tiny.callback.FileBatchCallback;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * 首页问题反馈
 * @date on 2018/5
 * @author liying
 */
public class QuestionPresenter extends BasePresenterImpl<QuestionContract.View> implements QuestionContract.Presenter {
    private static final String TAG = QuestionPresenter.class.getName();
    private SimpleLoadDialog simpleLoadDialog;

    /**
     * 提交问题压缩图片
     * @param questionType
     * @param questionTitle
     * @param questionContent
     * @param reservoirId
     * @param selectPhotos
     */
    @Override
    public void feedback(String questionType, String questionTitle, String questionContent, String reservoirId, ArrayList<String> selectPhotos) {
        simpleLoadDialog = new SimpleLoadDialog(AppManager.getInstance().getCurrentActivity(), "正在提交...", true);
        if (simpleLoadDialog != null) {
            simpleLoadDialog.show();
        }
        Tiny.FileCompressOptions options = new Tiny.FileCompressOptions();
        if (selectPhotos != null && selectPhotos.size() > 0) {
            Tiny.getInstance().source(selectPhotos.toArray(new String[selectPhotos.size()])).batchAsFile().withOptions(options).batchCompress(new FileBatchCallback() {
                @Override
                public void callback(boolean isSuccess, String[] outfile) {
                    //return the batch compressed file path
                    if (isSuccess) {
                        final ArrayList<String> tempslist = new ArrayList<>(10);
                        Collections.addAll(tempslist, outfile);
                        upFeed(questionType, questionTitle, questionContent, reservoirId, tempslist);

                    } else {
                        if (simpleLoadDialog != null) {
                            simpleLoadDialog.dismiss();
                        }
                        ToastUtils.shortToast("图片压缩失败");
                    }
                }
            });
        } else {
            upFeed(questionType, questionTitle, questionContent, reservoirId, selectPhotos);
        }


    }

    /**
     * 提交问题
     * @param questionType
     * @param questionTitle
     * @param questionContent
     * @param reservoirId
     * @param tempslist
     */
    private void upFeed(String questionType, String questionTitle, String questionContent, String reservoirId, ArrayList<String> tempslist) {
        QuestionManager.getInstance().feedback(questionType, questionTitle, questionContent, reservoirId, tempslist)
                .subscribe(new LoadingSubject<BaseResponse>(false, "") {
                    @Override
                    protected void _onNext(BaseResponse baseResponse) {
                        if (simpleLoadDialog != null) {
                            simpleLoadDialog.dismiss();
                        }
                        if (baseResponse.getCode() == 0) {
                            mView.success(baseResponse);
                            ToastUtils.longToast(R.string.question_success);
                        } else {
                            mView.failure(baseResponse.getMsg());

                        }
                    }

                    @Override
                    protected void _onError(String message) {
                        if (simpleLoadDialog != null) {
                            simpleLoadDialog.dismiss();
                        }
                        ToastUtils.longToast(message);

                        LogUtil.e(message + "1");
                    }
                });
    }

    /**
     * 获取水库列表
     *
     * @return
     */
    @Override
    public void listReservoirInCenter(String sparent) {
        QuestionManager.getInstance().listReservoirInCenter(sparent)
                .subscribe(new LoadingSubject<ReservoirBean>() {
                    @Override
                    protected void _onNext(ReservoirBean reservoirBean) {
                        if (reservoirBean != null) {
                            LogUtil.e(TAG, reservoirBean.getCode() + "");
                        }
                        if (reservoirBean.getCode() == 0) {
                            mView.success(reservoirBean);
                        } else {
                            mView.failure(reservoirBean.getMsg());
                        }
                    }

                    @Override
                    protected void _onError(String message) {
                        LogUtil.e(TAG, "_onError " + message);
                    }
                });

    }

    /**
     * 意见反馈压缩图片
     *
     * @param msgContent
     * @param mobile
     * @param selectPhotos
     */
    @Override
    public void appFeedBack(String msgContent, String mobile, ArrayList<String> selectPhotos) {
        simpleLoadDialog = new SimpleLoadDialog(AppManager.getInstance().getCurrentActivity(), "正在提交...", true);
        if (simpleLoadDialog != null) {
            simpleLoadDialog.show();
        }
        Tiny.FileCompressOptions options = new Tiny.FileCompressOptions();
        if (selectPhotos != null && selectPhotos.size() > 0) {
            Tiny.getInstance().source(selectPhotos.toArray(new String[selectPhotos.size()])).batchAsFile().withOptions(options).batchCompress(new FileBatchCallback() {
                @Override
                public void callback(boolean isSuccess, String[] outfile) {
                    //return the batch compressed file path
                    if (isSuccess) {
                        final ArrayList<String> tempslist = new ArrayList<>(10);
                        Collections.addAll(tempslist, outfile);
                        appFeedback(msgContent, mobile, tempslist);

                    } else {
                        if (simpleLoadDialog != null) {
                            simpleLoadDialog.dismiss();
                        }
                        ToastUtils.shortToast("图片压缩失败");
                    }
                }
            });
        } else {
            appFeedback(msgContent, mobile, selectPhotos);
        }
    }

    /**
     * 事件上报查询当前用户上报的所有事件
     * @param reservoirName
     * @param problemTitle
     * @param problemType
     * @param currentPage
     * @param pageSize
     * @param isshowloading
     * @return
     */
    @Override
    public void listAllProblem(String reservoirName, String problemTitle, String problemType, String currentPage, String pageSize, boolean isshowloading) {
        QuestionManager.getInstance().listAllProblem(reservoirName,problemTitle,problemType,currentPage,pageSize)
                .subscribe(new LoadingSubject<AllproblemBean>(isshowloading,Utils.getContext().getString(R.string.data_loading)) {
                    @Override
                    protected void _onNext(AllproblemBean allproblemBean) {
                        if (allproblemBean != null) {
                            if (allproblemBean.getCode() == 0) {
                                LogUtil.e(TAG,"listAllProblem--上报事件列表请求成功 ");
                                mView.success(allproblemBean);

                            }else{
                                mView.failure(allproblemBean.getMsg());
                            }
                        }
                    }

                    @Override
                    protected void _onError(String message) {
                        LogUtil.e(TAG,"listAllProblem--上报事件列表请求失败 ");

                        mView.failure(message);

                    }
                });
    }

    @Override
    public void getDetailedProblemInfoByProblemId(String problemId) {
        QuestionManager.getInstance().getDetailedProblemInfoByProblemId(problemId)
                .subscribe(new LoadingSubject<ProblemDetailBean>(true,Utils.getContext().getString(R.string.data_loading)) {
                    @Override
                    protected void _onNext(ProblemDetailBean problemDetailBean) {
                        if (problemDetailBean != null) {
                            if (problemDetailBean.getCode() == 0) {
                                LogUtil.e(TAG,"getDetailedProblemInfoByProblemId--事件上报详情请求成功 ");
                                mView.success(problemDetailBean);
                            }else{
                                mView.failure(problemDetailBean.getMsg());
                            }
                        }
                    }

                    @Override
                    protected void _onError(String message) {
                        LogUtil.e(TAG,"getDetailedProblemInfoByProblemId--事件上报详情请求失败 ");

                        mView.failure(message);

                    }
                });
    }

    /**
     * 意见反馈
     *
     * @param msgContent
     * @param mobile
     * @param files
     */
    private void appFeedback(String msgContent, String mobile, ArrayList<String> files) {
        QuestionManager.getInstance().appFeedBack(msgContent, mobile, files).subscribe(new LoadingSubject<BaseResponse>() {
            @Override
            protected void _onNext(BaseResponse baseResponse) {
                if (simpleLoadDialog != null) {
                    simpleLoadDialog.dismiss();
                }
                if (baseResponse != null) {
                    if (baseResponse.getCode() == 0) {
                        mView.success(baseResponse);
                    }
                }
            }

            @Override
            protected void _onError(String message) {
                if (simpleLoadDialog != null) {
                    simpleLoadDialog.dismiss();
                }
                ToastUtils.shortToast("提交失败");


            }
        });
    }

    /**
     * 待确认事件列表
     * @param reservoirName
     * @param problemCofirmType
     * @param problemStatus
     * @param problemSource
     * @param startDate
     * @param endDate
     * @param currentPage
     * @param pageSize
     * @param isshowloading
     */
    @Override
    public void getProblemCallList(String reservoirName, String problemCofirmType, String problemStatus, String problemSource, String startDate, String endDate, String currentPage, String pageSize, boolean isshowloading) {
        QuestionManager.getInstance().getProblemCallList(reservoirName,problemCofirmType,problemStatus,problemSource,startDate,endDate,currentPage,pageSize)
                .subscribe(new LoadingSubject<ProblemCallListBean>(isshowloading,Utils.getContext().getString(R.string.data_loading)) {
                    @Override
                    protected void _onNext(ProblemCallListBean problemCallListBean) {
                        if (problemCallListBean != null) {
                            if (problemCallListBean.getCode() == 0) {
                                LogUtil.e(TAG,"getProblemCallList--待确认事件列表请求成功 ");
                                mView.success(problemCallListBean);

                            }else{
                                mView.failure(problemCallListBean.getMsg());
                            }
                        }
                    }

                    @Override
                    protected void _onError(String message) {
                        LogUtil.e(TAG,"getProblemCallList--待确认事件列表请求失败 ");

                        mView.failure(message);

                    }
                });
    }

    /**
     * 获取待审核问题列表
     * @param reservoirName
     * @param problemType
     * @param problemTitle
     * @param problemCofirmType
     * @param problemStatus
     * @param startDate
     * @param endDate
     * @param currentPage
     * @param pageSize
     * @param isshowloading
     */
    @Override
    public void getProblemExamineList(String reservoirName, String problemType, String problemTitle, String problemCofirmType, String problemStatus, String startDate, String endDate, String currentPage, String pageSize, boolean isshowloading) {
        QuestionManager.getInstance().getProblemExamineList(reservoirName,problemType,problemTitle,problemCofirmType,problemStatus,startDate,endDate,currentPage,pageSize)
                .subscribe(new LoadingSubject<ProblemCallListBean>(isshowloading,Utils.getContext().getString(R.string.data_loading)) {
                    @Override
                    protected void _onNext(ProblemCallListBean problemCallListBean) {
                        if (problemCallListBean != null) {
                            if (problemCallListBean.getCode() == 0) {
                                LogUtil.e(TAG,"getProblemExamineList--待审核事件列表请求成功 ");
                                mView.success(problemCallListBean);

                            }else{
                                mView.failure(problemCallListBean.getMsg());
                            }
                        }
                    }

                    @Override
                    protected void _onError(String message) {
                        LogUtil.e(TAG,"getProblemExamineList--待审核事件列表请求失败 ");

                        mView.failure(message);

                    }
                });
    }
}
