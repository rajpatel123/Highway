package com.highway.commonretrofit;


import com.highway.common.base.commonModel.login.LoginRegisterRequest;
import com.highway.common.base.commonModel.otpverify.VerifyOtpRequest;
import com.highway.common.base.commonModel.otpverify.VerifyOtpResponse;
import com.highway.common.base.commonModel.registration.RegistrationRequest;
import com.highway.common.base.commonModel.registration.RegistrationResponse;
import com.highway.common.base.commonModel.customer_diver_owner_Models_class.AllHighwayTripsRequest;
import com.highway.common.base.commonModel.customer_diver_owner_Models_class.AllHighwayTripsResponse;

import okhttp3.ResponseBody;
import retrofit2.Callback;

public class RestClient {
    // login mob no
    public static void loginUser(LoginRegisterRequest loginRegisterRequest, Callback<ResponseBody> callback) {
        RetrofitClient.getClient().loginResponseCall(loginRegisterRequest).enqueue(callback);
    }

    // verify otp mobile
    public static void otpVerify(VerifyOtpRequest verifyOtpRequest, Callback<VerifyOtpResponse>otpResponseCallback){
        RetrofitClient.getClient().verifyOtpResponseCall(verifyOtpRequest).enqueue(otpResponseCallback);
    }
    // registration details
    public static void regDetails(RegistrationRequest registrationDetailsRequest, Callback<RegistrationResponse>registrationDetailsResponseCallback){
        RetrofitClient.getClient().regDetailsResponseCall(registrationDetailsRequest).enqueue(registrationDetailsResponseCallback);
    }

    public static void  allDriverTrips(AllHighwayTripsRequest allHighwayTripsRequest, Callback<AllHighwayTripsResponse>allDriverTripsResponseCallback){
        RetrofitClient.getClient().driverTrips(allHighwayTripsRequest).enqueue(allDriverTripsResponseCallback);
    }

   /* public static void allVehicleOwnerCompletedTrip(VehicleOwnerCompletedTripRequest vehicleOwnerCompletedTripRequest, Callback<VehicleOwnerCompletedTripResponse>vehicleOwnerCompletedTripResponseCallback){
        RetrofitClient.getClient().vehicleOwnerTrip(vehicleOwnerCompletedTripRequest).enqueue(vehicleOwnerCompletedTripResponseCallback);
    }*/
}
