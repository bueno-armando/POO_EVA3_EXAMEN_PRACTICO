package pkg1_sistema_gestion_estudiantes;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author IceCreamSandwich
 */
public class Main {

    public static void main(String[] args) {
        StudentManagementSystem system = new StudentManagementSystem();
        
        //1. Crear varios objetos de estudiante
        Estudiante estu1 = new Estudiante("Juan Jano", 12, "Tercero");
        Estudiante estu2 = new Estudiante("Carlos Contreras", 4, "Primero");
        Estudiante estu3 = new Estudiante("Alejandro Armendariz", 23, "Séptimo");
        
        
        //2. Agregarlos al sistema usando addStudent()
        system.addStudent(estu1);
        system.addStudent(estu2);
        system.addStudent(estu3);
        
        
        //3. Almacenar los datos usando storeData()
        try {
            
            system.storeData("Estudiantes.bin");
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //4. Eliminar todos los objetos (se creo un nuevo método)
        system.removeData();
    
        //5. Cargar los datos usando loadData()
        try {
            system.loadData("Estudiantes.bin");               
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //6. Obtener info. de un estudiante específico
        system.getStudent(4);
    }

}
