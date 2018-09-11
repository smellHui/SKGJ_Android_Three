package com.tepia.main.view.main.detail.vedio;

import android.annotation.SuppressLint;
import android.graphics.PixelFormat;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

import com.hikvision.netsdk.HCNetSDK;
import com.hikvision.netsdk.NET_DVR_PREVIEWINFO;
import com.hikvision.netsdk.RealPlayCallBack;

import org.MediaPlayer.PlayM4.Player;

/**
 * @主要功能：视频播放
 * @项目名: XtzWisdom
 * @包名: com.tepia.xtzwisdom.config
 * @创建者: yanlenovo
 * @创建时间:2017年05月29日 20:37
 * @更新人: yanlenovo
 * @更新时间: 2017年05月29日 20:37
 * @Copyright: 个人版权所有
 * @version: 1.0.0
 */
@SuppressLint("NewApi")
public class PlaySurfaceView extends SurfaceView implements Callback {

    private final String TAG = PlaySurfaceView.class.getSimpleName();
    private int m_iWidth = 0;
    private int m_iHeight = 0;
    public int m_iPreviewHandle = -1;
    private int m_iPort = -1;
    private boolean m_bSurfaceCreated = false;

    public PlaySurfaceView(VideoPlayActivity activity) {
        super(activity);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
        System.out.println("surfaceChanged");
    }
    public void setParams(int nScreenSize, int Height) {
        m_iWidth = nScreenSize;
        m_iHeight = Height;
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        m_bSurfaceCreated = true;
        setZOrderOnTop(true);
        getHolder().setFormat(PixelFormat.TRANSLUCENT);
        if (m_iPort == -1) {
            return;
        }
        Surface surface = holder.getSurface();
        if (true == surface.isValid()) {
            if (false == Player.getInstance().setVideoWindow(m_iPort, 0, holder)) {
            }
        }
    }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        m_bSurfaceCreated = false;
        if (m_iPort == -1) {
            return;
        }
        if (true == holder.getSurface().isValid()) {
            if (Player.getInstance().setVideoWindow(m_iPort, 0, null) == false) {
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.setMeasuredDimension(m_iWidth, m_iHeight);
    }

    public void setParam(int nScreenSize, int Height) {
        m_iWidth = nScreenSize;
        m_iHeight = (int) ((Height )*0.35);

    }
    public int getCurWidth() {
        return m_iWidth;
    }

    public int getCurHeight() {
        return m_iHeight;
    }

    /**
     * 开始播放
     * @param iUserID
     * @param iChan
     */
    public void startPreview( int position,int iUserID, int iChan) {
        RealPlayCallBack fRealDataCallBack = getRealPlayerCbf();
        if (fRealDataCallBack == null) {
            return;
        }
        NET_DVR_PREVIEWINFO previewInfo = new NET_DVR_PREVIEWINFO();
        previewInfo.lChannel = iChan;
        //  选择码流
        if(position==1) {
            previewInfo.dwStreamType = 0;
        }else if (position==2) {
            previewInfo.dwStreamType = 3;
        }
        previewInfo.bBlocked = 1;
        m_iPreviewHandle = HCNetSDK.getInstance().NET_DVR_RealPlay_V40(iUserID, previewInfo, fRealDataCallBack);
    }

    /**
     *
     * 停在播放
     */
    public void stopPreview() {
        HCNetSDK.getInstance().NET_DVR_StopRealPlay(m_iPreviewHandle);
        stopPlayer();
    }

    private void stopPlayer() {
        Player.getInstance().stopSound();
        if (!Player.getInstance().stop(m_iPort)) {
            return;
        }
        if (!Player.getInstance().closeStream(m_iPort)) {
            return;
        }
        if (!Player.getInstance().freePort(m_iPort)) {
            return;
        }
        m_iPort = -1;
    }

    /**
     *
     * @return
     */
    private RealPlayCallBack getRealPlayerCbf() {
        RealPlayCallBack cbf = new RealPlayCallBack() {
            @Override
            public void fRealDataCallBack(int iRealHandle, int iDataType, byte[] pDataBuffer, int iDataSize) {
                processRealData(1, iDataType, pDataBuffer, iDataSize, Player.STREAM_REALTIME);
            }
        };
        return cbf;
    }

    /**
     *
     * @param iPlayViewNo
     * @param iDataType
     * @param pDataBuffer
     * @param iDataSize
     * @param iStreamMode
     */
    private void processRealData(int iPlayViewNo, int iDataType, byte[] pDataBuffer, int iDataSize, int iStreamMode) {
        if (HCNetSDK.NET_DVR_SYSHEAD == iDataType) {
            if (m_iPort >= 0) {
                return;
            }
            m_iPort = Player.getInstance().getPort();
            if (m_iPort == -1) {
                return;
            }
            if (iDataSize > 0) {
                if (!Player.getInstance().setStreamOpenMode(m_iPort, iStreamMode)) {
                    return;
                }
                if (!Player.getInstance().openStream(m_iPort, pDataBuffer, iDataSize, 2 * 1024 * 1024)) {
                    return;
                }
                while (!m_bSurfaceCreated) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if (!Player.getInstance().play(m_iPort, getHolder())) {
                    return;
                }
                if (!Player.getInstance().playSound(m_iPort)) {
                    return;
                }
            }
        } else {
            if (!Player.getInstance().inputData(m_iPort, pDataBuffer, iDataSize)) {
            }
        }
    }
}
