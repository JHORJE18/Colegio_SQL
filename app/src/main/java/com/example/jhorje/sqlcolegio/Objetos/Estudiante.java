package com.example.jhorje.sqlcolegio.Objetos;

/**
 * Created by JHORJE on 1/12/17.
 */

public class Estudiante {

    //Variables
    String nombre;
    int edad;
    String ciclo;
    String curso;
    float nota;

    public Estudiante(String nombre, int edad, String ciclo, String curso, float nota) {
        this.nombre = nombre;
        this.edad = edad;
        this.ciclo = ciclo;
        this.curso = curso;
        this.nota = nota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public String getEdadString(){
        return String.valueOf(this.edad);
    }
}
