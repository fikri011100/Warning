package com.faishalbadri.hijab.First;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract.Profile;
import android.support.design.widget.NavigationView;
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
import com.faishalbadri.hijab.ActivityList.ListVideoActivity;
import com.faishalbadri.hijab.FragmentNavigation.HomeFragment;
import com.faishalbadri.hijab.FragmentNavigation.KategoriFragment;
import com.faishalbadri.hijab.Helper.SessionManager;

import com.faishalbadri.hijab.KritikSaran;
import com.faishalbadri.hijab.ProfileActivity;
import com.faishalbadri.hijab.R;
import com.faishalbadri.hijab.ActivityList.SearchActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import java.util.HashMap;


public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    MenuInflater inflater;
    MenuItem searchItem;
    SearchView searchView;
    String key,set;
    SessionManager sesi;
    AdView adHome;
    private ImageView imgUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        adHome = (AdView)findViewById(R.id.ad_home);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);

        setFirstFragment();
        sesi = new SessionManager(getApplicationContext());
        HashMap<String, String> user = sesi.getUserDetails();
        String name = user.get(SessionManager.kunci_email);
        Log.i("nama",name);
        setTitle("Hijab News");

        AdRequest request = new AdRequest.Builder().build();
        adHome.loadAd(request);
    }

    private void setFirstFragment() {
        set = getIntent().getStringExtra("set");
        if (set.equals("kat")){
            KategoriFragment kategoriFragment = new KategoriFragment();
            kategoriFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().replace(R.id.container, kategoriFragment, "KategoriFragment").commit();
            getSupportFragmentManager().popBackStack();
            setTitle("Kategori");
        }else {
            HomeFragment home = new HomeFragment();
            home.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().replace(R.id.container, home, "HomeFragment").commit();
            getSupportFragmentManager().popBackStack();
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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
            KategoriFragment kategoriFragment = new KategoriFragment();
            kategoriFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().replace(R.id.container, kategoriFragment, "KategoriFragment").commit();
            getSupportFragmentManager().popBackStack();
            setTitle("Kategori");
        } else if (id == R.id.video) {
            startActivity(new Intent(getApplicationContext(), ListVideoActivity.class));

        } else if (id == R.id.kirimartikelmu) {
          startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
        } else if (id == R.id.kritiksaran) {
          startActivity(new Intent(getApplicationContext(), KritikSaran.class));
        } else if (id == R.id.logout) {
            sesi.logout();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
