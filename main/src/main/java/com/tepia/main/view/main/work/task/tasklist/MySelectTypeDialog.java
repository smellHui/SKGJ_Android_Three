package com.tepia.main.view.main.work.task.tasklist;

import android.app.Dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tepia.main.R;
import com.tepia.main.view.main.work.task.AdapterSelectType;


import java.util.ArrayList;

/**
 * Created by Joeshould on 2018/6/25.
 *
 * @author zxh
 */

public class MySelectTypeDialog extends DialogFragment {


    private RecyclerView rvSelectItem;
    private AdapterSelectType adapterSelectType;
    private BaseQuickAdapter.OnItemClickListener onItemClickListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_dialog_type_select, null);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        rvSelectItem = view.findViewById(R.id.rv_selectItem);

        final Dialog dialog = new Dialog(getActivity(), R.style.style_dialog);
        dialog.setContentView(view);
        dialog.show();

        Window window = dialog.getWindow();
        //可设置dialog的位置
        window.setGravity(Gravity.RIGHT);
        //消除边距
        window.getDecorView().setPadding(0, 0, 0, 0);

        WindowManager.LayoutParams lp = window.getAttributes();
        //设置宽度充满屏幕
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);
        initListView();
        return dialog;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    public void onStart() {
        //  Auto-generated method stub
        super.onStart();
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        getDialog().getWindow().setLayout(dm.widthPixels, getDialog().getWindow().getAttributes().height);
    }

    private void initListView() {
        ArrayList<String> list = new ArrayList();
        list.add(getString(R.string.zonglanstr));
        list.add(getString(R.string.xunjianstr));
        list.add(getString(R.string.weihustr));
        list.add(getString(R.string.baojiestr));
        rvSelectItem.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterSelectType = new AdapterSelectType(R.layout.lv_select_type_item, list);
        rvSelectItem.setAdapter(adapterSelectType);
        adapterSelectType.setOnItemClickListener(onItemClickListener);
    }

    public void setOnItemClickListener(BaseQuickAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
