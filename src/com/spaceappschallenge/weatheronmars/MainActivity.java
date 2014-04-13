package com.spaceappschallenge.weatheronmars;

import java.io.IOException;
import java.util.Random;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.spaceappschallenge.weatheronmars.business.Magnitudes.MagnitudesDTO;
import com.spaceappschallenge.weatheronmars.business.WeatherReportForMars;
import com.spaceappschallenge.weatheronmars.dao.TriviaAdapter;

public class MainActivity extends FragmentActivity 
{

	TextView tvDate, tvSunrise, tvSunset;
	TextView tvTemperature, tvPressure, tvHumidity;
	TextView tvWindSpeed, tvSeason;

	WeatherReportForMars wr;
	String user = "MarsWxReport";

	boolean isRefreshTaskRunning = false;
	boolean isTimelineTaskRunning = false;
	ProgressDialog pbLoading;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tvDate = (TextView) findViewById(R.id.tvDate);
		tvPressure = (TextView) findViewById(R.id.tvPressure);
		tvHumidity = (TextView) findViewById(R.id.tvHumidity);
		tvWindSpeed = (TextView) findViewById(R.id.tvWindSpeed);
		tvTemperature = (TextView) findViewById(R.id.tvTemperature);
		tvSeason = (TextView) findViewById(R.id.tvSeason);
		tvSunrise = (TextView) findViewById(R.id.tvSunrise);
		tvSunset = (TextView) findViewById(R.id.tvSunset);

		new parseXMLMarsTask().execute((Void) null);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


	public class parseXMLMarsTask extends AsyncTask<Void, Void, Void> 
	{

		int error = 0;

		@Override
		protected Void doInBackground(Void... params) 
		{
			try {
				wr = WeatherReportForMars.parseXML(MainActivity.this);
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
				error = 1;
			} catch (SAXException e) {
				e.printStackTrace();
				error = 1;
			} catch (IOException e) {
				e.printStackTrace();
				error = 1;
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);

			if (error == 0) {

				tvDate.setText(wr.dto.getTerrestrialDate());
				tvSunrise.setText(wr.dto.getMagnitudes().dto.getSunrise());
				tvSunset.setText(wr.dto.getMagnitudes().dto.getSunset());

				MagnitudesDTO mdto = wr.dto.getMagnitudes().dto;
				tvTemperature.setText(mdto.getMin_temp() + " C - "
						+ mdto.getMax_temp() + " C");
				tvPressure.setText(mdto.getPressure() + " Pa");
				tvHumidity.setText(mdto.getAbs_humidity());
				tvWindSpeed.setText(mdto.getWind_speed() + " m/s "
						+ mdto.getWind_direction());
				tvSeason.setText(mdto.getSeason());
			}
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
	}

	public class RefreshTask extends AsyncTask<Void, Void, Void> 
	{

		@Override
		protected void onPreExecute()
		{
			super.onPreExecute();

			isRefreshTaskRunning = true;
		}

		@Override
		protected Void doInBackground(Void... params)
		{

			try
			{
				WeatherReportForMars.download(MainActivity.this);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			isRefreshTaskRunning = false;
			new parseXMLMarsTask().execute((Void) null);
			if (!isTimelineTaskRunning)
				pbLoading.dismiss();
		}

	}



	public void showTrivia(View view) 
	{

		// query
		String result = null;

		TriviaAdapter adapter = new TriviaAdapter(this);
		adapter.open();
		adapter.createTrivia();

		// get a random trivia from all
		// result = adapter.TriviaResult();

		// get a random trivia based on group_name
		String[] categories = { "Temperature", "Pressure", "Wind Speed",
				"General" };
		Random r = new Random();
		String category = categories[r.nextInt(categories.length)];

		Log.v("Category", "cat: " + category);
		category = "General";

		if (category == "Temperature") 
		{
			String minTemp = wr.dto.getMagnitudes().dto.getMin_temp();
			String maxTemp = wr.dto.getMagnitudes().dto.getMax_temp();

			result = adapter.TriviaResult(category, minTemp, maxTemp, null);
		}
		else if (category == "Pressure")
		{
			String value = wr.dto.getMagnitudes().dto.getPressure();

			result = adapter.TriviaResult(category, null, null, value);
		}
		else if (category == "Wind Speed")
		{
			String value = wr.dto.getMagnitudes().dto.getWind_speed();

			result = adapter.TriviaResult(category, null, null, value);
		}
		else 
		{

			result = adapter.TriviaResult(category, null, null, null);
		}
		
		LayoutInflater inflater = getLayoutInflater();
		View layout = inflater.inflate(R.layout.toast_layout,
				(ViewGroup) findViewById(R.id.toast_layout_root));

		TextView tv = (TextView) layout.findViewById(R.id.tvTriviaDesc);
		tv.setText(result);

		Toast toast = new Toast(MainActivity.this);
		toast.setGravity(Gravity.BOTTOM, 0, 0);
		toast.setDuration(6000);
		toast.setView(layout);
		toast.show();

	}
	
	public void changeToEarth(View view)
	{
		Intent changetoEarth=new Intent(MainActivity.this,MainEarthActivity.class);
		startActivity(changetoEarth);
	}
}
