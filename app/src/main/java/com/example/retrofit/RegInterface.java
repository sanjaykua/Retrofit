package com.example.retrofit;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RegInterface {

    @Headers("Content-Type:application/json")
    @POST("api.php")
    Call<RegResponse> createUser(@Body JsonObject jsonObject);
}
