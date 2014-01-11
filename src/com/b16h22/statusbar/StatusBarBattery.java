package com.b16h22.statusbar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.BatteryManager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class StatusBarBattery extends LinearLayout {
	
	int level;
	private BroadcastReceiver mBatInfoReceiver;
	private BroadcastReceiver mBatHideReceiver;
	private BroadcastReceiver mBatUnhideReceiver;
	private BroadcastReceiver mBatStyleReceiver;
	
	 public StatusBarBattery(final Context context, AttributeSet attrs) {
		  super(context, attrs);
		  

          final ImageView battery =new ImageView(context);
          final TextView batteryText =new TextView (context);

	        
		  LayoutParams text = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		  text.gravity=Gravity.CENTER_VERTICAL;
		  batteryText.setLayoutParams(text);
		  batteryText.setTextSize(13);
		  batteryText.setTextColor(Color.parseColor("#ff3792b4"));
          this.addView(batteryText);
          this.addView(battery);

          
          mBatInfoReceiver = new BroadcastReceiver() {
              @Override
              public void onReceive(Context c, Intent i) {
                  level = i.getIntExtra("level", 0);
              	  battery.setImageLevel(level);
              	  
                  final int circle = R.drawable.stat_battery_circle;
                  final int circleCharging = R.drawable.stat_battery_circle_charging;
                  final int stick = R.drawable.stat_battery_stick;
                  final int stickCharging = R.drawable.stat_battery_stick_charging;
                  
              	  SharedPreferences sharedPreferences = context.getSharedPreferences("EvoPrefsFile",Context.MODE_PRIVATE); 
              	  final Boolean batteryVisibility = sharedPreferences.getBoolean("mainBattery", true);
              	  String batteryStyle = sharedPreferences.getString("mainBatteryStyle", "normal");
    	        
                  int status = i.getIntExtra(BatteryManager.EXTRA_STATUS, 0);
                  boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                                        status == BatteryManager.BATTERY_STATUS_FULL;
                  if (isCharging == true){
                	  
          	        if (batteryVisibility== false){
          	        		battery.setVisibility(GONE);
          	        		batteryText.setVisibility(GONE);
          		        }
      	        	  else {
        	        	  battery.setImageLevel(level);
                    	 if ("circle" .equals(batteryStyle)){
                            	battery.setVisibility(VISIBLE);
                            	batteryText.setVisibility(GONE);
                            	battery.setImageResource(circleCharging);
                            	
                     	} else {
                       	 if ("stick" .equals(batteryStyle)){
                              	battery.setVisibility(VISIBLE);
                              	batteryText.setVisibility(VISIBLE);
                              	battery.setImageResource(stickCharging);
                              	
                       	    }
                          	else{
                             		battery.setVisibility(GONE);
                             	}
                     	}

        		        } 
                  }
                  else {
                	  
            	        if (batteryVisibility== false){
          	        		battery.setVisibility(GONE);
          	        		batteryText.setVisibility(GONE);
          		        }
          	        	else {
          	        	  battery.setImageLevel(level);
                      	 if ("circle" .equals(batteryStyle)){
                              	battery.setVisibility(VISIBLE);
                              	batteryText.setVisibility(GONE);
                              	battery.setImageResource(circle);
                              	
                       	} else {
                         	 if ("stick" .equals(batteryStyle)){
                                	battery.setVisibility(VISIBLE);
                                	batteryText.setVisibility(VISIBLE);
                                	battery.setImageResource(stick);
                                	
                         	    }
                            	else{
                               		battery.setVisibility(GONE);
                               	}
                       	}

          		        } 

                  }
                  
                  
                  batteryText.setText(Integer.toString(level));
             
              }

          };
          
          mBatHideReceiver = new BroadcastReceiver() {
              @Override

              public void onReceive(Context c, Intent i) {
		            SharedPreferences sharedPreferences = context.getSharedPreferences("EvoPrefsFile",Context.MODE_PRIVATE);
		            SharedPreferences.Editor editor = sharedPreferences.edit(); //opens the editor
		            editor.putBoolean("mainBattery", false); //true or false
		            editor.commit();
             
              }

          };
          
          mBatUnhideReceiver = new BroadcastReceiver() {
              @Override
              public void onReceive(Context c, Intent i) {
		            SharedPreferences sharedPreferences = context.getSharedPreferences("EvoPrefsFile",Context.MODE_PRIVATE);
		            SharedPreferences.Editor editor = sharedPreferences.edit(); //opens the editor
		            editor.putBoolean("mainBattery", true); //true or false
		            editor.commit();
             
              }

          };
              
          mBatStyleReceiver = new BroadcastReceiver() {
              @Override
              public void onReceive(Context c, Intent i) {
            	    String message = i.getStringExtra("batteryStyle");
	            	
		            SharedPreferences sharedPreferences = context.getSharedPreferences("EvoPrefsFile",Context.MODE_PRIVATE);
		            SharedPreferences.Editor editor = sharedPreferences.edit();
		            editor.putString("mainBatteryStyle", message); //true or false
		            editor.commit();
		            
             
              }

          };
          
	 }	
	    protected void onAttachedToWindow()
	    {
	        super.onAttachedToWindow();
	        getContext().registerReceiver(mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
	        getContext().registerReceiver(mBatHideReceiver, new IntentFilter("com.b16h22.statusbar.HIDE_MAIN_BATTERY"));
	        getContext().registerReceiver(mBatUnhideReceiver, new IntentFilter("com.b16h22.statusbar.UNHIDE_MAIN_BATTERY")); 
	        getContext().registerReceiver(mBatStyleReceiver, new IntentFilter("BATTERY_STYLE"));
	    }
	    
	    protected void onDetachedFromWindow()
	    {
	        super.onDetachedFromWindow();
	        getContext().unregisterReceiver(mBatInfoReceiver);
	        getContext().unregisterReceiver(mBatHideReceiver);
	        getContext().unregisterReceiver(mBatUnhideReceiver);
	        getContext().unregisterReceiver(mBatStyleReceiver);
	    }
}

