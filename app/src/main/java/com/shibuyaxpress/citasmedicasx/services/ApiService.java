package com.shibuyaxpress.citasmedicasx.services;

import com.shibuyaxpress.citasmedicasx.activities.RegisterUserActivity;
import com.shibuyaxpress.citasmedicasx.models.Citas;
import com.shibuyaxpress.citasmedicasx.models.Medicos;
import com.shibuyaxpress.citasmedicasx.models.Pacientes;
import com.shibuyaxpress.citasmedicasx.models.ResponseMessage;
import com.shibuyaxpress.citasmedicasx.models.ResponsePatient;
import com.shibuyaxpress.citasmedicasx.models.ResponseUser;
import com.shibuyaxpress.citasmedicasx.models.Usuarios;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by paulf on 24-Oct-17.
 */

public interface ApiService {

    String API_BASE_URL="http://54.149.168.221";
    //obtener los datos de un usuario registrado en caso de existir
    @FormUrlEncoded
    @POST("api/v1/login")
    Call<Usuarios> Login(@Field("username") String nombre,
                         @Field("password") String password);

    //obtener la información de los medicos para almacenarlos es un List
    @GET("api/v1/medicos")
    Call<List<Medicos>> getMedicos();

    @FormUrlEncoded
    @POST("api/v1/pacientes")
    Call<ResponseMessage>createPatient(
                    @Field("nombre")String nombre,
                    @Field("apellido_paterno")String ap_paterno,
                    @Field("apellido_materno")String ap_materno,
                    @Field("tipo_doc") String tipo_doc,
                    @Field("num_doc") String num_doc,
                    @Field("fecha_nacimiento") String fecha_nac,
                    @Field("nacionalidad") String nacionalidad,
                    @Field("direccion") String direccion,
                    @Field("cod_postal") String cod_postal,
                    @Field("ciudad") String ciudad,
                    @Field("provincia") String provincia,
                    @Field("telefono") String telefono,
                    @Field("celular") String celular,
                    @Field("correo") String correo,
                    @Field("genero") String genero,
                    @Field("usuario_id") String usuario_id
            );

    @Multipart
    @POST("api/v1/pacientes")
    Call<ResponseMessage>createPatientWithImage(
            @Part("nombre") RequestBody nombre,
            @Part("apellido_paterno")RequestBody ap_paterno,
            @Part("apellido_materno")RequestBody ap_materno,
            @Part("tipo_doc") RequestBody tipo_doc,
            @Part("num_doc") RequestBody num_doc,
            @Part("fecha_nacimiento") RequestBody fecha_nac,
            @Part("nacionalidad") RequestBody nacionalidad,
            @Part("direccion") RequestBody direccion,
            @Part("cod_postal") RequestBody cod_postal,
            @Part("ciudad") RequestBody ciudad,
            @Part("provincia") RequestBody provincia,
            @Part("telefono") RequestBody telefono,
            @Part("celular") RequestBody celular,
            @Part("correo") RequestBody correo,
            @Part("genero") RequestBody genero,
            @Part("usuario_id") RequestBody usuario_id,
            @Part MultipartBody.Part imagen_perfil
    );

    //registro de usuario
    @FormUrlEncoded
    @POST("api/v1/usuarios")
    Call<ResponseUser>createUser(
            @Field("username") String username,
            @Field("password")  String password,
            @Field("type")  String type,
            @Field("reg_date")  String reg_date
            );

        @GET("mis-citas/{id}")
    Call<Pacientes> dateByPatient(@Path("id") Integer id);

        @GET("buscar/{id}")
    Call<ResponsePatient>searchUser(@Path("id") Integer id);

        @GET("api/v1/medicos/{id}")
    Call<Medicos>searchDoctor(@Path("id")Integer id);

        @FormUrlEncoded
        @POST("api/v1/citas")
    Call<Citas>createDatewithDoctor(
            @Field("fecha") String fecha,
            @Field("descripcion") String descripcion,
            @Field("medico_id")String medico_id,
            @Field("paciente_id")String paciente_id);
}
