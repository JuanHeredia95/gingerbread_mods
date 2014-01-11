package com.b16h22.statusbar;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.view.Window;

public class VolumeSliders extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		final Dialog dialog = new Dialog(this);
		dialog.getWindow();
	    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);  
		dialog.setContentView(R.layout.volume_sliders);
		dialog.show();
	}
	
}
