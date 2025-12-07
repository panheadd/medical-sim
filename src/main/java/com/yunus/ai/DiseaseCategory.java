package com.yunus.ai;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class DiseaseCategory {

    @SerializedName(value = "name", alternate = {"category"})
    private String name;

    private List<Disease> diseases;

    public String getName() { return name; }
    public List<Disease> getDiseases() { return diseases; }
}
