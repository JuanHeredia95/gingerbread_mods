package com.b16h22.statusbar;

import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WifiSignal extends LinearLayout {
	
	int rssi;
	TextView signal;
	TextView Name;
	ImageView signalBar;
	WifiManager wifiManager;
	String textColor;
	String name;
	int signalAsu;
	
	 public WifiSignal(final Context context, AttributeSet attrs) {
		  super(context, attrs);
		  
			signal = new TextView(context);
			Name = new TextView(context);
			signalBar =new ImageView(context);
			Name.setSingleLine(true);
			this.addView(signalBar);
			this.addView(Name);
			LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			lp.gravity = Gravity.CENTER;
			Name.setLayoutParams(lp);
			Name.setTextSize(13);
			Name.setSingleLine(true);
			wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
			
		     SharedPreferences sharedPreferences = context.getSharedPreferences("EvoPrefsFile",Context.MODE_PRIVATE);    
		     textColor = sharedPreferences.getString("toggleTextColor","#ffffffff");
			 Name.setTextColor(Color.parseColor(textColor));
			 

			   //text color
			   BroadcastReceiver mReceiverColor = new BroadcastReceiver() {
			       @Override
			       public void onReceive(Context c, Intent i) {
			       	   textColor = i.getStringExtra("toggleTextColor");
			       	   Name.setTextColor(Color.parseColor(textColor));	 
			       }
			       
			   };
			   

			   //Wifi state reciever
				 BroadcastReceiver WifiStateChangedReceiver = new BroadcastReceiver(){

				 @Override
				 public void onReceive(final Context context, Intent intent) {
				  // TODO Auto-generated method stub
				
				 
			        SupplicantState supState;
			        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
			        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
			        supState = wifiInfo.getSupplicantState();


			        if (supState.equals(SupplicantState.COMPLETED)) {
					    final Handler h = new Handler();
					    h.post(new Runnable() {
					        @Override
					        public void run() {
					            updateSignal(context);
					            h.postDelayed(this, 200);
					        }
					    });
			        } 
			        else if (supState.equals(SupplicantState.DISCONNECTED)) {
					 	 Name.setText("Disconnected");
		 	           	 signalBar.setImageResource(R.drawable.ic_qs_wifi_signal_null);
		            } else {

			            if (supState.equals(SupplicantState.SCANNING)) {
						 	 Name.setText("Scanning..");
			 	           	 signalBar.setImageResource(R.drawable.ic_qs_wifi_signal_null);
			            } else if (supState.equals(SupplicantState.DISCONNECTED)) {
						 	 Name.setText("Disconnected");
			 	           	 signalBar.setImageResource(R.drawable.ic_qs_wifi_signal_null);
			            } else if (supState.equals(SupplicantState.DORMANT )) {

			            	Name.setText("Scanning..");
			 	           	 signalBar.setImageResource(R.drawable.ic_qs_wifi_signal_null);
			            } else if (supState.equals(SupplicantState.INACTIVE  )) {

			            	Name.setText("Scanning..");
			 	           	 signalBar.setImageResource(R.drawable.ic_qs_wifi_signal_null);
			            }else if (supState.equals(SupplicantState.UNINITIALIZED   )) {

			            	Name.setText("Scanning..");
			 	           	 signalBar.setImageResource(R.drawable.ic_qs_wifi_signal_null);
			            }
			        }
				 }
				 
				};
				
				 BroadcastReceiver WifiConnectionReceiver = new BroadcastReceiver(){

				 @Override
				 public void onReceive(Context c, Intent intent) {
					 if(intent.getAction().equals(WifiManager.SUPPLICANT_CONNECTION_CHANGE_ACTION)){ 
				          boolean connected = intent.getBooleanExtra(WifiManager.EXTRA_SUPPLICANT_CONNECTED, false);
				          if(!connected) {
				        	  Name.setText("Scanning..");
				 	          signalBar.setImageResource(R.drawable.ic_qs_wifi_signal_null);
				          }
				      }

				      else if(intent.getAction().equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)){
				          NetworkInfo netInfo = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
				          if( netInfo.isConnected() )
				          {
							    final Handler h = new Handler();
							    h.post(new Runnable() {
							        @Override
							        public void run() {
							            updateSignal(context);
							            h.postDelayed(this, 200);
							        }
							    });
				          }   
				      }
				  
				 }
				 };
			   context.registerReceiver(mReceiverColor, new IntentFilter("com.b16h22.statusbar.CHANGE_TOGGLETEXT_COLOR")); 
			   context.registerReceiver(WifiStateChangedReceiver, new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION));
			   context.registerReceiver(WifiStateChangedReceiver, new IntentFilter(WifiManager.SUPPLICANT_STATE_CHANGED_ACTION));
			   context.registerReceiver(WifiConnectionReceiver, new IntentFilter(WifiManager.SUPPLICANT_STATE_CHANGED_ACTION));
				}
	 
	 
	 public void updateSignal(Context context) {
	 wifiManager.startScan();
 	  final List<ScanResult> results = wifiManager.getScanResults();
 	//do it to get connected info..
 	    //  final WifiInfo wifiInfo = _wifiManager.getConnectionInfo();
 	      //           int connectedRssi= wifiInfo.getRssi();
 	        if (results != null) {
 	                for (final ScanResult result : results) {
 	                rssi=result.level;
 	            }
 	               int asu = (rssi+113)/2 ;
 	                signal.setText(String.valueOf(asu));
 	                signal.setVisibility(GONE);
 	               WifiManager wifiMgr = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
 	              WifiInfo wifiInfo = wifiMgr.getConnectionInfo();
 	              String name = wifiInfo.getSSID();
 	             Name.setText(name);
 	           	signalBar.setImageLevel(asu);
 	           	 signalBar.setImageResource(R.drawable.stat_wifi);
 	           	 
 	        } else {
 	        	Name.setText("Scanning..");
	 	          signalBar.setImageResource(R.drawable.ic_qs_wifi_signal_null);
 	        }
}
   }



