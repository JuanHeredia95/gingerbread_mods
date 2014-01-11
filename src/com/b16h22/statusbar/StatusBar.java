package com.b16h22.statusbar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class StatusBar extends LinearLayout {
	
    private LinearLayout statBar;
    String message;
    String line;
    BroadcastReceiver mReceiver;
	public StatusBar(final Context context, AttributeSet attrs) {
	super(context, attrs); 
	
	
	 statBar = (LinearLayout) findViewById(R.id.status_bar);
	 final ImageView leftregion = new ImageView(context);
	 final ImageView rightregion = new ImageView(context);
	 
	 LayoutParams view1 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT);
	 view1.weight = 1f;
	 LayoutParams view2 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT);
	 view2.weight = 1f;
	 
	 leftregion.setLayoutParams(view1);
	 rightregion.setLayoutParams(view2);
	 
     this.addView(leftregion);
     this.addView(rightregion);
	 
	     SharedPreferences sharedPreferences = context.getSharedPreferences("EvoPrefsFile",Context.MODE_PRIVATE);    
	     message = sharedPreferences.getString("statbarColor","#ff000000");
		 statBar.setBackgroundColor(Color.parseColor(message));  

		 leftregion.setOnTouchListener(new OnTouchListener()
		 {
			    @Override
			    public boolean onTouch(View v, MotionEvent event) {
					Intent intent = new Intent();
					intent.setAction("com.b16h22.statusbar.FLIP_TO_NOTIF");
					context.sendBroadcast(intent);
			            return true;
			    }
		 });
		 rightregion.setOnTouchListener(new OnTouchListener()
		 {
			    @Override
			    public boolean onTouch(View v, MotionEvent event) {
					Intent intent = new Intent();
					intent.setAction("com.b16h22.statusbar.FLIP_TO_TOGGLES");
					context.sendBroadcast(intent);
			            return true;
			    }
			    
		 });
		 

         
     mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context c, Intent i) {
        	message = i.getStringExtra("statbarColor");
	        statBar.setBackgroundColor(Color.parseColor(message));
            SharedPreferences sharedPreferences = context.getSharedPreferences("EvoPrefsFile",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit(); //opens the editor
            editor.putString("statbarColor", message); //true or false
            editor.commit();	 
        }
        
    };      
    

	}
	
    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        getContext().registerReceiver(mReceiver, new IntentFilter("com.b16h22.statusbar.CHANGE_STATUSBAR_BACKGROUND"));   
	}

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        getContext().unregisterReceiver(mReceiver);

    }

		
}
