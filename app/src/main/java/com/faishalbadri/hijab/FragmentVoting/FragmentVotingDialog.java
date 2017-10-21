package com.faishalbadri.hijab.FragmentVoting;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ImageView;
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
import com.faishalbadri.hijab.Helper.Server;
import com.faishalbadri.hijab.R;
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
  private RequestQueue reqLike;
  private RequestQueue reqUnlike;

  public FragmentVotingDialog() {
    // Required empty public constructor
  }

  String id, nama, img, id_user,id_voting,id_session;
  private String urlLike = Server.BASE_URL+"like.php";
  private String urlUnlike = Server.BASE_URL+"unlike.php";
  Double like,status_session;
  View v;


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    v = inflater.inflate(R.layout.fragment_dialog_voting, container, false);
    setView();
    Bundle mArgs = getArguments();
    nama = mArgs.getString("nama");
    id = mArgs.getString("id");
    id_user = mArgs.getString("id_user");
    img = mArgs.getString("img");
    like = mArgs.getDouble("like");
    status_session = mArgs.getDouble("session_status");
    id_voting = mArgs.getString("id_voting");
    id_session = mArgs.getString("id_session");
    txtNamaVoting.setText(nama);
    Toast.makeText(getActivity(), id_user, Toast.LENGTH_SHORT).show();
    RequestOptions options = new RequestOptions().fitCenter().format(DecodeFormat.PREFER_ARGB_8888);
    Glide.with(getActivity())
        .load(Server.BASE_IMG + img)
        .apply(options)
        .into(imgVoting);

    if (status_session == 1){
      btnAfterLikeVoting.setVisibility(View.VISIBLE);
      btnBeforeLikeVoting.setVisibility(View.INVISIBLE);
    }
    onClickMethod();
    return v;
  }

  private void setView() {
    imgVoting = (ImageView) v.findViewById(R.id.img_voting);
    btnBeforeLikeVoting = (ImageView) v.findViewById(R.id.btn_before_like_voting);
    btnAfterLikeVoting = (ImageView) v.findViewById(R.id.btn_after_like_voting);
    txtNamaVoting = (TextView) v.findViewById(R.id.txt_nama_voting);
    btnShareVoting = (ImageView) v.findViewById(R.id.btn_share_voting);
    reqLike = Volley.newRequestQueue(getActivity());
    reqUnlike = Volley.newRequestQueue(getActivity());
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
          if (String.valueOf(new JSONObject(response).getString("msq")).equals("berhasil update voting")) {
            try {

            } catch (Exception e) {
              e.printStackTrace();
            }
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
        params.put("id_voting", id);
        params.put("id_user", id_user);
        params.put("id_session", id_session);
        params.put("session_status", String.valueOf(status_session));
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
          if (String.valueOf(new JSONObject(response).getString("msq")).equals("berhasil update voting")) {
            try {

            } catch (Exception e) {
              e.printStackTrace();
            }
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
        params.put("id_voting", id);
        params.put("id_session", id_session);
        return params;
      }
    };
    reqUnlike.add(request);
  }
}
