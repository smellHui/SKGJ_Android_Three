package com.tepia.main.view.maincommon.setting;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arialyy.annotations.Download;
import com.arialyy.aria.core.Aria;
import com.arialyy.aria.core.download.DownloadTarget;
import com.arialyy.aria.core.download.DownloadTask;
import com.arialyy.aria.util.CommonUtil;
import com.arialyy.frame.permission.OnPermissionCallback;
import com.arialyy.frame.permission.PermissionManager;
import com.arialyy.frame.util.show.T;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.SPUtils;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.view.HorizontalProgressBar.HorizontalProgressBarWithNumber;
import com.tepia.main.R;
import com.tepia.main.view.maincommon.reservoirs.detail.OperationPlanActivity;

import java.io.File;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
  * Created by      Android studio
  *
  * @author :wwj (from Center Of Wuhan)
  * Date    :2018/10/9
  * Version :1.0
  * 功能描述 :下载
 **/

public class DownLoadActivity extends BaseActivity {

    private String downloadSpName = "downloadMap";
    private static String DOWNLOAD_URL = "http://rs.0.gaoshouyou.com/d/04/1e/400423a7551e1f3f0eb1812afa1f9b44.apk";
    String parentPath = Environment.getExternalStorageDirectory().getPath() + "/SKGJDownloads";
    private String fileName;
    private String filePath;
    private TextView tvFileName;
    private TextView tvSpeed;
    private HorizontalProgressBarWithNumber progressBar;
    private ImageView ivStop;
    private TextView tvDownloadPreview;
    private DownloadTarget target;
    private boolean isLoadComplete;
    private SharedPreferences sharedPreferences;
    private Map<String, Boolean> downloadMap;
    private String downloadMapStr;

    public static Intent setIntent(Intent intent, String fileName, String filePath) {
        intent.putExtra("fileName", fileName);
        intent.putExtra("filePath", filePath);
        return intent;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_download;
    }

    @Override
    public void initView() {
        setCenterTitle("文件下载");
        showBack();
        Intent intent = getIntent();
        String iFileName = intent.getStringExtra("fileName");
        String iFilePath = intent.getStringExtra("filePath");
        ImageView ivTitle = findViewById(R.id.iv_title);
//        this.fileName = "ggsg8.apk";
        tvFileName = findViewById(R.id.tv_file_name);
        tvSpeed = findViewById(R.id.tv_speed);
        progressBar = findViewById(R.id.progressBar);
        ivStop = findViewById(R.id.iv_stop);
        tvDownloadPreview = findViewById(R.id.tv_download_preview);
        ivTitle.setOnClickListener(v -> {
            initPermission();
        });
//        String tvSpeedString = "下载中...("+0+"/"+target.getConvertFileSize()+")";
//        tvSpeed.setText(tvSpeedString);
        ivStop.setOnClickListener(v -> {
            tvDownloadPreview.setVisibility(View.VISIBLE);
            //暂停
            Aria.download(DownLoadActivity.this).load(filePath).stop();
        });
        tvDownloadPreview.setOnClickListener(v -> {
            if (isLoadComplete) {
                Intent oIntent = new Intent();
                oIntent.setClass(DownLoadActivity.this, OperationPlanActivity.class);
                oIntent.putExtra("select", OperationPlanActivity.value_preview);
                oIntent.putExtra(OperationPlanActivity.PREVIEW_PATH, filePath);
                startActivity(oIntent);
            } else {
                tvDownloadPreview.setVisibility(View.GONE);
                initPermission();
            }
        });
        if (iFileName != null && iFilePath != null) {
            fileName = iFileName;
            filePath = iFilePath;
            tvFileName.setText(fileName);
            if (filePath.endsWith(".doc") || filePath.endsWith(".docx")) {
                ivTitle.setImageResource(R.drawable.jianjie_word);
            } else if (filePath.endsWith(".xls") || filePath.endsWith(".xlsx")) {
                ivTitle.setImageResource(R.drawable.jianjie_excel);
            } else if (filePath.endsWith(".ppt") || filePath.endsWith(".pptx")) {
                ivTitle.setImageResource(R.drawable.jianjie_ppt);
            } else if (filePath.endsWith(".pdf")) {
                ivTitle.setImageResource(R.drawable.jianjie_ppt);
            }
            target = Aria.download(this).load(filePath);
            long fileSize = target.getFileSize();
            File file = new File(parentPath + "/" + iFileName);
            //如果文件不存在并且不是目录文件
            if (!file.exists() && !file.isDirectory()) {
                //如果大小小于2b
//                LogUtil.i("filePath:" + filePath);
//                LogUtil.i("fileSize:" + fileSize);
                //                下载文件
                isLoadComplete = false;
                initPermission();
            } else {
                //文件存在
                String spDownloadStr = SPUtils.getInstance().getString(downloadSpName, "");
                if (spDownloadStr == null || "".equals(spDownloadStr)) {
                    //文件没有下载完全  开始下载
                    isLoadComplete = false;
                    initPermission();
                } else {
                    //是否下载完成
                    boolean isloadComplete = false;
                    downloadMap = new Gson().fromJson(spDownloadStr, downloadMap.getClass());
                    if (downloadMap != null && downloadMap.size() > 0) {
                        for (Map.Entry<String, Boolean> entry : downloadMap.entrySet()) {
                            String key = entry.getKey();
                            if (key.equals(fileName)) {
                                Boolean isLoadComplete = entry.getValue();
                                isloadComplete = isLoadComplete;
                            }
                        }
                    }
                    if (isloadComplete) {
                        //如果为ture 表示之前已经下载完成过
                        //文件已经下载
                        isLoadComplete = true;
                        tvDownloadPreview.setVisibility(View.VISIBLE);
                        tvDownloadPreview.setText("预览文件");
                    } else {
                        //文件没有下载完全  开始下载
                        isLoadComplete = false;
                        initPermission();
                    }
                }
            }
        } else {
            ToastUtils.longToast("文件名或者文件地址为空");
        }
    }

