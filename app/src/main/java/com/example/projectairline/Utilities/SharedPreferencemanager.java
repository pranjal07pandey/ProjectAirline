package com.example.projectairline.Utilities;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.projectairline.Datamodel.User;

public class SharedPreferencemanager  {

    private static  final String Shared_Pref_Name = "new_shared_pref";

    private static com.example.projectairline.Utilities.SharedPreferencemanager mInstance;
    private static Context mcontext;

    private SharedPreferencemanager(Context mcontext){
        this.mcontext = mcontext;
    }

    public static synchronized com.example.projectairline.Utilities.SharedPreferencemanager getmInstance(Context mcontext){

        if(mInstance == null){
            mInstance = new com.example.projectairline.Utilities.SharedPreferencemanager(mcontext);

        }
        return mInstance;
    }

    public void saveUser(User user){

        SharedPreferences sharedPreferences = mcontext.getSharedPreferences(Shared_Pref_Name,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("id", user.getId());
        editor.putString("userid", user.getUserid());
        editor.putInt("verified", user.getVerified());
        editor.putString("role", user.getRole());
        editor.putString("profilepic",user.getProfilepic());
        editor.apply();


    }

    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("id", -1)!= -1;
    }

    public User getUser(){
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences(Shared_Pref_Name,Context.MODE_PRIVATE);
        return new User(

                sharedPreferences.getInt("id", -1),
                sharedPreferences.getString("userid", null),
                sharedPreferences.getString("password", null),
                sharedPreferences.getString("role", null),
                sharedPreferences.getString("profilepic",null),
                sharedPreferences.getInt("verified", 1)



                );

    }

    public void clear(){
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

    }



}
