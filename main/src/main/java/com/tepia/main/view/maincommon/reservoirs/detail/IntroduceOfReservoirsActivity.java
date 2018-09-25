package com.tepia.main.view.maincommon.reservoirs.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tepia.base.mvp.BaseActivity;
import com.tepia.base.utils.ScreenUtil;
import com.tepia.main.R;

/**
  * Created by      Android studio
  *
  * @author         :       ly(from Center Of Wuhan)
  * Date            :       2018-9-18
  * Version         :       1.0
  * 功能描述         :       水库简介
 **/
public class IntroduceOfReservoirsActivity extends BaseActivity {

    private LinearLayout baseLy;
    private FrameLayout moreorlessFy;
    private TextView moreorlessTv;

    private boolean isopen;
    @Override
    public int getLayoutId() {
        return R.layout.activity_introduce_of_reservoirs;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCenterTitle("水库简介");
        showBack();
        baseLy = findViewById(R.id.baseLy);
        moreorlessFy = findViewById(R.id.moreorlessFy);
        moreorlessTv = findViewById(R.id.moreorlessTv);
        initBaseLy();
        moreorlessFy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isopen) {
                    initBaseLy();

                }else {
                    isopen = true;
                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                            RelativeLayout.LayoutParams.MATCH_PARENT);
                    layoutParams.height = RelativeLayout.LayoutParams.WRAP_CONTENT;
                    baseLy.setLayoutParams(layoutParams);
                    moreorlessTv.setText("收起");
                }

            }
        });
    }

    @Override
    public void initView() {


    }



    @Override
    public void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initRequestData() {

    }

    /**
     * 初始化基本信息布局（默认收缩）
     */
    private void initBaseLy(){
        isopen = false;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        layoutParams.height = ScreenUtil.dp2px(getBaseContext(),200);
        baseLy.setLayoutParams(layoutParams);
        moreorlessTv.setText("查看更多");
    }
}
