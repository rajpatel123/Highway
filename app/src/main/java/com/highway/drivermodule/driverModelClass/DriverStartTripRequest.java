package com.highway.drivermodule.driverModelClass;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Manish Bhargav.
 */

public class DriverStartTripRequest{

	@SerializedName("driverId")
	private String driverId;

	@SerializedName("tripId")
	private String tripId;

	@SerializedName("tripStatus")
	private String tripStatus;

	public void setDriverId(String driverId){
		this.driverId = driverId;
	}

	public String getDriverId(){
		return driverId;
	}

	public void setTripId(String tripId){
		this.tripId = tripId;
	}

	public String getTripId(){
		return tripId;
	}

	public void setTripStatus(String tripStatus){
		this.tripStatus = tripStatus;
	}

	public String getTripStatus(){
		return tripStatus;
	}

}