package com.example.syyam.jobsapp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DealerCityDatum {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("phone")
    @Expose
    private Object phone;
    @SerializedName("isAC")
    @Expose
    private Boolean isAC;
    @SerializedName("isRM")
    @Expose
    private Boolean isRM;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public Object getPhone() {
        return phone;
    }

    public void setPhone(Object phone) {
        this.phone = phone;
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

}