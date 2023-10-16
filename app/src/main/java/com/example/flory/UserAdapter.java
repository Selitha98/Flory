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

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder>{

    private Context context;
    private List<UserData> userList;

    public UserAdapter(Context context, List<UserData> userList) {
        this.context = context;
        this.userList = userList;
    }


    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.userName.setText(userList.get(position).getUsername());
        holder.userEmail.setText(userList.get(position).getUseremail());

        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UserDetails.class);
                intent.putExtra("Username", userList.get(holder.getAdapterPosition()).getUsername());
                intent.putExtra("Phone", userList.get(holder.getAdapterPosition()).getUserphone());
                intent.putExtra("Email", userList.get(holder.getAdapterPosition()).getUseremail());
                intent.putExtra("Password", userList.get(holder.getAdapterPosition()).getUserPassword());

                // Add any other details you want to send
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void searchDataList(ArrayList<UserData> searchList){
        userList = searchList;
        notifyDataSetChanged();
    }
}

class UserViewHolder extends RecyclerView.ViewHolder{

    TextView userName, userEmail;
    CardView recCard;
    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        userName = itemView.findViewById(R.id.userName);
        recCard = itemView.findViewById(R.id.recCard);
        userEmail = itemView.findViewById(R.id.userEmail);
    }
}
