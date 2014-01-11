package com.b16h22.statusbar;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

public class Menu extends ImageView {

	 public Menu(final Context context, AttributeSet attrs) {
		  super(context, attrs);
		  
		  ImageView image = (ImageView) findViewById(R.id.sett);
		  image.setImageResource(R.drawable.ic_notify_quicksettings);
			image.setOnClickListener(new View.OnClickListener() {
				 
				@Override
				public void onClick(View v) {
					Intent intent = new Intent();
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					intent.setComponent(new ComponentName("com.sec.android.app.myfiles", "com.sec.android.app.myfiles.MainActivity"));
				    v.getContext().startActivity(intent);
		        }
		});
	 }

	}
