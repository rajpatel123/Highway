
package com.highway.customer.customerModelClass.customerCurrentTripStatus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetCustomerCurrentTripStatusResp {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("CustomerTripStatus")
    @Expose
    private CustomerTripStatus customerTripStatus;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public CustomerTripStatus getCustomerTripStatus() {
        return customerTripStatus;
    }

    public void setCustomerTripStatus(CustomerTripStatus customerTripStatus) {
        this.customerTripStatus = customerTripStatus;
    }

}
