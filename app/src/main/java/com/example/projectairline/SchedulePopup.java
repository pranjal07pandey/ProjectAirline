package com.example.projectairline;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectairline.Datamodel.Schedulemodel;

import java.util.List;

public class SchedulePopup  extends Activity {

    Button buttonOK;
    String[] cabincrewarray;
    LinearLayout linearLayoutremarks;
    String remarksval;


    private List<Schedulemodel> schedulemodels;

    TextView id, eta, etd, remarks,copilot,pilot, cabincrew,flight,route,date;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.schedule_popup);
        buttonOK = findViewById(R.id.buttonOk);
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        linearLayoutremarks = findViewById(R.id.linearremarks);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * 0.85), (int) (height * 0.65));

        flight = findViewById(R.id.flightv);
        eta = findViewById(R.id.flightEta);
        etd = findViewById(R.id.flightEtd);
        route = findViewById(R.id.route);
        date = findViewById(R.id.datetv);
        remarks = findViewById(R.id.flightRemarks);
        pilot = findViewById(R.id.flightcaptaincrew);
        cabincrew= findViewById(R.id.flightcabincrew);
        copilot = findViewById(R.id.flightcocaptaincrew);

        eta.setText(getIntent().getExtras().getString("eta"));
        etd.setText(getIntent().getExtras().getString("etd"));
       remarksval = getIntent().getExtras().getString("remarks");
        if (remarksval.length()<=1){
            linearLayoutremarks.setVisibility(View.GONE);
        }

        else {
            linearLayoutremarks.setVisibility(View.VISIBLE);
            remarks.setText(remarksval);
        }
        pilot.setText(getIntent().getExtras().getString("pilot"));
        copilot.setText(getIntent().getExtras().getString("copilot"));

        flight.setText(getIntent().getExtras().getString("flightno"));
        route.setText(getIntent().getExtras().getString("route"));
        date.setText(getIntent().getExtras().getString("date"));



        cabincrewarray = getIntent().getStringArrayExtra("cabincrew");

        int arraysize = cabincrewarray.length;

        for (int i = 0;i< arraysize;i++){
          cabincrew.append(cabincrewarray[i]);
          cabincrew.append("\n");

        }




    }

}
