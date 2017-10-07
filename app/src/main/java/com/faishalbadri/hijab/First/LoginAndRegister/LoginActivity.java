package com.faishalbadri.hijab.First.LoginAndRegister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.faishalbadri.hijab.First.HomeActivity;
import com.faishalbadri.hijab.Helper.Server;
import com.faishalbadri.hijab.Helper.SessionManager;
import com.faishalbadri.hijab.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private MaterialEditText edtEmailLogin, edtPasswordLogin;
    private TextView textRegister;
    private Button btnLogin;
    SessionManager sessionManager;
    String URL = Server.BASE_URL + "login.php";
    private RequestQueue reqLogin;
    Gson gsonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setupView();
        sessionManager = new SessionManager(getApplicationContext());
        textRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
    }

    private void loginUser() {
        edtEmailLogin.setError(null);
        edtPasswordLogin.setError(null);
        /*check kebaradan teks*/
        if (Server.isEmpty(edtEmailLogin)) {
            edtEmailLogin.setError("Email masih kosong");
            edtEmailLogin.requestFocus();
        } else if (Server.isEmpty(edtPasswordLogin)) {
            edtPasswordLogin.setError("Password masih kosong");
            edtPasswordLogin.requestFocus();
        } else {
            lanjutinaja();
        }
    }

    private void lanjutinaja() {
        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    if (String.valueOf(new JSONObject(response).getString("msg")).equals("login berhasil")){
                        try {
                            sessionManager.createSession(edtEmailLogin.getText().toString());
                            startActivity(new Intent(getApplicationContext(),HomeActivity.class).putExtra("set","first"));
                            finish();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }else {

                    }
                }catch (JSONException e){

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email",edtEmailLogin.getText().toString());
                params.put("password",edtPasswordLogin.getText().toString());
                return params;
            }
        };
        reqLogin.add(request);
    }

    private void setupView() {
        edtEmailLogin = (MaterialEditText)findViewById(R.id.edt_email_login);
        edtPasswordLogin = (MaterialEditText)findViewById(R.id.edt_password_login);
        textRegister = (TextView) findViewById(R.id.txt_register);
        btnLogin = (Button)findViewById(R.id.btn_login);
        reqLogin = Volley.newRequestQueue(getApplicationContext());
        GsonBuilder gbLogin = new GsonBuilder();
        gsonLogin = gbLogin.create();
    }
}
