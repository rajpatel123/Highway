
package com.highway.customer.customerModelClass.spinnerGoodsType;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TypeData {

    @SerializedName("good_type_data")
    @Expose
    private List<GoodTypeDatum> goodTypeData = null;

    public List<GoodTypeDatum> getGoodTypeData() {
        return goodTypeData;
    }

    public void setGoodTypeData(List<GoodTypeDatum> goodTypeData) {
        this.goodTypeData = goodTypeData;
    }

}
