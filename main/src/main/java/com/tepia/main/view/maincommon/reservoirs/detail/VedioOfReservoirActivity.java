package com.tepia.main.view.maincommon.reservoirs.detail;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hikvision.netsdk.HCNetSDK;
import com.hikvision.netsdk.NET_DVR_DEVICEINFO_V30;
import com.hikvision.netsdk.NET_DVR_IPPARACFG_V40;
import com.hikvision.netsdk.NET_DVR_PICCFG_V30;
import com.hikvision.netsdk.NET_DVR_WORKSTATE_V30;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.base.utils.DoubleClickUtil;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.NetUtil;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.utils.Utils;
import com.tepia.base.view.dialog.loading.SimpleLoadDialog;
import com.tepia.main.R;
import com.tepia.main.model.VedioBean;
import com.tepia.main.model.map.VideoResponse;
import com.tepia.main.model.reserviros.CapacityBean;
import com.tepia.main.utils.EmptyLayoutUtil;
import com.tepia.main.view.main.detail.vedio.Constant;
import com.tepia.main.view.main.detail.vedio.SurvelVideoAdapter;
import com.tepia.main.view.main.detail.vedio.VedioFragment;
import com.tepia.main.view.main.detail.vedio.VideoInfo;
import com.tepia.main.view.main.detail.vedio.VideoPlayActivity;
import com.tepia.main.view.maincommon.reservoirs.ReservoirsFragment;
import com.tepia.main.view.maincommon.reservoirs.detailadapter.VideoListAdapter;
import com.tepia.main.view.maincommon.reservoirs.mvpreservoir.ReserviorContract;
import com.tepia.main.view.maincommon.reservoirs.mvpreservoir.ReserviorPresent;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by      Android studio
 *
 * @author :       ly(from Center Of Wuhan)
 * Date            :       2018-9-18
 * Version         :       1.0
 * 功能描述         :       水库视频
 **/
public class VedioOfReservoirActivity extends MVPBaseActivity<ReserviorContract.View, ReserviorPresent> implements ReserviorContract.View<VideoResponse> {

    private final static String TAG = VedioOfReservoirActivity.class.getName();
    private RecyclerView recycleIdRec;
    private VideoListAdapter videoListAdapter;
    private TextView nameTv;
    private String channelId;
    private boolean isSuccess = false;

