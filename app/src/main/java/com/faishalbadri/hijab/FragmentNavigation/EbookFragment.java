package com.faishalbadri.hijab.FragmentNavigation;


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
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.faishalbadri.hijab.Adapter.AdapterEbook;
import com.faishalbadri.hijab.Adapter.AdapterVoting;
import com.faishalbadri.hijab.Helper.Server;
import com.faishalbadri.hijab.Model.PojoEbook;
import com.faishalbadri.hijab.Model.PojoVoting;
import com.faishalbadri.hijab.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class EbookFragment extends Fragment {


  public EbookFragment() {
    // Required empty public constructor
  }

  private RecyclerView rvEbook;
  private RequestQueue reqEbook;
  private Gson gsonEbook;
  private View v;
  String urlEbook = Server.BASE_URL+"getTbEbook.php";

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    v = inflater.inflate(R.layout.fragment_ebook, container, false);
    setView();
    getEbook();
    return v;
  }

  private void getEbook() {
    StringRequest request = new StringRequest(Method.GET, urlEbook, new Response.Listener<String>() {
      @Override
      public void onResponse(String response) {
        try {
          if (String.valueOf(new JSONObject(response).getString("msg")).equals("Data Semua Ebook")) {
            try {
              Log.i("response", response);
              final PojoEbook ebook = gsonEbook.fromJson(response, PojoEbook.class);
              final AdapterEbook adapterEbook = new AdapterEbook(getActivity(),ebook.getEbook());
              rvEbook.setAdapter(adapterEbook);

            } catch (Exception e) {
              e.printStackTrace();
            }
          } else {
            Toast.makeText(getActivity(), "null", Toast.LENGTH_SHORT).show();
          }
        } catch (JSONException e) {
          Toast.makeText(getActivity(), "no connect", Toast.LENGTH_SHORT).show();
        }

      }
    }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {
      }
    });
    reqEbook.add(request);
  }

  private void setView() {
    rvEbook = (RecyclerView) v.findViewById(R.id.rv_ebook);
    reqEbook = Volley.newRequestQueue(getActivity());
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonEbook = gsonBuilder.create();
    GridLayoutManager ebook = new GridLayoutManager(getActivity(),2);
    ebook.setOrientation(GridLayoutManager.VERTICAL);
    rvEbook.setLayoutManager(ebook);
  }

}
