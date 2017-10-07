package com.faishalbadri.hijab.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.faishalbadri.hijab.ActivityList.ListVideoActivity;
import com.faishalbadri.hijab.DetailActivity.DetailVideoActivity;
import com.faishalbadri.hijab.Helper.Server;
import com.faishalbadri.hijab.Model.PojoVideo;
import com.faishalbadri.hijab.Model.PojoVideo.VideoBean;
import com.faishalbadri.hijab.R;
import java.util.List;

/**
 * Created by fikriimaduddin on 9/28/17.
 */

public class AdapterListVideo extends RecyclerView.Adapter<AdapterListVideo.ViewHolder> {
  Activity context;
  List<PojoVideo.VideoBean> list_data;

  public AdapterListVideo(List<VideoBean> populer, ListVideoActivity context) {
    this.context = context;
    this.list_data = populer;
  }


  @Override
  public AdapterListVideo.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_video, null);

    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(AdapterListVideo.ViewHolder holder, int position) {
    final PojoVideo.VideoBean listitem = list_data.get(position);
    Glide.with(context)
        .load(Server.imgus + listitem.getVideo()+Server.lanjut)
        .crossFade()
        .placeholder(R.mipmap.ic_launcher)
        .into(holder.img);
    holder.txtJudul.setText(listitem.getJudul_video());
    holder.txtJudul.setMaxLines(3);
    holder.txtDuration.setText(listitem.getDuration().toString());
    holder.cardView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent a = new Intent(v.getContext(),DetailVideoActivity.class);
        a.putExtra("id",listitem.getId());
        a.putExtra("judul_video",listitem.getJudul_video());
        a.putExtra("video",listitem.getVideo());
        a.putExtra("duration",listitem.getDuration());
        v.getContext().startActivity(a);
      }
    });
  }

  @Override
  public int getItemCount() {
    return list_data.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    TextView txtJudul, txtDuration;
    ImageView img;
    CardView cardView;

    public ViewHolder(View itemView) {
      super(itemView);

      txtJudul = (TextView) itemView.findViewById(R.id.txtJudulListVideo);
      txtDuration = (TextView) itemView.findViewById(R.id.txtDuraiton);
      img = (ImageView) itemView.findViewById(R.id.imgListVideo);
      cardView = (CardView) itemView.findViewById(R.id.cardVideo);
    }
  }
}
