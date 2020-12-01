package com.example.upapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    TextView tvid,tvname,tvemail,tvcontact,tvaddress;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        //Initializing Views
        tvid = findViewById(R.id.txtid);
        tvname = findViewById(R.id.txtname);
        tvemail = findViewById(R.id.txtemail);
        tvcontact = findViewById(R.id.txcontact);
        tvaddress = findViewById(R.id.txtaddress);

        Intent intent =getIntent();
        position = intent.getExtras().getInt("position");

        tvid.setText("ID: "+ViewUserActivity.userArrayList.get(position).getId());
        tvname.setText("Name: "+ViewUserActivity.userArrayList.get(position).getNombre());
        tvemail.setText("Email: "+ViewUserActivity.userArrayList.get(position).getEmail());
        tvcontact.setText("Apellido: "+ViewUserActivity.userArrayList.get(position).getApellido());
        tvaddress.setText("password: "+ViewUserActivity.userArrayList.get(position).getPassword());





    }
}
