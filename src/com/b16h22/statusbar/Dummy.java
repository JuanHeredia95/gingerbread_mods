package com.b16h22.statusbar;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class Dummy extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dummy);
	}

	public void black(View v) {
	Intent intent = new Intent();
	String color="#ff111111";
	intent.setAction("com.b16h22.statusbar.CHANGE_BACKGROUND_DARK");
	intent.putExtra("color",color.toString());
	sendBroadcast(intent);
}	
	public void black1(View v) {
		
	Intent intent = new Intent();
	String color="#aa111111";
	intent.setAction("com.b16h22.statusbar.CHANGE_BACKGROUND_DARK");
	intent.putExtra("color",color.toString());
	sendBroadcast(intent);

}
	public void black2(View v) {
		
	Intent intent = new Intent();
	String color="#e9111111";
	intent.setAction("com.b16h22.statusbar.CHANGE_BACKGROUND_DARK");
	intent.putExtra("color",color.toString());
	sendBroadcast(intent);

}
}
