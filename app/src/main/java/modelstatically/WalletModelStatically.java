package modelstatically;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class WalletModelStatically {

    String tvNetAmount;
    String tvFirstMoney;
    String tvSecondMoney;
    String tvThirdMoney;
    String tvPromocode;
    String btnAddAmount;

    @Override
    public String toString() {
        return "WalletModelStatically{" +
                "tvNetAmount='" + tvNetAmount + '\'' +
                ", tvFirstMoney='" + tvFirstMoney + '\'' +
                ", tvSecondMoney='" + tvSecondMoney + '\'' +
                ", tvThirdMoney='" + tvThirdMoney + '\'' +
                ", tvPromocode='" + tvPromocode + '\'' +
                ", btnAddAmount='" + btnAddAmount + '\'' +
                '}';
    }


    public String getTvNetAmount() {
        return tvNetAmount;
    }

    public void setTvNetAmount(String tvNetAmount) {
        this.tvNetAmount = tvNetAmount;
    }

    public String getTvFirstMoney() {
        return tvFirstMoney;
    }

    public void setTvFirstMoney(String tvFirstMoney) {
        this.tvFirstMoney = tvFirstMoney;
    }

    public String getTvSecondMoney() {
        return tvSecondMoney;
    }

    public void setTvSecondMoney(String tvSecondMoney) {
        this.tvSecondMoney = tvSecondMoney;
    }

    public String getTvThirdMoney() {
        return tvThirdMoney;
    }

    public void setTvThirdMoney(String tvThirdMoney) {
        this.tvThirdMoney = tvThirdMoney;
    }

    public String getTvPromocode() {
        return tvPromocode;
    }

    public void setTvPromocode(String tvPromocode) {
        this.tvPromocode = tvPromocode;
    }

    public String getBtnAddAmount() {
        return btnAddAmount;
    }

    public void setBtnAddAmount(String btnAddAmount) {
        this.btnAddAmount = btnAddAmount;
    }



    //private CheckBox cbPromocode;
}
