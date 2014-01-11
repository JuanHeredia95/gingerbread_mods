package com.b16h22.statusbar;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ClockHolo extends LinearLayout {
	
 private TextView hour;
 private TextView minute;
 private TextView ampm;
 private TextView semicolumn;
 private SimpleDateFormat hr;
 private SimpleDateFormat min;
 private SimpleDateFormat am;
 private String h;
 private String m;
 private String a;
 private String clockStyle;
 private String message;
 private Calendar cal;
 private BroadcastReceiver mReceiverClock;
 private BroadcastReceiver mAmpmReceiver;
 
	 public ClockHolo(final Context context, AttributeSet attrs) {
		  super(context, attrs);

            hour =new TextView (context);
            minute =new TextView (context);
            ampm =new TextView (context);
            semicolumn = new TextView (context);
  		  	this.addView(hour);
  		  	this.addView(semicolumn);
  		  	this.addView(minute);
  		  	this.addView(ampm);
  		  	
  		  	hour.setTextSize(TypedValue.COMPLEX_UNIT_SP, 33);
  		  	minute.setTextSize(TypedValue.COMPLEX_UNIT_SP, 33);
  		  	semicolumn.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
  		  	ampm.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
  		  	
  		  	semicolumn.setText(":");
  		  	
  		   SharedPreferences sharedPreferences = context.getSharedPreferences("EvoPrefsFile",Context.MODE_PRIVATE); 
      	   String am = sharedPreferences.getString("expanded_ampm", "false");

  		   
  		   if ("true".equals(am)) {
  			 ampm.setVisibility(VISIBLE);
  		   } else if ("false".equals(am)){
  			 ampm.setVisibility(GONE); 
  		   }

  		   String clock = sharedPreferences.getString("expandedClockStyle", "thin");
  		   
      	   if("boldThin".equals(clock)) {
      		   hour.setTypeface(null, Typeface.BOLD);
      		   hour.setPadding(0, -1, 0, 0);
      		   semicolumn.setTypeface(Typeface.MONOSPACE);
      		   minute.setTypeface(Typeface.MONOSPACE);
      		   ampm.setTypeface(Typeface.MONOSPACE);
      	   }
      	   		else if ("thin".equals(clock)){
      			   hour.setTypeface(Typeface.MONOSPACE);
      			   hour.setPadding(0, 0, 0, 0);
      			   semicolumn.setTypeface(Typeface.MONOSPACE);
      			   minute.setTypeface(Typeface.MONOSPACE);
      			   ampm.setTypeface(Typeface.MONOSPACE);
      		   }
			
      		   else{
      			   hour.setTypeface(null, Typeface.BOLD);
      			   semicolumn.setTypeface(null, Typeface.BOLD);
      			   minute.setTypeface(null, Typeface.BOLD);
      			   ampm.setTypeface( Typeface.MONOSPACE);
      		   }
      	   
  		  	
  	        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
  	        lp.gravity= Gravity.BOTTOM; 
  	        ampm.setLayoutParams(lp);
  		  	 ampm.setPadding(1, 0, 0, 0);
  		  	
        
              final Handler h = new Handler();
              h.post(new Runnable() {
                  @Override
                  public void run() {
                      updateTime();
                      h.postDelayed(this, 1000);
                  }
              }); 
	 
              this.setOnClickListener(new View.OnClickListener() {
 				 
  				@Override
  				public void onClick(View v) {
					Intent intent = new Intent();
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					intent.setComponent(new ComponentName("com.rahul.evo", "com.rahul.evo.ExpandedClock"));
					v.getContext().startActivity(intent);
  				    try{ 
  				    	   Object service  = context.getSystemService("statusbar");
  				    	   Class<?> statusbarManager = Class.forName("android.app.StatusBarManager");
  				    	   Method collapse = statusbarManager.getMethod("collapse");
  				    	   collapse.invoke(service);
  				    	 }
  				    	 catch(Exception ex){           

  				    	 }
  		        }
  		}); 
	 
              mReceiverClock = new BroadcastReceiver() {
                  @Override
                  public void onReceive(Context c, Intent i) {
                	    clockStyle = i.getStringExtra("expandedClockStyle");
    	            	
    		            SharedPreferences sharedPreferences = context.getSharedPreferences("EvoPrefsFile",Context.MODE_PRIVATE);
    		            SharedPreferences.Editor editor = sharedPreferences.edit();
    		            editor.putString("expandedClockStyle", clockStyle); 
    		            editor.commit();
    		            setTypeface();
                 
                  }

              };
              
              mAmpmReceiver = new BroadcastReceiver() {
                  @Override
                  public void onReceive(Context c, Intent i) {
                	    message = i.getStringExtra("expandedAmpm");
    	            	
    		            SharedPreferences sharedPreferences = context.getSharedPreferences("EvoPrefsFile",Context.MODE_PRIVATE);
    		            SharedPreferences.Editor editor = sharedPreferences.edit();
    		            editor.putString("expanded_ampm", message); 
    		            editor.commit();
    		            ampm();
    		            
                 
                  }

              };
	 }
	 

 

	private void updateTime() {
		  cal = Calendar.getInstance();
		  hr = new SimpleDateFormat("h");
          am = new SimpleDateFormat("aa");
          min = new SimpleDateFormat("mm");
          h = hr.format(cal.getTime());
          m = min.format(cal.getTime());
          a = am.format(cal.getTime());
		  hour.setText(h);
		  minute.setText(m);
		  ampm.setText(a);

		
	}
	
	private void setTypeface(){
		
		if("boldThin".equals(clockStyle)) {
		  	hour.setTypeface(null, Typeface.BOLD);
		  	hour.setPadding(0, -1, 0, 0);
		  	semicolumn.setTypeface(Typeface.MONOSPACE);
		  	minute.setTypeface(Typeface.MONOSPACE);
		  	ampm.setTypeface(Typeface.MONOSPACE);
		} else {
			if ("thin".equals(clockStyle)){
				hour.setTypeface(Typeface.MONOSPACE);
				hour.setPadding(0, 0, 0, 0);
			  	semicolumn.setTypeface(Typeface.MONOSPACE);
			  	minute.setTypeface(Typeface.MONOSPACE);
			  	ampm.setTypeface(Typeface.MONOSPACE);
			}
			
			else{
				hour.setTypeface(null, Typeface.BOLD);
			  	semicolumn.setTypeface(null, Typeface.BOLD);
			  	minute.setTypeface(null, Typeface.BOLD);
			  	ampm.setTypeface( Typeface.MONOSPACE);
			}
		}
	}
	
	
	private void ampm(){
		if ("true".equals(message)) {
			ampm.setVisibility(VISIBLE);
		} else {
			ampm.setVisibility(GONE);
		}
	}
	
    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        getContext().registerReceiver(mReceiverClock, new IntentFilter("EXPANDED_CLOCK_STYLE"));
        getContext().registerReceiver(mAmpmReceiver, new IntentFilter("EXPANDED_AM_PM"));

    }
    
    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        getContext().unregisterReceiver(mReceiverClock);
        getContext().unregisterReceiver(mAmpmReceiver);

    }
}
