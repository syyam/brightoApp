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
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.syyam.jobsapp.Models.CountryDatum;
import com.example.syyam.jobsapp.Models.Datum;
import com.example.syyam.jobsapp.Models.DesignerPalettesModel.DP_Datum;
import com.example.syyam.jobsapp.Models.DesignerPalettesModel.DesignerPalettes;
import com.example.syyam.jobsapp.Models.Like;
import com.example.syyam.jobsapp.Models.params.PaletteParam;
import com.example.syyam.jobsapp.Models.params.ProductParam;
import com.example.syyam.jobsapp.Utils.Config;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;

public class DesignerPalettesAdapter extends RecyclerView.Adapter<DesignerPalettesAdapter.CF_ViewHolder> {

    private AdapterCallback mAdapterCallback;
    AdapterCallback callback;

    private Context context;
    private List<DP_Datum> shades;
    private int lastSelectedPosition = 0;

    public static interface AdapterCallback {
        void onItemClicked(int position, Integer id, Boolean loved);
    }

    public DesignerPalettesAdapter(Context context, List<DP_Datum> shades, AdapterCallback callback) {
        this.context = context;
        this.shades = shades;
        this.callback = callback;

    }

    @NonNull
    @Override
    public CF_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_designer_palettes, parent, false);
        return new CF_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CF_ViewHolder holder, final int position) {


        final DP_Datum palettes = this.shades.get(position);

        final String name = palettes.getName();

        final int r1 = palettes.getColor1().getColor().getR();
        final int g1 = palettes.getColor1().getColor().getG();
        final int b1 = palettes.getColor1().getColor().getB();

        int r2 = palettes.getColor2().getColor().getR();
        int g2 = palettes.getColor2().getColor().getG();
        int b2 = palettes.getColor2().getColor().getB();

        int r3 = palettes.getColor3().getColor().getR();
        int g3 = palettes.getColor3().getColor().getG();
        int b3 = palettes.getColor3().getColor().getB();

        holder.pallteby.setText(palettes.getPalleteBy());
        holder.name.setText(name);
        holder.likes.setText(palettes.getLikes() + " Likes");

        holder.C1.setBackgroundColor(Color.rgb(r1, g1, b1));
        holder.C2.setBackgroundColor(Color.rgb(r2, g3, b3));
        holder.C3.setBackgroundColor(Color.rgb(r3, g3, b3));

        holder.PID = palettes.getId();


        final String hex1 = String.format("#%02x%02x%02x", r1, g1, b1);
        final String hex2 = String.format("#%02x%02x%02x", r2, g2, b2);
        final String hex3 = String.format("#%02x%02x%02x", r3, g3, b3);


        holder.LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Like = "UnLike";
                if (holder.loved == true) {
                    Like = "Like";
                } else
                    Like = "UnLike";

                Intent l = new Intent(context, DesignerPaletteDetails.class);
                l.putExtra("hex1", hex1);
                l.putExtra("hex2", hex2);
                l.putExtra("hex3", hex3);
                l.putExtra("name", name);
                l.putExtra("likes", palettes.getLikes().toString());
                l.putExtra("Like", Like);

                context.startActivity(l);


            }
        });


        //TODO: set the inital Like/UnLike icon from API
        holder.fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback != null) {
                    if (!holder.loved) {
                        callback.onItemClicked(position, palettes.getId(), holder.loved);
                        holder.loved = true;
                        holder.fav.setImageResource(context.getResources().getIdentifier(R.mipmap.favorite_selected + "", "drawable", context.getPackageName()));
                    } else {
                        callback.onItemClicked(position, palettes.getId(), holder.loved);
                        holder.loved = false;
                        holder.fav.setImageResource(context.getResources().getIdentifier(R.mipmap.top_favourite + "", "drawable", context.getPackageName()));
                    }
                }
            }
        });


    }

    private void loveCall(String sender, int p_id) {
        Retrofit build = new Retrofit
                .Builder()
                .baseUrl(Config.BASE_URL_AUTH)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API retrofitController = build.create(API.class);

        PaletteParam paletteParam = new PaletteParam();
        paletteParam.setPallete_id(p_id);

        Call<Like> productsCall = null;
        if (sender.equals("loved")) {
            productsCall = retrofitController.getLikePalettes(Config.getToken(context), paletteParam);
        }
        if (sender.equals("not_loved")) {
            productsCall = retrofitController.getUnLikePalettes(Config.getToken(context), paletteParam);
        }


        //Config.getToken(getContext())

        productsCall.enqueue(new Callback<Like>() {
            @Override
            public void onResponse(Call<Like> call, Response<Like> response) {

                if (response != null) {
                    Like list = response.body();

                    if (list != null) {
                        Toast.makeText(context, list.getMessage(), Toast.LENGTH_LONG).show();

                        if (list.getMessage() == null) {
                            Toast.makeText(context, list.getErrors(), Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(context, "An error occurred", Toast.LENGTH_LONG).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<Like> call, Throwable t) {
                Toast.makeText(context, "Failure: " + t, Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return shades.size();
    }

    public class CF_ViewHolder extends RecyclerView.ViewHolder {
        private TextView name, likes;
        private LinearLayout LL;
        private View C1, C2, C3;
        private ImageView fav;
        private TextView pallteby;
        private boolean loved = false;
        private int PID;


        //SharedPreferences.Editor editor = context.getSharedPreferences("Country", Context.MODE_PRIVATE).edit();


        public CF_ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            likes = itemView.findViewById(R.id.likes);

            LL = itemView.findViewById(R.id.LL);

            C1 = itemView.findViewById(R.id.color1);
            C2 = itemView.findViewById(R.id.color2);
            C3 = itemView.findViewById(R.id.color3);

            fav = itemView.findViewById(R.id.fav);
            pallteby = itemView.findViewById(R.id.plateby);


//            fav.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (!loved) {
//                        String token;
//                        SharedPreferences prefs = context.getSharedPreferences(Config.MY_PREFS_NAME, MODE_PRIVATE);
//                        token = prefs.getString("token", null);
//
//                        if (token == null) { //if user is signed in only then make him like the product
//                            Toast.makeText(context, "You need to sign in.", Toast.LENGTH_SHORT).show();
//
//                        } else {
//                            fav.setImageResource(context.getResources().getIdentifier(R.mipmap.favorite_selected + "", "drawable", context.getPackageName()));
//                            loved = true;
//                            loveCall("loved", PID);
//                        }
//
//
//                    }
//                    if (loved) {
//                        fav.setImageResource(R.mipmap.top_favourite);
//                        loved = false;
//                        loveCall("not_loved", PID);
//                    }
//                }
//            });

        }
    }
}
