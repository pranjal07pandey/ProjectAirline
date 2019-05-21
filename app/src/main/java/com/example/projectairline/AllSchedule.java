package com.example.projectairline;

import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectairline.Utilities.SharedPreferencemanager;

public class AllSchedule extends AppCompatActivity {
    FragmentTransaction fragmentTransaction;
    Myschedule myschedulefragment;
    MyHistoryFragment myHistoryFragment;
    Mynotificationfragment mynotificationfragment;
    FragmentManager fragmentManager;
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
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragmentplace,allScheduleFrag).commit();
                    return true;
                case R.id.navigation_mynotifications:
                    mynotificationfragment = new Mynotificationfragment();
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragmentplace,mynotificationfragment).commit();
                    return true;
                case R.id.navigation_myhistory:
                    myHistoryFragment = new MyHistoryFragment();
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragmentplace,myHistoryFragment).commit();
                    return true;
                case R.id.navigation_myschedules:
                    myschedulefragment = new Myschedule();
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
        fragmentTransaction.replace(R.id.fragmentplace,new AllScheduleFrag());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main,menu);
        MenuItem menuItem =menu.findItem(R.id.action_settings);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings){
            SharedPreferencemanager.getmInstance(this).clear();
            Intent t= new Intent(AllSchedule.this,Login.class);
            Toast.makeText(AllSchedule.this,"Logout Sucessful",Toast.LENGTH_SHORT).show();
            startActivity(t);
        }
        return super.onOptionsItemSelected(item);
    }
}
}


