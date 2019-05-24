package com.example.projectairline.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

//import com.example.projectairline.DataAdapter.AllScheduleAdapterFirst;
//import com.example.projectairline.DataAdapter.ScheduleAdapterSecond;
import com.example.projectairline.DataAdapter.Scheduleadpterfirst;
import com.example.projectairline.Datamodel.Schedulemodel;
import com.example.projectairline.R;
import com.example.projectairline.Utilities.FragmentCommunication;
import com.example.projectairline.Utilities.RetrofitClient;
import com.example.projectairline.Utilities.SharedPreferencemanager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AllScheduleFrag extends Fragment {

    RecyclerView recyclerViewAllSchedule;
    List<Schedulemodel> schedulemodelList;
    ProgressBar progressBar;
    Scheduleadpterfirst scheduleadpterfirst;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v = inflater.inflate(R.layout.fragment_all_schedule, container, false);


        progressBar = v.findViewById(R.id.progressbarschedulefrag);
        progressBar.setVisibility(View.VISIBLE);

        recyclerViewAllSchedule = v.findViewById(R.id.recycleviewallschedule);

        Call<List<Schedulemodel>> call = RetrofitClient.getmInstance().getApi().getAllScheduleFirst();
        call.enqueue(new Callback<List<Schedulemodel>>() {
            @Override
            public void onResponse(Call<List<Schedulemodel>> call, Response<List<Schedulemodel>> response) {

                progressBar.setVisibility(View.GONE);
                schedulemodelList = response.body();

                if(response.body()!=null){
                    scheduleadpterfirst = new Scheduleadpterfirst(schedulemodelList, getContext(), fragmentCommunication);
                    recyclerViewAllSchedule.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerViewAllSchedule.setAdapter(scheduleadpterfirst);

                }

            }

            @Override
            public void onFailure(Call<List<Schedulemodel>> call, Throwable t) {

                progressBar.setVisibility(View.GONE);

                Toast.makeText(getContext(), "Error in connection"+ t, Toast.LENGTH_SHORT).show();


            }
        });



        return v;
    }

    FragmentCommunication fragmentCommunication = new FragmentCommunication() {
        @Override
        public void respond(int position, String date) {

            AllScheduleFragSecond fragSecond = new AllScheduleFragSecond();
            Bundle bundle = new Bundle();
            bundle.putString("date", date);

            fragSecond.setArguments(bundle);
            FragmentManager manager = getFragmentManager();

            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.fragmentplace, fragSecond).commit();



        }
    };




}
