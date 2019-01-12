package com.videoapp.retrofit;

import com.videoapp.model.LoginResponse;
import com.videoapp.model.CommonApiResponse;
import com.videoapp.model.RecommendedApiResponse;
import com.videoapp.model.VideoListResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
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


    @Multipart
    @POST("dev/api/api.php?req=login")
    Call<LoginResponse> loginUser(@Part("email_id") RequestBody actionId, @Part("password") RequestBody offerCode);

    @GET("dev/api/api.php?req=videolist")
    Call<VideoListResponse> getVideoList();

    @Multipart
    @POST("http://akwebtech.com/dev/api/api.php?req=registration")
    Call<CommonApiResponse> registerUser(@Part("name") RequestBody name,
                                         @Part("email_id") RequestBody email,
                                         @Part("mobile_no") RequestBody mobile,
                                         @Part("image") RequestBody image,
                                         @Part("password") RequestBody password);

    @Multipart
    @POST("http://akwebtech.com/dev/api/api.php?req=recommended")
    Call<RecommendedApiResponse> getRecommendedList(@Part("user_id") RequestBody user_id);
}