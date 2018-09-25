package com.tepia.main.view.maincommon.reservoirs.detail;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tepia.base.mvp.BaseActivity;
import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.base.utils.ScreenUtil;
import com.tepia.main.R;
import com.tepia.main.databinding.ActivityIntroduceOfReservoirsBinding;
import com.tepia.main.model.reserviros.IntroduceOfReservoirsBean;
import com.tepia.main.view.maincommon.reservoirs.ReservoirsFragment;
import com.tepia.main.view.maincommon.reservoirs.mvpreservoir.ReserviorContract;
import com.tepia.main.view.maincommon.reservoirs.mvpreservoir.ReserviorPresent;

/**
  * Created by      Android studio
  *
  * @author         :       ly(from Center Of Wuhan)
  * Date            :       2018-9-18
  * Version         :       1.0
  * 功能描述         :       水库简介
 **/
public class IntroduceOfReservoirsActivity extends MVPBaseActivity<ReserviorContract.View,ReserviorPresent> implements  ReserviorContract.View<IntroduceOfReservoirsBean> {

    private LinearLayout baseLy;
    private FrameLayout moreorlessFy;
    private TextView moreorlessTv;
    ActivityIntroduceOfReservoirsBinding activityIntroduceOfReservoirsBinding;
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
        activityIntroduceOfReservoirsBinding = DataBindingUtil.bind(mRootView);
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
        String reservoirId = getIntent().getStringExtra(ReservoirsFragment.RESERVOIRId);
        mPresenter.getBaseInfo(reservoirId);
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

    @Override
    public void success(IntroduceOfReservoirsBean data) {
        IntroduceOfReservoirsBean.DataBean dataBean = data.getData();
        activityIntroduceOfReservoirsBinding.reservoirNameTv.setText(dataBean.getReservoir());
        activityIntroduceOfReservoirsBinding.belongTv.setText("所属乡镇："+"--");
        activityIntroduceOfReservoirsBinding.buildStartDateTv.setText("兴建时间："+dataBean.getBuildStartDate());
        activityIntroduceOfReservoirsBinding.buildEndDateTv.setText("竣工时间："+dataBean.getBuildEndDate());
        activityIntroduceOfReservoirsBinding.widthAndlengthTv.setText("坝高:"+dataBean.getDamHeight()+"m    |   坝长："+dataBean.getDamLength()+
                "m    |   坝宽："+dataBean.getDamWidth()+"m");
        activityIntroduceOfReservoirsBinding.normalImpoundedLevelTv.setText("正常蓄水位："+dataBean.getNormalImpoundedLevel()+"");
        activityIntroduceOfReservoirsBinding.damTypeTv.setText("大坝类型："+dataBean.getDamType()+"");
        activityIntroduceOfReservoirsBinding.damCrestElevationTv.setText("坝顶高程："+dataBean.getDamCrestElevation()+"");
        activityIntroduceOfReservoirsBinding.damBotmMaxWidthTv.setText("坝底最大宽度："+dataBean.getDamBotmMaxWidth()+"");
        activityIntroduceOfReservoirsBinding.capacityCoefficientTv.setText("库容系数："+dataBean.getCapacityCoefficient()+"");
        activityIntroduceOfReservoirsBinding.mainFunctionTv.setText(dataBean.getMainFunction()+"");
        activityIntroduceOfReservoirsBinding.reservoirAddressTv.setText(dataBean.getReservoirAddress()+"");

    }

    @Override
    public void failure(String msg) {

    }
}
