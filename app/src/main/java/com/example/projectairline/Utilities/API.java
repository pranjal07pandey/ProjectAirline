package com.example.projectairline.Utilities;

import com.example.projectairline.Datamodel.History;
import com.example.projectairline.Datamodel.Schedulemodel;
import com.example.projectairline.Datamodel.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface API {



    @GET("getDateCrew.php/")
    Call<List<Schedulemodel>> getMyschedulefirst(@Query("id") int id);

    @GET("getScheduleDateWise.php/")
    Call<List<Schedulemodel>> getMyschedulesecond(@Query("date") String date, @Query("id")int id);

    @GET("getAllSchedule.php/")
    Call<List<Schedulemodel>> getAllScheduleFirst();

    @GET("getScheduleDetail.php/")
    Call<List<Schedulemodel>> getAllScheduleSecond(@Query("date") String date, @Query("id") int id);


    @GET("getHistoryMonth.php/")
    Call<List<History>> getAllHistory(@Query("id") int id);

    @GET("getMonthDetail.php/")
    Call<List<History>> getAllHistorysecond(@Query("id") int id, @Query("month") String month, @Query("year") String year);





    @FormUrlEncoded
    @POST("login.php/")
    Call<User> verifylogin(
            @Field("username") String username,
            @Field("password") String password,
            @Field("key") String key
    );


}

