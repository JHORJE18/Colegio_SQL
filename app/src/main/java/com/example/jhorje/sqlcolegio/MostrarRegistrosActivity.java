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

import com.example.jhorje.sqlcolegio.Adaptadores.AdaptadorAsignaturas;
import com.example.jhorje.sqlcolegio.Adaptadores.AdaptadorEstudiantes;
import com.example.jhorje.sqlcolegio.Adaptadores.AdaptadorProfesores;
import com.example.jhorje.sqlcolegio.Objetos.Asignatura;
import com.example.jhorje.sqlcolegio.Objetos.Estudiante;
import com.example.jhorje.sqlcolegio.Objetos.Profesor;

import java.util.ArrayList;

public class MostrarRegistrosActivity extends AppCompatActivity {

    public MyDBAdapter dbAdapter;
    ArrayList<Estudiante> estudiantes;
    ArrayList<Profesor> profesores;
    ArrayList<Asignatura> asignaturas;

    AdaptadorEstudiantes adapterE;
    AdaptadorProfesores adapterP;
    AdaptadorAsignaturas adapterA;

    CheckBox boxEstudiantes, boxProfesores, boxAsignaturas;
    EditText editCiclo, editCurso;
    ListView listEstudiantes, listProfesores, listAsignaturas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_registros);

        //Controlador Vista
        boxEstudiantes = (CheckBox) findViewById(R.id.boxEstudiantes);
        boxProfesores = (CheckBox) findViewById(R.id.boxProfesores);
        editCiclo = (EditText) findViewById(R.id.editFiltroCiclo);
        editCurso = (EditText) findViewById(R.id.editFiltroCurso);
        boxAsignaturas = (CheckBox) findViewById(R.id.boxAsignaturas);

        estudiantes = new ArrayList<Estudiante>();
        profesores = new ArrayList<Profesor>();
        asignaturas = new ArrayList<Asignatura>();

        listEstudiantes = (ListView) findViewById(R.id.listEstudiantes);
        listProfesores = (ListView) findViewById(R.id.listProfesores);
        listAsignaturas = (ListView) findViewById(R.id.listAsignaturas);

        adapterE = new AdaptadorEstudiantes(this, estudiantes);
        listEstudiantes.setAdapter(adapterE);
        adapterP = new AdaptadorProfesores(this, profesores);
        listProfesores.setAdapter(adapterP);
        adapterA = new AdaptadorAsignaturas(this,asignaturas);
        listAsignaturas.setAdapter(adapterA);

        //Cargamos Base de datos SQLite
        dbAdapter = new MyDBAdapter(this);
        dbAdapter.open();

        //Evento para mostrar o no las asignaturas
        boxAsignaturas.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                boxEstudiantes.setChecked(!isChecked);
                boxProfesores.setChecked(!isChecked);
                boxEstudiantes.setEnabled(!isChecked);
                boxProfesores.setEnabled(!isChecked);

                if (isChecked){
                    editCiclo.setHint("Nombre asignatura:");
                    editCurso.setVisibility(View.GONE);

                    listEstudiantes.setVisibility(View.GONE);
                    listProfesores.setVisibility(View.GONE);
                    listAsignaturas.setVisibility(View.VISIBLE);
                } else {
                    editCiclo.setHint("Ciclo:");
                    editCurso.setVisibility(View.VISIBLE);

                    listEstudiantes.setVisibility(View.VISIBLE);
                    listProfesores.setVisibility(View.VISIBLE);
                    listAsignaturas.setVisibility(View.GONE);
                }
            }
        });
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

        if (boxAsignaturas.isChecked()){

            String filtros = " WHERE ";
            if (editCiclo.getText().toString().length() > 0){
                filtros += "nombre='" + editCiclo.getText().toString() + "'";
            }

            //Evaluador FINAL de filtros
            if (!filtros.equals(" WHERE ")){
                //Se han modificado filtros
                asignaturas = dbAdapter.filtroAsignaturas(filtros);
            } else {
                //No se han añadido filtros
                asignaturas = dbAdapter.llenarAsignaturas();
            }


            adapterA = new AdaptadorAsignaturas(this, asignaturas);
            listAsignaturas.setAdapter(adapterA);
        }
    }
}