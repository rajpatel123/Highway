package modelstatically;

import android.widget.ImageView;
import android.widget.TextView;

public class OnGoingFragmentModelStatically {

    String tvNoOnGoing;
    String tvOnGoingPresent;
   String onGoingTransportImgView;



    @Override
    public String toString() {
        return "OnGoingFragmentModelStatically{" +
                "tvNoOnGoing='" + tvNoOnGoing + '\'' +
                ", tvOnGoingPresent='" + tvOnGoingPresent + '\'' +
                ", onGoingTransportImgView='" + onGoingTransportImgView + '\'' +
                '}';
    }


    public String getTvNoOnGoing() {
        return tvNoOnGoing;
    }

    public void setTvNoOnGoing(String tvNoOnGoing) {
        this.tvNoOnGoing = tvNoOnGoing;
    }

    public String getTvOnGoingPresent() {
        return tvOnGoingPresent;
    }

    public void setTvOnGoingPresent(String tvOnGoingPresent) {
        this.tvOnGoingPresent = tvOnGoingPresent;
    }

    public String getOnGoingTransportImgView() {
        return onGoingTransportImgView;
    }

    public void setOnGoingTransportImgView(String onGoingTransportImgView) {
        this.onGoingTransportImgView = onGoingTransportImgView;
    }




}
