
package com.highway.ownerModule.vehileOwnerModelsClass.assignD2V.driverAssignSpinner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataModel {

    @SerializedName("driver_data")
    @Expose
    private List<DriverDatum> driverData = null;

    public List<DriverDatum> getDriverData() {
        return driverData;
    }

    public void setDriverData(List<DriverDatum> driverData) {
        this.driverData = driverData;
    }

}
