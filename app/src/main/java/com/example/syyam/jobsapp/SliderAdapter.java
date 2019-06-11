package com.example.syyam.jobsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.syyam.jobsapp.R;

public class SliderAdapter extends PagerAdapter
{

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }


    public int[] slide_images ={
            R.mipmap.slide_colormatch,
            R.mipmap.slide_colorpallets,
            R.mipmap.slide_visualize,
            R.mipmap.slide_calculate
    };

    public String[] slide_text={
            "COLOR MATCH",
            "DESIGNER \n COLOR PALLETS",
            "VISUALIZE \n BEFORE ORDER",
            "CALCULATE"
    };

    @Override
    public int getCount() {
        return slide_text.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==(RelativeLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater =(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view =layoutInflater.inflate(R.layout.slide2_layout,container,false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.slideImage);
        TextView slideTextView = (TextView) view.findViewById(R.id.slideTest);

        slideImageView.setImageResource(slide_images[position]);
        slideTextView.setText(slide_text[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }
}
