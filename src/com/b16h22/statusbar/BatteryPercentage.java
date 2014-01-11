package com.b16h22.statusbar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BatteryPercentage extends LinearLayout {
	
	int level;
	String visibility;
	private BroadcastReceiver mBatInfoReceiver;
	private BroadcastReceiver mBatHideReceiver;
	private BroadcastReceiver mTextColorReceiver;

	
	 public BatteryPercentage(final Context context, AttributeSet attrs) {
		  super(context, attrs);
		  
		  final SharedPreferences sharedPreferences = context.getSharedPreferences("EvoPrefsFile",Context.MODE_PRIVATE); 
		  String color = sharedPreferences.getString("percColor", "#ffffffff");
          final TextView batteryText =new TextView (context);
          batteryText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
          this.addView(batteryText);
          
          batteryText.setTextColor(Color.parseColor(color));
          
          mBatInfoReceiver = new BroadcastReceiver() {
              @Override
              public void onReceive(Context c, Intent i) {
                  level = i.getIntExtra("level", 0);
              	  
              	  SharedPreferences sharedPreferences = context.getSharedPreferences("EvoPrefsFile",Context.MODE_PRIVATE); 
              	  final Boolean percVisibility = sharedPreferences.getBoolean("percentage", false);
                                    
                  batteryText.setText(Integer.toString(level) + "%");
                  
                  if(percVisibility == true){
                	  batteryText.setVisibility(VISIBLE);
                  }
                  if(percVisibility == false){
                	  batteryText.setVisibility(GONE);
                  }
              }

          };
          
          mBatHideReceiver = new BroadcastReceiver() {
              @Override

              public void onReceive(Context c, Intent i) {
            	  visibility = i.getStringExtra("percVisibility");
            	  
            	  sharedprefHide();
              }

          };
          
          mTextColorReceiver = new BroadcastReceiver() {
              @Override
              public void onReceive(Context c, Intent i) {
            	  String color = i.getStringExtra("color");
            	  batteryText.setTextColor(Color.parseColor(color));
            	  
                  SharedPreferences.Editor editor = sharedPreferences.edit();
                  editor.putString("percColor",color);
                  editor.commit();
              }

          };
          
	 }	
	    protected void onAttachedToWindow()
	    {
	        super.onAttachedToWindow();
	        getContext().registerReceiver(mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
	        getContext().registerReceiver(mBatHideReceiver, new IntentFilter("HIDE_PERCENTAGE"));
	        getContext().registerReceiver(mTextColorReceiver, new IntentFilter("PERCENTAGE_COLOR")); 
	    }
	    
	    protected void onDetachedFromWindow()
	    {
	        super.onDetachedFromWindow();
	        getContext().unregisterReceiver(mBatInfoReceiver);
	        getContext().unregisterReceiver(mBatHideReceiver);
	        getContext().unregisterReceiver(mTextColorReceiver);
	    }
	    
	    public void sharedprefHide()
	    {
            SharedPreferences sharedPreferences = getContext().getSharedPreferences("EvoPrefsFile",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
      	  if ("visible".equals(visibility)){
              editor.putBoolean("percentage", true);
    	  }
    	  if ("hidden".equals(visibility)){
              editor.putBoolean("percentage", false);
    	  }	
    	  editor.commit();
	    }

}