    private void initPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            downLoad();
        } else {  //6.0处理
            boolean hasPermission = PermissionManager.getInstance()
                    .checkPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (hasPermission) {
                downLoad();
            } else {
                PermissionManager.getInstance().requestPermission(this, new OnPermissionCallback() {
                    @Override
                    public void onSuccess(String... permissions) {
                        downLoad();
                    }

                    @Override
                    public void onFail(String... permissions) {
                        T.showShort(DownLoadActivity.this, "没有文件读写权限");
                    }
                }, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
        }
    }

    private void downLoad() {
        File file = new File(Environment.getExternalStorageDirectory().getPath() + "SKGJDownloads");
        if (!file.exists() && !file.isDirectory()) {
            boolean mkdir = file.mkdir();
        }
        String path = Environment.getExternalStorageDirectory().getPath() + "/SKGJDownloads" + "/" + fileName;
        Aria.download(DownLoadActivity.this)
                .load(filePath)
                .setFilePath(path)
                .start();
    }

    @Override
    public void initData() {
        Aria.download(this).register();
        downloadMap = new HashMap();
        downloadMapStr = new Gson().toJson(downloadMap);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initRequestData() {

    }

    @Download.onWait
    void onWait(DownloadTask task) {
        if (task.getKey().equals(filePath)) {
//            Log.d(TAG, "wait ==> " + task.getDownloadEntity().getFileName());
            LogUtil.i("wait ==> " + task.getDownloadEntity().getFileName());
        }
    }

    @Download.onPre
    protected void onPre(DownloadTask task) {
        if (task.getKey().equals(filePath)) {
//            Log.d(TAG, "onPre ==> " + task.getDownloadEntity().getFileName());
            LogUtil.i("onPre ==> " + task.getDownloadEntity().getFileName());
        }
    }

    @Download.onTaskStart
    void taskStart(DownloadTask task) {
        if (task.getKey().equals(filePath)) {
//            Log.d(TAG, "taskStart ==> " + task.getDownloadEntity().getFileName()+"文件大小:"+task.getDownloadEntity().getFileSize());
            LogUtil.i("taskStart ==> " + task.getDownloadEntity().getFileName() + "文件大小:" + task.getDownloadEntity().getFileSize());
            String tvSpeedString = "下载中...(" + 0 + "/" + target.getConvertFileSize() + ")";
            tvSpeed.setText(tvSpeedString);
        }
    }

    @Download.onTaskRunning
    protected void running(DownloadTask task) {
//        ALog.d(TAG, String.format("%s_running_%s", getClass().getName(), hashCode()));
        LogUtil.i(String.format("%s_running_%s", getClass().getName(), hashCode()));
        if (task.getKey().equals(filePath)) {
            //Log.d(TAG, task.getKey());
            long len = task.getFileSize();
            int percent = task.getPercent();
            long size = task.getFileSize() * percent / 100;
            String s = CommonUtil.formatFileSize(size);
//            Log.i(TAG,"已下载:"+s);
            LogUtil.i("已下载:" + s);
            if (len == 0) {
                progressBar.setProgress(0);
                String tvSpeedString = "下载中...(" + 0 + "/" + target.getConvertFileSize() + ")";
                tvSpeed.setText(tvSpeedString);
            } else {
                progressBar.setProgress(task.getPercent());
                String tvSpeedString = "下载中...(" + s + "/" + target.getConvertFileSize() + ")";
                tvSpeed.setText(tvSpeedString);
            }

        }
    }

    @Download.onTaskResume
    void taskResume(DownloadTask task) {
        if (task.getKey().equals(filePath)) {
//            ALog.d(TAG, String.format("暂停", getClass().getName(), hashCode()));
            LogUtil.i(String.format("暂停", getClass().getName(), hashCode()));
        }
    }

    @Download.onTaskStop
    void taskStop(DownloadTask task) {
        if (task.getKey().equals(filePath)) {
//            Log.d(TAG, "taskStop ==> " + task.getDownloadEntity().getFileName());
            LogUtil.i("taskStop ==> " + task.getDownloadEntity().getFileName());

        }
    }

    @Download.onTaskCancel
    void taskCancel(DownloadTask task) {
        if (task.getKey().equals(filePath)) {
            Toast.makeText(DownLoadActivity.this, "取消下载", Toast.LENGTH_SHORT).show();
            LogUtil.i("cancel");
            progressBar.setProgress(0);
        }
    }

    @Download.onTaskFail
    void taskFail(DownloadTask task) {
        if (task.getKey().equals(filePath)) {
//            Log.d(TAG, "下载失败");
            LogUtil.i("下载失败");
            Toast.makeText(DownLoadActivity.this, "下载失败", Toast.LENGTH_SHORT).show();
            tvDownloadPreview.setVisibility(View.VISIBLE);
        }
    }

    @Download.onTaskComplete
    void taskComplete(DownloadTask task) {
        if (task.getKey().equals(filePath)) {
            LogUtil.i("下载完成");
//            btOpenFile.setVisibility(View.VISIBLE);
            progressBar.setProgress(100);
            Toast.makeText(DownLoadActivity.this, "下载完成", Toast.LENGTH_SHORT).show();
            tvDownloadPreview.setText("预览文件");
            tvDownloadPreview.setVisibility(View.VISIBLE);
            isLoadComplete = true;
            //拿到首选项中的map
            String spDownloadMapStr = SPUtils.getInstance().getString("downloadMap", "");
            if (spDownloadMapStr == null || spDownloadMapStr.equals("")) {
                //没有下载数据  保存数据
                downloadMap.put(fileName, true);
            } else {
                //拿到保存的下载完成数据
                downloadMap = new Gson().fromJson(spDownloadMapStr, downloadMap.getClass());
                if (downloadMap != null && downloadMap.size() > 0) {
                    downloadMap.put(fileName, true);
                }
            }
            SPUtils.getInstance().putString(downloadSpName, new Gson().toJson(downloadMap));
//            L.d(TAG, "path ==> " + task.getDownloadEntity().getDownloadPath());
            LogUtil.i(task.getDownloadEntity().getDownloadPath());
//            L.d(TAG, "md5Code ==> " + CommonUtil.getFileMD5(new File(task.getDownloadPath())));
            LogUtil.i("md5Code ==> " + CommonUtil.getFileMD5(new File(task.getDownloadPath())));
//            L.d(TAG, "data ==> " + Aria.download(this).getDownloadEntity(DOWNLOAD_URL));
            LogUtil.i("data ==> " + Aria.download(this).getDownloadEntity(filePath));
            //Intent install = new Intent(Intent.ACTION_VIEW);
            //install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //File apkFile = new File(task.getDownloadPath());
            //install.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
            //startActivity(install);
        }
    }

    @Download.onNoSupportBreakPoint
    public void onNoSupportBreakPoint(DownloadTask task) {
        if (task.getKey().equals(filePath)) {
//            Log.d(TAG, "该下载链接不支持断点");
            LogUtil.i("该下载链接不支持断点");
            T.showShort(DownLoadActivity.this, "该下载链接不支持断点");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Aria.download(this).unRegister();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Aria.download(this).unRegister();
    }


}
