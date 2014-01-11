package com.b16h22.statusbar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class Expanded extends LinearLayout {
	
    private LinearLayout ln;
    String message;
    String layoutType;
    
	public Expanded(final Context context, AttributeSet attrs) {
	super(context, attrs); 
	
	  final float scale = getContext().getResources().getDisplayMetrics().density;
	  final int bottom_phab = (int) (20 * scale + 0.5f);
	  final int bottom_tab = (int) (15 * scale + 0.5f);
	  final int left_tab = (int) (8 * scale + 0.5f);
	  final int left_phab = (int) (15 * scale + 0.5f);
	  final int right_phab = (int) (15 * scale + 0.5f);	  
	  final int right = (int) (0 * scale + 0.5f);
	  final int top = (int) (-0.1 * scale + 0.5f);
	  final int mSide = (int) (-0.1 * scale + 0.5f);
	
	 ln = (LinearLayout) findViewById(R.id.expanded);

	  
	     SharedPreferences sharedPreferences = context.getSharedPreferences("EvoPrefsFile",Context.MODE_PRIVATE);    
	     layoutType = sharedPreferences.getString("type","phablet");
	     	if ("tablet".equals(layoutType )) {
			    ln.setPadding(left_tab, 51, right, bottom_tab);
	    	}
	    	else if ("phablet".equals(layoutType )) {

	    		ln.setPadding(left_phab, 0, right_phab, bottom_phab);
	    	} 
	    	else if ("normal".equals(layoutType)) {
	    		ln.setPadding(0, 0, 0, 0);
	    	}

    BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context c, Intent i) {
        	message = i.getStringExtra("layoutType");
        	
        	if ("tablet".equals(message )) {
        		ln.setPadding(left_tab, 51, right, bottom_tab);
        	}
        	else if ("phablet".equals(message )) {
        		ln.setPadding(left_phab, top, right_phab, bottom_phab);
        	}
	    	else if ("normal".equals(message)) {
	    		ln.setPadding(mSide, top, mSide, 0);
	    	}
        }
        
    };      
    
    context.registerReceiver(mReceiver, new IntentFilter("com.b16h22.statusbar.CHANGE_LAYOUT"));   
	}


}
