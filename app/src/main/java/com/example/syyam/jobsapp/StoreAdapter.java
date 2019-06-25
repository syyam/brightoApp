package com.example.syyam.jobsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.syyam.jobsapp.Fragments.StoreFragment;
import com.example.syyam.jobsapp.Models.CountryDatum;

import java.util.List;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.CF_ViewHolder> {


    private AdapterCallback mAdapterCallback;


    private Context context;
    private List<CountryDatum> shades;
    private int lastSelectedPosition = -1;
    AdapterCallback callback;


    public static interface AdapterCallback {
        void onItemClicked(int position, Integer id, String name, String Sender);
    }


    public StoreAdapter(Context context, List<CountryDatum> shades, AdapterCallback callback) {
        this.context = context;
        this.shades = shades;
        this.callback = callback;

    }

    @NonNull
    @Override
    public CF_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_store, parent, false);
        return new CF_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CF_ViewHolder holder, final int position) {


        final CountryDatum countries = this.shades.get(position);

        String name = countries.getName().toString();

        holder.c_name.setText(name);

        holder.countrycityLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(callback != null) {
                    callback.onItemClicked(position, countries.getId(), countries.getName(), "first");
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return shades.size();
    }

    public class CF_ViewHolder extends RecyclerView.ViewHolder {
        private TextView c_name;
        private LinearLayout countrycityLL;


        public CF_ViewHolder(View itemView) {
            super(itemView);
            c_name = itemView.findViewById(R.id.c_name);
            countrycityLL = itemView.findViewById(R.id.countrycityLL);

        }
    }


}

//https://www.zoftino.com/android-recyclerview-radiobutton