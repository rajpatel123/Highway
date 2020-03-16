package com.highway.customer.customerModelClass.customerCurrentTripStatus;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetCustomerCurrentTripStatusReq {

@SerializedName("customerId")
@Expose
private String customerId;

public String getCustomerId() {
return customerId;
}

public void setCustomerId(String customerId) {
this.customerId = customerId;
}

}