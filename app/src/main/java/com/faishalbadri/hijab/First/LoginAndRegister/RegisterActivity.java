package com.faishalbadri.hijab.First.LoginAndRegister;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.faishalbadri.hijab.Helper.Server;
import com.faishalbadri.hijab.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private MaterialEditText edtUsername, edtEmail, edtPassword;
    private Button btnRegister;
    private TextView txtIfRegistered;
    private RequestQueue reqRegister;
    Gson gsonRegister;
    String URL = Server.BASE_URL + "register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        txtIfRegistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        edtUsername.setError(null);
        edtPassword.setError(null);
        edtEmail.setError(null);
        if (Server.isEmpty(edtEmail)) {
            edtEmail.setError("Email masih kosong");
            edtEmail.requestFocus();
        } else if (Server.isEmpty(edtPassword)) {
            edtPassword.setError("Password masih kosong");
            edtPassword.requestFocus();
        } else if (Server.isEmpty(edtUsername)) {
            edtUsername.setError("Konfirmasi password masih kosong");
            edtUsername.requestFocus();
        }else {
            StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        if (String.valueOf(new JSONObject(response).getString("msg")).equals("Register berhasil, silakan login")){
                            try {
                                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
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
                    Map<String, String> param = new HashMap<String, String>();
                    param.put("email", edtEmail.getText().toString());
                    param.put("username", edtUsername.getText().toString());
                    param.put("password", edtPassword.getText().toString());
                    return param;
                }
            };
            reqRegister.add(request);
        }
    }

    private void initView() {
        edtEmail = (MaterialEditText)findViewById(R.id.edt_email);
        edtPassword = (MaterialEditText)findViewById(R.id.edt_password);
        edtUsername = (MaterialEditText)findViewById(R.id.edt_username);
        btnRegister = (Button) findViewById(R.id.btn_register);
        txtIfRegistered = (TextView) findViewById(R.id.txt_registered);
        reqRegister = Volley.newRequestQueue(getApplicationContext());
        GsonBuilder gbRegister = new GsonBuilder();
        gsonRegister = gbRegister.create();
    }
}
