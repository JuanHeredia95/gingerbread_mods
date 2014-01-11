package com.b16h22.statusbar;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ComposeMessage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compose_message);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.compose_message, menu);
		return true;
	}

}
