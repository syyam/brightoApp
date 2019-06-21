package com.example.syyam.jobsapp.Models;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FinishSpecific {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<FinishSpecificDatum> data = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<FinishSpecificDatum> getData() {
        return data;
    }

    public void setData(List<FinishSpecificDatum> data) {
        this.data = data;
    }

}