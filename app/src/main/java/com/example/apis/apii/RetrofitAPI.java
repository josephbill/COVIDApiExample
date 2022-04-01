package com.example.apis.apii;

import com.example.apis.pojos.PostModal;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitAPI {

    @POST("users")
    Call<PostModal> createPost(@Body PostModal postModal);
    @GET("summary")
    Call<JSONResponse> getJSON();
}
