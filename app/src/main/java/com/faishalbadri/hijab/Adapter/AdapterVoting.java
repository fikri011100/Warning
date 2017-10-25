package com.faishalbadri.hijab.Adapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.faishalbadri.hijab.FragmentDialog.FragmentVotingDialog;
import com.faishalbadri.hijab.Helper.Server;
import com.faishalbadri.hijab.Model.PojoVoting;
import com.faishalbadri.hijab.Model.PojoVoting.VotingBean;
import com.faishalbadri.hijab.R;
import java.util.List;

/**
 * Created by fikriimaduddin on 10/21/17.
 */

public class AdapterVoting extends RecyclerView.Adapter<AdapterVoting.ViewHolder>{

  Activity context;
  List<PojoVoting.VotingBean> list_data;
  String idUser;

  public AdapterVoting(FragmentActivity context,
      List<VotingBean> list_data, String idUser) {
    this.context = context;
    this.list_data = list_data;
    this.idUser = idUser;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_voting, null);

    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final ViewHolder holder, int position) {
    final PojoVoting.VotingBean listitem = list_data.get(position);
    RequestOptions options = new RequestOptions().fitCenter().format(DecodeFormat.PREFER_ARGB_8888)
        .override(150, 150);
    Glide.with(context)
        .load(Server.BASE_IMG + listitem.getVoting_img())
        .apply(options)
        .into(holder.imgGridVote);
    holder.imgGridVote.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Bundle bundle = new Bundle();
        bundle.putString("id_voting",listitem.getId_voting());
        bundle.putString("nama",listitem.getVoting_nickname());
        bundle.putString("img",listitem.getVoting_img());
        bundle.putString("id_user",idUser);
        FragmentActivity activity = (FragmentActivity) (context);
        android.support.v4.app.FragmentManager fm = activity.getSupportFragmentManager();
        FragmentVotingDialog alert = new FragmentVotingDialog();
        alert.setArguments(bundle);
        activity.getFragmentManager().beginTransaction().addSharedElement(holder.imgGridVote,
            ViewCompat.getTransitionName(holder.imgGridVote));
//        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) context, v, "img_voting");
        alert.show(fm,"");
      }
    });
  }

  @Override
  public int getItemCount() {
    return list_data.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    ImageView imgGridVote;

    public ViewHolder(View itemView) {
      super(itemView);
      imgGridVote = (ImageView) itemView.findViewById(R.id.img_voting_grid);
    }
  }
}
