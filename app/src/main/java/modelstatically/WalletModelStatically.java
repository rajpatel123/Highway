package modelstatically;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class WalletModelStatically {

    String tvNetAmount;
    String tvAddMoneyView;
    String etEnterRechargeAmount;
    String tvFirstMoney;
    String tvSecondMoney;
    String tvThirdMoney;
    String tvPromocode;
    String btnAddAmount;

    @Override
    public String toString() {
        return "WalletModelStatically{" +
                "tvNetAmount='" + tvNetAmount + '\'' +
                ", tvAddMoneyView='" + tvAddMoneyView + '\'' +
                ", etEnterRechargeAmount='" + etEnterRechargeAmount + '\'' +
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

    public String getTvAddMoneyView() {
        return tvAddMoneyView;
    }

    public void setTvAddMoneyView(String tvAddMoneyView) {
        this.tvAddMoneyView = tvAddMoneyView;
    }

    public String getEtEnterRechargeAmount() {
        return etEnterRechargeAmount;
    }

    public void setEtEnterRechargeAmount(String etEnterRechargeAmount) {
        this.etEnterRechargeAmount = etEnterRechargeAmount;
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
