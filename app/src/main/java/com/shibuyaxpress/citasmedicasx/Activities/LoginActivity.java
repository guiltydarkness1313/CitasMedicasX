package com.shibuyaxpress.citasmedicasx.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.shibuyaxpress.citasmedicasx.Models.Usuarios;
import com.shibuyaxpress.citasmedicasx.R;
import com.shibuyaxpress.citasmedicasx.Services.ApiService;
import com.shibuyaxpress.citasmedicasx.Services.ApiServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener{

    private Button inicio,btnLogin;
    private EditText txtEmail,txtUsername,txtPassword;
    //?
    private static final int RC_SIGN_IN=9001;
    //inicio de api de google por la parte cliente
    private GoogleApiClient clienteGoogle;

    public String mFullName,mEmail,imagenperfil;
    public String UIDgo;

    public Intent launcher;

    private String BASE_URL ="http://54.149.168.221/";

    //variable firebase
    FirebaseAuth mfirebaseAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    //etiqueta :V
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inicio=findViewById(R.id.btnInicio);
        inicio.setOnClickListener(this);
        //formulario
        txtUsername=findViewById(R.id.txtUser);
        txtPassword=findViewById(R.id.txtPassword);
        btnLogin=findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=txtUsername.getText().toString();
                String password=txtPassword.getText().toString();
                initializeLogin(username,password);
            }
        });

        //creo que aca se puede filtrar por la entidad
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        clienteGoogle=new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

        //iniciar Firebase Auth
        mfirebaseAuth=FirebaseAuth.getInstance();
        //ver cambios de estado en el inciio de sesiso
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder bob=new AlertDialog.Builder(this);
        bob.setTitle(R.string.app_name);
        bob.setMessage(R.string.question_exit);
        bob.setPositiveButton(R.string.accept, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        bob.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        bob.show();
    }

    @Override
    public void onStart(){
        super.onStart();
        mfirebaseAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop(){
        super.onStop();
        if(mAuthListener!=null){
            mfirebaseAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnInicio:
                signIn();

                break;

        }
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(clienteGoogle);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        Log.d(TAG, "onConnectionFailed:" + connectionResult);
        Toast.makeText(this, "Google Play Services error.", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from
        //   GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                //el registro de firebase fue exitoso, añadir a la BD
                GoogleSignInAccount account = result.getSignInAccount();
                //la cuenta acct pasa como parametro a la funcion
                //creacion de variables para comparar el dominio del correo y obtencion de data
                mFullName = account.getDisplayName();
                mEmail = account.getEmail();
                UIDgo=account.getId();
                imagenperfil= String.valueOf(account.getPhotoUrl());
                //desde que se obtienen los datos de cada usuario en firebase

                launcher=new Intent(LoginActivity.this,MenuActivity.class);

                //usar shared preferences aqui
                launcher.putExtra("nombre",mFullName);
                launcher.putExtra("imagen",imagenperfil);
                launcher.putExtra("email",mEmail);

                firebaseAuthWithGoogle(account);
            }
        } else {
            Toast.makeText(this, "no es un usuario valido", Toast.LENGTH_SHORT).show();
        }

    }


    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGooogle:" + acct.getId());
        //meter progressbar para demostrar carga
        //------------------------------------------------------------------------------------------------------------------//
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mfirebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithCredential", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            //si el usuario le logea correctamente mandar a actividad home
                            startActivity(launcher);
                            Toast.makeText(getApplicationContext(),"Bienvenido "+launcher.getStringExtra("nombre"),Toast.LENGTH_SHORT).show();
                            finish();
                            overridePendingTransition(R.anim.animex_first,R.anim.animex_second);
                        }
                        //--------------------------------------------------//
                        //-------------------------------------------------//
                    }
                });
    }

    //-------------------------Uso de Retrofit----------------------------//
    //validacion del login
    private void initializeLogin(String username,String password){
        ApiService service= ApiServiceGenerator.createService(ApiService.class);
        Call<Usuarios> call=service.Login(username,password);
        call.enqueue(new Callback<Usuarios>() {
            @Override
            public void onResponse(Call<Usuarios> call, Response<Usuarios> response) {
                Usuarios lista=response.body();
                assert lista != null;
                if (response.isSuccessful()) {
                    launcher=new Intent(LoginActivity.this,MenuActivity.class);
                    startActivity(launcher);
                    Toast.makeText(getApplicationContext(),"exito",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),"error en datos",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Usuarios> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"no hay conexión",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void RegisterUser(View view){
        Intent launcher=new Intent(this,RegisterUserActivity.class);
        startActivity(launcher);
    }
}
