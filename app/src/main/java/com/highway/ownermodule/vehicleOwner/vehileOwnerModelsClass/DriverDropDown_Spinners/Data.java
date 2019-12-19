
package com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.DriverDropDown_Spinners;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

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
