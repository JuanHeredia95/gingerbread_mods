package com.b16h22.statusbar;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class AokpToggles extends LinearLayout {
	
	LinearLayout toggles;
	
	 public AokpToggles(final Context context, AttributeSet attrs) {
		  super(context, attrs);
			 toggles = (LinearLayout) findViewById(R.id.toggle_aokp);	  

	 	    SharedPreferences sharedPreferences = context.getSharedPreferences("EvoPrefsFile",Context.MODE_PRIVATE);    
	 	    Boolean AokpToggleVisibility = sharedPreferences.getBoolean("AokpToggleVisibility",true);
	 	      if (AokpToggleVisibility == false){
            	  toggles.setVisibility(VISIBLE);
	 	      }
	 	      else{
            	  toggles.setVisibility(GONE); 
	 	      }
          
          BroadcastReceiver mReceiver = new BroadcastReceiver() {
              @Override
              public void onReceive(Context c, Intent i) {
            	  toggles.setVisibility(GONE); 
              }
              
          }; 
          BroadcastReceiver mReceiver1 = new BroadcastReceiver() {
              @Override
              public void onReceive(Context c, Intent i) {
            	  toggles.setVisibility(VISIBLE); 
              }
              
          };        
          context.registerReceiver(mReceiver, new IntentFilter("com.b16h22.statusbar.HIDE_AOKP_TOGGLE")); 
          context.registerReceiver(mReceiver1, new IntentFilter("com.b16h22.statusbar.UNHIDE_AOKP_TOGGLE"));
	 }
	 
	 
}


