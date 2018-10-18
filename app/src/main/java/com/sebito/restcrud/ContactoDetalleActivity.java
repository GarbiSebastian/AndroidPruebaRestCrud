package com.sebito.restcrud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.sebito.restcrud.model.Contacto;
import com.sebito.restcrud.remote.ApiUtils;
import com.sebito.restcrud.remote.ContactoService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactoDetalleActivity extends AppCompatActivity {

    Contacto contacto;
    ContactoService contactoService;
    TextView valorCampoId;
    TextView valorCampoNombre;
    TextView valorCampoApellido;
    TextView valorCampoEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto_detalle);

        valorCampoId = findViewById(R.id.valor_id);
        valorCampoNombre = findViewById(R.id.valor_nombre);
        valorCampoApellido = findViewById(R.id.valor_apellido);
        valorCampoEmail = findViewById(R.id.valor_email);

        Bundle extras= getIntent().getExtras();

        contactoService = ApiUtils.getContactoService();
        final String contactoId = extras.getString("contacto_id");
        getContacto(Integer.parseInt(contactoId));

        if (contacto==null){
            Toast.makeText(ContactoDetalleActivity.this,"Erroraso contacto es null",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(ContactoDetalleActivity.this,"Anduvo",Toast.LENGTH_SHORT).show();
        }
        /*
        valorCampoId.setText(String.valueOf(contacto.getId()));
        valorCampoNombre.setText(String.valueOf(contacto.getNombre()));
        valorCampoApellido.setText(String.valueOf(contacto.getApellido()));
        valorCampoEmail.setText(String.valueOf(contacto.getEmail()));
        */
    }

    private void getContacto(int contactoId) {
        Call<Contacto> contactoCall = contactoService.retrieve(contactoId);
        contactoCall.enqueue(new Callback<Contacto>() {
            @Override
            public void onResponse(Call<Contacto> call, Response<Contacto> response) {
                if(response.isSuccessful()){
                    contacto = response.body();
                }
            }

            @Override
            public void onFailure(Call<Contacto> call, Throwable t) {
                Log.e("ERROR: ",t.getMessage());
            }
        });
    }
}
