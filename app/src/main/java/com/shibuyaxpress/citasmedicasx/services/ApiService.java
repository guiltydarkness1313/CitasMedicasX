package com.shibuyaxpress.citasmedicasx.services;

import com.shibuyaxpress.citasmedicasx.models.Medicos;
import com.shibuyaxpress.citasmedicasx.models.Usuarios;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by paulf on 24-Oct-17.
 */

public interface ApiService {

    String API_BASE_URL="http://54.149.168.221";
    //obtener los datos de un usuario registrado en caso de existir
    @FormUrlEncoded
    @POST("/api/v1/login")
    Call<Usuarios> Login(@Field("username") String nombre,
                         @Field("password") String password);

    //obtener la información de los medicos para almacenarlos es un List
    @GET("api/v1/medicos")
    Call<List<Medicos>> getMedicos();


}
