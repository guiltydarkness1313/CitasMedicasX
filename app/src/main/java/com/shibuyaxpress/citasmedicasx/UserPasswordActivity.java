package com.shibuyaxpress.citasmedicasx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;


public class UserPasswordActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText inputPassword,inputConfirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_password);
        toolbar=findViewById(R.id.toolbar_password);
        inputConfirm=findViewById(R.id.input_confirm);
        inputPassword=findViewById(R.id.input_password);

        toolbar.setTitle("Crear Contraseña");
        setSupportActionBar(toolbar);


    }
}
