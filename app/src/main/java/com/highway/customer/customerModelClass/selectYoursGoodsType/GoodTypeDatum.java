
package com.highway.customer.customerModelClass.selectYoursGoodsType;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GoodTypeDatum {

    @SerializedName("GoodsTypeId")
    @Expose
    private String goodsTypeId;
    @SerializedName("GoodsTypeTitle")
    @Expose
    private String goodsTypeTitle;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    private boolean isSelected=false;
    public String getGoodsTypeId() {
        return goodsTypeId;
    }

    public void setGoodsTypeId(String goodsTypeId) {
        this.goodsTypeId = goodsTypeId;
    }

    public String getGoodsTypeTitle() {
        return goodsTypeTitle;
    }

    public void setGoodsTypeTitle(String goodsTypeTitle) {
        this.goodsTypeTitle = goodsTypeTitle;
    }

}
