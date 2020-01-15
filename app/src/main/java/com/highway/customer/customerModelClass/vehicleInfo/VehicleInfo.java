
package com.highway.customer.customerModelClass.vehicleInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VehicleInfo {

    @SerializedName("VehicleId")
    @Expose
    private String vehicleId;
    @SerializedName("VehicleName")
    @Expose
    private String vehicleName;
    @SerializedName("VehicleCapacity")
    @Expose
    private Object vehicleCapacity;
    @SerializedName("VehicleSize")
    @Expose
    private Object vehicleSize;
    @SerializedName("v_info1")
    @Expose
    private String vInfo1;
    @SerializedName("v_info2")
    @Expose
    private String vInfo2;
    @SerializedName("v_info3")
    @Expose
    private String vInfo3;
    @SerializedName("v_info4")
    @Expose
    private String vInfo4;
    @SerializedName("v_info5")
    @Expose
    private String vInfo5;
    @SerializedName("v_info6")
    @Expose
    private String vInfo6;

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public Object getVehicleCapacity() {
        return vehicleCapacity;
    }

    public void setVehicleCapacity(Object vehicleCapacity) {
        this.vehicleCapacity = vehicleCapacity;
    }

    public Object getVehicleSize() {
        return vehicleSize;
    }

    public void setVehicleSize(Object vehicleSize) {
        this.vehicleSize = vehicleSize;
    }

    public String getVInfo1() {
        return vInfo1;
    }

    public void setVInfo1(String vInfo1) {
        this.vInfo1 = vInfo1;
    }

    public String getVInfo2() {
        return vInfo2;
    }

    public void setVInfo2(String vInfo2) {
        this.vInfo2 = vInfo2;
    }

    public String getVInfo3() {
        return vInfo3;
    }

    public void setVInfo3(String vInfo3) {
        this.vInfo3 = vInfo3;
    }

    public String getVInfo4() {
        return vInfo4;
    }

    public void setVInfo4(String vInfo4) {
        this.vInfo4 = vInfo4;
    }

    public String getVInfo5() {
        return vInfo5;
    }

    public void setVInfo5(String vInfo5) {
        this.vInfo5 = vInfo5;
    }

    public String getVInfo6() {
        return vInfo6;
    }

    public void setVInfo6(String vInfo6) {
        this.vInfo6 = vInfo6;
    }

}
