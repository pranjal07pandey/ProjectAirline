package com.example.projectairline.Utilities;

import com.example.projectairline.Datamodel.ScheduleCrewFrag;
import com.example.projectairline.Datamodel.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface API {



    @GET("getScheduleCrew.php/")
    Call<List<ScheduleCrewFrag>> getScheduleCrew();


    @FormUrlEncoded
    @POST("login.php/")
    Call<User> verifylogin(
            @Field("username") String username,
            @Field("password") String password
    );


}

