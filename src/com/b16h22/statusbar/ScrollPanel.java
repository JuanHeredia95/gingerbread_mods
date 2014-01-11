package com.b16h22.statusbar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class ScrollPanel extends LinearLayout {
	

	LinearLayout ln;
	
	 public ScrollPanel(final Context context, AttributeSet attrs) {
		  super(context, attrs);
		  
			 ln = (LinearLayout) findViewById(R.id.scroll_panel);
		     
				SharedPreferences sharedPreferences = context.getSharedPreferences("EvoPrefsFile",Context.MODE_PRIVATE); 
				   
			    String layoutType = sharedPreferences.getString("type","phablet");
			    	if ("tablet".equals(layoutType )) {
		            	  ln.setBackgroundResource(R.drawable.expanded_bg);
		            	  setHeightWrap();
			   	}
			   	else if ("phablet".equals(layoutType )) {
	            	  ln.setBackgroundColor(Color.parseColor("#00000000"));
	            	  setHeightWrap();
			   	} 
			   	else if ("normal".equals(layoutType )) {
			   		ln.setBackgroundColor(Color.parseColor("#00000000"));
			   		setHeightWrap();
			   	}
          
			        BroadcastReceiver mReceiverLayout = new BroadcastReceiver() {
			            @Override
			            public void onReceive(Context c, Intent i) {
			            	String message = i.getStringExtra("layoutType");
			            	
			            	if ("tablet".equals(message )) {
			            		 ln.setBackgroundResource(R.drawable.expanded_bg);
			            		 setHeightWrap();
			            	}
			            	else if ("phablet".equals(message )) {
			            		ln.setBackgroundColor(Color.parseColor("#00000000"));
			            		setHeightWrap();
			            		ln.setPadding(0, 0, 0, 0);
			            	}
			               	else if ("normal".equals(message )) {
			               		ln.setBackgroundColor(Color.parseColor("#00000000"));
			               		setHeightWrap();
			               		ln.setPadding(0, 0, 0, 0);
			               	}
			            }
			            
			        };     

			        context.registerReceiver(mReceiverLayout, new IntentFilter("com.b16h22.statusbar.CHANGE_LAYOUT"));
	 }

	 public void setHeightWrap() {
		 LayoutParams lp = new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT);
		 ln.setLayoutParams(lp);
	 }
	  
}


