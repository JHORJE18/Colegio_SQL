package com.example.jhorje.sqlcolegio.Adaptadores;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jhorje.sqlcolegio.Objetos.Estudiante;
import com.example.jhorje.sqlcolegio.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by JHORJE on 11/12/17.
 */

public class AdaptadorEstudiantes extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<Estudiante> items;

    public AdaptadorEstudiantes(Activity activity, ArrayList<Estudiante> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    public void addAll(ArrayList<Estudiante> category) {
        for (int i = 0; i < category.size(); i++) {
            items.add(category.get(i));
        }
    }

    public void clear() {
        items.clear();
    }

    @Override
    public Object getItem(int arg0) {
        return items.get(arg0);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.elemento, null);
        }

        Estudiante persona = items.get(position);

        //Variables vista
        TextView txtNombre = (TextView) v.findViewById(R.id.txtENombre);
        TextView txtEdad = (TextView) v.findViewById(R.id.txtEEdad);
        TextView txtCiclo = (TextView) v.findViewById(R.id.txtECiclo);
        TextView txtCurso = (TextView) v.findViewById(R.id.txtECurso);
        TextView txtNota = (TextView) v.findViewById(R.id.txtENota);

        txtNota.setVisibility(View.VISIBLE);

        //Asignando valores
        txtNombre.setText(persona.getNombre());
        txtEdad.setText("Edad: " + persona.getEdadString());
        txtCiclo.setText("Ciclo: "+persona.getCiclo());
        txtCurso.setText("Curso: "+persona.getCurso());
        txtNota.setText(Float.toString(persona.getNota()));

        if (persona.getNota() < 5f){
            txtNota.setTextColor(R.color.error_color);
        }

        return v;
    }
}
