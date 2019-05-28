package com.example.projectairline.Main;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectairline.Datamodel.User;
import com.example.projectairline.Fragment.MyscheduleFragSecond;
import com.example.projectairline.Fragment.ProfileFragment;
import com.example.projectairline.R;
import com.example.projectairline.Upload;
import com.example.projectairline.Utilities.Fragcommunication2;
import com.example.projectairline.Utilities.FragmentCommunication;
import com.example.projectairline.Utilities.SharedPreferencemanager;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DatabaseRegistrar;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.net.URI;

public class UploadImage extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView imageViewchosedimage;
    private Button uploadbutton,chooseimagebutton;
    private EditText filenameet;
    private ProgressBar progressBarupload;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String P_P = "profile";
    private Uri mImageuri;
    String filename,filenamekept;



    private StorageReference mstorageref;
    private DatabaseReference mDatabaseref;
    private StorageTask muploadtask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);



        imageViewchosedimage = findViewById(R.id.imageViewchosenimage);
        uploadbutton = findViewById(R.id.upload);
        chooseimagebutton = findViewById(R.id.choosefile);
        filenameet = findViewById(R.id.filename);
        progressBarupload = findViewById(R.id.progressBar);

        mstorageref = FirebaseStorage.getInstance().getReference("uploads");
        mDatabaseref = FirebaseDatabase.getInstance().getReference("uploads");

        chooseimagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                openfilechooser();

            }
        });

        uploadbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (muploadtask !=null && muploadtask.isInProgress()){
                    Toast.makeText(UploadImage.this, "Upload In progress", Toast.LENGTH_SHORT).show();

                }
                else {
                    uploadfile();
                }
                
            }
        });


    }


    private String getFileextension(Uri uri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }
    private void uploadfile() {

        if (mImageuri!= null){

            filenamekept = filenameet.getText().toString();

            StorageReference filereference  = mstorageref.child(filenamekept+
                    "."+getFileextension(mImageuri));
            filename = filenamekept+
                    "."+getFileextension(mImageuri);
           muploadtask = filereference.putFile(mImageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        progressBarupload.setProgress(0);

                    }
                },5000);

                    Toast.makeText(UploadImage.this, "Upload Sucessful", Toast.LENGTH_SHORT).show();
                    Upload upload = new Upload(filenameet.getText().toString().trim(),
                            taskSnapshot.getStorage().getDownloadUrl().toString());

                    String uploadid = mDatabaseref.push().getKey();
                    mDatabaseref.child(uploadid).setValue(upload);


                filenameet.setText(filename);

                    imageViewchosedimage.setImageResource(0);

                    SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString(P_P,filename);
                    editor.apply();
                    editor.commit();

                    Toast.makeText(UploadImage.this, "Profile Picture Changed"+sharedPreferences.getString("profilepic",null), Toast.LENGTH_SHORT).show();










                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(UploadImage.this, e.getMessage(), Toast.LENGTH_SHORT).show();



                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                    double progress = (100.0*taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                    progressBarupload.setProgress((int) progress);

                }
            });
        } else {
            Toast.makeText(this, "No image selected", Toast.LENGTH_SHORT).show();

        }

    }


    private void openfilechooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data !=null && data.getData() !=null){

            mImageuri = data.getData();

            Picasso.get().load(mImageuri).into(imageViewchosedimage);

        }
    }

//    Fragcommunication2 fragcommunication2 = new Fragcommunication2() {
//        @Override
//        public void openfragment(String filename) {
//
//
//            ProfileFragment fragmentprofile = new ProfileFragment();
//            Bundle bundle = new Bundle();
//            bundle.putString("filename",filename);
//
//
//
//            fragmentprofile.setArguments(bundle);
//            FragmentManager manager = getSupportFragmentManager();
//            FragmentTransaction transaction=manager.beginTransaction();
//            transaction.replace(R.id.fragmentplace,fragmentprofile).commit();
//
//        }
//    };







}
