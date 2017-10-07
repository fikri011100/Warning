package com.faishalbadri.hijab.DetailActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.faishalbadri.hijab.Helper.Server;
import com.faishalbadri.hijab.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.ErrorReason;
import com.google.android.youtube.player.YouTubePlayer.OnFullscreenListener;
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener;
import com.google.android.youtube.player.YouTubePlayer.PlayerStyle;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;

public class DetailVideoActivity extends YouTubeBaseActivity implements
    OnInitializedListener {

  private static final int RECOVERY_DIALOG_REQUEST = 1;
  private String judulVideo, id, video, duration;
  private YouTubePlayerView ytVideo;
  private TextView txtJudul;
  private AdView adView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
    setContentView(R.layout.activity_detail_video);
    setView();
    putString();
    setDetail();
    setAdView();
  }

  private void setAdView() {
    AdRequest request = new AdRequest.Builder().build();
    adView.loadAd(request);
  }

  private void setView() {
    ytVideo = (YouTubePlayerView) findViewById(R.id.ytVideo);
    txtJudul = (TextView) findViewById(R.id.txtJudulVideo);
    adView = (AdView)findViewById(R.id.ad_detail_video);
  }

  private void putString() {
    id = getIntent().getStringExtra("id");
    judulVideo = getIntent().getStringExtra("judul_video");
    video = getIntent().getStringExtra("video");
    duration = getIntent().getStringExtra("duration");
  }

  private void setDetail() {
    txtJudul.setText(judulVideo);
    ytVideo.initialize(Server.YT_CODE, this);
  }

  @Override
  public void onInitializationSuccess(Provider provider, YouTubePlayer youTubePlayer, boolean b) {
    if (!b) {

      // loadVideo() will auto play video
      // Use cueVideo() method, if you don't want to play it automatically
      youTubePlayer.loadVideo(video);
      youTubePlayer.addFullscreenControlFlag(RECOVERY_DIALOG_REQUEST);
      // Hiding player controls
      youTubePlayer.setPlayerStyle(PlayerStyle.DEFAULT);

    }
  }
  @Override
  public void onInitializationFailure (YouTubePlayer.Provider provider,
      YouTubeInitializationResult youTubeInitializationResult){

    if (youTubeInitializationResult.isUserRecoverableError()) {
      youTubeInitializationResult.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
    } else {
      String errorMessage = String
          .format(getString(R.string.error_player), youTubeInitializationResult.toString());
      Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }
  }
  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode ==RECOVERY_DIALOG_REQUEST) {
      getYoutubePlayerProvider().initialize(Server.YT_CODE,this);
    }
  }
  protected YouTubePlayerView getYoutubePlayerProvider() {
    return ytVideo;
  }
}
