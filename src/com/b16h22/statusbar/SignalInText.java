package com.b16h22.statusbar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

public class SignalInText extends TextView {
	
	int level;
	TextView signal;
	SignalStrength mSignalStrength;
    ServiceState mServiceState;
	ImageView data;
	int strengthAmplitude;
	int indBm;
	String type;
	SharedPreferences sharedPreferences;
	
	 public SignalInText(final Context context, AttributeSet attrs) {
		  super(context, attrs);
			sharedPreferences = context.getSharedPreferences("EvoPrefsFile",Context.MODE_PRIVATE); 
			signal = (TextView) findViewById(R.id.signal_text);
			 SignalStrengthListener signalStrengthListener;
			   //start the signal strength listener
			  signalStrengthListener = new SignalStrengthListener();	           
			   ((TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE)).listen(signalStrengthListener,SignalStrengthListener.LISTEN_SIGNAL_STRENGTHS);
			   
		       
			   BroadcastReceiver mReceiver = new BroadcastReceiver() {
		              @Override
		              public void onReceive(Context c, Intent i) {
		            	   
		            	    int state = i.getExtras().getInt("state");
		            	    if(state == ServiceState.STATE_OUT_OF_SERVICE)
		            	    {
		            	    	signal.setText(String.valueOf("0"+"dBm"));;
		            	    }
		            	    
		            	    
		            	    String  settingValue = Settings.System.getString(
		            	          context.getContentResolver(), Settings.System.AIRPLANE_MODE_ON);
		            	    if (settingValue == "airplane_mode_on") {
		            	    signal.setText(String.valueOf("0"+"dBm"));;
		            	    }
		            	    }	

		          }; 
		                    
			   context.registerReceiver(mReceiver, new IntentFilter("android.intent.action.SERVICE_STATE"));
			   
		          BroadcastReceiver mReceiverSignalDbm = new BroadcastReceiver() {
		              @Override
		              public void onReceive(Context c, Intent i) {
		            	  type = i.getStringExtra("signalType");
		            	  if (type == "dBm"){
		            	  signal.setText(String.valueOf(indBm+"dBm"));
		            	  }else{
		            		  signal.setText(String.valueOf(strengthAmplitude+"asu"));  
		            	  }
		                  SharedPreferences sharedPreferences = context.getSharedPreferences("EvoPrefsFile",Context.MODE_PRIVATE);
		                  SharedPreferences.Editor editor = sharedPreferences.edit(); //opens the editor
		                  editor.putString("signalType", type); //true or false
		                  editor.commit();	 
		              }
		              
		          }; 	   
		          context.registerReceiver(mReceiverSignalDbm, new IntentFilter("com.b16h22.statusbar.CHANGE_SIGNAL_TYPE"));
	 }


			 private class SignalStrengthListener extends PhoneStateListener
			 {
			  public void onSignalStrengthsChanged(android.telephony.SignalStrength signalStrength) {
			    
			     // get the signal strength (a value between 0 and 31)
			     strengthAmplitude = signalStrength.getGsmSignalStrength();
		         indBm = strengthAmplitude*2-113;
		         
					type = sharedPreferences.getString("signalType","dBm");
		      	  if (type == "dBm"){
		      	  signal.setText(String.valueOf(indBm+"dBm"));
		      	  }else{
		      		  signal.setText(String.valueOf(strengthAmplitude+"asu"));  
		      	  }
			    super.onSignalStrengthsChanged(signalStrength);
			  }
			}


		 
		        }



