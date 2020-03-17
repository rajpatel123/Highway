
package com.highway.customer.customerModelClass.customerInvoice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerInvoiceResp {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("customerInvoice")
    @Expose
    private CustomerInvoice customerInvoice;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public CustomerInvoice getCustomerInvoice() {
        return customerInvoice;
    }

    public void setCustomerInvoice(CustomerInvoice customerInvoice) {
        this.customerInvoice = customerInvoice;
    }

}
