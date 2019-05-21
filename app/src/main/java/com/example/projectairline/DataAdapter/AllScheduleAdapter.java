package com.example.projectairline.DataAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.projectairline.AllSchedule;
import com.example.projectairline.Datamodel.ScheduleCrewFrag;
import com.example.projectairline.R;

import java.util.List;

public class AllScheduleAdapter extends RecyclerView.Adapter<AllScheduleAdapter.Myviewholder> {


    private List<ScheduleCrewFrag> crewFragList;
    private Context mcontext;

    public AllScheduleAdapter(List<ScheduleCrewFrag> crewFragList, Context mcontext){
        this.crewFragList = crewFragList;
        this.mcontext = mcontext;
    }


    @NonNull
    @Override
    public AllScheduleAdapter.Myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v;
        LayoutInflater layoutInflater = LayoutInflater.from(mcontext);
        v = layoutInflater.inflate(R.layout.cardallschedule, viewGroup, false);
        return new Myviewholder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder myviewholder, int i) {

        myviewholder.route.setText(crewFragList.get(i).getRoute());
        myviewholder.date.setText(crewFragList.get(i).getDate());
        myviewholder.flightNo.setText(crewFragList.get(i).getFlightno());

    }

    @Override
    public int getItemCount() {
        return crewFragList.size();
    }

    public class Myviewholder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView date;
        TextView route;
        TextView flightNo;



        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardviewmyschedule);
            date = itemView.findViewById(R.id.flightdate);
            flightNo= itemView.findViewById(R.id.flightno);
            route = itemView.findViewById(R.id.flightroute);





        }
    }
}
