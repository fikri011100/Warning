package com.faishalbadri.hijab.FragmentDialog;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.faishalbadri.hijab.ActivityList.SearchActivity;
import com.faishalbadri.hijab.Adapter.AdapterIsiNew;
import com.faishalbadri.hijab.Helper.Server;
import com.faishalbadri.hijab.Model.PojoIsi;
import com.faishalbadri.hijab.Model.PojoSession;
import com.faishalbadri.hijab.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentVotingDialog extends DialogFragment {


  ImageView imgVoting;
  ImageView btnBeforeLikeVoting;
  ImageView btnAfterLikeVoting;
  TextView txtNamaVoting;
  ImageView btnShareVoting;
  RelativeLayout relativeLayout;
  private RequestQueue reqLike,reqUnlike,reqSession;
  Gson gsonSession;
  ProgressBar progress;


  public FragmentVotingDialog() {
    // Required empty public constructor
  }

  String  nama, img, id_user,id_voting,id_session,status_session, isi;
  private String urlLike = Server.BASE_URL+"like.php";
  private String urlUnlike = Server.BASE_URL+"unlike.php";
  private String urlSession = Server.BASE_URL+"getTbSession.php";
  View v;


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    v = inflater.inflate(R.layout.fragment_dialog_voting, container, false);
    initView();
    getIntent();
    getSession();
    setView();
    onClickMethod();
    return v;
  }

  private void setView() {
    txtNamaVoting.setText(nama);
    RequestOptions options = new RequestOptions().fitCenter().format(DecodeFormat.PREFER_ARGB_8888);
    Glide.with(getActivity())
        .load(Server.BASE_IMG + img)
        .apply(options)
        .into(imgVoting);
    isi = "Dapatkan aplikasi ini di Google Playstore. \"LINK\" \n Dan jangan lupa untuk mensupport " + nama + " sebagai pemenang \"NAMA AWARDING\" di tahun 2017" ;

  }

  private void getSession() {
    StringRequest request = new StringRequest(Method.POST, urlSession, new Response.Listener<String>() {
      @Override
      public void onResponse(String response) {
        try {
          if (String.valueOf(new JSONObject(response).getString("msg")).equals("Data Semua Session")) {
            try {
              PojoSession pojoSession = gsonSession.fromJson(response,PojoSession.class);
              Log.i("Response Data", response);
              for (int a = 0; a < pojoSession.getSession().size(); a++) {
                id_session = pojoSession.getSession().get(a).getId_session();
                status_session = pojoSession.getSession().get(a).getSession_status();
              }
              if (status_session.equals("1")){
                btnBeforeLikeVoting.setVisibility(View.INVISIBLE);
                btnAfterLikeVoting.setVisibility(View.VISIBLE);
              }
              relativeLayout.setVisibility(View.VISIBLE);
              progress.setVisibility(View.GONE);
              } catch (Exception e) {
              e.printStackTrace();
            }
          } else {
            Log.i("Response Data", response);
            relativeLayout.setVisibility(View.VISIBLE);
            progress.setVisibility(View.GONE);
          }
        } catch (JSONException e) {

        }

      }
    }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {
        progress.setVisibility(View.GONE);
      }
    }) {
      @Override
      protected Map<String, String> getParams() throws AuthFailureError {
        Map<String, String> params = new HashMap<String, String>();
        params.put("id_user", id_user);
        params.put("id_voting", id_voting);
        return params;
      }
    };
    reqSession.add(request);
  }

  private void getIntent() {
    Bundle mArgs = getArguments();
    nama = mArgs.getString("nama");
    id_voting = mArgs.getString("id_voting");
    id_user = mArgs.getString("id_user");
    img = mArgs.getString("img");
  }

  private void initView() {
    progress = (ProgressBar)v.findViewById(R.id.progress);
    relativeLayout = (RelativeLayout) v.findViewById(R.id.invisible_relative_dialog_fragment);
    imgVoting = (ImageView) v.findViewById(R.id.img_voting);
    btnBeforeLikeVoting = (ImageView) v.findViewById(R.id.btn_before_like_voting);
    btnAfterLikeVoting = (ImageView) v.findViewById(R.id.btn_after_like_voting);
    txtNamaVoting = (TextView) v.findViewById(R.id.txt_nama_voting);
    btnShareVoting = (ImageView) v.findViewById(R.id.btn_share_voting);
    reqLike = Volley.newRequestQueue(getActivity());
    reqUnlike = Volley.newRequestQueue(getActivity());
    reqSession = Volley.newRequestQueue(getActivity());
    GsonBuilder gbSession = new GsonBuilder();
    gsonSession = gbSession.create();
  }

  private void IntentShare() {
    Intent share = new Intent(Intent.ACTION_SEND);
    share.setType("text/plain");
    share.putExtra(Intent.EXTRA_SUBJECT,"PINKY HIJAB");
    share.putExtra(Intent.EXTRA_TEXT,isi);

    startActivity(Intent.createChooser(share,"Bagikan dengan"));
  }

  private void onClickMethod() {
    btnBeforeLikeVoting.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        likeOnClick();
        btnAfterLikeVoting.setVisibility(View.VISIBLE);
        btnBeforeLikeVoting.setVisibility(View.INVISIBLE);
      }
    });
    btnAfterLikeVoting.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        unlikeOnClick();
        btnBeforeLikeVoting.setVisibility(View.VISIBLE);
        btnAfterLikeVoting.setVisibility(View.INVISIBLE);
      }
    });
    btnShareVoting.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        IntentShare();
      }
    });
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    Dialog dialog = super.onCreateDialog(savedInstanceState);
    dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
    return dialog;
  }

  @Override
  public void onStart() {
    super.onStart();
    Dialog dialog = getDialog();
    if (dialog != null) {
      int width = LayoutParams.MATCH_PARENT;
      int height = LayoutParams.WRAP_CONTENT;
      dialog.getWindow().setLayout(width, height);
    }
  }

  private void likeOnClick() {
    StringRequest request = new StringRequest(Method.POST, urlLike, new Response.Listener<String>() {
      @Override
      public void onResponse(String response) {
        try {
          if (String.valueOf(new JSONObject(response).getString("msq")).equals("berhasil update voting") && String.valueOf(new JSONObject(response).getString("msq")).equals("berhasil update voting")) {
            try {
              getSession();
            } catch (Exception e) {
              e.printStackTrace();
            }
          }else {

          }
        } catch (JSONException e) {

        }

      }
    }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {

      }
    }) {
      @Override
      protected Map<String, String> getParams() throws AuthFailureError {
        Map<String, String> params = new HashMap<String, String>();
        params.put("id_voting", id_voting);
        params.put("id_user", id_user);
        params.put("id_session", "");
        return params;
      }
    };
    reqLike.add(request);
  }

  private void unlikeOnClick() {
    StringRequest request = new StringRequest(Method.POST, urlUnlike, new Response.Listener<String>() {
      @Override
      public void onResponse(String response) {
        try {
          if (String.valueOf(new JSONObject(response).getString("msq")).equals("berhasil delete voting") && String.valueOf(new JSONObject(response).getString("msq")).equals("berhasil update voting")) {
            try {
              getSession();
            } catch (Exception e) {
              e.printStackTrace();
            }
          }else{

          }
        } catch (JSONException e) {

        }

      }
    }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {

      }
    }) {
      @Override
      protected Map<String, String> getParams() throws AuthFailureError {
        Map<String, String> params = new HashMap<String, String>();
        params.put("id_voting", id_voting);
        params.put("id_session", id_session);
        return params;
      }
    };
    reqUnlike.add(request);
  }
}
