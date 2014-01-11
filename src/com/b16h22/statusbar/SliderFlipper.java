package com.b16h22.statusbar;

import java.lang.reflect.Method;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.ImageView;
import com.b16h22.statusbar.R;

public class SliderFlipper extends ImageView {

 
 public SliderFlipper(final Context context, AttributeSet attrs) {
  super(context, attrs);
  
  ImageView image = (ImageView) findViewById(R.id.slider);
  image.setImageResource(R.drawable.ic_notify_quicksettings);
  

	image.setOnClickListener(new View.OnClickListener() {
		 
		@Override
		public void onClick(View v) {
	        if (v.isSelected()){
	            v.setSelected(false);
				Intent intent = new Intent();
				intent.setAction("com.b16h22.statusbar.FLIP_TO_SLIDER");
				context.sendBroadcast(intent);
	        } else {
	            v.setSelected(true);
				Intent intent = new Intent();
				intent.setAction("com.b16h22.statusbar.FLIP_TO_PANEL");
				context.sendBroadcast(intent);
	        }		};
	});
	image.setOnLongClickListener(new View.OnLongClickListener() {
		@Override
		public boolean onLongClick(View v) {
            v.getContext().startActivity((new Intent("com.android.settings.DISPLAY_SETTINGS")).setFlags(0x10000000));
		    try{ 
		    	   Object service  = context.getSystemService("statusbar");
		    	   Class<?> statusbarManager = Class.forName("android.app.StatusBarManager");
		    	   Method collapse = statusbarManager.getMethod("collapse");
		    	   collapse.invoke(service);
		    	 }
		    	 catch(Exception ex){           

		    	 }
		    v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
            return false;
	}
 });
 }
 
}

