package com.example.tryout_pas.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.tryout_pas.R;
import com.example.tryout_pas.model.HoloMember;
import java.util.ArrayList;
import java.util.List;

public class HoloAdapter extends RecyclerView.Adapter<HoloAdapter.ViewHolder> {
    private List<HoloMember> holoList;
    private List<HoloMember> holoListFull; // untuk simpan data asli
    private Context context;

    public HoloAdapter(Context context, List<HoloMember> holoList) {
        this.context = context;
        this.holoList = new ArrayList<>(holoList);
        this.holoListFull = new ArrayList<>(holoList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_holo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HoloMember member = holoList.get(position);
        holder.tvName.setText(member.getName());
        holder.tvGen.setText(member.getGeneration());
        Glide.with(context).load(member.getImageUrl()).into(holder.imgProfile);
    }

    @Override
    public int getItemCount() {
        return holoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvGen;
        ImageView imgProfile;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvGen = itemView.findViewById(R.id.tvGen);
            imgProfile = itemView.findViewById(R.id.imgProfile);
        }
    }

    public void filter(String query) {
        holoList.clear();
        if (query.isEmpty()) {
            holoList.addAll(holoListFull);
        } else {
            String lowerQuery = query.toLowerCase();
            for (HoloMember member : holoListFull) {
                if (member.getName().toLowerCase().contains(lowerQuery) ||
                        member.getGeneration().toLowerCase().contains(lowerQuery)) {
                    holoList.add(member);
                }
            }
        }
        notifyDataSetChanged();
    }

    public void updateData(List<HoloMember> newList) {
        holoList.clear();
        holoList.addAll(newList);
        holoListFull.clear();
        holoListFull.addAll(newList);
        notifyDataSetChanged();
    }
}
