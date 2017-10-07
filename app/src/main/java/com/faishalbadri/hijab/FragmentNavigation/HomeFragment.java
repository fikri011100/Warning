package com.faishalbadri.hijab.FragmentNavigation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.faishalbadri.hijab.Adapter.AdapterIsiNew;
import com.faishalbadri.hijab.Adapter.AdapterPopuler;
import com.faishalbadri.hijab.First.Slider.ChildAnimationExample;
import com.faishalbadri.hijab.First.Slider.SliderLayout;
import com.faishalbadri.hijab.Helper.Server;
import com.faishalbadri.hijab.Model.PojoIsiNew;
import com.faishalbadri.hijab.Model.PojoPopuler;
import com.faishalbadri.hijab.Model.PojoSlider;
import com.faishalbadri.hijab.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

    Button btnMoreNew,btnMoreTop,btnMoreHot;
    RecyclerView rvNew,rvTop,rvHot;
    SliderLayout mDemoSlider;
    SwipeRefreshLayout refresh;
    TextView txtMore;
    RequestQueue reqNew,reqTop,reqHot,reqSlider;
    Gson gNew,gTop,gHot,gSlider;
    TextSliderView textSliderView;
    String urlSlider = Server.BASE_URL+"getTbSlider.php";
    String urlNew = Server.BASE_URL+"getTbIsiNew.php";
    String urlTop = Server.BASE_URL+"getTbPopuler.php";
    String urlHot = Server.BASE_URL+"getTbPopuler.php";
    private View rootView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        setView();
        setRefresh();
        setVolley();
        getServer();
        getSlider();
        setOnClickButton();
        setLayout();

        return rootView;
    }

    private void setView() {
        mDemoSlider = (SliderLayout)rootView.findViewById(R.id.slider);
        btnMoreNew = (Button)rootView.findViewById(R.id.btnMoreNew);
        btnMoreHot = (Button)rootView.findViewById(R.id.btnMoreHot);
        btnMoreTop = (Button)rootView.findViewById(R.id.btnMoreTop);
        rvNew = (RecyclerView)rootView.findViewById(R.id.rvNew);
        rvTop = (RecyclerView)rootView.findViewById(R.id.rvTop);
        rvHot = (RecyclerView)rootView.findViewById(R.id.rvHot);
        refresh = (SwipeRefreshLayout)rootView.findViewById(R.id.refresh);
        txtMore = (TextView)rootView.findViewById(R.id.txtMore);
    }

    private void setRefresh() {
        refresh = (SwipeRefreshLayout) rootView.findViewById(R.id.refresh);
        refresh.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh.setRefreshing(false);
                getServer();
            }

        });
    }

    private void getServer() {
        getNew();
        getTop();
        getHot();
    }
    private void setLayout() {

        LinearLayoutManager hot = new LinearLayoutManager(getActivity());
        hot.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvHot.setLayoutManager(hot);

        LinearLayoutManager topTen = new LinearLayoutManager(getActivity());
        topTen.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvTop.setLayoutManager(topTen);

        LinearLayoutManager layoutNew = new LinearLayoutManager(getActivity());
        layoutNew.setOrientation(LinearLayoutManager.VERTICAL);
        rvNew.setLayoutManager(layoutNew);

    }

    private void getHot() {
        StringRequest request = new StringRequest(Request.Method.GET, urlHot, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    if (String.valueOf(new JSONObject(response).getString("msg")).equals("Data Semua Isi Populer")){
                        try {
                            Log.i("Response Data", response);
                            final PojoPopuler populer = gTop.fromJson(response, PojoPopuler.class);
                            final AdapterPopuler adapterPopuler = new AdapterPopuler(populer.getIsi(), getActivity());
                            rvHot.setAdapter(adapterPopuler);

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
        reqHot.add(request);
    }

    private void getTop() {

        StringRequest request = new StringRequest(Request.Method.GET, urlTop, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    if (String.valueOf(new JSONObject(response).getString("msg")).equals("Data Semua Isi Populer")){
                        try {
                            Log.i("Response Data", response);
                            final PojoPopuler populer = gTop.fromJson(response, PojoPopuler.class);
                            final AdapterPopuler adapterPopuler = new AdapterPopuler(populer.getIsi(), getActivity());
                            rvTop.setAdapter(adapterPopuler);

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
        reqTop.add(request);
    }

    private void getNew() {

        StringRequest request = new StringRequest(Request.Method.GET, urlNew, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    if (String.valueOf(new JSONObject(response).getString("msg")).equals("Data Semua Isi")){
                        try {
                            Log.i("Response Data", response);
                            final PojoIsiNew isinew = gNew.fromJson(response, PojoIsiNew.class);
                            final AdapterIsiNew adapterIsiNew = new AdapterIsiNew(isinew.getIsi(),getActivity());
                            rvNew.setAdapter(adapterIsiNew);

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
        reqNew.add(request);

    }

    private void getSlider() {

        StringRequest request = new StringRequest(Request.Method.GET, urlSlider , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    if (String.valueOf(new JSONObject(response).getString("msg")).equals("Data Semua Slider")){
                        try {

                            final PojoSlider slider = gSlider.fromJson(response, PojoSlider.class);
                            Log.i("response",response);
                            for (int a = 0; a < slider.getSlider().size(); a++) {
                                HashMap<String, String> file_maps = new HashMap<String, String>();
                                file_maps.put(slider.getSlider().get(a).getSlider_judul(), Server.BASE_IMG+ slider.getSlider().get(a).getSlider_gambar());

                                for (final String name : file_maps.keySet()) {
                                    textSliderView = new TextSliderView(getActivity());
                                    // initialize a SliderLayout
                                    textSliderView
                                            .description(name)
                                            .image(file_maps.get(name))
                                            .setScaleType(BaseSliderView.ScaleType.CenterCrop)
                                            .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                                                @Override
                                                public void onSliderClick(BaseSliderView slider) {
                                                    Toast.makeText(getActivity(), slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
                                                }
                                            });


                                    textSliderView.bundle(new Bundle());
                                    textSliderView.getBundle().putString("extra", name);

                                    mDemoSlider.addSlider(textSliderView);
                                }
                                mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
                                mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                                mDemoSlider.setCustomAnimation(new ChildAnimationExample());
                                mDemoSlider.setDuration(2000);
                                mDemoSlider.addOnPageChangeListener(getActivity());
                            }

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }else {
                        Toast.makeText(getActivity().getApplicationContext(),"Check Connection",Toast.LENGTH_LONG).show();
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
        reqSlider.add(request);
    }

    private void setVolley() {

        reqNew = Volley.newRequestQueue(getActivity());
        GsonBuilder gbNew = new GsonBuilder();
        gNew = gbNew.create();

        reqTop = Volley.newRequestQueue(getActivity());
        GsonBuilder gbTop = new GsonBuilder();
        gTop = gbTop.create();

        reqHot = Volley.newRequestQueue(getActivity());
        GsonBuilder gbHot = new GsonBuilder();
        gHot = gbHot.create();

        reqSlider = Volley.newRequestQueue(getActivity());
        GsonBuilder gbSlider = new GsonBuilder();
        gSlider = gbSlider.create();

    }

    private void setOnClickButton() {

        txtMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "New", Toast.LENGTH_SHORT).show();
            }
        });

        btnMoreNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "New", Toast.LENGTH_SHORT).show();
            }
        });

        btnMoreHot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Hot", Toast.LENGTH_SHORT).show();
            }
        });

        btnMoreTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Top", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
