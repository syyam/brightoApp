package com.example.syyam.jobsapp.Models.DesignerPalettesModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Color2 {

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

public Color_ getColor() {
return color;
}

public void setColor(Color_ color) {
this.color = color;
}

}