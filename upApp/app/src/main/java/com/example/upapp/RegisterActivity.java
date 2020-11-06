package com.example.upapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final RequestQueue queue = Volley.newRequestQueue(this);

        final EditText name = findViewById(R.id.txt_name);
        final EditText last_name = findViewById(R.id.txt_lastname);
        final EditText email = findViewById(R.id.txt_email);
        final EditText password = findViewById(R.id.txt_password);
        final Button register_button = findViewById(R.id.btn_register);



        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String URL = "http://192.168.1.74/volley_backend/registrar_usuario.php";
                Toast.makeText(RegisterActivity.this, "1", Toast.LENGTH_SHORT).show();
                final String usu_nombre = name.getText().toString();
                final String usu_apellidos = last_name.getText().toString();
                final String usu_email = email.getText().toString();
                final String usu_password = password.getText().toString();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(RegisterActivity.this, "2", Toast.LENGTH_SHORT).show();
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String success = jsonObject.getString("success");
                                    if(success.equals('1')){
                                        Toast.makeText(RegisterActivity.this, "Register success!", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(RegisterActivity.this, "Register error! "+e.toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                                Toast.makeText(RegisterActivity.this, "Register error! "+error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError{
                        Map<String, String> params = new HashMap<>();
                        params.put("usu_email", usu_email);
                        params.put("usu_password", usu_password);
                        params.put("usu_nombres",usu_nombre);
                        params.put("usu_apellidos",usu_apellidos);
                        return params;
                    }
                };

                //RequestQueue requestQueue = Volley.newRequestQueue(this);
                queue.add(stringRequest);
            }
        });

    }
}