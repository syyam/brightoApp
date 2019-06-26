package com.example.syyam.jobsapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.syyam.jobsapp.Models.ShadesFamilyDatum;

import java.util.List;

public class ColorShadesAdapter extends RecyclerView.Adapter<ColorShadesAdapter.CF_ViewHolder> {

    private Context context;
    private List<ShadesFamilyDatum> shades;
    ProductTypesAdapter.AdapterCallback callback;

    public ColorShadesAdapter(Context context, List<ShadesFamilyDatum> shades, ProductTypesAdapter.AdapterCallback callback) {
        this.context = context;
        this.shades = shades;
        this.callback = callback;
    }

    @NonNull
    @Override
    public CF_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_colorshades, parent, false);
        return new CF_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CF_ViewHolder holder, final int position) {


        final ShadesFamilyDatum colors = this.shades.get(position);

        final int R = colors.getColor().getR();
        final int G = colors.getColor().getG();
        final int B = colors.getColor().getB();


//        final Datum colors = this.colors.get(position);


        if (colors.getName().equals("White"))  // if background color is white text color will become black
            holder.mTextView.setTextColor(Color.rgb(0, 0, 0));
        holder.mTextView.setText(colors.getName());
        holder.mCardView.setBackgroundColor(Color.rgb(R, G, B));

        if (callback == null) {
            holder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent s = new Intent(view.getContext(), SpecificShade.class);
                    int id = colors.getProducts().get(0).getId();
                    s.putExtra("sid", colors.getId().toString());
                    s.putExtra("R", colors.getColor().getR().toString());
                    s.putExtra("G", colors.getColor().getG().toString());
                    s.putExtra("B", colors.getColor().getB().toString());
                    s.putExtra("name", colors.getName());
                    s.putExtra("desc", colors.getDescription());
                    s.putExtra("itemCode", colors.getItemCode());
                    s.putExtra("pname", colors.getProducts().get(0).getName());
                    s.putExtra("pid", id + "");

                    view.getContext().startActivity(s);
                }
            });
        } else {
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
        return shades.size();
    }

    public class CF_ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;
        private LinearLayout mLinearLayout;
        private CardView mCardView;

        public CF_ViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.colorNameBox);
            mLinearLayout = itemView.findViewById(R.id.colorBox);
            mCardView = itemView.findViewById(R.id.cardView);

        }
    }
}
