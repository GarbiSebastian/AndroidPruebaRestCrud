package com.sebito.restcrud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sebito.restcrud.model.Contacto;
import com.sebito.restcrud.remote.ApiUtils;
import com.sebito.restcrud.remote.ContactoService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactoActivity extends AppCompatActivity {
    ContactoService contactoService;
    EditText edtUId;
    EditText edtUsername;
    Button btnSave;
    Button btnDel;
    TextView txtUId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        setTitle("Contactos");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtUId      = (TextView)    findViewById(R.id.txtUId);
        edtUId      = (EditText)    findViewById(R.id.edtUId);
        edtUsername = (EditText)    findViewById(R.id.edtUsername);
        btnSave     = (Button)      findViewById(R.id.btnSave);
        btnDel      = (Button)      findViewById(R.id.btnDel);

        contactoService = ApiUtils.getContactoService();

        Bundle extras= getIntent().getExtras();
        final String contactoId = extras.getString("contacto_id");
        String contactoNombre   = extras.getString("contacto_nombre");
        String contactoApellido = extras.getString("contacto_apellido");
        String contactoTelefono = extras.getString("contacto_telefono");
        String contactoEmail    = extras.getString("contacto_email");

        if (contactoId != null && contactoId.trim().length()>0){
            edtUId.setFocusable(false);
            edtUId.setText(contactoId);
            edtUsername.setText(contactoNombre);
        }else{
            txtUId.setVisibility(View.INVISIBLE);
            edtUId.setVisibility(View.INVISIBLE);
            btnDel.setVisibility(View.INVISIBLE);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contacto contacto = new Contacto();
                contacto.setNombre(edtUsername.getText().toString())
                        .setApellido("SARASA")
                        .setTelefono("1234556")
                        .setEmail(edtUsername.getText().toString()+"@sarasa.com");
                if((contactoId != null) && (contactoId.trim().length() > 0)){
                    updateContacto(Integer.parseInt(contactoId),contacto);
                }else {
                    addContacto(contacto);
                }
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteContacto(Integer.parseInt(contactoId));

                Intent intent = new Intent(ContactoActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void addContacto(Contacto contacto){
        Call<Contacto> contactoCall = contactoService.addContacto(contacto);
        contactoCall.enqueue(new Callback<Contacto>() {
            @Override
            public void onResponse(Call<Contacto> call, Response<Contacto> response) {
                if(response.isSuccessful()){
                    Toast.makeText(ContactoActivity.this,"Contacto creado con éxito", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Contacto> call, Throwable t) {
                Log.e("ERROR: ",t.getMessage());
            }
        });
    }

    public void updateContacto(int id, Contacto contacto){
        Call<Contacto> contactoCall= contactoService.updateContacto(id,contacto);
        contactoCall.enqueue(new Callback<Contacto>() {
            @Override
            public void onResponse(Call<Contacto> call, Response<Contacto> response) {
                if (response.isSuccessful()){
                    Toast.makeText(ContactoActivity.this, "Contacto actualizado", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Contacto> call, Throwable t) {
                Log.e("ERROR: ",t.getMessage());
            }
        });
    }

    public void deleteContacto(int id){
        Call<Contacto> contactoCall = contactoService.deleteContacto(id);
        contactoCall.enqueue(new Callback<Contacto>() {
            @Override
            public void onResponse(Call<Contacto> call, Response<Contacto> response) {
                if (response.isSuccessful()){
                    Toast.makeText(ContactoActivity.this,"Contacto eliminado",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Contacto> call, Throwable t) {
                Log.e("ERROR: ",t.getMessage());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}