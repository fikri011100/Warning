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
import com.faishalbadri.hijab.DetailActivity.DetailActivity;
import com.faishalbadri.hijab.Helper.Server;
import com.faishalbadri.hijab.Model.PojoIsiNew;
import com.faishalbadri.hijab.R;

import java.util.List;

/**
 * Created by faishal on 9/14/17.
 */

public class AdapterIsiNew extends RecyclerView.Adapter<AdapterIsiNew.ViewHolder> {

    Activity context;
    List<PojoIsiNew.IsiBean> list_data;


    public AdapterIsiNew(List<PojoIsiNew.IsiBean> isi, FragmentActivity homeActivity) {
        this.context = homeActivity;
        this.list_data = isi;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_menurun, null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterIsiNew.ViewHolder holder, int position) {
        final PojoIsiNew.IsiBean listitem = list_data.get(position);
        Glide.with(context)
                .load(Server.BASE_IMG + list_data.get(position).getIsi_gambar())
                .crossFade()
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.imgListMenurun);
        holder.txtJudulMenurun.setText(list_data.get(position).getIsi_judul());
        holder.txtTimeMenurun.setText(list_data.get(position).getIsi_tgl_upload().toString());
        holder.cvListMenurun.setOnClickListener(new View.OnClickListener() {
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
        TextView txtJudulMenurun, txtTimeMenurun;
        ImageView imgListMenurun;
        CardView cvListMenurun;

        public ViewHolder(View itemView) {
            super(itemView);

            txtJudulMenurun = (TextView) itemView.findViewById(R.id.txtJudulMenurun);
            txtTimeMenurun = (TextView) itemView.findViewById(R.id.txtTimeMenurun);
            imgListMenurun = (ImageView) itemView.findViewById(R.id.imgListMenurun);
            cvListMenurun = (CardView) itemView.findViewById(R.id.cvListMenurun);
        }
    }
}