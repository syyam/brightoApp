package com.example.syyam.jobsapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.syyam.jobsapp.Models.CountryDatum;
import com.example.syyam.jobsapp.Models.Datum;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CF_ViewHolder> {

    private Context context;
    private List<CountryDatum> shades;
    private int lastSelectedPosition = 0; // to start selectless make it -1


    public CountryAdapter(Context context, List<CountryDatum> shades) {
        this.context = context;
        this.shades = shades;
    }


    @NonNull
    @Override
    public CF_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_country, parent, false);
        return new CF_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CF_ViewHolder holder, final int position) {


        final CountryDatum countries = this.shades.get(position);

        final List<CountryDatum> country = this.shades;

        String name = countries.getName().toString();


        final int[] send_id = {this.shades.get(lastSelectedPosition).getId()};


        holder.mTextView.setText(name);

        holder.editor.putInt("countryId", country.get(0).getId()); //default 1st id will be sent if no field is selected
        holder.editor.apply();


        //TODO: radio button should be fixed
        holder.radioButton.setChecked(lastSelectedPosition == position);

        holder.LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastSelectedPosition = holder.getAdapterPosition();
                notifyDataSetChanged();


                holder.editor.putInt("countryId", country.get(lastSelectedPosition).getId()); // add +1 if start from -1
                holder.editor.apply();


            }
        });

        holder.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastSelectedPosition = holder.getAdapterPosition();
                notifyDataSetChanged();

                holder.editor.putInt("countryId", country.get(lastSelectedPosition).getId());
                holder.editor.apply();

            }
        });


    }


    @Override
    public int getItemCount() {
        return shades.size();
    }

    public class CF_ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;
        private RadioButton radioButton;
        private RadioGroup radioGroup;
        private int id = 4;
        private LinearLayout LL;


        SharedPreferences.Editor editor = context.getSharedPreferences("Country", Context.MODE_PRIVATE).edit();


        public CF_ViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.countryName);
            radioButton = itemView.findViewById(R.id.radio_btn);
            //radioGroup = itemView.findViewById(R.id.radioGroup1);
            LL = itemView.findViewById(R.id.LL);


//            LL.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    lastSelectedPosition = getAdapterPosition();
//                    notifyDataSetChanged();
//
//                    editor.putInt("countryId", id);
//                    editor.apply();
//
//                }
//            });
//
//            radioButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    lastSelectedPosition = getAdapterPosition();
//                    notifyDataSetChanged();
//
//                    editor.putInt("countryId", id);
//                    editor.apply();
//
//                }
//            });
        }
    }
}

//https://www.zoftino.com/android-recyclerview-radiobutton