package com.highway.commonretrofit;


import com.highway.common.base.commonModel.customerDiverOwnerModelsClass.AllHighwayTripsRequest;
import com.highway.common.base.commonModel.customerDiverOwnerModelsClass.AllHighwayTripsResponse;
import com.highway.common.base.commonModel.login.LoginRegisterRequest;
import com.highway.common.base.commonModel.otpverify.VerifyOtpRequest;
import com.highway.common.base.commonModel.otpverify.VerifyOtpResponse;
import com.highway.common.base.commonModel.registration.RegistrationRequest;
import com.highway.common.base.commonModel.registration.RegistrationResponse;
import com.highway.millUserModule.SpinnerModelForMiller.ApproxLoad.ApproxLoadDropDownRequest;
import com.highway.millUserModule.SpinnerModelForMiller.ApproxLoad.ApproxLoadDropDownResponse;
import com.highway.millUserModule.SpinnerModelForMiller.GoodsTypes.GoodsTypeDropDownRequest;
import com.highway.millUserModule.SpinnerModelForMiller.GoodsTypes.GoodsTypesDropDownResponse;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.addNewDriverThroughVehicleOwner.AddNewDriverRequest;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.addNewDriverThroughVehicleOwner.AddNewDriverResponse;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.addNewVehicleModel.AddNewVehicleRequest;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.addNewVehicleModel.AddNewVehicleResponse;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.driverDropDown_Spinners.DriverDropDownRequest;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.driverDropDown_Spinners.DriverDropDownResponse;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.getAllDriverDetailsList.GetAllDriverRequest;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.getAllDriverDetailsList.GetAllDriverResponse;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.getAllVehicleDetailsList.GetAllVehicleDetailsRequest;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.getAllVehicleDetailsList.GetAllVehicleDetailsResponse;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.vehicleDropDown_Spinners.VehicleDropDownRequest;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.vehicleDropDown_Spinners.VehicleDropDownResponse;

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

    public static void allVehicleOwnerTrip(AllHighwayTripsRequest allHighwayTripsRequest, Callback<AllHighwayTripsResponse>allDriverTripsResponseCallback){
        RetrofitClient.getClient().vehicleOwnerTrip(allHighwayTripsRequest).enqueue(allDriverTripsResponseCallback);
    }

    public static void  allMillerTrip(AllHighwayTripsRequest allHighwayTripsRequest ,Callback<AllHighwayTripsResponse> allDriverTripsResponseCallback){
        RetrofitClient.getClient().millerTrip(allHighwayTripsRequest).enqueue(allDriverTripsResponseCallback);
    }

    // Add new vehicle
    public static void addNewVehicle(AddNewVehicleRequest addNewVehicleRequest, Callback<AddNewVehicleResponse>addNewVehicleResponseCallback){
        RetrofitClient.getClient().addNewVehicleResponseCall(addNewVehicleRequest).enqueue(addNewVehicleResponseCallback);
    }
    //add new Driver

    public static  void addNewDriver(AddNewDriverRequest addNewDriverRequest, Callback<AddNewDriverResponse>addNewDriverResponseCallback){
        RetrofitClient.getClient().addNewDriverResponseCall(addNewDriverRequest).enqueue(addNewDriverResponseCallback);
    }

    // driver drop dowan ---spinners for owner
   public static void getDriverList(DriverDropDownRequest driverDropDownRequest , Callback<DriverDropDownResponse>driverDropDownResponseCallback) {
       RetrofitClient.getClient().driverDataResponse(driverDropDownRequest).enqueue(driverDropDownResponseCallback);
   }

   // vehicle drop dowan -- spinners for owner
    public static void getVehicleList(VehicleDropDownRequest vehicleDropDownRequest, Callback<VehicleDropDownResponse>vehicleDropDownResponseCallback){
        RetrofitClient.getClient().vehicleDataResponse(vehicleDropDownRequest).enqueue(vehicleDropDownResponseCallback);
    }

   /* public static void getVehicleList(Callback<VehicleDropDownResponse> callback) {
        RetrofitClient.getClient().vehicleData().enqueue(callback);
    }*/

   // Approx Load spinners
   public static void getApproxLoadList(ApproxLoadDropDownRequest approxLoadDropDownRequest, Callback<ApproxLoadDropDownResponse>approxLoadResponseCallback){
       RetrofitClient.getClient().approxLoadResponse(approxLoadDropDownRequest).enqueue(approxLoadResponseCallback);
   }

   // Goods types Spinners

   public static void getGoodsTypesLIst(GoodsTypeDropDownRequest goodsTypeDropDownRequest, Callback<GoodsTypesDropDownResponse>goodsTypeResponseCallback){
       RetrofitClient.getClient().goodsTypesResponse(goodsTypeDropDownRequest).enqueue(goodsTypeResponseCallback);
   }

   // getAll driver details
    public static void getAllDriverDetails(GetAllDriverRequest getAllDriverRequest, Callback<GetAllDriverResponse>getAllDriverResponseCallback){
       RetrofitClient.getClient().getAllDriverDetailsRes(getAllDriverRequest).enqueue(getAllDriverResponseCallback);
    }

   // getAll Vehicle details
    public static void getAllVehicleDetails(GetAllVehicleDetailsRequest getAllVehicleDetailsRequest, Callback<GetAllVehicleDetailsResponse>getAllVehicleDetailsResponseCallback){
       RetrofitClient.getClient().getVehicleResponse(getAllVehicleDetailsRequest).enqueue(getAllVehicleDetailsResponseCallback);
    }


}
