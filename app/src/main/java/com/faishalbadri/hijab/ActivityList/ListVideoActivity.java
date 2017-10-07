package com.faishalbadri.hijab.ActivityList;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.faishalbadri.hijab.Adapter.AdapterListVideo;
import com.faishalbadri.hijab.Adapter.AdapterPopuler;
import com.faishalbadri.hijab.Helper.Server;
import com.faishalbadri.hijab.Model.PojoPopuler;
import com.faishalbadri.hijab.Model.PojoVideo;
import com.faishalbadri.hijab.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONException;
import org.json.JSONObject;

public class ListVideoActivity extends AppCompatActivity {

  private RequestQueue reqListVideo;
  private RecyclerView rvListVideo;
  private SwipeRefreshLayout swipeListVideo;
  public String urlListVideo = Server.BASE_URL+"getVideo.php";
  private Gson gson;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_list_video);
    setView();
    setRefresh();
    setLayoutManager();
    setVolley();
    loadData();
  }

  private void setLayoutManager() {
    LinearLayoutManager hot = new LinearLayoutManager(ListVideoActivity.this);
    hot.setOrientation(LinearLayoutManager.VERTICAL);
    rvListVideo.setLayoutManager(hot);
  }

  private void setView() {
    rvListVideo = (RecyclerView)findViewById(R.id.rvListVideo);
    swipeListVideo = (SwipeRefreshLayout)findViewById(R.id.swipeListVideo);
  }

  private void setRefresh() {
    swipeListVideo.setColorSchemeResources(
        android.R.color.holo_blue_bright,
        android.R.color.holo_green_light,
        android.R.color.holo_orange_light,
        android.R.color.holo_red_light);
    swipeListVideo.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override
      public void onRefresh() {
        swipeListVideo.setRefreshing(false);
        loadData();
      }

    });
  }

  private void setVolley() {
    reqListVideo = Volley.newRequestQueue(ListVideoActivity.this);
    GsonBuilder gbNew = new GsonBuilder();
    gson = gbNew.create();
  }

  private void loadData() {
    StringRequest request = new StringRequest(Request.Method.GET, urlListVideo, new Response.Listener<String>() {
      @Override
      public void onResponse(String response) {
        try {
          if (String.valueOf(new JSONObject(response).getString("msg")).equals("Data Semua Video")){
            try {
              Log.i("Response Data", response);
              final PojoVideo populer = gson.fromJson(response, PojoVideo.class);
              final AdapterListVideo adapterListVideo = new AdapterListVideo(populer.getVideo(), ListVideoActivity.this);
              rvListVideo.setAdapter(adapterListVideo);

            }catch (Exception e){
              e.printStackTrace();
            }
          }else {
            Toast
                .makeText(getApplicationContext().getApplicationContext(),"Database Is Null",Toast.LENGTH_LONG).show();
          }
        }catch (JSONException e){

        }

      }
    }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplicationContext().getApplicationContext(),"Check Your Internet Connection",Toast.LENGTH_LONG).show();
      }
    });
    reqListVideo.add(request);
  }
}
