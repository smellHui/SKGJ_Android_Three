package com.tepia.base;

/**
 * Created by Joeshould on 2018/5/22.
 */

public class AppRoutePath {

    /**
     * ------------------------------------------
     *
     * 任何路径名称改动需要告知后台，否则无法正确显示或进入相关页面
     * 并注释上具体跳转页面
     *
     * ------------------------------------------
     */


    public final static String test = "/costom/test";
    public final static String appMain = "/app/main";

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


}
