package com.example.projectairline.DataAdapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.projectairline.Datamodel.History;

import com.example.projectairline.Fragment.ProfileFragmentSecond;
import com.example.projectairline.R;
import com.example.projectairline.Utilities.FragmentCommunication;
import com.example.projectairline.Utilities.FragmenyCommunication3;

import java.util.List;

public class HistoryAdapterfirst extends RecyclerView.Adapter<HistoryAdapterfirst.Myviewholder> {

    private List<History> historyList;
    private Context context;
    private FragmenyCommunication3 mcommunicator;
    FragmentManager fragmentManager;
    String month;
    String year;


    public HistoryAdapterfirst(List<History> historyList, Context context, FragmenyCommunication3 mcommunicator){

        this.historyList = historyList;
        this.context = context;
        this.mcommunicator = mcommunicator;

    }



    @NonNull
    @Override
    public HistoryAdapterfirst.Myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        v = layoutInflater.inflate(R.layout.cardhistoryfirst, viewGroup,false);


        return new Myviewholder(v, mcommunicator);
    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder myviewholder, int i) {

        myviewholder.month1.setText(historyList.get(i).getMonth());
        myviewholder.year1.setText(historyList.get(i).getYear());

         month = historyList.get(i).getMonth();
         year= historyList.get(i).getYear();


        ProfileFragmentSecond myNotificationFragmentSecond = new ProfileFragmentSecond();

        Bundle bundle = new Bundle();
        bundle.putString("month",month);
        bundle.putString("year", year);
        myNotificationFragmentSecond.setArguments(bundle);

    }


    @Override
    public int getItemCount() {
        return historyList.size();
    }

    public class Myviewholder extends RecyclerView.ViewHolder implements View.OnClickListener{

        FragmenyCommunication3 fragmentCommunication;
        CardView cardView;
        TextView month1;
        TextView year1;


        public Myviewholder( @NonNull View itemView, FragmenyCommunication3 Communicator) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cardviewhistoryfirst);
            month1 = itemView.findViewById(R.id.monthhistoryfirst);
            year1 = itemView.findViewById(R.id.historydate);
            mcommunicator = Communicator;
            cardView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            mcommunicator.respond(getAdapterPosition(), historyList.get(getAdapterPosition()).
                    getMonth(), historyList.get(getAdapterPosition()).getYear());


        }
    }
}
