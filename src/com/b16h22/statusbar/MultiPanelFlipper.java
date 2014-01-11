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

public class MultiPanelFlipper extends ViewFlipper {

    ViewFlipper VF;

    public MultiPanelFlipper (final Context context, AttributeSet attrs) {
		  super(context, attrs);
		  VF = (ViewFlipper) findViewById(R.id.multipanelflipper);
          BroadcastReceiver mReceiver = new BroadcastReceiver() {
              @Override
              public void onReceive(Context c, Intent i) {
            	 VF.setDisplayedChild(0);
          		 VF.setInAnimation(context, R.anim.card_flip_left_in);
          		VF.setOutAnimation(context, R.anim.card_flip_right_out); 
              }
              
          }; 
          BroadcastReceiver mReceiver1 = new BroadcastReceiver() {
              @Override
              public void onReceive(Context c, Intent i) {
             	 VF.setDisplayedChild(1);
          		 VF.setInAnimation(context, R.anim.card_flip_left_in);
           		VF.setOutAnimation(context, R.anim.card_flip_right_out); 
              }
              
          };
          BroadcastReceiver mReceiver2 = new BroadcastReceiver() {
              @Override
              public void onReceive(Context c, Intent i) {
             	 VF.setDisplayedChild(2);
          		 VF.setInAnimation(context, R.anim.card_flip_left_in);
           		VF.setOutAnimation(context, R.anim.card_flip_right_out); 
              }
              
          };          
          context.registerReceiver(mReceiver, new IntentFilter("com.b16h22.statusbar.FLIP_TO_NOTIF")); 
          context.registerReceiver(mReceiver1, new IntentFilter("com.b16h22.statusbar.FLIP_TO_TOGGLES"));
          context.registerReceiver(mReceiver2, new IntentFilter("com.b16h22.statusbar.FLIP_TO_SLIDERS"));
	 }	

}

