package com.b16h22.statusbar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class Toggles extends LinearLayout {
	
	LinearLayout toggles;
	
	 public Toggles(final Context context, AttributeSet attrs) {
		  super(context, attrs);
			 toggles = (LinearLayout) findViewById(R.id.toggle_layout);	  

	 	    SharedPreferences sharedPreferences = context.getSharedPreferences("EvoPrefsFile",Context.MODE_PRIVATE);    
	 	    Boolean ToggleVisibility = sharedPreferences.getBoolean("ToggleVisibility",false);
	 	      if (ToggleVisibility == false){
            	  toggles.setVisibility(VISIBLE);
	 	      }
	 	      else{
            	  toggles.setVisibility(GONE); 
	 	      }
          
          BroadcastReceiver mReceiver = new BroadcastReceiver() {
              @Override
              public void onReceive(Context c, Intent i) {
            	  toggles.setVisibility(GONE);
                  SharedPreferences sharedPreferences = context.getSharedPreferences("EvoPrefsFile",Context.MODE_PRIVATE);
                  SharedPreferences.Editor editor = sharedPreferences.edit(); //opens the editor
                  editor.putBoolean("ToggleVisibility", true); //true or false
                  editor.commit();	 
              }
              
          }; 
          BroadcastReceiver mReceiver1 = new BroadcastReceiver() {
              @Override
              public void onReceive(Context c, Intent i) {
            	  toggles.setVisibility(VISIBLE);
                  SharedPreferences sharedPreferences = context.getSharedPreferences("EvoPrefsFile",Context.MODE_PRIVATE);
                  SharedPreferences.Editor editor = sharedPreferences.edit(); //opens the editor
                  editor.putBoolean("ToggleVisibility", false); //true or false
                  editor.commit();	 
              }
              
          };        
          context.registerReceiver(mReceiver, new IntentFilter("com.b16h22.statusbar.HIDE_TOGGLE")); 
          context.registerReceiver(mReceiver1, new IntentFilter("com.b16h22.statusbar.UNHIDE_TOGGLE"));
	 }	
}

