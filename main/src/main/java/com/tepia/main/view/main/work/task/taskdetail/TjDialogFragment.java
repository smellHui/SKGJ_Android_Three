package com.tepia.main.view.main.work.task.taskdetail;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.SpannableString;
import android.view.View;

import com.tepia.base.mvp.BaseCommonFragment;
import com.tepia.base.mvp.BaseDialogFragment;
import com.tepia.main.R;
import com.tepia.main.databinding.FragmentDialogTjBinding;
import com.tepia.main.utils.EmptyLayoutUtil;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2019/1/3
 * Time            :       14:43
 * Version         :       1.0
 * 功能描述        :
 **/
public class TjDialogFragment extends BaseDialogFragment {
    private FragmentDialogTjBinding mBinding;
    public SpannableString tip;
    private View.OnClickListener onClickListener;
    private View.OnClickListener onClickListener1;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dialog_tj;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        mBinding = DataBindingUtil.bind(view);
        mBinding.tvMessage.setText(tip);
        mBinding.btCancel.setOnClickListener(onClickListener1);
        mBinding.btSure.setOnClickListener(onClickListener);
    }

    @Override
    protected void initRequestData() {

    }

    public void setListener(View.OnClickListener onClickListener, View.OnClickListener onClickListener1) {
        this.onClickListener = onClickListener;
        this.onClickListener1 = onClickListener1;
    }
}
