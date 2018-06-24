package com.example.pauli.finallogin;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter {
    Activity activity;
    int layout;
    ArrayList<Tareas> arrTareas;
    public CustomAdapter(@NonNull Activity activity, @LayoutRes int layout, @NonNull ArrayList<Tareas> arrTareas) {
        super(activity, layout, arrTareas);
        System.out.print("Entro Custom");
        this.activity = activity;
        this.layout = layout;
        this.arrTareas = arrTareas;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        convertView = layoutInflater.inflate(layout, null);

        //ImageView tareas = (ImageView) convertView.findViewById(R.id.list_tareas);
        TextView textNombreTarea = (TextView) convertView.findViewById(R.id.text_nombre_tarea);
        TextView textDuracion = (TextView) convertView.findViewById(R.id.text_duracion);
        TextView textIdUsuario = (TextView) convertView.findViewById(R.id.text_id_usuario);
        TextView textFecha = (TextView) convertView.findViewById(R.id.text_fecha);

        //tareas.setImageResource(arrTareas.get(position).getIdSprint());
        textNombreTarea.setText(arrTareas.get(position).getNombreSprint());
        textDuracion.setText(arrTareas.get(position).getDuracion().toString());
        textIdUsuario.setText(arrTareas.get(position).getIdUsuario().toString());

        textFecha.setText(arrTareas.get(position).getFecha());

        return convertView;
    }
}
