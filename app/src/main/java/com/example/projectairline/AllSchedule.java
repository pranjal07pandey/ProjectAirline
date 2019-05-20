package com.example.projectairline;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class AllSchedule extends AppCompatActivity {
    FragmentTransaction fragmentTransaction;
    Fragment fragment;
    View v;

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_allschedule:
               getSupportFragmentManager().beginTransaction().replace(R.id.fragmentplace,new AllScheduleFrag());

                    return true;
                case R.id.navigation_mynotifications:
                    return true;
                case R.id.navigation_myhistory:
                    return true;
                case R.id.navigation_myschedules:
                    Changefragment(v);

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

    public void Changefragment(View view){


        if (view == findViewById(R.id.navigation_allschedule)){
            fragment = new AllScheduleFrag();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentplace,fragment);
            fragmentTransaction.commit();


        }

        if (view == findViewById(R.id.navigation_myschedules)){
            Toast.makeText(AllSchedule.this, "My", Toast.LENGTH_SHORT).show();


            fragment = new Myschedule();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.replace(R.id.fragmentplace,fragment);
            fragmentTransaction.commit();



        }



    }

}
