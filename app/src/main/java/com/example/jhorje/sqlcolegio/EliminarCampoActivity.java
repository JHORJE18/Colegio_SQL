package com.example.jhorje.sqlcolegio;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jhorje.sqlcolegio.Objetos.Estudiante;

public class EliminarCampoActivity extends AppCompatActivity {

    //Variables
    private MyDBAdapter dbAdapter;
    EditText editID;
    Button btnBorrar;
    TextView txtTitulo;
    RadioGroup radioGroup;
    RadioButton rbEstudiante, rbProfesor, rbAsignatura;
    int elimina;        /* Para referenciar a que dato eliminar
                            1 = Estudiante
                            2 = Profesor
                            3 = Asignatura
                        */
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
        rbAsignatura = (RadioButton) findViewById(R.id.rbEliminarAsignatura);
        cbBorrarTODO = (CheckBox) findViewById(R.id.cbBorrarTodo);
        elimina = 1;
        todo = false;

        //Cargamos Base de datos SQLite
        dbAdapter = new MyDBAdapter(this);
        dbAdapter.open();

        //Radio Buttons
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (rbEstudiante.isChecked()){
                    elimina = 1;
                } else if (rbProfesor.isChecked()) {
                    elimina = 2;
                } else if (rbAsignatura.isChecked()){
                    elimina = 3;
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
        refrescarVista();
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnBorrar:
                if (todo){
                    borrarTODO();
                } else {
                    switch (elimina){
                        case 1:
                            borrarEstudiante();
                            break;
                        case 2:
                            borrarProfesor();
                            break;
                        case 3:
                            borrarAsignatura();
                            break;
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
            switch (elimina){
                case 1:
                    txtTitulo.setText("Eliminar Estudiante");
                    break;
                case 2:
                    txtTitulo.setText("Eliminar Profesor");
                    break;
                case 3:
                    txtTitulo.setText("Eliminar Asignatura");
                    break;
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

    private void borrarAsignatura(){
        dbAdapter.eliminarAsignatura(Integer.parseInt(editID.getText().toString()));

        Toast.makeText(this, "Asignatura eliminada", Toast.LENGTH_SHORT).show();
    }

    private void borrarTODO(){
        dbAdapter.eliminarBBDD();

        Toast.makeText(getApplicationContext(),"Base de datos eliminada!",Toast.LENGTH_LONG).show();
    }
}
