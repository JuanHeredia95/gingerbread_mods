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

public class Date extends TextView {

	TextView textView;
	
	public Date(final Context context, AttributeSet attrs) {
	super(context, attrs);	
	
	textView = (TextView) findViewById(R.id.dateView);
	
	SharedPreferences sharedPreferences = context.getSharedPreferences("EvoPrefsFile",Context.MODE_PRIVATE); 
   
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

    context.registerReceiver(mReceiverLayout, new IntentFilter("com.b16h22.statusbar.CHANGE_LAYOUT"));
	}
	protected void updateTime(final Context context) {
		  textView = (TextView) findViewById(R.id.dateView);
		  Calendar cal = Calendar.getInstance();
		  SimpleDateFormat month_date = new SimpleDateFormat("MMMMMMMMM");
		  SimpleDateFormat year_date = new SimpleDateFormat("yyyy");
		  SimpleDateFormat day_date = new SimpleDateFormat("dd");
          String day = day_date.format(cal.getTime());
          String mon = month_date.format(cal.getTime());
          String year = year_date.format(cal.getTime());
          textView.setText(mon.toUpperCase()+" "+day+", "+year);
          }
	
}
