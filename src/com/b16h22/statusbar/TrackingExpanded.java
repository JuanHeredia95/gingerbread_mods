package com.b16h22.statusbar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class TrackingExpanded extends LinearLayout {
	
    private LinearLayout ln;
    String message;
    String layoutType;
    
	public TrackingExpanded(final Context context, AttributeSet attrs) {
	super(context, attrs); 
	
	  final float scale = getContext().getResources().getDisplayMetrics().density;
	  final int left_tab = (int) (9.3 * scale + 0.5f);
	  final int right_tab = (int) (9.3 * scale + 0.5f);
	  final int bottom_tab = (int) (8.2 * scale + 0.5f);	  
	
	 ln = (LinearLayout) findViewById(R.id.tracking_expanded);

	  
	     SharedPreferences sharedPreferences = context.getSharedPreferences("EvoPrefsFile",Context.MODE_PRIVATE);    
	     layoutType = sharedPreferences.getString("type","phablet");
	     	if ("tablet".equals(layoutType )) {
			    ln.setPadding(left_tab, 0, right_tab, bottom_tab);
	    	}
	    	else if ("phablet".equals(layoutType )) {

	    		ln.setPadding(0, 0, 0, 0);
	    	} 
	    	else if ("normal".equals(layoutType)) {
	    		ln.setPadding(0, 0, 0, 0);
	    	}

    BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context c, Intent i) {
        	message = i.getStringExtra("layoutType");
        	
        	if ("tablet".equals(message )) {
        		ln.setPadding(left_tab, 0, right_tab, bottom_tab);
        	}
        	else if ("phablet".equals(message )) {
        		ln.setPadding(0, 0, 0, 0);
        	}
	    	else if ("normal".equals(message)) {
	    		ln.setPadding(0, 0, 0, 0);
	    	}
        }
        
    };      
    
    context.registerReceiver(mReceiver, new IntentFilter("com.b16h22.statusbar.CHANGE_LAYOUT"));   
	}


}
