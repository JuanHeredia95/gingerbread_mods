package com.b16h22.statusbar;

import java.lang.reflect.Method;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

public class VolumeToggle extends ImageView {

 
 public VolumeToggle(final Context context, AttributeSet attrs) {
  super(context, attrs);
  
  this.setImageResource(R.drawable.ic_volume_media);

  this.setOnClickListener(new View.OnClickListener() {
		 
		@Override
		public void onClick(View v) {
			Intent myIntent = new Intent( context, VolumeSliders.class);
			myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(myIntent);
		    try{ 
		    	   Object service  = context.getSystemService("statusbar");
		    	   Class<?> statusbarManager = Class.forName("android.app.StatusBarManager");
		    	   Method collapse = statusbarManager.getMethod("collapse");
		    	   collapse.invoke(service);
		    	 }
		    	 catch(Exception ex){           

		    	 }
		};
	});
 }

}

