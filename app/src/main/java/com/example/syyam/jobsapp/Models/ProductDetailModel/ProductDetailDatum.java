package com.example.syyam.jobsapp.Models.ProductDetailModel;

import java.util.List;

import com.example.syyam.jobsapp.Models.CountryDatum;
import com.example.syyam.jobsapp.Models.ProductDetailModel.Category;
import com.example.syyam.jobsapp.Models.ProductDetailModel.FinishType;
import com.example.syyam.jobsapp.Models.ProductDetailModel.ProjectType;
import com.example.syyam.jobsapp.Models.ProductDetailModel.Surface;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductDetailDatum {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("spreading")
    @Expose
    private Integer spreading;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("coverImage")
    @Expose
    private String coverImage;

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    @SerializedName("isActive")
    @Expose
    private Boolean isActive;
    @SerializedName("Countries")
    @Expose
    private List<Country> countries = null;
    @SerializedName("ProjectType")
    @Expose
    private ProjectType projectType;
    @SerializedName("Category")
    @Expose
    private Category category;
    @SerializedName("Surface")
    @Expose
    private Surface surface;
    @SerializedName("FinishType")
    @Expose
    private FinishType finishType;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSpreading() {
        return spreading;
    }

    public void setSpreading(Integer spreading) {
        this.spreading = spreading;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public ProjectType getProjectType() {
        return projectType;
    }

    public void setProjectType(ProjectType projectType) {
        this.projectType = projectType;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Surface getSurface() {
        return surface;
    }

    public void setSurface(Surface surface) {
        this.surface = surface;
    }

    public FinishType getFinishType() {
        return finishType;
    }

    public void setFinishType(FinishType finishType) {
        this.finishType = finishType;
    }

}