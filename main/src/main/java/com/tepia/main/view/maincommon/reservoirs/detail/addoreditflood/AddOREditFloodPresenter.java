package com.tepia.main.view.maincommon.reservoirs.detail.addoreditflood;

import com.tepia.base.http.BaseResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.base.utils.ToastUtils;
import com.tepia.main.model.image.ImageInfoBean;
import com.tepia.main.model.reserviros.ReservirosManager;
import com.tepia.main.model.task.TaskManager;
import com.zxy.tiny.Tiny;
import com.zxy.tiny.callback.FileBatchCallback;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author :       zhang xinhua
 * @Version :       1.0
 * @创建人 ：      zhang xinhua
 * @创建时间 :       2019/3/6 9:26
 * @修改人 ：
 * @修改时间 :       2019/3/6 9:26
 * @功能描述 :
 **/

public class AddOREditFloodPresenter extends BasePresenterImpl<AddOREditFloodContract.View> implements AddOREditFloodContract.Presenter {

    public void addReservoirMaterial(String reservoirId, String meName, String meType, String meTotals, String position,
                                     String manageName, String phoneNum, String remark, ArrayList<String> files) {
        if (files == null || files.size() == 0) {
            ReservirosManager.getInstance().addReservoirMaterial(reservoirId, meName, meType, meTotals, position, manageName, phoneNum, remark, files)
                    .safeSubscribe(new LoadingSubject<BaseResponse>(true, "请稍等...") {
                        @Override
                        protected void _onNext(BaseResponse response) {
                            mView.addReservoirMaterialSuccess();
                        }

                        @Override
                        protected void _onError(String message) {
                            ToastUtils.shortToast(message);
                        }
                    });
        } else {
            Tiny.FileCompressOptions options = new Tiny.FileCompressOptions();
            Tiny.getInstance().source(files.toArray(new String[files.size()])).batchAsFile().withOptions(options).batchCompress(new FileBatchCallback() {
                @Override
                public void callback(boolean isSuccess, String[] outfile) {
                    if (isSuccess) {
                        ArrayList<String> tempslist = new ArrayList<>();
                        Collections.addAll(tempslist, outfile);
                        ReservirosManager.getInstance().addReservoirMaterial(reservoirId, meName, meType, meTotals, position, manageName, phoneNum, remark, tempslist)
                                .safeSubscribe(new LoadingSubject<BaseResponse>(true, "请稍等...") {
                                    @Override
                                    protected void _onNext(BaseResponse response) {
                                        mView.addReservoirMaterialSuccess();
                                    }

                                    @Override
                                    protected void _onError(String message) {
                                        ToastUtils.shortToast(message);
                                    }
                                });
                    }
                }
            });
        }
    }

    public void updateReservoirMaterial(String id, String reservoirId, String meName, String meType, String meTotals, String position,
                                        String manageName, String phoneNum, String remark, ArrayList<String> files) {
        if (files == null || files.size() == 0) {
            ReservirosManager.getInstance().updateReservoirMaterial(id,reservoirId, meName, meType, meTotals, position, manageName, phoneNum, remark, files)
                    .safeSubscribe(new LoadingSubject<BaseResponse>(true, "请稍等...") {
                        @Override
                        protected void _onNext(BaseResponse response) {
                            mView.addReservoirMaterialSuccess();
                        }

                        @Override
                        protected void _onError(String message) {
                            ToastUtils.shortToast(message);
                        }
                    });
        } else {
            Tiny.FileCompressOptions options = new Tiny.FileCompressOptions();
            Tiny.getInstance().source(files.toArray(new String[files.size()])).batchAsFile().withOptions(options).batchCompress(new FileBatchCallback() {
                @Override
                public void callback(boolean isSuccess, String[] outfile) {
                    if (isSuccess) {
                        ArrayList<String> tempslist = new ArrayList<>();
                        Collections.addAll(tempslist, outfile);
                        ReservirosManager.getInstance().updateReservoirMaterial(id,reservoirId, meName, meType, meTotals, position, manageName, phoneNum, remark, tempslist)
                                .safeSubscribe(new LoadingSubject<BaseResponse>(true, "请稍等...") {
                                    @Override
                                    protected void _onNext(BaseResponse response) {
                                        mView.addReservoirMaterialSuccess();
                                    }

                                    @Override
                                    protected void _onError(String message) {
                                        ToastUtils.shortToast(message);
                                    }
                                });
                    }
                }
            });
        }
    }

    public void deleteImage(ImageInfoBean imageInfoBean, boolean isShow, String msg) {
        TaskManager.getInstance().delFile(imageInfoBean.getFileId()).subscribe(new LoadingSubject<BaseResponse>(isShow, msg) {
            @Override
            protected void _onNext(BaseResponse response) {
                if (response.getCode() == 0) {
                    if (mView != null) {
                        mView.delFileSucess(imageInfoBean);
                    }
                }
            }

            @Override
            protected void _onError(String message) {
                ToastUtils.shortToast(message);
            }
        });

    }
}
