package com.shibuyaxpress.citasmedicasx.fragments;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.jetradar.desertplaceholder.DesertPlaceholder;
import com.shibuyaxpress.citasmedicasx.R;
import com.shibuyaxpress.citasmedicasx.RegisterDateActivity;
import com.shibuyaxpress.citasmedicasx.adapters.CitasAdapter;
import com.shibuyaxpress.citasmedicasx.models.Citas;
import com.shibuyaxpress.citasmedicasx.models.MyWeekViewEvent;
import com.shibuyaxpress.citasmedicasx.models.Pacientes;
import com.shibuyaxpress.citasmedicasx.models.Usuarios;
import com.shibuyaxpress.citasmedicasx.services.ApiService;
import com.shibuyaxpress.citasmedicasx.services.ApiServiceGenerator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ServiceConfigurationError;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class DatesFragment extends Fragment {

    private static final String TAG=DatesFragment.class.getSimpleName();
    private RecyclerView datesRecycler;
    private CitasAdapter adapter;
    private LinearLayoutManager manager;
    private DesertPlaceholder desertPlaceholder;
    private FloatingActionButton fab;

    private static final int REGISTER_FORM_REQUEST = 100;

    public DatesFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View v=inflater.inflate(R.layout.fragment_dates, container, false);
        fab=v.findViewById(R.id.fab_cita);
        desertPlaceholder=v.findViewById(R.id.placeholder_dates);
        datesRecycler=v.findViewById(R.id.reciclador_citas);
        manager=new LinearLayoutManager(getContext());
        datesRecycler.setLayoutManager(manager);
        datesRecycler.setAdapter(new CitasAdapter(getContext()));
        getCitasList();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent launcher=new Intent(getContext(),RegisterDateActivity.class);
                startActivityForResult(launcher,REGISTER_FORM_REQUEST);
            }
        });
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REGISTER_FORM_REQUEST) {
            // refresh data
            getCitasList();
        }
    }

    private void getCitasList(){
        ApiService service=ApiServiceGenerator.createService(ApiService.class);
        Call<Pacientes>call=service.dateByPatient(Integer.parseInt(Usuarios.getInstance().getId()));
        call.enqueue(new Callback<Pacientes>() {
            @Override
            public void onResponse(Call<Pacientes> call, Response<Pacientes> response) {
                try{
                    int statusCode=response.code();
                    Log.d(TAG, "HTTP status code: " + statusCode);
                    if (response.isSuccessful()){
                        Pacientes pa=response.body();
                        List<Citas>citas= Arrays.asList(pa.getCitas());
                        Log.d(TAG, "medicos: " + citas.get(0).getDescripcion());

                        CitasAdapter adapter=(CitasAdapter)datesRecycler.getAdapter();
                        adapter.setLista(citas);
                        adapter.notifyDataSetChanged();
                    }else{
                        desertPlaceholder.setVisibility(View.VISIBLE);
                        Log.e(TAG, "onError: " + response.errorBody().string());
                        throw new Exception("Error en el servicio");
                    }
                }catch (Throwable t){
                    try{
                        Log.e(TAG, "onThrowable: " + t.toString(), t);
                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                    } catch (Throwable x) {
                    }
                }
            }

            @Override
            public void onFailure(Call<Pacientes> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
                CitasAdapter adapter=(CitasAdapter)datesRecycler.getAdapter();
                desertPlaceholder.setVisibility(View.VISIBLE);
                if(adapter.getLista().isEmpty()){
                    desertPlaceholder.setVisibility(View.VISIBLE);
                }else {
                    desertPlaceholder.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

}
