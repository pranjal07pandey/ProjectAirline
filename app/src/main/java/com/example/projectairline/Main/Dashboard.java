package com.example.projectairline.Main;

import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectairline.Fragment.AllScheduleFrag;
import com.example.projectairline.Fragment.MyNotificationFragment;
import com.example.projectairline.Fragment.ProfileFragment;
import com.example.projectairline.Fragment.MyscheduleFragment;
import com.example.projectairline.R;
import com.example.projectairline.Upload;
import com.example.projectairline.Utilities.SharedPreferencemanager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

public class Dashboard extends AppCompatActivity {
    int caseid;
    FragmentTransaction fragmentTransaction;
    MyscheduleFragment myschedulefragment;
    String pp;
    MyNotificationFragment mynotificationfragment;
    ProfileFragment myProfilefragment;
    FragmentManager fragmentManager;
    private String filename = "sdasfas";
    AllScheduleFrag allScheduleFrag;
    View v;

    private TextView mTextMessage;


    Button buttonYes;
    ImageButton buttonClose;
    Dialog mydialog;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_allschedule:
                    allScheduleFrag = new AllScheduleFrag();
                    fragmentTransaction.addToBackStack(null);

                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragmentplace,allScheduleFrag).commit();
                    return true;
                case R.id.navigation_myprofile:
                    myProfilefragment = new ProfileFragment();
                    fragmentManager = getSupportFragmentManager();
                    fragmentTransaction.addToBackStack(null);
                    fragmentManager.beginTransaction().replace(R.id.fragmentplace,myProfilefragment).commit();
                    return true;
                case R.id.navigation_mynotification:
                    mynotificationfragment = new MyNotificationFragment();
                    fragmentManager = getSupportFragmentManager();
                    fragmentTransaction.addToBackStack(null);

                    fragmentManager.beginTransaction().replace(R.id.fragmentplace,mynotificationfragment).commit();
                    return true;
                case R.id.navigation_myschedules:
                    myschedulefragment = new MyscheduleFragment();
                   fragmentManager = getSupportFragmentManager();
                   fragmentManager.beginTransaction().replace(R.id.fragmentplace,myschedulefragment).commit();



                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_schedule);


            mydialog = new Dialog(Dashboard.this);
            mydialog.setContentView(R.layout.logout_popup);
            buttonYes = mydialog.findViewById(R.id.buttonYes);
            buttonClose = mydialog.findViewById(R.id.buttonClose);

            mTextMessage = (TextView) findViewById(R.id.message);
            BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
            navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragmentplace, new AllScheduleFrag());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                NotificationChannel channel = new NotificationChannel("AirlineNotification", "AirlineNotification", NotificationManager.IMPORTANCE_DEFAULT);
                NotificationManager notificationManager = getSystemService(NotificationManager.class);
                notificationManager.createNotificationChannel(channel);

            }

            FirebaseMessaging.getInstance().subscribeToTopic("general")
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            String msg = "Sucessful";
                            if (!task.isSuccessful()) {
                                msg = "Unsucessgul";
                            }

                            Toast.makeText(Dashboard.this, msg, Toast.LENGTH_SHORT).show();
                        }
                    });



    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main,menu);
        MenuItem menuItem =menu.findItem(R.id.action_settings);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!SharedPreferencemanager.getmInstance(this).isLoggedIn()) {


            Intent intent = new Intent(this, Login.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            mydialog.show();

            buttonYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferencemanager.getmInstance(getApplicationContext()).clear();
                    Intent t= new Intent(Dashboard.this,Login.class);
                    Toast.makeText(Dashboard.this,"Logout Sucessful",Toast.LENGTH_SHORT).show();
                    startActivity(t);

                }
            });

            buttonClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mydialog.dismiss();
                }
            });


            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }


}





