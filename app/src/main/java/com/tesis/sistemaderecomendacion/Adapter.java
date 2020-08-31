package com.tesis.sistemaderecomendacion;

import android.content.ClipData;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.myViewHolder> {
    Context mContext;
    List<item> mData;

    public Adapter(Context mContext, List<item> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v =  inflater.inflate(R.layout.card_item, parent, false);
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        //holder.background_img.setImageDrawable(LoadImageFromWebOperations(mData.get(position).getBackgroundString()));

        holder.background_img.setImageResource(mData.get(position).getBackground());
        //holder.profile_photo.setImageResource(mData.get(position).getProfilePhoto());
        holder.tv_title.setText(mData.get(position).getProfileName());

        holder.tv_bnFollowers.setText(mData.get(position).getNbFollowers());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        ImageView profile_photo, background_img;
        TextView tv_title, tv_bnFollowers;

        public myViewHolder(View itemView) {
            super(itemView);

            //profile_photo = itemView.findViewById(R.id.profile_img);
            background_img = itemView.findViewById(R.id.card_background);
            tv_title = itemView.findViewById(R.id.card_title);
            tv_bnFollowers = itemView.findViewById(R.id.card_nb_follower);
        }
    }

    private Drawable LoadImageFromWebOperations(String url)
    {
        try{
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        }catch (Exception e) {
            System.out.println("Exc="+e);
            return null;
        }
    }
}
