package com.highwayjprproject.commonRetrofit;


import com.highwayjprproject.commonActivity.commonModel.login.LoginRegisterRequest;
import com.highwayjprproject.commonActivity.commonModel.login.LoginRegisterResponse;

import com.highwayjprproject.commonActivity.commonModel.registration.RegistrationDetailsRequest;
import com.highwayjprproject.commonActivity.commonModel.registration.RegistrationDetailsResponse;
import com.highwayjprproject.commonActivity.commonModel.otpverify.VerifyOtpRequest;
import com.highwayjprproject.commonActivity.commonModel.otpverify.VerifyOtpResponse;
import retrofit2.Callback;

public class RestClient {
    // login mob no
    public static void loginUser(LoginRegisterRequest loginRegisterRequest, Callback<LoginRegisterResponse> callback) {
        RetrofitClient.getClient().loginResponseCall(loginRegisterRequest).enqueue(callback);
    }

    // verify otp mobile
    public static void otpVerify(VerifyOtpRequest verifyOtpRequest, Callback<VerifyOtpResponse>otpResponseCallback){
        RetrofitClient.getClient().verifyOtpResponseCall(verifyOtpRequest).enqueue(otpResponseCallback);
    }
    // registration details
    public static void regDetails(RegistrationDetailsRequest registrationDetailsRequest, Callback<RegistrationDetailsResponse>registrationDetailsResponseCallback){
        RetrofitClient.getClient().regDetailsResponseCall(registrationDetailsRequest).enqueue(registrationDetailsResponseCallback);
    }



}
