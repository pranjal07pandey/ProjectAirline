package com.example.projectairline;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Toast;

import com.example.projectairline.DataAdapter.AllScheduleAdapter;
import com.example.projectairline.Datamodel.ScheduleCrewFrag;
import com.example.projectairline.Utilities.RetrofitClient;
import com.example.projectairline.Utilities.SharedPreferencemanager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Myschedule extends Fragment {

    RecyclerView recyclerViewmyschedule;
    int id;
List<ScheduleCrewFrag> scheduleCrewFragList;
AllScheduleAdapter allScheduleAdapterAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_myschedule, container, false);

        id = SharedPreferencemanager.getmInstance(getContext()).getUser().getId();



        recyclerViewmyschedule = v.findViewById(R.id.recycyleviewschedulemy);
        //call code

        Call<List<ScheduleCrewFrag>> call = RetrofitClient.getmInstance().getApi().getScheduleCrew(id);
        call.enqueue(new Callback<List<ScheduleCrewFrag>>() {
            @Override
            public void onResponse(Call<List<ScheduleCrewFrag>> call, Response<List<ScheduleCrewFrag>> response) {
                scheduleCrewFragList = response.body();

                if (response.body()!=null){

                    allScheduleAdapterAdapter = new AllScheduleAdapter(scheduleCrewFragList,getContext());
                    recyclerViewmyschedule.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerViewmyschedule.setAdapter(allScheduleAdapterAdapter);


                }

                else
                {
                    Toast.makeText(getContext(), "No Data To Display", Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onFailure(Call<List<ScheduleCrewFrag>> call, Throwable t) {

                Toast.makeText(getContext(), "Error in connection", Toast.LENGTH_SHORT).show();

            }
        });


        return v;
    }

}
