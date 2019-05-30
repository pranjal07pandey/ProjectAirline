package com.example.projectairline.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

import com.example.projectairline.DataAdapter.HistoryAdapterfirst;
import com.example.projectairline.Datamodel.History;
import com.example.projectairline.GlideApp;
import com.example.projectairline.Main.Dashboard;
import com.example.projectairline.Main.UploadImage;
import com.example.projectairline.R;
import com.example.projectairline.Utilities.Fragcommunication2;
import com.example.projectairline.Utilities.FragmentCommunication;
import com.example.projectairline.Utilities.FragmenyCommunication3;
import com.example.projectairline.Utilities.RetrofitClient;
import com.example.projectairline.Utilities.SharedPreferencemanager;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.InputStream;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;
import static com.example.projectairline.Main.UploadImage.SHARED_PREFS;


public class ProfileFragment extends Fragment {

    Dialog mydialog;
    String reference;
    String propic;
    final  long ONE_MEGABYTE = 1024*1024;
    private DatabaseReference mDatabaseref;
   private StorageReference mIamgeref;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String P_P = "profile";
    ImageView imageButton;
    Button up;

    //for history

    RecyclerView recyclerViewHistory;
    List<History> historyList;
    int id;
    HistoryAdapterfirst historyAdapterfirst;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

       View v =  inflater.inflate(R.layout.fragment_myprofilefragment, container, false);

        imageButton = v.findViewById(R.id.profilepicture);

      SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
     propic = sharedPreferences.getString(P_P,null);

        Toast.makeText(getContext(), propic, Toast.LENGTH_SHORT).show();

        reference = "uploads/"+propic;
        Toast.makeText(getContext(), "This is Referecence"+reference, Toast.LENGTH_LONG).show();


//        Toast.makeText(getContext(), URL2, Toast.LENGTH_LONG).show();


        mIamgeref = FirebaseStorage.getInstance().getReference(reference);

        GlideApp.with(this)
                .load(mIamgeref)
                .into(imageButton);


       mydialog = new Dialog(getContext());
       mydialog.setContentView(R.layout.dialogupload);
      up =  mydialog.findViewById(R.id.buttonup);


       imageButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               mydialog.show();



           }
       });

       up.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(getContext(), UploadImage.class);
               startActivity(intent);
           }
       });



       //for history//

        id = SharedPreferencemanager.getmInstance(getContext()).getUser().getId();


        recyclerViewHistory = v.findViewById(R.id.recyclerViewHistory);

        Call<List<History>> call = RetrofitClient.getmInstance().getApi().getAllHistory(id);
        call.enqueue(new Callback<List<History>>() {
            @Override
            public void onResponse(Call<List<History>> call, Response<List<History>> response) {

                historyList = response.body();

                if (response.body() != null) {

                    historyAdapterfirst = new HistoryAdapterfirst(historyList, getContext(),fragmentCommunication3);
                    recyclerViewHistory.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerViewHistory.setAdapter(historyAdapterfirst);


                }
            }

            @Override
            public void onFailure(Call<List<History>> call, Throwable t) {

                Toast.makeText(getContext(), "Error in Connection" + t, Toast.LENGTH_SHORT).show();

            }
        });



        return v;
    }

    FragmenyCommunication3 fragmentCommunication3 = new FragmenyCommunication3() {
        @Override
        public void respond(int position, String month, String year) {

            ProfileFragmentSecond profileFragmentSecond = new ProfileFragmentSecond();
            Bundle bundle = new Bundle();
            bundle.putString("month",  month);
            bundle.putString("year", year);
            profileFragmentSecond.setArguments(bundle);
            FragmentManager manager = getFragmentManager();

            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.fragmentplace, profileFragmentSecond).commit();
        }
    };


    @Override
    public void onResume() {

        ((Dashboard) getActivity())
                .setActionBarTitle("me");
        super.onResume();
    }




    //
}
