package com.highway.commonretrofit;


import com.highway.common.base.commonModel.bookingHTrip.BookingHTripRequest;
import com.highway.common.base.commonModel.bookingHTrip.BookingHTripResponse;
import com.highway.common.base.commonModel.customerDiverOwnerModelsClass.AllHighwayTripsRequest;
import com.highway.common.base.commonModel.customerDiverOwnerModelsClass.AllHighwayTripsResponse;
import com.highway.common.base.commonModel.login.LoginRegisterRequest;
import com.highway.common.base.commonModel.otpverify.VerifyOtpRequest;
import com.highway.common.base.commonModel.otpverify.VerifyOtpResponse;
import com.highway.common.base.commonModel.registration.RegistrationRequest;
import com.highway.common.base.commonModel.registration.RegistrationResponse;
import com.highway.customer.RegisterForPushModel;
import com.highway.customer.customerModelClass.bookingVehicleList.BookingVehicleListRequest;
import com.highway.customer.customerModelClass.bookingVehicleList.BookingVehicleListResponse;
import com.highway.customer.customerModelClass.cancleTripModel.cancleReason.CancelTripReasonRequest;
import com.highway.customer.customerModelClass.cancleTripModel.cancleReason.CancelTripReasonResponse;
import com.highway.customer.customerModelClass.cancleTripModel.cancleTrip.CancelTripByCustomerRequest;
import com.highway.customer.customerModelClass.cancleTripModel.cancleTrip.CancelTripByCustomerResponse;
import com.highway.customer.customerModelClass.selectYoursGoodsType.GoodsTypeDataRequest;
import com.highway.customer.customerModelClass.selectYoursGoodsType.GoodsTypeDataResponse;
import com.highway.customer.customerModelClass.updateReceiverModel.UpdateReceiverPhoneNoAndNameRequest;
import com.highway.customer.customerModelClass.updateReceiverModel.UpdateReceiverPhoneNoAndNameResponse;
import com.highway.customer.customerModelClass.vehicleInfo.BookingVehicleInfoRequest;
import com.highway.customer.customerModelClass.vehicleInfo.BookingVehicleInfoResponse;
import com.highway.millUserModule.SpinnerModelForMiller.ApproxLoad.ApproxLoadDropDownRequest;
import com.highway.millUserModule.SpinnerModelForMiller.ApproxLoad.ApproxLoadDropDownResponse;
import com.highway.millUserModule.SpinnerModelForMiller.GoodsTypes.GoodsTypeDropDownRequest;
import com.highway.millUserModule.SpinnerModelForMiller.GoodsTypes.GoodsTypesDropDownResponse;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.addNewDriverThroughVehicleOwner.AddNewDriverRequest;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.addNewDriverThroughVehicleOwner.AddNewDriverResponse;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.addVehicle.AddVehicleRequest;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.addVehicle.AddVehicleResponse;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.assignD2V.vehicleAssignSpinner.VehicleAssignDropDowanRequest;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.assignD2V.vehicleAssignSpinner.VehicleAssignDropDowanResponse;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.vehiceLoadCapicity.VehicleLoadCapicityRequest;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.vehiceLoadCapicity.VehicleLoadCapicityResponse;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.vehicleDimensionSize.VehicleDiamensionSizeRequest;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.vehicleDimensionSize.VehicleDiamensionSizeResponse;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.assignD2V.assignD2V_Model.AssignD2VRequest;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.assignD2V.assignD2V_Model.AssignD2VResponse;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.assignD2V.driverAssignSpinner.DriverDropDownRequest;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.assignD2V.driverAssignSpinner.DriverDropDownResponse;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.getAllDriver.GetAllDriverRequest;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.getAllDriver.GetAllDriverResponse;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.getAllVehicle.GetAllVehicleRequest;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.getAllVehicle.GetAllVehicleResponse;
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
    public static void otpVerify(VerifyOtpRequest verifyOtpRequest, Callback<VerifyOtpResponse> otpResponseCallback) {
        RetrofitClient.getClient().verifyOtpResponseCall(verifyOtpRequest).enqueue(otpResponseCallback);
    }

    // registration details
    public static void regDetails(RegistrationRequest registrationDetailsRequest, Callback<RegistrationResponse> registrationDetailsResponseCallback) {
        RetrofitClient.getClient().regDetailsResponseCall(registrationDetailsRequest).enqueue(registrationDetailsResponseCallback);
    }

    // driver trip
    public static void allDriverTrips(AllHighwayTripsRequest allHighwayTripsRequest, Callback<AllHighwayTripsResponse> allDriverTripsResponseCallback) {
        RetrofitClient.getClient().driverTrips(allHighwayTripsRequest).enqueue(allDriverTripsResponseCallback);
    }

    // vehicle owner trip
    public static void allVehicleOwnerTrip(AllHighwayTripsRequest allHighwayTripsRequest, Callback<AllHighwayTripsResponse> allDriverTripsResponseCallback) {
        RetrofitClient.getClient().vehicleOwnerTrip(allHighwayTripsRequest).enqueue(allDriverTripsResponseCallback);
    }

    //   mill user trip
    public static void allMillerTrip(AllHighwayTripsRequest allHighwayTripsRequest, Callback<AllHighwayTripsResponse> allDriverTripsResponseCallback) {
        RetrofitClient.getClient().millerTrip(allHighwayTripsRequest).enqueue(allDriverTripsResponseCallback);
    }

    // Customer trip
    public static void allCustomerTrip(AllHighwayTripsRequest allHighwayTripsRequest, Callback<AllHighwayTripsResponse> allHighwayTripsResponseCallback) {
        RetrofitClient.getClient().customerTrip(allHighwayTripsRequest).enqueue(allHighwayTripsResponseCallback);
    }

    // Add new vehicle
    public static void addNewVehicle(AddVehicleRequest addVehicleRequest, Callback<AddVehicleResponse> addVehicleResponseCallback) {
        RetrofitClient.getClient().addVehicleResponseCall(addVehicleRequest).enqueue(addVehicleResponseCallback);
    }


    //add new Driver

    public static void addNewDriver(AddNewDriverRequest addNewDriverRequest, Callback<AddNewDriverResponse> addNewDriverResponseCallback) {
        RetrofitClient.getClient().addNewDriverResponseCall(addNewDriverRequest).enqueue(addNewDriverResponseCallback);
    }


    public static void registerForPush(RegisterForPushModel obj, Callback<ResponseBody> addNewDriverResponseCallback) {
        RetrofitClient.getClient().registerForPush(obj).enqueue(addNewDriverResponseCallback);
    }

    // driver drop dowan ---spinners for owner
    public static void getDriverList(DriverDropDownRequest driverDropDownRequest, Callback<DriverDropDownResponse> driverDropDownResponseCallback) {
        RetrofitClient.getClient().driverDataResponse(driverDropDownRequest).enqueue(driverDropDownResponseCallback);
    }

    // vehicle Type drop dowan -- spinners for owner for add new vehicle
    public static void getVehicleTypeList(VehicleTypeDropDowanRequest vehicleTypeDropDowanRequest, Callback<VehicleTypeDropDowanResponse> vehicleTypeDropDowanResponseCallback) {
        RetrofitClient.getClient().vehicleTypeDropDowanResp(vehicleTypeDropDowanRequest).enqueue(vehicleTypeDropDowanResponseCallback);
    }

    // vehicle list spinner in AssignD2s /////////////////////////////////////
    public static void getVehicleAssignList(VehicleAssignDropDowanRequest vehicleAssignDropDowanRequest, Callback<VehicleAssignDropDowanResponse> vehicleAssignDropDowanResponseCallback) {
        RetrofitClient.getClient().VEHICLE_ASSIGN_DROP_DOWAN_RESPONSE_CALL(vehicleAssignDropDowanRequest).enqueue(vehicleAssignDropDowanResponseCallback);
    }

  /*  public static void getVehicleList(Callback<VehicleDropDownResponse> callback) {
        RetrofitClient.getClient().vehicleData().enqueue(callback);   // ByPart
    }*/

    // Approx Load spinners
    public static void getApproxLoadList(ApproxLoadDropDownRequest approxLoadDropDownRequest, Callback<ApproxLoadDropDownResponse> approxLoadResponseCallback) {
        RetrofitClient.getClient().approxLoadResponse(approxLoadDropDownRequest).enqueue(approxLoadResponseCallback);
    }

    // Goods types Spinners in miller module ...book load
    public static void getGoodsTypesLIst(GoodsTypeDropDownRequest goodsTypeDropDownRequest, Callback<GoodsTypesDropDownResponse> goodsTypeResponseCallback) {
        RetrofitClient.getClient().goodsTypesResponse(goodsTypeDropDownRequest).enqueue(goodsTypeResponseCallback);
    }

    // getAll driver details
    public static void getAllDriverDetails(GetAllDriverRequest getAllDriverRequest, Callback<GetAllDriverResponse> getAllDriverResponseCallback) {
        RetrofitClient.getClient().getAllDriverDetailsRes(getAllDriverRequest).enqueue(getAllDriverResponseCallback);
    }

    // getAll Vehicle details
    public static void getAllVehicleDetails(GetAllVehicleRequest getAllVehicleRequest, Callback<GetAllVehicleResponse> getAllVehicleResponseCallback) {
        RetrofitClient.getClient().getVehicleResponse(getAllVehicleRequest).enqueue(getAllVehicleResponseCallback);
    }

    // Assign Driver 2 vehicle
    public static void assign_D2V(AssignD2VRequest assignD2VRequest, Callback<AssignD2VResponse> assignD2VResponseCallback) {
        RetrofitClient.getClient().assignD2VReq(assignD2VRequest).enqueue(assignD2VResponseCallback);
    }

    //Goods type DataVehicle Booking With details Activity
    public static void selectUrGoodsType(GoodsTypeDataRequest goodsTypeDataRequest, Callback<GoodsTypeDataResponse> selectUrGoodsTypeDataResponseCallback) {
        RetrofitClient.getClient().SELECT_UR_GOODS_TYPE_DATA_RESPONSE_CALL(goodsTypeDataRequest).enqueue(selectUrGoodsTypeDataResponseCallback);
    }

    // update receiver no
    public static void updateReceiverNameOrNumber(UpdateReceiverPhoneNoAndNameRequest updateReceiverPhoneNoAndNameRequest, Callback<UpdateReceiverPhoneNoAndNameResponse> updateReceiverPhoneNoAndNameResponseCallback) {
        RetrofitClient.getClient().UPDATE_RECEIVER_PHONE_NO_AND_NAME_RESPONSE_CALL(updateReceiverPhoneNoAndNameRequest).enqueue(updateReceiverPhoneNoAndNameResponseCallback);
    }

    //Vehicle diamention size
    public static void vehicleDiamension(VehicleDiamensionSizeRequest vehicleDiamensionSizeRequest, Callback<VehicleDiamensionSizeResponse> vehicleDiamensionSizeResponseCallback) {
        RetrofitClient.getClient().VEHICLE_DIAMENSION_SIZE_RESPONSE_CALL(vehicleDiamensionSizeRequest).enqueue(vehicleDiamensionSizeResponseCallback);
    }

    // vehicle load capicirty through vehicle ownwer
    public static void vehicleLoadCapicity(VehicleLoadCapicityRequest vehicleLoadCapicityRequest, Callback<VehicleLoadCapicityResponse> vehicleLoadCapicityResponseCallback) {
        RetrofitClient.getClient().VEHICLE_LOAD_CAPICITY_RESPONSE_CALL(vehicleLoadCapicityRequest).enqueue(vehicleLoadCapicityResponseCallback);
    }

    // Booking vehicle list through customer
    public static void getBookingVehicleList(BookingVehicleListRequest bookingVehicleListRequest, Callback<BookingVehicleListResponse> bookingVehicleListResponseCallback) {
        RetrofitClient.getClient().BOOKING_VEHICLE_LIST_RESPONSE_CALL(bookingVehicleListRequest).enqueue(bookingVehicleListResponseCallback);
    }

    // conform booking
    public static void confirmBooking(BookingHTripRequest bookingHTripRequest, Callback<BookingHTripResponse> bookingHTripRespCallback) {
        RetrofitClient.getClient().BOOKING_H_TRIP_RESPONSE_CALL(bookingHTripRequest).enqueue(bookingHTripRespCallback);
    }

    // cancel reason type
    public static void getCancleReason(CancelTripReasonRequest cancelTripReasonRequest, Callback<CancelTripReasonResponse> cancelTripReasonResponseCallback) {
        RetrofitClient.getClient().CANCEL_TRIP_REASON_RESPONSE_CALL(cancelTripReasonRequest).enqueue(cancelTripReasonResponseCallback);
    }

    // cancel trip
    public static void getCancelledTripByCust(CancelTripByCustomerRequest cancelTripByCustomerRequest, Callback<CancelTripByCustomerResponse> cancelTripByCustomerResponseCallback) {
        RetrofitClient.getClient().CANCEL_TRIP_BY_CUSTOMER_RESPONSE_CALL(cancelTripByCustomerRequest).enqueue(cancelTripByCustomerResponseCallback);
    }

    // booking vehicle info
    public static void getInfo(BookingVehicleInfoRequest bookingVehicleInfoRequest, Callback<BookingVehicleInfoResponse> bookingVehicleInfoResponseCallback) {
        RetrofitClient.getClient().BOOKING_VEHICLE_INFO_RESPONSE_CALL(bookingVehicleInfoRequest).enqueue(bookingVehicleInfoResponseCallback);
    }

}
