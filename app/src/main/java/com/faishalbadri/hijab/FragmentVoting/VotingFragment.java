package com.faishalbadri.hijab.FragmentVoting;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.faishalbadri.hijab.Adapter.AdapterAllNews;
import com.faishalbadri.hijab.Adapter.AdapterVoting;
import com.faishalbadri.hijab.Helper.Server;
import com.faishalbadri.hijab.Model.PojoIsi;
import com.faishalbadri.hijab.Model.PojoSession;
import com.faishalbadri.hijab.Model.PojoVoting;
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
public class VotingFragment extends Fragment {


  public VotingFragment() {
    // Required empty public constructor
  }

  private String url = Server.BASE_URL+"getTbVoting.php";
  private Gson gsonVoting;
  private RecyclerView rvVoting;
  private RequestQueue reqVoting;
  private View v;
  String id_user;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    v = inflater.inflate(R.layout.fragment_voting, container, false);
    setView();
    Bundle bundle = getArguments();
    id_user = bundle.getString("id");
    getVote();
    return v;
  }

  private void getVote() {
    StringRequest request = new StringRequest(Method.GET, url, new Response.Listener<String>() {
      @Override
      public void onResponse(String response) {
        try {
          if (String.valueOf(new JSONObject(response).getString("msg")).equals("Data Semua Voting")) {
            try {
              Log.i("Response Data", response);
              final PojoVoting voting = gsonVoting.fromJson(response, PojoVoting.class);
              final AdapterVoting adapterVoting = new AdapterVoting(getActivity(),voting.getVoting(),id_user);
              rvVoting.setAdapter(adapterVoting);

            } catch (Exception e) {
              e.printStackTrace();
            }
          } else {

          }
        } catch (JSONException e) {

        }

      }
    }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {
      }
    });
    reqVoting.add(request);
  }

  private void setView() {
    rvVoting = (RecyclerView) v.findViewById(R.id.rv_voting);
    reqVoting = Volley.newRequestQueue(getActivity());
    GsonBuilder gbKategori = new GsonBuilder();
    gsonVoting = gbKategori.create();
    GridLayoutManager voting = new GridLayoutManager(getActivity(),3);
    voting.setOrientation(GridLayoutManager.VERTICAL);
    rvVoting.setLayoutManager(voting);
  }

}
