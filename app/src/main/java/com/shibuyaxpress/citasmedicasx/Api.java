package com.shibuyaxpress.citasmedicasx;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by paulf on 24-Oct-17.
 */

public interface Api {
    @GET("val.php")
    Call<List<Usuarios>> authenticate(@Query("usu") String username, @Query("pass") String password);
}
