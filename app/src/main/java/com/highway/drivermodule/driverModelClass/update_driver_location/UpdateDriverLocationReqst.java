package com.highway.drivermodule.driverModelClass.update_driver_location;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateDriverLocationReqst {

@SerializedName("driverId")
@Expose
private String driverId;
@SerializedName("lat")
@Expose
private double lat;
@SerializedName("long")
@Expose
private double _long;

public String getDriverId() {
return driverId;
}

public void setDriverId(String driverId) {
this.driverId = driverId;
}

public double getLat() {
return lat;
}

public void setLat(double lat) {
this.lat = lat;
}

public double getLong() {
return _long;
}

public void setLong(double _long) {
this._long = _long;
}

}