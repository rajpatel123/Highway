
package com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.DriverDropDown_Spinners;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DriverDatum {

    @SerializedName("DriverId")
    @Expose
    private String driverId;
    @SerializedName("DriverName")
    @Expose
    private String driverName;

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

}
