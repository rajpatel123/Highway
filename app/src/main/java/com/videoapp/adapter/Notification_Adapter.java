package com.videoapp.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URL;
import java.util.List;

import com.videoapp.R;
import com.videoapp.model.Notification_Model;

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
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {
        final Notification_Model notification_model = moviesList.get(i);
        myViewHolder.title.setText(notification_model.getTitle());
        //myViewHolder.imageView.setImageResource(notification_model.getImg());
        myViewHolder.days.setText(notification_model.getDays());
        myViewHolder.detail.setText(notification_model.getDetail());


        if (!notification_model.getImg().isEmpty() || !notification_model.getImg().equals(null)){
            new AsyncTask<Void,Void,Bitmap>(){
                Bitmap targetBitmap=null;
                @Override
                protected Bitmap doInBackground(Void... voids) {
                    try {
                        URL url = new URL(notification_model.getImg());
                        Bitmap image= BitmapFactory.decodeStream(url.openConnection().getInputStream());
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
                        Bitmap sourceBitmap = image;
                        canvas.drawBitmap(sourceBitmap,
                                new Rect(0, 0, sourceBitmap.getWidth(),
                                        sourceBitmap.getHeight()),
                                new Rect(0, 0, targetWidth, targetHeight), null);
                        return targetBitmap;
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Bitmap bitmap) {
                    super.onPostExecute(bitmap);

                    myViewHolder.imageView.setImageBitmap(bitmap);
                }
            }.execute();
        }




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
