package com.tepia.main.view.maincommon.reservoirs.detail;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fourfaith.common.BaseCommonConstant;
import com.fourfaith.common.BaseCommonFunc;
import com.google.gson.Gson;
import com.hikvision.netsdk.HCNetSDK;
import com.hikvision.netsdk.NET_DVR_DEVICEINFO_V30;
import com.hikvision.netsdk.NET_DVR_IPPARACFG_V40;
import com.hikvision.netsdk.NET_DVR_PICCFG_V30;
import com.hikvision.netsdk.NET_DVR_WORKSTATE_V30;
import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.base.utils.DoubleClickUtil;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.NetUtil;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.utils.Utils;
import com.tepia.base.view.dialog.loading.SimpleLoadDialog;
import com.tepia.main.R;
import com.tepia.main.model.map.VideoResponse;
import com.tepia.main.utils.EmptyLayoutUtil;
import com.tepia.main.view.main.detail.vedio.Constant;
import com.tepia.main.view.main.detail.vedio.VideoInfo;
import com.tepia.main.view.maincommon.reservoirs.ReservoirsFragment;
import com.tepia.main.view.maincommon.reservoirs.detailadapter.VideoListAdapter;
import com.tepia.main.view.maincommon.reservoirs.dvrapp.VideoSixinActivity;
import com.tepia.main.view.maincommon.reservoirs.dvrapp.po.HttpPacketReq;
import com.tepia.main.view.maincommon.reservoirs.dvrapp.po.ObjectDevices;
import com.tepia.main.view.maincommon.reservoirs.dvrapp.po.ObjectPayload;
import com.tepia.main.view.maincommon.reservoirs.dvrapp.po.ObjectUser;
import com.tepia.main.view.maincommon.reservoirs.mvpreservoir.ReserviorContract;
import com.tepia.main.view.maincommon.reservoirs.mvpreservoir.ReserviorPresent;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

                VideoResponse.DataBean dataBean = dataBeanList.get(position);

                if ("0".equals(accessType)) {
                    channelId = dataBean.getChannelId();
                    int channelReally = Integer.valueOf(channelId) - 1;
                    LogUtil.e(TAG, "channeId为" + channelId);
                    //海康视频
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
                } else if("1".equals(accessType)){
                    //四信视频
                    /*if (lstDevice.size() == 0) {
                        Toast.makeText(VedioOfReservoirActivity.this, getResources().getString(R.string.not_device_online), Toast.LENGTH_LONG).show();
                        return;
                    }*/

                    String defaultDvrId = dataBean.getDefaultDvrId();
                    /*for (int i = 0; i < lstDevice.size(); i++) {
                        ObjectDevices objectDevices = lstDevice.get(i);
                        if(defaultDvrId.equals(objectDevices.deviceId)){
                            Intent intent = new Intent(VedioOfReservoirActivity.this, VideoSixinActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("deviceId", objectDevices.deviceId);
                            bundle.putString("sRemark", objectDevices.deviceName);
                            bundle.putInt("iChannelAmt", objectDevices.channelAmt);
                            intent.putExtras(bundle);
                            startActivity(intent);

                            try {
                                thDeviceListThread.interrupt();
                                thDeviceListThread = null;
                            } catch (Exception e) {
                            }
                            break;
                        }
                    }*/
                    Intent intent = new Intent(VedioOfReservoirActivity.this, VideoSixinActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("deviceId", defaultDvrId);
                    bundle.putString("sRemark", dataBean.getVsnm());
                    bundle.putInt("iChannelAmt", Integer.valueOf(dataBean.getChannelId()));
                    intent.putExtras(bundle);
                    startActivity(intent);

                    try {
                        if(thDeviceListThread != null) {
                            thDeviceListThread.interrupt();
                            thDeviceListThread = null;
                        }
                    } catch (Exception e) {
                    }



                }else {
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
        if (dataBeanList.size() > 0) {
            VideoResponse.DataBean dataBean = dataBeanList.get(0);
            if (dataBean != null) {
                Ip = dataBean.getIp();
                User = dataBean.getAccount();
                Psd = dataBean.getPwd();
                accessType = dataBean.getAccessType();
            }else {
                videoListAdapter.setEmptyView(EmptyLayoutUtil.show("返回视频数据为空"));
                return;
            }
            LogUtil.e("视频类型accessType"+accessType);
            if ("0".equals(accessType)) {
                //海康视频
                initLogin(dataBean);
            } else if ("1".equals(accessType)) {
                //四信视频
                SharedPreferences sharedPreferences = getSharedPreferences(BaseCommonConstant.sConfigIPport, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("weburl", dataBean.getIp());
                editor.putString("serverurl", dataBean.getIp());

                editor.putString("webport", dataBean.getPort() + "");
                editor.putString("serverport", dataBean.getAppPort() + "");
                editor.commit();

                LogUtil.e("平台IP(weburl)："+dataBean.getIp()+"\n平台端口号webport："+dataBean.getPort()+"\n服务serverurl："+dataBean.getIp()+"\n服务端口号serverport："+dataBean.getAppPort());

                mQueue = Volley.newRequestQueue(this);
                sixin_account = dataBean.getAccount();
                sixin_pwd = dataBean.getPwd();
                String webPort = sharedPreferences.getString("webport","");
                initPostLogin(dataBeanList.get(0).getIp(), webPort);
            }else{
                ToastUtils.shortToast("暂无指定视频接入");
                return;
            }

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
            LogUtil.e(
                    "NET_DVR_GetDVRWorkState_V30 faild!" + " err: " + HCNetSDK.getInstance().NET_DVR_GetLastError());
        } else {
            LogUtil.e("NET_DVR_GetDVRWorkState_V30 succ!");
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


    /**
     * --------------------------四信视频相关
     */
    protected String sDeviceList = "";
    protected ObjectPayload oObjectPayload = null;
    protected ObjectDevices oObjectDevices = null;
    protected int iDevicesLen = 0;

    public ArrayList<ObjectDevices> lstDevice = new ArrayList<ObjectDevices>();


    private Integer[] mImageDevices = null;


    private Integer[] mImagePlays = null;

    private int userId;
    private RequestQueue mQueue;

    private DeviceListThread thDeviceListThread;


    /**
     * 登录四信服务器
     *
     * @param webIP
     * @param webPort
     */
    private void initPostLogin(String webIP, String webPort) {//Post请求
        if (simpleLoadDialog == null) {
            simpleLoadDialog = new SimpleLoadDialog(this, "正在登录视频服务器...", true);
        }
        simpleLoadDialog.show();
        String url = "http://" + webIP + ":" + webPort + "/dvr-gateway/api/genl/user/login";
        //这写你自己的内部类PostResquest。。。。Method改成POST
        mQueue.add(new PostResquestLogin(com.android.volley.Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String arg0) {

                //成功，在这里写处理内容的代码
                ToastUtils.shortToast("登录成功");
                if(simpleLoadDialog != null){
                    simpleLoadDialog.dismiss();
                }
                LogUtil.e("arg0:" + arg0);
                Gson gson = new Gson();
                HttpPacketReq oHttpPacketReq = null;
                oHttpPacketReq = gson.fromJson(arg0, HttpPacketReq.class);

                if (oHttpPacketReq.msg != null && oHttpPacketReq.success.equals("true")) {
                    String sUserInfo = gson.toJson(oHttpPacketReq.msg);
                    ObjectUser oObjectUser = gson.fromJson(sUserInfo, ObjectUser.class);
                    LogUtil.e("oObjectUser.userId  " + oObjectUser.userId);
                    LogUtil.e("oObjectUser.account  " + oObjectUser.account);
                    LogUtil.e("oObjectUser.userName  " + oObjectUser.userName);
                    LogUtil.e("oObjectUser.mobile  " + oObjectUser.mobile);
                    LogUtil.e("oObjectUser.expiredDate  " + oObjectUser.expiredDate);
                    LogUtil.e("oObjectUser.previewLimit  " + oObjectUser.previewLimit);
                    LogUtil.e("oObjectUser.description  " + oObjectUser.description);
                    LogUtil.e("oObjectUser.userState  " + oObjectUser.userState);
                    LogUtil.e("oObjectUser.roleName  " + oObjectUser.roleName);


                    SharedPreferences sharedPreferences = getSharedPreferences(BaseCommonConstant.sConfigIPport, Context.MODE_PRIVATE);
                    String webIP = sharedPreferences.getString("weburl", "");
                    String webPort = sharedPreferences.getString("webport", "");

                    /*if (valid(webIP, webPort)) {
                        LogUtil.e("---------------userId:" + userId);
                        userId = oObjectUser.userId;
                        getSixinVedio();
                    } else {
                        Toast.makeText(VedioOfReservoirActivity.this, getResources().getString(R.string.ip_port_config), Toast.LENGTH_SHORT).show();
                        return;
                    }*/


                } else {
                    if (simpleLoadDialog != null) {
                        simpleLoadDialog.dismiss();
                    }
                    String sMsg = oHttpPacketReq.msg.toString();
                    switch (sMsg) {
                        case "1": {
                            Toast.makeText(VedioOfReservoirActivity.this, getResources().getString(R.string.account_null), Toast.LENGTH_SHORT).show();
                            break;
                        }
                        case "2": {
                            Toast.makeText(VedioOfReservoirActivity.this, getResources().getString(R.string.password_null), Toast.LENGTH_SHORT).show();
                            break;
                        }
                        case "3": {
                            Toast.makeText(VedioOfReservoirActivity.this, getResources().getString(R.string.account_not_exist), Toast.LENGTH_SHORT).show();
                            break;
                        }
                        case "4": {
                            Toast.makeText(VedioOfReservoirActivity.this, getResources().getString(R.string.user_password_error), Toast.LENGTH_SHORT).show();
                            break;
                        }
                        case "5": {
                            Toast.makeText(VedioOfReservoirActivity.this, getResources().getString(R.string.account_overdue), Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }
                    return;
                }

            }
        },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError arg0) {
                        simpleLoadDialog.dismiss();
                        Toast.makeText(VedioOfReservoirActivity.this, "登录失败，请检查返回的端口号", Toast.LENGTH_LONG).show();
                        LogUtil.e("四信登录post请求失败:" + arg0.getMessage());
                        //失败
                    }
                }));
    }

    private String sixin_account;
    private String sixin_pwd;

    /**
     * 四信登录post
     */
    //写个内部类，Post里面放一些服务器需要的参数
    class PostResquestLogin extends com.android.volley.toolbox.StringRequest {

        public PostResquestLogin(int method, String url,
                                 com.android.volley.Response.Listener<String> listener, com.android.volley.Response.ErrorListener errorListener) {
            super(method, url, listener, errorListener);
        }


        @Override
        protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
            Map<String, String> params = new HashMap<String, String>();
            params.put("account", sixin_account);//参数
            params.put("password", sixin_pwd);//参数
            return params;
        }
    }

    private boolean valid(String serverurl, String serverport) {

        if (!BaseCommonFunc.isNotEmpty(serverurl)) {
            Toast.makeText(VedioOfReservoirActivity.this, getResources().getString(R.string.ip_port_config), Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!BaseCommonFunc.isNotEmpty(serverport)) {
            Toast.makeText(VedioOfReservoirActivity.this, getResources().getString(R.string.ip_port_config), Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }




    /**
     * 线程获取四信视频列表
     */
    private void getSixinVedio() {


        /*Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userId = extras.getInt("userId");
            LogUtil.e("----userId: " + userId);
        }*/
        //initPost();
        if (thDeviceListThread == null) {
            thDeviceListThread = new DeviceListThread();
        }
        thDeviceListThread.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences sharedPreferences = getSharedPreferences(BaseCommonConstant.sConfigIPport, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
        if (thDeviceListThread != null) {
            try {
                thDeviceListThread.interrupt();
                thDeviceListThread = null;
            } catch (Exception e) {
            }
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            if (thDeviceListThread != null) {
                thDeviceListThread.interrupt();
                thDeviceListThread = null;
            }
        } catch (Exception e) {
        }
        LogUtil.e("[VedioOfReservoirActivity.onPause]-------------------------------------------------[VedioOfReservoirActivity.onPause]");
    }



    /**
     * 四信视频列表请求
     */
    private void initPost() {
        //Post请求

        SharedPreferences sharedPreferences = getSharedPreferences(BaseCommonConstant.sConfigIPport, Context.MODE_PRIVATE);
        String webIP = sharedPreferences.getString("weburl", "");
        String webPort = sharedPreferences.getString("webport", "");

        String url = "http://" + webIP + ":" + webPort + "/dvr-gateway/api/genl/org/findOnlineDevice";

        //这写你自己的内部类PostResquest。。。。Method改成POST
        mQueue.add(new PostResquest(com.android.volley.Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String arg0) {
                if (simpleLoadDialog != null) {
                    simpleLoadDialog.dismiss();
                }
                Toast.makeText(VedioOfReservoirActivity.this, "获取视频列表请求成功", Toast.LENGTH_SHORT).show();
                //成功，在这里写处理内容的代码
                LogUtil.e("Post请求获取视频列表成功" + arg0);

                Gson gson = new Gson();
                HttpPacketReq oHttpPacketReq = null;
                oHttpPacketReq = gson.fromJson(arg0, HttpPacketReq.class);

                if (oHttpPacketReq.msg != null && oHttpPacketReq.success.equals("true")) {
                    sDeviceList = gson.toJson(oHttpPacketReq.msg);
                    LogUtil.e("[VedioOfReservoirActivity]sDeviceList:  " + sDeviceList);
                    int iLeft = sDeviceList.indexOf("[");
                    int iRight = sDeviceList.indexOf("]");
                    sDeviceList = sDeviceList.substring(iLeft + 1, iRight);
                    LogUtil.e("[VedioOfReservoirActivity]sDeviceList:  " + sDeviceList);

                    lstDevice.clear();
                    if (sDeviceList != null && sDeviceList.trim().length() > 0) {
                        int iMidle = sDeviceList.indexOf("},");
                        if (iMidle == -1) {
                            oObjectDevices = gson.fromJson(sDeviceList, ObjectDevices.class);

                            lstDevice.add(oObjectDevices);
                        } else {
                            String[] splitDeviceList = sDeviceList.split("\\},");
                            int iSplitLen = splitDeviceList.length;
                            if (iSplitLen >= 2) {
                                for (int i = 0; i < iSplitLen; i++) {
                                    LogUtil.e("splitDeviceList[i]  " + splitDeviceList[i]);
                                    if (i == (iSplitLen - 1)) {
                                        oObjectDevices = gson.fromJson(splitDeviceList[i], ObjectDevices.class);

                                        lstDevice.add(oObjectDevices);
                                    } else {
                                        oObjectDevices = gson.fromJson(splitDeviceList[i] + "}", ObjectDevices.class);

                                        lstDevice.add(oObjectDevices);
                                    }
                                }
                            }

                        }
                    }


                } else {
                    if (lstDevice.size() == 0) {
                        Toast.makeText(VedioOfReservoirActivity.this, getResources().getString(R.string.not_device_online), Toast.LENGTH_LONG).show();
                        return;
                    }
                }
                if (lstDevice.size() == 0) {
                    Toast.makeText(VedioOfReservoirActivity.this, getResources().getString(R.string.not_device_online), Toast.LENGTH_LONG).show();
                    return;
                }
                //initDeviceList();
            }
        },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError arg0) {
                        Toast.makeText(VedioOfReservoirActivity.this, "获取视频列表失败", Toast.LENGTH_LONG).show();
                        LogUtil.e("Post请求失败" + arg0);
                        //失败
                        //initDeviceList();
                    }
                }));
    }

    /**
     * 初始化设备列表
     */
    /*public void initDeviceList() {

        if (lstDevice.size() == 0) {
            Toast.makeText(VedioOfReservoirActivity.this, getResources().getString(R.string.not_device_online), Toast.LENGTH_LONG).show();
            return;
        }

        mImageDevices = new Integer[lstDevice.size()];
        mImagePlays = new Integer[lstDevice.size()];

        for (int i = 0; i < lstDevice.size(); i++) {
            mImageDevices[i] = R.mipmap.device_online;
            mImagePlays[i] = R.mipmap.buplay;//.budevice;
        }

        // 生成动态数组，并且转入数据
        ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < lstDevice.size(); i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            // 添加图像资源的ID
            map.put("ItemImage", mImageDevices[i]);
            // 按序号做ItemText
            map.put("ItemText", lstDevice.get(i).deviceName);
            map.put("sOnLine", lstDevice.get(i).monTraLimit);
            map.put("mImagePlays", mImagePlays[i]);
            lstImageItem.add(map);
        }

        // 生成适配器的ImageItem <====> 动态数组的元素，两者一一对应
        SimpleAdapter saImageItems = new SimpleAdapter(this, // 没什么解释
                lstImageItem,// 数据来源
                R.layout.item_main,// night_item的XML实现
                // 动态数组与ImageItem对应的子项
                new String[]{"ItemImage", "ItemText", "sOnLine", "mImagePlays"},
                // ImageItem的XML文件里面的一个ImageView,两个TextView ID
                new int[]{R.id.ItemImage, R.id.ItemText, R.id.OnLineText, R.id.ImagePlay});
        // 添加并且显示
        gridview.setAdapter(saImageItems);

        // 添加消息处理
        gridview.setOnItemClickListener(new ItemClickListener());

    }*/

    // 当AdapterView被单击(触摸屏或者键盘)，则返回的Item单击事件
    class ItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            if (lstDevice.size() == 0) {
                Toast.makeText(VedioOfReservoirActivity.this, getResources().getString(R.string.not_device_online), Toast.LENGTH_LONG).show();
                return;
            }
            String deviceId = lstDevice.get(position).deviceId;

            Intent intent = new Intent(VedioOfReservoirActivity.this, VideoSixinActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("deviceId", deviceId);
            bundle.putString("sRemark", lstDevice.get(position).deviceName);
            bundle.putInt("iChannelAmt", lstDevice.get(position).channelAmt);
            intent.putExtras(bundle);
            startActivity(intent);

            try {
                thDeviceListThread.interrupt();
                thDeviceListThread = null;
            } catch (Exception e) {
            }
        }
    }

    //写个内部类，Post里面放一些服务器需要的参数
    class PostResquest extends com.android.volley.toolbox.StringRequest {

        public PostResquest(int method, String url,
                            com.android.volley.Response.Listener<String> listener, com.android.volley.Response.ErrorListener errorListener) {
            super(method, url, listener, errorListener);
        }

        @Override
        protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
            Map<String, String> params = new HashMap<String, String>();
            params.put("userId", userId + "");//参数
            //params.put("Age",22+"");//参数
            return params;
        }
    }

    class DeviceListThread extends Thread {
        boolean isEixt = false;

        @Override
        public void run() {
            while (!isEixt) {
                initPost();
                try {
                    Thread.sleep(30 * 1000);
                } catch (InterruptedException e) {
                    isEixt = true;
                }
            }
        }
    }
}
