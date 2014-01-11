package com.b16h22.statusbar;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Signal extends RelativeLayout {
	
	int level;
	TextView signal;
	ImageView signalBar;
	SignalStrength mSignalStrength;
    ServiceState mServiceState;
	ImageView data;
	
	 public Signal(final Context context, AttributeSet attrs) {
		  super(context, attrs);
		  
			signal = new TextView(context);
			signalBar =new ImageView(context);
			data =new ImageView(context);
			this.addView(signalBar);
			this.addView(signal);
			this.addView(data);

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
				    	  data.setImageResource(R.drawable.ic_qs_signal_full_e);
				          break;
				      case 8:
				    	  data.setImageResource(R.drawable.ic_qs_signal_full_h);
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
			    signalBar.setImageResource(R.drawable.stat_signal);
			    

			    signal.setText(String.valueOf(strengthAmplitude));
			    signal.setVisibility(GONE);
			    super.onSignalStrengthsChanged(signalStrength);
			  }
			}


		 
		        }


