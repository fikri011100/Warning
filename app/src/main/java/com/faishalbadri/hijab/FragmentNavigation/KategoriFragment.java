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
import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.faishalbadri.hijab.Adapter.AdapterIsiNew;
import com.faishalbadri.hijab.Helper.Server;
import com.faishalbadri.hijab.Model.PojoIsi;
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
public class KategoriFragment extends Fragment {


  public KategoriFragment() {
    // Required empty public constructor
  }

  RecyclerView rvKategoriFragment;
  private View v;
  RequestQueue reqKategori;
  Gson gKategori;
  String url = Server.BASE_URL + "getTbIsiPerKategori.php";
  String id;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    v = inflater.inflate(R.layout.fragment_kategori, container, false);
    getIntentt();
    setView();
    getPerKat();

    return v;
  }

  private void getPerKat() {
    StringRequest request = new StringRequest(Method.POST, url, new Response.Listener<String>() {
      @Override
      public void onResponse(String response) {
        try {
          if (String.valueOf(new JSONObject(response).getString("msg")).equals("Data Semua Isi")) {
            try {
              Log.i("Response Data", response);
              final PojoIsi search = gKategori.fromJson(response, PojoIsi.class);
              final AdapterIsiNew adapterPopuler = new AdapterIsiNew(search.getIsi(),
                  getActivity());
              rvKategoriFragment.setAdapter(adapterPopuler);

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
        Toast.makeText(getActivity().getApplicationContext(),"Check Your Internet Connection And Refresh",Toast.LENGTH_LONG).show();
      }
    }) {
      @Override
      protected Map<String, String> getParams() throws AuthFailureError {
        Map<String, String> params = new HashMap<String, String>();
        params.put("id_kategori", id);
        return params;
      }
    };
    reqKategori.add(request);
  }

  private void getIntentt() {
    id = getArguments().getString("id");
  }

  private void setView() {
    rvKategoriFragment = (RecyclerView) v.findViewById(R.id.rvKategoriFragment);
    reqKategori = Volley.newRequestQueue(getActivity());
    GsonBuilder gbKategori = new GsonBuilder();
    gKategori = gbKategori.create();
    LinearLayoutManager kategori = new LinearLayoutManager(getActivity());
    kategori.setOrientation(LinearLayoutManager.VERTICAL);
    rvKategoriFragment.setLayoutManager(kategori);
  }


}
