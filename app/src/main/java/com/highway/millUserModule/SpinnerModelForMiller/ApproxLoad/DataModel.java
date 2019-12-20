package com.highway.millUserModule.SpinnerModelForMiller.ApproxLoad;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataModel {

    @SerializedName("approx_load_data")
    @Expose
    private List<ApproxLoadDatum> approxLoadData = null;

    public List<ApproxLoadDatum> getApproxLoadData() {
        return approxLoadData;
    }

    public void setApproxLoadData(List<ApproxLoadDatum> approxLoadData) {
        this.approxLoadData = approxLoadData;
    }

}
