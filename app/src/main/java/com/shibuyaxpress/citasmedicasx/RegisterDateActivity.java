package com.shibuyaxpress.citasmedicasx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.shibuyaxpress.citasmedicasx.adapters.DoctorAdapter;
import com.shibuyaxpress.citasmedicasx.adapters.DoctorSelectedAdapter;
import com.shibuyaxpress.citasmedicasx.fragments.DoctorListFragment;
import com.shibuyaxpress.citasmedicasx.models.Medicos;
import com.shibuyaxpress.citasmedicasx.services.ApiService;
import com.shibuyaxpress.citasmedicasx.services.ApiServiceGenerator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterDateActivity extends AppCompatActivity {
    private RecyclerView recicler;
    private LinearLayoutManager manager;
    private EditText fecha;
    private EditText descripcion;
    private static final String TAG = RegisterDateActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_date);
        recicler=findViewById(R.id.reciclador_doctores);
        manager=new LinearLayoutManager(this);
        fecha=findViewById(R.id.input_date_register);
        descripcion=findViewById(R.id.input_descripcion_register);

        recicler.setLayoutManager(manager);
        recicler.setAdapter(new DoctorSelectedAdapter(getApplicationContext(),this));
        getDoctorList();
    }

    private void getDoctorList(){
        ApiService service= ApiServiceGenerator.createService(ApiService.class);
        Call<List<Medicos>> call=service.getMedicos();

        call.enqueue(new Callback<List<Medicos>>() {
            @Override
            public void onResponse(Call<List<Medicos>> call, Response<List<Medicos>> response) {
                try {
                    int statusCode = response.code();
                    Log.d(TAG, "HTTP status code: " + statusCode);

                    if (response.isSuccessful()) {

                        List<Medicos> medicos = response.body();
                        Log.d(TAG, "medicos: " + medicos);

                        DoctorSelectedAdapter adapter = (DoctorSelectedAdapter) recicler.getAdapter();
                        adapter.setLista(medicos);
                        adapter.notifyDataSetChanged();

                    } else {
                        Log.e(TAG, "onError: " + response.errorBody().string());
                        throw new Exception("Error en el servicio");
                    }

                } catch (Throwable t) {
                    try {
                        Log.e(TAG, "onThrowable: " + t.toString(), t);
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                    } catch (Throwable x) {
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Medicos>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
                //Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
