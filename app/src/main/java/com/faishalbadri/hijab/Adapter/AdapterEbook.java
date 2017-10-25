package com.faishalbadri.hijab.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.faishalbadri.hijab.Helper.Server;
import com.faishalbadri.hijab.Model.PojoEbook;
import com.faishalbadri.hijab.Model.PojoEbook.EbookBean;
import com.faishalbadri.hijab.R;
import java.util.List;

/**
 * Created by fikriimaduddin on 10/24/17.
 */

public class AdapterEbook extends RecyclerView.Adapter<AdapterEbook.ViewHolder> {

  Activity context;
  List<PojoEbook.EbookBean> list_data;

  public AdapterEbook(FragmentActivity activity, List<EbookBean> ebook) {
    this.context = activity;
    this.list_data = ebook;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_ebook, null);

    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    final PojoEbook.EbookBean listitem = list_data.get(position);
    holder.txtEbook.setText(listitem.getJudul_ebook());
    RequestOptions options = new RequestOptions().fitCenter().format(DecodeFormat.PREFER_ARGB_8888)
        .override(150, 150);
    Glide.with(context)
        .load(Server.BASE_IMG + listitem.getGambar_ebook())
        .apply(options)
        .into(holder.imgEbook);
    holder.imgEbook.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(listitem.getLink()));
        v.getContext().startActivity(i);
      }
    });
  }

  @Override
  public int getItemCount() {
    return list_data.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    ImageView imgEbook;
    TextView txtEbook;
    public ViewHolder(View itemView) {
      super(itemView);
      imgEbook = (ImageView)itemView.findViewById(R.id.img_ebook);
      txtEbook = (TextView) itemView.findViewById(R.id.txt_ebook);
    }
  }
}
