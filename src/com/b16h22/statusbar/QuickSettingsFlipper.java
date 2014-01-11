package com.b16h22.statusbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ViewFlipper;

public class QuickSettingsFlipper extends ViewFlipper {

    ViewFlipper VF;
    GestureDetector gestureDetector;
    public QuickSettingsFlipper (final Context context, AttributeSet attrs) {
		  super(context, attrs);


        gestureDetector = new GestureDetector(new SwipeDetector()); 
        VF.setOnTouchListener(new OnTouchListener() {
           
        	   public boolean onTouch(View v, MotionEvent event) {
        	    if (gestureDetector.onTouchEvent(event)) {
        	     return true;
        	    } else {
        	     return false;
        	    }
        	   }
        	   
        	});

    }

    class SwipeDetector extends SimpleOnGestureListener {

  	  private static final int SWIPE_MIN_DISTANCE = 100;
  	  private static final int SWIPE_MAX_OFF_PATH = 200;
  	  private static final int SWIPE_THRESHOLD_VELOCITY = 250;

  	  public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
  	   System.out.println(" in onFling() :: ");
  	   if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
  	    return false;
  	   if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
  	     && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
  		 VF.setInAnimation(inFromRightAnimation());
  		VF.setOutAnimation(outToLeftAnimation());
  		VF.showNext();
  	   } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
  	     && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
  		 VF.setInAnimation(inFromLeftAnimation());
  		VF.setOutAnimation(outToRightAnimation());
  		VF.showPrevious();
  	   }
  	   return super.onFling(e1, e2, velocityX, velocityY);
  	  }
  	}
    private Animation inFromRightAnimation() {
    	  Animation inFromRight = new TranslateAnimation(
    	    Animation.RELATIVE_TO_PARENT, +1.0f,
    	    Animation.RELATIVE_TO_PARENT, 0.0f,
    	    Animation.RELATIVE_TO_PARENT, 0.0f,
    	    Animation.RELATIVE_TO_PARENT, 0.0f);
    	  inFromRight.setDuration(500);
    	  inFromRight.setInterpolator(new AccelerateInterpolator());
    	  return inFromRight;
    	 }

    	 private Animation outToLeftAnimation() {
    	  Animation outtoLeft = new TranslateAnimation(
    	    Animation.RELATIVE_TO_PARENT, 0.0f,
    	    Animation.RELATIVE_TO_PARENT, -1.0f,
    	    Animation.RELATIVE_TO_PARENT, 0.0f,
    	    Animation.RELATIVE_TO_PARENT, 0.0f);
    	  outtoLeft.setDuration(500);
    	  outtoLeft.setInterpolator(new AccelerateInterpolator());
    	  return outtoLeft;
    	 }

    	 private Animation inFromLeftAnimation() {
    	  Animation inFromLeft = new TranslateAnimation(
    	    Animation.RELATIVE_TO_PARENT, -1.0f,
    	    Animation.RELATIVE_TO_PARENT, 0.0f,
    	    Animation.RELATIVE_TO_PARENT, 0.0f,
    	    Animation.RELATIVE_TO_PARENT, 0.0f);
    	  inFromLeft.setDuration(500);
    	  inFromLeft.setInterpolator(new AccelerateInterpolator());
    	  return inFromLeft;
    	 }

    	 private Animation outToRightAnimation() {
    	  Animation outtoRight = new TranslateAnimation(
    	    Animation.RELATIVE_TO_PARENT, 0.0f,
    	    Animation.RELATIVE_TO_PARENT, +1.0f,
    	    Animation.RELATIVE_TO_PARENT, 0.0f,
    	    Animation.RELATIVE_TO_PARENT, 0.0f);
    	  outtoRight.setDuration(500);
    	  outtoRight.setInterpolator(new AccelerateInterpolator());
    	  return outtoRight;
    	 }
    	 @Override
    	 public boolean onTouchEvent(MotionEvent event) {
    	     if (gestureDetector.onTouchEvent(event))
    	         return true;
    	     else
    	         return false;
    	 }

    	 @Override
    	 public boolean dispatchTouchEvent(MotionEvent ev) {
    	     super.dispatchTouchEvent(ev);
    	     return gestureDetector.onTouchEvent(ev);
    	 }
}