    @Override
    public int getLayoutId() {
        return R.layout.activity_vedio_of_reservoirs;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCenterTitle("视频列表");
        showBack();
        recycleIdRec = findViewById(R.id.recycleIdRec);
        recycleIdRec.setLayoutManager(new LinearLayoutManager(this));

        String reservoirName = getIntent().getStringExtra(ReservoirsFragment.RESERVOIRNAME);
        String reservoirId = getIntent().getStringExtra(ReservoirsFragment.RESERVOIRId);
        nameTv = findViewById(R.id.nameTv);
        nameTv.setText(reservoirName);

        videoListAdapter = new VideoListAdapter(this, R.layout.adapter_video_items, dataBeanList);
        recycleIdRec.setAdapter(videoListAdapter);
        videoListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (!NetUtil.isNetworkConnected(Utils.getContext())) {
                    ToastUtils.shortToast(R.string.no_network);
                    return;
                }
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
                /*Intent intent = new Intent();
                intent.setClass(VedioOfReservoirActivity.this,VedioListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("vedio",dataBeanList.get(position));
                bundle.putString("vsnm",dataBeanList.get(position).getVsnm());
                bundle.putString("name",nameTv.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);*/


                channelId = dataBeanList.get(position).getChannelId();
                int channelReally = Integer.valueOf(channelId) - 1;
                LogUtil.e(TAG, "channeId为" + channelId);
                if ("0".equals(accessType)) {
                    List<VideoInfo> videoInfos = new ArrayList<>();

                    if (channelReally < data_video.size()) {
                        videoInfos.add(data_video.get(channelReally));
                    } else {
                        ToastUtils.shortToast("暂无指定视频接入");
                        return;
                    }


                    if (videoInfos != null && videoInfos.size() > 0) {
                        Intent intent = new Intent();
                        intent.setClass(VedioOfReservoirActivity.this, VideoPlayThreeActivity.class);
                        intent.putExtra("m_iLogID", m_iLogID);
                        intent.putExtra("m_iStartChan", m_iStartChan);
                        intent.putExtra("m_iChanNum", channelReally);
                        intent.putExtra("position", channelReally);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("video_list", (Serializable) videoInfos);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    } else {
                        ToastUtils.shortToast("暂无指定视频接入");
                        return;
                    }
                } else {
                    // TODO: 2018/8/2 解锁
                    ToastUtils.longToast("视频正在接入中");
                }

            }
        });
        mPresenter.getReservoirVideo(reservoirId);
    }

    @Override
    public void initView() {

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


    private List<VideoResponse.DataBean> dataBeanList = new ArrayList<>();

    @Override
    public void success(VideoResponse videoResponse) {
        dataBeanList.clear();
        dataBeanList.addAll(videoResponse.getData());
        videoListAdapter.notifyDataSetChanged();
        /**
         * 获取列表后先登录
         */
        if (dataBeanList.size() > 0 && "0".equals(dataBeanList.get(0).getAccessType())) {
            initLogin(dataBeanList.get(0));

        } else {
            videoListAdapter.setEmptyView(EmptyLayoutUtil.show("暂无视频"));
        }
    }

    @Override
    public void failure(String msg) {
        videoListAdapter.setEmptyView(EmptyLayoutUtil.show(msg));
    }


    private static String typekey_detail = "VedioFragment";


    private List<VideoInfo> data_video = new ArrayList<>();
    private int m_iLogID = -1;
    private int m_iStartChan = 0;
    private int m_iChanNum = 0;
    /**
     * 视频ip地址
     */
    private String Ip = "218.201.210.189";
    /**
     * 和web端返回端口不一样
     * 所以不要使用web端接口返回的port
     */
    private int Port = 8000;
    /**
     * 登录账户和密码
     */
    private String User = "admin";
    private String Psd = "tepia@com";
    private String accessType;

    private NET_DVR_DEVICEINFO_V30 m_oNetDvrDeviceInfoV30 = null;
    private int iLogID;
    private VideoInfo videoshuiwus;
    private String result;
    private int channel = 0;


    private SimpleLoadDialog simpleLoadDialog;

    protected void initLogin(VideoResponse.DataBean dataBean) {

        if (dataBean != null) {
            Ip = dataBean.getIp();
            User = dataBean.getAccount();
            Psd = dataBean.getPwd();
            accessType = dataBean.getAccessType();
        }

        if (!initSdk()) {
            return;
        }


        if (simpleLoadDialog == null) {
            simpleLoadDialog = new SimpleLoadDialog(this, "正在登录视频服务器...", true);
        }
        simpleLoadDialog.show();
        new LoadDataTask().execute(Constant.Video.LOAD_SUCCESS);

    }


    /**
     * @param data
     */
    private void showData(final List<VideoInfo> data) {
        ToastUtils.shortToast("视频正在接入中");

    }


    /**
     * 初始化sdk
     *
     * @return
     */
    private boolean initSdk() {
        return HCNetSDK.getInstance().NET_DVR_Init();
    }

    /**
     * 异步请求
     * AsyncTask
     */
    private class LoadDataTask extends AsyncTask<Integer, Integer, Integer> {
        public LoadDataTask() {
        }

        @Override
        protected Integer doInBackground(Integer... params) {
            switch (params[0]) {
                case Constant.Video.LOAD_SUCCESS:
                    login();
                    break;
            }
            return Constant.Video.REFREASH;
        }
    }

    /**
     * 登录中心
     */
    private void login() {
        if (m_iLogID < 0) {
            m_iLogID = loginDevice();
        }
    }

    /**
     * 视频中心
     *
     * @return
     */
    private int loginDevice() {
        int iLogID = -1;
        iLogID = loginNormalDevice();
        return iLogID;
    }

    /**
     * 登录
     * 中心设备
     *
     * @return
     */
    private int loginNormalDevice() {
        m_oNetDvrDeviceInfoV30 = new NET_DVR_DEVICEINFO_V30();
        if (null == m_oNetDvrDeviceInfoV30) {
            if (simpleLoadDialog != null) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        simpleLoadDialog.dismiss();
                        showData(null);

                    }
                });
            }
            return -1;
        }
        if (TextUtils.isEmpty(Ip) || TextUtils.isEmpty(User) || TextUtils.isEmpty(Psd)) {
            if (simpleLoadDialog != null) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        simpleLoadDialog.dismiss();
                        showData(null);

                    }
                });
            }
            return -1;
        }
        iLogID = HCNetSDK.getInstance().NET_DVR_Login_V30(Ip, Port, User, Psd, m_oNetDvrDeviceInfoV30);
        LogUtil.e(TAG, "Ip：" + Ip);
        LogUtil.e(TAG, "Port：" + Port);
        LogUtil.e(TAG, "User：" + User);
        LogUtil.e(TAG, "Psd：" + Psd);
        LogUtil.e(TAG, "iLogID：" + iLogID);
        int d = HCNetSDK.getInstance().NET_DVR_GetLastError();
        int sdkversion = HCNetSDK.getInstance().NET_DVR_GetSDKVersion();
        LogUtil.e(TAG, "NET_DVR_GetLastError：" + d);
        LogUtil.e(TAG, "sdkversion: -------" + String.valueOf(sdkversion));
        if (iLogID < 0) {
            if (simpleLoadDialog != null) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        simpleLoadDialog.dismiss();
                        ToastUtils.longToast("视频加载失败");
                    }
                });
            }
            return -1;
        }
        if (m_oNetDvrDeviceInfoV30.byChanNum > 0) {
            m_iStartChan = m_oNetDvrDeviceInfoV30.byStartChan;
            m_iChanNum = m_oNetDvrDeviceInfoV30.byChanNum;
        } else if (m_oNetDvrDeviceInfoV30.byIPChanNum > 0) {
            m_iStartChan = m_oNetDvrDeviceInfoV30.byStartDChan;
            m_iChanNum = m_oNetDvrDeviceInfoV30.byIPChanNum + m_oNetDvrDeviceInfoV30.byHighDChanNum * 256;
        }
        getVideoName();
        return iLogID;
    }

    /**
     * 获取设备参数
     */
    private void getVideoName() {
        NET_DVR_WORKSTATE_V30 struWorkState = new NET_DVR_WORKSTATE_V30();
        if (!HCNetSDK.getInstance().NET_DVR_GetDVRWorkState_V30(iLogID, struWorkState)) {
            System.out.println(
                    "NET_DVR_GetDVRWorkState_V30 faild!" + " err: " + HCNetSDK.getInstance().NET_DVR_GetLastError());
        } else {
            System.out.println("NET_DVR_GetDVRWorkState_V30 succ!");
        }
        NET_DVR_IPPARACFG_V40 DVR_Config = new NET_DVR_IPPARACFG_V40();
        Boolean bb = HCNetSDK.getInstance().NET_DVR_GetDVRConfig(iLogID, HCNetSDK.NET_DVR_GET_IPPARACFG_V40, 0,
                DVR_Config);
        for (int i = 0; i < 15; i++) {
            NET_DVR_PICCFG_V30 DVR_Piccfg = new NET_DVR_PICCFG_V30();
            Boolean cc = HCNetSDK.getInstance().NET_DVR_GetDVRConfig(iLogID, HCNetSDK.NET_DVR_GET_PICCFG_V30,
                    DVR_Config.dwStartDChan + i, DVR_Piccfg);
            int w = HCNetSDK.getInstance().NET_DVR_GetLastError();
            try {
                String s = new String(DVR_Piccfg.sChanName, "GB2312");
                videoshuiwus = new VideoInfo(channel, s, "ok");
                data_video.add(videoshuiwus);
                channel++;

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                if (simpleLoadDialog != null) {
                    simpleLoadDialog.dismiss();
                }
            }
        }

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtils.shortToast("登录成功");
                LogUtil.e(TAG, "获取视频信息成功----------------------------");
                if (simpleLoadDialog != null) {
                    simpleLoadDialog.dismiss();
                }


            }

        });

    }
}
