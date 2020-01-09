package com.highway.commonretrofit;


import com.highway.common.base.commonModel.customerDiverOwnerModelsClass.AllHighwayTripsRequest;
import com.highway.common.base.commonModel.customerDiverOwnerModelsClass.AllHighwayTripsResponse;
import com.highway.common.base.commonModel.login.LoginRegisterRequest;
import com.highway.common.base.commonModel.otpverify.VerifyOtpRequest;
import com.highway.common.base.commonModel.otpverify.VerifyOtpResponse;
import com.highway.common.base.commonModel.registration.RegistrationRequest;
import com.highway.common.base.commonModel.registration.RegistrationResponse;
import com.highway.customer.customerModelClass.spinnerGoodsType.GoodsTypeDataRequest;
import com.highway.customer.customerModelClass.spinnerGoodsType.GoodsTypeDataResponse;
import com.highway.millUserModule.SpinnerModelForMiller.ApproxLoad.ApproxLoadDropDownRequest;
import com.highway.millUserModule.SpinnerModelForMiller.ApproxLoad.ApproxLoadDropDownResponse;
import com.highway.millUserModule.SpinnerModelForMiller.GoodsTypes.GoodsTypeDropDownRequest;
import com.highway.millUserModule.SpinnerModelForMiller.GoodsTypes.GoodsTypesDropDownResponse;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.addNewDriverThroughVehicleOwner.AddNewDriverRequest;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.addNewDriverThroughVehicleOwner.AddNewDriverResponse;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.addNewVehicleModel.AddVehicleRequest;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.addNewVehicleModel.AddVehicleResponse;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.assignD2V.assignD2V_Model.AssignD2VRequest;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.assignD2V.assignD2V_Model.AssignD2VResponse;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.assignD2V.driverAssignSpinner.DriverDropDownRequest;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.assignD2V.driverAssignSpinner.DriverDropDownResponse;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.assignD2V.vehicleAssignSpinner.VehicleDropDowanRequest;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.assignD2V.vehicleAssignSpinner.VehicleDropDowanResponse;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.getAllDriverDetailsList.GetAllDriverRequest;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.getAllDriverDetailsList.GetAllDriverResponse;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.getAllVehicleDetailsList.GetAllVehicleDetailsRequest;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.getAllVehicleDetailsList.GetAllVehicleDetailsResponse;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.vehicleTypeDropDowan.VehicleTypeDropDowanRequest;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.vehicleTypeDropDowan.VehicleTypeDropDowanResponse;

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
 // driver trip
    public static void  allDriverTrips(AllHighwayTripsRequest allHighwayTripsRequest, Callback<AllHighwayTripsResponse>allDriverTripsResponseCallback){
        RetrofitClient.getClient().driverTrips(allHighwayTripsRequest).enqueue(allDriverTripsResponseCallback);
    }
// vehicle owner trip
    public static void allVehicleOwnerTrip(AllHighwayTripsRequest allHighwayTripsRequest, Callback<AllHighwayTripsResponse>allDriverTripsResponseCallback){
        RetrofitClient.getClient().vehicleOwnerTrip(allHighwayTripsRequest).enqueue(allDriverTripsResponseCallback);
    }
 //   mill user trip
    public static void  allMillerTrip(AllHighwayTripsRequest allHighwayTripsRequest ,Callback<AllHighwayTripsResponse> allDriverTripsResponseCallback){
        RetrofitClient.getClient().millerTrip(allHighwayTripsRequest).enqueue(allDriverTripsResponseCallback);
    }

   // Customer trip
   public static void allCustomerTrip(AllHighwayTripsRequest allHighwayTripsRequest, Callback<AllHighwayTripsResponse>allHighwayTripsResponseCallback){
        RetrofitClient.getClient().customerTrip(allHighwayTripsRequest).enqueue(allHighwayTripsResponseCallback);
   }

    // Add new vehicle
    public static void addNewVehicle(AddVehicleRequest addVehicleRequest, Callback<AddVehicleResponse>addVehicleResponseCallback){
        RetrofitClient.getClient().addVehicleResponseCall(addVehicleRequest).enqueue(addVehicleResponseCallback);
    }
    //add new Driver

    public static  void addNewDriver(AddNewDriverRequest addNewDriverRequest, Callback<AddNewDriverResponse>addNewDriverResponseCallback){
        RetrofitClient.getClient().addNewDriverResponseCall(addNewDriverRequest).enqueue(addNewDriverResponseCallback);
    }

    // driver drop dowan ---spinners for owner
   public static void getDriverList(DriverDropDownRequest driverDropDownRequest , Callback<DriverDropDownResponse>driverDropDownResponseCallback) {
       RetrofitClient.getClient().driverDataResponse(driverDropDownRequest).enqueue(driverDropDownResponseCallback);
   }

   // vehicle Type drop dowan -- spinners for owner for add new vehicle
    public  static void getVehicleTypeList(VehicleTypeDropDowanRequest vehicleTypeDropDowanRequest, Callback<VehicleTypeDropDowanResponse>vehicleTypeDropDowanResponseCallback){
        RetrofitClient.getClient().vehicleTypeDropDowanResp(vehicleTypeDropDowanRequest).enqueue(vehicleTypeDropDowanResponseCallback);
    }

    // vehicle list spinner in AssignD2s
   public static void getVehicleList(VehicleDropDowanRequest vehicleDropDownRequest, Callback<VehicleDropDowanResponse>vehicleDropDownResponseCallback){
        RetrofitClient.getClient().vehicleDataResponse(vehicleDropDownRequest).enqueue(vehicleDropDownResponseCallback);
    }

  /*  public static void getVehicleList(Callback<VehicleDropDownResponse> callback) {
        RetrofitClient.getClient().vehicleData().enqueue(callback);   // ByPart
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

    // Assign Driver 2 vehicle
        public  static void assign_D2V(AssignD2VRequest assignD2VRequest , Callback<AssignD2VResponse> assignD2VResponseCallback){
       RetrofitClient.getClient().assignD2VReq(assignD2VRequest).enqueue(assignD2VResponseCallback);
        }

      //Goods type Data Booking With details Activity
      public  static void goodsTypeDataWithBooking(GoodsTypeDataRequest goodsTypeDataRequest , Callback<GoodsTypeDataResponse>goodsTypeDataResponseCallback){
       RetrofitClient.getClient().goodsTypeDataResponse(goodsTypeDataRequest).enqueue(goodsTypeDataResponseCallback);
      }

}
