package com.faishalbadri.hijab.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.faishalbadri.hijab.Helper.Server;
import com.faishalbadri.hijab.Model.PojoKategori;
import com.faishalbadri.hijab.R;

import java.util.List;

/**
 * Created by faishal on 9/15/17.
 */

public class AdapterKategori extends RecyclerView.Adapter<AdapterKategori.ViewHolder> {
    Activity context;
    List<PojoKategori.KategoriBean> list_data;

    public AdapterKategori(List<PojoKategori.KategoriBean> populer, FragmentActivity list_data) {
        this.context = list_data;
        this.list_data = populer;
    }


    @Override
    public AdapterKategori.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_gridview, null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterKategori.ViewHolder holder, int position) {
        final PojoKategori.KategoriBean listitem = list_data.get(position);
        Glide.with(context)
                .load(Server.BASE_IMG + list_data.get(position).getKategori_gambar())
                .crossFade()
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.imgGrid);
        holder.txtGrid.setText(list_data.get(position).getKategori_nama());
        holder.cvGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent a = new Intent(v.getContext(),IsiKategoriActivity.class);
//                a.putExtra("id",listitem.getId_kategori());
//                v.getContext().startActivity(a);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtGrid;
        ImageView imgGrid;
        CardView cvGrid;

        public ViewHolder(View itemView) {
            super(itemView);

            txtGrid = (TextView) itemView.findViewById(R.id.txtGrid);
            imgGrid = (ImageView) itemView.findViewById(R.id.imgGrid);
            cvGrid = (CardView) itemView.findViewById(R.id.cvGridView);
        }
    }
}

