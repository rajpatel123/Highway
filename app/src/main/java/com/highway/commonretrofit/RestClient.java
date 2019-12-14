package com.highway.commonretrofit;


import com.highway.common.base.commonModel.login.LoginRegisterRequest;
import com.highway.common.base.commonModel.otpverify.VerifyOtpRequest;
import com.highway.common.base.commonModel.otpverify.VerifyOtpResponse;
import com.highway.common.base.commonModel.registration.RegistrationRequest;
import com.highway.common.base.commonModel.registration.RegistrationResponse;
import com.highway.common.base.commonModel.customerDiverOwnerModelsClass.AllHighwayTripsRequest;
import com.highway.common.base.commonModel.customerDiverOwnerModelsClass.AllHighwayTripsResponse;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.AddNewDriverThroughVehicleOwner.AddNewDriverRequest;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.AddNewDriverThroughVehicleOwner.AddNewDriverResponse;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.AddNewVehicleModel.AddNewVehicleRequest;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.AddNewVehicleModel.AddNewVehicleResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
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

    public static void allVehicleOwnerCompletedTrip(AllHighwayTripsRequest allHighwayTripsRequest, Callback<AllHighwayTripsResponse>allDriverTripsResponseCallback){
        RetrofitClient.getClient().vehicleOwnerTrip(allHighwayTripsRequest).enqueue(allDriverTripsResponseCallback);
    }
    // Add new vehicle
    public static void addNewVehicle(AddNewVehicleRequest addNewVehicleRequest, Callback<AddNewVehicleResponse>addNewVehicleResponseCallback){
        RetrofitClient.getClient().addNewVehicleResponseCall(addNewVehicleRequest).enqueue(addNewVehicleResponseCallback);
    }
    //add new Driver

    public static  void addNewDriver(AddNewDriverRequest addNewDriverRequest, Callback<AddNewDriverResponse>addNewDriverResponseCallback){
        RetrofitClient.getClient().addNewDriverResponseCall(addNewDriverRequest).enqueue(addNewDriverResponseCallback);
    }


}
