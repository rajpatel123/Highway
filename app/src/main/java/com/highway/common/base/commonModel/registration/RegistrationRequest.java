package com.highway.common.base.commonModel.registration;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegistrationRequest {

@SerializedName("User_Id")
@Expose
private String userId;
@SerializedName("Name")
@Expose
private String name;
@SerializedName("Email")
@Expose
private String email;
@SerializedName("Role_Id")
@Expose
private String roleId;
@SerializedName("Address")
@Expose
private String address;

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

public String getRoleId() {
return roleId;
}

public void setRoleId(String roleId) {
this.roleId = roleId;
}

public String getAddress() {
return address;
}

public void setAddress(String address) {
this.address = address;
}

}