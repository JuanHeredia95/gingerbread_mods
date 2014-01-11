package com.b16h22.statusbar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.provider.Settings;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SignalTab extends RelativeLayout {
	
	int level;
	TextView signal;
	ImageView signalBar;
	SignalStrength mSignalStrength;
    ServiceState mServiceState;
	ImageView data;
	
	 public SignalTab(final Context context, AttributeSet attrs) {
		  super(context, attrs);
		  
			signal = new TextView(context);
			signalBar =new ImageView(context);
			data =new ImageView(context);
			this.addView(signalBar);
			this.addView(signal);
			this.addView(data);

			SharedPreferences sharedPreferences = context.getSharedPreferences("EvoPrefsFile",Context.MODE_PRIVATE); 
			   
		    String layoutType = sharedPreferences.getString("type","phablet");
		    	if ("tablet".equals(layoutType )) {
		    		unhide();
		   	}
		   	else if ("phablet".equals(layoutType )) {
		   		hide();
		   	} 
		    	
			 SignalStrengthListener signalStrengthListener;
			   //start the signal strength listener
			  signalStrengthListener = new SignalStrengthListener();	           
			   ((TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE)).listen(signalStrengthListener,SignalStrengthListener.LISTEN_SIGNAL_STRENGTHS);
			   
		       
			   BroadcastReceiver mReceiver = new BroadcastReceiver() {
		              @Override
		              public void onReceive(Context c, Intent i) {
		            	  
				          TelephonyManager teleMan =  
				                  (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
				      int networkType = teleMan.getNetworkType();

				      switch (networkType)
				      {case 2:
				    	  data.setImageResource(R.drawable.stat_sys_data_fully_connected_e);
				          break;
				      case 8:
				    	  data.setImageResource(R.drawable.stat_sys_data_fully_connected_h);
				    	    break;    
				      case 0:
				    	  data.setVisibility(GONE);
				          break;
				      }	 
				      int networkState = teleMan.getDataState();

				      switch (networkState)
				      {   
				      case 0:
				    	  data.setVisibility(GONE);
				          break;
				      case 2:
				    	  data.setVisibility(VISIBLE);
				          break;
				      }	 
		            	    int state = i.getExtras().getInt("state");
		            	    if(state == ServiceState.STATE_OUT_OF_SERVICE)
		            	    {
		            	    	signalBar.setImageResource(R.drawable.stat_sys_signal_null);
		            	    }
		            	    
		            	    
		            	    String  settingValue = Settings.System.getString(
		            	          context.getContentResolver(), Settings.System.AIRPLANE_MODE_ON);
		            	    if (settingValue == "airplane_mode_on"){
		            	      signalBar.setImageResource(R.drawable.ic_qs_signal_flightmode);
		            	    }
		            	    }	

		          }; 
		          
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
		       context.registerReceiver(mReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));           
			   context.registerReceiver(mReceiver, new IntentFilter("android.intent.action.SERVICE_STATE"));
			   
			   
	 }


			 private class SignalStrengthListener extends PhoneStateListener
			 {
			  public void onSignalStrengthsChanged(android.telephony.SignalStrength signalStrength) {
			    
			     // get the signal strength (a value between 0 and 31)
			     int strengthAmplitude = signalStrength.getGsmSignalStrength();

			    //do something with it (in this case we update a text view)
			     signalBar.setImageLevel(strengthAmplitude);
			    signalBar.setImageResource(R.drawable.stat_signal_tab);
			    

			    signal.setText(String.valueOf(strengthAmplitude));
			    signal.setVisibility(GONE);
			    super.onSignalStrengthsChanged(signalStrength);
			  }
			}

				public void hide() {
					  this.setVisibility(GONE);
				}
				
				public void unhide() {
					  this.setVisibility(VISIBLE);
				}
		 
    }



