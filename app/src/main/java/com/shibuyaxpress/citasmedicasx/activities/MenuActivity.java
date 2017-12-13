package com.shibuyaxpress.citasmedicasx.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.shibuyaxpress.citasmedicasx.MessageFragment;
import com.shibuyaxpress.citasmedicasx.fragments.DatesFragment;
import com.shibuyaxpress.citasmedicasx.fragments.DoctorListFragment;
import com.shibuyaxpress.citasmedicasx.fragments.HorarioFragment;
import com.shibuyaxpress.citasmedicasx.R;
import com.shibuyaxpress.citasmedicasx.models.Pacientes;
import com.shibuyaxpress.citasmedicasx.models.ResponsePatient;
import com.shibuyaxpress.citasmedicasx.models.Usuarios;
import com.shibuyaxpress.citasmedicasx.services.ApiService;
import com.shibuyaxpress.citasmedicasx.services.ApiServiceGenerator;

import de.hdodenhof.circleimageview.CircleImageView;
import me.anwarshahriar.calligrapher.Calligrapher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    GoogleApiClient client;
    CircleImageView myImage;
    TextView txtName,txtEmail;
    private static final String TAG = "MenuActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        llenarDataPaciente();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        txtEmail=navigationView.getHeaderView(0).findViewById(R.id.txt_email_drawer);
        txtName=navigationView.getHeaderView(0).findViewById(R.id.txtNameDrawer);
        myImage=navigationView.getHeaderView(0).findViewById(R.id.imageDrawer);
        ApiService service= ApiServiceGenerator.createService(ApiService.class);
        Call<ResponsePatient>call=service.searchUser(Integer.parseInt(Usuarios.getInstance().getId()));
        call.enqueue(new Callback<ResponsePatient>() {
            @Override
            public void onResponse(Call<ResponsePatient> call, Response<ResponsePatient> response) {
                ResponsePatient x=response.body();
                Log.d(TAG,"cargando data");
                if(response.isSuccessful()){
                    Pacientes[] p=x.getData();
                    Log.d(TAG,x.getData()[0].getNombre().toString()+x.getData()[0].getCorreo());
                    Pacientes.getInstance().setCorreo(p[0].getCorreo());
                    Pacientes.getInstance().setUsuario_id(p[0].getUsuario_id());
                    Pacientes.getInstance().setImagen_perfil(p[0].getImagen_perfil());
                    Pacientes.getInstance().setProvincia(p[0].getProvincia());
                    Pacientes.getInstance().setNum_doc(p[0].getNum_doc());
                    Pacientes.getInstance().setNacionalidad(p[0].getNacionalidad());
                    Pacientes.getInstance().setTipo_doc(p[0].getTipo_doc());
                    Pacientes.getInstance().setGenero(p[0].getGenero());
                    Pacientes.getInstance().setFecha_nacimiento(p[0].getFecha_nacimiento());
                    Pacientes.getInstance().setTelefono(p[0].getTelefono());
                    Pacientes.getInstance().setDireccion(p[0].getDireccion());
                    Pacientes.getInstance().setNombre(p[0].getNombre());
                    Pacientes.getInstance().setApellido_paterno(p[0].getApellido_paterno());
                    Pacientes.getInstance().setApellido_materno(p[0].getApellido_materno());
                    Pacientes.getInstance().setCod_postal(p[0].getCod_postal());
                    Pacientes.getInstance().setCelular(p[0].getCelular());
                    Pacientes.getInstance().setUsuario_id(p[0].getUsuario_id());
                    Pacientes.getInstance().setId(p[0].getId());
                    txtName.setText(Pacientes.getInstance().getNombre()+" "+Pacientes.getInstance().getApellido_paterno());
                    txtEmail.setText(Pacientes.getInstance().getCorreo());
                    Glide.with(MenuActivity.this).load(ApiService.API_BASE_URL+"/images/"+Pacientes.getInstance().getImagen_perfil()).into(myImage);

                }
            }

            @Override
            public void onFailure(Call<ResponsePatient> call, Throwable t) {
                Log.d(TAG,t.getMessage());
            }
        });

        navigationView.setNavigationItemSelectedListener(this);
        //fragment por defecto
        mostrarPantallaSelect(R.id.nav_dates);
    }

    private void llenarDataPaciente(){

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
            CerrarSesion();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        mostrarPantallaSelect(id);
        return true;
    }


    protected void CerrarSesion(){
        final AlertDialog.Builder windows=new AlertDialog.Builder(this);
        windows.setTitle("Cerrar Sesión");
        windows.setMessage("¿Seguro desea salir de la aplicación?");
        windows.setPositiveButton("Salir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Auth.GoogleSignInApi.signOut(client).setResultCallback(new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        //..
                        //Toast.makeText(getApplicationContext(),"Log out",Toast.LENGTH_LONG).show();
                        Intent rocket=new Intent(getApplicationContext(),LoginActivity.class);
                        startActivity(rocket);
                        finish();
                        overridePendingTransition(R.anim.animex_triad,R.anim.animex_four);
                    }
                });
            }
        });
        windows.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //hacer nada

            }
        });

        windows.show();

    }

    protected void onStart() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN)
                .requestEmail()
                .build();
        client = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        client.connect();
        super.onStart();
    }

    private void mostrarPantallaSelect(int item){
        //control de fragments
        //creacion de fragments
        Fragment fragment=null;

        //iniciar los fragmentos
        switch (item){
            case R.id.nav_home:
                getSupportActionBar().setTitle(R.string.Home);
                fragment=new HorarioFragment();
                break;
            case R.id.nav_message:
                getSupportActionBar().setTitle(R.string.messages);
                fragment=new MessageFragment();
                break;
            case R.id.nav_dates:
                getSupportActionBar().setTitle(R.string.medical_dates);
                fragment=new DatesFragment();
                break;
            case R.id.nav_doctor:
                getSupportActionBar().setTitle(R.string.doctor_list);
                fragment=new DoctorListFragment();
                break;
            case R.id.nav_logout:
                CerrarSesion();
                break;
        }

        //remplazo de fragmentos
        if(fragment!=null){
            FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame,fragment);
            ft.commit();
        }

        DrawerLayout drawer=(DrawerLayout)findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

}
