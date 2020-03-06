package com.highway.drivermodule.driverModelClass;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Manish Bhargav.
 */

public class DriverResponse{

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}

}