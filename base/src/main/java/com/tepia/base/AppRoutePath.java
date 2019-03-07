package com.tepia.base;

/**
 * Created by Joeshould on 2018/5/22.
 */

public class AppRoutePath {

    /**
     * ------------------------------------------
     * <p>
     * 任何路径名称改动需要告知后台，否则无法正确显示或进入相关页面
     * 并注释上具体跳转页面
     * <p>
     * ------------------------------------------
     */


    public final static String test = "/costom/test";
    public final static String appMain = "/app/main";

    /**
     * 巡查责任人首页 （第一页）（包含 巡检 保洁 维修责任人）
     */
//    public final static String app_main_fragment_home_xuncha = "/app/main/fragment/home/xuncha";
    public final static String app_main_fragment_home_xuncha = "/app/main/fragment/home";
    /**
     * 技术责任人首页 （第一页）
     */
//    public final static String app_main_fragment_home_jishu = "/app/main/fragment/home/jishu";
    public final static String app_main_fragment_home_jishu = "/app/main/fragment/home";
    /**
     * 行政责任人首页 （第一页）
     */
//    public final static String app_main_fragment_home_admin = "/app/main/fragment/home/xingzheng";
    public final static String app_main_fragment_home_admin = "/app/main/fragment/home";

    /**
     * 巡检责任人 运维页（第二页）
     */
    public final static String app_main_fragment_yunwei_xunjian = "/app/main/fragment/yunwei/xunjian";
    /**
     * 保洁责任人 运维页（第二页）
     */
    public final static String app_main_fragment_yunwei_baojie = "/app/main/fragment/yunwei/baojie";
    /**
     * 维护责任人 运维页（第二页）
     */
    public final static String app_main_fragment_yunwei_weihu = "/app/main/fragment/yunwei/weihu";
    /**
     * 巡查责任人 运维页（第二页）（身兼多职的人员 巡检保洁）
     */
    public final static String app_main_fragment_yunwei = "/app/main/fragment/yunwei/more";
    /**
     * 技术责任人 运维页（第二页）
     */
    public final static String app_main_fragment_yunwei_jishu = "/app/main/fragment/yunwei/jishu";
    /**
     * 行政责任人 运维页（第二页）
     */
    public final static String app_main_fragment_yunwei_xingzheng = "/app/main/fragment/yunwei/xingzheng";

    /**
     * 巡查责任人 上报页（第三页）
     */
    public final static String app_main_fragment_shangbao_xuncha = "/app/main/fragment/shangbao/xuncha";
    /**
     * 技术责任人 三个重点页（第三页）
     */
//    public final static String app_main_fragment_threekey_jishu = "/app/main/fragment/threekey/jishu";
    public final static String app_main_fragment_threekey_jishu = "/app/main/fragment/threekey";

    /**
     * 行政责任人 三个重点页（第三页）
     */
    public final static String app_main_fragment_threekey_xingzheng = "/app/main/fragment/threekey";

    /**
     * 综合监控
     */
    public final static String app_main_fragment_look = "/app/main/fragment/look";


    /**
     * 水库页（第四页）
     */
    public final static String app_main_fragment_reservoirs = "/app/main/fragment/reservoirs";
    public final static String app_main_fragment_reservoirs_waterlevel = "/app/main/fragment/reservoirs/waterlevel";


    /**
     * 我的页（第五页）
     */
    public final static String app_main_fragment_mine = "/app/main/fragment/mine";


    /**
     * 首页巡检、维护、保洁
     */
    public final static String app_task_list = "/app/task/list";
    /**
     * 工单计划页面
     */
    public final static String app_gongdan_jihua = "/app/gongdan/jihua";
    public final static String app_task_detail = "/app/task/detail";
    public final static String app_task_deal = "/app/task/deal";
    public final static String app_task_edit = "/app/task/edit";
    public final static String app_plan_list = "/app/plan/list";

    public final static String app_task_list_2 = "/app/task/list/2";
    public final static String app_task_detail_2 = "/app/task/detail/2";
    /**
     * 巡检责任人问题上报页面
     */
    public final static String app_question = "/app/question";
    /**
     * 巡检责任人问题上报页面详情
     */
    public final static String app_question_list = "/app/question/list";
    /**
     * 上级责任人获取待处理问题事件列表
     */
    public final static String app_up_question_list = "/app/up/question/list";
    /**
     * 上级责任人获取待申恶化问题事件列表
     */
    public final static String app_up_question_configure_list = "/app/up/question/configure/list";


    /**
     * ----------------------------------------------------------------------------------------------
     */
    /**
     * 密码修改页面
     */
    public final static String app_password = "/app/password";

    public final static String app_speak = "/app/speak";
    public final static String app_voiceassistant_readme = "/app/voiceassistant/readme";

    /**
     * 语音助手
     */
    public final static String app_reservoir_list = "/app/reservoir/list";
    public final static String app_messtation_list = "/app/messtation/list";
    public final static String applogin = "/app/login";
    public final static String token = "/app/token";


    /**
     * 天气预报
     */
    public final static String app_weather_forecast = "/app/weather/forecast";
    /**
     * 通信录
     */
    public final static String app_contacts = "/app/contacts";
    /**
     * 防汛物资
     */
    public final static String app_flood_detail = "/app/flood/detail";
    /**
     * 工作通知
     */
    public final static String app_work_notification_leader = "/app/work/notification/leader";
    public final static String app_work_notification_worker = "/app/work/notification/woeker";
    public final static String app_work_notification_leader_detail = "/app/work/notification/leader/detail";
    public final static String app_work_notification_worker_detail = "/app/work/notification/worker/detail";
    public final static String app_add_work_notification = "/app/add/work/notification";

    public final static String app_select_reservor = "/app/select/reservor";
    public final static String app_work_order_question_list = "/app/work/order/question/list";
    public final static String app_reservoir_vr = "/app/reservoir/vr";
    public final static String app_RealTime_WaterLevel_StorageCapacity = "/app/RealTime/WaterLevel/StorageCapacity";
    public final static String app_task_work_report = "/app/task/work/report";
    /**
     * 行政运维具体月份
     */
    public final  static String app_admin_operation = "/app/admin/operation";
    /**
     * 行政运维具体月份上报
     */
    public  final static String app_admin_operation_report = "/app/admin/operation/report";


    /**
     * 添加或者修改 防汛物资
     */
    public final static String app_flood_add_or_edit = "/app/flood/add/or/edit";
}
