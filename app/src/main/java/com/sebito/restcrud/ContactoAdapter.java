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
        //TextView txtContactoNombre = (TextView) rowView.findViewById(R.id.txtContactoNombre);
        //TextView txtContactoApellido = (TextView) rowView.findViewById(R.id.txtContactoApellido);
        TextView txtContactoEmail = (TextView) rowView.findViewById(R.id.txtContactoEmail);

        txtContactoId.setText(String.format("#ID: %d",contactos.get(position).getId()));
        //txtContactoNombre.setText(String.format("Nombre: %s", contactos.get(position).getNombre()));
        //txtContactoApellido.setText(String.format("Apellido: %s", contactos.get(position).getApellido()));
        txtContactoEmail.setText(String.format("Email: %s", contactos.get(position).getEmail()));

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contacto contacto = contactos.get(position);
                Intent intent = new Intent(context, ContactoDetalleActivity.class);
                //Intent intent = new Intent(context, ContactoActivity.class);
                intent.putExtra("contacto_id", String.valueOf(contacto.getId()));
                //intent.putExtra("contacto_nombre", String.valueOf(contacto.getNombre()));
                //intent.putExtra("contacto_apellido", String.valueOf(contacto.getApellido()));
                //intent.putExtra("contacto_email", String.valueOf(contacto.getEmail()));
                context.startActivity(intent);
            }
        });

        return rowView;
    }
}
