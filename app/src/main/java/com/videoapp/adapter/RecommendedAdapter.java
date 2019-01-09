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

public class RecommendedAdapter extends RecyclerView.Adapter<RecommendedAdapter.MyViewHolder> {

    Context context;
    List<Itemlistobject> itemlistobjects;

    public RecommendedAdapter() {
    }

    public RecommendedAdapter(Context context, List<Itemlistobject> itemlistobjects) {
        this.context = context;
        this.itemlistobjects = itemlistobjects;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tab_recommended_items, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {

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
//                    holder.imgPicRec.setImageBitmap(bitmap);
//                }
//            }.execute();
//        }

        if (!items.getName().isEmpty()){
            holder.lblNamesRec.setText(items.getName());
        } else {
            holder.lblNamesRec.setVisibility(View.GONE);
        }

//        if (!items.getDesc().isEmpty()){
//            holder.lblDescRec.setText(items.getDesc());
//        } else {
//            holder.lblDescRec.setVisibility(View.GONE);
//        }


    }

    @Override
    public int getItemCount() {
        return itemlistobjects.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imgPicRec;
        TextView lblNamesRec, lblDescRec;


        public MyViewHolder(View itemView) {
            super(itemView);
            imgPicRec = (ImageView) itemView.findViewById(R.id.imgPicRec);
            lblNamesRec = (TextView) itemView.findViewById(R.id.lblNamesRec);
            lblDescRec = (TextView) itemView.findViewById(R.id.lblDescRec);
        }
    }
}
