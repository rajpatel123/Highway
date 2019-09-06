package adapterstatically;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.highway.R;

import java.util.List;

import fragment.UpComingFragment;
import modelstatically.UpComingFragmentModelStatically;

public class UpComingFragmentAdapter extends RecyclerView.Adapter<UpComingFragmentAdapter.MyViewHolder> {

    Context context;
    List<UpComingFragmentModelStatically>upComingFragmentModelStaticallies;
    CallCsallBack callCsallBack;

    public UpComingFragmentAdapter(Context context, List<UpComingFragmentModelStatically> upComingFragmentModelStaticallyList) {
        this.context = context;
        this.upComingFragmentModelStaticallies = upComingFragmentModelStaticallyList;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

       LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.fragment_up_coming_item,viewGroup,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        final UpComingFragmentModelStatically upcominghistory = upComingFragmentModelStaticallies.get(i);

        UpComingFragmentModelStatically upComingFragmentModelStatically = upComingFragmentModelStaticallies.get(i);

          myViewHolder.tvUpcomingDate.setText(upComingFragmentModelStatically.getUpcomingDateTv());
          myViewHolder.tvUpcomingTime.setText(upComingFragmentModelStatically.getUpcomingTimeTv());
          myViewHolder.tvUpcomingPickUpAddress.setText(upComingFragmentModelStatically.getUpcomingPickUpAddressTv());
          myViewHolder.tvUpcomingDeliverAddress.setText(upComingFragmentModelStatically.getUpcomingDeliverAddressTv());
          myViewHolder.tvUpcomingVehicleName.setText(upComingFragmentModelStatically.getUpcomingVehicleNameTv());
          myViewHolder.tvUpcomingIndication.setText(upComingFragmentModelStatically.getUpcomingIndicationTv());


      /*  myViewHolder..setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callCsallBack!=null){

                } }
        });*/



    }

    @Override
    public int getItemCount() {
        return upComingFragmentModelStaticallies.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvUpcomingDate,tvUpcomingTime, tvUpcomingPickUpAddress,tvUpcomingDeliverAddress, tvUpcomingIndication,tvUpcomingVehicleName;
        private ImageView imgVUpcomingImg,  imgVUpcomingDeliverImg;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvUpcomingDate = itemView.findViewById(R.id.UpcomingDate);
            tvUpcomingTime = itemView.findViewById(R.id.UpcomingTime);
            tvUpcomingPickUpAddress = itemView.findViewById(R.id.UpcomingPickUpAddress);
            tvUpcomingDeliverAddress = itemView.findViewById(R.id.UpcomingDeliverAddress);
            tvUpcomingVehicleName = itemView.findViewById(R.id.UpcomingVehicleName);
            tvUpcomingIndication = itemView.findViewById(R.id.UpcomingIndication);
            imgVUpcomingImg = itemView.findViewById(R.id.UpcomingImg);
            imgVUpcomingDeliverImg = itemView.findViewById(R.id.UpcomingDeliverImg);

        }
    }



    public  interface CallCsallBack{
        public  void onCall(String number);

    }



}
