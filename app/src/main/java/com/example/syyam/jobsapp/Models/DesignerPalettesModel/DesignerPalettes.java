package com.example.syyam.jobsapp.Models.DesignerPalettesModel;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DesignerPalettes {

@SerializedName("success")
@Expose
private Boolean success;
@SerializedName("data")
@Expose
private List<DP_Datum> data = null;

public Boolean getSuccess() {
return success;
}

public void setSuccess(Boolean success) {
this.success = success;
}

public List<DP_Datum> getData() {
return data;
}

public void setData(List<DP_Datum> data) {
this.data = data;
}

}