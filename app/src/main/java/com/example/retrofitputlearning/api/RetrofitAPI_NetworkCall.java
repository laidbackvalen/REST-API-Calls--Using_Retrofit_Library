package com.example.retrofitputlearning.api;

import com.example.retrofitputlearning.models.DataModel;
import com.example.retrofitputlearning.models.Retrofit_ResponseModel;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RetrofitAPI_NetworkCall {
    String BASE_URL = "https://jsonplaceholder.typicode.com/";

    @GET("comments")
    Call<List<DataModel>> getAllData();

    @GET("comments/{id}")
    Call<DataModel> getData(@Path("id") int id);

    @POST("comments")
    Call<Retrofit_ResponseModel> createData(@Body Retrofit_ResponseModel retrofitResponseModel);

    @PUT("comments/{id}")
    Call<Retrofit_ResponseModel> updateData(@Path("id") int id, @Body Retrofit_ResponseModel retrofitResponseModel);

    @DELETE("comments/{id}")
    Call<Retrofit_ResponseModel> deleteData(@Path("id") int id);
}
