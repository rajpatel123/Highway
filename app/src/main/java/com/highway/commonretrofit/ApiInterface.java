package com.highway.commonretrofit;

import com.highway.common.base.commonModel.bookingHTrip.BookingHTripRequest;
import com.highway.common.base.commonModel.bookingHTrip.BookingHTripResponse;
import com.highway.common.base.commonModel.customerDiverOwnerModelsClass.allHighwayTripModel.GetAllTripByUserIdRequest;
import com.highway.common.base.commonModel.customerDiverOwnerModelsClass.allHighwayTripModel.GetAllTripByUserIdResponse;
import com.highway.common.base.commonModel.login.LoginReqUpdated;
import com.highway.common.base.commonModel.otpverify.VerifyOtpRequest;
import com.highway.common.base.commonModel.otpverify.VerifyOtpResponse;
import com.highway.common.base.commonModel.registration.RegistrationReqUpdated;
import com.highway.common.base.commonModel.registration.RegistrationRespUpdated;
import com.highway.customer.RegisterForPushModel;
import com.highway.customer.customerModelClass.bookingVehicleList.BookingVehicleListRequest;
import com.highway.customer.customerModelClass.bookingVehicleList.BookingVehicleListResponse;
import com.highway.customer.customerModelClass.cancleTripModel.cancleReason.CancelTripReasonRequest;
import com.highway.customer.customerModelClass.cancleTripModel.cancleReason.CancelTripReasonResponse;
import com.highway.customer.customerModelClass.cancleTripModel.cancleTrip.CancelTripByCustomerRequest;
import com.highway.customer.customerModelClass.cancleTripModel.cancleTrip.CancelTripByCustomerResponse;
import com.highway.customer.customerModelClass.driverLocation.NearByDriverLocationResponse;
import com.highway.customer.customerModelClass.selectYoursGoodsType.GoodsTypeDataRequest;
import com.highway.customer.customerModelClass.selectYoursGoodsType.GoodsTypeDataResponse;
import com.highway.customer.customerModelClass.updateReceiverModel.UpdateReceiverPhoneNoAndNameRequest;
import com.highway.customer.customerModelClass.updateReceiverModel.UpdateReceiverPhoneNoAndNameResponse;
import com.highway.drivermodule.driverModelClass.BookingAcceptRejectData;
import com.highway.drivermodule.driverModelClass.BookingAcceptRejectResponse;
import com.highway.customer.customerModelClass.vehicleInfo.BookingVehicleInfoRequest;
import com.highway.customer.customerModelClass.vehicleInfo.BookingVehicleInfoResponse;
import com.highway.drivermodule.driverModelClass.DriverResponse;
import com.highway.drivermodule.driverModelClass.DriverStartTripRequest;
import com.highway.drivermodule.driverModelClass.VehicleCurrentLocation;
import com.highway.millUserModule.SpinnerModelForMiller.ApproxLoad.ApproxLoadDropDownRequest;
import com.highway.millUserModule.SpinnerModelForMiller.ApproxLoad.ApproxLoadDropDownResponse;
import com.highway.millUserModule.SpinnerModelForMiller.GoodsTypes.GoodsTypeDropDownRequest;
import com.highway.millUserModule.SpinnerModelForMiller.GoodsTypes.GoodsTypesDropDownResponse;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.addNewDriverThroughVehicleOwner.AddNewDriverRequest;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.addNewDriverThroughVehicleOwner.AddNewDriverResponse;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.addVehicle.AddVehicleRequest;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.addVehicle.AddVehicleResponse;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.assignD2V.assignD2V_Model.AssignD2VRequest;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.assignD2V.assignD2V_Model.AssignD2VResponse;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.assignD2V.driverAssignSpinner.DriverDropDownRequest;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.assignD2V.driverAssignSpinner.DriverDropDownResponse;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.assignD2V.vehicleAssignSpinner.VehicleAssignDropDowanRequest;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.assignD2V.vehicleAssignSpinner.VehicleAssignDropDowanResponse;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.getAllDriver.GetAllDriverRequest;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.getAllDriver.GetAllDriverResponse;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.getAllVehicle.GetAllVehicleRequest;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.getAllVehicle.GetAllVehicleResponse;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.vehiceLoadCapicity.VehicleLoadCapicityRequest;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.vehiceLoadCapicity.VehicleLoadCapicityResponse;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.vehicleDimensionSize.VehicleDiamensionSizeRequest;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.vehicleDimensionSize.VehicleDiamensionSizeResponse;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.vehicleTypeDropDowan.VehicleTypeDropDowanRequest;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.vehicleTypeDropDowan.VehicleTypeDropDowanResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    // mobile logon
    @POST("api/Login/login_register")
    Call<ResponseBody> loginResponseCall(@Body LoginReqUpdated loginReqUpdated);

    // otp verification
    @POST("api/Login/otp_verify")
    Call<VerifyOtpResponse> verifyOtpResponseCall(@Body VerifyOtpRequest verifyOtpRequest);

    // Registration Details
    @POST("api/Login/signup")
    Call<RegistrationRespUpdated> regDetailsResponseCall(@Body RegistrationReqUpdated registrationReqUpdated);

    // Driver All Trips
    @POST("api/Trip/getAllTripByUserId")
    Call<GetAllTripByUserIdResponse> driverTrips(@Body GetAllTripByUserIdRequest getAllTripByUserIdRequest);

    // Vehicle Owner trip details
    @POST("api/Trip/getAllTripByUserId")
    Call<GetAllTripByUserIdResponse> vehicleOwnerTrip(@Body GetAllTripByUserIdRequest getAllTripByUserIdRequest);

    // Miller all trips details
    @POST("api/Trip/getAllTripByUserId")
    Call<GetAllTripByUserIdResponse> millerTrip(@Body GetAllTripByUserIdRequest getAllTripByUserIdRequest);

    // Customer all trip
    @POST("api/Trip/getAllTripByUserId")
    Call<GetAllTripByUserIdResponse> customerTrip(@Body GetAllTripByUserIdRequest getAllTripByUserIdRequest);

    // Add New Vehicle
    @POST("index.php/api/Vehicle/addVehicle")
    Call<AddVehicleResponse> addVehicleResponseCall(@Body AddVehicleRequest addVehicleRequest);

    // Add new Driver
    @POST("index.php/api/Login/addDriver")
    Call<AddNewDriverResponse> addNewDriverResponseCall(@Body AddNewDriverRequest addNewDriverRequest);

    // vehicle type drop dowan --spinner for vehicle owners for Add new Vehicle
    @POST("api/Vehicle/vehicleTypeDropdown")
    Call<VehicleTypeDropDowanResponse> vehicleTypeDropDowanResp(@Body VehicleTypeDropDowanRequest vehicleTypeDropDowanRequest);

    // Driver drop down ----spinners
    @POST("index.php/api/vehicle/driverDropdown")
    Call<DriverDropDownResponse> driverDataResponse(@Body DriverDropDownRequest driverDropDownRequest);

    //vehicle drop dowan --spinner for AssignD2V
    @POST("index.php/api/vehicle/vehicleAssignDropdown")
    Call<VehicleAssignDropDowanResponse> VEHICLE_ASSIGN_DROP_DOWAN_RESPONSE_CALL(@Body VehicleAssignDropDowanRequest vehicleAssignDropDowanRequest);

    // Assign Driver 2 vehicle
    @POST("api/Vehicle/assignDriverToVehicle")
    Call<AssignD2VResponse> assignD2VReq(@Body AssignD2VRequest assignD2VRequest);

    // Goods  Types in miller module ..book load ----Spinners
    @POST("index.php/api/trip/goodType")
    Call<GoodsTypesDropDownResponse> goodsTypesResponse(@Body GoodsTypeDropDownRequest goodsTypeDropDownRequest);

    // Approx load spinners -- for miller Book load
    @POST("index.php/api/trip/approxLoad")
    Call<ApproxLoadDropDownResponse> approxLoadResponse(@Body ApproxLoadDropDownRequest approxLoadDropDownRequest);

    // getAll Driver List for owner;
    @POST("index.php/api/login/getAlldriverDetails")
    Call<GetAllDriverResponse> getAllDriverDetailsRes(@Body GetAllDriverRequest getAllDriverRequest);

    @POST("index.php/api/Vehicle/getAllVehicleDetails")
    Call<GetAllVehicleResponse> getVehicleResponse(@Body GetAllVehicleRequest getAllVehicleRequest);

    //Goods type DataVehicle Booking With details Activity
    @POST("api/Trip/selectYourGoodType")
    Call<GoodsTypeDataResponse> SELECT_UR_GOODS_TYPE_DATA_RESPONSE_CALL(@Body GoodsTypeDataRequest goodsTypeDataRequest);

    // Add receiver mob no and name
    @POST("api/Login/updateReceiver")
    Call<UpdateReceiverPhoneNoAndNameResponse> UPDATE_RECEIVER_PHONE_NO_AND_NAME_RESPONSE_CALL(@Body UpdateReceiverPhoneNoAndNameRequest updateReceiverPhoneNoAndNameRequest);

    /// vehicle diamension size in add vehicle through vehicle owner
    @POST("api/Vehicle/vehicleDimensionSize")
    Call<VehicleDiamensionSizeResponse> VEHICLE_DIAMENSION_SIZE_RESPONSE_CALL(@Body VehicleDiamensionSizeRequest vehicleDiamensionSizeRequest);

    // vehicle load capicity through vehicle owner
    @POST("api/Vehicle/vehicleLoadingCapicity")
    Call<VehicleLoadCapicityResponse> VEHICLE_LOAD_CAPICITY_RESPONSE_CALL(@Body VehicleLoadCapicityRequest vehicleLoadCapicityRequest);

    // Booking vehicle List
    @POST("api/Vehicle/bookingVehicleList")
    Call<BookingVehicleListResponse> BOOKING_VEHICLE_LIST_RESPONSE_CALL(@Body BookingVehicleListRequest bookingVehicleListRequest);

    // Register for pushnotification
    @POST("api/Notification/registerPushNotification")
    Call<ResponseBody> registerForPush(@Body RegisterForPushModel vehicleInfoRequest);

    // conform booking Request
    @POST("api/Booking/confirmBookingApi")
    Call<BookingHTripResponse> BOOKING_H_TRIP_RESPONSE_CALL(@Body BookingHTripRequest bookingHTripRequest);

    //cancle reason
    @POST("api/Booking/cancelTripReason")
    Call<CancelTripReasonResponse>CANCEL_TRIP_REASON_RESPONSE_CALL (@Body CancelTripReasonRequest cancelTripReasonRequest);

    //cancel trip by customer
    @POST("api/Booking/cancelTripByCustomer")
    Call<CancelTripByCustomerResponse>CANCEL_TRIP_BY_CUSTOMER_RESPONSE_CALL(@Body CancelTripByCustomerRequest cancelTripByCustomerRequest);

    // Vehicle Info
    @POST("api/Booking/bookingInfoForCustomer")
    Call<BookingVehicleInfoResponse> BOOKING_VEHICLE_INFO_RESPONSE_CALL(@Body BookingVehicleInfoRequest bookingVehicleInfoRequest);



    // accept/reject booking trip by driver
    @POST("api/Booking/acceptBookTrip")
    Call<BookingAcceptRejectResponse> ACCEPT_REJECT_BOOKING_TRIP_RESPONSE_CALL(@Body BookingAcceptRejectData bookingAcceptReject);

    // get near by driver to the particular location
    @GET("api/Booking/getNearByDriverLocation")
    Call<NearByDriverLocationResponse> GET_NEAR_BY_DRIVER_LOCATION_RESPONSE_CALL();

    // Add location route used by the driver
    @POST("api/Booking/addCurrentLocationOfVehicle")
    Call<DriverResponse> VEHICLE_CURRENT_LOCATION_RESPONSE_CALL(@Body VehicleCurrentLocation vehicleCurrentLocation);

    // Driver start the trip from pickup location
    @POST("api/Booking/addCurrentLocationOfVehicle")
    Call<DriverResponse> DRIVER_START_TRIP_RESPONSE_CALL(@Body DriverStartTripRequest startTripRequest);

}