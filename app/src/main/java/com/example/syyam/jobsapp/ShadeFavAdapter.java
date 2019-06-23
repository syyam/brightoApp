package com.example.syyam.jobsapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.syyam.jobsapp.Fragments.ProductFavFragment;
import com.example.syyam.jobsapp.Fragments.ShadeFavFragment;
import com.example.syyam.jobsapp.Models.LikedProductsDatum;
import com.example.syyam.jobsapp.Models.LikedShadesDatum;
import com.example.syyam.jobsapp.Utils.Config;

import java.util.List;

public class ShadeFavAdapter extends RecyclerView.Adapter<ShadeFavAdapter.CF_ViewHolder> {
    private ShadeFavFragment context;
    private List<LikedShadesDatum> shades;


    public ShadeFavAdapter(ShadeFavFragment context, List<LikedShadesDatum> shades) {
        this.context = context;
        this.shades = shades;
    }

    @NonNull
    @Override
    public ShadeFavAdapter.CF_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_shade_fav, parent, false);
        return new ShadeFavAdapter.CF_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ShadeFavAdapter.CF_ViewHolder holder, final int position) {


        final LikedShadesDatum countries = this.shades.get(position);


        String name = countries.getName().toString();
        String type = countries.getDescription().toString();

        holder.name.setText(name);
        holder.type.setText(type);


        final int R = countries.getColor().getR();
        final int G = countries.getColor().getG();
        final int B = countries.getColor().getB();

        holder.LL.setBackgroundColor(Color.rgb(R, G, B));


        holder.LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s = new Intent(v.getContext(), SpecificShade.class);

                s.putExtra("sid", countries.getId().toString());
                s.putExtra("R", countries.getColor().getR().toString());
                s.putExtra("G", countries.getColor().getG().toString());
                s.putExtra("B", countries.getColor().getB().toString());
                s.putExtra("name", countries.getName());
                s.putExtra("desc", countries.getDescription());
                s.putExtra("itemCode", countries.getItemCode());
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

            LL = itemView.findViewById(R.id.likedProductLL);


        }
    }
}
