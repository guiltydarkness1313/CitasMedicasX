package com.shibuyaxpress.citasmedicasx.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.shibuyaxpress.citasmedicasx.R;
import com.shibuyaxpress.citasmedicasx.holders.CitasHolder;
import com.shibuyaxpress.citasmedicasx.models.Citas;
import com.shibuyaxpress.citasmedicasx.models.Medicos;
import com.shibuyaxpress.citasmedicasx.services.ApiService;
import com.shibuyaxpress.citasmedicasx.services.ApiServiceGenerator;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by paulf on 06-Dec-17.
 */

public class CitasAdapter extends RecyclerView.Adapter<CitasHolder>{

    private Context context;
    private List<Citas> lista;
    private static final String TAG = CitasAdapter.class.getSimpleName();
    public CitasAdapter(Context context){
        this.context=context;
        this.lista=new ArrayList<>();
    }

    public void setLista(List<Citas>lista){
        this.lista=lista;
    }

    @Override
    public CitasHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_citas,parent,false);
        return new CitasHolder(item);
    }

    public List<Citas> getLista() {
        return lista;
    }

    @Override
    public void onBindViewHolder(final CitasHolder holder, int position) {
        holder.estado.setText(lista.get(position).getEstado());
        holder.descripcion.setText(lista.get(position).getDescripcion());
        holder.fecha.setText(lista.get(position).getFecha());
        holder.numero.setText(String.valueOf(position+1));

        ApiService service= ApiServiceGenerator.createService(ApiService.class);
        Call<Medicos>call=service.searchDoctor(Integer.parseInt(lista.get(position).getMedico_id()));
        call.enqueue(new Callback<Medicos>() {
            @Override
            public void onResponse(Call<Medicos> call, Response<Medicos> response) {
                Medicos e=response.body();
                Log.d(TAG,"el autor es " +e.getNombre());
                holder.nombreDoctor.setText(e.getNombre()+" "+e.getApellido_paterno());
                holder.especialidad.setText(e.getEspecialidad());
                String url = ApiService.API_BASE_URL + "/images/" + e.getImagen_perfil();
                Glide.with(context).load(url).into(holder.doctorImg);
            }

            @Override
            public void onFailure(Call<Medicos> call, Throwable t) {
                Log.d(TAG,t.getMessage());
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.lista.size();
    }
}
