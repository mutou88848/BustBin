package com.clientBase.db;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.dustbin.R;

import java.io.IOException;
import java.util.List;

public class MusicService extends Service {

	public static final int NOTICE_ID_TYPE_0 = R.string.app_name;
	private static final String TAG = "ServiceDemo";
	public static final String ACTION = "com.lql.service.ServiceDemo";
	private MediaPlayer mMediaPlayer;
	private int currentListItme = -1;// 当前播放第几首歌
	private List<MusicLoader.MusicInfo> musicList;
	private int currentTime = 0;// 歌曲播放进度
	private int play_mode = 1;

	@Override
	public void onStart(Intent intent, int startId) {
		Log.v(TAG, "ServiceDemo onStart");

		MusicLoader musicLoader = MusicLoader.instance(getContentResolver());
		musicList = musicLoader.getMusicList();
		// playMusic(musicList.get(0).getUrl());
	}

	@Override
	public IBinder onBind(Intent intent) {
		Log.v(TAG, "ServiceDemo onBind");
		System.out.println("onbind");
		// 返回我们自己写的，实现了IService接口的Binder
		return new MyBinder();
	}

	@Override
	public void onCreate() {
		Log.v(TAG, "ServiceDemo onCreate");
		super.onCreate();

		if (mMediaPlayer == null) {
			mMediaPlayer = new MediaPlayer();
		}


	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.v(TAG, "ServiceDemo onStartCommand");
		return super.onStartCommand(intent, flags, startId);
	}

	/**
	 * 根据歌曲存储路径播放歌曲
	 */
	private void playMusic(String path) {

		try {
			/* 重置MediaPlayer */
			mMediaPlayer.reset();
			/* 设置要播放的文件的路径 */
			mMediaPlayer.setDataSource(path);
			/* 准备播放 */
			mMediaPlayer.prepare();
			/* 开始播放 */
			mMediaPlayer.start();

			mMediaPlayer.setOnCompletionListener(new OnCompletionListener() {
				public void onCompletion(MediaPlayer arg0) {



					System.out.println("-----------p" + play_mode);


				}
			});



		} catch (IOException e) {
		}
	}



	/**
	 * 歌曲是否真在播放
	 */
	public boolean isPlayMusic() {
		return mMediaPlayer.isPlaying();
	}

	/**
	 * 暂停或开始播放歌曲
	 */
	public void pauseOrPlay() {
		if (mMediaPlayer.isPlaying()) {
			currentTime = mMediaPlayer.getCurrentPosition();
			mMediaPlayer.pause();
		} else {
			mMediaPlayer.start();
		}
	}

	public void setCurrentItem(int position) {
		currentListItme = position;





	}

	public void XunHuanMusic() {
		// 重要！不停止会造成播放异常，重影效果
		mMediaPlayer.stop();
		playMusic(musicList.get(currentListItme).getUrl());

	}




	/**
	 * 自定义绑定Service类，通过这里的getService得到Service，之后就可调用Service这里的方法了
	 */
	public class LocalBinder extends Binder {
		public MusicService getService() {
			Log.d("playerService", "getService");
			return MusicService.this;
		}
	}



	/** 通知栏按钮点击事件对应的ACTION */
	public final static String ACTION_BUTTON = "com.notifications.intent.action.ButtonClick";

	public final static String INTENT_BUTTONID_TAG = "ButtonId";

	/** 上一首 按钮点击 ID */
	public final static int BUTTON_PREV_ID = 1;
	/** 播放/暂停 按钮点击 ID */
	public final static int BUTTON_PALY_ID = 2;
	/** 下一首 按钮点击 ID */
	public final static int BUTTON_NEXT_ID = 3;

	/**
	 * 广播监听按钮点击时间
	 */
	private static final int CHANGED = 0x0010;






	public static final String BROADCAST_ACTION = "receiver";

	private void sendReceiver() {
		Intent intent = new Intent();
		intent.setAction(BROADCAST_ACTION);
		intent.putExtra("msg", "update");
		sendBroadcast(intent);

	}




	// 实现一个接口，然后我们就在绑定服务的时候拿到这个Binder对象，然后调用接口里面的方法
	class MyBinder extends Binder implements IService {

		@Override
		public void play() {
			pauseOrPlay();
		}

		@Override
		public void playNext() {


		}

		@Override
		public void playFront() {


		}

		@Override
		public void playPath(String path) {
			playMusic(path);

		}

		@Override
		public boolean isPlay() {
			return isPlayMusic();
		}

		@Override
		public void playPos(int pos) {
			setCurrentItem(pos);

		}

		@Override
		public void playNotifi() {

		}

		@Override
		public void playModeCheck() {

		}


	}


}