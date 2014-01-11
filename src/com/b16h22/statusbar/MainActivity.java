package com.b16h22.statusbar;

import java.io.FileNotFoundException;
import java.io.IOException;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends Activity {
	
	ImageView imageView;	
	Uri uri;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		  Button page = (Button) findViewById(R.id.pickerpage);
		  imageView = (ImageView) findViewById(R.id.image);
		  page.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent intent = new Intent();
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					intent.setComponent(new ComponentName("com.b16h22.statusbar", "com.b16h22.statusbar.QuickSettingsFlipper"));
				    v.getContext().startActivity(intent);
		        }
		});		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void message(View v) {
	Intent intent = new Intent("android.intent.action.SEARCH");
    startActivity(intent);
}
	public void blue(View v) {
	Intent intent = new Intent();
	intent.setAction("com.b16h22.statusbar.CHANGE_BACKGROUND");
	sendBroadcast(intent);

}	
	public void dark(View v) {
		
	Intent intent = new Intent();
	String color="#aa0099bc";
	intent.setAction("com.b16h22.statusbar.CHANGE_BACKGROUND_DARK");
	intent.putExtra("color",color.toString());
	sendBroadcast(intent);

}
    public void updateExpandedViewPos(int i)
    {

    }	
    public void run()
    {
        final Handler h = new Handler();
        h.post(new Runnable() {
            @Override
            public void run() {
            	updateExpandedViewPos(-1001);
                h.postDelayed(this, 1000);
            }
        }); 
    }	  
}
