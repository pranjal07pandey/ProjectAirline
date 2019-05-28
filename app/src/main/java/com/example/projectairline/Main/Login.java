package com.example.projectairline.Main;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectairline.Datamodel.User;
import com.example.projectairline.R;
import com.example.projectairline.Utilities.RetrofitClient;
import com.example.projectairline.Utilities.SharedPreferencemanager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    private EditText viewuserId;
    private EditText viewPassword;
    ProgressDialog progressDialog;
    private TextView register;
    private Button button;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        viewuserId = findViewById(R.id.userName);
        viewPassword = findViewById(R.id.password);
        button = findViewById(R.id.buttonLogin);

        firebaseinit();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userLogin();


            }
        });



    }


    private String firebaseinit() {

        FirebaseApp.initializeApp(Login.this);

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {

//                            Toast.makeText(Login.this,"Failed"+task.getException(),Toast.LENGTH_LONG).show();


                            return;
                        }

                        else {

//
                            token = task.getResult().getToken();

//

                        }

                    }
                });





        FirebaseMessaging.getInstance().subscribeToTopic("general")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Sucessful";
                        if (!task.isSuccessful()) {
                            msg = "Not Sucessful";
                        }

//                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });

        return token;



    }

    private void userLogin() {

        String id = viewuserId.getText().toString().trim();
        String password = viewPassword.getText().toString().trim();


        if (id.isEmpty()){

            viewuserId.setError("Please Enter Username");
            viewuserId.requestFocus();
            return;

        }

        if(password.isEmpty()){
            viewPassword.setError("Password is empty");
            viewPassword.requestFocus();
            return;

        }


        Call<User> call = RetrofitClient.getmInstance().getApi().verifylogin(id, password,token);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                User loginresponse = response.body();



                if (loginresponse == null){


                    Toast.makeText(Login.this, "Username or Password Incorrect", Toast.LENGTH_SHORT).show();
                    viewuserId.setText("");
                    viewPassword.setText("");



                }

                else if(loginresponse != null){

                    SharedPreferencemanager.getmInstance(Login.this).saveUser(loginresponse);
                    Intent intent = new Intent(Login.this, Dashboard.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(Login.this, "Error or Not Internet connection"+t, Toast.LENGTH_LONG).show();


            }
        });


    }


    @Override
    protected void onStart(){

        super.onStart();

        if (SharedPreferencemanager.getmInstance(this).isLoggedIn()) {
//
            Intent intent = new Intent(Login.this, Dashboard.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
//
        }


    }

}
