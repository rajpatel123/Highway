package com.highway.common.base.commonModel.login;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginReqUpdated {

@SerializedName("Mobile")
@Expose
private String mobile;
@SerializedName("RoleId")
@Expose
private String roleId;

public String getMobile() {
return mobile;
}

public void setMobile(String mobile) {
this.mobile = mobile;
}

public String getRoleId() {
return roleId;
}

public void setRoleId(String roleId) {
this.roleId = roleId;
}

}