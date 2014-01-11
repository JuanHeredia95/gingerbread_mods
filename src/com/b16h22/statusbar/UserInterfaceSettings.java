package com.b16h22.statusbar;

import java.lang.reflect.Method;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

public class UserInterfaceSettings extends ImageView {

	 public UserInterfaceSettings(final Context context, AttributeSet attrs) {
		  super(context, attrs);
		  
		  ImageView image = (ImageView) findViewById(R.id.ui_settings);
		  image.setImageResource(R.drawable.ic_notify_quicksettings);
			image.setOnClickListener(new View.OnClickListener() {
				 
				@Override
				public void onClick(View v) {
					Intent intent = new Intent();
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					intent.setComponent(new ComponentName("com.b16h22.evo", "com.b16h22.evo.UserInterface"));
				    v.getContext().startActivity(intent);
				    try{ 
				    	   Object service  = context.getSystemService("statusbar");
				    	   Class<?> statusbarManager = Class.forName("android.app.StatusBarManager");
				    	   Method collapse = statusbarManager.getMethod("collapse");
				    	   collapse.invoke(service);
				    	 }
				    	 catch(Exception ex){           

				    	 }
		        }
		});
	 }

	}

