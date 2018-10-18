package com.sebito.restcrud;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sebito.restcrud.model.Contacto;

import java.util.List;

public class ContactoAdapter extends ArrayAdapter<Contacto> {

    protected Context context;
    protected List<Contacto> contactos;

    public ContactoAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Contacto> objects) {
        super(context, resource, objects);
        this.context = context;
        this.contactos = objects;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_contacto,parent,false);

        TextView txtContactoId = (TextView) rowView.findViewById(R.id.txtContactoId);
        TextView txtContactoNombre = (TextView) rowView.findViewById(R.id.txtContactoNombre);

        txtContactoId.setText(String.format("#ID: %d",contactos.get(position).getId()));
        txtContactoNombre.setText(String.format("Nombre: %s", contactos.get(position).getNombre()));


        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ContactoActivity.class);
                intent.putExtra("contacto_id", String.valueOf(contactos.get(position).getId()));
                intent.putExtra("contacto_nombre", String.valueOf(contactos.get(position).getNombre()));
                intent.putExtra("contacto_apellido", String.valueOf(contactos.get(position).getApellido()));
                intent.putExtra("contacto_telefono", String.valueOf(contactos.get(position).getTelefono()));
                intent.putExtra("contacto_email", String.valueOf(contactos.get(position).getEmail()));
                context.startActivity(intent);
            }
        });

        return rowView;
    }
}
