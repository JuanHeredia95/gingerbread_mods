package com.b16h22.statusbar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class SecondPanel extends RelativeLayout {
	
	RelativeLayout mPanel;
	
	 public SecondPanel(final Context context, AttributeSet attrs) {
		  super(context, attrs);
			 mPanel = (RelativeLayout) findViewById(R.id.clock_panel);	  

				SharedPreferences sharedPreferences = context.getSharedPreferences("EvoPrefsFile",Context.MODE_PRIVATE); 
				   
			    String layoutType = sharedPreferences.getString("type","phablet");
			    	if ("tablet".equals(layoutType )) {
		            	  mPanel.setVisibility(VISIBLE);
			   	}
			   	else if ("phablet".equals(layoutType )) {
	            	  mPanel.setVisibility(GONE);
			   	} 
			   	else if ("normal".equals(layoutType )) {
			   		mPanel.setVisibility(GONE);
			   	}
          
			        BroadcastReceiver mReceiverLayout = new BroadcastReceiver() {
			            @Override
			            public void onReceive(Context c, Intent i) {
			            	String message = i.getStringExtra("layoutType");
			            	
			            	if ("tablet".equals(message )) {
			            		 mPanel.setVisibility(VISIBLE);
			            	}
			            	else if ("phablet".equals(message )) {
			            		 mPanel.setVisibility(GONE);
			            	}
			               	else if ("normal".equals(message )) {
			               		mPanel.setVisibility(GONE);
			               	}
			            }
			            
			        };     

			        context.registerReceiver(mReceiverLayout, new IntentFilter("com.b16h22.statusbar.CHANGE_LAYOUT"));
	 }	
}
