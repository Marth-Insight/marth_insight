package com.spaceappschallenge.weatheronmars;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.widget.TextView;

public class ChristmasTimerActivity extends Activity
{
	 private CountDownTimer countDownTimer;
	 private boolean timerHasStarted = false;
	 public TextView timerText;
	 public TextView daysText;
	 private final long startTime = 194 * 1000;
	 private final long interval = 86400 * 1000; 
	 
	 @Override
	  public void onCreate(Bundle savedInstanceState) 
	 {
	   super.onCreate(savedInstanceState);
	   setContentView(R.layout.activity_earth_main);
	   timerText = (TextView) this.findViewById(R.id.christmasTimer);
	   daysText = (TextView) this.findViewById(R.id.LabelToChristmas);
	   countDownTimer = new MyCountDownTimer(startTime, interval);
	   countDownTimer.start();
	   timerText.setText(timerText.getText() + String.valueOf(startTime / 1000));
	 }
	  
	 @Override
	public boolean onCreateOptionsMenu(Menu menu)
	 {
			getMenuInflater().inflate(R.menu.main, menu);
			return true;
	}
	 
	 public class MyCountDownTimer extends CountDownTimer 
	 {
		   public MyCountDownTimer(long startTime, long interval) 
		   {
		    super(startTime, interval);
		   }
		   
		   @Override
		   public void onFinish() 
		   {
		    timerText.setText("Christmas on Mars!");
		    daysText.setText("");
		   }
		   
		   @Override
		   public void onTick(long millisUntilFinished) 
		   {
		    timerText.setText("" + millisUntilFinished / 1000);
		   }
	 }
		 

	 
}
