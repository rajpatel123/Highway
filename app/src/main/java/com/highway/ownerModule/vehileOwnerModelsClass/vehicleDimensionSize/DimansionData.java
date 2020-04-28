
package com.highway.ownerModule.vehileOwnerModelsClass.vehicleDimensionSize;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DimansionData {

    @SerializedName("dimansion_size_data")
    @Expose
    private List<DimansionSizeDatum> dimansionSizeData = null;

    public List<DimansionSizeDatum> getDimansionSizeData() {
        return dimansionSizeData;
    }

    public void setDimansionSizeData(List<DimansionSizeDatum> dimansionSizeData) {
        this.dimansionSizeData = dimansionSizeData;
    }

}
