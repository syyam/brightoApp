package com.example.syyam.jobsapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.List;

/**
 * Created by huma on 07/04/2018.
 */

public class LuxuryFinishesAdapter extends RecyclerView.Adapter<LuxuryFinishesAdapter.MyViewHolder> {

    private List<String> List;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public RelativeLayout colorCode;
        public TextView colorName;

        public MyViewHolder(View view) {
            super(view);
            colorCode = (RelativeLayout) view.findViewById(R.id.colorNumber);
            colorName = (TextView) view.findViewById(R.id.colorName);

        }
    }


    public LuxuryFinishesAdapter(Context context,List<String> invoicesList) {
        this.List = invoicesList;
        this.context=context;
    }

    @Override
    public LuxuryFinishesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items_recyclerview_luxuryfinishes, parent, false);
        return new LuxuryFinishesAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(LuxuryFinishesAdapter.MyViewHolder holder, int position) {
        String list = List.get(position);
        holder.colorCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LuxuryFinishesActivity = new Intent(context, LuxuryFinishesActivity.class);
                LuxuryFinishesActivity.putExtra("POSITION", position);
                context.startActivity(LuxuryFinishesActivity);
            }
        });

    }

    @Override
    public int getItemCount() {
        return List.size();
    }







}
