package modelclass;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UploadVehicleRcRequest {

@SerializedName("User_Id")
@Expose
private String userId;
@SerializedName("RC_Image")
@Expose
private String rCImage;
@SerializedName("Bike_Colour")
@Expose
private String bikeColour;
@SerializedName("Vehical_Number")
@Expose
private String vehicalNumber;

public String getUserId() {
return userId;
}

public void setUserId(String userId) {
this.userId = userId;
}

public String getRCImage() {
return rCImage;
}

public void setRCImage(String rCImage) {
this.rCImage = rCImage;
}

public String getBikeColour() {
return bikeColour;
}

public void setBikeColour(String bikeColour) {
this.bikeColour = bikeColour;
}

public String getVehicalNumber() {
return vehicalNumber;
}

public void setVehicalNumber(String vehicalNumber) {
this.vehicalNumber = vehicalNumber;
}

}