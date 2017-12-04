package com.shibuyaxpress.citasmedicasx.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.shibuyaxpress.citasmedicasx.R;
import com.shibuyaxpress.citasmedicasx.models.Pacientes;
import com.shibuyaxpress.citasmedicasx.models.ResponseMessage;
import com.shibuyaxpress.citasmedicasx.models.ResponseUser;
import com.shibuyaxpress.citasmedicasx.models.Usuarios;
import com.shibuyaxpress.citasmedicasx.services.ApiService;
import com.shibuyaxpress.citasmedicasx.services.ApiServiceGenerator;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserPasswordActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText inputPassword,inputConfirm;

    private static final String TAG = UserPasswordActivity.class.getSimpleName();

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

    public void RegisterUserFinal(View view){
        if(inputConfirm.getText().toString().equals(inputPassword.getText().toString())&&!inputPassword.getText().toString().isEmpty()&&!inputConfirm.getText().toString().isEmpty()){
            String password=inputPassword.getText().toString();


            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault());
            String strDate = sdf.format(c.getTime());
            Usuarios.getInstance().setRegistro_fecha(strDate);
            Usuarios.getInstance().setTipo("P");
            Usuarios.getInstance().setClave(password);

            String username=Usuarios.getInstance().getNombre();
            String pass=Usuarios.getInstance().getClave();
            String fechaReg=Usuarios.getInstance().getRegistro_fecha();
            String typeUser=Usuarios.getInstance().getTipo();
            //
            ApiService service=ApiServiceGenerator.createService(ApiService.class);

            Call<ResponseUser>call=null;

            call=service.createUser(username,pass,typeUser,fechaReg);

            call.enqueue(new Callback<ResponseUser>() {
                @Override
                public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
                    int statusCode = response.code();
                    Log.d(TAG, "HTTP status code User REG: " + statusCode);
                    if(response.isSuccessful()){
                        ResponseUser u= response.body();
                        //assert u != null;
                        Log.d(TAG,"el id del prro es:"+u.getData().getId());
                        Pacientes.getInstance().setUsuario_id(Integer.parseInt(u.getData().getId()));
                    }
                }

                @Override
                public void onFailure(Call<ResponseUser> call, Throwable t) {
                    Log.d(TAG,"nulos errores");
                }
            });
            String nombre=Pacientes.getInstance().getNombre();
            String ap_paterno=Pacientes.getInstance().getApellido_paterno();
            String ap_materno=Pacientes.getInstance().getApellido_materno();
            String tipo_doc=Pacientes.getInstance().getTipo_doc();
            int num_doc=Pacientes.getInstance().getNum_doc();
            String fechaNac=Pacientes.getInstance().getFecha_nacimiento();
            String nacionalidad=Pacientes.getInstance().getNacionalidad();
            String direccion=Pacientes.getInstance().getDireccion();
            String postal=Pacientes.getInstance().getCod_postal();
            String ciudad=Pacientes.getInstance().getCiudad();
            String provincia=Pacientes.getInstance().getProvincia();
            int telefono=Pacientes.getInstance().getTelefono();
            int celular=Pacientes.getInstance().getCelular();
            String correo=Pacientes.getInstance().getCorreo();
            String genero=Pacientes.getInstance().getGenero();
            int usuarioId=Pacientes.getInstance().getUsuario_id();

            if(!String.valueOf(usuarioId).isEmpty()){
                ApiService service2=ApiServiceGenerator.createService(ApiService.class);
                Call<ResponseMessage>call1=null;
            call1=service2.createPatient(nombre,ap_paterno,ap_materno,tipo_doc,num_doc,fechaNac,nacionalidad,direccion,postal,ciudad,provincia,telefono,celular,correo,genero,usuarioId);

            call1.enqueue(new Callback<ResponseMessage>() {
                @Override
                public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                    Log.d(TAG,"REGISTRO PACIENTE: esperando resultado");
                    if(response.isSuccessful()){
                        ResponseMessage x=response.body();
                        Log.d(TAG,"ResponseMessage"+x);
                        Toast.makeText(UserPasswordActivity.this,x.getMessage(),Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);

                    }
                }

                @Override
                public void onFailure(Call<ResponseMessage> call, Throwable t) {
                    Log.e(TAG, "onFailure: " + t.toString());
                    Toast.makeText(UserPasswordActivity.this, "PACIENTE REG: "+t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
            }else{
                Toast.makeText(UserPasswordActivity.this,"no hay id disponible de usuario",Toast.LENGTH_SHORT).show();
            }

        }else{
            inputPassword.setError("ingrese la misma contraseña en ambos campos");
        }
    }
}
