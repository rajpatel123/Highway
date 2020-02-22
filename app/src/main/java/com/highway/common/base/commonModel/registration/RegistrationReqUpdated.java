package com.highway.common.base.commonModel.registration;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegistrationReqUpdated {

@SerializedName("User_Id")
@Expose
private String userId;
@SerializedName("Name")
@Expose
private String name;
@SerializedName("Email")
@Expose
private String email;

public String getUserId() {
return userId;
}

public void setUserId(String userId) {
this.userId = userId;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

}