package com.b16h22.statusbar;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import com.b16h22.statusbar.R;

public class Expander extends ImageView {

 
 public Expander(final Context context, AttributeSet attrs) {
  super(context, attrs);
  
  ImageView image = this;
  image.setImageResource(R.drawable.ic_notify_quicksettings);
  
	image.setOnClickListener(new View.OnClickListener() {
		 
		@Override
		public void onClick(View v) {
	        if (v.isSelected()){
	            v.setSelected(false);
				Intent intent = new Intent();
				intent.setAction("EXPAND");
				context.sendBroadcast(intent);
	        } else {
	            v.setSelected(true);
				Intent intent = new Intent();
				intent.setAction("SHRINK");
				context.sendBroadcast(intent);
	        }		};
	});
 }

}


