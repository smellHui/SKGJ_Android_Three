package com.tepia.main.view.mainworker.report;

import java.io.File;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;


import com.tepia.base.mvp.BaseActivity;
import com.tepia.main.R;
import com.tepia.main.common.CustomVideoView;

public class PlayLocalVedio extends BaseActivity {

	private CustomVideoView vv_player;
	private Button delBtnVedio;
	private String urlvedio;
	private ImageView imageVediopause;

	@Override
	public int getLayoutId() {
		return R.layout.play_local_vedio;
	}

	@Override
	public void initView() {
		setCenterTitle("视频预览");
		showBack();
		Bundle bundle;
		bundle = getIntent().getExtras();
		urlvedio = bundle.getString("urllocalvedio");
		loadView();
		addListener();
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

	public void loadView() {

		vv_player = findViewById(R.id.vv_player_local);
		delBtnVedio = findViewById(R.id.delBtnVedio);

		imageVediopause = (ImageView) findViewById(R.id.pause_vedio_imageview);
		imageVediopause.setVisibility(View.GONE);
		imageVediopause.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				imageVediopause.setVisibility(View.GONE);
				vv_player.start();

			}
		});
		if (urlvedio.contains("http")) {
			Uri uri = Uri.parse(urlvedio);
			vv_player.setVideoURI(uri);
		} else {
			vv_player.setVideoPath(urlvedio);
		}

		vv_player.setMediaController(new MediaController(PlayLocalVedio.this));
		vv_player.requestFocus();
		vv_player.start();

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		refreeVedios();

	}



	public void addListener() {

		vv_player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
					@Override
					public void onCompletion(MediaPlayer mp) {
						refreeVedios();
					}
				});
		
		vv_player.setOnErrorListener(new MediaPlayer.OnErrorListener() {
			@Override
			public boolean onError(MediaPlayer mp, int what, int extra) {
				imageVediopause.setVisibility(View.GONE);
				
				return false;
			}
		});

		vv_player.setPlayPauseListener(new CustomVideoView.PlayPauseListener() {

			@Override
			public void onPlay(){
				imageVediopause.setVisibility(View.GONE);
			}

			@Override
			public void onPause() {
				imageVediopause.setVisibility(View.VISIBLE);
			}
		});

		delBtnVedio.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				new AlertDialog.Builder(PlayLocalVedio.this)
						.setTitle("温馨提示")
						.setMessage("是否确认取消选中的视频")
						.setPositiveButton(getText(R.string.sure).toString(),
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										EmergenceDetaliFragment.videoPath = "";
										Intent intent = new Intent();
										setResult(200,intent);
										finish();
									}

								})
						.setNegativeButton(getText(R.string.cancel).toString(),
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
									}

								}).show();

			}
		});



	}

	public void refreeVedios() {
		vv_player.pause();
		imageVediopause.setVisibility(View.VISIBLE);
	}

}
