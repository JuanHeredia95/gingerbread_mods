package com.b16h22.statusbar;

import android.os.Handler;
import android.content.Context;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.widget.TextView;

public class DateTime extends TextView {

	public DateTime(final Context context, AttributeSet attrs) {
	super(context, attrs);	

	
    final Handler h = new Handler();
    h.post(new Runnable() {
        @Override
        public void run() {
            updateTime(context);
            h.postDelayed(this, 1000);
        }
    }); 
	}
	protected void updateTime(final Context context) {
		TextView textView = (TextView) findViewById(R.id.date);
		textView.setText(DateUtils.formatDateTime(context, System.currentTimeMillis(), DateUtils.LENGTH_LONG | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_WEEKDAY));
        }

}
