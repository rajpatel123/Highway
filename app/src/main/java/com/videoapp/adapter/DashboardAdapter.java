package com.videoapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.videoapp.R;
import com.videoapp.activities.Main2Activity;
import com.videoapp.model.VideoList;

import java.net.URL;
import java.util.List;
import java.util.logging.Handler;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.ProgrammingViewHolder> {
    Context context;
    String link;
    List<VideoList> videoLists;


    public DashboardAdapter() {
    }

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

        final VideoList list = videoLists.get(position);

        //holder.uploadedby.setText(videoLists.get(holder.getAdapterPosition()).getUserName());
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

        if (!list.getThumbs().isEmpty() || !list.getThumbs().equals(null)) {
            Picasso.with(context).load(list.getThumbs()).into(holder.imageView);
        }




        if (!list.getProfilePics().isEmpty() || !list.getProfilePics().equals(null)){

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        URL url = new URL(list.getProfilePics());
                        Bitmap image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                        Bitmap circleImage = getRoundedShape(image);
                        holder.imgProfilePic.setImageBitmap(circleImage);
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }

                public Bitmap getRoundedShape(Bitmap scaleBitmapImage) {
                    int targetWidth = 250;
                    int targetHeight = 250;
                    Bitmap targetBitmap = Bitmap.createBitmap(targetWidth,
                            targetHeight,Bitmap.Config.ARGB_8888);

                    Canvas canvas = new Canvas(targetBitmap);
                    Path path = new Path();
                    path.addCircle(((float) targetWidth - 1) / 2,
                            ((float) targetHeight - 1) / 2,
                            (Math.min(((float) targetWidth),
                                    ((float) targetHeight)) / 2),
                            Path.Direction.CCW);

                    canvas.clipPath(path);
                    Bitmap sourceBitmap = scaleBitmapImage;
                    canvas.drawBitmap(sourceBitmap,
                            new Rect(0, 0, sourceBitmap.getWidth(),
                                    sourceBitmap.getHeight()),
                            new Rect(0, 0, targetWidth, targetHeight), null);
                    return targetBitmap;
                }
            });
            thread.start();

        } else {
            holder.uploadedby.setVisibility(View.GONE);
        }




        if (!list.getStrDesc().equals(null)){
            holder.uploadedby.setText(list.getStrDesc());
        }

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, Main2Activity.class);
                i.putExtra("url",videoLists.get(holder.getAdapterPosition()).getVideo());
                //Toast.makeText(context, holder.getAdapterPosition()+"", Toast.LENGTH_SHORT).show();
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return videoLists.size();
    }

    public class ProgrammingViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView, imgProfilePic;
        ImageButton like, shareVideo;
        TextView uploadedby;
        public ProgrammingViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_feeds);
            imgProfilePic = itemView.findViewById(R.id.imgProfilePic);
            like = itemView.findViewById(R.id.like_button);
            uploadedby = itemView.findViewById(R.id.uploadedby);
            shareVideo = itemView.findViewById(R.id.share_video);

        }
    }


}
