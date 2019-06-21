package com.example.syyam.jobsapp.Models.ProductDetailModel;

import java.util.List;

import com.example.syyam.jobsapp.Models.Datum;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductDetailCity {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<ProductDetailDatum> data = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<ProductDetailDatum> getData() {
        return data;
    }

    public void setData(List<ProductDetailDatum> data) {
        this.data = data;
    }

}