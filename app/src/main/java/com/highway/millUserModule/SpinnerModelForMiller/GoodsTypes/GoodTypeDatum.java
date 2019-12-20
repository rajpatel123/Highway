
package com.highway.millUserModule.SpinnerModelForMiller.GoodsTypes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GoodTypeDatum {

    @SerializedName("GoodTypeId")
    @Expose
    private String goodTypeId;
    @SerializedName("GoodTypeTitle")
    @Expose
    private String goodTypeTitle;

    public String getGoodTypeId() {
        return goodTypeId;
    }

    public void setGoodTypeId(String goodTypeId) {
        this.goodTypeId = goodTypeId;
    }

    public String getGoodTypeTitle() {
        return goodTypeTitle;
    }

    public void setGoodTypeTitle(String goodTypeTitle) {
        this.goodTypeTitle = goodTypeTitle;
    }

}
