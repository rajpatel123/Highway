
package com.highway.millUserModule.SpinnerModelForMiller.GoodsTypes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

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
