package com.tepia.voice.xunfei;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.iflytek.aiui.AIUIAgent;
import com.iflytek.aiui.AIUIConstant;
import com.iflytek.aiui.AIUIEvent;
import com.iflytek.aiui.AIUIListener;
import com.iflytek.aiui.AIUIMessage;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.SynthesizerListener;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.iflytek.cloud.util.ResourceUtil;
import com.tepia.base.utils.AppManager;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.Utils;
import com.tepia.voice.R;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import io.reactivex.subjects.PublishSubject;

import static android.content.Context.AUDIO_SERVICE;

/**
 * Created by Joeshould on 2017/8/28.
 */

public class XuFeiManager {

    private static final String TAG = XuFeiManager.class.getSimpleName();
    private static final String XUFEI_APP_ID = "5afa4d62";
    private static XuFeiManager ourInstance;

    /***/
    // 语音合成对象
    private SpeechSynthesizer mTts;

    // 默认云端发音人
    public static String voicerCloud = "xiaoyan";

    /***/
    // 语音听写对象
    private SpeechRecognizer mIat;
    // 语音听写UI
    private RecognizerDialog mIatDialog;
    private RecognizerDialogListener mRecognizerDialogListener;
    private RecognizerListener mRecognizerListener;
    private StringBuffer mResultText;

    /******/
    private AIUIAgent mAIUIAgent;
    private AIUIListener mAIUIListener;
    private int mAIUIState;

    /***/
    public PublishSubject<String> mIatSubject = PublishSubject.create();

    private String tempTelephoneNumber;
    private SpeechAnimCallBack speechAnim;
    private boolean isDialogShow;
    private MediaPlayer mp;
    private boolean isAudio = false;
    private List<OtherCmdBean> otherCmdBeanList;


    public static XuFeiManager getInstance() {
        if (ourInstance == null) {
            ourInstance = new XuFeiManager();
        }
        return ourInstance;
    }

    private XuFeiManager() {
        init(Utils.getContext());

        initTTS(Utils.getContext());

        initSpeech(Utils.getContext());

        boolean ret = initAIUI();
        if (ret) LogUtil.d("initAIUI ret ：" + ret);
    }

    private void initAIUIListener() {
        mAIUIListener = new AIUIListener() {
            @Override
            public void onEvent(AIUIEvent event) {
                switch (event.eventType) {
                    case AIUIConstant.EVENT_WAKEUP:
                        LogUtil.d("on event: " + event.eventType);
                        LogUtil.d("进入识别状态");
                        break;

                    case AIUIConstant.EVENT_RESULT: {
                        try {
                            JSONObject bizParamJson = new JSONObject(event.info);
                            JSONObject data = bizParamJson.getJSONArray("data").getJSONObject(0);
                            JSONObject params = data.getJSONObject("params");
                            JSONObject content = data.getJSONArray("content").getJSONObject(0);

                            if (content.has("cnt_id")) {
                                String cnt_id = content.getString("cnt_id");
                                JSONObject cntJson = new JSONObject(new String(event.data.getByteArray(cnt_id), "utf-8"));
//                                mNlpText.append( "\n" );
//                                mNlpText.append(cntJson.toString());
//                                LogUtil.d(cntJson.toString());
                                String sub = params.optString("sub");
                                if ("nlp".equals(sub)) {
                                    // 解析得到语义结果
                                    String resultStr = cntJson.optString("intent");
                                    LogUtil.d(resultStr);
                                    handlerAIUIResult(resultStr);
                                }
                            }
                        } catch (Throwable e) {
                            e.printStackTrace();
//                            mNlpText.append( "\n" );
//                            mNlpText.append( e.getLocalizedMessage() );
                        }

//                        mNlpText.append( "\n" );
                    }
                    break;

                    case AIUIConstant.EVENT_ERROR: {
                        LogUtil.d("on event: " + event.eventType);
//                        mNlpText.append( "\n" );
//                        mNlpText.append( "错误: "+event.arg1+"\n"+event.info );
                        LogUtil.d("错误: " + event.arg1 + "\n" + event.info);
                        speak("我没听明白，你可以这样说：");
                        if (speechAnim != null) {
                            speechAnim.onError();
                        }
                    }
                    break;

                    case AIUIConstant.EVENT_VAD: {
                        if (AIUIConstant.VAD_BOS == event.arg1) {
                            LogUtil.d("找到vad_bos");
                        } else if (AIUIConstant.VAD_EOS == event.arg1) {
                            LogUtil.d("找到vad_eos");
                        } else {
                            LogUtil.d("" + event.arg2);
                        }
                    }
                    break;

                    case AIUIConstant.EVENT_START_RECORD: {
                        LogUtil.d("on event: " + event.eventType);
                        LogUtil.d("开始录音");
                    }
                    break;

                    case AIUIConstant.EVENT_STOP_RECORD: {
                        LogUtil.d("on event: " + event.eventType);
                        LogUtil.d("停止录音");
                    }
                    break;

                    case AIUIConstant.EVENT_STATE: {    // 状态事件
                        mAIUIState = event.arg1;

                        if (AIUIConstant.STATE_IDLE == mAIUIState) {
                            // 闲置状态，AIUI未开启
                            LogUtil.d("STATE_IDLE");
                        } else if (AIUIConstant.STATE_READY == mAIUIState) {
                            // AIUI已就绪，等待唤醒
                            LogUtil.d("STATE_READY");
                        } else if (AIUIConstant.STATE_WORKING == mAIUIState) {
                            // AIUI工作中，可进行交互
                            LogUtil.d("STATE_WORKING");
                        }
                    }
                    break;

                    case AIUIConstant.EVENT_CMD_RETURN: {
                        if (AIUIConstant.CMD_UPLOAD_LEXICON == event.arg1) {
                            LogUtil.d("上传" + (0 == event.arg2 ? "成功" : "失败"));
                        }
                    }
                    break;

                    default:
                        break;
                }
            }

        };
    }

