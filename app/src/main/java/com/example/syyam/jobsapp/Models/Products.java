package com.example.syyam.jobsapp.Models;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Products {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<ProductsDatum> data = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<ProductsDatum> getData() {
        return data;
    }

    public void setData(List<ProductsDatum> data) {
        this.data = data;
    }

}