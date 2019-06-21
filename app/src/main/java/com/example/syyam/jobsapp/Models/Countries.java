package com.example.syyam.jobsapp.Models;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Countries {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<CountryDatum> data = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<CountryDatum> getData() {
        return data;
    }

    public void setData(List<CountryDatum> data) {
        this.data = data;
    }

}