package com.example.flory;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class FlowerAdapter extends RecyclerView.Adapter<FlowerViewHolder>{

    private Context context;
    private List<FlowersData> flowersList;
    public FlowerAdapter(Context context, List<FlowersData> flowersList) {
        this.context = context;
        this.flowersList = flowersList;
    }

    @NonNull
    @Override
    public FlowerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.flower_item, parent, false);
        return new FlowerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlowerViewHolder holder, int position) {
        Glide.with(context).load(flowersList.get(position).getFlowerPhoto()).into(holder.flowerImage);
        holder.flowerTitle.setText(flowersList.get(position).getFlowerTitle());
        holder.flowerScience.setText(flowersList.get(position).getFlowerScientificalName());
        holder.recFlowerCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FlowersDetails.class);
                intent.putExtra("Image", flowersList.get(holder.getAdapterPosition()).getFlowerPhoto());
                intent.putExtra("Title", flowersList.get(holder.getAdapterPosition()).getFlowerTitle());
                intent.putExtra("Description", flowersList.get(holder.getAdapterPosition()).getFlowerDescription());
                intent.putExtra("Key",flowersList.get(holder.getAdapterPosition()).getKey());
                intent.putExtra("Scientifically Name", flowersList.get(holder.getAdapterPosition()).getFlowerScientificalName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return flowersList.size();
    }

    public void searchDataList(ArrayList<FlowersData> searchList){
        flowersList = searchList;
        notifyDataSetChanged();
    }
}

class FlowerViewHolder extends RecyclerView.ViewHolder{
    ImageView flowerImage;
    TextView flowerTitle, flowerScience;
    CardView recFlowerCard;
    public FlowerViewHolder(@NonNull View itemView) {
        super(itemView);
        flowerImage = itemView.findViewById(R.id.flowerImage);
        recFlowerCard = itemView.findViewById(R.id.recFlowerCard);
        flowerTitle = itemView.findViewById(R.id.flowerTitle);
        flowerScience = itemView.findViewById(R.id.flowerScience);
    }
}
