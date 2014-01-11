package com.b16h22.statusbar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView;

public class ProfilePicture extends ImageView {
	
    ImageView profilePicture;
    String uri;
    String imageUri;
	public ProfilePicture(final Context context, AttributeSet attrs) {
	super(context, attrs); 
	
	
	profilePicture = (ImageView) findViewById(R.id.profile);
	 
	     SharedPreferences sharedPreferences = context.getSharedPreferences("EvoPrefsFile",Context.MODE_PRIVATE);    
	     imageUri = sharedPreferences.getString("profilePic","null");
	     if (imageUri == "null") {
	     profilePicture.setImageResource(R.drawable.ic_qs_default_user);	 	    	 
	     } else {
		        profilePicture.setImageURI(Uri.parse(imageUri));
	     }
	 BroadcastReceiver mMediaReceiver = new BroadcastReceiver() {
	         @Override
	         public void onReceive(Context c, Intent i) {

	    	     SharedPreferences sharedPreferences = context.getSharedPreferences("EvoPrefsFile",Context.MODE_PRIVATE);    
	    	     imageUri = sharedPreferences.getString("profilePic","null");
	    	     if (imageUri == "null") {
	    	     profilePicture.setImageResource(R.drawable.ic_qs_default_user);	 	    	 
	    	     } else {
	    		        profilePicture.setImageURI(Uri.parse(imageUri));
	    	     }

	         }
	         
	     };
    BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context c, Intent i) {
        	uri = i.getStringExtra("URI");
	        profilePicture.setImageURI(Uri.parse(uri));
            SharedPreferences sharedPreferences = context.getSharedPreferences("EvoPrefsFile",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit(); //opens the editor
            editor.putString("profilePic", uri); //true or false
            editor.commit();	 
        }
        
    };      
    
    context.registerReceiver(mReceiver, new IntentFilter("com.b16h22.statusbar.CHANGE_PROFILE_PICTURE"));  
    IntentFilter intentFilter =  new IntentFilter(Intent.ACTION_MEDIA_SCANNER_FINISHED);
            intentFilter.addDataScheme("file");
    		context.registerReceiver(mMediaReceiver, intentFilter);
	}


}
