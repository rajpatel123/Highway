
package com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.assignD2V.vehicleAssignSpinner;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VehicleData {

    @SerializedName("vehicle_data_name")
    @Expose
    private List<VehicleDataName> vehicleDataName = null;

    public List<VehicleDataName> getVehicleDataName() {
        return vehicleDataName;
    }

    public void setVehicleDataName(List<VehicleDataName> vehicleDataName) {
        this.vehicleDataName = vehicleDataName;
    }

}
