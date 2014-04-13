package com.spaceappschallenge.weatheronmars.business;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import android.content.Context;

import com.spaceappschallenge.weatheronmars.helper.ParserWeatherReportForEarth;
import com.spaceappschallenge.weatheronmars.helper.ResourceHelper;

public class WeatherReportForEarth
{

	private static final String FILE_NAME = "earth_weather.xml";
	private static final String URL = "http://api.openweathermap.org/data/2.5/forecast?q=Bangalore,india&mode=xml";

	public WeatherReportEarthDTO dto;

	public WeatherReportForEarth()
	{
		dto = new WeatherReportEarthDTO();
	}

	public static void downloadEarthData(Context context) throws IOException 
	{
		String FILE_PATH = context.getFilesDir().getAbsolutePath() + "/"
				+ FILE_NAME;
		ResourceHelper.downloadFile(URL, FILE_PATH);
	}

	public static WeatherReportForEarth parseXMLForEarth(Context context) throws ParserConfigurationException, SAXException, IOException{
		String FILE_PATH = context.getFilesDir().getAbsolutePath() + "/"
				+ FILE_NAME;
		
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		ParserWeatherReportForEarth handler = new ParserWeatherReportForEarth();
		parser.parse(new InputSource("file://" + FILE_PATH), handler);
		
		return handler.getWeatherReport();
	}
	
	public class WeatherReportEarthDTO
	{
		private String title;
		private String link;
		private String sol;
		private String terrestrialDate;
		private Magnitudes magnitudes;

		public WeatherReportEarthDTO() {
			this.magnitudes = new Magnitudes();
		}
		
		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getLink() {
			return link;
		}

		public void setLink(String link) {
			this.link = link;
		}

		public String getSol() {
			return sol;
		}

		public void setSol(String sol) {
			this.sol = sol;
		}

		public String getTerrestrialDate() {
			return terrestrialDate;
		}

		public void setTerrestrialDate(String terrestrialDate) {
			this.terrestrialDate = terrestrialDate;
		}

		public Magnitudes getMagnitudes() {
			return magnitudes;
		}

		public void setMagnitudes(Magnitudes magnitudes) {
			this.magnitudes = magnitudes;
		}
	}
}
