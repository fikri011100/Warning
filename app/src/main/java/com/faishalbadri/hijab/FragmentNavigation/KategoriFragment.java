package com.faishalbadri.hijab.FragmentNavigation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.faishalbadri.hijab.Adapter.AdapterKategori;
import com.faishalbadri.hijab.Adapter.AdapterPopuler;
import com.faishalbadri.hijab.Helper.Server;
import com.faishalbadri.hijab.Model.PojoKategori;
import com.faishalbadri.hijab.Model.PojoPopuler;
import com.faishalbadri.hijab.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class KategoriFragment extends Fragment {


    public KategoriFragment() {
        // Required empty public constructor
    }

    private View rootView;
    private SwipeRefreshLayout refresh;
    private RecyclerView rvKategori;
    private RequestQueue reqKategori;
    private Gson gKategori;
    private String urlKategori = Server.BASE_URL+"getTbKategori.php";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView =  inflater.inflate(R.layout.fragment_kategori, container, false);
        setView();
        setLayout();
        setRefresh();
        getKategori();
        return rootView;
    }

    private void getKategori() {
        StringRequest request = new StringRequest(Request.Method.GET, urlKategori, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    if (String.valueOf(new JSONObject(response).getString("msg")).equals("Data Semua Kategori")){
                        try {
                            Log.i("Response Data", response);
                            final PojoKategori kategori = gKategori.fromJson(response, PojoKategori.class);
                            final AdapterKategori adapterKategori = new AdapterKategori(kategori.getKategori(), getActivity());
                            rvKategori.setAdapter(adapterKategori);

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }else {
                        Toast.makeText(getActivity().getApplicationContext(),"Database Is Null",Toast.LENGTH_LONG).show();
                    }
                }catch (JSONException e){

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity().getApplicationContext(),"Check Your Internet Connection",Toast.LENGTH_LONG).show();
            }
        });
        reqKategori.add(request);
    }

    private void setRefresh() {
        refresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh.setRefreshing(false);
                getKategori();
            }

        });
    }

    private void setLayout() {
        GridLayoutManager gridKategori = new GridLayoutManager(getActivity(), 3);
        gridKategori.setOrientation(GridLayoutManager.VERTICAL);
        rvKategori.setLayoutManager(gridKategori);
    }

    private void setView() {
        refresh = (SwipeRefreshLayout)rootView.findViewById(R.id.refreshKategori);
        rvKategori = (RecyclerView)rootView.findViewById(R.id.rvKategori);
        reqKategori = Volley.newRequestQueue(getActivity());
        GsonBuilder gbKategori = new GsonBuilder();
        gKategori = gbKategori.create();
    }

}
