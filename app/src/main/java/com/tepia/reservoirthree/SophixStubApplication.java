package com.tepia.reservoirthree;

import android.content.Context;
import android.support.annotation.Keep;
import android.support.multidex.MultiDex;
import android.util.Log;
import com.taobao.sophix.PatchStatus;
import com.taobao.sophix.SophixApplication;
import com.taobao.sophix.SophixEntry;
import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;
import com.tencent.bugly.crashreport.CrashReport;
import com.tepia.base.utils.LogUtil;

/**
 * Sophix入口类，专门用于初始化Sophix，不应包含任何业务逻辑。
 * 此类必须继承自SophixApplication，onCreate方法不需要实现。
 * 此类不应与项目中的其他类有任何互相调用的逻辑，必须完全做到隔离。
 * AndroidManifest中设置application为此类，而SophixEntry中设为原先Application类。
 * 注意原先Application里不需要再重复初始化Sophix，并且需要避免混淆原先Application类。
 * 如有其它自定义改造，请咨询官方后妥善处理。
 */
public class SophixStubApplication extends SophixApplication {
    private final String TAG = "SophixStubApplication";
    private final static String idSecret = "25798051";
    private final static String appSecret = "d4a08f2de730003e41464c52cb9f7e7f";
    private final static String RSASECRET = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCnRmfbSjqvftMJSp8143kyIhgeYa9RSGMDv+cLqzPFb3qg/Ec4aYqQTrt5CE8IgLYnmBQb7Sh3+Qu3soQFF334Hxy+1wdUSmMeYgFX84KwmuAwLDAnsF8B4UuDFDbJqMOOsr6/GmOJbxo68uHEF7C4+ZH+ZCJZ7fkdtlns35OFmqUU5cQiXoKYFIvqyO4aOga8cg8R7c8gTjs1x48uT1lCNHQCEwX34sWNmkS5J2EZ7pnooO5DpcI7sTG7tbXFNz6v2eOyM2ktdqO6Rslixz1tydGk4YWOCfi6UUZljoc+S4M7jTe1CxepOYUKx/0IK4qWM6doBXcSSMMCAA0jD1mvAgMBAAECggEAWTH/uz0Pofjciz3RO6j6PTNU7Q5lr9ALjDpN9zjpOERDfYhIadpNWrnA2b8GdaA8gUxknKePRrnz95YnM5fdQetFJrX+YIHx7IeR89YTQBy1orpNSzyOzK/X1iN3O3zUGklR0RE/Nk+aCwjsPeXsBBaANMgsUQvGJIPR2680OK7pQekY3DXs99bFn2HomvM531lk/FPFRxw6ApDnC/mMLT4UjRq46o1v9Bv4Wx+YnfMbFO3iB+9EQn1wJ0CuHQLHtK79z7dOtYjojJkmJBY5aN31xc877C5X7IsVkAxzYnkczwO+uWPLblN9xB/QIDrR8ltzH9007PNX5/3VH2SvUQKBgQDpZKnB5jBoGz+wQez1WWTtutwFovcbQItFaikMn+KxvQS/nLwniVY67C4gVyctH8+0WzpYcDQtoNsJVJPkp293v6Hrl3xqY1ra/B4LhjSAWf/u4UHhAM9ef2dLhLI3JBTQXq7GmvU0uw3/ZQuTDr1qVgcCchPVg09iur75VHy3UwKBgQC3ej4Cor9JuYiAA+81cmBo4VReKco5fIDjyIVmlLDW/7oWJ4vGLL963oS1b6QyD6FyHY+sveHdnQlUZTEpShQ4wKui9hBkxSjDtNfcmvoqOHcSmhhEk3unJV6PAhMgibSZ+/Cj9mQ0o5p5iKCPbCRXY5r4tjz2LrqEKffqd4vUtQKBgQCroz/EPMh0hzV7JcBhXnB+8+BCtOoMvJavYbb2dc5874jCcT1dxf4ILxBs8Cr7YF2bQUvTJhD5TUZHwk9Q5GFxKxik7S6t/oseaDKgPv31NjqlLjsIgVP/JqqMOvXk3z//TOIzZFnXgYh1vZWZfEmIHePhxIsZhguni7ZOHffpyQKBgQCHA3tSMTSWKeMaYMrMB6iORRHyYMog1095TvtrawteirwCxYMDh7Y30jrR0J+A/yOMd9hcAzaL/5z376bnfU24HX+v4WZa4QKIUTFvctOX8gkdNSuaevTJIKSek6ZDkVHG5jBEV3GRkrp/qFyObcOoUvGdB22Asw4pZ0C51tKc6QKBgQCibBSTSCQwMnBlzwcOE4uMyrYBTXSYDb/tZRShuIetQ4QYMA6B6YyzQZmH9O3niFdkb6q5zOWAuQW3lr1lNf4TLMrrPTP7Ju8NOVcK6D7vZUaXUlffWdP/g9T9SeAQk4r+agqDFkOk7+1n8rYid8NfA1KzkSraO00Mi+LrD5PNrQ==";
    // 此处SophixEntry应指定真正的Application，并且保证RealApplicationStub类名不被混淆。
    @Keep
    @SophixEntry(MyApplication.class)
    static class RealApplicationStub {}



    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//         如果需要使用MultiDex，需要在此处调用。
         MultiDex.install(this);
        initSophix();
    }
    private void initSophix() {
        String appVersion = "";
        try {
            appVersion = this.getPackageManager()
                    .getPackageInfo(this.getPackageName(), 0)
                    .versionName;
        } catch (Exception e) {
        }
        final SophixManager instance = SophixManager.getInstance();
        instance.setContext(this)
                .setAppVersion(appVersion)
                .setSecretMetaData(idSecret, appSecret, RSASECRET)
                .setEnableDebug(true)
                .setEnableFullLog()
                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                    @Override
                    public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {
                        if (code == PatchStatus.CODE_LOAD_SUCCESS) {
                            LogUtil.i(TAG, "sophix load patch success!");
                        } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
                            // 如果需要在后台重启，建议此处用SharePreference保存状态。
                            LogUtil.i(TAG, "sophix preload patch success. restart app to make effect.");
                        }
                    }
                }).initialize();
    }
}
