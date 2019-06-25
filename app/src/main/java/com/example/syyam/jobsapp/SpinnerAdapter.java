package com.example.syyam.jobsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class SpinnerAdapter  extends ArrayAdapter<String> {

    private int hidingItemIndex;

    SpinnerAdapter(Context context, int textViewResourceId, List<String> objects, int hidingItemIndex) {
        super(context, textViewResourceId, objects);
        this.hidingItemIndex = hidingItemIndex;
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        View v = null;
        if (position == hidingItemIndex) {
            TextView tv = new TextView(getContext());
            tv.setHeight(0);
            tv.setVisibility(View.GONE);
            v = tv;
        } else {
            v = super.getDropDownView(position, null, parent);
        }
        return v;
    }
}