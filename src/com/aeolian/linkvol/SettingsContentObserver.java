package com.aeolian.linkvol;

import android.content.Context;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.os.Handler;
import android.util.Log;

public class SettingsContentObserver extends ContentObserver {
	private static final String TAG = "SettingsContentObserver";
	private int preVol;
	private int curVol;
	private AudioManager mAudioManager;
	private Context mContext;

	public SettingsContentObserver(Context c, Handler handler) {
		super(handler);
		mContext = c;
		mAudioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
		preVol = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
	}

	@Override
	public boolean deliverSelfNotifications() {
		return super.deliverSelfNotifications();
	}

	@Override
	public void onChange(boolean selfChange) {
		super.onChange(selfChange);

		mAudioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
		curVol = mAudioManager.getStreamVolume(AudioManager.STREAM_RING);
		int delta = curVol - preVol;
		if (delta != 0) {
			Log.d(TAG, "Ringtone volume changed to " + curVol);
			preVol = curVol;
			mAudioManager.setStreamVolume(AudioManager.STREAM_NOTIFICATION, curVol, 0);
			Log.d(TAG, "Notification volume also changed to " + curVol);
		}

	}
}