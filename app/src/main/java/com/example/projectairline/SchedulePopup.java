package com.example.projectairline;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.projectairline.Datamodel.ScheduleCrewFrag;

import java.util.List;

public class SchedulePopup  extends Activity {

    Button buttonOK;

    private List<ScheduleCrewFrag> scheduleCrewFrags;
    TextView id, eta, etd, remarks, captaincrew, cabincrew;

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

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * 0.85), (int) (height * 0.55));

        id = findViewById(R.id.flightId);
        eta = findViewById(R.id.flightEta);
        etd = findViewById(R.id.flightEtd);
        remarks = findViewById(R.id.flightRemarks);
        captaincrew = findViewById(R.id.captainCrew);
        cabincrew= findViewById(R.id.cabinCrew);

        id.setText(getIntent().getExtras().getString("id"));
        eta.setText(getIntent().getExtras().getString("eta"));
        etd.setText(getIntent().getExtras().getString("etd"));
        remarks.setText(getIntent().getExtras().getString("remarks"));
        captaincrew.setText(getIntent().getExtras().getString("captaincrew"));
        cabincrew.setText(getIntent().getExtras().getString("fn"));




    }

}
