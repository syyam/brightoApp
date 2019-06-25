package com.example.syyam.jobsapp;

import android.content.Context;
import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.syyam.jobsapp.Fragments.ColorFinderFragment;
import com.example.syyam.jobsapp.Fragments.ProductFragment;
import com.example.syyam.jobsapp.Models.Datum;
import com.example.syyam.jobsapp.Models.ProductTypeDatum;
import com.example.syyam.jobsapp.Models.ProductsDatum;
import com.example.syyam.jobsapp.Utils.Config;

import java.util.List;

public class ProductTypesAdapter extends RecyclerView.Adapter<ProductTypesAdapter.CF_ViewHolder> {

    private AdapterCallback mAdapterCallback;

    private ProductTypes context;
    private List<ProductTypeDatum> colors;
    private int lastSelectedPosition = -1;
    AdapterCallback callback;


    public static interface AdapterCallback {
        void onItemClicked(int position, Integer id, String Sender);
    }

    public ProductTypesAdapter(ProductTypes context, List<ProductTypeDatum> colors, AdapterCallback callback) {
        this.context = context;
        this.colors = colors;
        this.callback = callback;

    }

    @NonNull
    @Override
    public CF_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_product_types, parent, false);
        return new CF_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CF_ViewHolder holder, final int position) {


        final ProductTypeDatum colors = this.colors.get(position);

        String topText = colors.getName().toString();


//        SharedPreferences prefs = holder.mContext.getSharedPreferences("Country", holder.mContext.MODE_PRIVATE);
//        CID =prefs.getInt("countryId",0); //0 is the default value.


        String imgurl = Config.IMAGE_URL + colors.getImage();

        holder.topTextView.setText(topText);
        Glide.with(context).load(imgurl).into(holder.imageView);

        holder.radioButton.setChecked(lastSelectedPosition == position);


//        holder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if (callback != null) {
//                    callback.onItemClicked(position, colors.getId(), "first");
//                }
//
//
//            }
//        });

        holder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (callback != null) {
                    callback.onItemClicked(position, colors.getId(), "first");
                }
            }
        });
        holder.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (callback != null) {
                    callback.onItemClicked(position, colors.getId(), "first");
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return colors.size();
    }


    public class CF_ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout mLinearLayout;
        private TextView topTextView;
        private ImageView imageView;
        private RadioButton radioButton;
        private int id;


        public CF_ViewHolder(View itemView) {
            super(itemView);
            mLinearLayout = itemView.findViewById(R.id.productTypesLL);
            topTextView = itemView.findViewById(R.id.topTextView);
            imageView = itemView.findViewById(R.id.imageView);
            radioButton = itemView.findViewById(R.id.radio_btn);


//            mLinearLayout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    lastSelectedPosition = getAdapterPosition();
//                    notifyDataSetChanged();
//
//                }
//            });

            radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lastSelectedPosition = getAdapterPosition();
                    notifyDataSetChanged();

//                    editor.putInt("countryId", id);
//                    editor.apply();

                }
            });
        }

    }
}
