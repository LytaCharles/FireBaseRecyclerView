package com.example.charlesfirebaseapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecylcerViewAdapter extends RecyclerView.Adapter<RecylcerViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<Charles> mUploads;

    public RecylcerViewAdapter(Context mContext, List<Charles> mUploads) {
        this.mContext = mContext;
        this.mUploads = mUploads;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        view = layoutInflater.inflate(R.layout.cardview, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        Charles uploadBind = mUploads.get(i);

        //Loading the data from the database to the recyclerView

      //  myViewHolder.UserPhoneNumber.setText(uploadBind.getUserNumber());

        myViewHolder.UserName.setText(uploadBind.getUserName());

        Picasso.get().load(uploadBind.getUserImage()).fit().centerCrop().into(myViewHolder.UserImage);

    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView UserName;
      //  public TextView UserPhoneNumber;
        public ImageView UserImage;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            UserName = (itemView).findViewById(R.id.myUserName_id);
           // UserPhoneNumber = (itemView).findViewById(R.id.UserPhoneNumber_id);
            UserImage = (itemView).findViewById(R.id.myUserImage_id);


        }
    }


}
