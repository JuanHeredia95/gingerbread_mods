package com.b16h22.statusbar;

import android.os.IBinder;
import android.widget.Toast;
import android.app.Service;
import android.content.Context;
import android.content.Intent;

public class StatusBarService extends Service {

    public void animateCollapse()
    {
    	Toast.makeText(getApplicationContext(),"Your message.", Toast.LENGTH_LONG).show();
    }	

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
