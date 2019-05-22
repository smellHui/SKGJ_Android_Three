package com.tepia.base.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tepia.base.utils.LogUtil;

import java.lang.reflect.ParameterizedType;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public abstract class MVPBaseFragment<V extends BaseView,T extends BasePresenterImpl<V>> extends BaseCommonFragment implements BaseView{
    public T mPresenter;

    @Override
    public void onAttach(Context context) {
        mPresenter = getInstance(this,1);
        mPresenter.attachView((V) this);
        LogUtil.e(MVPBaseFragment.class.getName(),"-------------开始执行"+mPresenter.mView);
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        if (mPresenter != null) {
            LogUtil.e(MVPBaseFragment.class.getName(),"-------------开始销毁"+mPresenter.mView);
            mPresenter.detachView();
        }
        super.onDetach();
    }

    @Override
    public Context getContext() {
        return super.getContext();
    }

    public  <T> T getInstance(Object o, int i) {
            try {
                return ((Class<T>) ((ParameterizedType) (o.getClass()
                        .getGenericSuperclass())).getActualTypeArguments()[i])
                        .newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassCastException e) {
                e.printStackTrace();
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            }
            return null;
        }
}
