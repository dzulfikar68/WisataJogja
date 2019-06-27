package com.digitcreativestudio.wisatajogja;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import java.util.List;

public class WisataAdapter extends RecyclerView.Adapter<WisataAdapter.WisataViewHolder>{

    private Context context;
    private List<WisataModel> list;
    RecyclerView mRecyclerView;

    public WisataAdapter(Context context, List<WisataModel> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
    }

    @NonNull
    @Override
    public WisataViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater
                .from(context)
                .inflate(R.layout.wisata_adapter, viewGroup,false);
        return new WisataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final WisataViewHolder itemMarketViewHolder, int i) {
        final WisataModel model = list.get(i);
        itemMarketViewHolder.name.setText(model.getName());
        itemMarketViewHolder.address.setText(model.getAddress());
        Glide.with(context).load(model.getImage()).into(itemMarketViewHolder.image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class WisataViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView name, address;

        public WisataViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            address = itemView.findViewById(R.id.address);
            image = itemView.findViewById(R.id.image);
        }
    }
}