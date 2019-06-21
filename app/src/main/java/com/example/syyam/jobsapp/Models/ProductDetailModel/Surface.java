package com.example.syyam.jobsapp.Models.ProductDetailModel;

import java.util.List;

import com.example.syyam.jobsapp.Models.ProductDetailModel.Category_;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Surface {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("Categories")
    @Expose
    private List<Category_> categories = null;

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

    public List<Category_> getCategories() {
        return categories;
    }

    public void setCategories(List<Category_> categories) {
        this.categories = categories;
    }

}