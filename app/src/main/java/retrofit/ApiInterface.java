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
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("http://vrok.in/highway_dev/login_register.php")
    Call<LoginResponse> loginResponseCall(@Body LoginRequest loginRequest);

    @POST("http://vrok.in/highway_dev/otp_verify")
    Call<VerifyOTPResponse> VerifyOTPResponseCall(@Body VerifyOTPRequest verifyOTPRequest);

    @POST("http://vrok.in/highway_dev/signup")
    Call<RegistrationSignUpResponse> VerifyRegistrationSignUpCall(@Body RegistrationSignUpRequest registrationSignUpRequest);


    @POST("http://vrok.in/highway_dev/upload_DL")
    Call<UploadDLResponse>UPLOAD_DL_RESPONSE_CALL(@Body UploadDLRequest uploadDLRequest);

    @POST("http://vrok.in/highway_dev/upload_RC")
    Call<UploadVehicleRcResponse>UPLOAD_VEHICLE_RC_RESPONSE_CALL(@Body UploadVehicleRcRequest uploadVehicleRcRequest);

    @POST("http://vrok.in/highway_dev/upload_adhar")
    Call<UploadAdharResponse>UPLOAD_ADHAR_RESPONSE_CALL(@Body UploadAdharRequest uploadAdharRequest);
}
