
package com.highway.customer.customerModelClass.cancleTripModel.cancleReason;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CancelTripReson {

    @SerializedName("cancelId")
    @Expose
    private String cancelId;
    @SerializedName("cancelReason")
    @Expose
    private String cancelReason;
    private boolean selected;

    public String getCancelId() {
        return cancelId;
    }

    public void setCancelId(String cancelId) {
        this.cancelId = cancelId;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }



}
