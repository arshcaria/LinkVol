package com.aeolian.linkvol;

import android.content.Context;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.os.Handler;
import android.util.Log;

public class SettingsContentObserver extends ContentObserver {
	private static final String TAG = "SettingsContentObserver";
	private int previousVolume;
	private int currentVolume;
	private Context mContext;

	public SettingsContentObserver(Context c, Handler handler) {
		super(handler);
		mContext = c;
		AudioManager audio = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
		previousVolume = audio.getStreamVolume(AudioManager.STREAM_MUSIC);
	}

	@Override
	public boolean deliverSelfNotifications() {
		return super.deliverSelfNotifications();
	}

	@Override
	public void onChange(boolean selfChange) {
		super.onChange(selfChange);

		AudioManager audio = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
		currentVolume = audio.getStreamVolume(AudioManager.STREAM_RING);
		int delta = previousVolume - currentVolume;
		if (delta != 0) {
			Log.d(TAG, "Ringtone volume changed to " + currentVolume);
			previousVolume = currentVolume;
			audio.setStreamVolume(AudioManager.STREAM_NOTIFICATION, currentVolume, 0);
		}

	}
}