package com.highway.drivermodule.driverModelClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DriverVehicleDetails {

    @SerializedName("driverName")
    @Expose
    private String driverName;
    @SerializedName("driverMobile")
    @Expose
    private String driverMobile;
    @SerializedName("vehicleId")
    @Expose
    private Integer vehicleId;
    @SerializedName("vehicleName")
    @Expose
    private String vehicleName;

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverMobile() {
        return driverMobile;
    }

    public void setDriverMobile(String driverMobile) {
        this.driverMobile = driverMobile;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }
}
