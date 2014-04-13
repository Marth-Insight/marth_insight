package com.spaceappschallenge.weatheronmars;

import java.io.IOException;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.webkit.WebView;
import android.widget.Toast;

import com.spaceappschallenge.weatheronmars.NoConnectionAlertDialog.OnAlertDialogClickListener;
import com.spaceappschallenge.weatheronmars.business.WeatherReportForMars;
import com.spaceappschallenge.weatheronmars.dao.TriviaAdapter;
import com.spaceappschallenge.weatheronmars.helper.DeviceConnection;

public class SplashScreenActivity extends FragmentActivity implements
		OnAlertDialogClickListener 
{

	WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);


		initializeDatabase();

		if (DeviceConnection.isConnected(SplashScreenActivity.this))
		{
			new DownloadTask().execute((Void) null);
		}
		else
		{
			showDialog();
			Toast.makeText(SplashScreenActivity.this, "No Internet",
					Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash_screen, menu);
		return true;
	}

	public void initializeDatabase() {
		boolean isExisting = TriviaAdapter
				.checkDatabase(SplashScreenActivity.this);

		if (!isExisting) {
			TriviaAdapter trivia = new TriviaAdapter(SplashScreenActivity.this);
			trivia.open();

			try {
				trivia.copyDataBase();
			} catch (IOException e) {
				e.printStackTrace();
			}

			trivia.close();
		}

	}

	public class DownloadTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {

			try {
				WeatherReportForMars.download(SplashScreenActivity.this);
			} catch (IOException e) {
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			Intent intent = new Intent(SplashScreenActivity.this,
					MainActivity.class);
			startActivity(intent);
			finish();
		}

	}

	void showDialog() {
		NoConnectionAlertDialog newFragment = new NoConnectionAlertDialog();
		newFragment.show(getSupportFragmentManager(), "dialog");
	}

	@Override
	public void onPositiveClick() {
		startActivity(new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS));
		finish();

	}

	@Override
	public void onNegativeClick() {
		Intent intent = new Intent(SplashScreenActivity.this,
				MainActivity.class);
		startActivity(intent);
		finish();

	}

}
