package modelclass;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UploadVehicleRcRequest {

@SerializedName("User_Id")
@Expose
private String userId;
@SerializedName("Rc_Image")
@Expose
private String rcImage;
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

public String getRcImage() {
return rcImage;
}

public void setRcImage(String rcImage) {
this.rcImage = rcImage;
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