package com.tepia.main.view.maincommon.reservoirs.detailadapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.main.R;
import com.tepia.main.model.dictmap.DictMapEntity;
import com.tepia.main.model.dictmap.DictMapManager;
import com.tepia.main.model.reserviros.SafeRunningBean;
import com.tepia.main.view.maincommon.reservoirs.MyReservoirsItemBean;

import java.util.List;
import java.util.Map;

/**
 * 主页--水库--安全鉴定记录适配器
 *
 * @author ly
 * @date 2018/9/18
 */

public class AdapterSafeRunningReservoirs extends BaseQuickAdapter<SafeRunningBean.DataBean, BaseViewHolder> {

    public AdapterSafeRunningReservoirs(Context context, int layoutResId, @Nullable List<SafeRunningBean.DataBean> data) {
        super(layoutResId, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder view, SafeRunningBean.DataBean item) {

        view.setText(R.id.middletitleResnameTv, item.getReportName());
        String valueId = item.getReportType();
        /*"report_type": [
        -{
                "name": "安全鉴定报告",
                "value": "1"
          },
        -{
                "name": "除险加固报告",
                "value": "2"
         },
        -{
                "name": "蓄水验收报告",
                "value": "3"
         },
        -{
                "name": "竣工验收报告",
                "value": "4"
         }
         ],*/
        DictMapEntity dictMapEntity = DictMapManager.getInstance().getmDictMap();
        Map<String, String> map_report = dictMapEntity.getObject().getReport_type();
         if("1".equals(valueId)){
             view.setImageResource(R.id.leftIv,R.drawable.jianjie_anquan);

         }else if("2".equals(valueId)){
             view.setImageResource(R.id.leftIv,R.drawable.jianjie_jiagu);

         }else if("3".equals(valueId)){
            view.setImageResource(R.id.leftIv,R.drawable.jianjie_xushui);

         }else if("4".equals(valueId)){
             view.setImageResource(R.id.leftIv,R.drawable.jianjie_yanshou);

         }else{
             view.setImageResource(R.id.leftIv,R.drawable.icon_empty);

         }
         view.setText(R.id.titleResnameTv, map_report.get(valueId));


    }
}
