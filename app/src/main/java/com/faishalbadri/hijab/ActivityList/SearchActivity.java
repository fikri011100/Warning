package com.faishalbadri.hijab.ActivityList;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.faishalbadri.hijab.Adapter.AdapterIsiNew;
import com.faishalbadri.hijab.Adapter.AdapterPopuler;
import com.faishalbadri.hijab.First.HomeActivity;
import com.faishalbadri.hijab.Helper.Server;
import com.faishalbadri.hijab.Model.PojoIsi;
import com.faishalbadri.hijab.Model.PojoIsiNew;
import com.faishalbadri.hijab.Model.PojoPopuler;
import com.faishalbadri.hijab.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class SearchActivity extends AppCompatActivity {

  String key;
  RecyclerView rvSearch;
  String url = Server.BASE_URL + "searchAll.php";
  RequestQueue requestQueue;
  Gson gson;
  SwipeRefreshLayout refresh;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_search);
    getIntentSearch();
    setView();
    getSearch();


  }

  private void getSearch() {
    StringRequest request = new StringRequest(Method.POST, url, new Response.Listener<String>() {
      @Override
      public void onResponse(String response) {
        try {
          if (String.valueOf(new JSONObject(response).getString("msg"))
              .equals("Data Semua Search")) {
            try {
              Log.i("Response Data", response);
              final PojoIsiNew search = gson.fromJson(response, PojoIsiNew.class);
              final AdapterIsiNew adapterPopuler = new AdapterIsiNew(search.getIsi(),
                  SearchActivity.this);
              rvSearch.setAdapter(adapterPopuler);

            } catch (Exception e) {
              e.printStackTrace();
            }
          } else {
            Toast.makeText(getApplicationContext(), "Database Is Null", Toast.LENGTH_LONG).show();
          }
        } catch (JSONException e) {

        }

      }
    }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplicationContext(), "Check Your Internet Connection And Refresh",
            Toast.LENGTH_LONG).show();
      }
    }) {
      @Override
      protected Map<String, String> getParams() throws AuthFailureError {
        Map<String, String> params = new HashMap<String, String>();
        params.put("key", key);
        return params;
      }
    };
    requestQueue.add(request);
  }

  private void setView() {
    requestQueue = Volley.newRequestQueue(this);
    GsonBuilder gsonBuilder = new GsonBuilder();
    gson = gsonBuilder.create();
    rvSearch = (RecyclerView) findViewById(R.id.rvSearch);
    refresh = (SwipeRefreshLayout) findViewById(R.id.refreshSearch);
    LinearLayoutManager search = new LinearLayoutManager(this);
    search.setOrientation(LinearLayoutManager.VERTICAL);
    rvSearch.setLayoutManager(search);
  }


  private void getIntentSearch() {
    key = getIntent().getStringExtra("key");
    setTitle(key);
  }

  @Override
  public void onBackPressed() {
    Intent a = new Intent(getApplicationContext(), HomeActivity.class);
    startActivity(a);
    finish();
    super.onBackPressed();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_search, menu);
    MenuItem searchItem = menu.findItem(R.id.search);
    final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(String query) {
        key = query;
        getSearch();
        searchView.clearFocus();
        setTitle(key);
        return true;
      }

      @Override
      public boolean onQueryTextChange(String newText) {
//                key = newText;
//                getSearch();
        return false;
      }


    });
    return super.onCreateOptionsMenu(menu);
  }
}
