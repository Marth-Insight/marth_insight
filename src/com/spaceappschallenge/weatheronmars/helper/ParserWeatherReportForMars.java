package com.spaceappschallenge.weatheronmars.helper;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.spaceappschallenge.weatheronmars.business.WeatherReportForMars;

public class ParserWeatherReportForMars extends DefaultHandler 
{

	private WeatherReportForMars report;
	private StringBuilder builder;

	public WeatherReportForMars getWeatherReport() 
	{
		return this.report;
	}

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();

		builder = new StringBuilder();
		report = new WeatherReportForMars();
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		super.characters(ch, start, length);

		builder.append(ch, start, length);
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		super.endElement(uri, localName, qName);

		String str = builder.toString().trim();

		if (localName.equalsIgnoreCase("title"))
			this.report.dto.setTitle(str);
		else if (localName.equalsIgnoreCase("link"))
			this.report.dto.setLink(str);
		else if (localName.equalsIgnoreCase("sol"))
			this.report.dto.setSol(str);
		else if (localName.equalsIgnoreCase("terrestrial_date"))
			this.report.dto.setTerrestrialDate(str);
		else if (localName.equals("min_temp"))
			this.report.dto.getMagnitudes().dto.setMin_temp(str);
		else if (localName.equalsIgnoreCase("max_temp"))
			this.report.dto.getMagnitudes().dto.setMax_temp(str);
		else if (localName.equalsIgnoreCase("pressure"))
			this.report.dto.getMagnitudes().dto.setPressure(str);
		else if (localName.equalsIgnoreCase("abs_humidity"))
			this.report.dto.getMagnitudes().dto.setAbs_humidity(str);
		else if (localName.equalsIgnoreCase("wind_speed"))
			this.report.dto.getMagnitudes().dto.setWind_speed(str);
		else if (localName.equalsIgnoreCase("wind_direction"))
			this.report.dto.getMagnitudes().dto.setWind_direction(str);
		else if (localName.equalsIgnoreCase("atmo_opacity"))
			this.report.dto.getMagnitudes().dto.setAtmo_opacity(str);
		else if (localName.equalsIgnoreCase("season"))
			this.report.dto.getMagnitudes().dto.setSeason(str);
		else if (localName.equalsIgnoreCase("ls"))
			this.report.dto.getMagnitudes().dto.setLs(str);
		else if (localName.equalsIgnoreCase("sunrise"))
			this.report.dto.getMagnitudes().dto.setSunrise(str);
		else if (localName.equalsIgnoreCase("sunset"))
			this.report.dto.getMagnitudes().dto.setSunset(str);

		builder.setLength(0);
	}
}
