package com.b16h22.statusbar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Profile extends Activity {
	
	ImageView imageView;
	ImageView done;
	EditText name;
	Uri uri;
	String imageUri;
	String profName;
	TextView owner;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_info);
		
		  ImageView photo = (ImageView) findViewById(R.id.photo_picker);
		  owner = (TextView) findViewById(R.id.Owner);
		  name = (EditText) findViewById(R.id.name_field);
		  done = (ImageView) findViewById(R.id.name);
		  imageView = (ImageView) findViewById(R.id.image);
		
		  //checks shared preferences
		     SharedPreferences sharedPreferences = getSharedPreferences("EvoPrefsFile",Context.MODE_PRIVATE); 
		     profName = sharedPreferences.getString("profileName","null");
		     if (profName == "null") {
					owner.setText("Owner"); 					
			     } else {
			    	owner.setText(profName);
			    	name.setText(profName); 
			     }
		     
		     imageUri = sharedPreferences.getString("profilePic","null");
		     if (imageUri == "null") {
		    	 imageView.setImageResource(R.drawable.ic_qs_default_user);	 	    	 
			     } else {
			    	 imageView.setImageURI(Uri.parse(imageUri));
			     }
		     
		  photo.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent intent = new Intent();
					intent.setType("image/*");
					intent.setAction(Intent.ACTION_GET_CONTENT);
					startActivityForResult(Intent.createChooser(intent, "Select Picture"),0);
		        }
		});
		  
		  done.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					Editable profileName = name.getText();
					owner.setText(profileName.toString());
					Intent intent = new Intent();
					intent.setAction("com.b16h22.statusbar.CHANGE_PROFILE_NAME");
					intent.putExtra("NAME", profileName.toString() );
					sendBroadcast(intent);
			        SharedPreferences sharedPreferences = getSharedPreferences("EvoPrefsFile",Context.MODE_PRIVATE);
			        SharedPreferences.Editor editor = sharedPreferences.edit(); //opens the editor
			        editor.putString("profileName", profileName.toString()); //true or false
			        editor.commit();
		        }
		});
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	switch (requestCode) {
	case 0:
	    if (resultCode == RESULT_OK) {
	    uri = Uri.parse(data.getDataString());
	    imageView.setImageURI(uri);
        SharedPreferences sharedPreferences = getSharedPreferences("EvoPrefsFile",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit(); //opens the editor
        editor.putString("profilePic", uri.toString()); //true or false
        editor.commit();	
	    Intent i = new Intent();
		i.setAction("com.b16h22.statusbar.CHANGE_PROFILE_PICTURE");
	    i.putExtra("URI", uri.toString() );
		sendBroadcast(i);
	    }
	    break;
	    }
	}

}

