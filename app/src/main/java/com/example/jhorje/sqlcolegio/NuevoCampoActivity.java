package com.example.jhorje.sqlcolegio;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jhorje.sqlcolegio.Objetos.Estudiante;
import com.example.jhorje.sqlcolegio.Objetos.Profesor;

public class NuevoCampoActivity extends AppCompatActivity {

    //Variables
    private MyDBAdapter dbAdapter;
    EditText editNombre, editEdad, editCiclo, editCurso, editNota, editDespacho;
    Button btnGuardar;
    RadioGroup radioGroup;
    RadioButton rbEstudiante, rbProfesor;
    TextView txtTitulo;
    boolean estudiante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_campo);

        //Conexion Vista
        txtTitulo = (TextView) findViewById(R.id.txtNuevo);
        editNombre = (EditText) findViewById(R.id.editNombre);
        editEdad = (EditText) findViewById(R.id.editEdad);
        editCiclo = (EditText) findViewById(R.id.editciclo);
        editCurso = (EditText) findViewById(R.id.editCurso);
        editNota = (EditText) findViewById(R.id.editNota);
        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        rbEstudiante = (RadioButton) findViewById(R.id.rbNuevoEstudiante);
        rbProfesor = (RadioButton) findViewById(R.id.rbNuevoProfesor);
        editDespacho = (EditText) findViewById(R.id.editDespacho);
        estudiante = true;

        //Cargamos Base de datos SQLite
        dbAdapter = new MyDBAdapter(this);

        //Radio Buttons
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (rbProfesor.isChecked()){
                    estudiante = false;
                } else {
                    estudiante = true;
                }

                refrescarVista();
            }
        });
    }
    
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnGuardar:
                if (estudiante){
                    nuevoEstudiante();
                } else {
                    nuevoProfesor();
                }
                break;
        }
    }

    public void refrescarVista(){
        if (estudiante){
            editDespacho.setVisibility(View.GONE);
            editNota.setVisibility(View.VISIBLE);
            txtTitulo.setText("Nuevo Estudiante");
        } else {
            editDespacho.setVisibility(View.VISIBLE);
            editNota.setVisibility(View.GONE);
            txtTitulo.setText("Nuevo Profesor");
        }
    }

    private void nuevoProfesor() {
        //Obtenemos datos
        String nombre = editNombre.getText().toString();
        int edad = Integer.parseInt(editEdad.getText().toString());
        String ciclo = editCiclo.getText().toString();
        String curso = editCurso.getText().toString();
        String despacho = editDespacho.getText().toString();

        //Creamos objeto estudiante
        Profesor nuevoProfesor = new Profesor(nombre,edad,ciclo,curso,despacho);

        dbAdapter.open();
        dbAdapter.insertarProfesor(nuevoProfesor);
        dbAdapter.close();

        Toast.makeText(getApplicationContext(),"Nuevo profesor generado",Toast.LENGTH_LONG).show();
    }

    private void nuevoEstudiante() {
        //Obtenemos datos
        String nombre = editNombre.getText().toString();
        int edad = Integer.parseInt(editEdad.getText().toString());
        String ciclo = editCiclo.getText().toString();
        String curso = editCurso.getText().toString();
        float nota = Float.parseFloat(editNota.getText().toString());

        //Creamos objeto estudiante
        Estudiante nuevoEstudiante = new Estudiante(nombre,edad,ciclo,curso,nota);

        dbAdapter.open();
        dbAdapter.insertarEstudiante(nuevoEstudiante);
        dbAdapter.close();

        Toast.makeText(getApplicationContext(),"Nuevo estudiante generado",Toast.LENGTH_LONG).show();
    }
}
