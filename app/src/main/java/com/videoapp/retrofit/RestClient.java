package com.videoapp.retrofit;

import com.videoapp.model.LoginResponse;
import com.videoapp.model.CommonApiResponse;

import okhttp3.RequestBody;
import retrofit2.Callback;

public class RestClient {
    private static final String TAG = "RestClient";


//    public static void loginUser(String email, String paswword, Callback<LoginResponse> callback) {
//        RetrofitClient.getClient().loginUser(email,paswword).enqueue(callback);
//    }
//

    public static void loginUser(RequestBody body, Callback<LoginResponse> callback) {
        RetrofitClient.getClient().loginUser(body).enqueue(callback);
    }

    public static void getProjects(RequestBody name,RequestBody email_id, RequestBody mobile_no ,RequestBody password, Callback<CommonApiResponse> callback) {
        RetrofitClient.getClient().registerUser(name,email_id,mobile_no,password).enqueue(callback);
    }

}