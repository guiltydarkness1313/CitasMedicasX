package com.shibuyaxpress.citasmedicasx;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by paulf on 24-Oct-17.
 */

public interface ApiService {

    String API_BASE_URL="http://54.149.168.221";
    //obtener los datos de un usuario registrado en caso de existir
    @GET("val.php")
    Call<List<Usuarios>> authenticate(@Query("usu") String username, @Query("pass") String password);

    //obtener la información de los medicos para almacenarlos es un List
    @GET("api/v1/medicos")
    Call<List<Medicos>> getMedicos();
}
