package com.b16h22.statusbar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.widget.TextView;

public class ProfileName extends TextView {
	
	TextView profilename;
    String name;
	public ProfileName(final Context context, AttributeSet attrs) {
	super(context, attrs); 
	
	
	profilename = (TextView) findViewById(R.id.profile_name);
	
	     profilename.setTextSize(15);
	     SharedPreferences sharedPreferences = context.getSharedPreferences("EvoPrefsFile",Context.MODE_PRIVATE);    
	     name = sharedPreferences.getString("profileName","Owner");
     	 profilename.setText(name);


    BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context c, Intent i) {
        	name = i.getStringExtra("NAME");
        	profilename.setText(name);
            SharedPreferences sharedPreferences = context.getSharedPreferences("EvoPrefsFile",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit(); //opens the editor
            editor.putString("profileName", name); //true or false
            editor.commit();	 
        }
        
    };      
    
    context.registerReceiver(mReceiver, new IntentFilter("com.b16h22.statusbar.CHANGE_PROFILE_NAME"));  
	}


}
