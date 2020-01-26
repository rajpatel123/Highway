
package com.highway.customer.customerModelClass.bookingVehicleList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VehicleList {

    @SerializedName("VehicleId")
    @Expose
    private String vehicleId;
    @SerializedName("VehicleTypeId")
    @Expose
    private String vehicleTypeId;
    @SerializedName("VehicleName")
    @Expose
    private String vehicleName;
    @SerializedName("VehicleFare")
    @Expose
    private String vehicleFare;

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Object getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(String vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getVehicleFare() {
        return vehicleFare;
    }

    public void setVehicleFare(String vehicleFare) {
        this.vehicleFare = vehicleFare;
    }


    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    private boolean isSelected;

}
