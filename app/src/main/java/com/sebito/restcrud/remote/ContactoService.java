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

    @GET("rest/")
    Call<List<Contacto>>getContactos();

    @POST("rest/")
    Call<Contacto>addContacto(@Body Contacto contacto);

    @PUT("rest/{id}")
    Call<Contacto>updateContacto(@Path("id") int id, @Body Contacto contacto);

    @DELETE("rest/{id}")
    Call<Contacto>deleteContacto(@Path("id") int id);
}
