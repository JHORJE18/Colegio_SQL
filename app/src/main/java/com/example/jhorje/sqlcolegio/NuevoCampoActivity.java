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

import com.example.jhorje.sqlcolegio.Objetos.Asignatura;
import com.example.jhorje.sqlcolegio.Objetos.Estudiante;
import com.example.jhorje.sqlcolegio.Objetos.Profesor;

public class NuevoCampoActivity extends AppCompatActivity {

    //Variables
    private MyDBAdapter dbAdapter;
    EditText editNombre, editEdad, editCiclo, editCurso, editNota, editDespacho, editHoras;
    Button btnGuardar;
    RadioGroup radioGroup;
    RadioButton rbEstudiante, rbProfesor, rbAsignatura;
    TextView txtTitulo;
    int nuevo;          /*Representa que estamos registrando
                            1 = Estudiante
                            2 = Profesor
                            3 = Asignatura
                         */

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
        editHoras = (EditText) findViewById(R.id.editHoras);
        rbAsignatura = (RadioButton) findViewById(R.id.rbNuevaAsignatura);
        nuevo = 1;

        //Cargamos Base de datos SQLite
        dbAdapter = new MyDBAdapter(this);

        //Radio Buttons
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (rbEstudiante.isChecked()){
                    nuevo = 1;
                } else if (rbProfesor.isChecked()){
                    nuevo = 2;
                } else if (rbAsignatura.isChecked()){
                    nuevo = 3;
                }

                refrescarVista();
            }
        });
    }
    
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnGuardar:
                switch (nuevo){
                    case 1:
                        nuevoEstudiante();
                        break;
                    case 2:
                        nuevoProfesor();
                        break;
                    case 3:
                        nuevaAsignatura();
                        break;
                }
                break;
        }
    }

    public void refrescarVista(){
        switch (nuevo){
            case 1:
                editDespacho.setVisibility(View.GONE);
                editNota.setVisibility(View.VISIBLE);
                editHoras.setVisibility(View.GONE);
                editCiclo.setVisibility(View.VISIBLE);
                editCurso.setVisibility(View.VISIBLE);
                editEdad.setVisibility(View.VISIBLE);
                txtTitulo.setText("Nuevo Estudiante");
                break;
            case 2:
                editCiclo.setVisibility(View.VISIBLE);
                editCurso.setVisibility(View.VISIBLE);
                editEdad.setVisibility(View.VISIBLE);
                editDespacho.setVisibility(View.VISIBLE);
                editNota.setVisibility(View.GONE);
                editHoras.setVisibility(View.GONE);
                txtTitulo.setText("Nuevo Profesor");
                break;
            case 3:
                editCiclo.setVisibility(View.GONE);
                editCurso.setVisibility(View.GONE);
                editDespacho.setVisibility(View.GONE);
                editEdad.setVisibility(View.GONE);
                editNota.setVisibility(View.GONE);
                editHoras.setVisibility(View.VISIBLE);
                txtTitulo.setText("Nueva Asignatura");
                break;
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

    private void nuevaAsignatura() {
        //Obtenemos datos
        String nombre = editNombre.getText().toString();
        String horas = editHoras.getText().toString();

        //Creamos objeto asignatura
        Asignatura nuevaAsignatura = new Asignatura(nombre,horas);

        dbAdapter.open();
        dbAdapter.insertarAsignatura(nuevaAsignatura);
        dbAdapter.close();

        Toast.makeText(this, "Nueva asignatura generada", Toast.LENGTH_LONG).show();
    }
}
