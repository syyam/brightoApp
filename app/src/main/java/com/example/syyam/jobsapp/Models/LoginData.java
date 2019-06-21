package com.example.syyam.jobsapp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginData {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("isActive")
    @Expose
    private Boolean isActive;
    @SerializedName("liked_products")
    @Expose
    private String likedProducts;
    @SerializedName("liked_shades")
    @Expose
    private String likedShades;
    @SerializedName("liked_pallets")
    @Expose
    private Object likedPallets;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getLikedProducts() {
        return likedProducts;
    }

    public void setLikedProducts(String likedProducts) {
        this.likedProducts = likedProducts;
    }

    public String getLikedShades() {
        return likedShades;
    }

    public void setLikedShades(String likedShades) {
        this.likedShades = likedShades;
    }

    public Object getLikedPallets() {
        return likedPallets;
    }

    public void setLikedPallets(Object likedPallets) {
        this.likedPallets = likedPallets;
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

}