    /**
     * @param resultStr
     */
    private void handlerAIUIResult(String resultStr) {

        if (!TextUtils.isEmpty(resultStr)) {
            try {
                JSONObject root = new JSONObject(resultStr);
                if (root.has("service")) {
                    String serviceStr = root.getString("service");
                    switch (serviceStr) {
                        case "openQA":
                        case "calc":
                        case "datetime":
                        case "poetry":
                        case "holiday":
                        case "riddle":
                        case "idiom":
                        case "constellation":
                        case "translation":
                        case "weather":
                        case "baike": {
                            AIUIResultBean mAIUIResultBean = new Gson().fromJson(resultStr, AIUIResultBean.class);
                            speak(mAIUIResultBean.getAnswer().getText());
                        }
                        break;
                        case "JOESHOULD123.SKGJ": {
                            try {
                                AIUICustomResultBean mAIUIResultBean = new Gson().fromJson(resultStr, AIUICustomResultBean.class);
                                if (mAIUIResultBean.getAnswer() != null &&
                                        !TextUtils.isEmpty(mAIUIResultBean.getAnswer().getText())) {
                                    speak(mAIUIResultBean.getAnswer().getText());
                                }
                                if (speechAnim != null && mAIUIResultBean != null
                                        && mAIUIResultBean.getData() != null
                                        && mAIUIResultBean.getData().getResult() != null
                                        && mAIUIResultBean.getData().getResult().get(0) != null) {
                                    speechAnim.customCmd(mAIUIResultBean.getData().getResult().get(0));
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                        default:
                            speak("这里还没开发完成，请等待下一版本哦。。。");
                            break;
                    }
                    if (!isDialogShow && speechAnim != null) {
                        speechAnim.onSuccess();
                    }
                } else {
                    speak("不好意思，我好像没听懂。您可以这样说：");
                    if (!isDialogShow && speechAnim != null) {
                        speechAnim.onError();
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    private boolean initAIUI() {
        initAIUIListener();
        if (null == mAIUIAgent) {
            LogUtil.d("create aiui agent");
            mAIUIAgent = AIUIAgent.createAgent(Utils.getContext(), getAIUIParams(), mAIUIListener);
            AIUIMessage startMsg = new AIUIMessage(AIUIConstant.CMD_START, 0, 0, null, null);
            mAIUIAgent.sendMessage(startMsg);
        }

        if (null == mAIUIAgent) {
            final String strErrorTip = "创建 AIUI Agent 失败！";
            LogUtil.d(strErrorTip);

        }
        return null != mAIUIAgent;
    }

    public void recognition(String text_reco) {
        text_reco = text_reco.replace("。", "");
        if (handlerOtherCmd(text_reco)) {
        } else {
            // 先发送唤醒消息，改变AIUI内部状态，只有唤醒状态才能接收语音输入
            if (AIUIConstant.STATE_WORKING != this.mAIUIState) {
                AIUIMessage wakeupMsg = new AIUIMessage(AIUIConstant.CMD_WAKEUP
                        , 0
                        , 0
                        , ""
                        , null);
                mAIUIAgent.sendMessage(wakeupMsg);
            }

            String params = "data_type=text";

            if (TextUtils.isEmpty(text_reco)) {
                text_reco = " .";
            }

            byte[] textData = text_reco.getBytes();

            AIUIMessage msg = new AIUIMessage(AIUIConstant.CMD_WRITE, 0, 0, params, textData);
            mAIUIAgent.sendMessage(msg);
        }
    }

    private boolean handlerOtherCmd(String text_reco) {

        boolean isOtherCmd = false;
        if (otherCmdBeanList == null || otherCmdBeanList.size() == 0) {
            return isOtherCmd;
        } else {
            for (OtherCmdBean bean : otherCmdBeanList) {
                if (bean.getCmdStriList() != null && bean.getCmdStriList().size() != 0) {
                    for (String cmd : bean.getCmdStriList()) {
                        if (text_reco.contains(cmd)) {
                            isOtherCmd = true;
                            if (speechAnim != null) {
                                speechAnim.otherCmd(bean.getCode(), text_reco);
                                return isOtherCmd;
                            }
                        }
                    }
                }
            }
        }

        return isOtherCmd;
    }

    private String getAIUIParams() {
        String params = "";

        AssetManager assetManager = Utils.getContext().getResources().getAssets();
        try {
            InputStream ins = assetManager.open("cfg/aiui_phone.cfg");
            byte[] buffer = new byte[ins.available()];

            ins.read(buffer);
            ins.close();

            params = new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return params;
    }

    private void initSpeech(Context context) {
        // 初始化识别无UI识别对象
        // 使用SpeechRecognizer对象，可根据回调消息自定义界面；
        final InitListener mInitListener = new InitListener() {
            @Override
            public void onInit(int code) {
                Log.d(TAG, "InitListener init() code = " + code);
                if (code != ErrorCode.SUCCESS) {
                    LogUtil.d("初始化失败,错误码：" + code);

                } else {
                    // 初始化成功，之后可以调用startSpeaking方法
                    // 注：有的开发者在onCreate方法中创建完合成对象之后马上就调用startSpeaking进行合成，
                    // 正确的做法是将onCreate中的startSpeaking调用移至这里
                }
            }
        };
        mIat = SpeechRecognizer.createRecognizer(context, mInitListener);

        // 初始化听写Dialog，如果只使用有UI听写功能，无需创建SpeechRecognizer
        // 使用UI听写功能，请根据sdk文件目录下的notice.txt,放置布局文件和图片资源

        if (mIat != null) {
            setSpeechParam();
        }
        mRecognizerListener = new RecognizerListener() {

            @Override
            public void onBeginOfSpeech() {
                // 此回调表示：sdk内部录音机已经准备好了，用户可以开始语音输入
                LogUtil.d("开始说话");
                isAudio = true;
            }

            @Override
            public void onError(SpeechError error) {
                isAudio = false;
                LogUtil.d(error.getPlainDescription(true));
                if (error.getErrorCode() == 10118) {
                    speak("您好像没有说话哦");

                }
                if (error.getErrorCode() == 10114) {
                    speak("网络连接发生异常");
                }
                if (speechAnim != null) {
                    speechAnim.onError();
                }
                // Tips：
                // 错误码：10118(您没有说话)，可能是录音机权限被禁，需要提示用户打开应用的录音权限。

            }

            @Override
            public void onEndOfSpeech() {
                // 此回调表示：检测到了语音的尾端点，已经进入识别过程，不再接受语音输入
                LogUtil.d("结束说话");
                isAudio = false;
            }

            @Override
            public void onResult(RecognizerResult results, boolean isLast) {
                LogUtil.d(new Gson().toJson(results));
                isAudio = false;
                String text = JsonParser.parseIatResult(results.getResultString());
                mResultText.append(text);
                if (isLast) {
                    TalkBean bean = new TalkBean();
                    bean.setLeft(false);
                    bean.setContext(mResultText.toString());
                    bean.save();
                    if (!isDialogShow && speechAnim != null) {
                        speechAnim.refreshView();
                    }
                    LogUtil.d(new Gson().toJson(mResultText));
                    mIatSubject.onNext(mResultText.toString());
                    recognition(mResultText.toString());
                    mIat.stopListening();
                }
            }

            @Override
            public void onVolumeChanged(int volume, byte[] data) {
                LogUtil.d("当前正在说话，音量大小：" + volume);
                if (speechAnim != null) {
                    speechAnim.onVolumeChanged(volume);
                }
            }

            @Override
            public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {
                // 以下代码用于获取与云端的会话id，当业务出错时将会话id提供给技术支持人员，可用于查询会话日志，定位出错原因
                // 若使用本地能力，会话id为null
                //	if (SpeechEvent.EVENT_SESSION_ID == eventType) {
                //		String sid = obj.getString(SpeechEvent.KEY_EVENT_SESSION_ID);
                //		Log.d(TAG, "session id =" + sid);
                //	}
            }
        };

        mRecognizerDialogListener = new RecognizerDialogListener() {

            @Override
            public void onResult(RecognizerResult results, boolean isLast) {
                isAudio = false;
                String text = JsonParser.parseIatResult(results.getResultString());
                mResultText.append(text);
                if (isLast) {
                    TalkBean bean = new TalkBean();
                    bean.setLeft(false);
                    bean.setContext(mResultText.toString());
                    bean.save();
                    if (!isDialogShow && speechAnim != null) {
                        speechAnim.refreshView();
                    }
                    LogUtil.d(new Gson().toJson(mResultText));
                    mIatSubject.onNext(mResultText.toString());
                    recognition(mResultText.toString());
                    mIat.stopListening();
                }

            }

            /**
             * 识别回调错误.
             */
            @Override
            public void onError(SpeechError error) {
                isAudio = false;
                if (error.getErrorCode() == 10118) {
                    speak(error.getErrorDescription() + "，您可以这样说：", new SpreakCompleteLister() {
                        @Override
                        public void onCompleted() {
                            mIatDialog.dismiss();
                        }
                    });
                }
                mResultText = new StringBuffer();
                mIat.stopListening();
            }

        };
    }

    public void startSpeech(final boolean isShowDialog) {
        if (mTts.isSpeaking()) {
            mTts.stopSpeaking();
        }
        mp = MediaPlayer.create(Utils.getContext(), R.raw.collide);
        mp.start();
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mResultText = new StringBuffer();

                isDialogShow = isShowDialog;
                isAudio = true;
                if (isShowDialog) {
                    // 显示听写对话框
                    mIatDialog = new RecognizerDialog(AppManager.getInstance().getCurrentActivity(), null);
                    mIatDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialogInterface) {
                            isAudio = false;
                        }
                    });
                    mIatDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface) {
                            isAudio = false;
                        }
                    });
                    mIatDialog.setListener(mRecognizerDialogListener);
                    mIatDialog.show();
                } else {
                    // 不显示听写对话框
                    if (mIatDialog != null && mIatDialog.isShowing()) {
                        mIatDialog.dismiss();
                    }

                    int ret = mIat.startListening(mRecognizerListener);

                    if (ret != ErrorCode.SUCCESS) {
                        LogUtil.d("听写失败,错误码：" + ret);
                    }
                }
            }
        });

    }

