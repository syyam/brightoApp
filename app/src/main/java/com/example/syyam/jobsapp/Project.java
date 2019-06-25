package com.example.syyam.jobsapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

public class Project {

    private int ProjectID;
    private int ShadeID;
    private int DealerID;
    private boolean isDealerSet;
    private int index1;
    private int index2;
    private String textViewText;
    private Drawable imageView1;
    private Drawable imageView2;
    private String imageTextView3;

    Project(Context context, int index1, int index2) {
        this.ProjectID = -1;
        this.ShadeID = -1;
        this.index1 = index1;
        this.index2 = index2;
        this.imageView1 = ContextCompat.getDrawable(context.getApplicationContext(), R.mipmap.pf_interior);
        this.imageView2 = ContextCompat.getDrawable(context.getApplicationContext(), R.mipmap.pf_finish);
    }

    Project(Context context, String textViewText, int index1, int index2) {
        this.ProjectID = -1;
        this.ShadeID = -1;
        isDealerSet = false;
        this.index1 = index1;
        this.index2 = index2;
        this.textViewText = textViewText;
        this.imageView1 = ContextCompat.getDrawable(context.getApplicationContext(), R.mipmap.pf_interior);
        this.imageView2 = ContextCompat.getDrawable(context.getApplicationContext(), R.mipmap.pf_finish);
        this.imageTextView3 = "Assign Dealer";
    }

    public int getShadeID() {
        return ShadeID;
    }

    public void setShadeID(int shadeID) {
        ShadeID = shadeID;
    }

    public int getDealerID() {
        return DealerID;
    }

    public void setDealerID(int dealerID) {
        DealerID = dealerID;
    }

    public boolean isDealerSet() {
        return isDealerSet;
    }

    public void setDealerSet(boolean dealerSet) {
        isDealerSet = dealerSet;
    }

    public int getIndex1() {
        return index1;
    }

    public void setIndex1(int index1) {
        this.index1 = index1;
    }

    public int getIndex2() {
        return index2;
    }

    public void setIndex2(int index2) {
        this.index2 = index2;
    }

    public int getProjectID() {
        return ProjectID;
    }

    public void setProjectID(int projectID) {
        ProjectID = projectID;
    }

    String getTextViewText() {
        return textViewText;
    }

    void setTextViewText(String textViewText) {
        this.textViewText = textViewText;
    }

    Drawable getImageView1() {
        return imageView1;
    }

    void setImageView1(Drawable imageView1) {
        this.imageView1 = imageView1;
    }

    Drawable getImageView2() {
        return imageView2;
    }

    void setImageView2(Drawable imageView2) {
        this.imageView2 = imageView2;
    }

    String getImageTextView3() {
        return imageTextView3;
    }

    void setImageTextView3(String imageTextView3) {
        this.imageTextView3 = imageTextView3;
    }
}
