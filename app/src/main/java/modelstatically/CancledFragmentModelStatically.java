package modelstatically;

import android.widget.ImageView;
import android.widget.TextView;

public class CancledFragmentModelStatically {

   String tvNoCancled;
    String tvCancledPresent;
    String cancledtransportImgView;


    @Override
    public String toString() {
        return "CancledFragmentModelStatically{" +
                "tvNoCancled='" + tvNoCancled + '\'' +
                ", tvCancledPresent='" + tvCancledPresent + '\'' +
                ", cancledtransportImgView='" + cancledtransportImgView + '\'' +
                '}';
    }


    public String getTvNoCancled() {
        return tvNoCancled;
    }

    public void setTvNoCancled(String tvNoCancled) {
        this.tvNoCancled = tvNoCancled;
    }

    public String getTvCancledPresent() {
        return tvCancledPresent;
    }

    public void setTvCancledPresent(String tvCancledPresent) {
        this.tvCancledPresent = tvCancledPresent;
    }

    public String getCancledtransportImgView() {
        return cancledtransportImgView;
    }

    public void setCancledtransportImgView(String cancledtransportImgView) {
        this.cancledtransportImgView = cancledtransportImgView;
    }


}
