
package com.highway.customer.customerModelClass.bookingVehicleList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VInfo {

    @SerializedName("info1")
    @Expose
    private String info1;
    @SerializedName("info2")
    @Expose
    private String info2;
    @SerializedName("info3")
    @Expose
    private String info3;
    @SerializedName("info4")
    @Expose
    private String info4;
    @SerializedName("info5")
    @Expose
    private String info5;
    @SerializedName("info6")
    @Expose
    private String info6;
    @SerializedName("VehicleName")
    @Expose
    private String vehicleName;
    @SerializedName("VehicleCapacity")
    @Expose
    private String vehicleCapacity;
    @SerializedName("VehicleSize")
    @Expose
    private String vehicleSize;

    public String getInfo1() {
        return info1;
    }

    public void setInfo1(String info1) {
        this.info1 = info1;
    }

    public String getInfo2() {
        return info2;
    }

    public void setInfo2(String info2) {
        this.info2 = info2;
    }

    public String getInfo3() {
        return info3;
    }

    public void setInfo3(String info3) {
        this.info3 = info3;
    }

    public String getInfo4() {
        return info4;
    }

    public void setInfo4(String info4) {
        this.info4 = info4;
    }

    public String getInfo5() {
        return info5;
    }

    public void setInfo5(String info5) {
        this.info5 = info5;
    }

    public String getInfo6() {
        return info6;
    }

    public void setInfo6(String info6) {
        this.info6 = info6;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
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

}
