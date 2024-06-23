package com.example.heathcare_app.api;

import com.example.heathcare_app.model.ApiResponse;
import com.example.heathcare_app.model.LoginResponse;
import com.example.heathcare_app.model.Metadata;
import com.example.heathcare_app.model.SignupResponse;
import com.example.heathcare_app.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
public interface ApiService {
    Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();
    ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://192.168.0.3:3000/v1/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);
    @POST("auth/signup")
    Call<SignupResponse> signup(@Body User user);
    @POST("auth/login")
    Call<ApiResponse<Metadata>> login(@Body User user);
    @GET("users")
    Call<List<User>> getListUser();
    @GET("articles")
    Call<ArticleResponse> getArticles();
}
