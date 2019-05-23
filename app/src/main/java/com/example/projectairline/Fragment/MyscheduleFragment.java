package com.example.projectairline.Fragment;

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


public class MyscheduleFragment extends Fragment {

    RecyclerView recyclerViewmyschedule;
    int id;
List<Schedulemodel> schedulemodelList;
ProgressBar progressBarmy;
Scheduleadpterfirst scheduleadpterfirst;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_myschedule, container, false);

        id = SharedPreferencemanager.getmInstance(getContext()).getUser().getId();


        progressBarmy = new ProgressBar(getContext());
        progressBarmy.setVisibility(View.VISIBLE);

        recyclerViewmyschedule = v.findViewById(R.id.recycyleviewschedulemy);
        //call code

        Call<List<Schedulemodel>> call = RetrofitClient.getmInstance().getApi().getMyschedulefirst(id);
        call.enqueue(new Callback<List<Schedulemodel>>() {
            @Override
            public void onResponse(Call<List<Schedulemodel>> call, Response<List<Schedulemodel>> response) {
                progressBarmy.setVisibility(View.GONE);


                schedulemodelList = response.body();

                if (response.body()!=null){

                    scheduleadpterfirst = new Scheduleadpterfirst(schedulemodelList,getContext(), fragmentCommunication);
                    recyclerViewmyschedule.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerViewmyschedule.setAdapter(scheduleadpterfirst);



                }

                else
                {
                    progressBarmy.setVisibility(View.GONE);

                    Toast.makeText(getContext(), "No Data To Display", Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onFailure(Call<List<Schedulemodel>> call, Throwable t) {

                progressBarmy.setVisibility(View.GONE);

                Toast.makeText(getContext(), "Error in connection", Toast.LENGTH_SHORT).show();

            }
        });


        return v;
    }

    FragmentCommunication fragmentCommunication = new FragmentCommunication() {
        @Override
        public void respond(int position, String date) {

            MyscheduleFragSecond fragmentB=new MyscheduleFragSecond();
            Bundle bundle=new Bundle();
            bundle.putString("date",date);

            fragmentB.setArguments(bundle);
            FragmentManager manager=getFragmentManager();
            FragmentTransaction transaction=manager.beginTransaction();
            transaction.replace(R.id.fragmentplace,fragmentB).commit();

        }
    };


}
