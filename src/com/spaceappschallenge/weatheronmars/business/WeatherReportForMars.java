package com.spaceappschallenge.weatheronmars.business;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import android.content.Context;

import com.spaceappschallenge.weatheronmars.helper.ParserWeatherReportForMars;
import com.spaceappschallenge.weatheronmars.helper.ResourceHelper;

public class WeatherReportForMars
{

	private static final String FILE_NAME = "rems_weather.xml";
	private static final String URL = "http://cab.inta-csic.es/rems/rems_weather.xml";

	public WeatherReportDTO dto;

	public WeatherReportForMars() {
		dto = new WeatherReportDTO();
	}

	public static void download(Context context) throws IOException 
	{
		String FILE_PATH = context.getFilesDir().getAbsolutePath() + "/"
				+ FILE_NAME;
		ResourceHelper.downloadFile(URL, FILE_PATH);
	}

	public static WeatherReportForMars parseXML(Context context) throws ParserConfigurationException, SAXException, IOException{
		String FILE_PATH = context.getFilesDir().getAbsolutePath() + "/"
				+ FILE_NAME;
		
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		ParserWeatherReportForMars handler = new ParserWeatherReportForMars();
		parser.parse(new InputSource("file://" + FILE_PATH), handler);
		
		return handler.getWeatherReport();
	}
	
	public class WeatherReportDTO {
		private String title;
		private String link;
		private String sol;
		private String terrestrialDate;
		private Magnitudes magnitudes;

		public WeatherReportDTO() {
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
