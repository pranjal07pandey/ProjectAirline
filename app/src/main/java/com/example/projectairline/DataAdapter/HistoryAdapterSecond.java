package com.example.projectairline.DataAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.projectairline.Datamodel.History;
import com.example.projectairline.R;

import java.util.List;

public class HistoryAdapterSecond extends RecyclerView.Adapter<HistoryAdapterSecond.Myviewholder> {

    private List<History> historyList;
    private Context context;

    public HistoryAdapterSecond(List<History> historyList, Context context){

        this.historyList = historyList;
        this.context= context;

    }


    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v ;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        v = layoutInflater.inflate(R.layout.cardhistorysecond, viewGroup, false);


        return new Myviewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder myviewholder, int i) {


        myviewholder.month.setText(historyList.get(i).getMonth());
        myviewholder.year.setText(historyList.get(i).getYear());

    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    public class Myviewholder extends RecyclerView.ViewHolder {


        CardView cardView;
        TextView month;
        TextView year;


        public Myviewholder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cardviewhistorysecond);
            year = itemView.findViewById(R.id.historydate);
        }
    }
}
