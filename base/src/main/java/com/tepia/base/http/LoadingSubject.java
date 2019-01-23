package com.tepia.base.http;



import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.tepia.base.AppRoutePath;
import com.tepia.base.utils.AppManager;
import com.tepia.base.utils.SPUtils;
import com.tepia.base.utils.UnifiedErrorUtil;
import com.tepia.base.utils.Utils;
import com.tepia.base.view.dialog.loading.SimpleLoadDialog;

import org.litepal.util.LogUtil;

import io.reactivex.disposables.Disposable;

/**
 * Created by Joeshould on 2018/5/22.
 */

public abstract class LoadingSubject<T extends BaseResponse> implements io.reactivex.Observer<T> {
    private Boolean isShow = false;
    private String msg;
    private SimpleLoadDialog simpleLoadDialog;

    public LoadingSubject(Boolean isShow, String msg) {
        this.isShow = isShow;
        this.msg = msg;

    }

    public LoadingSubject() {
    }

    @Override
    public void onSubscribe(Disposable d) {
        if (isShow) {
            try {
                simpleLoadDialog = new SimpleLoadDialog(AppManager.getInstance().getCurrentActivity(), msg, true);
                if (simpleLoadDialog != null && !AppManager.getInstance().getCurrentActivity().isFinishing()) {
                    simpleLoadDialog.show();
                }
            } catch (Exception e) {
                Log.e("LoadingSubject", "onSubscribe: Exception" + e.getMessage());

            }
        }
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if ("The mapper returned a null ObservableSource".equals(e.getMessage())) {
            Log.e("LoadingSubject", "onError :The mapper returned a null ObservableSource" + e.getMessage());
            return;
        }
        if (isShow) {
            if (simpleLoadDialog != null && !AppManager.getInstance().getCurrentActivity().isFinishing()) {
                simpleLoadDialog.dismiss();
            }
        }
        Throwable throwable = UnifiedErrorUtil.unifiedError(e, Utils.getContext());
        if (throwable != null) {
            Log.e("LoadingSubject", "onError:>>>>>>>>>>>>>>>>" + throwable.getMessage());
            _onError(throwable.getMessage());
        }


    }

    @Override
    public void onComplete() {
        if (isShow) {
            if (simpleLoadDialog != null) {
                simpleLoadDialog.dismiss();
            }
        }
    }

    @Override
    public void onNext(T o) {
        if (isShow) {
            if (simpleLoadDialog != null) {
                simpleLoadDialog.dismiss();
            }
        }
        try {
            if (0 == o.getCode()) {
                _onNext(o);

            }else if (-500 == o.getCode()){

                ARouter.getInstance().build(AppRoutePath.token).navigation();
            }else {
                _onError(o.getMsg());
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    protected abstract void _onNext(T t);

    protected abstract void _onError(String message);
}
