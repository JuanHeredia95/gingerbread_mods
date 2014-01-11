package com.b16h22.statusbar;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import android.media.AudioManager;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;

public class BrightnessSlider extends LinearLayout {
	
    public static final String my_pref = "MyPrefsFile";
    float BackLightValue = 0.5f; 
	 public BrightnessSlider(final Context context, AttributeSet attrs) {
		  super(context, attrs);
		  
		  
		    final ImageView media =new ImageView(context);
		    Map<String, Integer> map = new HashMap<String, Integer>();
		    map.put("img", R.drawable.ic_volume_ringer);
		    LayoutParams icon = new LayoutParams(50,50);
		    icon.gravity=Gravity.CENTER_VERTICAL;
		    media.setLayoutParams(icon);
		    media.setImageResource(map.get("img"));
		    this.addView(media);
		    
		    final SeekBar volControl =new SeekBar(context);
		    SharedPreferences sharedPreferences = context.getSharedPreferences(my_pref, Context.MODE_PRIVATE);
		    
		    boolean value = sharedPreferences.getBoolean("isChecked5", false);
            if (value) {   
	            volControl.setEnabled(false);
            }
            else {
            	volControl.setEnabled(true);
            }   
		    
		    this.addView(volControl);		    
		    volControl.setMax(100);
		    volControl.setProgress(100);
		    
		    LayoutParams seekbar = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		    seekbar.gravity=Gravity.CENTER_VERTICAL;
		    seekbar.weight = 1f;
	        volControl.setLayoutParams(seekbar);		    
		    volControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
		        @Override
		        public void onStopTrackingTouch(SeekBar arg0) {
		        }

		        @Override
		        public void onStartTrackingTouch(SeekBar arg0) {
		        }

		        @Override
		        public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {

					    try{ 
					    	   Object service  = context.getSystemService("power");
					    	   Class<?> ipowermanager = Class.forName("android.os.IPowerManager");
					    	   Method brightness = ipowermanager.getMethod("setBacklightBrightness");
					           brightness.invoke(service, new Object[]{arg1});
					    }
					    	 catch(Exception ex){           

					    	 }
		        }
		    });
		    
		    final CheckBox check =new CheckBox(context);
		    this.addView(check);
		    LayoutParams checkBox = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);		    
	        check.setLayoutParams(checkBox);		    
		    check.setChecked(sharedPreferences.getBoolean("isChecked5", false));
		    check.setOnCheckedChangeListener(new OnCheckedChangeListener() {

		            @Override
		            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked5) {
		            if (isChecked5) {   
			            volControl.setEnabled(false);
	 					Intent intent = new Intent();
	 					intent.setAction("com.b16h22.statusbar.CHANGE_LAYOUT");
	 					intent.putExtra("layoutType","phablet");
	 					context.sendBroadcast(intent);
			            SharedPreferences sharedPreferences = context.getSharedPreferences(my_pref, Context.MODE_PRIVATE);
			            SharedPreferences.Editor editor = sharedPreferences.edit(); //opens the editor
			            editor.putBoolean("isChecked5", true); //true or false
			            editor.commit();
		            }
		            else {
		            	volControl.setEnabled(true);
	 					Intent intent = new Intent();
	 					intent.setAction("com.b16h22.statusbar.CHANGE_LAYOUT");
	 					intent.putExtra("layoutType","normal");
	 					context.sendBroadcast(intent);
			            SharedPreferences sharedPreferences = context.getSharedPreferences(my_pref, Context.MODE_PRIVATE);
			            SharedPreferences.Editor editor = sharedPreferences.edit(); //opens the editor
			            editor.putBoolean("isChecked5", false); //true or false
			            editor.commit();
		            }
		            }   
		        });

	 }

	}
