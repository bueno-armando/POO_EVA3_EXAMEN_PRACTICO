package pkg1_sistema_gestion_estudiantes;

import java.io.Serializable;

/**
 *
 * @author IceCreamSandwich
 */
public class Estudiante implements Serializable {
    private String nombre;
    private int numLista;
    private String grado;

    public Estudiante() {
    }

    public Estudiante(String nombre, int numLista, String grado) {
        this.nombre = nombre;
        this.numLista = numLista;
        this.grado = grado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumLista() {
        return numLista;
    }

    public void setNumLista(int numLista) {
        this.numLista = numLista;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }
        
}
