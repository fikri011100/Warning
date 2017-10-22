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
public class AllNewsFragment extends Fragment {

  private View v;
  private RecyclerView rvAllNews;
  private RequestQueue reqAllNews;
  private Gson gsonAllNews;
  private String url = Server.BASE_URL+"getTbIsi.php";

  public AllNewsFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    v = inflater.inflate(R.layout.fragment_all_news, container, false);
    setView();
    getAllNews();
    return v;
  }

  private void getAllNews() {
    StringRequest request = new StringRequest(Method.POST, url, new Response.Listener<String>() {
      @Override
      public void onResponse(String response) {
        try {
          if (String.valueOf(new JSONObject(response).getString("msg")).equals("Data Semua Isi")) {
            try {
              Log.i("Response Data", response);
              final PojoIsi isi = gsonAllNews.fromJson(response, PojoIsi.class);
              final AdapterAllNews adapterPopuler = new AdapterAllNews(isi.getIsi(),
                  getActivity());
              rvAllNews.setAdapter(adapterPopuler);

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
    reqAllNews.add(request);
  }

  private void setView() {
    rvAllNews = (RecyclerView) v.findViewById(R.id.rv_all_new);
    reqAllNews = Volley.newRequestQueue(getActivity());
    GsonBuilder gbKategori = new GsonBuilder();
    gsonAllNews = gbKategori.create();
    LinearLayoutManager kategori = new LinearLayoutManager(getActivity());
    kategori.setOrientation(LinearLayoutManager.VERTICAL);
    rvAllNews.setLayoutManager(kategori);
  }

}
