package retrofit;

import modelclass.LoginRequest;
import modelclass.LoginResponse;
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

}
