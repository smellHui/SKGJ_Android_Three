package com.tepia.main.view.maincommon.reservoirs.dvrapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.MediaCodec;
import android.media.MediaFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.storage.StorageManager;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.fourfaith.VideoAPI;
import com.fourfaith.common.BaseCommonConstant;
import com.fourfaith.common.BaseCommonFunc;
import com.fourfaith.netty.NettyPressureClient;
import com.fourfaith.netty.client.NettySocketMgr;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.base.view.dialog.loading.SimpleLoadDialog;
import com.tepia.main.R;

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
    int port = 0;
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

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            SharedPreferences sharedPreferences = getSharedPreferences(BaseCommonConstant.sConfigIPport, Context.MODE_PRIVATE);
            String serverIP = sharedPreferences.getString("weburl", "");
            String serverPort = sharedPreferences.getString("serverport", "");
            //String webIP = sharedPreferences.getString("weburl","");
            //String webPort = sharedPreferences.getString("webport","");
            ipAddr = serverIP;
            port = 10000;

            deviceId = extras.getString("deviceId");
            //surl = extras.getString("surl");
            sRemark = extras.getString("sRemark");
            iChannelAmt = extras.getInt("iChannelAmt");

            deviceName.setText(sRemark);
            setCenterTitle(sRemark);
            showBack();

            if (iChannelAmt == 8) {
                System.out.println("[VideoSixinActivity.onCreate]iChannelAmt == 8  " + iChannelAmt);
            } else {
            }
            VideoAPI oVideoActivity = new VideoAPI();
            //VideoAPI.startConnect(ipAddr, port);
            iViewChannel = 0;
            //new Client_5205_RunnableTask(deviceId, iViewChannel).start();

            System.out.println("[VideoSixinActivity.onCreate]ipAddr  " + ipAddr);
            System.out.println("[VideoSixinActivity.onCreate]port  " + port);
            System.out.println("[VideoSixinActivity.onCreate]deviceId  " + deviceId);
            System.out.println("[VideoSixinActivity.onCreate]sRemark  " + sRemark);
            System.out.println("[VideoSixinActivity.onCreate]iChannelAmt  " + iChannelAmt);


            oVideoActivity.initCreate(ipAddr, port, deviceId, iViewChannel);

            if (readViewThread == null) {
                System.out.println("[VideoSixinActivity.onCreate]--------------------------readViewThread == null ");

                readViewThread = new ReadViewThread();
                readFlag = true;
                readViewThread.start();
            }

        }
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

    /*@Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        System.out.println("[onCreate]-------------------------------------------------------------[onCreate]");
        setContentView(R.layout.activity_video);



//        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }*/

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

        mSurfaceView.setVisibility(View.GONE);
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
                System.out.println("[checkedChangeListener]----------------------------------[checkedChangeListener]iViewChannel:  " + iViewChannel);

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
        System.out.println("[onPause]-------------------------------------------------[onPause]");
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
                System.out.println("[onPause]-------------------------------------------------[onPause] mSurfaceView = null ");
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
                System.out.println("[onPause]   readViewThread.stop()  ");
            }
        } catch (Exception e) {
            System.out.println("[onPause]exception:   " + e.getMessage());
            if (oNettyPressureClient != null) {
                oNettyPressureClient.stopConnect();
            }

            if (readViewThread != null) {
                readFlag = false;
//                readViewThread.interrupt();
//                readViewThread.stop();
                readViewThread = null;
                System.out.println("[onPause]   readViewThread.stop()  ");
            }
            //e.printStackTrace();
        }
    }

    private void resumeVedio() {
        System.out.println("[onResume]-----------------------------------------------------[onResume]iViewChannel:  " + iViewChannel + " now time string:  "
                + BaseCommonFunc.getNowTimeString());


        if (mSurfaceView == null) {
            System.out.println("[onResume]---------------------------------- mSurfaceView == null ");
            initViews();

        }
        if (readViewThread == null) {
            System.out.println("[onResume]--------------------------readViewThread == null ");

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
        System.out.println("[onDestroy]-------------------------------------------------[onDestroy]");
        NettySocketMgr.getInstance().setInitVideo(false);//isInit = false;
        try {
            if (mSurfaceView != null) {
                mSurfaceView = null;
                System.out.println("[onDestroy]-------------------------------------------------[onDestroy] mSurfaceView = null ");
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
                System.out.println("[onDestroy]   readViewThread.stop()  ");
            }

        } catch (Exception e) {
            System.out.println("[onDestroy]exception:   " + e.getMessage());
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        System.out.println("[onConfigurationChanged]------------------------------------------[onConfigurationChanged]");
        oRadioGroup.setVisibility(View.GONE);
        oRadioGroup.setVisibility(View.VISIBLE);
    }

    public void release() {
        try {
            if (mCodec != null) {
                mCodec = null;
            }
        } catch (Exception e) {
            System.out.println("[release]exception " + e.getMessage());
        }

    }

    Handler timeHandler = new Handler();
    Runnable timeRunnable = new Runnable() {

        @Override
        public void run() {
            // handler自带方法实现定时器
            try {
                int iNumViewBytes = NettySocketMgr.getInstance().getNumViewBytes();
                System.out.println("[timeRunnable]iNumViewBytes=  " + iNumViewBytes);
                if (iNumViewBytes == 2) {


                    if (readViewThread != null) {
                        System.out.println("[timeRunnable]VideoSixinActivity.readViewThread.isAlive()=  " + VideoSixinActivity.readViewThread.isAlive());
                        if (!readViewThread.isAlive()) {
                            readFlag = true;
                            readViewThread.start();
                        }
                    } else {
                        System.out.println("[timeRunnable]  VideoSixinActivity.readViewThread == null  ");
                        readViewThread = new ReadViewThread();
                        readFlag = true;
                        readViewThread.start();
                    }
                }

                if (NettySocketMgr.getInstance().getNumNoGenConnection() >= 5) {
                    if (dialog != null) {
                        System.out.println("[timeRunnable]--------------------dialog != null  getNumNoGenConnection() >= 5");
                        dialog.dismiss();
                    }
                    if (myDialog != null) {
                        System.out.println("[timeRunnable]------------------------------myDialog != null  getNumNoGenConnection() >= 5");
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
                    System.out.println("[timeRunnable]-------------------------------------------------timeHandler.removeCallbacks(timeRunnable)");
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

    final Handler handlerStop = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    timeHandler.removeCallbacks(timeRunnable);
                    break;
            }
            super.handleMessage(msg);
        }
    };

    public void initDecoder() {

        try {

            NettySocketMgr.getInstance().setNumConnection();

            if (mSurfaceView == null) {
                System.out.println("[initDecoder]----------------------------------mSurfaceView == null ");
                mSurfaceView = (SurfaceView) findViewById(R.id.SurfaceView);

                mSurfaceView.setVisibility(View.GONE);
                mSurfaceView.setVisibility(View.VISIBLE);

            } else {
                if (mCodec == null) {
                    System.out.println("[initDecoder]----------------------------------mCodec == null ");

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
                            System.out.println("[initDecoder]----------------------------------invalidSurface == true ");
                            //return;
                        } else {
                            System.out.println("[initDecoder]----------------------------------invalidSurface != true ");

                            mCodec.configure(mediaFormat, mSurfaceView.getHolder().getSurface(), null, 0);
                            mCodec.start();
                        }
                    }

                }
            }

        } catch (Exception e) {
            System.out.println("[initDecoder]exception:   " + e.getMessage());
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
                            //System.out.println("[onFrame]----------------------------------invalidSurface == true ");
                            //return;
                        } else {
                            //System.out.println("[onFrame]----------------------------------invalidSurface != true ");
                            ByteBuffer[] inputBuffers = mCodec.getInputBuffers();

                            int inputBufferIndex = mCodec.dequeueInputBuffer(10000);
                            //System.out.println("[onFrame]    inputBuffers.length:  " + inputBuffers.length  + "  inputBufferIndex:  "  + inputBufferIndex  );
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
            System.out.println("[onFrame].exception " + e.getMessage());
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
        //System.out.println("[checkConnectState]NettySocketMgr.getInstance().getNumNoGenViewBytes():  " + iNumNoGenViewBytes );
        if (iNumNoGenViewBytes >= 2) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    /*public Action getIndexApiAction()
    {
        Thing object = new Thing.Builder()
                .setName("Video Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }*/
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
                    if (1 == initbuffflag) {
                        frameOffset = 0;
                        framebuffer = new byte[600000];
                        initbuffflag = 0;
                    }
                    byte[] bys = NettySocketMgr.getInstance().removeViewBytesFile();
                    if (bys != null) {
                        System.out.println("[ReadViewThread]bys != null  ");
                        NettySocketMgr.getInstance().setNullViewNum();

                        int iNumNoGenViewBytes = NettySocketMgr.getInstance().getNumNoGenViewBytes();
                        System.out.println("[ReadViewThread]NettySocketMgr.getInstance().getNumNoGenViewBytes():  " + iNumNoGenViewBytes);
                        if (iNumNoGenViewBytes >= 10)//&& !isInit )
                        {
                            //System.out.println("[readViewBytes]NettySocketMgr.getInstance().getNumNoGenViewBytes():  " + iNumNoGenViewBytes );
                            initDecoder();
                        }
                        if (iNumNoGenViewBytes >= 55) {
                            if (dialog != null) {
                                dialog.dismiss();
                            }
                        }


                        bais = new ByteArrayInputStream(bys);

                        int length = bais.available();
                        System.out.println("[readViewBytes]bais.available():  " + length);
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
                                    //System.out.format("wangwr debug head %b %x %x %x %x %x %x %d \r\n"  ,flag,framebuffer[0],framebuffer[1],framebuffer[2],framebuffer[3],framebuffer[4],framebuffer[5],offset);
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
                        System.out.println("[ReadViewThread]bys==null ");
                    }
                } catch (Exception e) {
                    System.out.println("[ReadViewThread]Exception  " + e.getMessage());
                    //e.printStackTrace();
                    readFlag = false;
                }

                try {
                    Thread.sleep(TIME_INTERNAL);
                } catch (InterruptedException e) {
                    System.out.println("[ReadViewThread]InterruptedException " + e.getMessage());
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
        if (language.endsWith("zh"))
            return true;
        else
            return false;
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
