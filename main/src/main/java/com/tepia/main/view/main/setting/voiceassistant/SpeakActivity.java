package com.tepia.main.view.main.setting.voiceassistant;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.pgyersdk.javabean.AppBean;
import com.pgyersdk.update.PgyUpdateManager;
import com.pgyersdk.update.UpdateManagerListener;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.base.utils.SPUtils;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.utils.Utils;
import com.tepia.base.view.floatview.FloatUtil;
import com.tepia.main.R;
import com.tepia.main.databinding.ActivitySpeakBinding;
import com.tepia.main.view.main.setting.VersionActivity;
import com.tepia.voice.xunfei.OtherCmdBean;
import com.tepia.voice.xunfei.SpreakCompleteLister;
import com.tepia.voice.xunfei.TalkBean;
import com.tepia.voice.xunfei.XuFeiManager;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.tepia.main.view.main.MainActivity.MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE;

/**
 * Created by Joeshould on 2018/7/26.
 */
@Route(path = AppRoutePath.app_speak)
public class SpeakActivity extends BaseActivity {
    private static final int PERMISSION_REQUESTCODE = 1;
    @Autowired(name = "isAudio")
    boolean isAudio = false;
    ActivitySpeakBinding mBinding;
    private AdapterTalkList adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_speak;
    }

    @Override
    public void initView() {
        mBinding = DataBindingUtil.bind(mRootView);
        mBinding.rvTalkList.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        adapter = new AdapterTalkList(getBaseContext());
        mBinding.rvTalkList.setAdapter(adapter);

        List<TalkBean> data = DataSupport.order("id desc").limit(20).find(TalkBean.class);
        adapter.setData(data);
        adapter.notifyDataSetChanged();
        mBinding.rvTalkList.scrollToPosition(mBinding.rvTalkList.getAdapter().getItemCount() - 1);
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (SPUtils.getInstance().getBoolean("ISSHOWFLOATVIEW", false)) {
            FloatUtil.getInstance().hideFloatView(this);
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUESTCODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //用户点击了同意授权
                    initSpeak();
                } else {
                    //用户拒绝了授权
                    Toast.makeText(this, "权限被拒绝", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void initListener() {
        mBinding.ivStartVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XuFeiManager.getInstance().startSpeech(false);
            }
        });

        mBinding.ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    private void initSpeak() {
        if (isAudio) {
            XuFeiManager.getInstance().startSpeech(false);
        }
        List<OtherCmdBean> otherCmdBeanList = new ArrayList<>();
//        otherCmdBeanList.add(new OtherCmdBean(101, new ArrayList<String>(Arrays.asList("工作模块"))));
//        otherCmdBeanList.add(new OtherCmdBean(102, new ArrayList<String>(Arrays.asList("地图模块"))));
//        otherCmdBeanList.add(new OtherCmdBean(103, new ArrayList<String>(Arrays.asList("我的模块"))));
//        otherCmdBeanList.add(new OtherCmdBean(1010, new ArrayList<String>(Arrays.asList("全部", "全部工单", "全部任务"))));
//        otherCmdBeanList.add(new OtherCmdBean(1011, new ArrayList<String>(Arrays.asList("巡检"))));
//        otherCmdBeanList.add(new OtherCmdBean(1012, new ArrayList<String>(Arrays.asList("维护", "维修养护"))));
//        otherCmdBeanList.add(new OtherCmdBean(1013, new ArrayList<String>(Arrays.asList("保洁"))));
//        otherCmdBeanList.add(new OtherCmdBean(1014, new ArrayList<String>(Arrays.asList("事件上报","上报事件","上报"))));
//        otherCmdBeanList.add(new OtherCmdBean(1015, new ArrayList<String>(Arrays.asList("查看日志","打开日志","日志查询"))));
//        otherCmdBeanList.add(new OtherCmdBean(1016, new ArrayList<String>(Arrays.asList("密码","我要修改密码","密码修改"))));
//        otherCmdBeanList.add(new OtherCmdBean(10101, new ArrayList<String>(Arrays.asList("搜索工单","工单搜索","搜索任务","工单搜索"))));
//        otherCmdBeanList.add(new OtherCmdBean(1011,new ArrayList<String>(Arrays.asList("巡检"))));
//        otherCmdBeanList.add(new OtherCmdBean(1011,new ArrayList<String>(Arrays.asList("巡检"))));
//        otherCmdBeanList.add(new OtherCmdBean(1011,new ArrayList<String>(Arrays.asList("巡检"))));
//        otherCmdBeanList.add(new OtherCmdBean(1011,new ArrayList<String>(Arrays.asList("巡检"))));
        XuFeiManager.getInstance().setOtherCmd(otherCmdBeanList);
        XuFeiManager.getInstance().setSpeechAnimCallBack(new XuFeiManager.SpeechAnimCallBack() {
            @Override
            public void onVolumeChanged(int volume) {

            }

            @Override
            public void onError() {

            }

            @Override
            public void onSuccess() {

            }

            @Override
            public void refreshView() {
                List<TalkBean> data = DataSupport.order("id desc").limit(20).find(TalkBean.class);
                adapter.setData(data);
                adapter.notifyDataSetChanged();
                mBinding.rvTalkList.scrollToPosition(mBinding.rvTalkList.getAdapter().getItemCount() - 1);
            }

            @Override
            public void otherCmd(int code, String msg) {
                switch (code) {
                    case 101:
                        ARouter.getInstance().build(AppRoutePath.appMain)
                                .withInt("position", 0)
                                .navigation();
                        break;
                    case 102:
                        ARouter.getInstance().build(AppRoutePath.appMain)
                                .withInt("position", 1)
                                .navigation();
                        break;
                    case 103:
                        ARouter.getInstance().build(AppRoutePath.appMain)
                                .withInt("position", 2)
                                .navigation();
                        break;
                    case 1010:
                        ARouter.getInstance().build(AppRoutePath.app_task_list)
                                .withString("TYPEKEY", "全部")
                                .navigation();
                        break;
                    case 1011:
                        ARouter.getInstance().build(AppRoutePath.app_task_list)
                                .withString("TYPEKEY", "巡检")
                                .navigation();
                        break;
                    case 1012:
                        ARouter.getInstance().build(AppRoutePath.app_task_list)
                                .withString("TYPEKEY", "维护")
                                .navigation();
                        break;
                    case 1013:
                        ARouter.getInstance().build(AppRoutePath.app_task_list)
                                .withString("TYPEKEY", "保洁")
                                .navigation();
                        break;
                    case 1014:
                        ARouter.getInstance().build(AppRoutePath.app_question)
                                .navigation();
                        break;
                    case 1015:
                        ARouter.getInstance().build(AppRoutePath.app_question_list)
                                .navigation();
                        break;
                    case 1016:
                        ARouter.getInstance().build(AppRoutePath.app_password)
                                .navigation();
                        break;
                    case 10101:
                        ARouter.getInstance().build(AppRoutePath.app_task_list)
                                .withString("TYPEKEY", "全部")
                                .withString("SearchKey", msg)
                                .navigation();
                        break;
                    default:
                }
                finish();
            }

            @Override
            public void customCmd(Map<String, Object> resultData) {
                if (resultData == null) {
                    return;
                }
                String intent = (String) resultData.get("intent");
                if (TextUtils.isEmpty(intent)) {
                    return;
                }
                switch (intent) {
                    /**  工单列表 */
                    case "open_wordorder_list":
                        String exeStatus = (String) resultData.get("exeStatus");
                        String workType = (String) resultData.get("workType");
                        if (TextUtils.isEmpty(workType)) {
                            ARouter.getInstance().build(AppRoutePath.app_task_list)
                                    .withString("TYPEKEY", "全部")
                                    .withString("exeStatus",exeStatus)
                                    .navigation();
                        }else {
                            ARouter.getInstance().build(AppRoutePath.app_task_list)
                                    .withString("TYPEKEY", workType)
                                    .withString("exeStatus",exeStatus)
                                    .navigation();
                        }
                        break;
                    case "open_map_module":
                        ARouter.getInstance().build(AppRoutePath.appMain)
                                .withInt("position", 1)
                                .navigation();
                        break;
                    case "map_search":
                        String searchKey = (String) resultData.get("searchKey");
                        ARouter.getInstance().build(AppRoutePath.appMain)
                                .withInt("position", 1)
                                .withString("searchKey", searchKey)
                                .navigation();
                        break;
                    case "open_question_activity":
                        ARouter.getInstance().build(AppRoutePath.app_question)
                                .navigation();
                        break;
                    case "open_question_list":
                        ARouter.getInstance().build(AppRoutePath.app_question_list)
                                .navigation();
                        break;
                    case "open_password_activity":
                        ARouter.getInstance().build(AppRoutePath.app_password)
                                .withInt("position", 1)
                                .navigation();
                        break;
                    case "open_update":
                        updateApp();
                        break;
                    case "open_messtation_list":
                        ARouter.getInstance().build(AppRoutePath.app_messtation_list)
                                .navigation();
                        break;
                    case "open_messtation_detail":
                        break;
                    case "open_reservoir_list":
                        ARouter.getInstance().build(AppRoutePath.app_reservoir_list)
                                .navigation();
                        break;
                    case "open_reservoir_detail":
                        break;
                    case "how_much_messtation":
                    case "how_much_reservoir":
                        break;
                    default:
                        ARouter.getInstance().build(AppRoutePath.appMain)
                                .withInt("position", 0)
                                .navigation();
                        break;
                }
                finish();
            }
        });
    }
    public AppBean appBean;
    private void updateApp() {

        PgyUpdateManager.register(this, new UpdateManagerListener() {

            @Override
            public void onNoUpdateAvailable() {
                ToastUtils.shortToast("已是最新版本");
                XuFeiManager.getInstance().speak("已是最新版本");

            }

            @Override
            public void onUpdateAvailable(String result) {
                // 将新版本信息封装到AppBean中
                appBean = getAppBeanFromString(result);
                new AlertDialog.Builder(SpeakActivity.this)
                        .setTitle("更新")
                        .setMessage(""+appBean.getReleaseNote())
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setPositiveButton(
                                "确定",
                                new DialogInterface.OnClickListener() {


                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //android 6.0动态申请权限
                                        if (ContextCompat.checkSelfPermission(Utils.getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                                != PackageManager.PERMISSION_GRANTED) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                                        MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
                                            }
                                            ActivityCompat.requestPermissions(SpeakActivity.this,
                                                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                                    MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
                                        } else {
                                            XuFeiManager.getInstance().speak("开始为您更新！");
                                            startDownloadTask(SpeakActivity.this, appBean.getDownloadURL());

                                        }
                                    }
                                }).show();

            }
        });
    }

    @Override
    protected void initRequestData() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            //没有授权
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, PERMISSION_REQUESTCODE);
        } else {
            //已经授权
            initSpeak();
        }
    }
}
