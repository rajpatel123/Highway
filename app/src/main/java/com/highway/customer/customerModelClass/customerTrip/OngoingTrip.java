
package com.highway.customer.customerModelClass.customerTrip;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OngoingTrip {

    @SerializedName("sourceLat")
    @Expose
    private String sourceLat;
    @SerializedName("sourceLong")
    @Expose
    private String sourceLong;
    @SerializedName("destinationLat")
    @Expose
    private String destinationLat;
    @SerializedName("destinationLong")
    @Expose
    private String destinationLong;
    @SerializedName("driverName")
    @Expose
    private String driverName;
    @SerializedName("vehicleName")
    @Expose
    private String vehicleName;
    @SerializedName("vehicleNumber")
    @Expose
    private String vehicleNumber;
    @SerializedName("fare")
    @Expose
    private String fare;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("startDate")
    @Expose
    private String startDate;
    @SerializedName("endDate")
    @Expose
    private String endDate;
    @SerializedName("pickupTime")
    @Expose
    private String pickupTime;
    @SerializedName("dropTime")
    @Expose
    private String dropTime;

    public String getSourceLat() {
        return sourceLat;
    }

    public void setSourceLat(String sourceLat) {
        this.sourceLat = sourceLat;
    }

    public String getSourceLong() {
        return sourceLong;
    }

    public void setSourceLong(String sourceLong) {
        this.sourceLong = sourceLong;
    }

    public String getDestinationLat() {
        return destinationLat;
    }

    public void setDestinationLat(String destinationLat) {
        this.destinationLat = destinationLat;
    }

    public String getDestinationLong() {
        return destinationLong;
    }

    public void setDestinationLong(String destinationLong) {
        this.destinationLong = destinationLong;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

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

    public String getFare() {
        return fare;
    }

    public void setFare(String fare) {
        this.fare = fare;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
    }

    public String getDropTime() {
        return dropTime;
    }

    public void setDropTime(String dropTime) {
        this.dropTime = dropTime;
    }

}
