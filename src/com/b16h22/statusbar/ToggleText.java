package com.b16h22.statusbar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.TextView;

public class ToggleText extends TextView {

	TextView toggleText;
	String textColor;
	public ToggleText (final Context context, AttributeSet attrs) {
		super(context, attrs); 
		
		toggleText = (TextView) findViewById(R.id.toggle_text);
	     SharedPreferences sharedPreferences = context.getSharedPreferences("EvoPrefsFile",Context.MODE_PRIVATE);    
	     textColor = sharedPreferences.getString("toggleTextColor","#ffffffff");
		 toggleText.setTextColor(Color.parseColor(textColor));  

   BroadcastReceiver mReceiver = new BroadcastReceiver() {
       @Override
       public void onReceive(Context c, Intent i) {
       	   textColor = i.getStringExtra("toggleTextColor");
	       toggleText.setTextColor(Color.parseColor(textColor));
           SharedPreferences sharedPreferences = context.getSharedPreferences("EvoPrefsFile",Context.MODE_PRIVATE);
           SharedPreferences.Editor editor = sharedPreferences.edit(); //opens the editor
           editor.putString("toggleTextColor", textColor); //true or false
           editor.commit();	 
       }
       
   };      
   
   context.registerReceiver(mReceiver, new IntentFilter("com.b16h22.statusbar.CHANGE_TOGGLETEXT_COLOR"));   
	}
	}




