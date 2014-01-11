package com.b16h22.statusbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import android.os.Handler;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

public class Day extends TextView {

	 private TextView textView;	
	public Day(final Context context, AttributeSet attrs) {
	super(context, attrs);	
	
	textView = (TextView) findViewById(R.id.dayView);
	
	SharedPreferences sharedPreferences = context.getSharedPreferences("EvoPrefsFile",Context.MODE_PRIVATE); 

    Boolean DayVisibility = sharedPreferences.getBoolean("dayVisibility", false);
    if (DayVisibility == false){
    	textView.setVisibility(VISIBLE);
    } else {
    	textView.setVisibility(GONE);
    }
    
    String layoutType = sharedPreferences.getString("type","phablet");
    	if ("tablet".equals(layoutType )) {
    		textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
    		textView.setTextColor(Color.parseColor("#ff6a6a6a"));
   	}
   	else if ("phablet".equals(layoutType )) {
		textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
		textView.setTextColor(Color.parseColor("#ffffffff"));
   	} 
   	else if ("normal".equals(layoutType )) {
		textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
		textView.setTextColor(Color.parseColor("#ffffffff"));
   	} 
    
    final Handler h = new Handler();
    h.post(new Runnable() {
        @Override
        public void run() {
            updateTime(context);
            h.postDelayed(this, 1000);
        }
    }); 
    BroadcastReceiver mclockHideReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context c, Intent i) {
        	textView.setVisibility(GONE);
            SharedPreferences sharedPreferences = context.getSharedPreferences("EvoPrefsFile",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit(); //opens the editor
            editor.putBoolean("dayVisibility", true); //true or false
            editor.commit();	 
        }
        
    };
    BroadcastReceiver mclockUnhideReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context c, Intent i) {
        	textView.setVisibility(VISIBLE);
            SharedPreferences sharedPreferences = context.getSharedPreferences("EvoPrefsFile",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit(); //opens the editor
            editor.putBoolean("dayVisibility", false); //true or false
            editor.commit();	 
        }
        
    };
    
    BroadcastReceiver mReceiverLayout = new BroadcastReceiver() {
        @Override
        public void onReceive(Context c, Intent i) {
        	String message = i.getStringExtra("layoutType");
        	
        	if ("tablet".equals(message )) {
        		textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
        		textView.setTextColor(Color.parseColor("#ff6a6a6a"));
        	}
        	else if ("phablet".equals(message )) {
        		textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
        		textView.setTextColor(Color.parseColor("#ffffffff"));
        	}
          	else if ("normal".equals(message )) {
        		textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
        		textView.setTextColor(Color.parseColor("#ffffffff"));
           	}
        }
        
    };     
    context.registerReceiver(mclockHideReceiver, new IntentFilter("com.b16h22.statusbar.HIDE_DAY"));
    context.registerReceiver(mclockUnhideReceiver, new IntentFilter("com.b16h22.statusbar.UNHIDE_DAY"));
    context.registerReceiver(mReceiverLayout, new IntentFilter("com.b16h22.statusbar.CHANGE_LAYOUT")); 
	}
	protected void updateTime(final Context context) {
		  Calendar cal = Calendar.getInstance();
		  SimpleDateFormat day_date = new SimpleDateFormat("EEEEEEEE");
          String day = day_date.format(cal.getTime());
          textView.setText(day.toUpperCase());
          }

}