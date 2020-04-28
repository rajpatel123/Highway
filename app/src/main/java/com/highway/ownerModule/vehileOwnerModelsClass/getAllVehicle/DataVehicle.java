
package com.highway.ownerModule.vehileOwnerModelsClass.getAllVehicle;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataVehicle {

    @SerializedName("vehicle_details")
    @Expose
    private List<VehicleDetail> vehicleDetails = null;

    public List<VehicleDetail> getVehicleDetails() {
        return vehicleDetails;
    }

    public void setVehicleDetails(List<VehicleDetail> vehicleDetails) {
        this.vehicleDetails = vehicleDetails;
    }

}
