package com.b16h22.statusbar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class Tracking extends RelativeLayout {
	
    private RelativeLayout ln;
    String message;
    String layoutType;
    ImageView tracking;
    
	public Tracking(final Context context, AttributeSet attrs) {
	super(context, attrs); 
	
	tracking = (ImageView)findViewWithTag("tracking_view");
	  final float scale = getContext().getResources().getDisplayMetrics().density;
	  final int bottom_phab = (int) (15 * scale + 0.5f);
	  final int bottom_tab = (int) (15 * scale + 0.5f);
	  final int left_tab = (int) (8 * scale + 0.5f);
	  final int left_phab = (int) (15 * scale + 0.5f);
	  final int right_phab = (int) (15 * scale + 0.5f);		  
	  final int right = (int) (0 * scale + 0.5f);
	
	 ln = (RelativeLayout) findViewById(R.id.tracking);

	  
	     SharedPreferences sharedPreferences = context.getSharedPreferences("EvoPrefsFile",Context.MODE_PRIVATE);    
	     layoutType = sharedPreferences.getString("type","phablet");
	     	if ("tablet".equals(layoutType )) {
			    ln.setPadding(left_tab, 124, right, bottom_tab);
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
        		ln.setPadding(left_tab, 124, right, bottom_tab);
                SharedPreferences sharedPreferences = context.getSharedPreferences("EvoPrefsFile",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit(); //opens the editor
                editor.putString("type", "tablet"); //true or false
                editor.commit();
        	}
        	else if ("phablet".equals(message )) {
        		ln.setPadding(left_phab, 0, right_phab, bottom_phab);
                SharedPreferences sharedPreferences = context.getSharedPreferences("EvoPrefsFile",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit(); //opens the editor
                editor.putString("type", "phablet"); //true or false
                editor.commit();
        	}
	    	else if ("normal".equals(message)) {
	    		ln.setPadding(0, 0, 0, 0);
                SharedPreferences sharedPreferences = context.getSharedPreferences("EvoPrefsFile",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit(); //opens the editor
                editor.putString("type", "normal"); //true or false
                editor.commit();
	    	}
        }
        
    };      
    
    context.registerReceiver(mReceiver, new IntentFilter("com.b16h22.statusbar.CHANGE_LAYOUT"));   
	}


}