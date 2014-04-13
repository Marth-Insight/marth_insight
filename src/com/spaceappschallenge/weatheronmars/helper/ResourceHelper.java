package com.spaceappschallenge.weatheronmars.helper;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.content.Context;

public class ResourceHelper {
	public static String loadManySerialized(String url) throws ClientProtocolException, IOException, ConnectTimeoutException, SocketTimeoutException {
		int connectiontimeout = 30000; 
		int sockettimeout = 30000;
		
		HttpParams httpParameters = new BasicHttpParams(); 
		
		HttpConnectionParams.setConnectionTimeout(httpParameters, connectiontimeout);
		HttpConnectionParams.setSoTimeout(httpParameters, sockettimeout);
		
		HttpClient httpclient = new DefaultHttpClient(httpParameters);

		// Prepare a request object
		HttpGet httpget = new HttpGet(url);

		// Execute the request
		HttpResponse response;

		String result = null;
			response = httpclient.execute(httpget);

			// Get hold of the response entity
			HttpEntity entity = response.getEntity();
			// If the response does not enclose an entity, there is no need
			// to worry about connection release

			if (entity != null) {
				// A Simple Response Read
				InputStream instream = entity.getContent();
				result = convertStreamToString(instream);

				// Closing the input stream will trigger connection release
				instream.close();
			}
		

		return result;
	}

	public static String convertStreamToString(InputStream is) throws IOException {
		/*
		 * To convert the InputStream to String we use the
		 * BufferedReader.readLine() method. We iterate until the BufferedReader
		 * return null which means there's no more data to read. Each line will
		 * appended to a StringBuilder and returned as String.
		 */
		
		String error = null; 
		BufferedReader reader = new BufferedReader(new InputStreamReader(is),
				8192);
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			error = e.getMessage();

		} finally {
			try {
				is.close();
			} catch (IOException e) {
				if (error != null)
					throw e;
			}
		}
		return sb.toString();
	}

	public static void downloadFile(String url, String filePath)
			throws IOException {

		URL mURL = new URL(url);
		InputStream is = mURL.openStream();

		DataInputStream dis = new DataInputStream(is);

		// download and writefile
		File file = new File(filePath);
		byte[] buffer = new byte[2048];
		int length;

		FileOutputStream fout = new FileOutputStream(file);
		BufferedOutputStream bufferOut = new BufferedOutputStream(fout,
				buffer.length);

		while ((length = dis.read(buffer, 0, buffer.length)) != -1)
			bufferOut.write(buffer, 0, length);

		bufferOut.flush();
		bufferOut.close();

		dis.close();
		fout.close();
	}

	public static String openAssetFile(Context mContext, int rawId)
			throws IOException {
		InputStream instream = mContext.getResources().openRawResource(rawId);
		ByteArrayOutputStream byteArray = new ByteArrayOutputStream();

		int i;
		i = instream.read();
		while (i != -1) {
			byteArray.write(i);
		}
		instream.close();

		return byteArray.toString();
	}

	public static void convertStringToFile(String str, String filePath)
			throws IOException {

		InputStream is = new ByteArrayInputStream(str.getBytes(Charset
				.defaultCharset()));

		DataInputStream dis = new DataInputStream(is);

		// download and writefile
		File file = new File(filePath);
		byte[] buffer = new byte[2048];
		int length;

		FileOutputStream fout = new FileOutputStream(file);
		BufferedOutputStream bufferOut = new BufferedOutputStream(fout,
				buffer.length);

		while ((length = dis.read(buffer, 0, buffer.length)) != -1)
			bufferOut.write(buffer, 0, length);

		bufferOut.flush();
		bufferOut.close();

		dis.close();
		fout.close();
	}
	
	public static String openRawFile(Context mContext, int rawId)
			throws IOException {
		
		InputStream instream = mContext.getResources().openRawResource(rawId);
		
		InputStreamReader inputReader = new InputStreamReader(instream);
		BufferedReader buffreader = new BufferedReader(inputReader);
		String line; 
		StringBuilder text = new StringBuilder(); 
		
		while ((line = buffreader.readLine()) != null) {
			text.append(line);
		}
		
		return text.toString();
	}
}