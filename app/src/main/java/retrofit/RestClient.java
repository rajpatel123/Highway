package retrofit;

import modelclass.LoginRequest;
import modelclass.LoginResponse;
import modelclass.RegistrationSignUpRequest;
import modelclass.RegistrationSignUpResponse;
import modelclass.VerifyOTPRequest;
import modelclass.VerifyOTPResponse;
import retrofit2.Callback;

public class RestClient {

    public static void loginUser(LoginRequest loginRequest , Callback<LoginResponse> callback){
      RetrofitClient.getClient().loginResponseCall(loginRequest).enqueue(callback);
    }


    public static void otpVerifed(VerifyOTPRequest verifyOTPRequest , Callback<VerifyOTPResponse> callback){
        RetrofitClient.getClient().VerifyOTPResponseCall(verifyOTPRequest).enqueue(callback);
    }

    public static void registrationSignUp(RegistrationSignUpRequest registrationSignUpRequest, Callback<RegistrationSignUpResponse> callback){
        RetrofitClient.getClient().VerifyRegistrationSignUpCall(registrationSignUpRequest).enqueue(callback);
    }

    public static void driveryLicences(RegistrationSignUpRequest registrationSignUpRequest, Callback<RegistrationSignUpResponse> callback){
        RetrofitClient.getClient().VerifyRegistrationSignUpCall(registrationSignUpRequest).enqueue(callback);
    }

}
