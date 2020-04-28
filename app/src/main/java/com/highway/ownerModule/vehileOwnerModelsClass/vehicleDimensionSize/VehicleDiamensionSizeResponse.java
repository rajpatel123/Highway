
package com.highway.ownerModule.vehileOwnerModelsClass.vehicleDimensionSize;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VehicleDiamensionSizeResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("dimansionData")
    @Expose
    private DimansionData dimansionData;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public DimansionData getDimansionData() {
        return dimansionData;
    }

    public void setDimansionData(DimansionData dimansionData) {
        this.dimansionData = dimansionData;
    }

}