    public void init(Context context) {
        // 应用程序入口处调用,避免手机内存过小,杀死后台进程后通过历史intent进入Activity造成SpeechUtility对象为null
        // 注意：此接口在非主进程调用会返回null对象，如需在非主进程使用语音功能，请增加参数：SpeechConstant.FORCE_LOGIN+"=true"
        // 参数间使用“,”分隔。
        // 设置你申请的应用appid

        // 注意： appid 必须和下载的SDK保持一致，否则会出现10407错误

        StringBuffer param = new StringBuffer();
        param.append("appid=" + XUFEI_APP_ID);
        param.append(",");
        // 设置使用v5+
        param.append(SpeechConstant.ENGINE_MODE + "=" + SpeechConstant.MODE_MSC);
        SpeechUtility.createUtility(context, param.toString());
    }


    public void initTTS(Context context) {
        // 初始化合成对象
        InitListener mTtsInitListener = new InitListener() {
            @Override
            public void onInit(int code) {
                Log.d(TAG, "InitListener init() code = " + code);
                if (code != ErrorCode.SUCCESS) {
                    LogUtil.d("初始化失败,错误码：" + code);
                } else {
                    // 初始化成功，之后可以调用startSpeaking方法
                    // 注：有的开发者在onCreate方法中创建完合成对象之后马上就调用startSpeaking进行合成，
                    // 正确的做法是将onCreate中的startSpeaking调用移至这里
                }
            }
        };
        mTts = SpeechSynthesizer.createSynthesizer(context, mTtsInitListener);
        if (mTts == null) {
            return;
        }
        // 清空参数
        mTts.setParameter(SpeechConstant.PARAMS, null);

        //设置使用云端引擎
        mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);
        //设置发音人
        mTts.setParameter(SpeechConstant.VOICE_NAME, voicerCloud);

