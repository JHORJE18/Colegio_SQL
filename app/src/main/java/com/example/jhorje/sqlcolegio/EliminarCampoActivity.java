package com.example.jhorje.sqlcolegio;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class EliminarCampoActivity extends AppCompatActivity {

    //Variables
    private MyDBAdapter dbAdapter;
    EditText editID;
    Button btnBorrar;
    TextView txtTitulo;
    RadioGroup radioGroup;
    RadioButton rbEstudiante, rbProfesor;
    boolean estudiante;
    boolean todo;
    CheckBox cbBorrarTODO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_campo);

        //Conexion Vista
        btnBorrar = (Button) findViewById(R.id.btnBorrar);
        editID = (EditText) findViewById(R.id.editID);
        txtTitulo = (TextView) findViewById(R.id.txtEliminar);
        rbEstudiante = (RadioButton) findViewById(R.id.rbEliminarEstudiante);
        rbProfesor = (RadioButton) findViewById(R.id.rbEliminarProfesor);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroupEliminar);
        cbBorrarTODO = (CheckBox) findViewById(R.id.cbBorrarTodo);
        estudiante = true;
        todo = false;

        //Cargamos Base de datos SQLite
        dbAdapter = new MyDBAdapter(this);
        dbAdapter.open();

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

        //Checkbox
        cbBorrarTODO.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    todo = true;
                } else {
                    todo = false;
                }

                refrescarVista();
            }
        });
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnBorrar:
                if (todo){
                    borrarTODO();
                } else {
                    if (estudiante) {
                        borrarEstudiante();
                    } else {
                        borrarProfesor();
                    }
                }
                break;
        }
    }

    public void refrescarVista(){
        //Borrar Todo
        if (todo){
            txtTitulo.setText("ELIMINAR TODO");
            radioGroup.setVisibility(View.GONE);
            editID.setVisibility(View.GONE);
        } else {
            //Estudiante / Profesor
            if (estudiante) {
                txtTitulo.setText("Eliminar Estudiante");
            } else {
                txtTitulo.setText("Eliminar Profesor");
            }
            radioGroup.setVisibility(View.VISIBLE);
            editID.setVisibility(View.VISIBLE);
        }
    }

    private void borrarEstudiante() {
        dbAdapter.eliminarEstudiante(Integer.parseInt(editID.getText().toString()));

        Toast.makeText(getApplicationContext(),"Estudiante eliminado",Toast.LENGTH_LONG).show();
    }

    private void borrarProfesor() {
        dbAdapter.eliminarProfesor(Integer.parseInt(editID.getText().toString()));

        Toast.makeText(getApplicationContext(),"Profesor eliminado",Toast.LENGTH_LONG).show();
    }

    private void borrarTODO(){
        dbAdapter.eliminarBBDD();

        Toast.makeText(getApplicationContext(),"Base de datos eliminada!",Toast.LENGTH_LONG).show();
    }
}
