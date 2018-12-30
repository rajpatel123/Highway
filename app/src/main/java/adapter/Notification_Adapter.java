package adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import dev_video.dev_video.R;
import model.Notification_Model;

public class Notification_Adapter extends RecyclerView.Adapter<Notification_Adapter.MyViewHolder> {

    private List<Notification_Model> moviesList;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.notification_list_row, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Notification_Model notification_model = moviesList.get(i);
        myViewHolder.title.setText(notification_model.getTitle());
        myViewHolder.imageView.setImageResource(notification_model.getImg());
        myViewHolder.days.setText(notification_model.getDays());
        myViewHolder.detail.setText(notification_model.getDetail());


    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, days, detail;
        ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            imageView=view.findViewById(R.id.img);
            title=view.findViewById(R.id.title_);
            days=view.findViewById(R.id.days);
            detail=view.findViewById(R.id.details);
        }
    }

    public Notification_Adapter(List<Notification_Model>moviesList)
    {
        this.moviesList = moviesList;
    }
}
