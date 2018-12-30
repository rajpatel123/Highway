package adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import dev_video.dev_video.Main2Activity;
import dev_video.dev_video.R;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.ProgrammingViewHolder> {
    Context context;
    String link;

    public DashboardAdapter(Context context, String link) {
        this.context = context;
        this.link = link;
    }

    @NonNull
    @Override
    public ProgrammingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_data, viewGroup, false);
        return new ProgrammingViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgrammingViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 8;
    }

    public class ProgrammingViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        ImageButton like, shareVideo;
        public ProgrammingViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_feeds);
            like = itemView.findViewById(R.id.like_button);
            shareVideo = itemView.findViewById(R.id.share_video);
            like.setTag("black");

            like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(like.getTag().equals("black")) {
                        like.setImageDrawable(context.getResources().getDrawable(R.drawable.liked));
                        like.setTag("red");
                    }
                    else if(like.getTag().equals("red")){
                        like.setImageDrawable(context.getResources().getDrawable(R.drawable.like));
                        like.setTag("black");
                    }
                }
            });

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, Main2Activity.class);
                    Toast.makeText(context, getAdapterPosition()+"", Toast.LENGTH_SHORT).show();
                    context.startActivity(i);
                }
            });
        }
    }
}
