package com.highway.drivermodule.driverModelClass;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Manish Bhargav.
 */

public class VehicleCurrentLocation{

	@SerializedName("currentLat")
	private String currentLat;

	@SerializedName("driverId")
	private String driverId;

	@SerializedName("currentLong")
	private String currentLong;

	@SerializedName("tripId")
	private String tripId;

	public void setCurrentLat(String currentLat){
		this.currentLat = currentLat;
	}

	public String getCurrentLat(){
		return currentLat;
	}

	public void setDriverId(String driverId){
		this.driverId = driverId;
	}

	public String getDriverId(){
		return driverId;
	}

	public void setCurrentLong(String currentLong){
		this.currentLong = currentLong;
	}

	public String getCurrentLong(){
		return currentLong;
	}

	public void setTripId(String tripId){
		this.tripId = tripId;
	}

	public String getTripId(){
		return tripId;
	}

}