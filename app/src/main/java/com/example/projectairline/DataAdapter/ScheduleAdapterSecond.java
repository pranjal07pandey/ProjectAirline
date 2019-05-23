package com.example.projectairline.DataAdapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.projectairline.Datamodel.Schedulemodel;
import com.example.projectairline.R;
import com.example.projectairline.SchedulePopup;

import java.util.List;

public class ScheduleAdapterSecond extends RecyclerView.Adapter<ScheduleAdapterSecond.Myviewholder> {

    Dialog mydialog;
    private List<Schedulemodel> crewFragList;
    String cabincrew[];

    private Context mcontext;

    public ScheduleAdapterSecond(List<Schedulemodel> crewFragList, Context mcontext){
        this.crewFragList = crewFragList;
        this.mcontext = mcontext;
    }


    @NonNull
    @Override
    public ScheduleAdapterSecond.Myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v;
        LayoutInflater layoutInflater = LayoutInflater.from(mcontext);
        v = layoutInflater.inflate(R.layout.cardallschedule, viewGroup, false);

        mydialog = new Dialog(mcontext);
        mydialog.setContentView(R.layout.schedule_popup);





        return new Myviewholder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull final Myviewholder myviewholder, final int i) {

        myviewholder.route.setText(crewFragList.get(i).getRoute());
        myviewholder.date.setText(crewFragList.get(i).getDate());
        myviewholder.flightNo.setText(crewFragList.get(i).getFlightno());



        myviewholder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext, SchedulePopup.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("date",crewFragList.get(myviewholder.getAdapterPosition()).getDate());
                intent.putExtra("route",crewFragList.get(myviewholder.getAdapterPosition()).getRoute());
                intent.putExtra("flightno",crewFragList.get(myviewholder.getAdapterPosition()).getFlightno());
                intent.putExtra("eta",crewFragList.get(myviewholder.getAdapterPosition()).getEta());
                intent.putExtra("etd",crewFragList.get(myviewholder.getAdapterPosition()).getEtd());
                intent.putExtra("remarks",crewFragList.get(myviewholder.getAdapterPosition()).getRemarks());
                intent.putExtra("pilot",crewFragList.get(myviewholder.getAdapterPosition()).getPilot());
                intent.putExtra("copilot",crewFragList.get(myviewholder.getAdapterPosition()).getCopilot());
                intent.putExtra("cabincrew",crewFragList.get(myviewholder.getAdapterPosition()).getCabincrew());


                mcontext.startActivity(intent);
            }
        });


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
