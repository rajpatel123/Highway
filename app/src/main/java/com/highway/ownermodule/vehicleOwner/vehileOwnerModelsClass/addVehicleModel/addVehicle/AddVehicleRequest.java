package com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.addVehicleModel.addVehicle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddVehicleRequest {

@SerializedName("owner_id")
@Expose
private String ownerId;
@SerializedName("vehicleNumber")
@Expose
private String vehicleNumber;
@SerializedName("vehicle_type_id")
@Expose
private String vehicleTypeId;
@SerializedName("vehicleDescription")
@Expose
private String vehicleDescription;
@SerializedName("vehicleModelNo")
@Expose
private String vehicleModelNo;
@SerializedName("vehicleCapacity")
@Expose
private String vehicleCapacity;
@SerializedName("vehicleSize")
@Expose
private String vehicleSize;

public String getOwnerId() {
return ownerId;
}

public void setOwnerId(String ownerId) {
this.ownerId = ownerId;
}

public String getVehicleNumber() {
return vehicleNumber;
}

public void setVehicleNumber(String vehicleNumber) {
this.vehicleNumber = vehicleNumber;
}

public String getVehicleTypeId() {
return vehicleTypeId;
}

public void setVehicleTypeId(String vehicleTypeId) {
this.vehicleTypeId = vehicleTypeId;
}

public String getVehicleDescription() {
return vehicleDescription;
}

public void setVehicleDescription(String vehicleDescription) {
this.vehicleDescription = vehicleDescription;
}

public String getVehicleModelNo() {
return vehicleModelNo;
}

public void setVehicleModelNo(String vehicleModelNo) {
this.vehicleModelNo = vehicleModelNo;
}

public String getVehicleCapacity() {
return vehicleCapacity;
}

public void setVehicleCapacity(String vehicleCapacity) {
this.vehicleCapacity = vehicleCapacity;
}

public String getVehicleSize() {
return vehicleSize;
}

public void setVehicleSize(String vehicleSize) {
this.vehicleSize = vehicleSize;
}

}