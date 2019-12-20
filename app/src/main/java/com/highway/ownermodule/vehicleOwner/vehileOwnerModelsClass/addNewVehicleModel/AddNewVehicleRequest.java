package com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.addNewVehicleModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddNewVehicleRequest {

@SerializedName("owner_id")
@Expose
private String ownerId;
@SerializedName("vehicleName")
@Expose
private String vehicleName;
@SerializedName("vehicleNumber")
@Expose
private String vehicleNumber;
@SerializedName("vehicleModelNo")
@Expose
private String vehicleModelNo;
@SerializedName("vehicleDescription")
@Expose
private String vehicleDescription;
@SerializedName("driverId")
@Expose
private String driverId;

public String getOwnerId() {
return ownerId;
}

public void setOwnerId(String ownerId) {
this.ownerId = ownerId;
}

public String getVehicleName() {
return vehicleName;
}

public void setVehicleName(String vehicleName) {
this.vehicleName = vehicleName;
}

public String getVehicleNumber() {
return vehicleNumber;
}

public void setVehicleNumber(String vehicleNumber) {
this.vehicleNumber = vehicleNumber;
}

public String getVehicleModelNo() {
return vehicleModelNo;
}

public void setVehicleModelNo(String vehicleModelNo) {
this.vehicleModelNo = vehicleModelNo;
}

public String getVehicleDescription() {
return vehicleDescription;
}

public void setVehicleDescription(String vehicleDescription) {
this.vehicleDescription = vehicleDescription;
}

public String getDriverId() {
return driverId;
}

public void setDriverId(String driverId) {
this.driverId = driverId;
}

}