package com.example.upapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Edit_Activity extends AppCompatActivity {

    EditText edId,edName,edApellido,edEmail,edContraseña;
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_);

        edId = findViewById(R.id.ed_id);
        edName = findViewById(R.id.ed_name);
        edApellido = findViewById(R.id.ed_apellido);
        edEmail = findViewById(R.id.ed_email);
        edContraseña = findViewById(R.id.ed_password);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");


        edId.setText(ViewUserActivity.userArrayList.get(position).getId());
        edName.setText(ViewUserActivity.userArrayList.get(position).getNombre());
        edApellido.setText(ViewUserActivity.userArrayList.get(position).getEmail());
        edEmail.setText(ViewUserActivity.userArrayList.get(position).getApellido());
        edContraseña.setText(ViewUserActivity.userArrayList.get(position).getPassword());




    }

    public void btn_updateData(View view) {

        final String name = edName.getText().toString();
        final String email = edEmail.getText().toString();
        final String apellido = edApellido.getText().toString();
        final String contraseña = edContraseña.getText().toString();
        final String id = edId.getText().toString();





        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating....");
        progressDialog.show();

        StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.1.66/volley_backend/update.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(Edit_Activity.this, response, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),ViewUserActivity.class));
                        finish();
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Edit_Activity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();

                params.put("id",id);
                params.put("nombre",name);
                params.put("apellido",apellido);
                params.put("email",email);
                params.put("contraseña",contraseña);
                Log.e("param",params.toString());
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Edit_Activity.this);
        requestQueue.add(request);





    }
}
