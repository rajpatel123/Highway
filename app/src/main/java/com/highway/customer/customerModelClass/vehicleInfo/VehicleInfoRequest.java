package com.highway.customer.customerModelClass.vehicleInfo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VehicleInfoRequest {

@SerializedName("user_id")
@Expose
private String userId;
@SerializedName("vehicle_id")
@Expose
private String vehicleId;

public String getUserId() {
return userId;
}

public void setUserId(String userId) {
this.userId = userId;
}

public String getVehicleId() {
return vehicleId;
}

public void setVehicleId(String vehicleId) {
this.vehicleId = vehicleId;
}

}