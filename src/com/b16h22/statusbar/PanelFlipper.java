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

public class PanelFlipper extends ViewFlipper {

    ViewFlipper VF;

    public PanelFlipper (final Context context, AttributeSet attrs) {
		  super(context, attrs);
  
          BroadcastReceiver mReceiver = new BroadcastReceiver() {
              @Override
              public void onReceive(Context c, Intent i) {
            	 VF.setDisplayedChild(2);
          		 VF.setInAnimation(inFromRightAnimation());
          		VF.setOutAnimation(outToLeftAnimation());	 
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
          context.registerReceiver(mReceiver, new IntentFilter("com.b16h22.statusbar.FLIP_TO_SLIDER")); 
          context.registerReceiver(mReceiver1, new IntentFilter("com.b16h22.statusbar.FLIP_TO_PANEL"));
	 }	




    private Animation inFromRightAnimation() {
    	  Animation inFromRight = new TranslateAnimation(
    	    Animation.RELATIVE_TO_PARENT, +1.0f,
    	    Animation.RELATIVE_TO_PARENT, 0.0f,
    	    Animation.RELATIVE_TO_PARENT, 0.0f,
    	    Animation.RELATIVE_TO_PARENT, 0.0f);
    	  inFromRight.setDuration(300);
    	  inFromRight.setInterpolator(new AccelerateInterpolator());
    	  return inFromRight;
    	 }

    	 private Animation outToLeftAnimation() {
    	  Animation outtoLeft = new TranslateAnimation(
    	    Animation.RELATIVE_TO_PARENT, 0.0f,
    	    Animation.RELATIVE_TO_PARENT, -1.0f,
    	    Animation.RELATIVE_TO_PARENT, 0.0f,
    	    Animation.RELATIVE_TO_PARENT, 0.0f);
    	  outtoLeft.setDuration(300);
    	  outtoLeft.setInterpolator(new AccelerateInterpolator());
    	  return outtoLeft;
    	 }

    	 private Animation inFromLeftAnimation() {
    	  Animation inFromLeft = new TranslateAnimation(
    	    Animation.RELATIVE_TO_PARENT, -1.0f,
    	    Animation.RELATIVE_TO_PARENT, 0.0f,
    	    Animation.RELATIVE_TO_PARENT, 0.0f,
    	    Animation.RELATIVE_TO_PARENT, 0.0f);
    	  inFromLeft.setDuration(300);
    	  inFromLeft.setInterpolator(new AccelerateInterpolator());
    	  return inFromLeft;
    	 }

    	 private Animation outToRightAnimation() {
    	  Animation outtoRight = new TranslateAnimation(
    	    Animation.RELATIVE_TO_PARENT, 0.0f,
    	    Animation.RELATIVE_TO_PARENT, +1.0f,
    	    Animation.RELATIVE_TO_PARENT, 0.0f,
    	    Animation.RELATIVE_TO_PARENT, 0.0f);
    	  outtoRight.setDuration(300);
    	  outtoRight.setInterpolator(new AccelerateInterpolator());
    	  return outtoRight;
    	 }

}

