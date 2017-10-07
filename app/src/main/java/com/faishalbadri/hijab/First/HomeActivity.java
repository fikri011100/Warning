package com.faishalbadri.hijab.First;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract.Profile;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.faishalbadri.hijab.ActivityList.ListVideoActivity;
import com.faishalbadri.hijab.FragmentNavigation.HomeFragment;
import com.faishalbadri.hijab.FragmentNavigation.KategoriFragment;
import com.faishalbadri.hijab.Helper.Server;
import com.faishalbadri.hijab.Helper.SessionManager;

import com.faishalbadri.hijab.KritikSaran;
import com.faishalbadri.hijab.ProfileActivity;
import com.faishalbadri.hijab.R;
import com.faishalbadri.hijab.ActivityList.SearchActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class HomeActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

    MenuInflater inflater;
    MenuItem searchItem;
    SearchView searchView;
    String key,emailUser,id,imgUser,username;
    SessionManager sesi;
    AdView adHome;
    private ConstraintLayout linearLayout;
    private RequestQueue reqLogin;
    private ImageView imageUseer;
    private TextView txtUsername;
    String name;
    Gson gsonLogin;
    String URLLogin = Server.BASE_URL+"getUser.php";
    Bundle bundleKategori;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bundleKategori = new Bundle();
        adHome = (AdView)findViewById(R.id.ad_home);
        emailUser = getIntent().getStringExtra("email");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
        linearLayout = (ConstraintLayout) header.findViewById(R.id.view_header);
        imageUseer = (ImageView) header.findViewById(R.id.img_view_header);
        txtUsername = (TextView)header.findViewById(R.id.txtNamaUser);
        setFirstFragment();
        sesi = new SessionManager(getApplicationContext());
        HashMap<String, String> user = sesi.getUserDetails();
        name = user.get(SessionManager.kunci_email);
        Log.i("nama",name);
        setTitle("Hijab News");
        getGsonLogin();
        getLoginPojo();
        AdRequest request = new AdRequest.Builder().build();
        adHome.loadAd(request);
        intentProfile();
    }


    private void getGsonLogin() {
        reqLogin = Volley.newRequestQueue(HomeActivity.this);
        GsonBuilder gbNew = new GsonBuilder();
        gsonLogin = gbNew.create();
    }

    private void getLoginPojo() {
        reqLogin = Volley.newRequestQueue(HomeActivity.this);
        StringRequest request = new StringRequest(Method.POST, URLLogin, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("response ", response);
                try {
                    if (String.valueOf(new JSONObject(response).getString("msg")).equals("Data Semua User")){
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("user");
                            for (int a = 0; a < jsonArray.length(); a++) {
                                JSONObject json = jsonArray.getJSONObject(a);
                                HashMap<String, String> map = new HashMap<String, String>();
                                map.put("id_user", json.getString("id_user"));
                                map.put("username", json.getString("username"));
                                map.put("img_user", json.getString("img_user"));
                                id = json.getString("id_user");
                                imgUser = json.getString("img_user");
                                username = json.getString("username");
                                RequestOptions options = new RequestOptions().circleCrop().format(
                                    DecodeFormat.PREFER_ARGB_8888).override(100,100);
                                Glide.with(getApplicationContext())
                                    .load(Server.BASE_IMG + imgUser)
                                    .apply(options)
                                    .into(imageUseer);

                                setUserProfile();
                            }
                        } catch (JSONException e) {
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
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("email", name);
                return param;
            }
        };
        reqLogin.add(request);
    }

    private void setUserProfile() {
        txtUsername.setText(username);
    }

    private void intentProfile() {
        linearLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ProfileActivity.class).putExtra("email",id));
            }
        });
    }

    private void setFirstFragment() {
        HomeFragment home = new HomeFragment();
        home.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().replace(R.id.container, home, "HomeFragment").commit();
        getSupportFragmentManager().popBackStack();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        int count = getFragmentManager().getBackStackEntryCount();
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        searchItem = menu.findItem(R.id.search);
        searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("Judul / Kategori / penulis");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                key = query;
                Intent a = new Intent(getApplicationContext(), SearchActivity.class);
                a.putExtra("key", key);
                startActivity(a);
                finish();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }

        });


        return super.onCreateOptionsMenu(menu);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            HomeFragment home = new HomeFragment();
            home.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().replace(R.id.container, home, "HomeFragment").commit();
            getSupportFragmentManager().popBackStack();
            setTitle("Hijab News");
            searchView.setVisibility(View.VISIBLE);
        } else if (id == R.id.pinkyhijab) {

        } else if (id == R.id.allnews) {

        } else if (id == R.id.topnews) {

        } else if (id == R.id.video) {
            startActivity(new Intent(getApplicationContext(), ListVideoActivity.class));
        } else if (id == R.id.event) {

        } else if (id == R.id.ebook) {

        } else if (id == R.id.vote) {

        } else if (id == R.id.muslimahinspiratif) {
            bundleKategori.putString("id","1");
            setTitle("Muslimah Inspiratif");
            kategoriFragment();
        } else if (id == R.id.muslimahworld) {
            bundleKategori.putString("id","2");
            setTitle("Muslimah World");
            kategoriFragment();
        } else if (id == R.id.community) {
            bundleKategori.putString("id","3");
            setTitle("Community");
            kategoriFragment();
        } else if (id == R.id.globalmuslim) {
            bundleKategori.putString("id","4");
            setTitle("Global Muslim");
            kategoriFragment();
        } else if (id == R.id.beauty) {
            bundleKategori.putString("id","5");
            setTitle("Beauty");
            kategoriFragment();
        } else if (id == R.id.fashion) {
            bundleKategori.putString("id","6");
            setTitle("Fashion");
            kategoriFragment();
        } else if (id == R.id.religi) {
            bundleKategori.putString("id","7");
            setTitle("Religi");
            kategoriFragment();
        } else if (id == R.id.lifestyle) {
            bundleKategori.putString("id","8");
            setTitle("Lifestyle");
            kategoriFragment();
        } else if (id == R.id.travel) {
            bundleKategori.putString("id","9");
            setTitle("Travel");
            kategoriFragment();
        } else if (id == R.id.cerpen) {
            bundleKategori.putString("id","10");
            setTitle("Cerpen");
            kategoriFragment();
        } else if (id == R.id.cerbung) {
            bundleKategori.putString("id","11");
            setTitle("Cerbung");
            kategoriFragment();
        } else if (id == R.id.kisahmu) {
            bundleKategori.putString("id","12");
            setTitle("Kisahmu");
            kategoriFragment();
        } else if (id == R.id.celeb) {
            bundleKategori.putString("id","13");
            setTitle("Selebrity");
            kategoriFragment();
        } else if (id == R.id.motivation) {
            bundleKategori.putString("id","14");
            setTitle("Motivasi");
            kategoriFragment();
        } else if (id == R.id.financial) {
            bundleKategori.putString("id","15");
            setTitle("Financial");
            kategoriFragment();
        } else if (id == R.id.kesehatan) {
            bundleKategori.putString("id","16");
            setTitle("Kesehatan");
            kategoriFragment();
        } else if (id == R.id.mfb) {
            bundleKategori.putString("id","17");
            setTitle("Musik, Film, Buku");
            kategoriFragment();
        } else if (id == R.id.sport) {
            bundleKategori.putString("id","18");
            setTitle("Sport");
            kategoriFragment();
        } else if (id == R.id.tech) {
            bundleKategori.putString("id","19");
            setTitle("Teknologi");
            kategoriFragment();
        } else if (id == R.id.konsultasi) {
            bundleKategori.putString("id","20");
            setTitle("Konsultasi");
            kategoriFragment();
        } else if (id == R.id.eventupdate) {
            bundleKategori.putString("id","21");
            setTitle("Event Update");
            kategoriFragment();
        } else if (id == R.id.resep) {
            bundleKategori.putString("id","22");
            setTitle("Resep");
            kategoriFragment();
        } else if (id == R.id.kuliner) {
            bundleKategori.putString("id","23");
            setTitle("Kuliner");
            kategoriFragment();
        } else if (id == R.id.referensi) {
            bundleKategori.putString("id","24");
            setTitle("Referensi");
            kategoriFragment();
        } else if (id == R.id.kirimartikelmu) {
            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
        } else if (id == R.id.kritiksaran) {
            startActivity(new Intent(getApplicationContext(), KritikSaran.class));
        } else if (id == R.id.about) {

        } else if (id == R.id.share) {

        } else if (id == R.id.logout) {
            sesi.logout();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void kategoriFragment() {
        KategoriFragment kategoriFragment = new KategoriFragment();
        kategoriFragment.setArguments(bundleKategori);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, kategoriFragment, "KategoriFragment").commit();
        getSupportFragmentManager().popBackStack();
    }

}
