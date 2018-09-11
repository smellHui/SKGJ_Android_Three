package com.tepia.main.model.dictmap;

import com.google.gson.annotations.SerializedName;
import com.tepia.base.http.BaseResponse;

import java.util.List;

/**
 * Created by Joeshould on 2018/7/31.
 */

public class DictMapResponse extends BaseResponse {

    /**
     * data : {"array":{"sys_code":[{"name":"APP菜单","value":"mobileApp"},{"name":"主导航菜单","value":"default"}],
     * "VIDEO_TYPE":[{"name":"大坝视频","value":"1"},{"name":"水库视频","value":"0"},{"name":"其他视频","value":"2"}],
     * "planType":[{"name":"定期巡检","value":"2"},{"name":"日常巡检","value":"1"},{"name":"特别巡检","value":"3"}],
     * "menu_type":[{"name":"链接","value":"3"},{"name":"菜单","value":"1"},{"name":"功能","value":"2"}],
     * "executeStatus":[{"name":"已生成报告","value":"5"},{"name":"执行中","value":"2"},{"name":"已通过审核","value":"4"},{"name":"已提交","value":"3"},{"name":"待执行","value":"1"},{"name":"未下发","value":"0"}],
     * "dam_type":[{"name":"混凝土坝","value":"1"},{"name":"土石坝","value":"0"}],
     * "sttp":[{"name":"流量站","value":"ZQ"},{"name":"水质站","value":"WQ"},{"name":"雨量站","value":"PP"},{"name":"水库水文站","value":"RR"},{"name":"气象站","value":"MM"},{"name":"图像站","value":"II"}],
     * "problemCofirmType":[{"name":"严重问题","value":"2"},{"name":"一般问题","value":"1"},{"name":"不是问题","value":"0"}],
     * "isAdmin":[{"name":"是","value":"1"},{"name":"否","value":"0"}],
     * "reservoir_type":[{"name":"大（二）型","value":"5"},{"name":"小（二）型","value":"2"},{"name":"大（一）型","value":"4"},{"name":"小（一）型","value":"1"},{"name":"中型","value":"3"}],
     * "is_show":[{"name":"显示","value":"1"},{"name":"隐藏","value":"0"}],
     * "superviseShowType":[{"name":"0:有,1:无","value":"1"},{"name":"0:完成,1:未完成","value":"2"},{"name":"0:正常,1:不正常","value":"0"}],
     * "problemStatus":[{"name":"待确认","value":"0"},{"name":"业主已审核","value":"3"},{"name":"已确认","value":"1"},{"name":"已完结（反馈操作）","value":"4"},{"name":"已审核","value":"2"}],
     * "ACCESS_TYPE":[{"name":"SIM卡","value":"1"},{"name":"IP","value":"0"}],
     * "login_scope":[{"name":"PC","value":"0"},{"name":"APP","value":"1"},{"name":"公众号","value":"2"}],
     * "operationType":[{"name":"维护","value":"2"},{"name":"其他类型","value":"4"},{"name":"保洁","value":"3"},{"name":"巡检","value":"1"}],
     * "isSys":[{"name":"否","value":"0"},{"name":"是","value":"1"}],
     * "status":[{"name":"禁用","value":"2"},{"name":"正常","value":"0"}]},
     * "object":{"sys_code":{"default":"主导航菜单","mobileApp":"APP菜单"},
     * "VIDEO_TYPE":{"0":"水库视频","1":"大坝视频","2":"其他视频"},
     * "planType":{"1":"日常巡检","2":"定期巡检","3":"特别巡检"},
     * "menu_type":{"1":"菜单","2":"功能","3":"链接"},
     * "executeStatus":{"0":"未下发","1":"待执行","2":"执行中","3":"已提交","4":"已通过审核","5":"已生成报告"},
     * "dam_type":{"0":"土石坝","1":"混凝土坝"},
     * "sttp":{"PP":"雨量站","RR":"水库水文站","MM":"气象站","II":"图像站","ZQ":"流量站","WQ":"水质站"},
     * "problemCofirmType":{"0":"不是问题","1":"一般问题","2":"严重问题"},
     * "isAdmin":{"0":"否","1":"是"},
     * "reservoir_type":{"1":"小（一）型","2":"小（二）型","3":"中型","4":"大（一）型","5":"大（二）型"},
     * "is_show":{"0":"隐藏","1":"显示"},
     * "superviseShowType":{"0":"0:正常,1:不正常","1":"0:有,1:无","2":"0:完成,1:未完成"},
     * "problemStatus":{"0":"待确认","1":"已确认","2":"已审核","3":"业主已审核","4":"已完结（反馈操作）"},
     * "ACCESS_TYPE":{"0":"IP","1":"SIM卡"},
     * "login_scope":{"0":"PC","1":"APP","2":"公众号"},
     * "operationType":{"1":"巡检","2":"维护","3":"保洁","4":"其他类型"},
     * "isSys":{"0":"否","1":"是"},
     * "status":{"0":"正常","2":"禁用"}}}
     */

    private DictMapEntity data;

    public DictMapEntity getData() {
        return data;
    }

    public void setData(DictMapEntity data) {
        this.data = data;
    }


}
