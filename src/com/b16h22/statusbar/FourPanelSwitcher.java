package com.b16h22.statusbar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.AttributeSet;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ViewFlipper;

public class FourPanelSwitcher extends ViewFlipper {

    ViewFlipper VF;

    public FourPanelSwitcher (final Context context, AttributeSet attrs) {
		  super(context, attrs);
		  VF = (ViewFlipper) findViewById(R.id.fourpanelflipper);
          BroadcastReceiver mReceiver = new BroadcastReceiver() {
              @Override
              public void onReceive(Context c, Intent i) {
            	 VF.setDisplayedChild(0);
          		 VF.setInAnimation(inFromLeftAnimation());
          		VF.setOutAnimation(outToRightAnimation());	 
              }
              
          }; 
          BroadcastReceiver mReceiver1 = new BroadcastReceiver() {
              @Override
              public void onReceive(Context c, Intent i) {
             	 VF.setDisplayedChild(1);
          		 VF.setInAnimation(inFromLeftAnimation());
          		VF.setOutAnimation(outToRightAnimation());
              }
              
          };
          BroadcastReceiver mReceiver2 = new BroadcastReceiver() {
              @Override
              public void onReceive(Context c, Intent i) {
             	 VF.setDisplayedChild(2);
          		 VF.setInAnimation(inFromLeftAnimation());
          		VF.setOutAnimation(outToRightAnimation());
              }
              
          };       
          
          BroadcastReceiver mReceiver3 = new BroadcastReceiver() {
              @Override
              public void onReceive(Context c, Intent i) {
             	 VF.setDisplayedChild(3);
          		 VF.setInAnimation(inFromLeftAnimation());
          		VF.setOutAnimation(outToRightAnimation());
              }
              
          }; 
          context.registerReceiver(mReceiver, new IntentFilter("com.b16h22.statusbar.FLIP_TO_NOTIF")); 
          context.registerReceiver(mReceiver1, new IntentFilter("com.b16h22.statusbar.FLIP_TO_TOGGLES"));
          context.registerReceiver(mReceiver2, new IntentFilter("com.b16h22.statusbar.FLIP_TO_SLIDERS"));
          context.registerReceiver(mReceiver3, new IntentFilter("com.b16h22.statusbar.FLIP_TO_FOURTH"));
	 }	


    	 private Animation inFromLeftAnimation() {
    	  Animation inFromLeft = new TranslateAnimation(
    	    Animation.RELATIVE_TO_PARENT, -1.0f,
    	    Animation.RELATIVE_TO_PARENT, 0.0f,
    	    Animation.RELATIVE_TO_PARENT, 0.0f,
    	    Animation.RELATIVE_TO_PARENT, 0.0f);
    	  inFromLeft.setDuration(200);
    	  inFromLeft.setInterpolator(new AccelerateInterpolator());
    	  return inFromLeft;
    	 }

    	 private Animation outToRightAnimation() {
    	  Animation outtoRight = new TranslateAnimation(
    	    Animation.RELATIVE_TO_PARENT, 0.0f,
    	    Animation.RELATIVE_TO_PARENT, +1.0f,
    	    Animation.RELATIVE_TO_PARENT, 0.0f,
    	    Animation.RELATIVE_TO_PARENT, 0.0f);
    	  outtoRight.setDuration(200);
    	  outtoRight.setInterpolator(new AccelerateInterpolator());
    	  return outtoRight;
    	 }

}


