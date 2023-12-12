package com.example.retrofitputlearning.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitputlearning.R;
import com.example.retrofitputlearning.models.DataModel;

import java.util.List;

public class GettingAllDataRecyclerAdapter extends RecyclerView.Adapter<GettingAllDataRecyclerAdapter.ViewHolder> {
    Context context;
    List<DataModel> arrdatamodelEntities;


    public GettingAllDataRecyclerAdapter(Context context, List<DataModel> arrdatamodelEntitiesList) {
        this.context = context;
        this.arrdatamodelEntities = arrdatamodelEntitiesList;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, email;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.nameDataLayout);
            email = itemView.findViewById(R.id.emailDataLayout);

        }
    }

    @NonNull
    @Override
    public GettingAllDataRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.data_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GettingAllDataRecyclerAdapter.ViewHolder holder, int position) {

        holder.name.setText(arrdatamodelEntities.get(position).getName());
        holder.email.setText(arrdatamodelEntities.get(position).getEmail());

    }

    @Override
    public int getItemCount() {
        return arrdatamodelEntities.size();
    }


}
