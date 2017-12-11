package com.example.jhorje.sqlcolegio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jhorje.sqlcolegio.Adaptadores.AdaptadorEstudiantes;
import com.example.jhorje.sqlcolegio.Objetos.Estudiante;
import com.example.jhorje.sqlcolegio.Objetos.Profesor;

import java.util.ArrayList;

public class MostrarRegistrosActivity extends AppCompatActivity {

    //TODO https://androidstudiofaqs.com/tutoriales/adapter-personalizado-en-android
    ArrayList<Estudiante> estudiantes;
    ArrayList<Profesor> profesores;
    AdaptadorEstudiantes adapter;
    CheckBox boxEstudiantes, boxProfesores;
    EditText editCiclo, editCurso;

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

        estudiantes.add(new Estudiante("Jorge",19,"Florida","DAM",9));
        estudiantes.add(new Estudiante("Jorge",19,"Florida","DAM",9));
        estudiantes.add(new Estudiante("Jorge",19,"Florida","DAM",9));
        estudiantes.add(new Estudiante("Jorge",19,"Florida","DAM",9));
        estudiantes.add(new Estudiante("Jorge",19,"Florida","DAM",9));
        estudiantes.add(new Estudiante("Jorge",19,"Florida","DAM",9));
        estudiantes.add(new Estudiante("Jorge",19,"Florida","DAM",9));

        ListView lv = (ListView) findViewById(R.id.listEstudiantes);
        adapter = new AdaptadorEstudiantes(this, estudiantes);
        lv.setAdapter(adapter);
    }

}