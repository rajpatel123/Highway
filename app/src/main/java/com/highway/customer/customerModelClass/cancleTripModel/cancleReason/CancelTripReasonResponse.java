
package com.highway.customer.customerModelClass.cancleTripModel.cancleReason;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CancelTripReasonResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("cancelTripReson")
    @Expose
    private List<CancelTripReson> cancelTripReson = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<CancelTripReson> getCancelTripReson() {
        return cancelTripReson;
    }

    public void setCancelTripReson(List<CancelTripReson> cancelTripReson) {
        this.cancelTripReson = cancelTripReson;
    }

}
