package com.faishalbadri.hijab.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.faishalbadri.hijab.DetailActivity.DetailActivity;
import com.faishalbadri.hijab.Helper.Server;
import com.faishalbadri.hijab.Model.PojoPerKategori;
import com.faishalbadri.hijab.R;

import java.util.List;

/**
 * Created by fikriimaduddin on 9/15/17.
 */

public class AdapterMoreNewsDetail extends RecyclerView.Adapter<AdapterMoreNewsDetail.ViewHolder> {
    Context context;
    List<PojoPerKategori.IsiBean> list_data;

    public AdapterMoreNewsDetail(List<PojoPerKategori.IsiBean> populer, Context list_data) {
        this.context = list_data;
        this.list_data = populer;
    }


    @Override
    public AdapterMoreNewsDetail.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_menurun, null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterMoreNewsDetail.ViewHolder holder, int position) {
        final PojoPerKategori.IsiBean listitem = list_data.get(position);
        Glide.with(context)
                .load(Server.BASE_IMG + list_data.get(position).getIsi_gambar())
                .crossFade()
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.img);
        holder.txtJudul.setText(list_data.get(position).getIsi_judul());
        holder.txtTanggal.setText(list_data.get(position).getIsi_tgl_upload().toString());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(v.getContext(),DetailActivity.class);
                a.putExtra("id_isi",listitem.getId_isi());
                a.putExtra("id_kategori",listitem.getId_kategori());
                a.putExtra("judul",listitem.getIsi_judul());
                a.putExtra("gambar",listitem.getIsi_gambar());
                a.putExtra("keterangan",listitem.getIsi_keterangan());
                a.putExtra("tglUpload",listitem.getIsi_tgl_upload());
                v.getContext().startActivity(a);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtJudul, txtTanggal;
        ImageView img;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);

            txtJudul = (TextView) itemView.findViewById(R.id.txtJudulMenurun);
            txtTanggal = (TextView) itemView.findViewById(R.id.txtTimeMenurun);
            img = (ImageView) itemView.findViewById(R.id.imgListMenurun);
            cardView = (CardView) itemView.findViewById(R.id.cvListMenurun);
        }
    }}
