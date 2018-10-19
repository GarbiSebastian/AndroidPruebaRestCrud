package com.sebito.restcrud.listener;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.sebito.restcrud.MainActivity;
import com.sebito.restcrud.model.Contacto;
import com.sebito.restcrud.remote.ApiUtils;
import com.sebito.restcrud.remote.ContactoService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactoDelOnClickListener implements View.OnClickListener {

    protected int contactoId;
    protected Context context;

    public ContactoDelOnClickListener setContactoId(int contactoId) {
        this.contactoId = contactoId;
        return this;
    }

    public ContactoDelOnClickListener setContext(Context context) {
        this.context = context;
        return this;
    }

    @Override
    public void onClick(View v) {
        deleteContacto(contactoId);

        Intent intent = new Intent(context,MainActivity.class);
        context.startActivity(intent);
    }

    private void deleteContacto(int id){
        Call<Contacto> contactoCall = ApiUtils.getContactoService().delete(id);
        contactoCall.enqueue(new Callback<Contacto>() {
            @Override
            public void onResponse(Call<Contacto> call, Response<Contacto> response) {
                if (response.isSuccessful()){
                    Toast.makeText(context,"Contacto eliminado",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Contacto> call, Throwable t) {
                Log.e("ERROR: ",t.getMessage());
            }
        });
    }
}
