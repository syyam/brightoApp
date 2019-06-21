package com.example.syyam.jobsapp.Models.ProductDetailModel;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("ProjectTypes")
    @Expose
    private List<ProjectType_> projectTypes = null;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<ProjectType_> getProjectTypes() {
        return projectTypes;
    }

    public void setProjectTypes(List<ProjectType_> projectTypes) {
        this.projectTypes = projectTypes;
    }

}