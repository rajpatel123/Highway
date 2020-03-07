package com.highway.customer.customerModelClass.driverLocation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NearByDriverLocation {

    @SerializedName("driverName")
    @Expose
    private String driverName;
    @SerializedName("driverMobile")
    @Expose
    private String driverMobile;
    @SerializedName("driverLat")
    @Expose
    private String driverLat;
    @SerializedName("driverLong")
    @Expose
    private String driverLong;

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

    public String getDriverLat() {
        return driverLat;
    }

    public void setDriverLat(String driverLat) {
        this.driverLat = driverLat;
    }

    public String getDriverLong() {
        return driverLong;
    }

    public void setDriverLong(String driverLong) {
        this.driverLong = driverLong;
    }
}