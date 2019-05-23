package com.example.projectairline.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.projectairline.DataAdapter.ScheduleAdapterSecond;
import com.example.projectairline.DataAdapter.Scheduleadpterfirst;
import com.example.projectairline.Datamodel.Schedulemodel;
import com.example.projectairline.R;
import com.example.projectairline.Utilities.RetrofitClient;
import com.example.projectairline.Utilities.SharedPreferencemanager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyscheduleFragSecond extends Fragment {
    RecyclerView recyclerViewmyschedule;
    ProgressBar progressBar;
    String date;
    List<Schedulemodel> schedulemodelList;
   ScheduleAdapterSecond scheduleAdapterSecond;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        date = getArguments().getString("date");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_myschedule_frag_second, container, false);


        progressBar = new ProgressBar(getContext());
        progressBar.setVisibility(View.VISIBLE);



        recyclerViewmyschedule = v.findViewById(R.id.recycyleviewschedulemysecond);
        //call code

        Call<List<Schedulemodel>> call = RetrofitClient.getmInstance().getApi().getMyschedulesecond(date);
        call.enqueue(new Callback<List<Schedulemodel>>() {
            @Override
            public void onResponse(Call<List<Schedulemodel>> call, Response<List<Schedulemodel>> response) {
                schedulemodelList = response.body();
                progressBar.setVisibility(View.GONE);
                if (response.body()!=null){

                    scheduleAdapterSecond = new ScheduleAdapterSecond(schedulemodelList,getContext());
                    recyclerViewmyschedule.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerViewmyschedule.setAdapter(scheduleAdapterSecond);



                }

                else
                {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getContext(), "No Data To Display", Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onFailure(Call<List<Schedulemodel>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Error in connection", Toast.LENGTH_SHORT).show();

            }
        });


        return v;
    }






}
