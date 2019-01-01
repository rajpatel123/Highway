package com.videoapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.videoapp.R;
import com.videoapp.activities.Main2Activity;
import com.videoapp.model.VideoList;

import java.util.List;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.ProgrammingViewHolder> {
    Context context;
    String link;
    List<VideoList> videoLists;
    public DashboardAdapter(Context context, List<VideoList> videoLists) {
        this.context = context;
        this.videoLists = videoLists;
    }

    @NonNull
    @Override
    public ProgrammingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_data, viewGroup, false);
        return new ProgrammingViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProgrammingViewHolder holder, int position) {
        holder.like.setTag("black");

        holder.uploadedby.setText(videoLists.get(holder.getAdapterPosition()).getUserName());
        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.like.getTag().equals("black")) {
                    holder.like.setImageDrawable(context.getResources().getDrawable(R.drawable.liked));
                    holder.like.setTag("red");
                }
                else if(holder.like.getTag().equals("red")){
                    holder.like.setImageDrawable(context.getResources().getDrawable(R.drawable.like));
                    holder.like.setTag("black");
                }
            }
        });

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, Main2Activity.class);
                i.putExtra("url",videoLists.get(holder.getAdapterPosition()).getVideo());
                Toast.makeText(context, holder.getAdapterPosition()+"", Toast.LENGTH_SHORT).show();
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return videoLists.size();
    }

    public class ProgrammingViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        ImageButton like, shareVideo;
        TextView uploadedby;
        public ProgrammingViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_feeds);
            like = itemView.findViewById(R.id.like_button);
            uploadedby = itemView.findViewById(R.id.uploadedby);
            shareVideo = itemView.findViewById(R.id.share_video);

        }
    }
}
