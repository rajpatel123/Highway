package modelclass;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UploadAdharRequest {

@SerializedName("User_Id")
@Expose
private String userId;
@SerializedName("Adhar_Image")
@Expose
private String adharImage;
@SerializedName("Adhar_Number")
@Expose
private String adharNumber;

public String getUserId() {
return userId;
}

public void setUserId(String userId) {
this.userId = userId;
}

public String getAdharImage() {
return adharImage;
}

public void setAdharImage(String adharImage) {
this.adharImage = adharImage;
}

public String getAdharNumber() {
return adharNumber;
}

public void setAdharNumber(String adharNumber) {
this.adharNumber = adharNumber;
}

}