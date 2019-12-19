package com.highway.commonretrofit;


import com.highway.common.base.commonModel.customerDiverOwnerModelsClass.AllHighwayTripsRequest;
import com.highway.common.base.commonModel.customerDiverOwnerModelsClass.AllHighwayTripsResponse;
import com.highway.common.base.commonModel.login.LoginRegisterRequest;
import com.highway.common.base.commonModel.otpverify.VerifyOtpRequest;
import com.highway.common.base.commonModel.otpverify.VerifyOtpResponse;
import com.highway.common.base.commonModel.registration.RegistrationRequest;
import com.highway.common.base.commonModel.registration.RegistrationResponse;
import com.highway.millmodule.SpinnerModelForMiller.ApproxLoad.ApproxLoadDropDownRequest;
import com.highway.millmodule.SpinnerModelForMiller.ApproxLoad.ApproxLoadDropDownResponse;
import com.highway.millmodule.SpinnerModelForMiller.GoodsTypes.GoodsTypeDropDownRequest;
import com.highway.millmodule.SpinnerModelForMiller.GoodsTypes.GoodsTypesDropDownResponse;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.AddNewDriverThroughVehicleOwner.AddNewDriverRequest;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.AddNewDriverThroughVehicleOwner.AddNewDriverResponse;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.AddNewVehicleModel.AddNewVehicleRequest;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.AddNewVehicleModel.AddNewVehicleResponse;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.DriverDropDown_Spinners.DriverDropDownRequest;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.DriverDropDown_Spinners.DriverDropDownResponse;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.GetAllDriverDetailsList.GetAllDriverRequest;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.GetAllDriverDetailsList.GetAllDriverResponse;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.GetAllVehicleDetailsList.GetAllVehicleDetailsRequest;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.GetAllVehicleDetailsList.GetAllVehicleDetailsResponse;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.VehicleDropDown_Spinners.VehicleDropDownRequest;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.VehicleDropDown_Spinners.VehicleDropDownResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("http://vrok.in/highway_dev/login_register")  // mobile logon
    Call<ResponseBody> loginResponseCall(@Body LoginRegisterRequest loginRequest);

    @POST("http://vrok.in/highway_dev/otp_verify") // otp verification
    Call<VerifyOtpResponse> verifyOtpResponseCall(@Body VerifyOtpRequest verifyOtpRequest);

    @POST("http://vrok.in/highway_dev/signup") // Registration Details
    Call<RegistrationResponse>regDetailsResponseCall(@Body RegistrationRequest registrationDetailsRequest);


    // Driver All Trips
    @POST("http://vrok.in/highway_dev/getAllTripByDriverId")
    Call<AllHighwayTripsResponse> driverTrips(@Body AllHighwayTripsRequest allHighwayTripsRequest);

    // Vehicle Owner trip details

    @POST("http://vrok.in/highway_dev/getAllTripByOwnerId")
    Call<AllHighwayTripsResponse>vehicleOwnerTrip(@Body AllHighwayTripsRequest allHighwayTripsRequest);

     // Add New Vehicle
    @POST("http://highway.vrok.in/index.php/api/Vehicle/addVehicle")
    Call<AddNewVehicleResponse>addNewVehicleResponseCall(@Body AddNewVehicleRequest addNewVehicleRequest);

    // Add new Driver
    @POST("http://highway.vrok.in/index.php/api/Login/addDriver")
    Call<AddNewDriverResponse>addNewDriverResponseCall(@Body AddNewDriverRequest addNewDriverRequest);

  // Driver drop down ----spinners
   @POST("http://highway.vrok.in/index.php/api/vehicle/driverDropdown")
    Call<DriverDropDownResponse>driverDataResponse(@Body DriverDropDownRequest driverDropDownRequest);

   // vehicle drop dowan --spinner for vehicle owners
    @POST("http://highway.vrok.in/index.php/api/vehicle/vehicleDropdown")
    Call<VehicleDropDownResponse> vehicleDataResponse(@Body VehicleDropDownRequest vehicleDropDownRequest);      //Call<VehicleDropDownResponse> vehicleData();

    // Approx load spinners -- for miller Book load
    @POST("http://highway.vrok.in/index.php/api/trip/goodType")
    Call<ApproxLoadDropDownResponse> approxLoadResponse(@Body ApproxLoadDropDownRequest approxLoadDropDownRequest);

   // Goods  Types ----Spinners
    @POST("http://highway.vrok.in/index.php/api/trip/goodType")
    Call<GoodsTypesDropDownResponse>goodsTypesResponse(@Body GoodsTypeDropDownRequest goodsTypeDropDownRequest);

    // getAll Driver List for owner;

    @POST("http://highway.vrok.in/index.php/api/login/getAlldriverDetails")
    Call<GetAllDriverResponse>getAllDriverDetailsRes(@Body GetAllDriverRequest getAllDriverRequest);

    @POST("http://highway.vrok.in/index.php/api/Vehicle/getAllVehicleDetails")
    Call<GetAllVehicleDetailsResponse>getVehicleResponse(@Body GetAllVehicleDetailsRequest getAllVehicleDetailsRequest);

}
