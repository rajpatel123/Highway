package com.videoapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import com.videoapp.R;
import com.videoapp.model.Itemlistobject;

public class MyAdapter  extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Itemlistobject> itemList;
    private Context context;
    private ItemClickListener clickListener;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView, textView1;
        ImageView imageView;
Button arrowimageview;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.list_title);
            textView1 = (TextView) itemView.findViewById(R.id.list_desc);
            imageView = (ImageView) itemView.findViewById(R.id.list_avatar);
/*arrowimageview=itemView.findViewById(R.id.checkBox);
arrowimageview.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent i=new Intent(context,InviteActivity.class);
        context.startActivity(i);

    }
});*/

        }
    }

    public MyAdapter(Context context, List<Itemlistobject> itemList) {

        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem, parent, false);
// set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.textView.setText(itemList.get(position).getName());
        holder.textView1.setText(itemList.get(position).getDesc());
        holder.imageView.setImageResource(itemList.get(position).getPhoto());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null) clickListener.itemClick(v, position);
            }
        });
        holder.imageView.setTag(holder);
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

    public interface ItemClickListener {
        public void itemClick(View view, int position);
    }

}
