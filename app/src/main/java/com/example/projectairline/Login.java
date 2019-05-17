package com.example.projectairline;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectairline.Datamodel.User;
import com.example.projectairline.Utilities.RetrofitClient;
import com.example.projectairline.Utilities.SharedPreferencemanager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    private EditText viewuserId;
    private EditText viewPassword;
    ProgressDialog progressDialog;
    private TextView register;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        viewuserId = findViewById(R.id.userName);
        viewPassword = findViewById(R.id.password);
        register = findViewById(R.id.textRegister);
        button = findViewById(R.id.buttonLogin);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userLogin();



            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent browserintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://logisparktech.com/lic/register"));
                startActivity(browserintent);

            }
        });


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


        Call<User> call = RetrofitClient.getmInstance().getApi().verifylogin(id, password);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                User loginresponse = response.body();

                if (loginresponse.getError()){

                    Toast.makeText(Login.this, "Username or Password Incorrect", Toast.LENGTH_SHORT).show();

                    return;


                }

                else if(loginresponse != null || loginresponse.getError()==false){

                    SharedPreferencemanager.getmInstance(Login.this).saveUser(loginresponse);

                    Toast.makeText(Login.this, loginresponse.getId()+loginresponse.getRole(), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(Login.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

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
            Intent intent = new Intent(Login.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
//
        }


    }

}
