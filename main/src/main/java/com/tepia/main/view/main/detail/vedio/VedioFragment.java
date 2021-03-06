package com.tepia.main.view.main.detail.vedio;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.hikvision.netsdk.HCNetSDK;
import com.hikvision.netsdk.NET_DVR_DEVICEINFO_V30;
import com.hikvision.netsdk.NET_DVR_IPPARACFG_V40;
import com.hikvision.netsdk.NET_DVR_PICCFG_V30;
import com.hikvision.netsdk.NET_DVR_WORKSTATE_V30;
import com.tepia.base.mvp.BaseCommonFragment;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.view.dialog.loading.SimpleLoadDialog;
import com.tepia.main.R;
import com.tepia.main.model.map.VideoResponse;
import com.tepia.main.utils.EmptyLayoutUtil;
import com.tepia.main.view.maincommon.reservoirs.detail.VideoPlayThreeActivity;
import com.tepia.main.view.maincommon.reservoirs.dvrapp.VideoSixinActivity;


import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * copy from鸭溪水务app
 * 视频详情
 *
 * @author ly
 * @date 2018/8/1
 */
public class VedioFragment extends BaseCommonFragment {

    private static String typekey_detail = "VedioFragment";


    private RecyclerView video_list_view;
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
    /**
     * 0表示海康
     * 1表示四信
     * */
    private String accessType;
    /**
     * 四信相关信息
     */
    private String vedioname;
    private String deviceId;

    public static String currentChannelId;
    private String sRemark;
    private String serverurl;
    private String serverport = "10000";


    private NET_DVR_DEVICEINFO_V30 m_oNetDvrDeviceInfoV30 = null;
    private int iLogID;
    private VideoInfo videoshuiwus;
    private String result;


    private SimpleLoadDialog simpleLoadDialog;

    public VedioFragment() {

    }

    /**
     * @param dataBean 视频站点实体
     * @return
     */
    public static VedioFragment newInstance(VideoResponse.DataBean dataBean) {
        VedioFragment f = new VedioFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(typekey_detail, dataBean);
        f.setArguments(bundle);
        return f;

    }

