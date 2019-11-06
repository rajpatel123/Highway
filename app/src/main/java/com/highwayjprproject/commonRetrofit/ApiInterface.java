package com.highwayjprproject.commonRetrofit;


import com.highwayjprproject.commonActivity.commonModel.login.LoginRegisterRequest;
import com.highwayjprproject.commonActivity.commonModel.login.LoginRegisterResponse;
import com.highwayjprproject.commonActivity.commonModel.registration.RegistrationDetailsRequest;
import com.highwayjprproject.commonActivity.commonModel.registration.RegistrationDetailsResponse;
import com.highwayjprproject.commonActivity.commonModel.otpverify.VerifyOtpRequest;
import com.highwayjprproject.commonActivity.commonModel.otpverify.VerifyOtpResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("http://vrok.in/highway_dev/login_register")  // mobile logon
    Call<LoginRegisterResponse> loginResponseCall(@Body LoginRegisterRequest loginRequest);


    @POST("http://vrok.in/highway_dev/otp_verify") // otp verification
    Call<VerifyOtpResponse> verifyOtpResponseCall(@Body VerifyOtpRequest verifyOtpRequest);

    @POST("http://vrok.in/highway_dev/signup") // Registration Details
    Call<RegistrationDetailsResponse>regDetailsResponseCall(@Body RegistrationDetailsRequest registrationDetailsRequest);


}
