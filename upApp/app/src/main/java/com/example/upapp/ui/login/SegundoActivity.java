package com.example.upapp.ui.login;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.upapp.R;
public class SegundoActivity extends AppCompatActivity {
    private LoginViewModel loginViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segundo);
        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final EditText nombre =  findViewById(R.id.txt_name);
        final EditText apellido =  findViewById(R.id.txt_lastname);
        final EditText telefono = findViewById(R.id.txt_phone);
        final EditText email =  findViewById(R.id.txt_email);

        final Button button_back = findViewById(R.id.btn_back);

        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                button_back.setEnabled(loginFormState.isDataValid());

                if (loginFormState.getNameError() != null) {
                    nombre.setError(getString(loginFormState.getNameError()));
                }
                if (loginFormState.getLastNameError() != null) {
                    apellido.setError(getString(loginFormState.getLastNameError()));
                }
                if (loginFormState.getPhoneError() != null) {
                    telefono.setError(getString(loginFormState.getPhoneError()));
                }
                if (loginFormState.getUsernameError() != null) {
                    email.setError(getString(loginFormState.getUsernameError()));
                }

            }
        });


        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(nombre.getText().toString(),
                        apellido.getText().toString(), telefono.getText().toString(), email.getText().toString());
                //Toast.makeText(getApplicationContext(), s.toString(), Toast.LENGTH_SHORT).show();
            }
        };
        nombre.addTextChangedListener(afterTextChangedListener);
        apellido.addTextChangedListener(afterTextChangedListener);
        telefono.addTextChangedListener(afterTextChangedListener);
        email.addTextChangedListener(afterTextChangedListener);



    }

    public void regresar(View view){
        Intent profileActivity = new Intent(this,ProfileActivity.class);
        startActivity(profileActivity);
    }
}