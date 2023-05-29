/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package examen3_p2_icecreamsandwich;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFileChooser;

/**
 *
 * @author galle
 */
public class EXAMEN3_P2_ICECREAMSANDWICH {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
       Inventario inventario = new Inventario (); 
        Scanner scanner = new Scanner (System.in); 
        
        boolean continuar = true; 
        while (continuar){
            System.out.println("m e n ú");
            System.out.println("1.- agregar producto ");
            System.out.println("2.- eliminar producto ");
            System.out.println("3.- buscar producto: ");
            System.out.println("4.- mostrar inventario: ");
            System.out.println("5.- guardar inventario en archivos: ");
            System.out.println("6.- cargar inventario desde archivos: ");
            System.out.println("7.- salir");
            int opcion = scanner.nextInt(); 
            scanner.nextLine(); //al momento de leer la opcion limpia el menu 
            
            switch (opcion){
                case 1: 
                    System.out.print("ID del producto: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el salto de línea después de leer el número
                    System.out.print("nombre del producto: ");
                    String nombre = scanner.nextLine();
                    System.out.print("precio del producto: ");
                    double precio = scanner.nextDouble();
                    System.out.print("cantidad del producto: ");
                    int cantidad = scanner.nextInt();
                    Producto producto = new Producto(id, nombre, precio, cantidad);
                    inventario.addProduct(producto);
                    break;
                case 2: 
                    System.out.println("ID del producto que vas a eliminar");
                    int iDEliminar = scanner.nextInt(); 
                    inventario.removeProduct(iDEliminar);
                    break; 
                case 3:
                    System.out.print("ID del producto que quieres encontrar");
                    int iDBuscar = scanner.nextInt(); 
                    Producto productoEncontrado = inventario.searchProducto(iDBuscar); 
                    if (productoEncontrado !=null){
                        System.out.print("Producto Encontrado: "+productoEncontrado);
                    }else {
                        System.out.print("No hay producto con ese ID");
                    }
                    break; 
                case 4: 
                    inventario.dInventory();
                    break; 
                case 5: 
                    System.out.print("Mombre del Archivo para guardar ");
                    String ArchGuardar = scanner.next(); 
                    inventario.saveToFiles(ArchGuardar);
                    break; 
                case 6: 
                    System.out.print("Nombre del inventario que desea cargar");
                    String ArchCargar = scanner.next(); 
                    inventario.loadFromFile(ArchCargar);
                    break; 
                case 7: 
                    continuar = false; 
                    break; 
                default: 
                    System.out.print("Esta opcion no existe, ingrese una nueva opción");
                 
            }
            
        }
        scanner.close();
        System.out.print("see ya \n");
    }
    
}

class Producto {
    private String nombre;
    private int id;
    private int cantidad;
    private double precio;
    
     public Producto(int id1, String nombre1, double precio1, int cantidad1) {
        this.nombre = "";
        this.id = 0;
        this.cantidad = 0;
        this.precio = 0;
    }

    public Producto(String nombre, int id, int cantidad, double precio) {
        this.nombre = nombre;
        this.id = id;
        this.cantidad = cantidad;
        this.precio = precio;
    }
// get and set
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}

class Inventario {
    private Producto [] inventario;
    private int cProducto;
    private List <Producto> productos;
    public Inventario (){
        inventario = new Producto [10];
        cProducto = 0;
        productos = new ArrayList<> ();
    } 
    
    /////////////////////////////////////////////////
    public void addProduct (Producto producto) {
    if (cProducto < 10){
        inventario [cProducto] = producto;
        cProducto++;
        System.out.println("Producto agregado");
        
    }else {
        System.out.println("0");
    }
}
    
    //////////////////////////////////////////////////////
    public void removeProduct (int producto){
        for (int i =0; i <cProducto; i++){
            if (inventario [i].getId()==producto){
                for (int j =i; j <cProducto - 1;j++ ){
                        inventario [j]= inventario [j+1]; 
                    }
                    inventario [cProducto-1]= null; 
                    cProducto--; 
                    System.out.println("Producto eliminado");
                    return; 
            }
        } System.out.println("No se encontró articulo con ese ID");
    }
    
    /////////////////////////////////////////////////////////////
         public Producto searchProducto (int productoId){
        for (int i = 0; i<cProducto; i++){
            if (inventario [i].getId()==productoId){
                return inventario [i]; 
            }
        }
        return null; 
    }
         
         ///////////////////////////////////////
          public void dInventory(){
        if (cProducto>0){
            System.out.println("----inventory----");
            for (int i = 0; i<cProducto; i++){
                System.out.println(inventario[i]);
            }
        }else{
            System.out.println("No hay ningun articulo");
        }
    }
    
     /////////////////////////////////////////////////////////
          public void saveToFiles(String FileName) throws IOException{
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FileName))) {
             JFileChooser fileChooser = new JFileChooser("C:\\archivos\\");
             int resu = fileChooser.showOpenDialog(fileChooser);
        if(resu == JFileChooser.APPROVE_OPTION){
            
            for (int i = 0; i < cProducto; i++) {
                Producto producto = inventario[i];
                String line = producto.getId() + "," + producto.getNombre()+ "," + producto.getPrecio() + "," + producto.getCantidad();
                writer.write(line);
                writer.newLine();
            }
        }
            System.out.println("Inventario guardado con exito en: " + FileName);
        } catch (IOException e) {
            System.out.println("Error"); 
        }
    }
    /////////////////////////////////////////////////////////////////    /////////////////////////////////////////////////////////////////
      public void loadFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0]);
                    String nombre = parts[1];
                    double precio = Double.parseDouble(parts[2]);
                    int cantidad = Integer.parseInt(parts[3]);
                    Producto producto = new Producto(id, nombre, precio, cantidad);
                    productos.add(producto); 
                }
            }
            System.out.println("Inventario cargado desde el archivo " + filename);
        } catch (IOException e) {
            System.out.println("Error al cargar el inventario desde el archivo " + filename);
        }
    }
}