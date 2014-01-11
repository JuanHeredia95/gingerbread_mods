package com.b16h22.statusbar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ImageView;


public class TabView extends LinearLayout {

	   ImageView notif;
	   ImageView toggle;
	   ImageView slider;
	   ImageView fourthpanel;
	   View view1;
	   View view2;
	   View view3;
	   String message;
	  
 public TabView(final Context context, AttributeSet attrs) {
  super(context, attrs);
  
  notif = new ImageView(context);
  toggle = new ImageView(context);
  slider = new ImageView(context);
  fourthpanel = new ImageView(context);
  notif.setBackgroundResource(R.drawable.btn);
  toggle.setBackgroundResource(R.drawable.btn);
  slider.setBackgroundResource(R.drawable.btn);
  fourthpanel.setBackgroundResource(R.drawable.btn);
  final float scale = getContext().getResources().getDisplayMetrics().density;
  int pixels = (int) (8 * scale + 0.5f);
  view1 = new View(context);
  view2 = new View(context);
  view3 = new View(context);
  LayoutParams view = new LayoutParams(1,LayoutParams.FILL_PARENT);
  view.topMargin = pixels;
  view.bottomMargin = pixels;
  view1.setLayoutParams(view);
  view2.setLayoutParams(view);
  view3.setLayoutParams(view);
  view1.setBackgroundColor(Color.parseColor("#1fffffff"));
  view2.setBackgroundColor(Color.parseColor("#1fffffff"));
  view3.setBackgroundColor(Color.parseColor("#1fffffff"));
  LayoutParams text = new LayoutParams(0,LayoutParams.FILL_PARENT);
  text.weight = 1f;
  text.gravity=Gravity.CENTER;
  notif.setLayoutParams(text);
  toggle.setLayoutParams(text);
  slider.setLayoutParams(text);
  fourthpanel.setLayoutParams(text);
  this.addView(notif);
  this.addView(view1);
  this.addView(toggle);
  this.addView(view2);
  this.addView(slider);
  this.addView(view3);
  this.addView(fourthpanel);
  int height = (int) (30 * scale + 0.5f);
  LayoutParams layout = new LayoutParams(LayoutParams.FILL_PARENT,height);
  this.setLayoutParams(layout);
  
  final SharedPreferences sharedPreferences = context.getSharedPreferences("EvoPrefsFile",Context.MODE_PRIVATE);
  String layoutType = sharedPreferences.getString("type","phablet");
	if ("tablet".equals(layoutType )) {
		  Boolean SliderVisibility = sharedPreferences.getBoolean("SliderVisibility",false);
		    if (SliderVisibility == false){
		        unhideSlider(); 
		    }
		    else{
		    	hideSlider(); 
		    }
		hide();
	}
	if ("normal".equals(layoutType )) {
		  Boolean SliderVisibility = sharedPreferences.getBoolean("SliderVisibility",false);
		    if (SliderVisibility == false){
		        unhideSlider(); 
		    }
		    else{
		    	hideSlider(); 
		    }
		hide();
	}	
	else if ("phablet".equals(layoutType )) {
		  unhide();
		  Boolean SliderVisibility = sharedPreferences.getBoolean("SliderVisibility",false);
		    if (SliderVisibility == false){
		        unhideSlider(); 
		    }
		    else{
		    	hideSlider(); 
		    }
	} 
 
	notif.setOnClickListener(new View.OnClickListener() {
		 
		@Override
		public void onClick(View v) {

	            v.setSelected(true);
	            toggle.setSelected(false);
	            slider.setSelected(false);
	            fourthpanel.setSelected(false);
				Intent intent = new Intent();
				intent.setAction("com.b16h22.statusbar.FLIP_TO_NOTIF");
				context.sendBroadcast(intent);

	        };
	});
	
	toggle.setOnClickListener(new View.OnClickListener() {
		 
		@Override
		public void onClick(View v) {

	            v.setSelected(true);
	            notif.setSelected(false);
	            slider.setSelected(false);
	            fourthpanel.setSelected(false);
				Intent intent = new Intent();
				intent.setAction("com.b16h22.statusbar.FLIP_TO_TOGGLES");
				context.sendBroadcast(intent);

	        };
	});
	
	slider.setOnClickListener(new View.OnClickListener() {
		 
		@Override
		public void onClick(View v) {
	
	            v.setSelected(true);
	            notif.setSelected(false);
	            toggle.setSelected(false);
	            fourthpanel.setSelected(false);
				Intent intent = new Intent();
				intent.setAction("com.b16h22.statusbar.FLIP_TO_SLIDERS");
				context.sendBroadcast(intent);

	        };
	});
	
	fourthpanel.setOnClickListener(new View.OnClickListener() {
		 
		@Override
		public void onClick(View v) {
	
	            v.setSelected(true);
	            notif.setSelected(false);
	            toggle.setSelected(false);
	            slider.setSelected(false);
				Intent intent = new Intent();
				intent.setAction("com.b16h22.statusbar.FLIP_TO_FOURTH");
				context.sendBroadcast(intent);

	        };
	});
	
    BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context c, Intent i) {
        	hideSlider();
        }
        
    };
    
    BroadcastReceiver mReceiverUnhide = new BroadcastReceiver() {
        @Override
        public void onReceive(Context c, Intent i) {
        	unhideSlider();
        }
        
    };
 
    BroadcastReceiver mReceiverLayout = new BroadcastReceiver() {
        @Override
        public void onReceive(Context c, Intent i) {
        	message = i.getStringExtra("layoutType");
        	
        	if ("tablet".equals(message )) {
        		hide();
        	}
        	if ("normal".equals(message )) {
        		hide();
        	}
        	else if ("phablet".equals(message )) {
      		  Boolean SliderVisibility = sharedPreferences.getBoolean("SliderVisibility",false);
  		    if (SliderVisibility == false){
  		        unhideSlider(); 
  		    }
  		    else{
  		    	hideSlider(); 
  		    }
        		unhide();
        	}	 
        }
        
    }; 
    
	context.registerReceiver(mReceiver, new IntentFilter("com.b16h22.statusbar.HIDE_SLIDER"));
	context.registerReceiver(mReceiverUnhide, new IntentFilter("com.b16h22.statusbar.UNHIDE_SLIDER"));
    context.registerReceiver(mReceiverLayout, new IntentFilter("com.b16h22.statusbar.CHANGE_LAYOUT")); 	
 }
 
	public void hideSlider() {
		  LayoutParams text = new LayoutParams(0,LayoutParams.FILL_PARENT);
		  text.weight = 1.5f;
		  text.gravity=Gravity.CENTER;
		  notif.setLayoutParams(text);
		  toggle.setLayoutParams(text);
		  slider.setVisibility(GONE);
		  view2.setVisibility(GONE);
	}
	
	public void unhideSlider() {
		  LayoutParams text = new LayoutParams(0,LayoutParams.FILL_PARENT);
		  text.weight = 1f;
		  text.gravity=Gravity.CENTER;
		  notif.setLayoutParams(text);
		  toggle.setLayoutParams(text);
		  slider.setVisibility(VISIBLE);
		  view2.setVisibility(VISIBLE);
	}
	
	public void hide() {
		  this.setVisibility(GONE);
	}
	
	public void unhide() {
		  this.setVisibility(VISIBLE);
	}
 }
