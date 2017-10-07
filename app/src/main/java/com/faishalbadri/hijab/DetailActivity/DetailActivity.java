package com.faishalbadri.hijab.DetailActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.faishalbadri.hijab.Adapter.AdapterMoreNewsDetail;
import com.faishalbadri.hijab.Helper.Server;
import com.faishalbadri.hijab.Model.PojoPerKategori;
import com.faishalbadri.hijab.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DetailActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private String judul,gambar,keterangan,tglUpload,id_isi,id_kategori;
    private RequestQueue reqDetail;
    Gson gsonDetail;
    private String urlDetail = Server.BASE_URL+"getTbIsiPerKategori.php";

    private WebView DetailwebView;
    private ImageView DetailImageView;
    private TextView textDetail;
    private RecyclerView recyclerDetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initView();
        setSupportActionBar(toolbar);
        TangkapId();
        setDetail();
        setVolleyDetail();
        setLayoutDetail();
        getMoreNews();
    }


    private void setVolleyDetail() {
        reqDetail = Volley.newRequestQueue(getApplicationContext());
        GsonBuilder gbNew = new GsonBuilder();
        gsonDetail = gbNew.create();
    }

    private void setLayoutDetail() {
        LinearLayoutManager det = new LinearLayoutManager(DetailActivity.this);
        det.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerDetail.setLayoutManager(det);
    }

    private void setDetail() {
        textDetail.setText(judul);
        textDetail.setSelected(true);
        DetailwebView.loadData(keterangan,"text/html","uutf/-8");
        Glide.with(this)
                .load(Server.BASE_IMG+gambar)
                .placeholder(R.mipmap.ic_launcher)
                .crossFade()
                .into(DetailImageView);
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        DetailwebView = (WebView)findViewById(R.id.wbDetail);
        DetailImageView = (ImageView)findViewById(R.id.imgDetail);
        textDetail = (TextView) findViewById(R.id.textDetail);
        recyclerDetail = (RecyclerView)findViewById(R.id.recyclerDetail);
    }


    private void TangkapId() {
        id_isi = getIntent().getStringExtra("id_isi");
        id_kategori = getIntent().getStringExtra("id_kategori");
        judul = getIntent().getStringExtra("judul");
        gambar = getIntent().getStringExtra("gambar");
        keterangan = getIntent().getStringExtra("keterangan");
        tglUpload = getIntent().getStringExtra("tglUpload");
    }

    private void getMoreNews() {
        StringRequest request = new StringRequest(Request.Method.POST, urlDetail, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    if (String.valueOf(new JSONObject(response).getString("msg")).equals("Data Semua Isi")){
                        try {
                            Log.i("Response Data", response);
                            final PojoPerKategori perkat = gsonDetail.fromJson(response, PojoPerKategori.class);
                            final AdapterMoreNewsDetail adapterMoreNewsDetail = new AdapterMoreNewsDetail(perkat.getIsi(), getApplicationContext());
                            recyclerDetail.setAdapter(adapterMoreNewsDetail);

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }else {
                        Toast.makeText(getApplicationContext().getApplicationContext(),"Database Is Null",Toast.LENGTH_LONG).show();
                    }
                }catch (JSONException e){

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext().getApplicationContext(),"Check Your Internet Connection",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("id_kategori",id_kategori);
                return params;
            }
        };
        reqDetail.add(request);
    }
}
