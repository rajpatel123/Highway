package com.videoapp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.videoapp.R;
import com.videoapp.model.Itemlistobject;

import java.net.URL;
import java.util.List;

public class FollowAdapter extends RecyclerView.Adapter<FollowAdapter.MyViewHolder> {

    Context context;
    List<Itemlistobject> itemlistobjects;

    public FollowAdapter() {
    }

    public FollowAdapter(Context context, List<Itemlistobject> itemlistobjects) {
        this.context = context;
        this.itemlistobjects = itemlistobjects;
    }

    @NonNull
    @Override
    public FollowAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tab_follow_items, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FollowAdapter.MyViewHolder holder, int position) {


        final Itemlistobject items = itemlistobjects.get(position);

//        if (!items.getPhoto().isEmpty() || !items.getPhoto().equals(null)){
//            new AsyncTask<Void,Void,Bitmap>(){
//                Bitmap targetBitmap=null;
//                @Override
//                protected Bitmap doInBackground(Void... voids) {
//                    try {
//                        URL url = new URL(items.getPhoto());
//                        Bitmap image= BitmapFactory.decodeStream(url.openConnection().getInputStream());
//                        int targetWidth = 250;
//                        int targetHeight = 250;
//                        Bitmap targetBitmap = Bitmap.createBitmap(targetWidth,
//                                targetHeight,Bitmap.Config.ARGB_8888);
//
//                        Canvas canvas = new Canvas(targetBitmap);
//                        Path path = new Path();
//                        path.addCircle(((float) targetWidth - 1) / 2,
//                                ((float) targetHeight - 1) / 2,
//                                (Math.min(((float) targetWidth),
//                                        ((float) targetHeight)) / 2),
//                                Path.Direction.CCW);
//
//                        canvas.clipPath(path);
//                        Bitmap sourceBitmap = image;
//                        canvas.drawBitmap(sourceBitmap,
//                                new Rect(0, 0, sourceBitmap.getWidth(),
//                                        sourceBitmap.getHeight()),
//                                new Rect(0, 0, targetWidth, targetHeight), null);
//                        return targetBitmap;
//                    } catch(Exception e) {
//                        e.printStackTrace();
//                    }
//                    return null;
//                }
//
//                @Override
//                protected void onPostExecute(Bitmap bitmap) {
//                    super.onPostExecute(bitmap);
//
//                    holder.imgPicFoll.setImageBitmap(bitmap);
//                }
//            }.execute();
//        }

        if (!items.getName().isEmpty()){
            holder.lblNamesFoll.setText(items.getName());
        } else {
            holder.lblNamesFoll.setVisibility(View.GONE);
        }

//        if (!items.getDesc().isEmpty()){
//            holder.lblDescFoll.setText(items.getDesc());
//        } else {
//            holder.lblDescFoll.setVisibility(View.GONE);
//        }

    }

    @Override
    public int getItemCount() {
        return itemlistobjects.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imgPicFoll;
        TextView lblNamesFoll, lblDescFoll;

        public MyViewHolder(View itemView) {
            super(itemView);
            imgPicFoll = (ImageView) itemView.findViewById(R.id.imgPicFoll);
            lblNamesFoll = (TextView) itemView.findViewById(R.id.lblNamesFoll);
            lblDescFoll = (TextView) itemView.findViewById(R.id.lblDescFoll);
        }
    }
}
