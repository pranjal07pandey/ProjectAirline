package com.example.projectairline.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.projectairline.DataAdapter.HistoryAdapterSecond;
import com.example.projectairline.Datamodel.History;
import com.example.projectairline.R;
import com.example.projectairline.Utilities.RetrofitClient;
import com.example.projectairline.Utilities.SharedPreferencemanager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragmentSecond extends Fragment {

    RecyclerView recyclerView;
    int id;
    String year;
    String month;
    List<History> historyList;
    HistoryAdapterSecond historyAdapterSecond;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        month = getArguments().getString("month");
        year = getArguments().getString("year");

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_myprofilesecond, container,false);

        id = SharedPreferencemanager.getmInstance(getContext()).getUser().getId();
        recyclerView = view.findViewById(R.id.recycyleviewallhistorysecond);

        Call<List<History>> call = RetrofitClient.getmInstance().getApi().getAllHistorysecond(id, month, year);
        call.enqueue(new Callback<List<History>>() {
            @Override
            public void onResponse(Call<List<History>> call, Response<List<History>> response) {

                historyList = response.body();
                if(response.body() !=null){
                    historyAdapterSecond = new HistoryAdapterSecond(historyList,getContext());
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setAdapter(historyAdapterSecond);
                }

                else{
                    Toast.makeText(getContext(), "No data to display", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<History>> call, Throwable t) {

                Toast.makeText(getContext(), "Error in Connection", Toast.LENGTH_SHORT).show();

            }
        });


return view;

    }
}
