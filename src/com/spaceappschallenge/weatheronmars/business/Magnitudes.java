package com.spaceappschallenge.weatheronmars.business;

public class Magnitudes
{

	public MagnitudesDTO dto;
	
	public Magnitudes() 
	{
		dto = new MagnitudesDTO();
	}

	public class MagnitudesDTO
	{
		private String min_temp;
		private String max_temp;
		private String pressure;
		private String abs_humidity;
		private String wind_speed;
		private String wind_direction;
		private String atmo_opacity;
		private String season;
		private String ls;
		private String sunrise;
		private String sunset;

		public String getMin_temp() 
		{
			return min_temp;
		}

		public void setMin_temp(String min_temp) 
		{
			this.min_temp = min_temp;
		}

		public String getMax_temp() 
		{
			return max_temp;
		}

		public void setMax_temp(String max_temp) 
		{
			this.max_temp = max_temp;
		}

		public String getPressure() 
		{
			return pressure;
		}

		public void setPressure(String pressure)
		{
			this.pressure = pressure;
		}

		public String getAbs_humidity() 
		{
			return abs_humidity;
		}

		public void setAbs_humidity(String abs_humidity)
		{
			this.abs_humidity = abs_humidity;
		}

		public String getWind_direction() 
		{
			return wind_direction;
		}

		public void setWind_direction(String wind_direction) 
		{
			this.wind_direction = wind_direction;
		}

		public String getAtmo_opacity() 
		{
			return atmo_opacity;
		}

		public void setAtmo_opacity(String atmo_opacity)
		{
			this.atmo_opacity = atmo_opacity;
		}

		public String getSeason() 
		{
			return season;
		}

		public void setSeason(String season) 
		{
			this.season = season;
		}

		public String getLs() 
		{
			return ls;
		}

		public void setLs(String ls) {
			this.ls = ls;
		}

		public String getSunrise() {
			return sunrise;
		}

		public void setSunrise(String sunrise) {
			this.sunrise = sunrise;
		}

		public String getSunset() {
			return sunset;
		}

		public void setSunset(String sunset) {
			this.sunset = sunset;
		}

		public String getWind_speed() {
			return wind_speed;
		}

		public void setWind_speed(String wind_speed) {
			this.wind_speed = wind_speed;
		}

	}
}
