package com.example.jhorje.sqlcolegio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jhorje.sqlcolegio.Adaptadores.AdaptadorEstudiantes;
import com.example.jhorje.sqlcolegio.Adaptadores.AdaptadorProfesores;
import com.example.jhorje.sqlcolegio.Objetos.Estudiante;
import com.example.jhorje.sqlcolegio.Objetos.Profesor;

import java.util.ArrayList;

public class MostrarRegistrosActivity extends AppCompatActivity {

    public MyDBAdapter dbAdapter;
    ArrayList<Estudiante> estudiantes;
    ArrayList<Profesor> profesores;
    AdaptadorEstudiantes adapterE;
    AdaptadorProfesores adapterP;
    CheckBox boxEstudiantes, boxProfesores;
    EditText editCiclo, editCurso;
    ListView listEstudiantes, listProfesores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_registros);

        //Controlador Vista
        boxEstudiantes = (CheckBox) findViewById(R.id.boxEstudiantes);
        boxProfesores = (CheckBox) findViewById(R.id.boxProfesores);
        editCiclo = (EditText) findViewById(R.id.editFiltroCiclo);
        editCurso = (EditText) findViewById(R.id.editFiltroCurso);

        estudiantes = new ArrayList<Estudiante>();
        profesores = new ArrayList<Profesor>();

        listEstudiantes = (ListView) findViewById(R.id.listEstudiantes);
        listProfesores = (ListView) findViewById(R.id.listProfesores);

        adapterE = new AdaptadorEstudiantes(this, estudiantes);
        listEstudiantes.setAdapter(adapterE);
        adapterP = new AdaptadorProfesores(this, profesores);
        listProfesores.setAdapter(adapterP);

        //Cargamos Base de datos SQLite
        dbAdapter = new MyDBAdapter(this);
        dbAdapter.open();
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnAplicarFiltros:
                recargarVista();
                break;
        }
    }

    private void recargarVista() {
        if (boxEstudiantes.isChecked()){
            listEstudiantes.setVisibility(View.VISIBLE);

            String filtros = " WHERE ";
            if (editCiclo.getText().toString().length() > 0){
                filtros += "ciclo='" + editCiclo.getText().toString() + "'";
            }

            if (editCiclo.getText().toString().length() > 0 && editCurso.getText().toString().length() > 0){
                filtros += " AND ";
            }

            if (editCurso.getText().toString().length() > 0){
                filtros += "curso='" + editCurso.getText().toString() + "'";
            }

            //Evaluador FINAL de filtros
            if (!filtros.equals(" WHERE ")){
                //Se han modificado filtros
                estudiantes = dbAdapter.filtroEstudiantes(filtros);
            } else {
                //No se han añadido filtros
                estudiantes = dbAdapter.llenarEstudiantes();
            }

            adapterE = new AdaptadorEstudiantes(this, estudiantes);
            listEstudiantes.setAdapter(adapterE);
        } else {
            listEstudiantes.setVisibility(View.GONE);
        }

        if (boxProfesores.isChecked()){
            listProfesores.setVisibility(View.VISIBLE);

            String filtros = " WHERE ";
            if (editCiclo.getText().toString().length() > 0){
                filtros += "ciclo='" + editCiclo.getText().toString() + "'";
            }

            if (editCiclo.getText().toString().length() > 0 && editCurso.getText().toString().length() > 0){
                filtros += " AND ";
            }

            if (editCurso.getText().toString().length() > 0){
                filtros += "curso='" + editCurso.getText().toString() + "'";
            }

            //Evaluador FINAL de filtros
            if (!filtros.equals(" WHERE ")){
                //Se han modificado filtros
                profesores = dbAdapter.filtroProfesores(filtros);
            } else {
                //No se han añadido filtros
                profesores = dbAdapter.llenarProfesores();
            }


            adapterP = new AdaptadorProfesores(this, profesores);
            listProfesores.setAdapter(adapterP);
        } else {
            listProfesores.setVisibility(View.GONE);
        }
    }
}