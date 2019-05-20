package com.example.projectairline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.projectairline.Utilities.SharedPreferencemanager;

public class MainActivity extends AppCompatActivity {

//    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        toolbar = findViewById(R.id.toolbar2);
//        setSupportActionBar(toolbar);

        getSupportActionBar().hide();

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
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.main, menu);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

                    SharedPreferencemanager.getmInstance(getApplicationContext()).clear();
                    Intent t= new Intent(MainActivity.this,Login.class);
                    Toast.makeText(MainActivity.this,"Logout Sucessful",Toast.LENGTH_SHORT).show();
                    startActivity(t);
                    return true;
                }
        return super.onOptionsItemSelected(item);

    }


}
