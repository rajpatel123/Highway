
package com.highway.millUserModule.SpinnerModelForMiller.ApproxLoad;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApproxLoadDatum {

    @SerializedName("ApproxLoadId")
    @Expose
    private String approxLoadId;
    @SerializedName("ApproxLoadTitle")
    @Expose
    private String approxLoadTitle;

    public String getApproxLoadId() {
        return approxLoadId;
    }

    public void setApproxLoadId(String approxLoadId) {
        this.approxLoadId = approxLoadId;
    }

    public String getApproxLoadTitle() {
        return approxLoadTitle;
    }

    public void setApproxLoadTitle(String approxLoadTitle) {
        this.approxLoadTitle = approxLoadTitle;
    }

}
