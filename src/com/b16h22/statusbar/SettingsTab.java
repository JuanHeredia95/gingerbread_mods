package com.b16h22.statusbar;

import java.lang.reflect.Method;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

public class SettingsTab extends ImageView {

	 public SettingsTab(final Context context, AttributeSet attrs) {
		  super(context, attrs);
		  
		  ImageView image = (ImageView) findViewById(R.id.set_btn);
		  
			SharedPreferences sharedPreferences = context.getSharedPreferences("EvoPrefsFile",Context.MODE_PRIVATE); 
			   
		    String layoutType = sharedPreferences.getString("type","phablet");
		    	if ("tablet".equals(layoutType )) {
		    		unhide();
		   	}
		   	else if ("phablet".equals(layoutType )) {
		   		hide();
		   	}
		    	
		  image.setImageResource(R.drawable.ic_notify_quicksettings);
			image.setOnClickListener(new View.OnClickListener() {
 
				@Override
				public void onClick(View v) {
					v.getContext().startActivity((new Intent("android.settings.SETTINGS")).setFlags(0x10000000));
				    try{ 
				    	   Object service  = context.getSystemService("statusbar");
				    	   Class<?> statusbarManager = Class.forName("android.app.StatusBarManager");
				    	   Method collapse = statusbarManager.getMethod("collapse");
				    	   collapse.invoke(service);
				    	 }
				    	 catch(Exception ex){           

				    	 }
		        }
		});
			
	          BroadcastReceiver mReceiverLayout = new BroadcastReceiver() {
	              @Override
	              public void onReceive(Context c, Intent i) {
	              	String message = i.getStringExtra("layoutType");
	              	
	              	if ("tablet".equals(message )) {
	              		unhide();
	              	}
	              	else if ("phablet".equals(message )) {
	              		hide();
	              	}	 
	              }
	              
	          };        

		    context.registerReceiver(mReceiverLayout, new IntentFilter("com.b16h22.statusbar.CHANGE_LAYOUT"));
	 }
		public void hide() {
			  this.setVisibility(GONE);
		}
		
		public void unhide() {
			  this.setVisibility(VISIBLE);
		}
	}