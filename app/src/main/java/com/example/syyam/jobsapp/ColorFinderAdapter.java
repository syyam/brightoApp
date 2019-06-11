package com.example.syyam.jobsapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.syyam.jobsapp.Fragments.ColorFinderFragment;
import com.example.syyam.jobsapp.Models.Datum;

import java.util.List;

public class ColorFinderAdapter extends RecyclerView.Adapter<ColorFinderAdapter.CF_ViewHolder> {

    private ColorFinderFragment context;
    private List<Datum> colors;

    public ColorFinderAdapter(ColorFinderFragment context, List<Datum> colors) {
        this.context = context;
        this.colors = colors;
    }

    @NonNull
    @Override
    public CF_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.item_colorfinder,parent,false);
        return new CF_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CF_ViewHolder holder, int position) {

        final Datum colors = this.colors.get(position);

        int R = colors.getColors().getR();
        int G = colors.getColors().getG();
        int B = colors.getColors().getB();


        if (colors.getName().equals("White")){ // if background color is white text color will become black
            holder.mTextView.setTextColor(Color.rgb(0, 0, 0));
        }
        holder.mLinearLayout.setBackgroundColor(Color.rgb(R, G, B));
        holder.mTextView.setText(colors.getName());


        holder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent s= new Intent(view.getContext(), ColorShadesActivity.class);

                int a=colors.getId();
                s.putExtra("color_id", colors.getId().toString());
                //TODO: make country id dynamic when you make ChoooseCountryActivity
                s.putExtra("country_id","2");

                view.getContext().startActivity(s);


            }
        });

    }

    @Override
    public int getItemCount() {
        return colors.size();
    }

    public class CF_ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;
        private LinearLayout mLinearLayout;

        public CF_ViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.colorNameRow);
            mLinearLayout = itemView.findViewById(R.id.colorRow);
        }
    }
}