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

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;
    private List<DiseasesData> dataList;
    public MyAdapter(Context context, List<DiseasesData> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(dataList.get(position).getDiseasePhoto()).into(holder.recImage);
        holder.recTitle.setText(dataList.get(position).getDiseaseTitle());
        holder.recDesc.setText(dataList.get(position).getDiseaseDescription());
//        holder.recTreat.setText(dataList.get(position).getDiseaseTreeatement());
        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DiseasesDetails.class);
                intent.putExtra("Image", dataList.get(holder.getAdapterPosition()).getDiseasePhoto());
                intent.putExtra("Description", dataList.get(holder.getAdapterPosition()).getDiseaseDescription());
                intent.putExtra("Title", dataList.get(holder.getAdapterPosition()).getDiseaseTitle());
                intent.putExtra("Key",dataList.get(holder.getAdapterPosition()).getKey());
                intent.putExtra("Treatments", dataList.get(holder.getAdapterPosition()).getDiseaseTreeatement());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void searchDataList(ArrayList<DiseasesData> searchList){
        dataList = searchList;
        notifyDataSetChanged();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder{
    ImageView recImage;
    TextView recTitle, recDesc, recTreat;
    CardView recCard;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        recImage = itemView.findViewById(R.id.recImage);
        recCard = itemView.findViewById(R.id.recCard);
        recTitle = itemView.findViewById(R.id.recTitle);
        recDesc = itemView.findViewById(R.id.recDesc);
//        recTreat = itemView.findViewById(R.id.recTreat);
    }
}
