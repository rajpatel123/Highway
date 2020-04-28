
package com.highway.ownerModule.vehileOwnerModelsClass.vehicleTypeDropDowan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VehicleDatum {

    @SerializedName("VehicleTypeId")
    @Expose
    private String vehicleTypeId;
    @SerializedName("VehicleName")
    @Expose
    private String vehicleName;

    public String getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(String vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

}
