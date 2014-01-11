package com.b16h22.statusbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import com.b16h22.statusbar.R;

public class HideSlider extends ImageView {

 
 public HideSlider(final Context context, AttributeSet attrs) {
  super(context, attrs);
  
  ImageView image = (ImageView) findViewById(R.id.slider_hide);
  image.setImageResource(R.drawable.ic_notify_quicksettings);
  
   SharedPreferences sharedPreferences = context.getSharedPreferences("EvoPrefsFile",Context.MODE_PRIVATE);    
   Boolean SliderVisibility = sharedPreferences.getBoolean("SliderVisibility",false);
     if (SliderVisibility == true){
    	  image.setSelected(false);
     }
     else{
    	  image.setSelected(true); 
     }

	image.setOnClickListener(new View.OnClickListener() {
		 
		@Override
		public void onClick(View v) {
	        if (v.isSelected()){
	            v.setSelected(false);
				Intent intent = new Intent();
				intent.setAction("com.b16h22.statusbar.HIDE_SLIDER");
				context.sendBroadcast(intent);
                SharedPreferences sharedPreferences = context.getSharedPreferences("EvoPrefsFile",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit(); //opens the editor
                editor.putBoolean("SliderVisibility", true); //true or false
                editor.commit();
	        } else {
	            v.setSelected(true);
				Intent intent = new Intent();
				intent.setAction("com.b16h22.statusbar.UNHIDE_SLIDER");
				context.sendBroadcast(intent);
                SharedPreferences sharedPreferences = context.getSharedPreferences("EvoPrefsFile",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit(); //opens the editor
                editor.putBoolean("SliderVisibility", false); //true or false
                editor.commit();
	        }		};
	});
 }

}

