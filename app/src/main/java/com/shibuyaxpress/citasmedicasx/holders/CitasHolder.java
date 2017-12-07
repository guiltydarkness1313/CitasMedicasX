package com.shibuyaxpress.citasmedicasx.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.shibuyaxpress.citasmedicasx.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by paulf on 06-Dec-17.
 */

public class CitasHolder extends RecyclerView.ViewHolder {

    public TextView numero;
    public TextView nombreDoctor;
    public TextView especialidad;
    public TextView fecha;
    public TextView estado;
    public TextView descripcion;
    public CircleImageView doctorImg;

    public CitasHolder(View v) {
        super(v);
        numero=v.findViewById(R.id.txt_num_citas);
        nombreDoctor=v.findViewById(R.id.doc_name_citas);
        especialidad=v.findViewById(R.id.txt_esp_citas);
        fecha=v.findViewById(R.id.txt_date_citas);
        estado=v.findViewById(R.id.txt_estado_citas);
        descripcion=v.findViewById(R.id.txt_descp_citas);
        doctorImg=v.findViewById(R.id.img_doc_citas);
    }
}
