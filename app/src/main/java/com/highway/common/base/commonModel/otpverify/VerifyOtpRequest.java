package com.highway.common.base.commonModel.otpverify;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerifyOtpRequest {

@SerializedName("Mobile")
@Expose
private String mobile;
@SerializedName("Otp")
@Expose
private String otp;

public String getMobile() {
return mobile;
}

public void setMobile(String mobile) {
this.mobile = mobile;
}

public String getOtp() {
return otp;
}

public void setOtp(String otp) {
this.otp = otp;
}

}