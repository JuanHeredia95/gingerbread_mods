package com.b16h22.statusbar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;

public class DatePanel extends View {
	
    private View viewchild;
    String color1;
    String color2;
	public DatePanel(final Context context, AttributeSet attrs) {
	super(context, attrs); 
	
	
	 viewchild = (View) findViewById(R.id.datepanel);
	 
	     SharedPreferences sharedPreferences = context.getSharedPreferences("EvoPrefsFile",Context.MODE_PRIVATE);    
	     color1 = "#ff000000";
	     color2 = sharedPreferences.getString("datepanelColor","#e8000000");
	     GradientDrawable datePanel = new GradientDrawable(
	             GradientDrawable.Orientation.TOP_BOTTOM,
	             new int[] {Color.parseColor(color1),Color.parseColor(color2)});
	     datePanel.setShape(GradientDrawable.RECTANGLE);
		 viewchild.setBackgroundDrawable(datePanel);  

    BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context c, Intent i) {
        	color1 = "#ff000000";
        	color2 = i.getStringExtra("color");
   	        GradientDrawable datePanel = new GradientDrawable(
	             GradientDrawable.Orientation.TOP_BOTTOM,
	             new int[] {Color.parseColor(color1),Color.parseColor(color2)});
	        datePanel.setShape(GradientDrawable.RECTANGLE);
		    viewchild.setBackgroundDrawable(datePanel);
            SharedPreferences sharedPreferences = context.getSharedPreferences("EvoPrefsFile",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit(); //opens the editor
            editor.putString("datepanelColor", color2);//true or false
            editor.commit();	 
        }
        
    };      
    
    context.registerReceiver(mReceiver, new IntentFilter("com.b16h22.statusbar.CHANGE_BACKGROUND"));   
	}


}