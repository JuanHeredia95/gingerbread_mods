package com.b16h22.statusbar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

public class ShortcutBackground extends View {
	
    private View shortcutBackground;
    String toggleColor;
	public ShortcutBackground(final Context context, AttributeSet attrs) {
	super(context, attrs); 
	
	
	 shortcutBackground = (View) findViewById(R.id.shortcut_bg);
	 
	     SharedPreferences sharedPreferences = context.getSharedPreferences("EvoPrefsFile",Context.MODE_PRIVATE);    
	     toggleColor = sharedPreferences.getString("toggleBgColor","#ff161616");
		 shortcutBackground.setBackgroundColor(Color.parseColor(toggleColor));  

    BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context c, Intent i) {
        	toggleColor = i.getStringExtra("shortcutBackgroundColor");
	        shortcutBackground.setBackgroundColor(Color.parseColor(toggleColor));
            SharedPreferences sharedPreferences = context.getSharedPreferences("EvoPrefsFile",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit(); //opens the editor
            editor.putString("toggleBgColor", toggleColor); //true or false
            editor.commit();	 
        }
        
    };      
    
    context.registerReceiver(mReceiver, new IntentFilter("com.b16h22.statusbar.CHANGE_TOGGLE_BACKGROUND"));   
	}


}
