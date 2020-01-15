
package com.highway.customer.customerModelClass.bookingVehicleList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VehicleList {

    @SerializedName("VehicleId")
    @Expose
    private String vehicleId;
    @SerializedName("VehicleTypeId")
    @Expose
    private Object vehicleTypeId;
    @SerializedName("VehicleName")
    @Expose
    private Object vehicleName;
    @SerializedName("VehicleFare")
    @Expose
    private Object vehicleFare;

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Object getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(Object vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public Object getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(Object vehicleName) {
        this.vehicleName = vehicleName;
    }

    public Object getVehicleFare() {
        return vehicleFare;
    }

    public void setVehicleFare(Object vehicleFare) {
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
