package com.b16h22.statusbar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.widget.ImageView;

public class TrackingView extends ImageView {
	
    
    String message;
    String layoutType;
    ImageView tracking;
    BroadcastReceiver mReceiver;
    
	public TrackingView(final Context context, AttributeSet attrs) {
	super(context, attrs); 	
	
	tracking = this;
	  
	     SharedPreferences sharedPreferences = context.getSharedPreferences("EvoPrefsFile",Context.MODE_PRIVATE);    
	     layoutType = sharedPreferences.getString("type","phablet");
	     	if ("tablet".equals(layoutType )) {
	     		tracking.setBackgroundResource(R.drawable.status_bar_close_on);
	    	}
	    	else if ("phablet".equals(layoutType )) {
	    		tracking.setBackgroundResource(R.drawable.statusbar_close_jb);
	    	} 
	    	else if ("normal".equals(layoutType)) {
	    		tracking.setBackgroundResource(R.drawable.statusbar_close_jb);
	    	}

    mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context c, Intent i) {
        	message = i.getStringExtra("layoutType");
        	
        	if ("tablet".equals(message )) {
        		tracking.setBackgroundResource(R.drawable.status_bar_close_on);
        	}
        	else if ("phablet".equals(message )) {
        		tracking.setBackgroundResource(R.drawable.statusbar_close_jb);
        	}
	    	else if ("normal".equals(message)) {
	    		tracking.setBackgroundResource(R.drawable.statusbar_close_jb);
	    	}
        }
        
    };      
   
	}

	protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        getContext().registerReceiver(mReceiver, new IntentFilter("com.b16h22.statusbar.CHANGE_LAYOUT"));

    }
    
    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        getContext().unregisterReceiver(mReceiver);

    }
}
