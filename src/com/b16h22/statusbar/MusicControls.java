package com.b16h22.statusbar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class MusicControls extends LinearLayout {
	
	LinearLayout controls;
	BroadcastReceiver mReceiver;
	BroadcastReceiver mReceiver1;
	
	 public MusicControls(final Context context, AttributeSet attrs) {
		  super(context, attrs);
			 controls = this;	  

          
          mReceiver = new BroadcastReceiver() {
              @Override
              public void onReceive(Context c, Intent i) {
            	  controls.setVisibility(GONE);
              }
              
          }; 
          mReceiver1 = new BroadcastReceiver() {
              @Override
              public void onReceive(Context c, Intent i) {
            	  controls.setVisibility(VISIBLE);

              }
              
          };        
	 }	
	 protected void onAttachedToWindow()
	    {
	        super.onAttachedToWindow();
	        getContext().registerReceiver(mReceiver, new IntentFilter("SHRINK"));
	        getContext().registerReceiver(mReceiver1, new IntentFilter("EXPAND"));

	    }
	    
	    protected void onDetachedFromWindow()
	    {
	        super.onDetachedFromWindow();
	        getContext().unregisterReceiver(mReceiver1);
	        getContext().unregisterReceiver(mReceiver);

	    }
}

