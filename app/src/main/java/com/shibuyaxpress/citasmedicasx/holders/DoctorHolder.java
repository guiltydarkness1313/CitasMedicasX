package com.shibuyaxpress.citasmedicasx.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.shibuyaxpress.citasmedicasx.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by paulf on 06-Nov-17.
 */

public class DoctorHolder extends RecyclerView.ViewHolder {
    public TextView nombre;
    public TextView especialidad;
    public TextView apellido;
    public CircleImageView perfil;

    public DoctorHolder(View v) {
        super(v);
    nombre=v.findViewById(R.id.txt_name_med);
    apellido=v.findViewById(R.id.txt_lastname_med);
    especialidad=v.findViewById(R.id.txt_esp);
    perfil=v.findViewById(R.id.img_profile_med);
    }
}
