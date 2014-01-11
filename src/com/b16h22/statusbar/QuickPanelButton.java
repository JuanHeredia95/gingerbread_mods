package com.b16h22.statusbar;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

public class QuickPanelButton extends ImageView {

 String message;
 ImageView image;
 public QuickPanelButton(final Context context, AttributeSet attrs) {
  super(context, attrs);
  
  image = this;
  image.setImageResource(R.drawable.ic_notify_quicksettings);

	image.setOnClickListener(new View.OnClickListener() {
		 
		@Override
		public void onClick(View v) {
	        if (v.isSelected()){
	            v.setSelected(false);
				Intent intent = new Intent();
				intent.setAction("com.b16h22.statusbar.FLIP_TO_NOTIF");
				context.sendBroadcast(intent);
	        } else {
	            v.setSelected(true);
				Intent intent = new Intent();
				intent.setAction("com.b16h22.statusbar.FLIP_TO_TOGGLES");
				context.sendBroadcast(intent);
	        }		};
	}); 
    

 }
 
}

