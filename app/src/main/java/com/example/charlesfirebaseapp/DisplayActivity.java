package com.example.charlesfirebaseapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DisplayActivity extends AppCompatActivity {

    //Creating the variables for the elements
    private RecyclerView mRecyclerView;

    private RecylcerViewAdapter mAdapter;

    private List<Charles> mUpload;

    private  DatabaseReference mDatabaseRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        //Calling the variables by id

        mRecyclerView = findViewById(R.id.recyclerview_id);

        //This code allignes the images on the same size so that the row are equal to the collums
        mRecyclerView.setHasFixedSize(true);

        //This code insures that the image remains the same(horizontal/ vertical)
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        //Bringine the mUploads an array
        mUpload = new ArrayList<>();

        //This code gets the reference from your Database
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("myImageUploads");
        mDatabaseRef.keepSynced(true);



        //New retrieve method
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

               for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){

                   Charles mCharles = dataSnapshot.getValue(Charles.class);

                   mUpload.add(mCharles);

               }

                //Connecting the adapter to this activity
               mAdapter = new RecylcerViewAdapter(DisplayActivity.this, mUpload);

                mRecyclerView.setAdapter(mAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(DisplayActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });








    }
}
