package com.tepia.main.view.maincommon.reservoirs.detail;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.ToastUtils;
import com.tepia.main.R;
import com.tepia.main.common.DecimalInputTextWatcher;
import com.tepia.main.databinding.ActivityWaterLevelBinding;
import com.tepia.main.view.maincommon.reservoirs.ReservoirsFragment;
import com.tepia.main.view.maincommon.reservoirs.detailadapter.AdapterWaterlevelReservoirs;
import com.tepia.main.view.maincommon.reservoirs.mvpreservoir.ReserviorContract;
import com.tepia.main.view.maincommon.reservoirs.mvpreservoir.ReserviorPresent;
/**
  * Created by      Android studio
  *
  * @author :ly (from Center Of Wuhan)
  * 创建时间 :2018-12-26
  * 更新时间 :
  * Version :1.0
  * 功能描述 :讯限水位页面
 **/
public class WaterLevelActivity extends MVPBaseActivity<ReserviorContract.View,ReserviorPresent> {

    private AdapterWaterlevelReservoirs adapterWaterlevelReservoirs;
    private ActivityWaterLevelBinding binding;
    @Override
    public int getLayoutId() {
        return R.layout.activity_water_level;
    }

    @Override
    public void initView() {

        setCenterTitle(getString(R.string.waterlevel_name));
        showBack();
        binding = DataBindingUtil.bind(mRootView);
        String reservoirId = getIntent().getStringExtra(ReservoirsFragment.RESERVOIRId);
        String reservoirName = getIntent().getStringExtra(ReservoirsFragment.RESERVOIRNAME);
        binding.rerserviorNameTag.nameTv.setText(reservoirName);
        binding.addTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDialog();
                if(!isFinishing()) {
                    dialog_show.show();
//                    getReservoirId();
                }
            }
        });

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

    private Dialog dialog_show;
    private TextView selectReserviorTv;
    private EditText selectShuiweiEv;

    private void initDialog() {
        LayoutInflater inflater = (LayoutInflater) getLayoutInflater();
        final ViewGroup nullparent = null;
        View layout = inflater.inflate(R.layout.fragment_waterlevel_dailog, nullparent);
        selectReserviorTv = layout.findViewById(R.id.selectReserviorTv);
        selectShuiweiEv = layout.findViewById(R.id.selectShuiweiEv);
        Button cancelBtn = layout.findViewById(R.id.cancelBtn);
        Button suerBtn = layout.findViewById(R.id.suerBtn);

        dialog_show = new Dialog(this, R.style.Transparent);
        dialog_show.setCanceledOnTouchOutside(true);
        dialog_show.setContentView(layout);
        WindowManager windowManager = getWindowManager();
        DisplayMetrics metric = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metric);
        // 屏幕宽度（像素）
        int width = metric.widthPixels * 4 / 5;
//        int height = metric.heightPixels / 3;
        WindowManager.LayoutParams lp = dialog_show.getWindow().getAttributes();
        lp.width = width;
        dialog_show.getWindow().setAttributes(lp);
        //限制输入位数：整数3位，小数点后两位
        selectShuiweiEv.addTextChangedListener(new DecimalInputTextWatcher(selectShuiweiEv, 4, 3));
        suerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rz = selectShuiweiEv.getText().toString();
               /* DecimalFormat df = new DecimalFormat("#.00");
                String rz = df.format(Double.parseDouble(waterleverValue));*/
                LogUtil.e("水位值:"+rz);
                if(TextUtils.isEmpty(rz)){
                    ToastUtils.shortToast("请填写水位值");
                    return;
                }

//                mPresenter.uploadingStRsvr(reservoirId,rz);
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog_show.dismiss();
            }
        });

    }
}
