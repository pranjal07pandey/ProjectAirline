package com.example.projectairline.DataAdapter;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.projectairline.Datamodel.Schedulemodel;
import com.example.projectairline.Fragment.MyHistoryFragment;
import com.example.projectairline.Fragment.MyscheduleFragSecond;
import com.example.projectairline.R;
import com.example.projectairline.Utilities.FragmentCommunication;

import java.util.List;

public class Scheduleadpterfirst extends RecyclerView.Adapter<Scheduleadpterfirst.Scheduleadapterviewholder> {

    public Scheduleadpterfirst(List<Schedulemodel> schedulemodels, Context context, FragmentCommunication mCommunicator) {
        this.schedulemodels = schedulemodels;
        this.context = context;
        this.mCommunicator = mCommunicator;
    }

    private List<Schedulemodel> schedulemodels;
    private Context context;
    private FragmentCommunication mCommunicator;
    String date;
FragmentManager fragmentManager;



    @NonNull
    @Override
    public Scheduleadpterfirst.Scheduleadapterviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.cardschedulefirst, viewGroup, false);
        return new Scheduleadapterviewholder(view, mCommunicator);
    }

    @Override
    public void onBindViewHolder(@NonNull Scheduleadapterviewholder scheduleadapterviewholder, int i) {

      scheduleadapterviewholder.datetv.setText(schedulemodels.get(i).getDate());

      date = schedulemodels.get(i).getDate();


           MyscheduleFragSecond myFragment = new MyscheduleFragSecond();

              Bundle bundle=new Bundle();
              bundle.putString("date",date);
              myFragment.setArguments(bundle);



    }


    @Override
    public int getItemCount() {
        return schedulemodels.size();
    }

    public class Scheduleadapterviewholder extends RecyclerView.ViewHolder implements View.OnClickListener {

FragmentCommunication fragmentCommunication;
        CardView cardViewcv;
        TextView datetv;

        public Scheduleadapterviewholder(@NonNull View itemView, FragmentCommunication Communicator ) {
            super(itemView);
            cardViewcv = itemView.findViewById(R.id.cardviewmyschedulefirst);
            datetv = itemView.findViewById(R.id.flightdatefirst);
            mCommunicator = Communicator;

            cardViewcv.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            mCommunicator.respond(getAdapterPosition(),schedulemodels.get(getAdapterPosition()).getDate());


        }
    }
}


