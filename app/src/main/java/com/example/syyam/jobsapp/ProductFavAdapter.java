package com.example.syyam.jobsapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.bumptech.glide.Glide;
import com.example.syyam.jobsapp.Fragments.ProductFavFragment;
import com.example.syyam.jobsapp.Models.CountryDatum;
import com.example.syyam.jobsapp.Models.LikedProductsDatum;
import com.example.syyam.jobsapp.Models.ProductsDatum;
import com.example.syyam.jobsapp.Utils.Config;

import java.util.List;

public class ProductFavAdapter extends RecyclerView.Adapter<ProductFavAdapter.CF_ViewHolder> {
    private Context context;
    private List<LikedProductsDatum> shades;
    private int lastSelectedPosition = 0;


    public ProductFavAdapter(Context context, List<LikedProductsDatum> shades) {
        this.context = context;
        this.shades = shades;
    }

    @NonNull
    @Override
    public ProductFavAdapter.CF_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_product_fav, parent, false);
        return new ProductFavAdapter.CF_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductFavAdapter.CF_ViewHolder holder, final int position) {


        final LikedProductsDatum countries = this.shades.get(position);


        String name = countries.getName().toString();
        String type = countries.getDescription().toString();
        final int pid = countries.getId();

        holder.name.setText(name);
        holder.type.setText(type);

        String imgurl = Config.IMAGE_URL + countries.getImage();
        Glide.with(context).load(imgurl).into(holder.imagePF);


        holder.LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s = new Intent(v.getContext(), ProductDetails.class);

                s.putExtra("product_id", pid + "");

                v.getContext().startActivity(s);

            }
        });
//
//        holder.radioButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                lastSelectedPosition = holder.getAdapterPosition();
//                notifyDataSetChanged();
//
//
//
//            }
//        });


    }


    @Override
    public int getItemCount() {
        return shades.size();
    }

    public class CF_ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView type;

        private ImageView loveImg;
        private ImageView imagePF;
        private LinearLayout LL;


        public CF_ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            type = itemView.findViewById(R.id.type);
            loveImg = itemView.findViewById(R.id.loveImg);
            imagePF = itemView.findViewById(R.id.imagePF);

            LL = itemView.findViewById(R.id.likedProductLL);


        }
    }
}
