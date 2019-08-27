package retrofit;

import modelclass.LoginRequest;
import modelclass.LoginResponse;
import modelclass.RegistrationSignUpRequest;
import modelclass.RegistrationSignUpResponse;
import modelclass.UploadAdharRequest;
import modelclass.UploadAdharResponse;
import modelclass.UploadDLRequest;
import modelclass.UploadDLResponse;
import modelclass.UploadVehicleRcRequest;
import modelclass.UploadVehicleRcResponse;
import modelclass.VerifyOTPRequest;
import modelclass.VerifyOTPResponse;
import retrofit2.Callback;

public class RestClient {

    public static void loginUser(LoginRequest loginRequest, Callback<LoginResponse> callback) {
        RetrofitClient.getClient().loginResponseCall(loginRequest).enqueue(callback);
    }


    public static void otpVerifed(VerifyOTPRequest verifyOTPRequest, Callback<VerifyOTPResponse> callback) {
        RetrofitClient.getClient().VerifyOTPResponseCall(verifyOTPRequest).enqueue(callback);
    }

    public static void registrationSignUp(RegistrationSignUpRequest registrationSignUpRequest, Callback<RegistrationSignUpResponse> callback) {
        RetrofitClient.getClient().VerifyRegistrationSignUpCall(registrationSignUpRequest).enqueue(callback);
    }

    public static void uploadDL(UploadDLRequest uploadDLRequest, Callback<UploadDLResponse> callback) {
        RetrofitClient.getClient().UPLOAD_DL_RESPONSE_CALL(uploadDLRequest).enqueue(callback);
    }

    public static void uploadVehicleRCDetails(UploadVehicleRcRequest uploadVehicleRcRequest, Callback<UploadVehicleRcResponse> rcResponseCallback) {
        RetrofitClient.getClient().UPLOAD_VEHICLE_RC_RESPONSE_CALL(uploadVehicleRcRequest).enqueue(rcResponseCallback);
    }

    public static void uploadAdharDetails(UploadAdharRequest uploadAdharRequest, Callback<UploadAdharResponse>adharResponseCallback){
        RetrofitClient.getClient().UPLOAD_ADHAR_RESPONSE_CALL(uploadAdharRequest).enqueue(adharResponseCallback);
    }


}
