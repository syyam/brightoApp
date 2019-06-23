package com.example.syyam.jobsapp.Models.SpecificShadeModel;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("itemCode")
    @Expose
    private String itemCode;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("isAC")
    @Expose
    private Boolean isAC;
    @SerializedName("isRM")
    @Expose
    private Boolean isRM;
    @SerializedName("Products")
    @Expose
    private List<Product> products = null;
    @SerializedName("Family")
    @Expose
    private Family family;
    @SerializedName("Countries")
    @Expose
    private List<Country> countries = null;
    @SerializedName("color")
    @Expose
    private Color_ color;

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

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsAC() {
        return isAC;
    }

    public void setIsAC(Boolean isAC) {
        this.isAC = isAC;
    }

    public Boolean getIsRM() {
        return isRM;
    }

    public void setIsRM(Boolean isRM) {
        this.isRM = isRM;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public Color_ getColor() {
        return color;
    }

    public void setColor(Color_ color) {
        this.color = color;
    }

}