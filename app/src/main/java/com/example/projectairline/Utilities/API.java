package com.example.projectairline.Utilities;

import com.example.projectairline.Datamodel.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface API {


    @FormUrlEncoded
    @POST("login.php/")
    Call<User> verifylogin(
            @Field("username") String username,
            @Field("password") String password
    );
}
