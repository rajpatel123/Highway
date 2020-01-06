package com.highway.millUserModule.SpinnerModelForMiller.GoodsTypes;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GoodsTypeDropDownRequest {

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