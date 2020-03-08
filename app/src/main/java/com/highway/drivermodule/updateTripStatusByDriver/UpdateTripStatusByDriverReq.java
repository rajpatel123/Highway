package com.highway.drivermodule.updateTripStatusByDriver;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateTripStatusByDriverReq {

@SerializedName("trip_id")
@Expose
private String tripId;
@SerializedName("driver_id")
@Expose
private String driverId;
@SerializedName("TRIP_STATS")
@Expose
private String tRIPSTATS;
@SerializedName("updatedAt")
@Expose
private String updatedAt;

public String getTripId() {
return tripId;
}

public void setTripId(String tripId) {
this.tripId = tripId;
}

public String getDriverId() {
return driverId;
}

public void setDriverId(String driverId) {
this.driverId = driverId;
}

public String getTRIPSTATS() {
return tRIPSTATS;
}

public void setTRIPSTATS(String tRIPSTATS) {
this.tRIPSTATS = tRIPSTATS;
}

public String getUpdatedAt() {
return updatedAt;
}

public void setUpdatedAt(String updatedAt) {
this.updatedAt = updatedAt;
}

}