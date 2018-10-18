package com.sebito.restcrud.remote;

import com.sebito.restcrud.model.Contacto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ContactoService {

    @POST("contacto/rest/")
    Call<Contacto> create(@Body Contacto contacto);

    @GET("contacto/rest/")
    Call<List<Contacto>> retrieve();

    @GET("contacto/rest/{id}")
    Call<Contacto> retrieve(@Path("id") int id);

    @PUT("contacto/rest/{id}")
    Call<Contacto> update(@Path("id") int id, @Body Contacto contacto);

    @DELETE("contacto/rest/{id}")
    Call<Contacto> delete(@Path("id") int id);
}
