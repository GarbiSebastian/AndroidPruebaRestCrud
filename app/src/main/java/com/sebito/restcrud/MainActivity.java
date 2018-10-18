package com.sebito.restcrud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.sebito.restcrud.model.Contacto;
import com.sebito.restcrud.remote.ApiUtils;
import com.sebito.restcrud.remote.ContactoService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button btnAddContacto;
    Button btnGetContactos;
    ListView listView;

    ContactoService contactoService;
    List<Contacto> list = new ArrayList<Contacto>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Sebito es groso");

        btnAddContacto  = (Button) findViewById(R.id.btnAddContacto);
        //btnGetContactos = (Button) findViewById(R.id.btnGetContactos);
        listView        = (ListView) findViewById(R.id.listView);
        contactoService = ApiUtils.getContactoService();

        /*
        btnGetContactos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrieve();
            }
        });
        */
        getContactos();

        btnAddContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ContactoActivity.class);
                intent.putExtra("contacto_nombre","");
                intent.putExtra("contacto_apellido","");
                intent.putExtra("contacto_email","");
                startActivity(intent);
            }
        });

    }

    public void getContactos(){
        Call<List<Contacto>> call= contactoService.retrieve();
        call.enqueue(new Callback<List<Contacto>>() {
            @Override
            public void onResponse(Call<List<Contacto>> call, Response<List<Contacto>> response) {
                if(response.isSuccessful()){
                    list=response.body();
                    listView.setAdapter(new ContactoAdapter(MainActivity.this,R.layout.list_contacto,list));
                }
            }

            @Override
            public void onFailure(Call<List<Contacto>> call, Throwable t) {
                Log.e("ERROR: ",t.getMessage());
            }
        });
    }
}
