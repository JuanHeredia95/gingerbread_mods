package com.b16h22.statusbar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class FirstPanel extends LinearLayout {
	
    String color1;
    String color2;
	LinearLayout ln;
	
	 public FirstPanel(final Context context, AttributeSet attrs) {
		  super(context, attrs);
		  
			 ln = (LinearLayout) findViewById(R.id.first_panel);
			 
			  final float scale = getContext().getResources().getDisplayMetrics().density;
			  final int top = (int) (-0.1 * scale + 0.5f);
			 
		     color1 = "#ff000000";
		     color2 = "#00000000";
		     final GradientDrawable datePanel = new GradientDrawable(
		             GradientDrawable.Orientation.TOP_BOTTOM,
		             new int[] {Color.parseColor(color1),Color.parseColor(color2)});
		     datePanel.setShape(GradientDrawable.RECTANGLE);
		     
				SharedPreferences sharedPreferences = context.getSharedPreferences("EvoPrefsFile",Context.MODE_PRIVATE); 
				   
			    String layoutType = sharedPreferences.getString("type","phablet");
			    	if ("tablet".equals(layoutType )) {
		            	  ln.setBackgroundResource(R.drawable.panel_bg);
		            	  setHeightWrap();
			   	}
			   	else if ("phablet".equals(layoutType )) {
	            	  ln.setBackgroundDrawable(datePanel);
	            	  setHeightWrap();
			   	} 
			   	else if ("normal".equals(layoutType )) {
			   		ln.setBackgroundDrawable(datePanel);
			   		setHeightWrap();
			   	}
          
			        BroadcastReceiver mReceiverLayout = new BroadcastReceiver() {
			            @Override
			            public void onReceive(Context c, Intent i) {
			            	String message = i.getStringExtra("layoutType");
			            	
			            	if ("tablet".equals(message )) {
			            		 ln.setBackgroundResource(R.drawable.panel_bg);
			            		 setHeightWrap();
			            	}
			            	else if ("phablet".equals(message )) {
			            		 ln.setBackgroundDrawable(datePanel);
			            		 setHeight();
			            		 ln.setPadding(0, top, 0, 0);
			            	}
			               	else if ("normal".equals(message )) {
			               		ln.setBackgroundDrawable(datePanel);
			               		setHeight();
			               		ln.setPadding(0, top, 0, 0);
			               	}
			            }
			            
			        };     

			        context.registerReceiver(mReceiverLayout, new IntentFilter("com.b16h22.statusbar.CHANGE_LAYOUT"));
	 }

	 
	 public void setHeightWrap() {
		 LayoutParams lp = new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT);
		 ln.setLayoutParams(lp);
	 }
	 
	 public void setHeight() {
		 float scale = getContext().getResources().getDisplayMetrics().density;
		 int height = (int) (49 * scale + 0.5f);
		 LayoutParams lp = new LayoutParams(LayoutParams.FILL_PARENT,height);
		 ln.setLayoutParams(lp);
	 }
}

