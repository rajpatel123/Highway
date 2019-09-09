package modelstatically;

import android.widget.ImageView;
import android.widget.TextView;

public class CompletedFragmentModelStatically {

    String tvNOCompleted;
    String tvPresent;
    String transportImgView;

    @Override
    public String toString() {
        return "CompletedFragmentModelStatically{" +
                "tvNOCompleted='" + tvNOCompleted + '\'' +
                ", tvPresent='" + tvPresent + '\'' +
                ", transportImgView='" + transportImgView + '\'' +
                '}';
    }




    public String getTvNOCompleted() {
        return tvNOCompleted;
    }

    public void setTvNOCompleted(String tvNOCompleted) {
        this.tvNOCompleted = tvNOCompleted;
    }

    public String getTvPresent() {
        return tvPresent;
    }

    public void setTvPresent(String tvPresent) {
        this.tvPresent = tvPresent;
    }

    public String getTransportImgView() {
        return transportImgView;
    }

    public void setTransportImgView(String transportImgView) {
        this.transportImgView = transportImgView;
    }




}
