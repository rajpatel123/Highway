
package com.highway.ownerModule.vehileOwnerModelsClass.getAllDriver;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("driver_details")
    @Expose
    private List<DriverDetail> driverDetails = null;

    public List<DriverDetail> getDriverDetails() {
        return driverDetails;
    }

    public void setDriverDetails(List<DriverDetail> driverDetails) {
        this.driverDetails = driverDetails;
    }

}
