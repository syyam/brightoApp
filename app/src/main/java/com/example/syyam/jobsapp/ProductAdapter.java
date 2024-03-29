package com.example.syyam.jobsapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.syyam.jobsapp.Models.ProductsDatum;
import com.example.syyam.jobsapp.Utils.Config;
import com.google.gson.Gson;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.CF_ViewHolder> {

    private Context context;
    private List<ProductsDatum> colors;
    int cid;
    boolean DetailPage;
    ProductTypesAdapter.AdapterCallback callback;


    public ProductAdapter(Context context, List<ProductsDatum> colors, int cid, boolean detailPage, ProductTypesAdapter.AdapterCallback callback) {
        this.context = context;
        this.colors = colors;
        this.cid = cid;
        this.DetailPage = detailPage;
        this.callback = callback;
    }

    @NonNull
    @Override
    public CF_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_product, parent, false);
        return new CF_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CF_ViewHolder holder, final int position) {


        final ProductsDatum colors = this.colors.get(position);
        String topText = colors.getName().toString();
        String bottomText = colors.getDescription().toString();

        final int pid= colors.getId();

//        SharedPreferences prefs = holder.mContext.getSharedPreferences("Country", holder.mContext.MODE_PRIVATE);
//        CID =prefs.getInt("countryId",0); //0 is the default value.


        String imgurl= Config.IMAGE_URL + colors.getImage();

        holder.topTextView.setText(topText);
        holder.bottomTextView.setText(bottomText);
        Glide.with(context).load(imgurl).into(holder.imageView);


        if(DetailPage) {
            holder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent s = new Intent(view.getContext(), ProductDetails.class);
                    s.putExtra("product_id", pid + "");
                    view.getContext().startActivity(s);
                }
            });
        }
        else {
            holder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (callback != null) {
                        callback.onItemClicked(position, colors.getId(), "first");
                    }
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return colors.size();
    }

    public class CF_ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout mLinearLayout;
        private TextView topTextView;
        private TextView bottomTextView;
        private ImageView imageView;


        public CF_ViewHolder(View itemView) {
            super(itemView);
            mLinearLayout = itemView.findViewById(R.id.productLL);
            topTextView = itemView.findViewById(R.id.topTextView);
            bottomTextView = itemView.findViewById(R.id.bottomTextView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
