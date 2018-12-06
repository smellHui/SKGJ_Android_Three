package com.tepia.main.view.maincommon.reservoirs.dvrapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.MediaCodec;
import android.media.MediaFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.storage.StorageManager;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fourfaith.VideoAPI;
import com.fourfaith.common.BaseCommonConstant;
import com.fourfaith.common.BaseCommonFunc;
import com.fourfaith.netty.NettyPressureClient;
import com.fourfaith.netty.client.NettySocketMgr;
import com.githang.statusbar.StatusBarCompat;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.base.utils.DoubleClickUtil;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.ScreenUtil;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.view.dialog.loading.SimpleLoadDialog;
import com.tepia.main.R;
import com.tepia.main.view.maincommon.reservoirs.detail.VideoPlayThreeActivity;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Locale;

/**
 * Created by yebinghuai on 2017/5/2.
 */

/**
 * Created by      Android studio
 *
 * @author :ly (from Center Of Wuhan)
 * Date    : 2018-10-11
 * Version :1.0
 * 功能描述 : 四信视频
 **/
public class VideoSixinActivity extends BaseActivity {
    String ipAddr = "";
    int port = 10000;
    String deviceId = "";
    //String surl ="";
    String sRemark = "";
    public static int iChannelAmt = 0;


    ByteArrayInputStream bais;

    public static SurfaceView mSurfaceView;
    public static MediaCodec mCodec;

    public static ReadViewThread readViewThread;


    // Video Constants
    private final static String MIME_TYPE = "video/avc"; // H.264 Advanced Video
    private final static int VIDEO_WIDTH = 1920;
    private final static int VIDEO_HEIGHT = 1080;
    private final static int TIME_INTERNAL = 10;
    private final static int HEAD_OFFSET = 512;//512;//32;

    public static NettyPressureClient oNettyPressureClient = null;
    //public static String sDeviceId = "ffmonitor";
    public static int iViewChannel = 0;

    private TextView deviceName;
    private ImageView back;
    private ImageView itemImageBottom;
    static int initbuffflag = 0;
    static byte[] buffer = new byte[300000];
    static byte[] framebuffer = new byte[600000];

    static RadioGroup oRadioGroup;
    public static SimpleLoadDialog dialog;
    public static MyDialog myDialog;
    private int TIME = 10 * 1000;

//    private GoogleApiClient client;


    VideoAPI oVideoActivity = new VideoAPI();

    @Override
    public int getLayoutId() {
        return R.layout.activity_video;
    }

    @Override
    public void initView() {

        initViews();
        fullScreenIv = findViewById(R.id.fullScreenIv);
        fullScreenIv.setVisibility(View.GONE);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            SharedPreferences sharedPreferences = getSharedPreferences(BaseCommonConstant.sConfigIPport, Context.MODE_PRIVATE);
            String serverIP = sharedPreferences.getString("weburl", "");
//            String serverPort = sharedPreferences.getString("serverport", "");

            if (extras.containsKey("serverurl")) {
                serverIP = extras.getString("serverurl");
            }
            //String webIP = sharedPreferences.getString("weburl","");
            //String webPort = sharedPreferences.getString("webport","");
            ipAddr = serverIP;
            port = 10000;


            deviceId = extras.getString("deviceId");
            //surl = extras.getString("surl");
            sRemark = extras.getString("sRemark");
            iChannelAmt = extras.getInt("currentChannelId");

            deviceName.setText(sRemark);
            setCenterTitle(sRemark);
            showBack();

            if (iChannelAmt == 8) {
                LogUtil.e("[VideoSixinActivity.onCreate]currentChannelId == 8  " + iChannelAmt);
            } else {
            }
            VideoAPI oVideoActivity = new VideoAPI();
            //VideoAPI.startConnect(ipAddr, port);
            iViewChannel = 0;
            //new Client_5205_RunnableTask(deviceId, iViewChannel).start();

            LogUtil.e("[VideoSixinActivity.onCreate]ipAddr  " + ipAddr);
            LogUtil.e("[VideoSixinActivity.onCreate]port  " + port);
            LogUtil.e("[VideoSixinActivity.onCreate]deviceId  " + deviceId);
            LogUtil.e("[VideoSixinActivity.onCreate]sRemark  " + sRemark);
            LogUtil.e("[VideoSixinActivity.onCreate]currentChannelId  " + iChannelAmt);


            oVideoActivity.initCreate(ipAddr, port, deviceId, iViewChannel);

            if (readViewThread == null) {
                LogUtil.e("[VideoSixinActivity.onCreate]--------------------------readViewThread == null ");

                readViewThread = new ReadViewThread();
                readFlag = true;
                readViewThread.start();
            }

        }

        fullScreenIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
                if (isFull) {
                    isFull = false;
                    changeFull();

                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    getLoToolbarCommon().setVisibility(View.VISIBLE);
//                    pauseVedio();
//                    resumeVedio();


                } else {
                    isFull = true;
                    changeFull();
                    // 设置当前activity为横屏
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    getLoToolbarCommon().setVisibility(View.GONE);
//                    pauseVedio();
//                    resumeVedio();

                }
            }
        });
    }

    private void changeFull() {
        if (isFull) {
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);

            mSurfaceView.setLayoutParams(params);
            if (Build.VERSION.SDK_INT >= 19) {
                View decorView = getWindow().getDecorView();
                decorView.setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

            }

        } else {
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
            params.height = ScreenUtil.dp2px(this, 220);
            mSurfaceView.setLayoutParams(params);
            if (Build.VERSION.SDK_INT >= 19) {
                getWindow().getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                );
                StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this, R.color.colorPrimary), true);
            } else {
                StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this, R.color.colorPrimary), true);

            }


        }
    }

    /**
     * 返回取消播放
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isFull) {
                isFull = false;
                changeFull();
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                getLoToolbarCommon().setVisibility(View.VISIBLE);
//                pauseVedio();
//                resumeVedio();
                return true;

            }
        } else {
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);

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

    private boolean isFull = false;
    private ImageView fullScreenIv;


    private void initViews() {
        NettySocketMgr.getInstance().setInitVideo(true);//isInit = true;
        initbuffflag = 1;
        mSurfaceView = null;
        mSurfaceView = (SurfaceView) findViewById(R.id.SurfaceView);

        String loadingText = "正在加载视频...";
        dialog = null;
        dialog = new SimpleLoadDialog(VideoSixinActivity.this, loadingText, true);
        dialog.show();

        myDialog = null;
        myDialog = new MyDialog();
        // 每隔10s执行
        timeHandler.postDelayed(timeRunnable, TIME);

        mSurfaceView.setVisibility(View.VISIBLE);

        deviceName = (TextView) findViewById(R.id.deviceName);
        itemImageBottom = (ImageView) findViewById(R.id.ItemImageBottom);
        oRadioGroup = (RadioGroup) findViewById(R.id.video_channel);


        back = (ImageView) findViewById(R.id.back);


        oRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {


                if (dialog == null) {
                    dialog = new SimpleLoadDialog(VideoSixinActivity.this, loadingText, true);
                }
                dialog.show();

                if (myDialog == null) {
                    myDialog = new MyDialog();
                }

                String param = "0";
                if (checkedId == R.id.channel1) {
                    param = "0";
                } else if (checkedId == R.id.channel2) {
                    param = "1";
                } else if (checkedId == R.id.channel3) {
                    param = "2";
                } else if (checkedId == R.id.channel4) {
                    param = "3";
                } else if (checkedId == R.id.channel5) {
                    param = "4";
                } else if (checkedId == R.id.channel6) {
                    param = "5";
                } else if (checkedId == R.id.channel7) {
                    param = "6";
                } else if (checkedId == R.id.channel8) {
                    param = "7";
                } else {
                }

                iViewChannel = Integer.valueOf(param);
                LogUtil.e("[checkedChangeListener]----------------------------------[checkedChangeListener]iViewChannel:  " + iViewChannel);

//                onPause();
                pauseVedio();
//                onResume();
                resumeVedio();
                oVideoActivity.restartCreate(ipAddr, port, deviceId, iViewChannel);

                timeHandler.postDelayed(timeRunnable, TIME); // 每隔10s执行
            }
        });
    }

    private void pauseVedio() {
        LogUtil.e("[onPause]-------------------------------------------------[onPause]");
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
        if (myDialog != null) {
            myDialog = null;
        }
        NettySocketMgr.getInstance().setInitVideo(false);//isInit = false;
        try {
            if (mSurfaceView != null) {
                mSurfaceView = null;
                LogUtil.e("[onPause]-------------------------------------------------[onPause] mSurfaceView = null ");
            }

            NettySocketMgr.getInstance().setNumViewBytes();
            NettySocketMgr.getInstance().getAllViewTemp();

            NettySocketMgr.getInstance().setNumConnection();

            Message message = new Message();
            message.what = 1;
            handlerStop.sendMessage(message);

            release();
            if (oNettyPressureClient != null) {
                oNettyPressureClient.stopConnect();
            }

            if (readViewThread != null) {
                readFlag = false;
//                readViewThread.interrupt();
//                readViewThread.stop();
                readViewThread = null;
                LogUtil.e("[onPause]   readViewThread.stop()  ");
            }
        } catch (Exception e) {
            LogUtil.e("[onPause]exception:   " + e.getMessage());
            if (oNettyPressureClient != null) {
                oNettyPressureClient.stopConnect();
            }

            if (readViewThread != null) {
                readFlag = false;
//                readViewThread.interrupt();
//                readViewThread.stop();
                readViewThread = null;
                LogUtil.e("[onPause]   readViewThread.stop()  ");
            }
            //e.printStackTrace();
        }
    }

    private void resumeVedio() {
        LogUtil.e("[onResume]-----------------------------------------------------[onResume]iViewChannel:  " + iViewChannel + " now time string:  "
                + BaseCommonFunc.getNowTimeString());


        if (mSurfaceView == null) {
            LogUtil.e("[onResume]---------------------------------- mSurfaceView == null ");
            initViews();

        }
        if (readViewThread == null) {
            LogUtil.e("[onResume]--------------------------readViewThread == null ");

            readViewThread = new ReadViewThread();
            readFlag = true;
            readViewThread.start();
        } else {
            if (!readViewThread.isAlive()) {
                readFlag = true;
                readViewThread.start();
            }
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        resumeVedio();
    }


    @Override
    protected void onPause() {
        super.onPause();
        pauseVedio();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
        if (myDialog != null) {
            myDialog = null;
        }
        LogUtil.e("[onDestroy]-------------------------------------------------[onDestroy]");
        NettySocketMgr.getInstance().setInitVideo(false);//isInit = false;
        try {
            if (mSurfaceView != null) {
                mSurfaceView = null;
                LogUtil.e("[onDestroy]-------------------------------------------------[onDestroy] mSurfaceView = null ");
            }

            NettySocketMgr.getInstance().setNumViewBytes();
            NettySocketMgr.getInstance().getAllViewTemp();

            NettySocketMgr.getInstance().setNumConnection();

            Message message = new Message();
            message.what = 1;
            handlerStop.sendMessage(message);

            release();
            if (oNettyPressureClient != null) {
                oNettyPressureClient.stopConnect();
            }
            if (readViewThread != null) {
                readFlag = false;
//                readViewThread.interrupt();
//                readViewThread.stop();
                readViewThread = null;
                LogUtil.e("[onDestroy]   readViewThread.stop()  ");
            }

        } catch (Exception e) {
            LogUtil.e("[onDestroy]exception:   " + e.getMessage());
        }
    }

    public void release() {
        try {
            if (mCodec != null) {
                mCodec = null;
            }
        } catch (Exception e) {
            LogUtil.e("[release]exception " + e.getMessage());
        }

    }

    Handler timeHandler = new Handler();
    Runnable timeRunnable = new Runnable() {

        @Override
        public void run() {
            // handler自带方法实现定时器
            try {
                int iNumViewBytes = NettySocketMgr.getInstance().getNumViewBytes();
                LogUtil.e("[timeRunnable]iNumViewBytes=  " + iNumViewBytes);
                if (iNumViewBytes == 2) {


                    if (readViewThread != null) {
                        LogUtil.e("[timeRunnable]VideoSixinActivity.readViewThread.isAlive()=  " + VideoSixinActivity.readViewThread.isAlive());
                        if (!readViewThread.isAlive()) {
                            readFlag = true;
                            readViewThread.start();
                        }
                    } else {
                        LogUtil.e("[timeRunnable]  VideoSixinActivity.readViewThread == null  ");
                        readViewThread = new ReadViewThread();
                        readFlag = true;
                        readViewThread.start();
                    }
                }

                if (NettySocketMgr.getInstance().getNumNoGenConnection() >= 5) {
                    if (dialog != null) {
                        LogUtil.e("[timeRunnable]--------------------dialog != null  getNumNoGenConnection() >= 5");
                        dialog.dismiss();
                    }
                    if (myDialog != null) {
                        LogUtil.e("[timeRunnable]------------------------------myDialog != null  getNumNoGenConnection() >= 5");
                        myDialog.ShowMsg("提示", "确认",
                                "连接失败,请返回",
                                VideoSixinActivity.this);
                    } else {
                        myDialog = new MyDialog();
                    }

                    NettySocketMgr.getInstance().setNumConnection();

                    release();
                    if (oNettyPressureClient != null) {
                        oNettyPressureClient.stopConnect();
                    }
                    if (readViewThread != null) {
                        readViewThread.interrupt();
                        readViewThread = null;
                    }
                    LogUtil.e("[timeRunnable]-------------------------------------------------timeHandler.removeCallbacks(timeRunnable)");
                    timeHandler.removeCallbacks(timeRunnable);

                    Message message = new Message();
                    message.what = 1;
                    handlerStop.sendMessage(message);
                }

                timeHandler.postDelayed(this, TIME);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    @SuppressLint("HandlerLeak")
    private final Handler handlerStop = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    timeHandler.removeCallbacks(timeRunnable);
                    break;
                default:
            }
            super.handleMessage(msg);
        }
    };

    public void initDecoder() {

        try {

            NettySocketMgr.getInstance().setNumConnection();

            if (mSurfaceView == null) {
                LogUtil.e("[initDecoder]----------------------------------mSurfaceView == null ");
                mSurfaceView = (SurfaceView) findViewById(R.id.SurfaceView);

                mSurfaceView.setVisibility(View.GONE);
                mSurfaceView.setVisibility(View.VISIBLE);

            } else {
                if (mCodec == null) {
                    LogUtil.e("[initDecoder]----------------------------------mCodec == null ");

                    byte[] sps = {0x00, 0x00, 0x00, 0x01, 0x67, 0x4d, 0x00, 0x1e, (byte) 0x95, (byte) 0xa8,
                            0x2c, 0x04, (byte) 0x9a, 0x10, 0x00, 0x00, 0x70, (byte) 0x80, 0x00, 0x15,
                            (byte) 0xf9, 0x08, 0x40};
                    byte[] pps = {0x00, 0x00, 0x00, 0x01, 0x68, (byte) 0xee, 0x3c, (byte) 0x80};

                    mCodec = MediaCodec.createDecoderByType(MIME_TYPE);
                    MediaFormat mediaFormat;
                    //mediaFormat = MediaFormat.createVideoFormat(MIME_TYPE, 1280, 720);
                    mediaFormat = MediaFormat.createVideoFormat(MIME_TYPE, 1920, 1080);

                    mediaFormat.setByteBuffer("csd-0", ByteBuffer.wrap(sps));
                    mediaFormat.setByteBuffer("csd-1", ByteBuffer.wrap(pps));

                    final Surface surface = mSurfaceView.getHolder().getSurface();
                    if (surface != null) {
                        final boolean invalidSurface = !surface.isValid();
                        if (invalidSurface) {
                            LogUtil.e("[initDecoder]----------------------------------invalidSurface == true ");
                            //return;
                        } else {
                            LogUtil.e("[initDecoder]----------------------------------invalidSurface != true ");

                            mCodec.configure(mediaFormat, mSurfaceView.getHolder().getSurface(), null, 0);
                            mCodec.start();
                        }
                    }

                }
            }

        } catch (Exception e) {
            LogUtil.e("[initDecoder]exception:   " + e.getMessage());
        }

    }

    int mCount = 0;

    @SuppressWarnings("deprecation")
    public boolean onFrame(byte[] _buf, int _offset, int _length) {
        try {
            if (checkConnectState()) {
                if (mCodec != null && mSurfaceView != null) {
                    final Surface surface = mSurfaceView.getHolder().getSurface();
                    if (surface != null) {
                        final boolean invalidSurface = !surface.isValid();
                        if (invalidSurface) {
                            //LogUtil.e("[onFrame]----------------------------------invalidSurface == true ");
                            //return;
                        } else {
                            //LogUtil.e("[onFrame]----------------------------------invalidSurface != true ");
                            ByteBuffer[] inputBuffers = mCodec.getInputBuffers();

                            int inputBufferIndex = mCodec.dequeueInputBuffer(10000);
                            //LogUtil.e("[onFrame]    inputBuffers.length:  " + inputBuffers.length  + "  inputBufferIndex:  "  + inputBufferIndex  );
                            if (inputBufferIndex >= 0) {
                                ByteBuffer inputBuffer = inputBuffers[inputBufferIndex];
                                inputBuffer.clear();
                                inputBuffer.put(_buf, _offset, _length);
                                mCodec.queueInputBuffer(inputBufferIndex, 0, _length, mCount * 40000, 0);
                                mCount++;
                            } else {
                                return false;
                            }

                            // Get output buffer index
                            MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
                            int outputBufferIndex = mCodec.dequeueOutputBuffer(bufferInfo, 1);
                            while (outputBufferIndex >= 0) {
                                mCodec.releaseOutputBuffer(outputBufferIndex, true);
                                outputBufferIndex = mCodec.dequeueOutputBuffer(bufferInfo, 0);
                            }

                            return true;
                        }
                    }

                }

            } else {
            }


        } catch (Exception e) {
            LogUtil.e("[onFrame].exception " + e.getMessage());
            e.printStackTrace();

        }

        return true;
    }

    /**
     * Find H264 frame head
     *
     * @param _buffer
     * @param len
     * @return the offset of frame head, return 0 if can not find one
     */
    static int findHead(byte[] _buffer, int len) {
        int i;
        for (i = 4; i < len; i++) {
            if (checkHead(_buffer, i)) {
                break;
            }
        }
        if (i == len) {
            return 0;
        }
        if (i == 4) {
            return 0;
        }
        return i;
    }

    /**
     * Check if is H264 frame head
     *
     * @param _buffer
     * @param offset
     * @return whether the src _buffer is frame head
     */
    static boolean checkHead(byte[] _buffer, int offset) {
        // 00 00 00 01
        if (_buffer[offset] == 0 && _buffer[offset + 1] == 0
                && _buffer[offset + 2] == 0 && _buffer[offset + 3] == 1)//&& ( ((_buffer[4] & 0x1f) == 0x01)||((_buffer[4] & 0x1f) == 0x07))
        {
            if (((_buffer[offset + 4] & 0x1f) == 0x01) || ((_buffer[offset + 4] & 0x1f) == 0x07)) {
                return true;
            } else {
                return false;
            }
        }

        return false;
    }

    static boolean check264Head(byte[] _buffer, int offset) {
        // 00 00 00 01
        if (_buffer[offset] == 0 && _buffer[offset + 1] == 0
                && _buffer[offset + 2] == 0 && _buffer[offset + 3] == 1)//&& ( ((_buffer[4] & 0x1f) == 0x01)||((_buffer[4] & 0x1f) == 0x07))
        {
            return true;
        }

        // 00 00 01
        if (_buffer[offset] == 0 && _buffer[offset + 1] == 0 && _buffer[offset + 2] == 1)
            return true;
        return false;
    }

    static boolean checkConnectState() {
        int iNumNoGenViewBytes = NettySocketMgr.getInstance().getNumNoGenViewBytes();
        //LogUtil.e("[checkConnectState]NettySocketMgr.getInstance().getNumNoGenViewBytes():  " + iNumNoGenViewBytes );
        if (iNumNoGenViewBytes >= 2) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public void onStart() {
        super.onStart();

//        client.connect();
//        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

//        AppIndex.AppIndexApi.end(client, getIndexApiAction());
//        client.disconnect();
    }

    private boolean readFlag = true;

    class ReadViewThread extends Thread {

        @Override
        public void run() {
            int frameOffset = 0;


            while (readFlag) {
                try {
                    fullScreenIv.setVisibility(View.VISIBLE);
                    if (1 == initbuffflag) {
                        frameOffset = 0;
                        framebuffer = new byte[600000];
                        initbuffflag = 0;
                    }
                    byte[] bys = NettySocketMgr.getInstance().removeViewBytesFile();
                    if (bys != null) {
                        LogUtil.e("[ReadViewThread]bys != null  ");
                        NettySocketMgr.getInstance().setNullViewNum();

                        int iNumNoGenViewBytes = NettySocketMgr.getInstance().getNumNoGenViewBytes();
                        LogUtil.e("[ReadViewThread]NettySocketMgr.getInstance().getNumNoGenViewBytes():  " + iNumNoGenViewBytes);
                        if (iNumNoGenViewBytes >= 10) {
                            initDecoder();
                        }
                        if (iNumNoGenViewBytes >= 55) {
                            if (dialog != null) {
                                dialog.dismiss();
                            }
                        }


                        bais = new ByteArrayInputStream(bys);

                        int length = bais.available();
                        LogUtil.e("[readViewBytes]bais.available():  " + length);
                        if (length > 0) {
                            // Read file and fill buffer
                            int count = bais.read(buffer);

                            // Fill frameBuffer
                            if (frameOffset + count < 600000) {
                                System.arraycopy(buffer, 0, framebuffer, frameOffset, count);
                                frameOffset += count;
                            } else {
                                frameOffset = 0;
                                System.arraycopy(buffer, 0, framebuffer, frameOffset, count);
                                frameOffset += count;
                            }

                            // Find H264 head
                            int offset = findHead(framebuffer, frameOffset);

                            while (offset > 0) {
                                if (check264Head(framebuffer, 0)) {
                                    // Fill decoder
                                    boolean flag = onFrame(framebuffer, 0, offset);
                                    if (flag) {
                                        byte[] temp = framebuffer;
                                        framebuffer = new byte[600000];
                                        System.arraycopy(temp, offset, framebuffer,
                                                0, frameOffset - offset);
                                        frameOffset -= offset;

                                        // Continue finding head
                                        offset = findHead(framebuffer, frameOffset);
                                    }

                                } else {

                                    offset = 0;
                                }
                            }
                        } else {

                            frameOffset = 0;
                            readFlag = false;
                        }

                    } else {
                        NettySocketMgr.getInstance().getNullViewNum();
                        LogUtil.e("[ReadViewThread]bys==null ");
                    }
                } catch (Exception e) {
                    LogUtil.e("[ReadViewThread]Exception  " + e.getMessage());
                    //e.printStackTrace();
                    readFlag = false;
                }

                try {
                    Thread.sleep(TIME_INTERNAL);
                } catch (InterruptedException e) {
                    LogUtil.e("[ReadViewThread]InterruptedException " + e.getMessage());
                    //e.printStackTrace();
                    readFlag = false;
                }

            }
        }
    }

    /**
     * 通过反射调用获取内置存储和外置sd卡根路径(通用)
     *
     * @param mContext    上下文
     * @param is_removale 是否可移除，false返回内部存储，true返回外置sd卡
     * @return
     */
    private static String getStoragePath(Context mContext, boolean is_removale) {

        StorageManager mStorageManager = (StorageManager) mContext.getSystemService(Context.STORAGE_SERVICE);
        Class<?> storageVolumeClazz = null;
        try {
            storageVolumeClazz = Class.forName("android.os.storage.StorageVolume");
            Method getVolumeList = mStorageManager.getClass().getMethod("getVolumeList");
            Method getPath = storageVolumeClazz.getMethod("getPath");
            Method isRemovable = storageVolumeClazz.getMethod("isRemovable");
            Object result = getVolumeList.invoke(mStorageManager);
            final int length = Array.getLength(result);
            for (int i = 0; i < length; i++) {
                Object storageVolumeElement = Array.get(result, i);
                String path = (String) getPath.invoke(storageVolumeElement);
                boolean removable = (Boolean) isRemovable.invoke(storageVolumeElement);
                if (is_removale == removable) {
                    return path;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean isZh() {
        Locale locale = getResources().getConfiguration().locale;
        String language = locale.getLanguage();
        if (language.endsWith("zh")) {
            return true;
        } else {
            return false;
        }
    }

    class MyDialog {

        //提示信息
        public void ShowMsg(String _title, String _posbuttonText, String _msg, Context context) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(context);
            dlg.setTitle(_title);
            dlg.setMessage(_msg);
            //dlg.setPositiveButton(_posbuttonText,null);
            dlg.setPositiveButton(_posbuttonText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    VideoSixinActivity.this.finish();
                }
            });
            dlg.setCancelable(false);
            dlg.show();
        }

    }
}
