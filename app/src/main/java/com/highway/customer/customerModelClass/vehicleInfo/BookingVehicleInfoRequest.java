package com.highway.customer.customerModelClass.vehicleInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookingVehicleInfoRequest {

@SerializedName("userId")
@Expose
private String userId;
@SerializedName("vehicleTypeId")
@Expose
private String vehicleTypeId;

public String getUserId() {
return userId;
}

public void setUserId(String userId) {
this.userId = userId;
}

public String getVehicleTypeId() {
return vehicleTypeId;
}

public void setVehicleTypeId(String vehicleTypeId) {
this.vehicleTypeId = vehicleTypeId;
}

}