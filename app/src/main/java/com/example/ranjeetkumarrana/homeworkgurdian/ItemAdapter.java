package com.example.ranjeetkumarrana.homeworkgurdian;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.balysv.materialripple.MaterialRippleLayout;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.NewHolder>
{
    Context mContext;
    List<ListItem> mData;

    public ItemAdapter(Context mContext, List<ListItem> mData)
    {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public ItemAdapter.NewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View layout;
        layout = LayoutInflater.from(mContext).inflate(R.layout.item_list,viewGroup,false);

        return new NewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull NewHolder newHolder, int i)
    {
        newHolder.Username.setText(mData.get(i).getName());
        newHolder.description.setText(mData.get(i).getDescription());
        //newHolder.img_user.setImageResource(mData.get(i).getImage());

    }

    @Override
    public int getItemCount()
    {
        return mData.size();
    }

    public class NewHolder extends RecyclerView.ViewHolder
    {

        private TextView Username,description;
        //private ImageView img_user;
        private MaterialRippleLayout container;

        public NewHolder(@NonNull View itemView)
        {
            super(itemView);

            container = itemView.findViewById(R.id.rootLayout2);
            Username = itemView.findViewById(R.id.name);
            description = itemView.findViewById(R.id.desc);
            //img_user = itemView.findViewById(R.id.image);
        }
    }
}
