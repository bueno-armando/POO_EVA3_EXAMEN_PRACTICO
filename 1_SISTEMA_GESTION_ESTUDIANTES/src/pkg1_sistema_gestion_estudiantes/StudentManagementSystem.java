package pkg1_sistema_gestion_estudiantes;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author IceCreamSandwich
 */
public class StudentManagementSystem {
    private Estudiante[] stud = new Estudiante[101];
    Queue<Integer> queue = new LinkedList<>();
    
    
    public void addStudent(Estudiante estudiante){
        stud[estudiante.getNumLista()] = estudiante;
        queue.add(estudiante.getNumLista());
    }
    
    public void getStudent(int rollNumber){
        System.out.println("Nombre: "+stud[rollNumber].getNombre());
        System.out.println("Núm. lista: "+stud[rollNumber].getNumLista());
        System.out.println("Grado: "+stud[rollNumber].getGrado());
    }
    
    public void storeData(String filename) throws FileNotFoundException, IOException{
        //guardar los datos de los estudiantes a un archivo
        FileOutputStream fileOut = new FileOutputStream(filename);
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        //Escribir objetos al stream usando la
        //cola con los números de estudiantes
        int numLista;
        while(!queue.isEmpty()){
            numLista = queue.poll();
            objectOut.writeObject(stud[numLista]);
        }
        System.out.println("Lista de estudiantes guardada exitosamente");
        fileOut.close();
    }
    
    public void loadData(String filename) throws FileNotFoundException, IOException, ClassNotFoundException{
        //cargar los datos de los estudiantes desde un archivo
        FileInputStream openFile = new FileInputStream(filename);
        ObjectInputStream oiStream = new ObjectInputStream(openFile);
        
        
        try {
            while (true) {
                Object obj = oiStream.readObject(); //objeto leído
                if (obj instanceof Estudiante) {
                    Estudiante readStud = (Estudiante)obj;
                    stud[readStud.getNumLista()] = readStud;
                }
            }
        } catch (EOFException ignored) {
        }

        oiStream.close();
        System.out.println("Lista de estudiantes recuperada exitosamente");
    }
    
    public void removeData(){
        //Crea el arreglo de nuevo, eliminando los objetos que tenía
        stud = new Estudiante[101];
        System.out.println("Se han reiniciado los datos de estudiantes");
    }
    
}
