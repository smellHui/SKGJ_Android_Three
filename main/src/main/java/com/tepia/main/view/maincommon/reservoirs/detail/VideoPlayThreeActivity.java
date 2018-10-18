package com.tepia.main.view.maincommon.reservoirs.detail;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.githang.statusbar.StatusBarCompat;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.base.utils.DoubleClickUtil;
import com.tepia.base.utils.ScreenUtil;
import com.tepia.main.R;
import com.tepia.main.common.MyListView;
import com.tepia.main.databinding.ActivityVideoThreePlayBinding;
import com.tepia.main.view.main.detail.vedio.Constant;
import com.tepia.main.view.main.detail.vedio.PlaySurfaceView;
import com.tepia.main.view.main.detail.vedio.VideoInfo;

import org.MediaPlayer.PlayM4.Player;

import java.util.List;

import static com.tepia.base.utils.ScreenUtil.getStatusBarHeight;

/**
 * Created by      Android studio
 *
 * @author :ly (from Center Of Wuhan)
 * Date    :2018-10-17
 * Version :1.2.4
 * 功能描述 :海康视频播放
 **/
public class VideoPlayThreeActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    private ActivityVideoThreePlayBinding binding;
    private VideoView videoView;
    private int m_iPort = -1;
    private int m_iPlayID = -1;
    private static PlaySurfaceView[] playView = new PlaySurfaceView[1];
    //    private PlaySurfaceView playsurfaceView;
    private int screenWidth = 0;
    private int screenHeight = 0;
    private int m_iLogID;
    private int m_iStartChan;
    private int m_iChanNum = 0;
    private boolean m_bMultiPlay = false;
    private boolean m_bNeedDecode = true;
    private ImageView back_finsh;
    private List<VideoInfo> data;
    private ProgressBar liveProgressBar;
    private TextView loading;
    private float x1 = 0;
    private float x2 = 0;
    private float y1 = 0;
    private float y2 = 0;
    private int position = 0;
    private MyListView videolist;
    private Handler handler = new Handler();
    private int position_first;
    private int position_again;
    private LinearLayout selectionArea;
    private ScrollView scrollView;
    private RelativeLayout video_rel;
    private boolean isFull = false;
    private boolean isMplay = false;
    private LinearLayout title_Line;
    private boolean isRadio = false;
    private boolean isItem = false;

    /**
     * 码流类型
     */
    private int mStreamType;
    /**
     * 码流切换
     */
    private RadioGroup mRadioGroup;

    private TextView tvStatus;
    private TextView tvName;
    private ImageView fullScreenIv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_video_three_play;
    }


    @Override
    public void initView() {
        setCenterTitle("实时视频");
        showBack();
//        playsurfaceView = findViewById(R.id.playsurfaceView);
        binding = DataBindingUtil.bind(mRootView);
        videoView = findViewById(R.id.surfaceView);
        liveProgressBar = findViewById(R.id.liveProgressBar);
        loading = findViewById(R.id.loading);
        videolist = findViewById(R.id.videolist);
        mRadioGroup = findViewById(R.id.radioGroup);
        scrollView = findViewById(R.id.scrollView);
        selectionArea = findViewById(R.id.selectionArea);
        video_rel = findViewById(R.id.video_rel);
        tvStatus = findViewById(R.id.tv_video_channelStatus);
        tvName = findViewById(R.id.tv_video_channelname);
        fullScreenIv = findViewById(R.id.fullScreenIv);
        binding.fullScreenIv.setVisibility(View.GONE);
        mRadioGroup.check(R.id.subRadio);
        mRadioGroup.check(R.id.subRadio);
        mStreamType = Constant.SUB_STREAM;
        initVedioview();
        fullScreenIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
                if (isFull) {
                    isFull = false;
                    changeFull();
                    if (m_bMultiPlay) {
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

                        getLoToolbarCommon().setVisibility(View.VISIBLE);
                        binding.selectionArea.setVisibility(View.VISIBLE);
                        binding.itemLy.rootVedioLy.setVisibility(View.VISIBLE);
                        stopMultiPreview();
                        startPlay(position_first);

                    }
                } else {
                    isFull = true;
                    changeFull();
                    // 设置当前activity为横屏
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

                    getLoToolbarCommon().setVisibility(View.GONE);
                    binding.selectionArea.setVisibility(View.GONE);
                    binding.itemLy.rootVedioLy.setVisibility(View.GONE);
                    stopMultiPreview();
                    startPlay(position_first);

                }
            }
        });
    }

    /**
     * 初始化相关控件
     */
    private void initVedioview() {

        tvStatus.setTextColor(ContextCompat.getColor(this, R.color.color_load_blue));
        tvName.setTextColor(ContextCompat.getColor(this, R.color.color_load_blue));
        VideoInfo video = data.get(0);
        if (video.getChaStatus() != null && "ok".equalsIgnoreCase(video.getChaStatus())) {
            tvStatus.setText("连接状态:正常");
        } else {
            tvStatus.setText("连接状态:不正常");
            tvStatus.setTextColor(Color.RED);
        }
        if (TextUtils.isEmpty(video.getChaName())) {
            tvName.setText("--");
        } else {
            tvName.setText(video.getChaName().trim());
        }

        if (m_bNeedDecode) {
            if (!m_bMultiPlay) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startPlay(position_first);
                        m_bMultiPlay = true;
                    }
                }, 1000);
                liveProgressBar.setVisibility(View.VISIBLE);
                loading.setVisibility(View.VISIBLE);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        liveProgressBar.setVisibility(View.GONE);
                        loading.setVisibility(View.GONE);
                        binding.fullScreenIv.setVisibility(View.VISIBLE);
                    }
                }, 2000);
            } else {
                stopMultiPreview();
                m_bMultiPlay = false;
                loading.setVisibility(View.GONE);
                liveProgressBar.setVisibility(View.GONE);
            }
        } else {
            Toast.makeText(VideoPlayThreeActivity.this, "加载失败，请重新加载", Toast.LENGTH_SHORT).show();

        }


        mRadioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        m_iLogID = intent.getIntExtra("m_iLogID", 0);
        m_iStartChan = intent.getIntExtra("m_iStartChan", 33);
        m_iChanNum = intent.getIntExtra("m_iChanNum", 0);
        position_first = intent.getIntExtra("position", 0);
        data = (List<VideoInfo>) getIntent().getSerializableExtra("video_list");


    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initRequestData() {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.mainRadio) {
            if (isItem) {
                mStreamType = Constant.MAIN_STREAM;
                stopMultiPreview();
                startPlay(position_first);
            } else {
                mStreamType = Constant.MAIN_STREAM;
                position = position_first;
                stopMultiPreview();
                startPlay(position);
            }

        } else if (checkedId == R.id.subRadio) {
            if (isItem) {
                mStreamType = Constant.SUB_STREAM;
                stopMultiPreview();
                startPlay(position_first);
            } else {
                mStreamType = Constant.SUB_STREAM;
                position = position_first;
                stopMultiPreview();
                startPlay(position);
            }

        }
    }


    /**
     * 停在播放
     */
    private void stopMultiPreview() {
        for (int i = 0; i < 1; i++) {
            playView[i].stopPreview();
            ((ViewGroup) playView[i].getParent()).removeView(playView[i]);
            playView[i] = null;
        }
        m_iPlayID = -1;

    }


    /**
     * 选择播放
     *
     * @param pos
     */
    private void startPlay(int pos) {
        /*DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;*/
        for (int position = 0; position < 1; position++) {
            if (playView[position] == null) {
                playView[position] = new PlaySurfaceView(this);
                playView[position].setLayoutParams(videoView.getLayoutParams());
                video_rel.addView(playView[position]);
                playView[position].startPreview(mStreamType, m_iLogID, m_iStartChan + pos);
            }
        }
        m_iPlayID = playView[0].m_iPreviewHandle;
    }


    private void changeFull() {
        if (isFull) {
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);

            videoView.setLayoutParams(params);
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
            params.height = ScreenUtil.dp2px(VideoPlayThreeActivity.this, 220);
            videoView.setLayoutParams(params);
            if (Build.VERSION.SDK_INT >= 19) {
                getWindow().getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                );
                StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this, R.color.colorPrimary), true);
            }
             else {
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
            isMplay = true;
            if (isFull) {
                isFull = false;
                changeFull();
                if (m_bMultiPlay) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    getLoToolbarCommon().setVisibility(View.VISIBLE);
                    binding.selectionArea.setVisibility(View.VISIBLE);
                    binding.itemLy.rootVedioLy.setVisibility(View.VISIBLE);
                    stopMultiPreview();
                    startPlay(position_first);
                    return true;

                }
            } else {
                if (m_bMultiPlay) {
                    stopMultiPreview();
                    m_bMultiPlay = false;
                    finish();
                } else {
                    finish();
                }
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);

    }

    @Override
    protected void onDestroy() {
        if (m_bMultiPlay) {
            /*for (int i = 0; i < 1; i++) {
                playView[i].stopPreview();
                ((ViewGroup) playView[i].getParent()).removeView(playView[i]);
                playView[i] = null;
            }*/
            stopMultiPreview();
//            playsurfaceView.stopPreview();
//            ((ViewGroup) playsurfaceView.getParent()).removeView(playsurfaceView);
        }

        //把所有的消息和回调移除
        data.clear();
        handler.removeCallbacksAndMessages(null);
        Player.getInstance().freePort(m_iPort);
        m_iPort = -1;
        super.onDestroy();
    }


}
