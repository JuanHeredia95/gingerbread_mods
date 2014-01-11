package com.b16h22.statusbar;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MenuPopup extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_popup);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_popup, menu);
		return true;
	}

}