        //设置合成语速
        mTts.setParameter(SpeechConstant.SPEED, "50");
        //设置合成音调
        mTts.setParameter(SpeechConstant.PITCH, "50");
        //设置合成音量
        mTts.setParameter(SpeechConstant.VOLUME, "50");
        //设置播放器音频流类型
        mTts.setParameter(SpeechConstant.STREAM_TYPE, "3");

        // 设置播放合成音频打断音乐播放，默认为true
        mTts.setParameter(SpeechConstant.KEY_REQUEST_FOCUS, "false");

        // 设置音频保存路径，保存音频格式支持pcm、wav，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE权限
        // 注：AUDIO_FORMAT参数语记需要更新版本才能生效
        mTts.setParameter(SpeechConstant.AUDIO_FORMAT, "wav");
        mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, Environment.getExternalStorageDirectory() + "/msc/tts.wav");
    }

    /**
     * @param msg
     * @param type type == 1 开始听
     */
    public void speak(String msg, final int type) {
        mTts.startSpeaking(msg, new SynthesizerListener() {
            @Override
            public void onSpeakBegin() {

            }

            @Override
            public void onBufferProgress(int i, int i1, int i2, String s) {

            }

            @Override
            public void onSpeakPaused() {

            }

            @Override
            public void onSpeakResumed() {

            }

            @Override
            public void onSpeakProgress(int i, int i1, int i2) {

            }

            @Override
            public void onCompleted(SpeechError speechError) {
                if (speechError == null) {
                    if (type == 1) {
                        startSpeech(true);
                    }
                } else if (speechError != null) {
                    LogUtil.d(speechError.getPlainDescription(true));
                }

            }

            @Override
            public void onEvent(int i, int i1, int i2, Bundle bundle) {

            }
        });
    }

    public void speak(String msg) {
        if (mTts.isSpeaking()) {
            mTts.stopSpeaking();
        }
        TalkBean bean = new TalkBean();
        bean.setLeft(true);
        bean.setContext(msg);
        bean.save();

        if (!isDialogShow && speechAnim != null) {
            speechAnim.refreshView();
        }
        final AudioManager am = (AudioManager) Utils.getContext().getSystemService(AUDIO_SERVICE);
        am.requestAudioFocus(null, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
        mTts.startSpeaking(msg + "，。，。，。，。", new SynthesizerListener() {
            @Override
            public void onSpeakBegin() {

            }

            @Override
            public void onBufferProgress(int i, int i1, int i2, String s) {

            }

            @Override
            public void onSpeakPaused() {

            }

            @Override
            public void onSpeakResumed() {

            }

            @Override
            public void onSpeakProgress(int i, int i1, int i2) {

            }

            @Override
            public void onCompleted(SpeechError speechError) {
                am.abandonAudioFocus(null);
                if (speechError == null) {

                } else if (speechError != null) {
                    LogUtil.d(speechError.getPlainDescription(true));
                    speak("网络连接发生异常.");
                }

            }

            @Override
            public void onEvent(int i, int i1, int i2, Bundle bundle) {

            }
        });
    }

    public void speak(String msg, final SpreakCompleteLister listener) {
        if (mTts.isSpeaking()) {
            mTts.stopSpeaking();
        }
        TalkBean bean = new TalkBean();
        bean.setLeft(true);
        bean.setContext(msg);
        bean.save();

        if (!isDialogShow && speechAnim != null) {
            speechAnim.refreshView();
        }
        final AudioManager am = (AudioManager) Utils.getContext().getSystemService(AUDIO_SERVICE);
        am.requestAudioFocus(null, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
        mTts.startSpeaking(msg, new SynthesizerListener() {
            @Override
            public void onSpeakBegin() {

            }

            @Override
            public void onBufferProgress(int i, int i1, int i2, String s) {

            }

            @Override
            public void onSpeakPaused() {

            }

            @Override
            public void onSpeakResumed() {

            }

            @Override
            public void onSpeakProgress(int i, int i1, int i2) {

            }

            @Override
            public void onCompleted(final SpeechError speechError) {
                am.abandonAudioFocus(null);

                if (speechError == null) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            listener.onCompleted();
                        }
                    }, 50);

                } else if (speechError != null) {
                    LogUtil.d(speechError.getPlainDescription(true));
                }

            }

            @Override
            public void onEvent(int i, int i1, int i2, Bundle bundle) {

            }
        });
    }

    /**
     * 参数设置
     *
     * @param
     * @return
     */
    public void setSpeechParam() {
        // 清空参数
        mIat.setParameter(SpeechConstant.PARAMS, null);
        // 设置引擎
        mIat.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);
        // 设置返回结果格式
        mIat.setParameter(SpeechConstant.RESULT_TYPE, "json");

        // 设置语言
        mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");

        // 设置语音前端点:静音超时时间，即用户多长时间不说话则当做超时处理
        mIat.setParameter(SpeechConstant.VAD_BOS, "7000");

        // 设置语音后端点:后端点静音检测时间，即用户停止说话多长时间内即认为不再输入， 自动停止录音
        mIat.setParameter(SpeechConstant.VAD_EOS, "3000");

        // 设置标点符号,设置为"0"返回结果无标点,设置为"1"返回结果有标点
        mIat.setParameter(SpeechConstant.ASR_PTT, "1");

        // 设置音频保存路径，保存音频格式支持pcm、wav，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE权限
        // 注：AUDIO_FORMAT参数语记需要更新版本才能生效
        mIat.setParameter(SpeechConstant.AUDIO_FORMAT, "wav");
        mIat.setParameter(SpeechConstant.ASR_AUDIO_PATH, Environment.getExternalStorageDirectory() + "/msc/iat.wav");
    }

    private String getResource() {
        final String resPath = ResourceUtil.generateResourcePath(Utils.getContext(), ResourceUtil.RESOURCE_TYPE.assets, "ivw/" + XUFEI_APP_ID + ".jet");
        Log.d(TAG, "resPath: " + resPath);
        return resPath;
    }

    public void speakNavString(String msg) {
        if (isAudio) return;
        final AudioManager am = (AudioManager) Utils.getContext().getSystemService(AUDIO_SERVICE);
        am.requestAudioFocus(null, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK);
        mTts.startSpeaking(msg + "，。，。，。，。", new SynthesizerListener() {
            @Override
            public void onSpeakBegin() {

            }

            @Override
            public void onBufferProgress(int i, int i1, int i2, String s) {

            }

            @Override
            public void onSpeakPaused() {

            }

            @Override
            public void onSpeakResumed() {

            }

            @Override
            public void onSpeakProgress(int i, int i1, int i2) {

            }

            @Override
            public void onCompleted(final SpeechError speechError) {
                am.abandonAudioFocus(null);
            }

            @Override
            public void onEvent(int i, int i1, int i2, Bundle bundle) {

            }
        });
    }

    public void StopSpeaking() {
        mTts.stopSpeaking();
    }

    public void setSpeechAnimCallBack(SpeechAnimCallBack speechAnim) {
        this.speechAnim = speechAnim;
    }

    public void setOtherCmd(List<OtherCmdBean> otherCmdBeanList) {
        this.otherCmdBeanList = otherCmdBeanList;
    }

    public interface SpeechAnimCallBack {
        void onVolumeChanged(int volume);

        void onError();

        void onSuccess();

        void refreshView();

        void otherCmd(int code, String msg);

        void customCmd(Map<String, Object> stringObjectMap);
    }
}
