
package com.highway.ownerModule.vehileOwnerModelsClass.vehiceLoadCapicity;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoadCapicityData {

    @SerializedName("load_cacpacity_size_data")
    @Expose
    private List<LoadCacpacitySizeDatum> loadCacpacitySizeData = null;

    public List<LoadCacpacitySizeDatum> getLoadCacpacitySizeData() {
        return loadCacpacitySizeData;
    }

    public void setLoadCacpacitySizeData(List<LoadCacpacitySizeDatum> loadCacpacitySizeData) {
        this.loadCacpacitySizeData = loadCacpacitySizeData;
    }

}
