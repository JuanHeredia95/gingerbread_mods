package com.b16h22.statusbar;

import java.util.HashMap;
import java.util.Map;

import android.media.AudioManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;

public class MediaVolumeSlider extends LinearLayout {
	
    public static final String my_pref = "MyPrefsFile";
	 
	 public MediaVolumeSlider(final Context context, AttributeSet attrs) {
		  super(context, attrs);
		  
		  
		    final ImageView media =new ImageView(context);
		    Map<String, Integer> map = new HashMap<String, Integer>();
		    map.put("img", R.drawable.ic_volume_media);
		    LayoutParams icon = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		    icon.gravity=Gravity.CENTER_VERTICAL;
		    media.setLayoutParams(icon);
		    media.setImageResource(map.get("img"));
		    this.addView(media);
		    
		    final AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		    int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		    int curVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		    final SeekBar volControl =new SeekBar(context);
		    SharedPreferences sharedPreferences = context.getSharedPreferences(my_pref, Context.MODE_PRIVATE);
		    
		    boolean value = sharedPreferences.getBoolean("isChecked", false);
            if (value) {   
	            volControl.setEnabled(false);
            }
            else {
            	volControl.setEnabled(true);
            }   
		    
		    this.addView(volControl);		    
		    volControl.setMax(maxVolume);
		    volControl.setProgress(curVolume);
		    
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
		            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, arg1, 0);
		        }
		    });
		    
		    final CheckBox check =new CheckBox(context);
		    this.addView(check);
		    LayoutParams checkBox = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);		    
	        check.setLayoutParams(checkBox);		    
		    check.setChecked(sharedPreferences.getBoolean("isChecked", false));
		    check.setOnCheckedChangeListener(new OnCheckedChangeListener() {

		            @Override
		            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		            if (isChecked) {   
			            volControl.setEnabled(false);
			            SharedPreferences sharedPreferences = context.getSharedPreferences(my_pref, Context.MODE_PRIVATE);
			            SharedPreferences.Editor editor = sharedPreferences.edit(); //opens the editor
			            editor.putBoolean("isChecked", true); //true or false
			            editor.commit();
		            }
		            else {
		            	volControl.setEnabled(true);
			            SharedPreferences sharedPreferences = context.getSharedPreferences(my_pref, Context.MODE_PRIVATE);
			            SharedPreferences.Editor editor = sharedPreferences.edit(); //opens the editor
			            editor.putBoolean("isChecked", false); //true or false
			            editor.commit();
		            }
		            }   
		        });

	 }

	}
