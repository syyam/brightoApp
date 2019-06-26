package com.example.syyam.jobsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.syyam.jobsapp.Models.FurnishFinish;
import com.example.syyam.jobsapp.Models.LikedProductsDatum;
import com.example.syyam.jobsapp.Utils.Config;

import java.util.List;

public class PalleteFavAdapter extends RecyclerView.Adapter<PalleteFavAdapter.CF_ViewHolder> {
    private Context context;
    private List<LikedProductsDatum> shades;



    public PalleteFavAdapter(Context context, List<LikedProductsDatum> shades) {
        this.context = context;
        this.shades = shades;
    }

    @NonNull
    @Override
    public PalleteFavAdapter.CF_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_pallete_fav, parent, false);
        return new PalleteFavAdapter.CF_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PalleteFavAdapter.CF_ViewHolder holder, final int position) {


        final LikedProductsDatum countries = this.shades.get(position);


        String name = countries.getName().toString();
        holder.name.setText(name);


        String imgurl = Config.IMAGE_URL + countries.getImage();
        Glide.with(context).load(imgurl).into(holder.loveImg);
        holder.LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent s = new Intent(v.getContext(), ProductDetails.class);
//                s.putExtra("product_id", pid + "");
//                v.getContext().startActivity(s);

            }
        });



    }


    @Override
    public int getItemCount() {
        return shades.size();
    }

    public class CF_ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private ImageView loveImg;
        private LinearLayout LL;


        public CF_ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.title);
            loveImg = itemView.findViewById(R.id.favImage);
            LL = itemView.findViewById(R.id.likedProductLL);


        }
    }
}
