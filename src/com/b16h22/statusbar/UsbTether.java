package com.b16h22.statusbar;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.util.Log;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.b16h22.statusbar.R;

public class UsbTether extends ImageView {

 
 public UsbTether(final Context context, AttributeSet attrs) {
  super(context, attrs);
  
  	final ImageView image = (ImageView) findViewById(R.id.usb_tether);
 	image.setImageResource(R.drawable.ic_notify_quicksettings);

	image.setOnClickListener(new View.OnClickListener() {
		 
		@Override
		public void onClick(View v) {
	        if (v.isSelected()){
	            v.setSelected(false);
	            unTether(context);
	        } else {
	            v.setSelected(true);
	            tether(context);
	            Handler handler = new Handler();
	            handler.postDelayed(new Runnable() {

	                public void run() {
	                	image.setClickable(true);
	                }

	            }, 5000);

	        }		};
	});
	
    BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context c, Intent i) {
        	image.setClickable(true);
        }
        
    }; 
    
    BroadcastReceiver mReceiver2 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context c, Intent i) {
        	image.setClickable(false);
        }
        
    };
    
	context.registerReceiver(mReceiver, new IntentFilter("android.intent.action.UMS_CONNECTED"));
	context.registerReceiver(mReceiver2, new IntentFilter("android.intent.action.UMS_DISCONNECTED"));
	
    IntentFilter intentfilter = new IntentFilter();
    intentfilter.addAction("android.intent.action.MEDIA_SHARED");
    intentfilter.addDataScheme("file");
    context.registerReceiver(mReceiver2, intentfilter);
    
    IntentFilter intentfilter1 = new IntentFilter();
    intentfilter1.addAction("android.intent.action.MEDIA_UNSHARED");
    intentfilter1.addDataScheme("file");
    context.registerReceiver(mReceiver, intentfilter1);
    
 }
	public void tether(Context context) {
	    ConnectivityManager cm =
	            (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
	        String[] available = null;
	        int code=-1;
	        Method[] wmMethods = cm.getClass().getDeclaredMethods();

	        for(Method method: wmMethods){
	          if(method.getName().equals("getTetherableIfaces")){
	            try {
	                available = (String[]) method.invoke(cm);
	                break;
	            } catch (IllegalArgumentException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	                return;
	            } catch (IllegalAccessException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	                return;
	            } catch (InvocationTargetException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	                return;
	            }
	          }
	        }

	        for(Method method: wmMethods){
	              if(method.getName().equals("tether")){                  
	                  try {
	                    code = (Integer) method.invoke(cm, available[0]);


	                } catch (IllegalArgumentException e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	                    return;
	                } catch (IllegalAccessException e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	                    return;
	                } catch (InvocationTargetException e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	                    return;
	                }
	                break;
	              }
	        }

	        if (code==0) 
	            Log.d("","Enable usb tethering successfully!");
	        else
	            Log.d("","Enable usb tethering failed!");
	}
	
	public void unTether(Context context) {
	    ConnectivityManager cm =
	            (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
	        String[] available = null;
	        int code=-1;
	        Method[] wmMethods = cm.getClass().getDeclaredMethods();

	        for(Method method: wmMethods){
	          if(method.getName().equals("getTetheredIfaces")){
	            try {
	                available = (String[]) method.invoke(cm);
	                break;
	            } catch (IllegalArgumentException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	                return;
	            } catch (IllegalAccessException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	                return;
	            } catch (InvocationTargetException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	                return;
	            }
	          }
	        }

	        for(Method method: wmMethods){
	              if(method.getName().equals("untether")){                  
	                  try {
	                    code = (Integer) method.invoke(cm, available[0]);


	                } catch (IllegalArgumentException e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	                    return;
	                } catch (IllegalAccessException e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	                    return;
	                } catch (InvocationTargetException e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	                    return;
	                }
	                break;
	              }
	        }

	        if (code==0) 
	            Log.d("","disable usb tethering successfully!");
	        else
	            Log.d("","disable usb tethering failed!");
	}
}
