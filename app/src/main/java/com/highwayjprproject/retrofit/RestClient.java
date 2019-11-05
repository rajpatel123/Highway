package com.highwayjprproject.retrofit;


import com.highwayjprproject.model.login.LoginRegisterRequest;
import com.highwayjprproject.model.login.LoginRegisterResponse;

import com.highwayjprproject.model.registration.RegistrationDetailsRequest;
import com.highwayjprproject.model.registration.RegistrationDetailsResponse;
import com.highwayjprproject.model.otpverify.VerifyOtpRequest;
import com.highwayjprproject.model.otpverify.VerifyOtpResponse;
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
