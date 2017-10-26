package com.faishalbadri.hijab.Adapter;


import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.faishalbadri.hijab.ActivityList.ListVideoActivity;
import com.faishalbadri.hijab.Adapter.AdapterListVideo.adViewHolder;
import com.faishalbadri.hijab.Adapter.AdapterListVideo.itemViewHolder;
import com.faishalbadri.hijab.DetailActivity.DetailVideoActivity;
import com.faishalbadri.hijab.Helper.Server;
import com.faishalbadri.hijab.Model.PojoAds;
import com.faishalbadri.hijab.Model.PojoAds.AdsBean;
import com.faishalbadri.hijab.Model.PojoVideo;
import com.faishalbadri.hijab.Model.PojoVideo.VideoBean;
import com.faishalbadri.hijab.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import java.util.List;

/**
 * Created by fikriimaduddin on 9/28/17.
 */

public class AdapterListVideo extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private static final int CONTENT_TYPE = 0;
  private static final int AD_TYPE = 1;
  Activity context;
  List<VideoBean> list_data;

  public AdapterListVideo(List<VideoBean> populer, ListVideoActivity context) {
    this.context = context;
    this.list_data = populer;
  }

  @Override
  public int getItemViewType(int position) {
    if (position>1 && position % 3 == 0) {
      return AD_TYPE;
    }
    return CONTENT_TYPE;
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view;
    LayoutInflater layoutInflater = LayoutInflater.from(context);
    switch (viewType) {
      default:
        view = layoutInflater
            .inflate(R.layout.list_video, parent,false);
        return new itemViewHolder(view);
      case AD_TYPE:
        view = layoutInflater.inflate(R.layout.list_ad_view, parent, false);
        return new adViewHolder(view);
    }

  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    if (!(holder instanceof itemViewHolder)) {
      return;
    }

    final PojoVideo.VideoBean listitem = list_data.get(position);

    itemViewHolder itemViewHolder = (itemViewHolder) holder;
    RequestOptions options = new RequestOptions().fitCenter().format(DecodeFormat.PREFER_ARGB_8888)
        .override(150, 150);
    Glide.with(context)
        .load(Server.imgus + listitem.getVideo() + Server.lanjut)
        .apply(options)
        .into(itemViewHolder.img);
    itemViewHolder.txtJudul.setText(listitem.getJudul_video());
    itemViewHolder.txtJudul.setMaxLines(3);
    itemViewHolder.txtDuration.setText(listitem.getDuration().toString());
    itemViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent a = new Intent(v.getContext(), DetailVideoActivity.class);
        a.putExtra("id", listitem.getId());
        a.putExtra("judul_video", listitem.getJudul_video());
        a.putExtra("video", listitem.getVideo());
        a.putExtra("duration", listitem.getDuration());
        v.getContext().startActivity(a);
        ((Activity)context).overridePendingTransition(R.anim.slide_from_right,R.anim.slide_from_right);
      }
    });
  }
  @Override
  public int getItemCount() {
    return list_data.size();
  }

  public class itemViewHolder extends RecyclerView.ViewHolder {

    TextView txtJudul, txtDuration;
    ImageView img;
    CardView cardView;

    public itemViewHolder(View itemView) {
      super(itemView);

      txtJudul = (TextView) itemView.findViewById(R.id.txtJudulListVideo);
      txtDuration = (TextView) itemView.findViewById(R.id.txtDuraiton);
      img = (ImageView) itemView.findViewById(R.id.imgListVideo);
      cardView = (CardView) itemView.findViewById(R.id.cardVideo);
    }
  }

  public class adViewHolder extends RecyclerView.ViewHolder {

    AdView adView;
    public adViewHolder(View itemView) {
      super(itemView);
      adView = (AdView) itemView.findViewById(R.id.ad_home_rec);
      AdRequest request = new AdRequest.Builder().build();
      adView.loadAd(request);
    }
  }
}
