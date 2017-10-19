package com.faishalbadri.hijab.FragmentNavigation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.faishalbadri.hijab.Adapter.AdapterAllNews;
import com.faishalbadri.hijab.Helper.Server;
import com.faishalbadri.hijab.Model.PojoIsi;
import com.faishalbadri.hijab.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoreFragment extends Fragment {


  public MoreFragment() {
    // Required empty public constructor
  }

  private RecyclerView rvMore;
  private RequestQueue reqMore;
  private Gson gsonMore;
  private String urlMore = Server.BASE_URL+"getTbIsi.php";
  View v;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    v = inflater.inflate(R.layout.fragment_more, container, false);
    setView();
    getDataMore();
    return v;
  }

  private void getDataMore() {
    StringRequest request = new StringRequest(Method.POST, urlMore, new Response.Listener<String>() {
      @Override
      public void onResponse(String response) {
        try {
          if (String.valueOf(new JSONObject(response).getString("msg")).equals("Data Semua Isi")) {
            try {
              Log.i("Response Data", response);
              final PojoIsi isi = gsonMore.fromJson(response, PojoIsi.class);
              final AdapterAllNews adapterPopuler = new AdapterAllNews(isi.getIsi(),
                  getActivity());
              rvMore.setAdapter(adapterPopuler);

            } catch (Exception e) {
              e.printStackTrace();
            }
          } else {
            Toast.makeText(getActivity().getApplicationContext(), "Database Is Null",
                Toast.LENGTH_SHORT).show();
          }
        } catch (JSONException e) {

        }

      }
    }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {
      }
    });
    reqMore.add(request);
  }

  private void setView() {
    rvMore = (RecyclerView) v.findViewById(R.id.rv_more);
    reqMore = Volley.newRequestQueue(getActivity());
    GsonBuilder gbKategori = new GsonBuilder();
    gsonMore = gbKategori.create();
    LinearLayoutManager kategori = new LinearLayoutManager(getActivity());
    kategori.setOrientation(LinearLayoutManager.VERTICAL);
    rvMore.setLayoutManager(kategori);
  }

}
