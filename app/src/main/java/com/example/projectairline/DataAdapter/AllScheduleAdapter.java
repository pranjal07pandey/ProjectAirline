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
import com.example.projectairline.SchedulePopup;

import java.util.List;

public class AllScheduleAdapter extends RecyclerView.Adapter<AllScheduleAdapter.Myviewholder> {


    private List<ScheduleCrewFrag> crewFragList;
    private Context mcontext;
    String id, eta, etd, remarks, captainCrew, cabinCrew;

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
    public void onBindViewHolder(@NonNull final Myviewholder myviewholder, final int i) {

        myviewholder.route.setText(crewFragList.get(i).getRoute());
        myviewholder.date.setText(crewFragList.get(i).getDate());
        myviewholder.flightNo.setText(crewFragList.get(i).getFlightno());




        myviewholder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext, SchedulePopup.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id",crewFragList.get(myviewholder.getAdapterPosition()).getId());
                intent.putExtra("eta",crewFragList.get(myviewholder.getAdapterPosition()).getEta());
                intent.putExtra("etd",crewFragList.get(myviewholder.getAdapterPosition()).getEtd());
                intent.putExtra("remarks",crewFragList.get(myviewholder.getAdapterPosition()).getRemarks());
                intent.putExtra("fn",crewFragList.get(myviewholder.getAdapterPosition()).getFlightno());
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
