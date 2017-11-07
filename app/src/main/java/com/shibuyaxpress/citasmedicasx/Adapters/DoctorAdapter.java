package com.shibuyaxpress.citasmedicasx.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.shibuyaxpress.citasmedicasx.Holders.DoctorHolder;
import com.shibuyaxpress.citasmedicasx.Medicos;
import com.shibuyaxpress.citasmedicasx.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paulf on 06-Nov-17.
 */

public class DoctorAdapter extends RecyclerView.Adapter<DoctorHolder>{

    private Context context;
    private List<Medicos> lista;

    public DoctorAdapter(Context context) {
        this.context = context;
        this.lista=new ArrayList<>();
    }

    public void setLista(List<Medicos> lista) {

        this.lista = lista;
    }

    @Override
    public DoctorHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_doctor_info,parent,false);
        return new DoctorHolder(item);
    }

    @Override
    public void onBindViewHolder(DoctorHolder holder, int position) {

        holder.nombre.setText(lista.get(position).getNombre());
        holder.apellido.setText(lista.get(position).getApellido_paterno());
        holder.especialidad.setText(lista.get(position).getEspecialidad());
        Glide.with(context).load(lista.get(position).getImagen_perfil()).into(holder.perfil);
    }

    @Override
    public int getItemCount() {
        return this.lista.size();
    }
}
