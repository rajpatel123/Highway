
package com.highway.ownerModule.vehileOwnerModelsClass.vehicleTypeDropDowan;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("vehicle_data")
    @Expose
    private List<VehicleDatum> vehicleData = null;

    public List<VehicleDatum> getVehicleData() {
        return vehicleData;
    }

    public void setVehicleData(List<VehicleDatum> vehicleData) {
        this.vehicleData = vehicleData;
    }

}
