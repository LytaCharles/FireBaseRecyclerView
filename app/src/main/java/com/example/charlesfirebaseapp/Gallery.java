package com.example.charlesfirebaseapp;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

public class Gallery extends AppCompatActivity {


    private static final int PICK_IMAGE_REQUEST = 1;

    private Uri mImageUri;

    private Button ChooseProfileImageButton;

    private  ImageView myChooseImage;



    //Database and Storage Refference

    private StorageReference mStorageRef;
    private DatabaseReference mDatabadeRef;
    private StorageTask  mStoragetask;

    private Button mUploadButton;

    private EditText mUserName;

    private EditText mPhonenumber;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        myChooseImage = (ImageView)findViewById(R.id.ProfileImage_id);

        ChooseProfileImageButton = (Button) findViewById(R.id.MyfirstButton);

        mUserName = (EditText) findViewById(R.id.entername_id);

        mPhonenumber = (EditText)findViewById(R.id.EnterPhoneNumber_id);


        mDatabadeRef = FirebaseDatabase.getInstance().getReference("myImageUploads");

        mStorageRef = FirebaseStorage.getInstance().getReference("myStoredImages");

        mUploadButton = (Button)findViewById(R.id.MySecondButton);



        mUploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FileUpload();

            }
        });

        ChooseProfileImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChooseImage();


            }
        });
    }

    private void ChooseImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){

            mImageUri = data.getData();

            myChooseImage.setImageURI(mImageUri);
        }
    }
    //The method will assign to the image whether its a png or jpeg

    private  String getFileExtension(Uri uri){

        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(mImageUri));
    }


    private void FileUpload() {


        if (mImageUri != null) {

            final StorageReference fileReference = mStorageRef.child(System.currentTimeMillis() + "." + getFileExtension(mImageUri));

            mStoragetask = fileReference.putFile(mImageUri);
            mStoragetask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }
                    return fileReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {

                        // String downloadUri = task.getResult().toString();
                        // UploadTask submit = new Upload(
                        // mUserName.getText().toString().trim(),
                        // mPhoneNumber.getText().toString().trim(),
                        // downloadUri);

                        // String uploadId = mDatabaseRef.push().getKey();
                        // mDatabaseRef.child(uploadId).setValue(submit);

                        Toast.makeText(Gallery.this, "Upload success", Toast.LENGTH_SHORT).show();

                        //Converting the Image Uri into String

                        String downloadUri = task.getResult().toString();

                        //sending data to firebase

                        Charles submit = new Charles(mUserName.getText().toString().trim(), downloadUri);

                        String UploadID = mDatabadeRef.push().getKey();
                        mDatabadeRef.child(UploadID).setValue(submit);


                    } else {
                        // Handle failures
                        Log.e("Upload","Upload failed");
                    }
                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(Gallery.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });

        } else {

            Toast.makeText(this, "Other details not Provided", Toast.LENGTH_SHORT).show();
        }

    }

}


