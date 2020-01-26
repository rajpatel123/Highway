package com.highway.customer;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterForPushModel {

@SerializedName("userId")
@Expose
private String userId;
@SerializedName("tokenId")
@Expose
private String tokenId;

public String getUserId() {
return userId;
}

public void setUserId(String userId) {
this.userId = userId;
}

public String getTokenId() {
return tokenId;
}

public void setTokenId(String tokenId) {
this.tokenId = tokenId;
}

}