package com.spaceappschallenge.weatheronmars.helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class DeviceConnection {

	/**
	 * Returns true if there is an internet connection available, otherwise
	 * false
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isConnected(Context context) {
		
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo[] allNetworks = manager.getAllNetworkInfo();

		for (NetworkInfo networkInfo : allNetworks) {
			if (networkInfo.isAvailable() && networkInfo.isConnected()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets the connection type available
	 * 
	 * @param context
	 * @return
	 */
	public static String getConnectionType(Context context) {
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = manager.getActiveNetworkInfo();
		return networkInfo.getTypeName();
	}

}
