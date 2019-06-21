package com.example.syyam.jobsapp.Models;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SurfaceSpecific {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<SurfaceSpecificDatum> data = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<SurfaceSpecificDatum> getData() {
        return data;
    }

    public void setData(List<SurfaceSpecificDatum> data) {
        this.data = data;
    }

}