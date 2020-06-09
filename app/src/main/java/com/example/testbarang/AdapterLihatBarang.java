package com.example.testbarang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterLihatBarang extends
        RecyclerView.Adapter<AdapterLihatBarang.ViewHolder> {

    private ArrayList<Barang> daftarBarang;
    private Context context;

    public AdapterLihatBarang(ArrayList<Barang> barangs, Context ctx){
        daftarBarang = barangs;
        context = ctx;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle;

        ViewHolder(View v){
            super(v);
            tvTitle = (TextView) v.findViewById(R.id.tv_namabarang);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_barang, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
       final String name = daftarBarang.get(position).getNama();
       holder.tvTitle.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v) {

           }
       });
       holder.tvTitle.setOnLongClickListener(new View.OnLongClickListener(){
           @Override
           public boolean onLongClick(View view) {
               return true;
           }
       });
       holder.tvTitle.setText(name);
    }

    @Override
    public int getItemCount() {
        return daftarBarang.size();
    }
}
