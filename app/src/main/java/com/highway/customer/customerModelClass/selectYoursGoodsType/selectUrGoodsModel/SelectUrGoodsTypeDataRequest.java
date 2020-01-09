package com.highway.customer.customerModelClass.selectYoursGoodsType.selectUrGoodsModel;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SelectUrGoodsTypeDataRequest {

@SerializedName("user_id")
@Expose
private String userId;

public String getUserId() {
return userId;
}

public void setUserId(String userId) {
this.userId = userId;
}

}