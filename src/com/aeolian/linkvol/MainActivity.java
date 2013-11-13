package com.aeolian.linkvol;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

import android.widget.CheckBox;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onCheckboxClicked(View view) {
		Intent intent = new Intent();
		intent.setClass(getApplicationContext(), LinkVolService.class);
		
		boolean checked = ((CheckBox) view).isChecked();
		switch (view.getId()) {
		case R.id.chk_start:
			if (checked) {
				startService(intent);
			} else {
				stopService(intent);
			}
			break;
		}
	}
}
