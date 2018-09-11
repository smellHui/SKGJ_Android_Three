package com.tepia.main.view.main.detail.vedio;

/**
 * copy from鸭溪水务app
 * @author ly
 * @date 2018/8/1
 */
public class Constant {

    /**
     * 基本路径
     */
    public static final String BASE_SEVER = "http://218.201.212.243:11001/YXSW/";


    /**
     * 登录相关
     */
    public interface Login {
        /**
         * 登录成功
         */
        int LOGIN_SUCCESS = 1;
    }

    /**
     * 视频
     */
    public interface Video {

        int SUCCESS_VIDEO = 1;
        /**
         * 加载成功
         */
        int LOAD_SUCCESS = 1;
        /**
         * 加载成功
         */
        int REFREASH = 2;
    }

    /**
     * 主码流标签
     */
    public static final int MAIN_STREAM = 1;
    /**
     * 子码流标签
     */
    public static final int SUB_STREAM = 0;
}
