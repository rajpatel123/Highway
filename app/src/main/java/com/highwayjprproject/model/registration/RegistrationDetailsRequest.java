package com.highwayjprproject.model.registration;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegistrationDetailsRequest {

@SerializedName("User_Id")
@Expose
private String userId;
@SerializedName("Role_Id")
@Expose
private String roleId;
@SerializedName("base64_file")
@Expose
private String base64File;
@SerializedName("Name")
@Expose
private String name;
@SerializedName("Email")
@Expose
private String email;
@SerializedName("Gender")
@Expose
private String gender;
@SerializedName("Dob")
@Expose
private String dob;
@SerializedName("Role")
@Expose
private String role;
@SerializedName("Address")
@Expose
private String address;

public String getUserId() {
return userId;
}

public void setUserId(String userId) {
this.userId = userId;
}

public String getRoleId() {
return roleId;
}

public void setRoleId(String roleId) {
this.roleId = roleId;
}

public String getBase64File() {
return base64File;
}

public void setBase64File(String base64File) {
this.base64File = base64File;
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

public String getGender() {
return gender;
}

public void setGender(String gender) {
this.gender = gender;
}

public String getDob() {
return dob;
}

public void setDob(String dob) {
this.dob = dob;
}

public String getRole() {
return role;
}

public void setRole(String role) {
this.role = role;
}

public String getAddress() {
return address;
}

public void setAddress(String address) {
this.address = address;
}

}