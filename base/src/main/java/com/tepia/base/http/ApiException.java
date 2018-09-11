package com.tepia.base.http;

/**
 * Describe:RuntimeException
 * Created by liying on 2018/2/7.
 */
public class ApiException extends RuntimeException{

    public static final int USER_NOT_EXIST = 100;
    public static final int WRONG_PASSWORD = 101;
    private static String message;

    public ApiException(int resultCode) {
        this(getApiExceptionMessage(resultCode));
    }

    public ApiException(String detailMessage) {
        super(detailMessage);
    }

    @Override
    public String getMessage() {
        return message;
    }

    /**
     * 由于服务器传递过来的错误信息直接给用户看的话，用户未必能够理解
     * 需要根据错误码对错误信息进行一个转换，在显示给用户
     * @param code
     * @return
     */
    private static String getApiExceptionMessage(int code){
        switch (code) {
            case USER_NOT_EXIST:
                message = "error";
                break;
            case WRONG_PASSWORD:
                message = "密码错误";
                break;
            case 1000:
                message = "取消dialog";
                break;
            default:
                message = "未知错误";
        }
        return message;
    }
}
