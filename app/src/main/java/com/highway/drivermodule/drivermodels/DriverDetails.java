package com.highway.drivermodule.drivermodels;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DriverDetails {

@SerializedName("status")
@Expose
private Boolean status;
@SerializedName("tripStatus")
@Expose
private TripStatus driverTripStatus;

public Boolean getStatus() {
return status;
}

public void setStatus(Boolean status) {
this.status = status;
}

public TripStatus getDriverTripStatus() {
return driverTripStatus;
}

public void setDriverTripStatus(TripStatus driverTripStatus) {
this.driverTripStatus = driverTripStatus;
}

}