    @Override
    protected void initView(View mview) {
        if (getArguments() != null && getArguments().containsKey(typekey_detail)) {
            VideoResponse.DataBean dataBean = (VideoResponse.DataBean) getArguments().getSerializable(typekey_detail);
            Ip = dataBean.getIp();
            User = dataBean.getAccount();
            Psd = dataBean.getPwd();
            accessType = dataBean.getAccessType();
            vedioname = dataBean.getVsnm();
            deviceId = dataBean.getDefaultDvrId();
            currentChannelId = dataBean.getChannelId();
            serverurl = dataBean.getIp();
//            serverport = String.valueOf(dataBean.getPort());
        }
//        Ip = "218.201.210.189";
//        User = "admin";
//        Psd = "tepia@com";

        if (!initSdk()) {
            return;
        }
        video_list_view = findView(R.id.video_list_view);
        video_list_view.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new SurvelVideoAdapter(getBaseActivity(), R.layout.adapter_video_items, data_video);
        video_list_view.setAdapter(adapter);
        adapter.setOnItemClickListener((adapter, view, position) -> {
            if( "0".equals(accessType)) {
                /*Intent intent = new Intent();
                intent.setClass(getBaseActivity(), VideoPlayActivity.class);
                intent.putExtra("m_iLogID", m_iLogID);
                intent.putExtra("m_iStartChan", m_iStartChan);
                intent.putExtra("m_iChanNum", data_video.get(position).getId());
                intent.putExtra("position", position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("video_list", (Serializable) data_video);
                intent.putExtras(bundle);
                startActivity(intent);*/

                Intent intent = new Intent();
                int channelReally = data_video.get(position).getId();
                intent.setClass(getBaseActivity(), VideoPlayThreeActivity.class);
                intent.putExtra("m_iLogID", m_iLogID);
                intent.putExtra("m_iStartChan", m_iStartChan);
                intent.putExtra("m_iChanNum", channelReally);
                intent.putExtra("position", channelReally);
                Bundle bundle = new Bundle();
                bundle.putSerializable("video_list", (Serializable) data_video);
                intent.putExtras(bundle);
                startActivity(intent);
            }else if("1".equals(accessType)){
              /*  Intent intent = new Intent();
                intent.setClass(getBaseActivity(), VideoOfFourFaithActivity.class);
                Bundle bundle = new Bundle();

                bundle.putString("deviceId", deviceId);
                bundle.putString("sRemark", "");
                bundle.putString("serverurl", serverurl);
                bundle.putString("serverport", serverport);
                bundle.putInt("currentChannelId", Integer.valueOf(currentChannelId));
                intent.putExtras(bundle);
                startActivity(intent);*/

                Intent intent = new Intent(getBaseActivity(), VideoSixinActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("deviceId", deviceId);
                bundle.putString("sRemark", vedioname);
                bundle.putInt("currentChannelId", Integer.valueOf(currentChannelId));
                bundle.putString("serverurl", serverurl);
                intent.putExtras(bundle);
                startActivity(intent);
            }else{
                ToastUtils.longToast("该视频类型正在接入中");

            }
        });
        if("0".equals(accessType)) {
            simpleLoadDialog = new SimpleLoadDialog(getBaseActivity(), getString(R.string.data_loading), true);
            simpleLoadDialog.show();
            new LoadDataTask().execute(Constant.Video.LOAD_SUCCESS);
        }else if("1".equals(accessType)){
            videoshuiwus = new VideoInfo(Integer.valueOf(currentChannelId), vedioname, "ok");
            data_video.add(videoshuiwus);
            adapter.notifyDataSetChanged();
        }else{
            adapter.setEmptyView(EmptyLayoutUtil.show("该视频类型正在接入中"));
        }
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragemnt_detail_video;
    }

    private SurvelVideoAdapter adapter;

    /**
     * @param data
     */
    private void showData(final List<VideoInfo> data) {
        if (data != null) {
            adapter.notifyDataSetChanged();
        }else{
            adapter.setEmptyView(EmptyLayoutUtil.show("该视频类型正在接入中"));
        }

    }


    @Override
    protected void initData() {



    }


    @Override
    protected void initRequestData() {

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
                getBaseActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        simpleLoadDialog.dismiss();
                        showData(null);

                    }
                });
            }
            return -1;
        }
        if(TextUtils.isEmpty(Ip) || TextUtils.isEmpty(User) || TextUtils.isEmpty(Psd)){
            if (simpleLoadDialog != null) {
                getBaseActivity().runOnUiThread(new Runnable() {
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
        LogUtil.e("Ip：" + Ip);
        LogUtil.e("Port：" + Port);
        LogUtil.e("User：" + User);
        LogUtil.e("Psd：" + Psd);
        LogUtil.e("iLogID：" + iLogID);
        int d = HCNetSDK.getInstance().NET_DVR_GetLastError();
        int sdkversion = HCNetSDK.getInstance().NET_DVR_GetSDKVersion();
        LogUtil.e("NET_DVR_GetLastError：" + d);
        LogUtil.e("sdkversion: -------" + String.valueOf(sdkversion));
        if (iLogID < 0) {
            if (simpleLoadDialog != null) {
                getBaseActivity().runOnUiThread(new Runnable() {
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
        /*for (int i = 0; i < 15; i++) {
            NET_DVR_PICCFG_V30 DVR_Piccfg = new NET_DVR_PICCFG_V30();
            Boolean cc = HCNetSDK.getInstance().NET_DVR_GetDVRConfig(iLogID, HCNetSDK.NET_DVR_GET_PICCFG_V30,
                    DVR_Config.dwStartDChan + i, DVR_Piccfg);
            int w = HCNetSDK.getInstance().NET_DVR_GetLastError();
            try {
                if(channel == Integer.valueOf(currentChannelId)) {
                    String s = new String(DVR_Piccfg.sChanName, "GB2312");
                    videoshuiwus = new VideoInfo(Integer.valueOf(currentChannelId), s, "ok");
                    data_video.add(videoshuiwus);
                }
                channel++;



            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                if (simpleLoadDialog != null) {
                    simpleLoadDialog.dismiss();
                }
            }
        }*/
        int channel_id  = Integer.valueOf(currentChannelId);
        NET_DVR_PICCFG_V30 DVR_Piccfg = new NET_DVR_PICCFG_V30();
        Boolean cc = HCNetSDK.getInstance().NET_DVR_GetDVRConfig(iLogID, HCNetSDK.NET_DVR_GET_PICCFG_V30,
                DVR_Config.dwStartDChan + channel_id, DVR_Piccfg);
        int w = HCNetSDK.getInstance().NET_DVR_GetLastError();
        try {
                String s = new String(DVR_Piccfg.sChanName, "GB2312");
                videoshuiwus = new VideoInfo(channel_id, vedioname, "ok");
                data_video.add(videoshuiwus);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            if (simpleLoadDialog != null) {
                simpleLoadDialog.dismiss();
            }
        }
        getBaseActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                showData(data_video);
                if (simpleLoadDialog != null) {
                    simpleLoadDialog.dismiss();
                }
            }

        });

    }
}
