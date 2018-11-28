package com.guidezmoi.entity;


import com.fasterxml.jackson.annotation.JsonView;

public class Coordonnee {

	@JsonView(Views.Public.class)
	private double Latitude;
	@JsonView(Views.Public.class)
	private double Longitude;
	
	public double getLatitude() {
		return Latitude;
	}
	
	public void setLatitude(double latitude) {
		this.Latitude = latitude;
	}

	public double getLongitude() {
		return Longitude;
	}
	
	public void setLongitude(double longitude) {
		this.Longitude = longitude;
	}
}
