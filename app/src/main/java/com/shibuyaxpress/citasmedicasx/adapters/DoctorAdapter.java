package com.shibuyaxpress.citasmedicasx.adapters;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shibuyaxpress.citasmedicasx.services.ApiService;
import com.shibuyaxpress.citasmedicasx.holders.DoctorHolder;
import com.shibuyaxpress.citasmedicasx.models.Medicos;
import com.shibuyaxpress.citasmedicasx.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

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

    public List<Medicos> getLista() {
        return lista;
    }

    @Override
    public DoctorHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_doctor_info,parent,false);
        return new DoctorHolder(item);
    }

    @Override
    public void onBindViewHolder(final DoctorHolder holder, final int position) {

        holder.nombre.setText(lista.get(position).getNombre());
        holder.apellido.setText(lista.get(position).getApellido_paterno());
        holder.especialidad.setText(lista.get(position).getEspecialidad());
        final String link= ApiService.API_BASE_URL+"/images/"+lista.get(position).getImagen_perfil();
        Glide.with(context).load(link).into(holder.perfil);

        final Dialog dialog=new Dialog(context);
        dialog.setContentView(R.layout.dialog_doctor_info);
        dialog.setTitle("Información del doctor");
        TextView nombre=dialog.findViewById(R.id.txt_name_dialog);
        TextView dni=dialog.findViewById(R.id.txt_doc_dialog);
        CircleImageView img=dialog.findViewById(R.id.doctor_img_dialog);
        TextView horario=dialog.findViewById(R.id.txt_schedule_dialog);
        TextView especialidad=dialog.findViewById(R.id.txt_special_dialog);
        TextView fecha=dialog.findViewById(R.id.txt_birth_dialog);

        nombre.setText(lista.get(position).getNombre()+" "+lista.get(position).getApellido_paterno());
        dni.setText(lista.get(position).getNum_doc_identidad());
        Glide.with(context).load(link).into(img);
        horario.setText(lista.get(position).getHora_entrada()+"-"+lista.get(position).getHora_salida());
        especialidad.setText(lista.get(position).getEspecialidad());
        fecha.setText(lista.get(position).getFecha_nacimiento());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });


    }


    @Override
    public int getItemCount() {
        return this.lista.size();
    }
}
