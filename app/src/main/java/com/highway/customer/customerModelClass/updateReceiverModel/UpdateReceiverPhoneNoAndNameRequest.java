package com.highway.customer.customerModelClass.updateReceiverModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateReceiverPhoneNoAndNameRequest {

@SerializedName("user_id")
@Expose
private String userId;
@SerializedName("receiverName")
@Expose
private String receiverName;
@SerializedName("receiverMobile")
@Expose
private String receiverMobile;

public String getUserId() {
return userId;
}

public void setUserId(String userId) {
this.userId = userId;
}

public String getReceiverName() {
return receiverName;
}

public void setReceiverName(String receiverName) {
this.receiverName = receiverName;
}

public String getReceiverMobile() {
return receiverMobile;
}

public void setReceiverMobile(String receiverMobile) {
this.receiverMobile = receiverMobile;
}

}