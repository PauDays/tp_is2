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
        TextView textNombreUS = convertView.findViewById(R.id.text_nombre_tarea);
        TextView textIdUsuarioCreador = (TextView) (TextView) convertView.findViewById(R.id.text_id_usuario_creador);
        TextView textIdUsuarioEditor = (TextView) (TextView) convertView.findViewById(R.id.text_id_usuario_editor);
        TextView textEstado= convertView.findViewById(R.id.text_estado);
        TextView textIdSprint = (TextView) (TextView) convertView.findViewById(R.id.text_id_sprint);
        TextView textFecha =  convertView.findViewById(R.id.text_fecha);
        TextView textFechaFin = (TextView) (TextView) convertView.findViewById(R.id.text_fecha_fin);

        //tareas.setImageResource(arrTareas.get(position).getIdSprint());
        textNombreUS.setText(arrTareas.get(position).getNombreUS());
        textIdUsuarioCreador.setText(arrTareas.get(position).getIdUsuarioCreador().toString());
        textIdUsuarioEditor.setText(arrTareas.get(position).getIdUsuarioEditor().toString());
        textEstado.setText(arrTareas.get(position).getEstado().toString());
        textIdSprint.setText(arrTareas.get(position).getIdSprint().toString());
        System.out.println("++++++++++++++++++++"+arrTareas.get(position).getFecha());
        textFecha.setText(arrTareas.get(position).getFecha());
        textFechaFin.setText(arrTareas.get(position).getFechaFin());

        return convertView;
    }
}
