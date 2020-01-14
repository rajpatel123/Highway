
package com.highway.customer.customerModelClass.selectYoursGoodsType;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GoodsTypeDataResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("typeData")
    @Expose
    private TypeData typeData;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public TypeData getTypeData() {
        return typeData;
    }

    public void setTypeData(TypeData typeData) {
        this.typeData = typeData;
    }

}
