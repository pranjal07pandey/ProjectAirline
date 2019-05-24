package com.example.projectairline.Main;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectairline.Fragment.AllScheduleFrag;
import com.example.projectairline.Fragment.MyHistoryFragment;
import com.example.projectairline.Fragment.Mynotificationfragment;
import com.example.projectairline.Fragment.MyscheduleFragment;
import com.example.projectairline.R;
import com.example.projectairline.Utilities.SharedPreferencemanager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class Dashboard extends AppCompatActivity {
    int caseid;
    FragmentTransaction fragmentTransaction;
    MyscheduleFragment myschedulefragment;
    MyHistoryFragment myHistoryFragment;
    Mynotificationfragment mynotificationfragment;
    FragmentManager fragmentManager;
    private String token;
    AllScheduleFrag allScheduleFrag;
    View v;

    private TextView mTextMessage;

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
                case R.id.navigation_mynotifications:
                    mynotificationfragment = new Mynotificationfragment();
                    fragmentManager = getSupportFragmentManager();
                    fragmentTransaction.addToBackStack(null);

                    fragmentManager.beginTransaction().replace(R.id.fragmentplace,mynotificationfragment).commit();
                    return true;
                case R.id.navigation_myhistory:
                    myHistoryFragment = new MyHistoryFragment();
                    fragmentManager = getSupportFragmentManager();
                    fragmentTransaction.addToBackStack(null);

                    fragmentManager.beginTransaction().replace(R.id.fragmentplace,myHistoryFragment).commit();
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

            mTextMessage = (TextView) findViewById(R.id.message);
            BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
            navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragmentplace, new AllScheduleFrag());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

              NotificationChannel channel = new NotificationChannel("AirlineNotification","AirlineNotification",NotificationManager.IMPORTANCE_DEFAULT);
              NotificationManager notificationManager  = getSystemService(NotificationManager.class);
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

        if (id == R.id.action_settings){
            SharedPreferencemanager.getmInstance(this).clear();
            Intent t= new Intent(Dashboard.this,Login.class);
            Toast.makeText(Dashboard.this,"Logout Sucessful",Toast.LENGTH_SHORT).show();
            startActivity(t);
        }
        return super.onOptionsItemSelected(item);
    }


}





