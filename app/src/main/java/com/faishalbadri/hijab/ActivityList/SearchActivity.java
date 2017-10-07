package com.faishalbadri.hijab.ActivityList;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.faishalbadri.hijab.First.HomeActivity;
import com.faishalbadri.hijab.R;

public class SearchActivity extends AppCompatActivity {
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getIntentSearch();

    }

    private void getIntentSearch() {
        key = getIntent().getStringExtra("key");
        setTitle(key);
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(getApplicationContext(),HomeActivity.class);
        a.putExtra("set","search");
        startActivity(a);
        finish();
        super.onBackPressed();
    }
}
