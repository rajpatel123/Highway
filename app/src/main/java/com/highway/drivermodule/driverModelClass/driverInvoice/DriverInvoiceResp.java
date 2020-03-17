
package com.highway.drivermodule.driverModelClass.driverInvoice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DriverInvoiceResp {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("driverInvoice")
    @Expose
    private DriverInvoice driverInvoice;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public DriverInvoice getDriverInvoice() {
        return driverInvoice;
    }

    public void setDriverInvoice(DriverInvoice driverInvoice) {
        this.driverInvoice = driverInvoice;
    }

}
