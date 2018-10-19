package com.sebito.restcrud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sebito.restcrud.listener.ContactoDelOnClickListener;
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
    Button btnEdit;
    Button btnDel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto_detalle);

        valorCampoId = findViewById(R.id.valor_id);
        valorCampoNombre = findViewById(R.id.valor_nombre);
        valorCampoApellido = findViewById(R.id.valor_apellido);
        valorCampoEmail = findViewById(R.id.valor_email);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnDel = (Button) findViewById(R.id.btnDel);

        Bundle extras= getIntent().getExtras();

        contactoService = ApiUtils.getContactoService();
        final String contactoId = extras.getString("contacto_id");
        getContacto(Integer.parseInt(contactoId));

        btnDel.setOnClickListener(new ContactoDelOnClickListener()
                .setContactoId(Integer.parseInt(contactoId))
                .setContext(ContactoDetalleActivity.this));
    }

    private void getContacto(int contactoId) {
        Call<Contacto> contactoCall = contactoService.retrieve(contactoId);
        contactoCall.enqueue(new Callback<Contacto>() {
            @Override
            public void onResponse(Call<Contacto> call, Response<Contacto> response) {
                if(response.isSuccessful()){
                    contacto = response.body();
                    valorCampoId.setText(String.valueOf(contacto.getId()));
                    valorCampoNombre.setText(String.valueOf(contacto.getNombre()));
                    valorCampoApellido.setText(String.valueOf(contacto.getApellido()));
                    valorCampoEmail.setText(String.valueOf(contacto.getEmail()));
                    btnEdit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(ContactoDetalleActivity.this,ContactoActivity.class);
                            intent.putExtra("contacto_id",String.valueOf(contacto.getId()));
                            intent.putExtra("contacto_nombre",String.valueOf(contacto.getNombre()));
                            intent.putExtra("contacto_apellido",String.valueOf(contacto.getApellido()));
                            intent.putExtra("contacto_email",String.valueOf(contacto.getEmail()));
                            //intent.putExtra("contacto_cteatedAt",String.valueOf(contacto.getId()));
                            startActivity(intent);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<Contacto> call, Throwable t) {
                Log.e("ERROR: ",t.getMessage());
            }
        });
    }


}
