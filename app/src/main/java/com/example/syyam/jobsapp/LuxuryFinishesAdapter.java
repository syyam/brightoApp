package com.example.syyam.jobsapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.syyam.jobsapp.Models.FurnishFinish;
import com.example.syyam.jobsapp.Utils.Config;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by huma on 07/04/2018.
 */

public class LuxuryFinishesAdapter extends RecyclerView.Adapter<LuxuryFinishesAdapter.MyViewHolder> {

    List<FurnishFinish.Datum> list;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView colorName;
        ImageView image;

        public MyViewHolder(View view) {
            super(view);


            colorName = (TextView) view.findViewById(R.id.colorName);
            image = view.findViewById(R.id.image);

        }
    }


    public LuxuryFinishesAdapter(Context context, java.util.List<FurnishFinish.Datum> invoicesList) {
        list = invoicesList;
        this.context = context;
    }

    @Override
    public LuxuryFinishesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items_recyclerview_luxuryfinishes, parent, false);
        return new LuxuryFinishesAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(LuxuryFinishesAdapter.MyViewHolder holder, int position) {
        FurnishFinish.Datum listt = list.get(position);
        holder.colorName.setText(listt.getName());
        String imgurl = Config.IMAGE_URL + listt.getCoverImage();
        Glide.with(context).load(imgurl).into(holder.image);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LuxuryFinishesActivity = new Intent(context, LuxuryFinishesActivity.class);
                Gson gson=new Gson();
                String data=gson.toJson(listt);
                LuxuryFinishesActivity.putExtra("data",data);
                context.startActivity(LuxuryFinishesActivity);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
