
package com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.assignD2V.vehicleAssignSpinner;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataV {

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
