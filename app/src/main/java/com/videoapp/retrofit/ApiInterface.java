package com.videoapp.retrofit;

import com.videoapp.model.LoginResponse;
import com.videoapp.model.CommonApiResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {

    @Multipart
    @POST("dev/api/api.php/req=video")

    Call<CommonApiResponse> uploadVideoToServer(@Part("customer_id") RequestBody customer_id, @Part MultipartBody.Part body);

//    @POST("dev/api/api.php/req=login")
//    Call<LoginResponse> loginUser(@Field(value="email_id",encoded = true) String email, @Field(value = "password",encoded = true) String password);
//

    @POST("dev/api/api.php/req=login")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Call<LoginResponse> loginUser(@Part RequestBody body);

    @POST("dev/api/api.php/req=registration")
    Call<CommonApiResponse> registerUser(@Field("name") RequestBody name,
                                         @Part("email_id") RequestBody email,
                                         @Part("mobile_no") RequestBody mobile,
                                         @Part("password") RequestBody password);
}