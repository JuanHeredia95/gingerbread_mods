package com.b16h22.statusbar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

public class Pulldown extends View {
	
    private View viewchild;
    String message;
	public Pulldown(final Context context, AttributeSet attrs) {
	super(context, attrs); 
	
	
	 viewchild = (View) findViewById(R.id.view);
	 
	     SharedPreferences sharedPreferences = context.getSharedPreferences("EvoPrefsFile",Context.MODE_PRIVATE);    
	     message = sharedPreferences.getString("backgroundColor","#f9111111");
		 viewchild.setBackgroundColor(Color.parseColor(message));  

    BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context c, Intent i) {
        	message = i.getStringExtra("color");
	        viewchild.setBackgroundColor(Color.parseColor(message));
            SharedPreferences sharedPreferences = context.getSharedPreferences("EvoPrefsFile",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit(); //opens the editor
            editor.putString("backgroundColor", message); //true or false
            editor.commit();	 
        }
        
    };      
    
    context.registerReceiver(mReceiver, new IntentFilter("com.b16h22.statusbar.CHANGE_BACKGROUND"));   
	}


}
