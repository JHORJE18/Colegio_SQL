package com.example.jhorje.sqlcolegio.Adaptadores;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jhorje.sqlcolegio.Objetos.Asignatura;
import com.example.jhorje.sqlcolegio.Objetos.Estudiante;
import com.example.jhorje.sqlcolegio.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by JHORJE on 22/01/17.
 */

public class AdaptadorAsignaturas extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<Asignatura> items;

    public AdaptadorAsignaturas(Activity activity, ArrayList<Asignatura> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    public void addAll(ArrayList<Asignatura> category) {
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

        Asignatura asignaturaTEMP = items.get(position);

        //Variables vista
        TextView txtNombre = (TextView) v.findViewById(R.id.txtENombre);
        TextView txtEdad = (TextView) v.findViewById(R.id.txtEEdad);
        TextView txtCiclo = (TextView) v.findViewById(R.id.txtECiclo);
        TextView txtCurso = (TextView) v.findViewById(R.id.txtECurso);
        TextView txtHora = (TextView) v.findViewById(R.id.txtENota);

        txtHora.setVisibility(View.VISIBLE);
        txtEdad.setVisibility(View.GONE);
        txtCiclo.setVisibility(View.GONE);
        txtCurso.setVisibility(View.GONE);

        //Asignando valores
        txtNombre.setText(asignaturaTEMP.getNombre());
        txtHora.setText(asignaturaTEMP.getHoras());

        return v;
    }
}