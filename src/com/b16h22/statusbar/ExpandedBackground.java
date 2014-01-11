package com.b16h22.statusbar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView;

public class ExpandedBackground extends ImageView {
	
    ImageView background;
    String uri;
    String imageUri;
    BroadcastReceiver mReceiver;
    BroadcastReceiver mMediaReceiver;
	public ExpandedBackground(final Context context, AttributeSet attrs) {
	super(context, attrs); 
	
	
	background = this;
	 
	     SharedPreferences sharedPreferences = context.getSharedPreferences("EvoPrefsFile",Context.MODE_PRIVATE);
	     
	     imageUri = sharedPreferences.getString("expandedImage","null");
	     if (imageUri == "null") {
	     background.setImageResource(Color.parseColor("#00000000"));	 	    	 
	     } else {
		        background.setImageURI(Uri.parse(imageUri));
	     }
	     
	 mMediaReceiver = new BroadcastReceiver() {
	         @Override
	         public void onReceive(Context c, Intent i) {

	    	     SharedPreferences sharedPreferences = context.getSharedPreferences("EvoPrefsFile",Context.MODE_PRIVATE);    
	    	     imageUri = sharedPreferences.getString("expandedImage","null");
	    	     if (imageUri == "null") {
	    	     background.setImageResource(Color.parseColor("#00000000"));	 	    	 
	    	     } else {
	    		        background.setImageURI(Uri.parse(imageUri));
	    	     }

	         }
	         
	     };
	     
    mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context c, Intent i) {
        	uri = i.getStringExtra("URI");
	        background.setImageURI(Uri.parse(uri));
            SharedPreferences sharedPreferences = context.getSharedPreferences("EvoPrefsFile",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit(); //opens the editor
            editor.putString("expandedImage", uri); //true or false
            editor.commit();	 
        }
        
    };      
     
 
	}

	protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        getContext().registerReceiver(mReceiver, new IntentFilter("SET_BACKGROUND_PICTURE"));
        IntentFilter intentFilter =  new IntentFilter(Intent.ACTION_MEDIA_SCANNER_FINISHED);
        intentFilter.addDataScheme("file");
        getContext().registerReceiver(mMediaReceiver, intentFilter);

    }
    
    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        getContext().unregisterReceiver(mReceiver);
        getContext().unregisterReceiver(mMediaReceiver);

    }
}

