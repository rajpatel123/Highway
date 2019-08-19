package modelclass;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegistrationSignUpRequest {

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
@SerializedName("Id")
@Expose
private String id;
@SerializedName("Dob")
@Expose
private String dob;
@SerializedName("Role")
@Expose
private String role;

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

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
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

}