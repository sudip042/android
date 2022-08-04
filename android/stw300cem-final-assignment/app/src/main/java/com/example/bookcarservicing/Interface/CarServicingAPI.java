package com.example.bookcarservicing.Interface;

import com.example.bookcarservicing.Model.AuthToken;
import com.example.bookcarservicing.Model.Cars;
import com.example.bookcarservicing.Model.Parts;
import com.example.bookcarservicing.Model.Users;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CarServicingAPI {

    @FormUrlEncoded
    @POST("registerusers")
    Call<String> register(@Field("fullname") String fullname, @Field("email") String email, @Field("phonenumber") String phonenumber, @Field("address") String address, @Field("username") String username, @Field("password") String password, @Field("cnfpassword") String cnfpassword, @Field("usertype") String usertype);

    @FormUrlEncoded
    @POST("login")
    Call<AuthToken> loginCheck(@Field("username") String username, @Field("password") String password);

    @GET("user/me")
    Call<Users> userProfile(@Header("Authorization") String token);

    @GET("showparts")
    Call<List<Parts>> getParts();

    @GET("showCars")
    Call<List<Cars>> getCar();

    @GET("/users")
    Call<List<Users>> getUsers();

    @FormUrlEncoded
    @PUT("profileupdate")
    Call<String> updateProfile(@Field("_id") String uid, @Field("fullname") String fullname, @Field("email") String email, @Field("phonenumber") String phonenumber, @Field("address") String address, @Field("username") String username);


    @FormUrlEncoded
    @POST("bookservicing")
    Call<String> Book(@Field("user_id") String user_id, @Field("fullname") String fullname, @Field("phonenumber") String phonenumber, @Field("address") String address, @Field("Carselection") String Carselection , @Field("dateselection") String dateselection, @Field("locationselection") String locationselection);

    @POST("/users/logout")
    Call<Void> logout();

}
