
package com.highway.ownerModule.vehileOwnerModelsClass.assignD2V.vehicleAssignSpinner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VehicleDataName {

    @SerializedName("VehicleNameWithNumberId")
    @Expose
    private String vehicleNameWithNumberId;
    @SerializedName("VehicleNameWithNumber")
    @Expose
    private String vehicleNameWithNumber;

    public String getVehicleNameWithNumberId() {
        return vehicleNameWithNumberId;
    }

    public void setVehicleNameWithNumberId(String vehicleNameWithNumberId) {
        this.vehicleNameWithNumberId = vehicleNameWithNumberId;
    }

    public String getVehicleNameWithNumber() {
        return vehicleNameWithNumber;
    }

    public void setVehicleNameWithNumber(String vehicleNameWithNumber) {
        this.vehicleNameWithNumber = vehicleNameWithNumber;
    }

}
