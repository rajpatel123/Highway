package modelclass;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UploadDLRequest {

@SerializedName("User_Id")
@Expose
private String userId;
@SerializedName("License_Number")
@Expose
private String licenseNumber;
@SerializedName("License_Image")
@Expose
private String licenseImage;
@SerializedName("Expiry_Date")
@Expose
private String expiryDate;

public String getUserId() {
return userId;
}

public void setUserId(String userId) {
this.userId = userId;
}

public String getLicenseNumber() {
return licenseNumber;
}

public void setLicenseNumber(String licenseNumber) {
this.licenseNumber = licenseNumber;
}

public String getLicenseImage() {
return licenseImage;
}

public void setLicenseImage(String licenseImage) {
this.licenseImage = licenseImage;
}

public String getExpiryDate() {
return expiryDate;
}

public void setExpiryDate(String expiryDate) {
this.expiryDate = expiryDate;
}

}