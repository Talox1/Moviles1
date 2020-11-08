package com.example.upapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.example.upapp.ui.login.ProfileActivity;

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
        final EditText last_name = findViewById(R.id.txt_apellido);
        final EditText email = findViewById(R.id.txt_email);
        final EditText password = findViewById(R.id.txt_password);
        final Button register_button = findViewById(R.id.btn_register);



       register_button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               String URL = "http://192.168.1.66/volley_backend/registrar_usuario.php";



               StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                   @Override
                   public void onResponse(String response) {

                       if (!response.isEmpty()) {
                           Intent profileActivity = new Intent(getApplicationContext(), ProfileActivity.class);
                           startActivity(profileActivity);
                       }else{
                           Toast.makeText(getApplicationContext(), "Ocurrio un error", Toast.LENGTH_LONG).show();
                       }
                   }
               }, new Response.ErrorListener() {
                   @Override
                   public void onErrorResponse(VolleyError error) {
                       Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                   }
               }){
                   @Override
                   protected Map<String, String> getParams() throws AuthFailureError {

                       final String usu_nombre = name.getText().toString();
                       final String usu_apellidos = last_name.getText().toString();
                       final String usu_email = email.getText().toString();
                       final String usu_password = password.getText().toString();

                       Map<String, String> parametros = new HashMap<String, String>();
                       parametros.put("usuario",usu_email );
                       parametros.put("password", usu_password);
                       parametros.put("nombres", usu_nombre);
                       parametros.put("apellidos", usu_apellidos);
                       return parametros;
                   }
               };
               //RequestQueue requestQueue = Volley.newRequestQueue(this);
               queue.add(stringRequest);
           }
       });

    }
}