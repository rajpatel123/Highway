package com.highway.ownerModule.vehileOwnerModelsClass.assignD2V.assignD2V_Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AssignD2VRequest {

@SerializedName("owner_id")
@Expose
private String ownerId;
@SerializedName("driver_id")
@Expose
private String driverId;
@SerializedName("vehicle_id")
@Expose
private String vehicleId;

public String getOwnerId() {
return ownerId;
}

public void setOwnerId(String ownerId) {
this.ownerId = ownerId;
}

public String getDriverId() {
return driverId;
}

public void setDriverId(String driverId) {
this.driverId = driverId;
}

public String getVehicleId() {
return vehicleId;
}

public void setVehicleId(String vehicleId) {
this.vehicleId = vehicleId;
}

}