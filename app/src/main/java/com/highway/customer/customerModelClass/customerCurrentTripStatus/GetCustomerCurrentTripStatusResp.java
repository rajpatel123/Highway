
package com.highway.customer.customerModelClass.customerCurrentTripStatus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetCustomerCurrentTripStatusResp {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("tripStatus")
    @Expose
    private CustomerTripStatus tripStatus;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public CustomerTripStatus getTripStatus() {
        return tripStatus;
    }

    public void setTripStatus(CustomerTripStatus tripStatus) {
        this.tripStatus = tripStatus;
    }

}
