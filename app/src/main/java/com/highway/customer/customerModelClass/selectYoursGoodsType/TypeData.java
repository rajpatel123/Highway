
package com.highway.customer.customerModelClass.selectYoursGoodsType;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.highway.customer.customerModelClass.selectYoursGoodsType.GoodTypeDatum;

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
