package com.example.syyam.jobsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.syyam.jobsapp.Models.Datum;

import java.util.List;

public class ColorShadesAdapter extends RecyclerView.Adapter<ColorShadesAdapter.CF_ViewHolder> {

    private Context context;
    private List<Datum> shades;

    public ColorShadesAdapter(Context context, List<Datum> shades) {
        this.context = context;
        this.shades = shades;
    }

    @NonNull
    @Override
    public CF_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.item_colorshades,parent,false);
        return new CF_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CF_ViewHolder holder, int position) {


        final Datum colors = this.shades.get(position);

        int r= colors.getColors().getR();


        holder.mTextView.setText(colors.getName());




//        final Datum colors = this.colors.get(position);

//        int R = colors.getColors().getR();
//        int G = colors.getColors().getG();
//        int B = colors.getColors().getB();


//        if (colors.getName().equals("White")){ // if background color is white text color will become black
//            holder.mTextView.setTextColor(Color.rgb(0, 0, 0));
//        }
//        holder.mLinearLayout.setBackgroundColor(Color.rgb(R, G, B));
//        holder.mTextView.setText(colors.getName());


        holder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent s= new Intent(view.getContext(), ColorShadesActivity.class);
//
//                s.putExtra("color_id", colors.getId());
//                s.putExtra("country_id",1);
//                view.getContext().startActivity(s);


            }
        });

    }

    @Override
    public int getItemCount() {
        return shades.size();
    }

    public class CF_ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;
        private LinearLayout mLinearLayout;

        public CF_ViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.colorNameBox);
            mLinearLayout = itemView.findViewById(R.id.colorBox);
        }
    }
}
