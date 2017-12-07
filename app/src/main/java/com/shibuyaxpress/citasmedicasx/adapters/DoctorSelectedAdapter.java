package com.shibuyaxpress.citasmedicasx.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.shibuyaxpress.citasmedicasx.FinalRegisterDateActivity;
import com.shibuyaxpress.citasmedicasx.R;
import com.shibuyaxpress.citasmedicasx.holders.DoctorHolder;
import com.shibuyaxpress.citasmedicasx.models.Medicos;
import com.shibuyaxpress.citasmedicasx.models.Pacientes;
import com.shibuyaxpress.citasmedicasx.services.ApiService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paulf on 07-Dec-17.
 */

public class DoctorSelectedAdapter extends RecyclerView.Adapter<DoctorHolder> {
    private Context context;
    private List<Medicos>lista;
    private Activity activity;

    public DoctorSelectedAdapter(Context context,Activity activity) {
        this.context = context;
        this.lista=new ArrayList<>();
        this.activity=activity;
    }

    public void setLista(List<Medicos> lista) {

        this.lista = lista;
    }

    public List<Medicos> getLista() {
        return lista;
    }
    @Override
    public DoctorHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_doctor_info,parent,false);
        return new DoctorHolder(item);
    }

    @Override
    public void onBindViewHolder(DoctorHolder holder, final int position) {
        holder.nombre.setText(lista.get(position).getNombre());
        holder.apellido.setText(lista.get(position).getApellido_paterno());
        holder.especialidad.setText(lista.get(position).getEspecialidad());
        final String link= ApiService.API_BASE_URL+"/images/"+lista.get(position).getImagen_perfil();
        Glide.with(context).load(link).into(holder.perfil);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Medicos.getInstance().setApellido_materno(lista.get(position).getApellido_materno());
                Medicos.getInstance().setApellido_paterno(lista.get(position).getApellido_paterno());
                Medicos.getInstance().setCelular(lista.get(position).getCelular());
                Medicos.getInstance().setImagen_perfil(lista.get(position).getImagen_perfil());
                Medicos.getInstance().setEspecialidad(lista.get(position).getEspecialidad());
                Medicos.getInstance().setGenero(lista.get(position).getGenero());
                Medicos.getInstance().setFecha_nacimiento(lista.get(position).getFecha_nacimiento());
                Medicos.getInstance().setHora_entrada(lista.get(position).getHora_entrada());
                Medicos.getInstance().setHora_salida(lista.get(position).getHora_salida());
                Medicos.getInstance().setId(lista.get(position).getId());
                Medicos.getInstance().setNum_doc_identidad(lista.get(position).getNum_doc_identidad());
                Medicos.getInstance().setUsuario_id(lista.get(position).getUsuario_id());
                Medicos.getInstance().setTipo_doc_identidad(lista.get(position).getTipo_doc_identidad());
                Medicos.getInstance().setNum_doc_identidad(lista.get(position).getNum_doc_identidad());
                Medicos.getInstance().setTelefono(lista.get(position).getTelefono());
                Intent intent=new Intent(context, FinalRegisterDateActivity.class);
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}
