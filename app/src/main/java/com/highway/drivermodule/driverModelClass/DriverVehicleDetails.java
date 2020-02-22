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
    private String vehicleId;
    @SerializedName("vehicleName")
    @Expose
    private String vehicleName;
    @SerializedName("tripAcceptStatus")
    @Expose
    private String tripAcceptStatus;
    @SerializedName("vehicleType")
    @Expose
    private String vehicleType;
    @SerializedName("vehicleTypeId")
    @Expose
    private String vehicleTypeId;
    @SerializedName("DrivrTripCount")
    @Expose
    private Integer driverTripCount;
    @SerializedName("DrivrRating")
    @Expose
    private Integer driverRating;
    @SerializedName("driverId")
    @Expose
    private String driverId;
    @SerializedName("customerId")
    @Expose
    private String customerId;

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

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getTripAcceptStatus() {
        return tripAcceptStatus;
    }

    public void setTripAcceptStatus(String tripAcceptStatus) {
        this.tripAcceptStatus = tripAcceptStatus;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(String vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public Integer getDriverTripCount() {
        return driverTripCount;
    }

    public void setDriverTripCount(Integer driverTripCount) {
        this.driverTripCount = driverTripCount;
    }

    public Integer getDriverRating() {
        return driverRating;
    }

    public void setDriverRating(Integer driverRating) {
        this.driverRating = driverRating;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
