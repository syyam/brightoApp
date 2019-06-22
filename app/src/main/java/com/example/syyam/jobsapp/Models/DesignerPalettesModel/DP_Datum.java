package com.example.syyam.jobsapp.Models.DesignerPalettesModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DP_Datum {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("likes")
    @Expose
    private Integer likes;
    @SerializedName("pallete_by")
    @Expose
    private String palleteBy;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("color1Id")
    @Expose
    private Integer color1Id;
    @SerializedName("color2Id")
    @Expose
    private Integer color2Id;
    @SerializedName("color3Id")
    @Expose
    private Integer color3Id;
    @SerializedName("color_1")
    @Expose
    private Color1 color1;
    @SerializedName("color_2")
    @Expose
    private Color2 color2;
    @SerializedName("color_3")
    @Expose
    private Color3 color3;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public String getPalleteBy() {
        return palleteBy;
    }

    public void setPalleteBy(String palleteBy) {
        this.palleteBy = palleteBy;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getColor1Id() {
        return color1Id;
    }

    public void setColor1Id(Integer color1Id) {
        this.color1Id = color1Id;
    }

    public Integer getColor2Id() {
        return color2Id;
    }

    public void setColor2Id(Integer color2Id) {
        this.color2Id = color2Id;
    }

    public Integer getColor3Id() {
        return color3Id;
    }

    public void setColor3Id(Integer color3Id) {
        this.color3Id = color3Id;
    }

    public Color1 getColor1() {
        return color1;
    }

    public void setColor1(Color1 color1) {
        this.color1 = color1;
    }

    public Color2 getColor2() {
        return color2;
    }

    public void setColor2(Color2 color2) {
        this.color2 = color2;
    }

    public Color3 getColor3() {
        return color3;
    }

    public void setColor3(Color3 color3) {
        this.color3 = color3;
    }

}