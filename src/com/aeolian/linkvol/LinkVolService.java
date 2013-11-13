package com.aeolian.linkvol;

import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

public class LinkVolService extends Service {
	private static final String TAG = "LinkVolService";
	private SettingsContentObserver mSettingsContentObserver;
	private ContentResolver mContentResolver;
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onStart(Intent intent, int startid) {
		Log.d(TAG, "onStart");
		Log.d(TAG, "start tracking volume change");
		mSettingsContentObserver = new SettingsContentObserver(this, new Handler());
		mContentResolver = getApplicationContext().getContentResolver();
		mContentResolver.registerContentObserver(android.provider.Settings.System.CONTENT_URI, true, mSettingsContentObserver );
	}

	@Override
	public void onDestroy() {
		Log.d(TAG, "onDestroy");
		Log.d(TAG, "stop tracking volume change");
		mContentResolver.unregisterContentObserver(mSettingsContentObserver);
	}
}
