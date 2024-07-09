package com.example.heathcare_app.api;

import com.example.heathcare_app.model.ApiResponseBookAppointment;
import com.example.heathcare_app.model.ApiResponseDoctor;
import com.example.heathcare_app.model.ApiResponseGetBookAppointment;
import com.example.heathcare_app.model.ApiResponsePatchBookAppointment;
import com.example.heathcare_app.model.ArticleResponse;
import com.example.heathcare_app.model.BodyDeleteCart;
import com.example.heathcare_app.model.BodyUpdate;
import com.example.heathcare_app.model.Cart;
import com.example.heathcare_app.model.CartResponse;
import com.example.heathcare_app.model.DataCarts;
import com.example.heathcare_app.model.MedicineResponse;
import com.example.heathcare_app.model.ApiResponse;
import com.example.heathcare_app.model.Metadata;
import com.example.heathcare_app.model.BookAppointment;

import com.example.heathcare_app.model.SignupResponse;
import com.example.heathcare_app.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiService {
   String urlIpByTuanAnh = "http://192.168.1.69:3000/v1/api/";
    String urlIpByDat = "http://192.168.0.3:3000/v1/api/";
//    String urlIpByDat = "http://172.16.25.75:3000/v1/api/";
    Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();
    ApiService apiService = new Retrofit.Builder()
            .baseUrl(urlIpByTuanAnh)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);
    @POST("auth/signup")
    Call<ApiResponse<Metadata>> signup(@Body User user);
    @POST("auth/login")
    Call<ApiResponse<Metadata>> login(@Body User user);
    @PATCH("book-appointments/{id}")
    Call<ApiResponsePatchBookAppointment> handlePatchBookAppointments(@Path("id") int id , @Body HashMap<String, String> body);
    @GET("orders")
    Call<ApiResponse<DataCarts>> handleGetOrderDetails(@Query("userId") int userId,@Query("status") String status);
    @PATCH("order")
    Call<ApiResponse<Object>> handleUpdateItemOrderDetails(@Body BodyUpdate bodyUpdate);
    @DELETE("order/{id}")
    Call<ApiResponse<Integer>> handleRemoveItemOrdeDetails(@Path("id") int orderId);
    @GET("book-appointments")
    Call<ApiResponseGetBookAppointment> handleGetBookAppointments(@Query("userId") int userId);
    @POST("book-appointments")
    Call<ApiResponseBookAppointment> handleBookAppointments(@Body BookAppointment bookAppointment);
    @GET("doctors")
    Call<ApiResponseDoctor> getListDoctors(@Query("doctorGroupId") int doctorGroupId);
    @GET("users")
    Call<List<User>> getListUser();
    @GET("articles")
    Call<ArticleResponse> getArticles();
    @GET("medicines")
    Call<MedicineResponse> getMedicines();
    @POST("order")
    Call<CartResponse> handleCreateOrder(@Body Cart cart);
}
