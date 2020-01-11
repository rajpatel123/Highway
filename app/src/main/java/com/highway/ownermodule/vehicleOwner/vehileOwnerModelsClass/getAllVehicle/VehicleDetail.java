
package com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.getAllVehicle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VehicleDetail {

    @SerializedName("VehicleName")
    @Expose
    private String vehicleName;
    @SerializedName("VehicleNumber")
    @Expose
    private String vehicleNumber;
    @SerializedName("VehicleModelNo")
    @Expose
    private String vehicleModelNo;
    @SerializedName("VehicleDescription")
    @Expose
    private String vehicleDescription;
    @SerializedName("VehicleCapacity")
    @Expose
    private String vehicleCapacity;
    @SerializedName("VehicleSize")
    @Expose
    private String vehicleSize;
    @SerializedName("DriverId")
    @Expose
    private Object driverId;
    @SerializedName("DriverName")
    @Expose
    private Object driverName;
    @SerializedName("Mobile")
    @Expose
    private Object mobile;
    @SerializedName("Email")
    @Expose
    private Object email;
    @SerializedName("DLNumber")
    @Expose
    private Object dLNumber;
    @SerializedName("ExpiryDate")
    @Expose
    private Object expiryDate;
    @SerializedName("Address")
    @Expose
    private Object address;
    @SerializedName("Latitude")
    @Expose
    private Object latitude;
    @SerializedName("Longitude")
    @Expose
    private Object longitude;

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleModelNo() {
        return vehicleModelNo;
    }

    public void setVehicleModelNo(String vehicleModelNo) {
        this.vehicleModelNo = vehicleModelNo;
    }

    public String getVehicleDescription() {
        return vehicleDescription;
    }

    public void setVehicleDescription(String vehicleDescription) {
        this.vehicleDescription = vehicleDescription;
    }

    public String getVehicleCapacity() {
        return vehicleCapacity;
    }

    public void setVehicleCapacity(String vehicleCapacity) {
        this.vehicleCapacity = vehicleCapacity;
    }

    public String getVehicleSize() {
        return vehicleSize;
    }

    public void setVehicleSize(String vehicleSize) {
        this.vehicleSize = vehicleSize;
    }

    public Object getDriverId() {
        return driverId;
    }

    public void setDriverId(Object driverId) {
        this.driverId = driverId;
    }

    public Object getDriverName() {
        return driverName;
    }

    public void setDriverName(Object driverName) {
        this.driverName = driverName;
    }

    public Object getMobile() {
        return mobile;
    }

    public void setMobile(Object mobile) {
        this.mobile = mobile;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public Object getDLNumber() {
        return dLNumber;
    }

    public void setDLNumber(Object dLNumber) {
        this.dLNumber = dLNumber;
    }

    public Object getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Object expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public Object getLatitude() {
        return latitude;
    }

    public void setLatitude(Object latitude) {
        this.latitude = latitude;
    }

    public Object getLongitude() {
        return longitude;
    }

    public void setLongitude(Object longitude) {
        this.longitude = longitude;
    }

}
